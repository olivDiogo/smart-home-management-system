package smart_home.domain.sensor.temperature_sensor;

import org.junit.jupiter.api.Test;
import smart_home.value_object.*;

import static org.junit.jupiter.api.Assertions.*;

public class TemperatureSensorAggregateTest {


    /**
     * Tests the instantiation of TemperatureSensor when the constructor arguments are valid.
     */
    @Test
    void shouldInstantiateTemperatureSensor_whenConstructorArgumentsAreValid() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Assert
        assertNotNull(sensor);
    }

    /**
     * Tests the instantiation of TemperatureSensor when the deviceID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
        // Arrange
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = null;
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName));

        // Assert
        assertEquals("DeviceID is required", exception.getMessage());
    }

    /**
     * Tests the instantiation of TemperatureSensor when the modelPath is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = null;
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName));

        // Assert
        assertEquals("ModelPath is required", exception.getMessage());
    }

    /**
     * Tests the instantiation of TemperatureSensor when the sensorName is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = null;
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName));

        // Assert
        assertEquals("SensorName is required", exception.getMessage());
    }

    /**
     * Tests the instantiation of TemperatureSensor when the sensorTypeID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNull() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = null;

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName));

        // Assert
        assertEquals("SensorTypeID is required", exception.getMessage());
    }

    /**
     * Tests the getter for the sensorID.
     */
    @Test
    void shouldReturnSensorID_whenGetIDIsCalled() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorID result = sensor.getID();

        // Assert
        assertNotNull(result);
    }

    /**
     * Tests the getter for the sensor name.
     */
    @Test
    void shouldReturnSensorName_whenGetSensorNameIsCalled() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorName result = sensor.getName();

        // Assert
        assertEquals(sensorName, result);
    }

    /**
     * Tests the getter for the model path.
     */
    @Test
    void shouldReturnModelPath_whenGetModelPathIsCalled() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        ModelPath result = sensor.getModelPath();

        // Assert
        assertEquals(modelPath, result);
    }

    /**
     * Tests the getter for the device ID.
     */
    @Test
    void shouldReturnDeviceID_whenGetDeviceIDIsCalled() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        DeviceID result = sensor.getDeviceID();

        // Assert
        assertEquals(deviceID, result);
    }

    /**
     * Tests the getter for the sensor type ID.
     */
    @Test
    void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorTypeID result = sensor.getSensorTypeID();

        // Assert
        assertEquals(sensorTypeID, result);
    }

    /**
     * Tests the getter for the sensor type ID when the sensor type ID is not of type 'Temperature'.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNotTemperature() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String typeID = "SomethingElse";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        String expectedMessage = "SensorTypeID must be of type 'Temperature'";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName));

        String actualMessage = exception.getMessage();

        // Assert
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Tests the getter for the sensor value.
     */
    @Test
    void shouldReturnSensorValue_whenGetValueIsCalled() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        TemperatureSensorValue result = sensor.getValue();

        // Assert
        double value = Double.parseDouble(result.toString());
        assertTrue(value >= -273.15);
    }

    /**
     * Tests method equals when the instance is compared to itself.
     */
    @Test
    void shouldReturnTrue_WhenComparingTheSameInstance(){
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        boolean result = sensor.equals(sensor);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests method equals when there are two different objects.
     */
    @Test
    void shouldReturnFalse_WhenThereTwoDifferentObjects() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        boolean result = sensor.equals(new Object());

        // Assert
        assertFalse(result);
    }

    /**
     * Tests equals when instances is compared to a null object.
     */
    @Test
    void shouldReturnFalse_WhenComparedToNull() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        boolean result = sensor.equals(null);

        // Assert
        assertFalse(result);
    }

    /**
     * Test hashCode method.
     */
    @Test
    void shouldReturnHashCode_whenHashCodeIsCalled(){
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);

        SensorID sensorID = sensor.getID();

        int expected = sensorID.hashCode();

        // Act
        int result = sensor.hashCode();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test toString method.
     */
    @Test
    void shouldReturnString_whenToStringIsCalled() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Temperature";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);

        String expectedValue = sensor.getValue().toString();

        String expected = "TemperatureSensor:" +
                " modelPath=" + modelPath +
                ", sensorName=" + sensorName +
                ", sensorID=" + sensor.getID() +
                ", sensorTypeID=" + sensorTypeID +
                ", temperatureValue=" + expectedValue +
                ", deviceID=" + deviceID;

        // Act
        String result = sensor.toString();

        // Assert
        assertEquals(expected, result);
    }

}