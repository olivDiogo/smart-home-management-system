package smart_home.persistence.jpa.repository;

import jakarta.persistence.*;
import smart_home.domain.actuator.IActuator;
import smart_home.domain.repository.IActuatorRepository;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.ActuatorDataModel;
import smart_home.value_object.ActuatorID;
import smart_home.visitor_pattern.IActuatorVisitorForDataModel;

import java.util.List;
import java.util.Optional;

public class ActuatorRepositoryJPAImp implements IActuatorRepository {
    private EntityManagerFactory _factory;
    private IDataModelAssembler<ActuatorDataModel, IActuator> _dataModelAssembler;
    private IActuatorVisitorForDataModel _actuatorVisitorForDataModel;

    /**
     * Constructor for RepositoryActuatorJPAImp
     * @param dataModelAssembler is the data model assembler
     * @param actuatorVisitorForDataModel is the actuator visitor for data model
     */
    public ActuatorRepositoryJPAImp(IDataModelAssembler<ActuatorDataModel, IActuator> dataModelAssembler, IActuatorVisitorForDataModel actuatorVisitorForDataModel) {
        validateDataModelAssembler(dataModelAssembler);
        _dataModelAssembler = dataModelAssembler;
        _factory = Persistence.createEntityManagerFactory("smart_home");
        validateActuatorVisitorForDataModel(actuatorVisitorForDataModel);
        _actuatorVisitorForDataModel = actuatorVisitorForDataModel;
    }

    /**
     * Method to validate the data model assembler
     * @param dataModelAssembler is the data model assembler
     */
    private void validateDataModelAssembler(IDataModelAssembler<ActuatorDataModel, IActuator> dataModelAssembler) {
        if (dataModelAssembler == null) {
            throw new IllegalArgumentException("Data model converter cannot be null");
        }
    }

    /**
     * Method to validate the actuator visitor for data model
     * @param actuatorVisitorForDataModel is the actuator visitor for data model
     */
    private void validateActuatorVisitorForDataModel(IActuatorVisitorForDataModel actuatorVisitorForDataModel) {
        if (actuatorVisitorForDataModel == null) {
            throw new IllegalArgumentException("Actuator visitor for data model cannot be null");
        }
    }

    /**
     * Saves the specified actuator entity to the database.
     *
     * @param entity is the domain entity to be saved.
     * @return the saved actuator entity
     * @throws IllegalArgumentException if the entity is null
     */
    @Override
    public IActuator save(IActuator entity) {
        if (entity == null) {
            throw new IllegalArgumentException("The provided entity must not be null.");
        }
        entity.accept(_actuatorVisitorForDataModel);
        ActuatorDataModel actuatorDataModel = _actuatorVisitorForDataModel.getActuatorDataModel();
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(actuatorDataModel);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
        return entity;
    }

    /**
     * Retrieves all actuator entities from the database.
     *
     * @return a list of actuator entities
     */
    @Override
    public List<IActuator> findAll() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT e FROM ActuatorDataModel e");
            List<ActuatorDataModel> listDataModel = query.getResultList();
            return _dataModelAssembler.toDomain(listDataModel);
        } finally {
            em.close();
        }
    }

    /**
     * Finds an actuator entity by its identity.
     *
     * @param actuatorID is the identity of the actuator entity
     * @return an optional containing the actuator entity if found, otherwise an empty optional
     */
    @Override
    public Optional<IActuator> ofIdentity(ActuatorID actuatorID) {
        EntityManager em = getEntityManager();
        try {
            ActuatorDataModel actuatorDataModel = em.find(ActuatorDataModel.class, actuatorID);
            if (actuatorDataModel == null) {
                return Optional.empty();
            }
            IActuator actuator = _dataModelAssembler.toDomain(actuatorDataModel);
            return Optional.of(actuator);
        } finally {
            em.close();
        }
    }

    /**
     * Checks if an actuator entity with the specified identity exists in the database.
     *
     * @param actuatorID is the identity of the actuator entity
     * @return true if the actuator entity exists, otherwise false
     */
    @Override
    public boolean containsOfIdentity(ActuatorID actuatorID) {
        return ofIdentity(actuatorID).isPresent();
    }

    /**
     * Returns a new instance of EntityManager.
     *
     * @return a new EntityManager instance
     */
    private EntityManager getEntityManager() {
        return _factory.createEntityManager();
    }
}