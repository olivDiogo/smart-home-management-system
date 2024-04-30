package smarthome.persistence.jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;
import smarthome.domain.repository.IUnitRepository;
import smarthome.domain.unit.Unit;
import smarthome.domain.value_object.UnitID;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.jpa.data_model.UnitDataModel;

public class UnitRepositoryJPAImpl implements IUnitRepository {

  private final IDataModelAssembler<UnitDataModel, Unit> dataModelConverter;

  private final EntityManagerFactory factory;

  /**
   * RepositoryUninJPAImpl constructor
   *
   * @param dataModelAssembler IDataModelAssembler<UnitDataModel, Unit>
   */
  public UnitRepositoryJPAImpl(IDataModelAssembler<UnitDataModel, Unit> dataModelAssembler) {
    validateDataModelConverter(dataModelAssembler);
    dataModelConverter = dataModelAssembler;
    factory = Persistence.createEntityManagerFactory("smarthome");
  }

  /**
   * Method to validate unit data model converter
   *
   * @param entity IDataModelConverter<UnitDataModel, Unit>
   */
  private void validateDataModelConverter(IDataModelAssembler<UnitDataModel, Unit> entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Data model assembler cannot be null.");
    }
  }

  /**
   * Method to get entity manager
   *
   * @return EntityManager
   */
  private EntityManager getEntityManager() {
    EntityManager manager = factory.createEntityManager();
    return manager;
  }

  /**
   * Method to save unit
   *
   * @param unit is the domain entity to be saved.
   * @return Unit
   */
  @Override
  public Unit save(Unit unit) {
    if (unit == null) {
      throw new IllegalArgumentException("Unit cannot be null");
    }
    UnitDataModel unitDataModel = new UnitDataModel(unit);
    EntityManager em = getEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      em.persist(unitDataModel);
      tx.commit();
    } catch (RuntimeException e) {
      if (tx.isActive()) {
        tx.rollback();
      }
      throw e;
    } finally {
      em.close();
    }
    return unit;
  }

  /**
   * Method to find all units
   *
   * @return List<Unit>
   */
  @Override
  public List<Unit> findAll() {
    EntityManager entityManager = getEntityManager();
    try {
      Query query = entityManager.createQuery(
          "SELECT e FROM UnitDataModel e");
      List<UnitDataModel> listDataModel = query.getResultList();
      List<Unit> listDomain = dataModelConverter.toDomain(listDataModel);
      return listDomain;
    } finally {
      entityManager.close();
    }
  }

  /**
   * Method to find unit by identity
   *
   * @param objectID is the identity of the unit
   * @return Optional<Unit>
   */
  @Override
  public Optional<Unit> ofIdentity(UnitID objectID) {
    EntityManager entityManager = getEntityManager();
    try {
      UnitDataModel unitDataModel = entityManager.find(UnitDataModel.class, objectID);
      if (unitDataModel == null) {
        return Optional.empty();
      } else {
        Unit unit = dataModelConverter.toDomain(unitDataModel);
        return Optional.of(unit);
      }
    } finally {
      entityManager.close();
    }
  }

  /**
   * Method to check if unit contains identity
   *
   * @param objectID is the identity of the unit
   * @return boolean
   */
  @Override
  public boolean containsOfIdentity(UnitID objectID) {
    Optional<Unit> unitDataModel = ofIdentity(objectID);
    return unitDataModel.isPresent();
  }
}