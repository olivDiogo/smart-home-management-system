package smart_home.domain.sensor.dew_point_sensor;

import org.junit.jupiter.api.Test;
import smart_home.value_object.*;

import static org.junit.jupiter.api.Assertions.*;

class DewPointSensorAggregateTest {

    /**
     * Test to check if the DewPointSensor is instantiated correctly.
     */
    @Test
    void shouldInstantiateDewPointSensor_WhenParametersAreValid() {
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        // act
        DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //assert
        assertNotNull(dewPointSensor);

    }

    /**
     * Test to check if the DewPointSensor throws an exception when the DeviceID is null.
     */
    @Test
    void shouldThrowException_WhenDeviceIDIsNull() {
        //Arrange
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = null;
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        String expectedMessage = "DeviceID is required";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName)
        );

        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);

    }

    /**
     * Test to check if the DewPointSensor throws an exception when the ModelPath is null.
     */
    @Test
    void shouldThrowException_WhenModelPathIsNull() {
        //Arrange
        String deviceIDName = "123A";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = null;
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        String expectedMessage = "ModelPath is required";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName)
        );

        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test to check if the DewPointSensor throws an exception when the SensorName is null.
     */
    @Test
    void shouldThrowException_WhenSensorNameIsNull() {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = null;
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        String expectedMessage = "SensorName is required";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName)
        );

        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test to check if the DewPointSensor throws an exception when the SensorTypeID is null.
     */
    @Test
    void shouldThrowException_WhenSensorTypeIDIsNull() {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = null;

        String expectedMessage = "SensorTypeID is required";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName)
        );

        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_WhenSensorTypeIDIsInvalid () {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "Fahrenheit";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        String expectedMessage = "SensorTypeID must be 'DewPoint'";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName)
        );

        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);

    }

    /**
     * Should return Sensor ID.
     */
    @Test
    void shouldGetDewPointID() {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Act
        SensorID result = dewPointSensor.getID();

        //Assert
        assertNotNull(result);
    }

    /**
     * Should get sensor name.
     */
    @Test
    void shouldGetDewPointName() {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Act
        SensorName result = dewPointSensor.getName();

        //Assert
        assertEquals(sensorName, result);

    }

    /**
     * Should get model Path.
     */
    @Test
    void shouldGetDewPointModelPath() {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Act
        ModelPath result = dewPointSensor.getModelPath();

        //Assert
        assertEquals(modelPath, result);
    }

    /**
     * Should get sensorType ID.
     */
    @Test
    void shouldGetDewPointSensorTypeID() {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Act
        SensorTypeID result = dewPointSensor.getSensorTypeID();

        //Assert
        assertEquals(sensorTypeID, result);
    }

    /**
     * Should return device ID.
     */
    @Test
    void shouldGetDeviceID() {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Act
        DeviceID result = dewPointSensor.getDeviceID();

        //Assert
        assertEquals(deviceID, result);
    }

    /**
     * Should return dew point value.
     */
    @Test
    void shouldGetDewPointValue() {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        //Act
        DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Assert
        int value = Integer.parseInt(dewPointSensor.getValue().toString());
        assertTrue(value >= -70 && value <= 70);
    }

    /**
     * Tests method equals when the instance is compared to itself.
     */
    @Test
    void shouldReturnTrue_WhenComparingTheSameInstance() {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Act
        boolean result = dewPointSensor.equals(dewPointSensor);

        //Assert
        assertTrue(result);
    }


    /**
     * Tests method equals when there are two different objects.
     */
    @Test
    void shouldReturnFalse_WhenThereTwoDifferentObjects() {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Act
        boolean result = dewPointSensor.equals(new Object());

        //Assert
        assertFalse(result);
    }


    /**
     * Tests equals when instances is compared to a null object.
     */
    @Test
    void shouldReturnFalse_WhenComparedToNull() {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Act
        boolean result = dewPointSensor.equals(null);

        //Assert
        assertFalse(result);
    }


    /**
     * Tests equals when instances is compared to an object of a different class.
     */
    @Test
    void shouldReturnFalse_WhenComparedToDifferentClass() {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

        //Act
        boolean result = dewPointSensor.equals(new Object());

        //Assert
        assertFalse(result);
    }

    /**
     * Test hashCode method.
     */
    @Test
    void shouldReturnHashCode_whenHashCodeIsCalled() {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);

        SensorID sensorID = dewPointSensor.getID();
        int expected = sensorID.hashCode();

        //Act
        int result = dewPointSensor.hashCode();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test toString method.
     */
    @Test
    void shouldReturnString_whenToStringIsCalled() {
        //Arrange
        String deviceIDName = "123A";
        String modelPathName = "SmartHome.sensors.DewPointSensor";
        String name = "DewPointSensor";
        String typeID = "DewPoint";

        DeviceID deviceID = new DeviceID(deviceIDName);
        ModelPath modelPath = new ModelPath(modelPathName);
        SensorName sensorName = new SensorName(name);
        SensorTypeID sensorTypeID = new SensorTypeID(typeID);

        DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName);
        DewPointValue dewPointValue = dewPointSensor.getValue();

        String expected = "DewPointSensor:" +
                " modelPath=" + modelPath +
                ", sensorName=" + sensorName +
                ", sensorID=" + dewPointSensor.getID() +
                ", sensorTypeID=" + sensorTypeID +
                ", dewPointValue=" + dewPointValue +
                ", deviceID=" + deviceID;

        //Act
        String result = dewPointSensor.toString();

        //Assert
        assertEquals(expected, result);
    }
}