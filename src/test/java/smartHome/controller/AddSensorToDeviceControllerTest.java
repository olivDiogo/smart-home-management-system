package smartHome.controller;

import smartHome.dto.*;
import smartHome.assembler.*;
import smartHome.domain.device.Device;
import smartHome.domain.device.DeviceFactoryImpl;
import smartHome.domain.house.House;
import smartHome.domain.house.HouseFactoryImpl;
import smartHome.domain.room.RoomFactoryImpl;
import smartHome.domain.room.Room;
import smartHome.domain.sensor.SensorFactoryImpl;
import smartHome.domain.sensorModel.SensorModelFactoryImpl;
import smartHome.domain.sensorModel.SensorModel;
import smartHome.domain.sensorType.SensorTypeFactoryImpl;
import smartHome.domain.sensorType.SensorType;
import smartHome.domain.unit.UnitFactoryImpl;
import smartHome.dto.sensorDataDto.SensorDataGenericDTOImp;
import smartHome.persistence.mem.*;
import smartHome.service.*;
import smartHome.valueObject.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddSensorToDeviceControllerTest {

  /** Test to check if the AddSensorToDeviceController is being created correctly. */
  @Test
  void shouldCreateAddSensorToDeviceController() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();

    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, unitFactory);
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    DeviceAssembler deviceAssembler = new DeviceAssembler();

    // Act
    AddSensorToDeviceController addSensorToDeviceController = new AddSensorToDeviceController(
            roomService,
            roomAssembler,
            deviceService,
            deviceAssembler,
            sensorModelService,
            sensorModelAssembler,
            sensorTypeService,
            sensorTypeAssembler,
            sensorAssembler,
            sensorService);

    // Assert
    Assertions.assertNotNull(addSensorToDeviceController);
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the RoomService is
   * null.
   */
  @Test
  void shouldThrowException_whenRoomServiceIsNull() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();

    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    RoomService roomService = null;
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, unitFactory);
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    DeviceAssembler deviceAssembler = new DeviceAssembler();

    // Act
    try {
      new AddSensorToDeviceController(
              roomService,
              roomAssembler,
              deviceService,
              deviceAssembler,
              sensorModelService,
              sensorModelAssembler,
              sensorTypeService,
              sensorTypeAssembler,
              sensorAssembler,
              sensorService);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid room service.", e.getMessage());
    }
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the RoomAssembler is
   * null.
   */
  @Test
  void shouldThrowException_whenRoomAssemblerIsNull() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = null;
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();

    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, unitFactory);
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    DeviceAssembler deviceAssembler = new DeviceAssembler();

    // Act
    try {
      new AddSensorToDeviceController(
              roomService,
              roomAssembler,
              deviceService,
              deviceAssembler,
              sensorModelService,
              sensorModelAssembler,
              sensorTypeService,
              sensorTypeAssembler,
              sensorAssembler,
              sensorService);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid room assembler.", e.getMessage());
    }
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the DeviceService is
   * null.
   */
  @Test
  void shouldThrowException_whenDeviceServiceIsNull() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();

    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, unitFactory);
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    DeviceService deviceService = null;
    DeviceAssembler deviceAssembler = new DeviceAssembler();

    // Act
    try {
      new AddSensorToDeviceController(
              roomService,
              roomAssembler,
              deviceService,
              deviceAssembler,
              sensorModelService,
              sensorModelAssembler,
              sensorTypeService,
              sensorTypeAssembler,
              sensorAssembler,
              sensorService);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid device service.", e.getMessage());
    }
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the DeviceAssembler is
   * null.
   */
  @Test
  void shouldThrowException_whenDeviceAssemblerIsNull() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();

    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, unitFactory);
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    DeviceAssembler deviceAssembler = null;

    // Act
    try {
      new AddSensorToDeviceController(
              roomService,
              roomAssembler,
              deviceService,
              deviceAssembler,
              sensorModelService,
              sensorModelAssembler,
              sensorTypeService,
              sensorTypeAssembler,
              sensorAssembler,
              sensorService);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid device assembler.", e.getMessage());
    }
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the SensorModelService
   * is null.
   */
  @Test
  void shouldThrowException_whenSensorModelServiceIsNull() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();

    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, unitFactory);
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    DeviceAssembler deviceAssembler = new DeviceAssembler();

    // Act
    String expectedMessage = "Please enter a valid sensor model service.";

    Exception exception =
            assertThrows(
                    IllegalArgumentException.class,
                    () ->
                            new AddSensorToDeviceController(
                                    roomService,
                                    roomAssembler,
                                    deviceService,
                                    deviceAssembler,
                                    null,
                                    sensorModelAssembler,
                                    sensorTypeService,
                                    sensorTypeAssembler,
                                    sensorAssembler,
                                    sensorService));
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the
   * SensorModelAssembler is null.
   */
  @Test
  void shouldThrowException_whenSensorModelAssemblerIsNull() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();

    SensorModelAssembler sensorModelAssembler = null;
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, unitFactory);
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    DeviceAssembler deviceAssembler = new DeviceAssembler();

    // Act
    try {
      new AddSensorToDeviceController(
              roomService,
              roomAssembler,
              deviceService,
              deviceAssembler,
              sensorModelService,
              sensorModelAssembler,
              sensorTypeService,
              sensorTypeAssembler,
              sensorAssembler,
              sensorService);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid sensor model assembler.", e.getMessage());
    }
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the
   * ConfigurationService is null.
   */
  @Test
  void shouldThrowException_whenConfigurationServiceIsNull() {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();

    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    ConfigurationService configurationService = null;
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    DeviceAssembler deviceAssembler = new DeviceAssembler();

    // Act
    try {
      new AddSensorToDeviceController(
              roomService,
              roomAssembler,
              deviceService,
              deviceAssembler,
              sensorModelService,
              sensorModelAssembler,
              sensorTypeService,
              sensorTypeAssembler,
              sensorAssembler,
              sensorService);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid configuration service.", e.getMessage());
    }
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the SensorTypeService
   * is null.
   */
  @Test
  void shouldThrowException_whenSensorTypeServiceIsNull() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();

    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, unitFactory);
    SensorTypeService sensorTypeService = null;
    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    DeviceAssembler deviceAssembler = new DeviceAssembler();

    // Act
    try {
      new AddSensorToDeviceController(
              roomService,
              roomAssembler,
              deviceService,
              deviceAssembler,
              sensorModelService,
              sensorModelAssembler,
              sensorTypeService,
              sensorTypeAssembler,
              sensorAssembler,
              sensorService);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid sensor type service.", e.getMessage());
    }
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the SensorTypeAssembler
   * is null.
   */
  @Test
  void shouldThrowException_whenSensorTypeAssemblerIsNull() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();

    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, unitFactory);
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
    SensorTypeAssembler sensorTypeAssembler = null;
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    DeviceAssembler deviceAssembler = new DeviceAssembler();

    // Act
    try {
      new AddSensorToDeviceController(
              roomService,
              roomAssembler,
              deviceService,
              deviceAssembler,
              sensorModelService,
              sensorModelAssembler,
              sensorTypeService,
              sensorTypeAssembler,
              sensorAssembler,
              sensorService);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid sensor type assembler.", e.getMessage());
    }
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the SensorAssembler is
   * null.
   */
  @Test
  void shouldThrowException_whenSensorAssemblerIsNull() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();

    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, unitFactory);
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorAssembler sensorAssembler = null;
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    DeviceAssembler deviceAssembler = new DeviceAssembler();

    // Act
    try {
      new AddSensorToDeviceController(
              roomService,
              roomAssembler,
              deviceService,
              deviceAssembler,
              sensorModelService,
              sensorModelAssembler,
              sensorTypeService,
              sensorTypeAssembler,
              sensorAssembler,
              sensorService);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid sensor assembler.", e.getMessage());
    }
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the SensorService is
   * null.
   */
  @Test
  void shouldThrowException_whenSensorServiceIsNull() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();

    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, unitFactory);
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService = null;
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    DeviceAssembler deviceAssembler = new DeviceAssembler();

    // Act
    try {
      new AddSensorToDeviceController(
              roomService,
              roomAssembler,
              deviceService,
              deviceAssembler,
              sensorModelService,
              sensorModelAssembler,
              sensorTypeService,
              sensorTypeAssembler,
              sensorAssembler,
              sensorService);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid sensor service.", e.getMessage());
    }
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning an empty list when there are no
   * rooms.
   */
  @Test
  void shouldReturnEmptyList_whenThereAreNoRooms() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();

    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, unitFactory);
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    DeviceAssembler deviceAssembler = new DeviceAssembler();

    AddSensorToDeviceController addSensorToDeviceController =
            new AddSensorToDeviceController(
                    roomService,
                    roomAssembler,
                    deviceService,
                    deviceAssembler,
                    sensorModelService,
                    sensorModelAssembler,
                    sensorTypeService,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorService);

    // Act
    List<RoomDTO> roomDTOList = addSensorToDeviceController.getRooms();

    // Assert
    assertTrue(roomDTOList.isEmpty());
  }

  /** Test to get rooms from a house. */
  @Test
  void shouldGetRoomsFromHouse_WhenGivenValidHouseID() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
    PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    HouseFactoryImpl houseFactory = new HouseFactoryImpl();

    HouseService houseService = new HouseService(houseFactory, houseRepository);
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, unitFactory);
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    DeviceAssembler deviceAssembler = new DeviceAssembler();

    AddSensorToDeviceController addSensorToDeviceController =
            new AddSensorToDeviceController(
                    roomService,
                    roomAssembler,
                    deviceService,
                    deviceAssembler,
                    sensorModelService,
                    sensorModelAssembler,
                    sensorTypeService,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorService);

    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";

    Address newAddress =
            new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    House house = houseService.addHouse(newAddress, newGPS);

    HouseID houseID = house.getID();
    String name2 = "Quarto da Maria";
    RoomName roomName2 = new RoomName(name2);
    String name1 = "Quarto do Joao";
    RoomName roomName1 = new RoomName(name1);

    int width = 10;
    int length = 10;
    int height = 10;
    Dimension dimension = new Dimension(width, length, height);

    int floor = 2;
    RoomFloor roomFloor = new RoomFloor(floor);

    roomService.addRoom(houseID, roomName1, dimension, roomFloor);
    roomService.addRoom(houseID, roomName2, dimension, roomFloor);

    List<Room> rooms = roomRepository.findAll();

    List<RoomDTO> expectedRoomsDTOList = roomAssembler.domainToDTO(rooms);
    String expectedRoomDTOName1 = expectedRoomsDTOList.get(0).roomName;
    String expectedRoomDTOID1 = expectedRoomsDTOList.get(0).roomId;
    String expectedRoomDTOName2 = expectedRoomsDTOList.get(1).roomName;
    String expectedRoomDTOID2 = expectedRoomsDTOList.get(1).roomId;

    List<String> expectedList =
            List.of(expectedRoomDTOName1, expectedRoomDTOID1, expectedRoomDTOName2, expectedRoomDTOID2);

    // Act
    List<RoomDTO> roomsDTOList = addSensorToDeviceController.getRooms();

    String actualRoomDTOName1 = roomsDTOList.get(0).roomName;
    String actualRoomDTOID1 = roomsDTOList.get(0).roomId;
    String actualRoomDTOName2 = roomsDTOList.get(1).roomName;
    String actualRoomDTOID2 = roomsDTOList.get(1).roomId;

    List<String> actualList =
            List.of(actualRoomDTOName1, actualRoomDTOID1, actualRoomDTOName2, actualRoomDTOID2);

    // Assert
    assertEquals(expectedList, actualList);
  }

  /** Throws exception when the room ID does not exist in the repository. */
  @Test
  void shouldThrowException_WhenRoomIDDoesNotExistInRepository()
          throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
    HouseFactoryImpl houseFactory = new HouseFactoryImpl();

    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, unitFactory);
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    DeviceAssembler deviceAssembler = new DeviceAssembler();

    AddSensorToDeviceController addSensorToDeviceController =
            new AddSensorToDeviceController(
                    roomService,
                    roomAssembler,
                    deviceService,
                    deviceAssembler,
                    sensorModelService,
                    sensorModelAssembler,
                    sensorTypeService,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorService);

    String roomID = "123";
    RoomID nonExistentRoomID = new RoomID(roomID);

    String roomName = "Quarto da Maria";
    String dimension = "10x10x10";
    String floor = "2";
    RoomDTO roomDTO = new RoomDTO(roomName, dimension, floor, nonExistentRoomID.toString());

    String expectedMessage = "Room with ID " + nonExistentRoomID + " not found.";

    // Act & Assert
    Exception exception =
            assertThrows(
                    IllegalArgumentException.class,
                    () -> addSensorToDeviceController.getDevicesFromRoom(roomDTO));

    assertEquals(expectedMessage, exception.getMessage());
  }

  /** Test to get devices from a room. */
  @Test
  void shouldGetDevicesFromRoom_WhenParametersAreValid() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
    PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    HouseFactoryImpl houseFactory = new HouseFactoryImpl();

    HouseService houseService = new HouseService(houseFactory, houseRepository);
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, unitFactory);
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    DeviceAssembler deviceAssembler = new DeviceAssembler();

    AddSensorToDeviceController addSensorToDeviceController =
            new AddSensorToDeviceController(
                    roomService,
                    roomAssembler,
                    deviceService,
                    deviceAssembler,
                    sensorModelService,
                    sensorModelAssembler,
                    sensorTypeService,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorService);
    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";

    Address newAddress =
            new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    House house = houseService.addHouse(newAddress, newGPS);

    HouseID houseID = house.getID();
    String name2 = "Quarto da Maria";
    int width = 10;
    int length = 10;
    int height = 10;
    int floor = 2;

    RoomName roomName2 = new RoomName(name2);
    RoomFloor roomFloor = new RoomFloor(floor);
    Dimension dimension = new Dimension(width, length, height);

    Room room2 = roomService.addRoom(houseID, roomName2, dimension, roomFloor);

    RoomID roomID = room2.getID();
    String name1 = "Lampada";
    String nameDevice = "Ar Condicionado";
    DeviceName deviceName = new DeviceName(name1);
    DeviceName deviceName2 = new DeviceName(nameDevice);
    DeviceStatus deviceStatus = new DeviceStatus(true);
    DeviceTypeID deviceTypeID = new DeviceTypeID("1");

    deviceService.addDevice(roomID, deviceName, deviceStatus, deviceTypeID);
    deviceService.addDevice(roomID, deviceName2, deviceStatus, deviceTypeID);

    List<RoomDTO> roomsDTOList = addSensorToDeviceController.getRooms();
    RoomDTO roomDTO = roomsDTOList.get(0);

    List<Device> devices = deviceService.getDevicesByRoomId(roomID);
    List<DeviceDTO> deviceDTOListExpected = deviceAssembler.domainToDTO(devices);

    String expectedDeviceDTOID = deviceDTOListExpected.get(0).deviceID;
    String expectedDeviceDTOName = deviceDTOListExpected.get(0).deviceName;
    String expectedDeviceDTOID2 = deviceDTOListExpected.get(1).deviceID;
    String expectedDeviceDTOName2 = deviceDTOListExpected.get(1).deviceName;

    List<String> expectedDeviceDTOList =
            List.of(
                    expectedDeviceDTOID,
                    expectedDeviceDTOName,
                    expectedDeviceDTOID2,
                    expectedDeviceDTOName2);

    // Act
    List<DeviceDTO> devicesDTOList = addSensorToDeviceController.getDevicesFromRoom(roomDTO);

    String actualDeviceDTOID = devicesDTOList.get(0).deviceID;
    String actualDeviceDTOName = devicesDTOList.get(0).deviceName;
    String actualDeviceDTOID2 = devicesDTOList.get(1).deviceID;
    String actualDeviceDTOName2 = devicesDTOList.get(1).deviceName;

    List<String> actualDeviceDTOList =
            List.of(actualDeviceDTOID, actualDeviceDTOName, actualDeviceDTOID2, actualDeviceDTOName2);

    // Assert
    assertEquals(expectedDeviceDTOList, actualDeviceDTOList);
  }

  @Test
  void shouldGetAvailableSensorModelsList() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
    PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    HouseFactoryImpl houseFactory = new HouseFactoryImpl();

    HouseService houseService = new HouseService(houseFactory, houseRepository);
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, unitFactory);
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    DeviceAssembler deviceAssembler = new DeviceAssembler();

    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitID unit = new UnitID("Celsius");
    SensorType sensorType = sensorTypeService.createSensorType(typeDescription, unit);
    sensorTypeService.saveSensorType(sensorType);

    // Act
    AddSensorToDeviceController addSensorToDeviceController =
            new AddSensorToDeviceController(
                    roomService,
                    roomAssembler,
                    deviceService,
                    deviceAssembler,
                    sensorModelService,
                    sensorModelAssembler,
                    sensorTypeService,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorService);

    SensorTypeDTO sensorTypeDTO = sensorTypeAssembler.domainToDTO(sensorType);

    List<SensorModelDTO> sensorModelDTOList =
            addSensorToDeviceController.getSensorModels(sensorTypeDTO);

    List<SensorModel> sensorModels = sensorModelRepository.findAll();
    List<SensorModelDTO> expectedSensorModelDTOList =
            sensorModelAssembler.domainToDTO(sensorModels);

    // Assert
    assertEquals(
            expectedSensorModelDTOList.get(0).sensorModelID, sensorModelDTOList.get(0).sensorModelID);
  }

  @Test
  void shouldThrowExceptionWhenSensorModelRepositoryIsEmpty() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
    PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    HouseFactoryImpl houseFactory = new HouseFactoryImpl();

    HouseService houseService = new HouseService(houseFactory, houseRepository);
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    DeviceAssembler deviceAssembler = new DeviceAssembler();

    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitDescription unitDescription = new UnitDescription("Celsius");
    UnitID unitID = new UnitID("Celsius");
    UnitSymbol unitSymbol = new UnitSymbol("C");
    UnitService unitService = new UnitService(unitRepository, unitFactory);
    unitService.createAndSaveMeasurementType(unitDescription, unitSymbol);
    SensorType sensorType = sensorTypeService.createSensorType(typeDescription, unitID);
    sensorTypeService.saveSensorType(sensorType);

    // Act
    AddSensorToDeviceController addSensorToDeviceController =
            new AddSensorToDeviceController(
                    roomService,
                    roomAssembler,
                    deviceService,
                    deviceAssembler,
                    sensorModelService,
                    sensorModelAssembler,
                    sensorTypeService,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorService);
    SensorTypeDTO sensorTypeDTO = sensorTypeAssembler.domainToDTO(sensorType);

    IllegalArgumentException exception =
            assertThrows(
                    IllegalArgumentException.class,
                    () -> addSensorToDeviceController.getSensorModels(sensorTypeDTO));

    // Assert
    assertEquals("No sensor models found.", exception.getMessage());
  }

  /**
   * Test if exception is thrown when there are no sensor types in the repository.
   *
   * @throws InstantiationException
   */
  @Test
  void shouldThrowExceptionWhenSensorTypeRepositoryIsEmpty() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();

    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, unitFactory);
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    DeviceAssembler deviceAssembler = new DeviceAssembler();

    // Act
    AddSensorToDeviceController addSensorToDeviceController =
            new AddSensorToDeviceController(
                    roomService,
                    roomAssembler,
                    deviceService,
                    deviceAssembler,
                    sensorModelService,
                    sensorModelAssembler,
                    sensorTypeService,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorService);

    IllegalArgumentException exception =
            assertThrows(
                    IllegalArgumentException.class, () -> addSensorToDeviceController.getSensorTypes());

    // Assert
    assertEquals("No sensor types found.", exception.getMessage());
  }

  /** Test to check if the AddSensorToDeviceController is returning a list of sensor types. */
  @Test
  void shouldReturnListOfSensorTypes_whenSensorTypeRepositoryIsNotEmpty()
          throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, unitFactory);

    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitID unit = new UnitID("Celsius");
    SensorType sensorType = sensorTypeService.createSensorType(typeDescription, unit);
    sensorTypeService.saveSensorType(sensorType);

    // Act
    AddSensorToDeviceController addSensorToDeviceController =
            new AddSensorToDeviceController(
                    roomService,
                    roomAssembler,
                    deviceService,
                    new DeviceAssembler(),
                    sensorModelService,
                    sensorModelAssembler,
                    sensorTypeService,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorService);

    List<SensorTypeDTO> sensorTypeDTOList = addSensorToDeviceController.getSensorTypes();
    List<SensorTypeDTO> sensorTypeDTO =
            sensorTypeAssembler.domainToDTO(sensorTypeRepository.findAll());

    // Assert
    assertEquals(sensorTypeDTO.get(0).sensorTypeID, sensorTypeDTOList.get(0).sensorTypeID);
  }

  /** Test if exception is thrown when the sensor type doesn't exist in the repository. */
  @Test
  void shouldReturnEmptyList_whenThereAreNoSensorTypes() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);
    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, new UnitFactoryImpl());
    DeviceAssembler deviceAssembler = new DeviceAssembler();

    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitID unit = new UnitID("Celsius");
    SensorType sensorType = sensorTypeService.createSensorType(typeDescription, unit);
    sensorTypeService.saveSensorType(sensorType);

    TypeDescription nonExistintTypeDescription = new TypeDescription("Humidity");
    UnitID unitID = new UnitID("Percent");
    SensorType nonExistintSensorType =
            sensorTypeService.createSensorType(nonExistintTypeDescription, unitID);

    SensorTypeDTO sensorTypeDTO = sensorTypeAssembler.domainToDTO(nonExistintSensorType);

    // Act
    AddSensorToDeviceController addSensorToDeviceController =
            new AddSensorToDeviceController(
                    roomService,
                    roomAssembler,
                    deviceService,
                    deviceAssembler,
                    sensorModelService,
                    sensorModelAssembler,
                    sensorTypeService,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorService);

    IllegalArgumentException exception =
            assertThrows(
                    IllegalArgumentException.class,
                    () -> addSensorToDeviceController.getSensorModels(sensorTypeDTO));

    // Assert
    assertEquals(
            "Sensor type with ID " + nonExistintTypeDescription + " not found.",
            exception.getMessage());
  }

  /** Test addSensorToDevice method with valid parameters. Adding temperature sensor. */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForTemperatureSensor() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    HouseFactoryImpl houseFactory = new HouseFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);

    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, new UnitFactoryImpl());
    DeviceAssembler deviceAssembler = new DeviceAssembler();
    HouseService houseService = new HouseService(houseFactory, houseRepository);

    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";

    Address newAddress =
            new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    House house = houseService.addHouse(newAddress, newGPS);

    HouseID houseID = house.getID();

    String name1 = "Quarto do Joao";
    RoomName roomName1 = new RoomName(name1);

    int width = 10;
    int length = 10;
    int height = 10;
    Dimension dimension = new Dimension(width, length, height);

    int floor = 2;
    RoomFloor roomFloor = new RoomFloor(floor);

    Room room = roomService.addRoom(houseID, roomName1, dimension, roomFloor);

    RoomID roomID = room.getID();
    DeviceName deviceName = new DeviceName(name1);
    DeviceStatus deviceStatus = new DeviceStatus(true);
    DeviceTypeID deviceTypeID = new DeviceTypeID("1");

    Device device = deviceService.addDevice(roomID, deviceName, deviceStatus, deviceTypeID);

    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitID unit = new UnitID("Celsius");
    SensorType sensorType = sensorTypeService.createSensorType(typeDescription, unit);
    sensorTypeService.saveSensorType(sensorType);


    String modelPath = "smartHome.domain.sensor.temperatureSensor.TemperatureSensor";
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorDataGenericDTOImp sensorDataGenericDTOImp =
            new SensorDataGenericDTOImp(
                    device.getID().getID(), modelPath, sensorName, sensorType.getID().getID());

    AddSensorToDeviceController addSensorToDeviceController =
            new AddSensorToDeviceController(
                    roomService,
                    roomAssembler,
                    deviceService,
                    deviceAssembler,
                    sensorModelService,
                    sensorModelAssembler,
                    sensorTypeService,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorService);

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /** Test addSensorToDevice method with valid parameters. Adding HumiditySensor sensor. */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForHumiditySensor() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    HouseFactoryImpl houseFactory = new HouseFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);

    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, new UnitFactoryImpl());
    DeviceAssembler deviceAssembler = new DeviceAssembler();
    HouseService houseService = new HouseService(houseFactory, houseRepository);

    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";

    Address newAddress =
            new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    House house = houseService.addHouse(newAddress, newGPS);

    HouseID houseID = house.getID();

    String name1 = "Quarto do Joao";
    RoomName roomName1 = new RoomName(name1);

    int width = 10;
    int length = 10;
    int height = 10;
    Dimension dimension = new Dimension(width, length, height);

    int floor = 2;
    RoomFloor roomFloor = new RoomFloor(floor);

    Room room = roomService.addRoom(houseID, roomName1, dimension, roomFloor);

    RoomID roomID = room.getID();
    DeviceName deviceName = new DeviceName(name1);
    DeviceStatus deviceStatus = new DeviceStatus(true);
    DeviceTypeID deviceTypeID = new DeviceTypeID("1");

    Device device = deviceService.addDevice(roomID, deviceName, deviceStatus, deviceTypeID);

    TypeDescription typeDescription = new TypeDescription("Humidity");
    UnitID unit = new UnitID("Percent");
    SensorType sensorType = sensorTypeService.createSensorType(typeDescription, unit);
    sensorTypeService.saveSensorType(sensorType);


    String modelPath = "smartHome.domain.sensor.humiditySensor.HumiditySensor";
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorDataGenericDTOImp sensorDataGenericDTOImp =
            new SensorDataGenericDTOImp(
                    device.getID().getID(), modelPath, sensorName, sensorType.getID().getID());

    AddSensorToDeviceController addSensorToDeviceController =
            new AddSensorToDeviceController(
                    roomService,
                    roomAssembler,
                    deviceService,
                    deviceAssembler,
                    sensorModelService,
                    sensorModelAssembler,
                    sensorTypeService,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorService);

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /** Test addSensorToDevice method with valid parameters. Adding AveragePowerConsumptionSensor sensor. */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForAveragePowerConsumptionSensor() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    HouseFactoryImpl houseFactory = new HouseFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);

    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, new UnitFactoryImpl());
    DeviceAssembler deviceAssembler = new DeviceAssembler();
    HouseService houseService = new HouseService(houseFactory, houseRepository);

    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";

    Address newAddress =
            new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    House house = houseService.addHouse(newAddress, newGPS);

    HouseID houseID = house.getID();

    String name1 = "Quarto do Joao";
    RoomName roomName1 = new RoomName(name1);

    int width = 10;
    int length = 10;
    int height = 10;
    Dimension dimension = new Dimension(width, length, height);

    int floor = 2;
    RoomFloor roomFloor = new RoomFloor(floor);

    Room room = roomService.addRoom(houseID, roomName1, dimension, roomFloor);

    RoomID roomID = room.getID();
    DeviceName deviceName = new DeviceName(name1);
    DeviceStatus deviceStatus = new DeviceStatus(true);
    DeviceTypeID deviceTypeID = new DeviceTypeID("1");

    Device device = deviceService.addDevice(roomID, deviceName, deviceStatus, deviceTypeID);

    TypeDescription typeDescription = new TypeDescription("AveragePowerConsumption");
    UnitID unit = new UnitID("Watt");
    SensorType sensorType = sensorTypeService.createSensorType(typeDescription, unit);
    sensorTypeService.saveSensorType(sensorType);


    String modelPath = "smartHome.domain.sensor.averagePowerConsumptionSensor.AveragePowerConsumptionSensor";
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorDataGenericDTOImp sensorDataGenericDTOImp =
            new SensorDataGenericDTOImp(
                    device.getID().getID(), modelPath, sensorName, sensorType.getID().getID());

    AddSensorToDeviceController addSensorToDeviceController =
            new AddSensorToDeviceController(
                    roomService,
                    roomAssembler,
                    deviceService,
                    deviceAssembler,
                    sensorModelService,
                    sensorModelAssembler,
                    sensorTypeService,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorService);

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /** Test addSensorToDevice method with valid parameters. Adding Switch sensor. */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForSwitchSensor() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    HouseFactoryImpl houseFactory = new HouseFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);

    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, new UnitFactoryImpl());
    DeviceAssembler deviceAssembler = new DeviceAssembler();
    HouseService houseService = new HouseService(houseFactory, houseRepository);

    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";

    Address newAddress =
            new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    House house = houseService.addHouse(newAddress, newGPS);

    HouseID houseID = house.getID();

    String name1 = "Quarto do Joao";
    RoomName roomName1 = new RoomName(name1);

    int width = 10;
    int length = 10;
    int height = 10;
    Dimension dimension = new Dimension(width, length, height);

    int floor = 2;
    RoomFloor roomFloor = new RoomFloor(floor);

    Room room = roomService.addRoom(houseID, roomName1, dimension, roomFloor);

    RoomID roomID = room.getID();
    DeviceName deviceName = new DeviceName(name1);
    DeviceStatus deviceStatus = new DeviceStatus(true);
    DeviceTypeID deviceTypeID = new DeviceTypeID("1");

    Device device = deviceService.addDevice(roomID, deviceName, deviceStatus, deviceTypeID);

    TypeDescription typeDescription = new TypeDescription("Switch");
    UnitID unit = new UnitID("Watt");
    SensorType sensorType = sensorTypeService.createSensorType(typeDescription, unit);
    sensorTypeService.saveSensorType(sensorType);


    String modelPath = "smartHome.domain.sensor.switchSensor.SwitchSensor";
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorDataGenericDTOImp sensorDataGenericDTOImp =
            new SensorDataGenericDTOImp(
                    device.getID().getID(), modelPath, sensorName, sensorType.getID().getID());

    AddSensorToDeviceController addSensorToDeviceController =
            new AddSensorToDeviceController(
                    roomService,
                    roomAssembler,
                    deviceService,
                    deviceAssembler,
                    sensorModelService,
                    sensorModelAssembler,
                    sensorTypeService,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorService);

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /** Test addSensorToDevice method with valid parameters. Adding DewPointSensor sensor. */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForDewPointSensor() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    HouseFactoryImpl houseFactory = new HouseFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);

    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, new UnitFactoryImpl());
    DeviceAssembler deviceAssembler = new DeviceAssembler();
    HouseService houseService = new HouseService(houseFactory, houseRepository);

    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";

    Address newAddress =
            new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    House house = houseService.addHouse(newAddress, newGPS);

    HouseID houseID = house.getID();

    String name1 = "Quarto do Joao";
    RoomName roomName1 = new RoomName(name1);

    int width = 10;
    int length = 10;
    int height = 10;
    Dimension dimension = new Dimension(width, length, height);

    int floor = 2;
    RoomFloor roomFloor = new RoomFloor(floor);

    Room room = roomService.addRoom(houseID, roomName1, dimension, roomFloor);

    RoomID roomID = room.getID();
    DeviceName deviceName = new DeviceName(name1);
    DeviceStatus deviceStatus = new DeviceStatus(true);
    DeviceTypeID deviceTypeID = new DeviceTypeID("1");

    Device device = deviceService.addDevice(roomID, deviceName, deviceStatus, deviceTypeID);

    TypeDescription typeDescription = new TypeDescription("DewPoint");
    UnitID unit = new UnitID("Celsius");
    SensorType sensorType = sensorTypeService.createSensorType(typeDescription, unit);
    sensorTypeService.saveSensorType(sensorType);


    String modelPath = "smartHome.domain.sensor.dewPointSensor.DewPointSensor";
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorDataGenericDTOImp sensorDataGenericDTOImp =
            new SensorDataGenericDTOImp(
                    device.getID().getID(), modelPath, sensorName, sensorType.getID().getID());

    AddSensorToDeviceController addSensorToDeviceController =
            new AddSensorToDeviceController(
                    roomService,
                    roomAssembler,
                    deviceService,
                    deviceAssembler,
                    sensorModelService,
                    sensorModelAssembler,
                    sensorTypeService,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorService);

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /** Test addSensorToDevice method with valid parameters. Adding Solar Irradiance sensor. */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForSolarIrradianceSensor() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    HouseFactoryImpl houseFactory = new HouseFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);

    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, new UnitFactoryImpl());
    DeviceAssembler deviceAssembler = new DeviceAssembler();
    HouseService houseService = new HouseService(houseFactory, houseRepository);

    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";

    Address newAddress =
            new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    House house = houseService.addHouse(newAddress, newGPS);

    HouseID houseID = house.getID();

    String name1 = "Quarto do Joao";
    RoomName roomName1 = new RoomName(name1);

    int width = 10;
    int length = 10;
    int height = 10;
    Dimension dimension = new Dimension(width, length, height);

    int floor = 2;
    RoomFloor roomFloor = new RoomFloor(floor);

    Room room = roomService.addRoom(houseID, roomName1, dimension, roomFloor);

    RoomID roomID = room.getID();
    DeviceName deviceName = new DeviceName(name1);
    DeviceStatus deviceStatus = new DeviceStatus(true);
    DeviceTypeID deviceTypeID = new DeviceTypeID("1");

    Device device = deviceService.addDevice(roomID, deviceName, deviceStatus, deviceTypeID);

    TypeDescription typeDescription = new TypeDescription("SolarIrradiance");
    UnitID unit = new UnitID("WattBySquareMeter");
    SensorType sensorType = sensorTypeService.createSensorType(typeDescription, unit);
    sensorTypeService.saveSensorType(sensorType);


    String modelPath = "smartHome.domain.sensor.solarIrradianceSensor.SolarIrradianceSensor";
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorDataGenericDTOImp sensorDataGenericDTOImp =
            new SensorDataGenericDTOImp(
                    device.getID().getID(), modelPath, sensorName, sensorType.getID().getID());

    AddSensorToDeviceController addSensorToDeviceController =
            new AddSensorToDeviceController(
                    roomService,
                    roomAssembler,
                    deviceService,
                    deviceAssembler,
                    sensorModelService,
                    sensorModelAssembler,
                    sensorTypeService,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorService);

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /** Test addSensorToDevice method with valid parameters. Adding PercentagePositionSensor sensor. */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForPercentagePositionSensor() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    HouseFactoryImpl houseFactory = new HouseFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);

    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, new UnitFactoryImpl());
    DeviceAssembler deviceAssembler = new DeviceAssembler();
    HouseService houseService = new HouseService(houseFactory, houseRepository);

    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";

    Address newAddress =
            new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    House house = houseService.addHouse(newAddress, newGPS);

    HouseID houseID = house.getID();

    String name1 = "Quarto do Joao";
    RoomName roomName1 = new RoomName(name1);

    int width = 10;
    int length = 10;
    int height = 10;
    Dimension dimension = new Dimension(width, length, height);

    int floor = 2;
    RoomFloor roomFloor = new RoomFloor(floor);

    Room room = roomService.addRoom(houseID, roomName1, dimension, roomFloor);

    RoomID roomID = room.getID();
    DeviceName deviceName = new DeviceName(name1);
    DeviceStatus deviceStatus = new DeviceStatus(true);
    DeviceTypeID deviceTypeID = new DeviceTypeID("1");

    Device device = deviceService.addDevice(roomID, deviceName, deviceStatus, deviceTypeID);

    TypeDescription typeDescription = new TypeDescription("Position");
    UnitID unit = new UnitID("Percent");
    SensorType sensorType = sensorTypeService.createSensorType(typeDescription, unit);
    sensorTypeService.saveSensorType(sensorType);


    String modelPath = "smartHome.domain.sensor.percentagePositionSensor.PercentagePositionSensor";
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorDataGenericDTOImp sensorDataGenericDTOImp =
            new SensorDataGenericDTOImp(
                    device.getID().getID(), modelPath, sensorName, sensorType.getID().getID());

    AddSensorToDeviceController addSensorToDeviceController =
            new AddSensorToDeviceController(
                    roomService,
                    roomAssembler,
                    deviceService,
                    deviceAssembler,
                    sensorModelService,
                    sensorModelAssembler,
                    sensorTypeService,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorService);

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /** Test addSensorToDevice method with valid parameters. Adding Instance Power Consumption sensor. */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForInstantPowerConsumptionSensor() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    HouseFactoryImpl houseFactory = new HouseFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);

    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, new UnitFactoryImpl());
    DeviceAssembler deviceAssembler = new DeviceAssembler();
    HouseService houseService = new HouseService(houseFactory, houseRepository);

    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";

    Address newAddress =
            new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    House house = houseService.addHouse(newAddress, newGPS);

    HouseID houseID = house.getID();

    String name1 = "Quarto do Joao";
    RoomName roomName1 = new RoomName(name1);

    int width = 10;
    int length = 10;
    int height = 10;
    Dimension dimension = new Dimension(width, length, height);

    int floor = 2;
    RoomFloor roomFloor = new RoomFloor(floor);

    Room room = roomService.addRoom(houseID, roomName1, dimension, roomFloor);

    RoomID roomID = room.getID();
    DeviceName deviceName = new DeviceName(name1);
    DeviceStatus deviceStatus = new DeviceStatus(true);
    DeviceTypeID deviceTypeID = new DeviceTypeID("1");

    Device device = deviceService.addDevice(roomID, deviceName, deviceStatus, deviceTypeID);

    TypeDescription typeDescription = new TypeDescription("InstantPowerConsumption");
    UnitID unit = new UnitID("Watt");
    SensorType sensorType = sensorTypeService.createSensorType(typeDescription, unit);
    sensorTypeService.saveSensorType(sensorType);


    String modelPath = "smartHome.domain.sensor.instantPowerConsumptionSensor.InstantPowerConsumptionSensor";
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorDataGenericDTOImp sensorDataGenericDTOImp =
            new SensorDataGenericDTOImp(
                    device.getID().getID(), modelPath, sensorName, sensorType.getID().getID());

    AddSensorToDeviceController addSensorToDeviceController =
            new AddSensorToDeviceController(
                    roomService,
                    roomAssembler,
                    deviceService,
                    deviceAssembler,
                    sensorModelService,
                    sensorModelAssembler,
                    sensorTypeService,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorService);

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /** Test addSensorToDevice method with valid parameters. Adding Percentage Position Sensor. */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForInstantElectricConsumptionSensor () throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    HouseFactoryImpl houseFactory = new HouseFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);

    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    ConfigurationService configurationService =
            new ConfigurationService(
                    sensorModelRepository, unitRepository, sensorModelFactory, new UnitFactoryImpl());
    DeviceAssembler deviceAssembler = new DeviceAssembler();
    HouseService houseService = new HouseService(houseFactory, houseRepository);

    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";

    Address newAddress =
            new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    House house = houseService.addHouse(newAddress, newGPS);

    HouseID houseID = house.getID();

    String name1 = "Quarto do Joao";
    RoomName roomName1 = new RoomName(name1);

    int width = 10;
    int length = 10;
    int height = 10;
    Dimension dimension = new Dimension(width, length, height);

    int floor = 2;
    RoomFloor roomFloor = new RoomFloor(floor);

    Room room = roomService.addRoom(houseID, roomName1, dimension, roomFloor);

    RoomID roomID = room.getID();
    DeviceName deviceName = new DeviceName(name1);
    DeviceStatus deviceStatus = new DeviceStatus(true);
    DeviceTypeID deviceTypeID = new DeviceTypeID("1");

    Device device = deviceService.addDevice(roomID, deviceName, deviceStatus, deviceTypeID);

    TypeDescription typeDescription = new TypeDescription("Position");
    UnitID unit = new UnitID("Percent");
    SensorType sensorType = sensorTypeService.createSensorType(typeDescription, unit);
    sensorTypeService.saveSensorType(sensorType);


    String modelPath = "smartHome.domain.sensor.percentagePositionSensor.PercentagePositionSensor";
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorDataGenericDTOImp sensorDataGenericDTOImp =
            new SensorDataGenericDTOImp(
                    device.getID().getID(), modelPath, sensorName, sensorType.getID().getID());

    AddSensorToDeviceController addSensorToDeviceController =
            new AddSensorToDeviceController(
                    roomService,
                    roomAssembler,
                    deviceService,
                    deviceAssembler,
                    sensorModelService,
                    sensorModelAssembler,
                    sensorTypeService,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorService);

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }
  /** Test addSensorToDevice method with invalid parameters. */
  @Test
  void shouldThrowException_whenParametersAreInvalid() throws InstantiationException {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    HouseFactoryImpl houseFactory = new HouseFactoryImpl();
    SensorRepository sensorRepository = new SensorRepository();
    SensorFactoryImpl sensorFactory = new SensorFactoryImpl();
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorTypeService sensorTypeService =
            new SensorTypeService(sensorTypeRepository, sensorTypeFactory, unitRepository);

    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    SensorModelRepository sensorModelRepository = new SensorModelRepository();
    SensorModelFactoryImpl sensorModelFactory = new SensorModelFactoryImpl();
    SensorModelAssembler sensorModelAssembler = new SensorModelAssembler();
    SensorModelService sensorModelService =
            new SensorModelService(sensorModelRepository, sensorModelFactory);
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
    DeviceService deviceService =
            new DeviceService(deviceRepository, deviceFactory, roomRepository);
    SensorAssembler sensorAssembler = new SensorAssembler();
    SensorService sensorService =
            new SensorService(sensorRepository, sensorFactory, deviceRepository);
    RoomService roomService =
            new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
    new ConfigurationService(
            sensorModelRepository, unitRepository, sensorModelFactory, new UnitFactoryImpl());
    DeviceAssembler deviceAssembler = new DeviceAssembler();
    HouseService houseService = new HouseService(houseFactory, houseRepository);

    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";

    Address newAddress =
            new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    House house = houseService.addHouse(newAddress, newGPS);

    HouseID houseID = house.getID();

    String name1 = "Quarto do Joao";
    RoomName roomName1 = new RoomName(name1);

    int width = 10;
    int length = 10;
    int height = 10;
    Dimension dimension = new Dimension(width, length, height);

    int floor = 2;
    RoomFloor roomFloor = new RoomFloor(floor);

    Room room = roomService.addRoom(houseID, roomName1, dimension, roomFloor);

    RoomID roomID = room.getID();
    DeviceName deviceName = new DeviceName(name1);
    DeviceStatus deviceStatus = new DeviceStatus(true);
    DeviceTypeID deviceTypeID = new DeviceTypeID("1");

    deviceService.addDevice(roomID, deviceName, deviceStatus, deviceTypeID);

    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitID unit = new UnitID("Celsius");
    SensorType sensorType = sensorTypeService.createSensorType(typeDescription, unit);
    sensorTypeService.saveSensorType(sensorType);

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorDataGenericDTOImp sensorDataGenericDTOImp = null;

    AddSensorToDeviceController addSensorToDeviceController =
            new AddSensorToDeviceController(
                    roomService,
                    roomAssembler,
                    deviceService,
                    deviceAssembler,
                    sensorModelService,
                    sensorModelAssembler,
                    sensorTypeService,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorService);

    // Act
    IllegalArgumentException exception =
            assertThrows(
                    IllegalArgumentException.class,
                    () -> addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp));

    // Assert
    assertEquals(exception.getMessage(), "Please enter a valid sensor data DTO.");
  }
}