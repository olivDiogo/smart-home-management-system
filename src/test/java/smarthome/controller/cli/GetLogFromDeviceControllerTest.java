package smarthome.controller.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smarthome.ddd.IAssembler;
import smarthome.domain.device.Device;
import smarthome.domain.device.DeviceFactoryImpl;
import smarthome.domain.device.IDeviceFactory;
import smarthome.domain.house.House;
import smarthome.domain.house.HouseFactoryImpl;
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.log.ILogFactory;
import smarthome.domain.log.Log;
import smarthome.domain.log.LogFactoryImpl;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.repository.ILogRepository;
import smarthome.domain.repository.IRoomRepository;
import smarthome.domain.room.IRoomFactory;
import smarthome.domain.room.Room;
import smarthome.domain.room.RoomFactoryImpl;
import smarthome.domain.service.IDeviceService;
import smarthome.domain.service.IHouseService;
import smarthome.domain.service.ILogService;
import smarthome.domain.service.IRoomService;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceStatus;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.postal_code.IPostalCodeFactory;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.domain.value_object.ReadingValue;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.UnitID;
import smarthome.mapper.LogAssembler;
import smarthome.persistence.mem.LogRepository;
import smarthome.service.DeviceServiceImpl;
import smarthome.service.HouseServiceImpl;
import smarthome.service.LogServiceImpl;
import smarthome.service.RoomServiceImpl;
import smarthome.utils.dto.LogDTO;
import smarthome.utils.dto.LogDataDTO;

class GetLogFromDeviceControllerTest {

  private ILogRepository logRepository;
  private IDeviceRepository deviceRepository;
  private IRoomRepository roomRepository;
  private IHouseRepository houseRepository;
  private IHouseService houseServiceImpl;
  private IRoomService roomService;
  private IDeviceService deviceService;
  private IPostalCodeFactory postalCodeFactory;
  private GetLogFromDeviceController getLogFromDeviceController;

  /**
   * These changes ensure that each test has access to the necessary instances that are
   * reinitialized before each test, maintaining isolation between tests and ensuring that the state
   * of one test does not affect another.
   */

