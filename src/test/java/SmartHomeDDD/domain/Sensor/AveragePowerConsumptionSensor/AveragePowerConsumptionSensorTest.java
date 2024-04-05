package SmartHomeDDD.domain.Sensor.AveragePowerConsumptionSensor;

import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class AveragePowerConsumptionSensorTest {

  /** See if the constructor works. */
  @Test
  void shouldInstantiateAveragePowerConsumptionSensor() throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    // Act
    new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
  }

  /** tests if Exception is thrown for null Sensor type. */
  @Test
  void shouldThrowExceptionForNullSensorTypeOfPowerConsumptionSensor() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = null;

    // Act + Assert
    Exception e =Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName));

    // Assert
    Assertions.assertEquals("SensorTypeID cannot be null.", e.getMessage());
  }

  /** tests if Exception is thrown when sensorType is different */
  @Test
  void shouldThrowExceptionForDifferentSensorTypeOfPowerConsumptionSensor() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "SunriseTime";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    // Act + Assert
    Exception e = Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName));

    // Assert
    Assertions.assertEquals("SensorTypeID must be 'AveragePowerConsumption'.", e.getMessage());
  }

  /** tests if Exception is thrown for null DeviceID. */
  @Test
  void shouldThrowExceptionForNullDeviceIDOfPowerConsumptionSensor() {
    // Arrange
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = null;
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);;

    // Act + Assert
    Exception e = Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName));

    // Assert
    Assertions.assertEquals("DeviceID cannot be null.", e.getMessage());
  }

  /** tests if Exception is thrown for null ModelPath. */
  @Test
  void shouldThrowExceptionForNullModelPathOfPowerConsumptionSensor() {
    // Arrange
    String deviceIDValue = "deviceID";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = null;
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    // Act + Assert
    Exception e = Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName));

    // Assert
    Assertions.assertEquals("ModelPath cannot be null.", e.getMessage());
  }

  /** tests if Exception is thrown for null SensorName. */
  @Test
  void shouldThrowExceptionForNullSensorNameOfPowerConsumptionSensor() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = null;
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    // Act + Assert
    Exception e = Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName));

    // Assert
    Assertions.assertEquals("SensorName cannot be null", e.getMessage());
  }

  /** See if the getAverageValue method works. */
  @Test
  void shouldReturnAveragePowerConsumptionForAGivenPeriod() throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    double value = 1250.0;

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    LocalDateTime initialTime = LocalDateTime.of(2024, 2, 29, 10, 10, 5);
    LocalDateTime finalTime = LocalDateTime.of(2024, 2, 29, 10, 10, 10);

    averagePowerConsumptionSensor.addReading(initialTime, 1000);
    averagePowerConsumptionSensor.addReading(finalTime, 1500);

    // Act
    ValueObject average = averagePowerConsumptionSensor.getValue(initialTime, finalTime);

    // Assert
    Assertions.assertEquals(value, Double.parseDouble(average.toString()), 0.01);
  }

  /** See if the getAverageValue method works with another time. */
  @Test
  void shouldReturnAveragePowerConsumptionForAGivenPeriodOnADiferenteFormat()
      throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    double value = 1500.0;

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    LocalDateTime initialTime = LocalDateTime.now().minusHours(2);
    LocalDateTime finalTime = LocalDateTime.now();

    averagePowerConsumptionSensor.addReading(initialTime, 1000);
    averagePowerConsumptionSensor.addReading(finalTime, 2000);

    // Act
    ValueObject average = averagePowerConsumptionSensor.getValue(initialTime, finalTime);

    // Assert
    Assertions.assertEquals(value, Double.parseDouble(average.toString()), 0.01);
  }

  /** See if the getAverageValue method works with more values than the initial and final time. */
  @Test
  void shouldReturnAveragePowerConsumptionForAGivenPeriodWithThreeReadings()
      throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    double value = 1500.0;

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    LocalDateTime initialTime = LocalDateTime.now().minusHours(2);
    LocalDateTime secondTime = LocalDateTime.now().minusHours(1);
    LocalDateTime finalTime = LocalDateTime.now();

    averagePowerConsumptionSensor.addReading(initialTime, 1000);
    averagePowerConsumptionSensor.addReading(secondTime, 1500);
    averagePowerConsumptionSensor.addReading(finalTime, 2000);

    // Act
    ValueObject average = averagePowerConsumptionSensor.getValue(initialTime, finalTime);

    // Assert
    Assertions.assertEquals(value, Double.parseDouble(average.toString()), 0.01);
  }

  /**
   * See if the getAverageValue method works with readings out of range of the initial and final
   * times.
   */
  @Test
  void shouldReturnAveragePowerConsumptionForAGivenPeriodWithExtraPeriodReadings()
      throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    double value = 1400.0;

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    LocalDateTime OtherTime1 = LocalDateTime.now().minusHours(3);
    LocalDateTime initialTime = LocalDateTime.now().minusHours(2);
    LocalDateTime OtherTime2 = LocalDateTime.now().minusHours(1);
    LocalDateTime finalTime = LocalDateTime.now();
    LocalDateTime OtherTime3 = LocalDateTime.now().plusHours(1);

    averagePowerConsumptionSensor.addReading(OtherTime1, 500);
    averagePowerConsumptionSensor.addReading(initialTime, 1000);
    averagePowerConsumptionSensor.addReading(OtherTime2, 1200);
    averagePowerConsumptionSensor.addReading(finalTime, 2000);
    averagePowerConsumptionSensor.addReading(OtherTime3, 400);

    // Act
    ValueObject average = averagePowerConsumptionSensor.getValue(initialTime, finalTime);

    // Assert
    Assertions.assertEquals(value, Double.parseDouble(average.toString()), 0.01);
  }

  /** See if the getAverageValue method works with non-sequential readings. */
  @Test
  void shouldReturnAveragePowerConsumptionForAGivenPeriodWithNonSequentialReadings()
      throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    double value = 1133.33;

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    LocalDateTime initialTime = LocalDateTime.now();
    LocalDateTime finalTime = LocalDateTime.now().plusHours(2);
    LocalDateTime otherTime1 = LocalDateTime.now().minusHours(1);
    LocalDateTime otherTime2 = LocalDateTime.now().plusHours(3);
    LocalDateTime otherTime3 = LocalDateTime.now().plusHours(1);

    averagePowerConsumptionSensor.addReading(otherTime2, 500);
    averagePowerConsumptionSensor.addReading(initialTime, 1000);
    averagePowerConsumptionSensor.addReading(otherTime1, 1200);
    averagePowerConsumptionSensor.addReading(finalTime, 2000);
    averagePowerConsumptionSensor.addReading(otherTime3, 400);

    // Act
    ValueObject average = averagePowerConsumptionSensor.getValue(initialTime, finalTime);

    // Assert
    assertEquals(value, Double.parseDouble(average.toString()), 0.01);
  }

  /** Tests if Exception is thrown for initial time after final time. */
  @Test
  void shouldThrowExceptionWhenInitialTimeAfterFinalTime() throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    String expectedMessage = "Initial time must be before final time";

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    LocalDateTime initialTime = LocalDateTime.now().plusHours(3);
    LocalDateTime finalTime = LocalDateTime.now();

    Exception exception =
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> averagePowerConsumptionSensor.getValue(initialTime, finalTime));

    // Assert
    String actualMessage = exception.getMessage();
    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  /** Tests if Exception is thrown for initial time equals to final time. */
  @Test
  void shouldReturnAverageValueWhenInitialEqualsToFinalTime() throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    String expectedMessage = "There is already a reading for this time";

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    LocalDateTime initialTime = LocalDateTime.now();

    averagePowerConsumptionSensor.addReading(initialTime, 1000);

    Exception exception =
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> averagePowerConsumptionSensor.addReading(initialTime, 1000));

    // Assert
    String actualMessage = exception.getMessage();
    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  /** See if the getValue method works but value is not a dummy, for this instance. */
  @Test
  void shouldReturnAverageValueForThisInstant() throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
    double expectedAverage = 0;

    // Act
    ValueObject averageValue = averagePowerConsumptionSensor.getValue();
    // Assert
    Assertions.assertEquals(expectedAverage, Double.parseDouble(averageValue.toString()), 0.01);
  }

  /** See if the addReading method works. */
  @Test
  void shouldReturnReading() throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
    LocalDateTime initialTime = LocalDateTime.now();
    // Act
    double reading = averagePowerConsumptionSensor.addReading(initialTime, 500);
    // Assert
    Assertions.assertEquals(500, reading, 0.01);
  }

  /**
   * Test to verify that the {@link AveragePowerConsumptionSensor} generates an ID when the sensor
   * is instantiated.
   */
  @Test
  void shouldGenerateSensorID_WhenAveragePowerConsumptionSensorIsInstantiated()
      throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
    SensorID result = averagePowerConsumptionSensor.getID();

    Assertions.assertTrue(averagePowerConsumptionSensor.toString().contains(result.toString()));
  }

  /** Test to verify that the {@link AveragePowerConsumptionSensor} returns the sensor name. */
  @Test
  void shouldGetSensorName_WhenAveragePowerConsumptionSensorIsInstantiated()
      throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
    SensorName result = averagePowerConsumptionSensor.getName();

    Assertions.assertEquals(sensorName.toString(), result.toString());
  }

  /** Test to verify that the {@link AveragePowerConsumptionSensor} returns the model path. */
  @Test
  void shouldGetModelPath_WhenAveragePowerConsumptionSensorIsInstantiated()
      throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
    ModelPath result = averagePowerConsumptionSensor.getModelPath();

    Assertions.assertEquals(modelPath.toString(), result.toString());
  }

  /** Test to verify that the {@link AveragePowerConsumptionSensor} returns the device ID. */
  @Test
  void shouldGetDeviceID_WhenAveragePowerConsumptionSensorIsInstantiated()
      throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
    DeviceID result = averagePowerConsumptionSensor.getDeviceID();

    Assertions.assertEquals(deviceID.toString(), result.toString());
  }

  /** Test to verify that the {@link AveragePowerConsumptionSensor} returns the sensor type ID. */
  @Test
  void shouldGetSensorTypeID_WhenAveragePowerConsumptionSensorIsInstantiated()
      throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
    SensorTypeID result = averagePowerConsumptionSensor.getSensorTypeID();

    Assertions.assertEquals(sensorTypeID.toString(), result.toString());
  }
}