package smarthome.persistence.spring_data.sensor_model;

import java.util.List;
import java.util.Optional;
import smarthome.domain.repository.ISensorModelRepository;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.jpa.data_model.SensorModelDataModel;
import smarthome.utils.Validator;

public class SensorModelSpringDataRepository implements ISensorModelRepository {

  private final ISensorModelSpringDataRepository repository;
  private final IDataModelAssembler<SensorModelDataModel, SensorModel> assembler;

  /**
   * Constructor of the class.
   *
   * @param repository is the sensor model spring data repository.
   * @param assembler  is the data model assembler.
   */

  public SensorModelSpringDataRepository(ISensorModelSpringDataRepository repository,
      IDataModelAssembler<SensorModelDataModel, SensorModel> assembler) {
    Validator.validateNotNull(repository, "Sensor Model Spring Data Repository");
    Validator.validateNotNull(assembler, "Data Model Assembler");

    this.repository = repository;
    this.assembler = assembler;
  }

  /**
   * Method to save a domain entity.
   *
   * @param sensorModel is the domain entity to be saved.
   * @return the saved domain entity.
   */
  @Override
  public SensorModel save(SensorModel sensorModel) {
    Validator.validateNotNull(sensorModel, "Sensor Model");

    SensorModelDataModel dataModel = new SensorModelDataModel(sensorModel);

    repository.save(dataModel);

    return sensorModel;
  }


  /**
   * Method to find all domain entities.
   *
   * @return list of domain entities
   */
  @Override
  public List<SensorModel> findAll() {
    List<SensorModelDataModel> listSensorModelDataModelSaved = repository.findAll();
    List<SensorModel> listDomain = assembler.toDomain(listSensorModelDataModelSaved);

    return listDomain;
  }

  /**
   * Method to find a domain entity by its identity.
   *
   * @param objectID is the identity of the domain entity.
   * @return the domain entity.
   */

  @Override
  public Optional<SensorModel> ofIdentity(ModelPath objectID) {

    Optional<SensorModelDataModel> sensorModelDataModel = repository.findById(objectID.getID());

    if (sensorModelDataModel.isPresent()) {
      SensorModel domain = assembler.toDomain(sensorModelDataModel.get());
      return Optional.of(domain);
    } else {
      return Optional.empty();
    }
  }


  /**
   * Method to check if a domain entity exists by its identity.
   *
   * @param objectID is the identity of the domain entity.
   * @return true if the domain entity exists, false otherwise.
   */
  @Override
  public boolean containsOfIdentity(ModelPath objectID) {
    return repository.existsById(objectID.getID());
  }

  /**
   * Method to find all sensor models by sensor type id.
   *
   * @param sensorTypeID is the sensor type id.
   * @return list of sensor models.
   */

  @Override
  public List<SensorModel> findBySensorTypeId(SensorTypeID sensorTypeID) {

    List<SensorModelDataModel> listDataModel = repository.findBySensorTypeID(sensorTypeID.getID());

    return assembler.toDomain(listDataModel);

  }
}
