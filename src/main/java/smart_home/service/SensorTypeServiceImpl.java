package smart_home.service;

import smart_home.ddd.IRepository;
import smart_home.domain.sensor_type.ISensorTypeFactory;
import smart_home.domain.sensor_type.SensorType;
import smart_home.domain.service.ISensorTypeService;
import smart_home.domain.unit.Unit;
import smart_home.utils.Validator;
import smart_home.value_object.SensorTypeID;
import smart_home.value_object.TypeDescription;
import smart_home.value_object.UnitID;

import java.util.List;
import java.util.Optional;

public class SensorTypeServiceImpl implements ISensorTypeService {

  private IRepository<SensorTypeID, SensorType> sensorTypeRepository;
  private ISensorTypeFactory sensorTypeFactory;
  private IRepository<UnitID, Unit> unitRepository;

  /**
   * Constructor for SensorTypeService.
   *
   * @param sensorTypeRepository is the repository for sensor types.
   * @param sensorTypeFactory    is the factory for sensor types.
   * @param unitRepository       is the repository for units.
   */
  public SensorTypeServiceImpl(IRepository<SensorTypeID, SensorType> sensorTypeRepository,
      ISensorTypeFactory sensorTypeFactory, IRepository<UnitID, Unit> unitRepository) {
    Validator.validateNotNull(sensorTypeRepository, "Sensor type repository");
    this.sensorTypeRepository = sensorTypeRepository;
    Validator.validateNotNull(sensorTypeFactory, "Sensor type factory");
    this.sensorTypeFactory = sensorTypeFactory;
    Validator.validateNotNull(unitRepository, "Unit repository");
    this.unitRepository = unitRepository;

  }

  
  /**
   * Creates a new SensorType.
   *
   * @param name   The description of the sensor type.
   * @param unitID The unit of the sensor type.
   * @return The created SensorType object.
   */
  @Override
  public SensorType createSensorType(TypeDescription name, UnitID unitID) {
    if (!unitRepository.containsOfIdentity(unitID)) {
      throw new IllegalArgumentException("Please enter a valid measurement type.");
    }
    SensorType sensorType = sensorTypeFactory.createSensorType(name, unitID);
    return sensorType;
  }

  /**
   * Saves a SensorType.
   *
   * @param sensorType The SensorType to save.
   * @return The saved SensorType object.
   */
  @Override
  public SensorType addSensorType(SensorType sensorType) {
    if (sensorType == null) {
      throw new IllegalArgumentException("Please enter a valid sensor type.");
    }
    return sensorTypeRepository.save(sensorType);
  }

  /**
   * Finds a SensorType by its ID.
   *
   * @param sensorTypeID The ID of the SensorType to find.
   * @return The SensorType object.
   */
  @Override
  public Optional<SensorType> getSensorTypeByID(SensorTypeID sensorTypeID) {
    if (sensorTypeID == null) {
      throw new IllegalArgumentException("Please enter a valid sensor type ID.");
    }
    return sensorTypeRepository.ofIdentity(sensorTypeID);
  }

  /**
   * Finds all SensorTypes.
   *
   * @return A list of all SensorType objects.
   */
  @Override
  public List<SensorType> getAllSensorTypes() {
    return sensorTypeRepository.findAll();
  }
}
