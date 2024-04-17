package smart_home.domain.sensor.wind_sensor;

import org.junit.jupiter.api.Test;
import smart_home.value_object.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class WindSensorAggregateTest {

    /**
     * Tests the instantiation of WindSensor when the constructor arguments are valid.
     */
    @Test
    void shouldInstantiateWindSensor_WhenParametersAreValid() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Wind";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        WindSensor windSensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Assert
        assertNotNull(windSensor);
    }

    /**
     * Tests the instantiation of WindSensor when the deviceID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
        // Arrange
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Wind";
        DeviceID deviceID = null;
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new WindSensor(deviceID, modelPath, sensorTypeID, sensorName));

        // Assert
        assertEquals("DeviceID is required", exception.getMessage());
    }

    /**
     * Tests the instantiation of WindSensor when the modelPath is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenModelPathIsNull() {

        // Arrange
        String deviceIDValue = "some-device-id";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Wind";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = null;
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new WindSensor(deviceID, null, sensorTypeID, sensorName));

        // Assert
        assertEquals("ModelPath is required", exception.getMessage());
    }

    /**
     * Tests the instantiation of WindSensor when the sensorName is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorTypeIDValue = "Wind";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = null;
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new WindSensor(deviceID, modelPath, sensorTypeID, null));

        // Assert
        assertEquals("SensorName is required", exception.getMessage());
    }

    /**
     * Tests the instantiation of WindSensor when the sensorTypeID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNull() {
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = null;

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new WindSensor(deviceID, modelPath, null, sensorName));

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
        String sensorTypeIDValue = "Wind";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        WindSensor windSensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorID result = windSensor.getID();

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
        String sensorTypeIDValue = "Wind";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        WindSensor windSensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorName result = windSensor.getName();

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
        String sensorTypeIDValue = "Wind";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        WindSensor windSensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        ModelPath result = windSensor.getModelPath();

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
        String sensorTypeIDValue = "Wind";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        WindSensor windSensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        DeviceID result = windSensor.getDeviceID();

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
        String sensorTypeIDValue = "Wind";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        WindSensor windSensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorTypeID result = windSensor.getSensorTypeID();

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
        String sensorTypeIDValue = "somethingElse";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        String expectedMessage = "SensorTypeID must be 'Wind'";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new WindSensor(deviceID, modelPath, sensorTypeID, sensorName));

        String actualMessage = exception.getMessage();

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Tests method equals when the instance is compared to itself.
     */
    @Test
    void shouldReturnTrue_WhenComparingTheSameInstance() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Wind";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        WindSensor windSensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        boolean result = windSensor.equals(windSensor);

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
        String sensorTypeIDValue = "Wind";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        WindSensor windSensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        boolean result = windSensor.equals(new Object());

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
        String sensorTypeIDValue = "Wind";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        WindSensor windSensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        boolean result = windSensor.equals(null);

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
        String sensorTypeIDValue = "Wind";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        WindSensor windSensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);

        SensorID sensorID = windSensor.getID();

        int expected = sensorID.hashCode();

        // Act
        int result = windSensor.hashCode();

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
        String sensorTypeIDValue = "Wind";
        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        WindSensor windSensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);

        String expectedValue = windSensor.getValue().toString();

        String expected = "WindSensor:" +
                " modelPath=" + modelPath +
                ", sensorName=" + sensorName +
                ", sensorID=" + windSensor.getID() +
                ", sensorTypeID=" + sensorTypeID +
                ", windSensorValue=" + expectedValue +
                ", deviceID=" + deviceID;

        // Act
        String result = windSensor.toString();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests the instantiation of WindSensor when the constructor arguments are valid.
     */
    @Test
    void shouldInstantiateWindSensor_WhenSensorIDIsValid() {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Wind";
        String sensorIDValue = "sensorID";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
        SensorID sensorID = new SensorID(sensorIDValue);

        // Act
        WindSensor windSensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);

        // Assert
        assertNotNull(windSensor);
    }

    @Test
    void shouldThrowException_WhenSensorIDIsNull () {
        // Arrange
        String deviceIDValue = "some-device-id";
        String modelPathValue = "some-model-path";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Wind";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
        SensorID sensorID = null;

        String expectedMessage = "SensorID cannot be null.";

        // Act + Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new WindSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID));

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);

    }
}