  @BeforeEach
  void setup() {
    logRepository = mock(LogRepository.class);
    ILogService logService = new LogServiceImpl(logRepository);
    IAssembler<Log, LogDTO> logAssembler = new LogAssembler();
    deviceRepository = mock(IDeviceRepository.class);
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    houseRepository = mock(IHouseRepository.class);
    IHouseFactory houseFactory = new HouseFactoryImpl();
    houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);
    roomService = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
    deviceService = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
    postalCodeFactory = new PostalCodeFactory();
    getLogFromDeviceController = new GetLogFromDeviceController(logService, logAssembler);
  }

  private House createHouse() {
    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";

    Address newAddress =
        new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    House house = houseServiceImpl.addHouse(newAddress, newGPS);
    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    return house;
  }

  private Room createRoom(HouseID id) {
    String name1 = "Quarto do Joao";
    RoomName roomName1 = new RoomName(name1);

    int width = 10;
    int length = 10;
    int height = 10;
    Dimension dimension = new Dimension(width, length, height);

    int floor = 2;
    RoomFloor roomFloor = new RoomFloor(floor);

    Room room = roomService.addRoom(id, roomName1, dimension, roomFloor);
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));
    return room;
  }

  private Device createDevice(RoomID id) {
    String name = "Lampada";
    DeviceName deviceName = new DeviceName(name);
    DeviceStatus deviceStatus = new DeviceStatus(true);
    DeviceTypeID deviceTypeID = new DeviceTypeID("1");

    Device device = deviceService.addDevice(id, deviceName, deviceStatus, deviceTypeID);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));
    return device;
  }


  /**
   * Test getLogFromDevice method.
   */
  @Test
  void shouldGetLogFromDevice_WhenParametersAreValid() {
    // Arrange
    // Add a house
    House house = createHouse();

    // Add a room
    Room room = createRoom(house.getID());

    // Add a device
    Device device = createDevice(room.getID());
    DeviceID deviceID = device.getID();

    // Add a log
    ILogFactory logFactory = new LogFactoryImpl();
    LocalDateTime timeStamp = LocalDateTime.of(2021, 5, 1, 12, 0);
    ReadingValue readingValue = new ReadingValue("20");
    SensorID sensorID = new SensorID("1");
    SensorTypeID sensorTypeID = new SensorTypeID("Temperature");
    UnitID unitID = new UnitID("C");
    Log log =
        logFactory.createLog(
            device.getID(), sensorID, timeStamp, readingValue, sensorTypeID, unitID);
    logRepository.save(log);

    // Create LogDataDTO
    String timeStart = "2020-03-01T13:45:30";
    String timeEnd = "2022-03-01T13:50:30";
    LogDataDTO logDataDTO = new LogDataDTO(deviceID.toString(), timeStart, timeEnd);
    DatePeriod datePeriod = new DatePeriod(LocalDateTime.parse(timeStart),
        LocalDateTime.parse(timeEnd));

    when(logRepository.findByDeviceIDAndDatePeriodBetween(deviceID, datePeriod)).thenReturn(
        List.of(log));

    // Act
    List<LogDTO> logs = getLogFromDeviceController.getLogFromDevice(logDataDTO);

    // Assert
    assertEquals(1, logs.size());
    assertEquals(log.getID().getID(), logs.get(0).logID);
  }

  /**
   * Test getLogFromDevice method when timeStart is after timeEnd.
   */
  @Test
  void shouldReturnInvalidTimePeriod_WhenTimeStartIsAfterTimeEnd() {
    // Arrange
    // Add a house
    House house = createHouse();

    // Add a room
    createRoom(house.getID());

    // Add a log
    ILogFactory logFactory = new LogFactoryImpl();
    LocalDateTime timeStamp = LocalDateTime.of(2021, 5, 1, 12, 0);
    ReadingValue readingValue = new ReadingValue("20");
    SensorID sensorID = new SensorID("1");
    SensorTypeID sensorTypeID = new SensorTypeID("Temperature");
    UnitID unitID = new UnitID("C");
    DeviceID deviceID = new DeviceID("2");
    Log log =
        logFactory.createLog(deviceID, sensorID, timeStamp, readingValue, sensorTypeID, unitID);
    logRepository.save(log);

    // Create LogDataDTO
    String timeEnd = "2020-03-01T13:45:30";
    String timeStart = "2022-03-01T13:50:30";
    LogDataDTO logDataDTO = new LogDataDTO(deviceID.toString(), timeStart, timeEnd);

    String expected = "Start date cannot be after end date.";

    // Act & Assert
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> getLogFromDeviceController.getLogFromDevice(logDataDTO));
    assertEquals(expected, exception.getMessage());
  }

  /**
   * Test when no measurements are available for the given period.
   */
  @Test
  void shouldReturnNoMeasurementsAvailable_WhenNoMeasurementsForGivenPeriod() {
    // Arrange
    // Add a house
    House house = createHouse();

    // Add a room
    Room room = createRoom(house.getID());

    // Add a device
    Device device = createDevice(room.getID());
    DeviceID deviceID = device.getID();

    // Add a log
    ILogFactory logFactory = new LogFactoryImpl();
    LocalDateTime timeStamp = LocalDateTime.of(2021, 5, 1, 12, 0);
    ReadingValue readingValue = new ReadingValue("20");
    SensorID sensorID = new SensorID("1");
    SensorTypeID sensorTypeID = new SensorTypeID("Temperature");
    UnitID unitID = new UnitID("C");
    DeviceID deviceID2 = new DeviceID("2");
    Log log =
        logFactory.createLog(deviceID2, sensorID, timeStamp, readingValue, sensorTypeID, unitID);
    logRepository.save(log);

    // Create LogDataDTO
    String timeStart = "2020-03-01T13:45:30";
    String timeEnd = "2022-03-01T13:50:30";
    LogDataDTO logDataDTO = new LogDataDTO(deviceID.toString(), timeStart, timeEnd);

    // Act
    List<LogDTO> logs = getLogFromDeviceController.getLogFromDevice(logDataDTO);

    // Assert
    assertEquals(1, logs.size());
    assertEquals("No logs found", logs.get(0).logID);
  }

  /**
   * Test when 2 devices are created, one log is created for Device 1, one logs fo Device 2
   * Controller calls for Device 2 Get logs should return a list with only one element.
   */
  @Test
  void shouldReturnLogFromCorrectDeviceOnly_WhenThereAreMultipleDevicesInRoom() {
    // Arrange
    // Add a house
    House house = createHouse();

    // Add a room
    Room room = createRoom(house.getID());

    // Add a device
    Device device = createDevice(room.getID());
    DeviceID deviceID = device.getID();
    Device deviceTwo = createDevice(room.getID());
    DeviceID deviceIDTwo = deviceTwo.getID();

    // Add a log
    ILogFactory logFactory = new LogFactoryImpl();
    LocalDateTime timeStamp = LocalDateTime.of(2021, 5, 1, 12, 0);
    ReadingValue readingValue = new ReadingValue("20");
    SensorID sensorID = new SensorID("1");
    SensorTypeID sensorTypeID = new SensorTypeID("Temperature");
    UnitID unitID = new UnitID("C");
    Log log =
        logFactory.createLog(deviceID, sensorID, timeStamp, readingValue, sensorTypeID, unitID);
    logRepository.save(log);

    // Add a log to the second device
    Log logTwo =
        logFactory.createLog(deviceIDTwo, sensorID, timeStamp, readingValue, sensorTypeID, unitID);
    logRepository.save(logTwo);

    // Create LogDataDTO
    String timeStart = "2020-03-01T13:45:30";
    String timeEnd = "2022-03-01T13:50:30";
    LogDataDTO logDataDTO = new LogDataDTO(deviceID.toString(), timeStart, timeEnd);

    int expected = 1;

    // Act
    List<LogDTO> logs = getLogFromDeviceController.getLogFromDevice(logDataDTO);

    // Assert
    assertEquals(expected, logs.size());
  }

  /**
   * Should throw a IllegalArgumentException when the log service is null.
   */
  @Test
  void shouldThrowExceptionWhenLogServiceIsNull() {
    String expected = "Log Service is required";

    //Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new GetLogFromDeviceController(null, mock(IAssembler.class)));

    assertEquals(expected, exception.getMessage());
  }

  /**
   * Should throw a IllegalArgumentException when the log assembler is null.
   */
  @Test
  void shouldThrowExceptionWhenLogAssemblerIsNull() {
    String expected = "Log Assembler is required";

    //Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new GetLogFromDeviceController(mock(ILogService.class), null));

    assertEquals(expected, exception.getMessage());
  }
}