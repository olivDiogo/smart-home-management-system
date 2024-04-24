package smart_home.persistence.jpa.repository;

import jakarta.persistence.*;
import smart_home.domain.repository.ISensorRepository;
import smart_home.domain.sensor.ISensor;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.SensorDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.SensorID;
import smart_home.visitor_pattern.ISensorVisitorForDataModel;

import java.util.List;
import java.util.Optional;

public class SensorRepositoryJPAImp implements ISensorRepository {
    private EntityManagerFactory factory;
    private IDataModelAssembler<SensorDataModel, ISensor> dataModelAssembler;
    private ISensorVisitorForDataModel sensorVisitorForDataModel;

    /**
     * Constructs a new repository instance with the specified data model assembler and sensor visitor for data model.
     *
     * @param dataModelAssembler the converter to transform data models to domain models and vice versa
     * @param sensorVisitorForDataModel the sensor visitor for data model
     */
    public SensorRepositoryJPAImp(IDataModelAssembler<SensorDataModel, ISensor> dataModelAssembler, ISensorVisitorForDataModel sensorVisitorForDataModel) {
        Validator.validateNotNull(dataModelAssembler, "Data model assembler");
        this.dataModelAssembler = dataModelAssembler;
        Validator.validateNotNull(sensorVisitorForDataModel, "Sensor visitor for data model");
        this.sensorVisitorForDataModel = sensorVisitorForDataModel;
        factory = Persistence.createEntityManagerFactory("smart_home");

    }

    /**
     * Get the entity manager
     *
     * @return the entity manager
     */
    private EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    /**
     * Saves the specified sensor entity to the database.
     *
     * @param sensor the sensor entity to save
     * @return the saved sensor entity
     * @throws IllegalArgumentException if the entity is null
     */
    @Override
    public ISensor save(ISensor sensor) {
        Validator.validateNotNull(sensor, "Sensor");
        sensor.accept(sensorVisitorForDataModel);
        SensorDataModel sensorDataModel = sensorVisitorForDataModel.getSensorDataModel();
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(sensorDataModel);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
        return sensor;
    }

    /**
     * Finds all sensor entities in the database.
     *
     * @return a list of all sensor entities
     */
    @Override
    public List<ISensor> findAll() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT e FROM SensorDataModel e");
            List<SensorDataModel> listDataModel = query.getResultList();
            return dataModelAssembler.toDomain(listDataModel);
        } finally {
            em.close();
        }
    }

    /**
     * Finds the sensor entity with the specified identity.
     *
     * @param sensorID the identity of the sensor entity to find
     * @return an optional containing the sensor entity if found, empty otherwise
     */
    @Override
    public Optional<ISensor> ofIdentity(SensorID sensorID)
    {
        EntityManager em = getEntityManager();
        try {
            SensorDataModel sensorDataModel = em.find(SensorDataModel.class, sensorID);
            if (sensorDataModel == null) {
                return Optional.empty();
            }
            ISensor sensor = dataModelAssembler.toDomain(sensorDataModel);
            return Optional.of(sensor);
        } finally {
            em.close();
        }
    }

    /**
     * Checks if the repository contains the specified sensor entity.
     *
     * @param sensorID the identity of the sensor entity to check
     * @return true if the repository contains the sensor entity, false otherwise
     */
    @Override
    public boolean containsOfIdentity(SensorID sensorID) {
        return ofIdentity(sensorID).isPresent();
    }
}
