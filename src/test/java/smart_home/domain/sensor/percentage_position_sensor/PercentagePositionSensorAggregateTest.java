package smart_home.domain.sensor.percentage_position_sensor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import smart_home.domain.sensor.average_power_consumption_sensor.AveragePowerConsumptionSensor;
import smart_home.domain.sensor.humidity_sensor.HumiditySensor;
import smart_home.domain.sensor.percentage_position_sensor.PercentagePositionSensor;
import smart_home.value_object.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Test cases for the PercentagePositionSensor class.
 */
class PercentagePositionSensorAggregateTest {

    /**
     * Test to verify that PercentagePositionSensor is properly instantiated when constructor arguments are valid.
     */
    @Test
    void shouldInstantiatePercentagePositionSensor_whenConstructorArgumentsAreValid() {
        //Arrange
        DeviceID deviceID = new DeviceID("1");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("1");
        SensorName sensorName = new SensorName("sensorName");

        //Act
        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Assert
        assertNotNull(percentagePositionSensor);
    }

    /**
     * Test to verify that an IllegalArgumentException is thrown when ModelPath is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
        //Arrange
        DeviceID deviceID = new DeviceID("1");
        ModelPath modelPath = null;
        SensorTypeID sensorTypeID = new SensorTypeID("1");
        SensorName sensorName = new SensorName("sensorName");

        //Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName));
        assertEquals("ModelPath is required", thrown.getMessage());
    }

    /**
     * Test to verify that an IllegalArgumentException is thrown when SensorName is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
        //Arrange
        DeviceID deviceID = new DeviceID("1");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("1");
        SensorName sensorName = null;

        //Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName));
        assertEquals("SensorName is required", thrown.getMessage());
    }

    /**
     * Test to verify that an IllegalArgumentException is thrown when SensorTypeID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNull() {
        //Arrange
        DeviceID deviceID = new DeviceID("1");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = null;
        SensorName sensorName = new SensorName("sensorName");

        //Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName));
        assertEquals("SensorTypeID is required", thrown.getMessage());
    }

    /**
     * Test to verify that an IllegalArgumentException is thrown when DeviceID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
        //Arrange
        DeviceID deviceID = null;
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("1");
        SensorName sensorName = new SensorName("sensorName");

        //Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName));
        assertEquals("DeviceID is required", thrown.getMessage());
    }

    /**
     * Test to verify that the correct value is returned when getValue is called.
     */
    @Test
    void shouldReturnSensorValue_whenGetValueIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        String result = percentagePositionSensor.getValue().toString();

        // Assert
        assertEquals("14", result);
    }

    /**
     * Test to verify that the correct ID is returned when getID is called.
     */
    @Test
    void shouldReturnSensorValue_whenGetIDIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        String result = percentagePositionSensor.getID().toString();

        // Assert

        assertNotNull(result);
    }

    /**
     * Test to verify that the correct SensorTypeID is returned when getSensorTypeID is called.
     */
    @Test
    void shouldReturnSensorValue_whenGetSensorTypeIDIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorTypeID result = percentagePositionSensor.getSensorTypeID();

        // Assert

        assertEquals(sensorTypeID, result);
    }

    /**
     * Test to verify that the correct DeviceID is returned when getDeviceID is called.
     */
    @Test
    void shouldReturnSensorValue_whenGetDeviceIDIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        DeviceID result = percentagePositionSensor.getDeviceID();

        // Assert

        assertEquals(deviceID, result);
    }

    /**
     * Test to verify that the correct ModelPath is returned when getModelPath is called.
     */
    @Test
    void shouldReturnSensorValue_whenGetModelPathIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        ModelPath result = percentagePositionSensor.getModelPath();

        // Assert

        assertEquals(modelPath, result);
    }

    /**
     * Test to verify that the correct SensorName is returned when getName is called.
     */
    @Test
    void shouldReturnSensorValue_whenGetNameIsCalled() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        SensorName result = percentagePositionSensor.getName();

        // Assert

        assertEquals(sensorName, result);
    }

    @Test
    void shouldReturnTrue_WhenInstancesAreSameObject() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        boolean result = percentagePositionSensor.equals(percentagePositionSensor);

        // Assert

        assertTrue(result);
    }

    @Test
    void shouldReturnFalse_WhenInstancesAreNotEqual() {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Act
        boolean result = percentagePositionSensor.equals(null);

        // Assert

        assertFalse(result);
    }

    @Test
    void shouldReturnPercentagePositionSensorHashCode_WhenHashCodeMethodIsCalled(){

        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);
        int expected = percentagePositionSensor.hashCode();
        // Act
        int result = percentagePositionSensor.hashCode();

        // Assert

        assertEquals(expected,result);
    }

    /** test for tostring method */

    @Test
    void shouldReturnPercentagePositionSensorString_WhenToStringMethodIsCalled(){

        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);
        String expected = "SwitchSensor: DeviceID= " + deviceIDValue + " ModelPath= " + modelPathValue + " SensorTypeID= " + sensorTypeIDValue + " SensorName= " + sensorNameValue + " SensorID= " + percentagePositionSensor.getID().getID();
        // Act
        String result = percentagePositionSensor.toString();

        // Assert

        assertEquals(expected,result);
    }

    /**
     * Should instantiate percentage position sensor with sensorID valid.
     */
    @Test
    void shouldInstantiatePercentagePositionSensor_WhenSensorIDIsValid () {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";
        String sensorIDValue = "SensorID";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
        SensorID sensorID = new SensorID(sensorIDValue);

        //Act
        PercentagePositionSensor percentagePositionSensor = new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);

        //Assert
        assertNotNull(percentagePositionSensor);
    }

    /**
     * Should throw exception when sensorID is null
     */
    @Test
    void shouldThrowException_WhenSensorIDIsNull () {
        // Arrange
        String deviceIDValue = "deviceID";
        String modelPathValue = "modelPath";
        String sensorNameValue = "sensorName";
        String sensorTypeIDValue = "Percentage";

        DeviceID deviceID = new DeviceID(deviceIDValue);
        ModelPath modelPath = new ModelPath(modelPathValue);
        SensorName sensorName = new SensorName(sensorNameValue);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
        SensorID sensorID = null;

        String expectedMessage = "SensorID cannot be null.";

        //Act + assert
        Exception exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName,sensorID));

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);

    }



}