package smart_home.persistence.assembler;

import org.junit.jupiter.api.Test;
import smart_home.domain.log.ILogFactory;
import smart_home.domain.log.Log;
import smart_home.persistence.jpa.data_model.LogDataModel;
import smart_home.value_object.DeviceID;
import smart_home.value_object.LogID;
import smart_home.value_object.ReadingValue;
import smart_home.value_object.SensorID;
import smart_home.value_object.SensorTypeID;
import smart_home.value_object.UnitID;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LogDataModelAssemblerTest {

  /** Test of LogDataModelAssembler constructor. */
  @Test
  void shouldInstantiateLogDataModelAssembler_whenLogFactoryIsValid() {
    // Arrange
    ILogFactory logFactory = mock(ILogFactory.class);

    // Act
    LogDataModelAssembler logDataModelAssembler = new LogDataModelAssembler(logFactory);

    // Assert
    assertNotNull(logDataModelAssembler);
  }

  /** Test of LogDataModelAssembler constructor when LogFactory is null. */
  @Test
  void shouldThrowIllegalArgumentException_whenLogFactoryIsNull() {
    // Arrange
    ILogFactory logFactory = null;
    String expectedMessage = "Log Factory cannot be null.";

    // Act
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new LogDataModelAssembler(logFactory));

    // Assert
    String actualMessage = exception.getMessage();
    assertNotNull(expectedMessage, actualMessage);
  }

  /** Test of toDomain method when given valid LogDataModel. */
  @Test
  void shouldReturnLog_whenGivenValidLogDataModel() {
    // Arrange
    ILogFactory logFactoryDouble = mock(ILogFactory.class);
    when(logFactoryDouble.createLog(
            any(LogID.class),
            any(DeviceID.class),
            any(SensorID.class),
            any(LocalDateTime.class),
            any(ReadingValue.class),
            any(SensorTypeID.class),
            any(UnitID.class)))
        .thenReturn(mock(Log.class));
    LogDataModelAssembler logDataModelAssembler = new LogDataModelAssembler(logFactoryDouble);
    LogID logIDDouble = mock(LogID.class);
    when(logIDDouble.getID()).thenReturn("1L");
    DeviceID deviceIDDouble = mock(DeviceID.class);
    when(deviceIDDouble.getID()).thenReturn("1L");
    SensorID sensorIDDouble = mock(SensorID.class);
    when(sensorIDDouble.getID()).thenReturn("1L");
    LocalDateTime timeStampDouble = mock(LocalDateTime.class);
    ReadingValue readingValueDouble = mock(ReadingValue.class);
    when(readingValueDouble.getReadingValue()).thenReturn("1L");
    SensorTypeID descriptionDouble = mock(SensorTypeID.class);
    when(descriptionDouble.getID()).thenReturn("1L");
    UnitID unitDouble = mock(UnitID.class);
    when(unitDouble.getID()).thenReturn("1L");

    LogDataModel logDataModelDouble = mock(LogDataModel.class);
    when(logDataModelDouble.getLogID()).thenReturn("1L");
    when(logDataModelDouble.getDeviceID()).thenReturn("1L");
    when(logDataModelDouble.getSensorID()).thenReturn("1L");
    when(logDataModelDouble.getTimestamp()).thenReturn(timeStampDouble);
    when(logDataModelDouble.getReadingValue()).thenReturn("1L");
    when(logDataModelDouble.getDescription()).thenReturn("1L");
    when(logDataModelDouble.getUnit()).thenReturn("1L");

    Log expected =
        logFactoryDouble.createLog(
            logIDDouble,
            deviceIDDouble,
            sensorIDDouble,
            timeStampDouble,
            readingValueDouble,
            descriptionDouble,
            unitDouble);

    // Act
    Log log = logDataModelAssembler.toDomain(logDataModelDouble);

    // Assert
    assertEquals(expected, log);
  }

  /** Test of toDomain method when given null LogDataModel. */
  @Test
  void shouldThrowIllegalArgumentException_whenLogDataModelIsNull() {
    // Arrange
    LogDataModel logDataModel = null;
    String expectedMessage = "Log Data Model is required";

    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new LogDataModelAssembler(mock(ILogFactory.class)).toDomain(logDataModel));
    String actualMessage = exception.getMessage();

    // Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /** Test of toDomain method when given valid list of LogDataModels. */
  @Test
  void shouldReturnListOfLogs_whenGivenValidListOfLogDataModels() {
    // Arrange
    ILogFactory logFactoryDouble = mock(ILogFactory.class);
    when(logFactoryDouble.createLog(
            any(LogID.class),
            any(DeviceID.class),
            any(SensorID.class),
            any(LocalDateTime.class),
            any(ReadingValue.class),
            any(SensorTypeID.class),
            any(UnitID.class)))
        .thenReturn(mock(Log.class));
    LogDataModelAssembler logDataModelAssembler = new LogDataModelAssembler(logFactoryDouble);
    LogID logIDDouble = mock(LogID.class);
    when(logIDDouble.getID()).thenReturn("1L");
    DeviceID deviceIDDouble = mock(DeviceID.class);
    when(deviceIDDouble.getID()).thenReturn("1L");
    SensorID sensorIDDouble = mock(SensorID.class);
    when(sensorIDDouble.getID()).thenReturn("1L");
    LocalDateTime timeStampDouble = mock(LocalDateTime.class);
    ReadingValue readingValueDouble = mock(ReadingValue.class);
    when(readingValueDouble.getReadingValue()).thenReturn("1L");
    SensorTypeID descriptionDouble = mock(SensorTypeID.class);
    when(descriptionDouble.getID()).thenReturn("1L");
    UnitID unitDouble = mock(UnitID.class);
    when(unitDouble.getID()).thenReturn("1L");

    LogDataModel logDataModelDouble = mock(LogDataModel.class);
    when(logDataModelDouble.getLogID()).thenReturn("1L");
    when(logDataModelDouble.getDeviceID()).thenReturn("1L");
    when(logDataModelDouble.getSensorID()).thenReturn("1L");
    when(logDataModelDouble.getTimestamp()).thenReturn(timeStampDouble);
    when(logDataModelDouble.getReadingValue()).thenReturn("1L");
    when(logDataModelDouble.getDescription()).thenReturn("1L");
    when(logDataModelDouble.getUnit()).thenReturn("1L");

    List<LogDataModel> logDataModels = List.of(logDataModelDouble);

    Log expected =
        logFactoryDouble.createLog(
            logIDDouble,
            deviceIDDouble,
            sensorIDDouble,
            timeStampDouble,
            readingValueDouble,
            descriptionDouble,
            unitDouble);

    // Act
    List<Log> log = logDataModelAssembler.toDomain(logDataModels);

    // Assert
    System.out.println(expected);
    assertEquals(expected, log.get(0));
  }
}