package smartHome.domain.sensor;

import smartHome.domain.sensor.temperatureSensor.TemperatureSensorValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureSensorValueTest {

    /**
     * Tests the instantiation of TemperatureSensorValue when the constructor arguments are valid.
     */
    @Test
    void shouldInstantiateTemperatureSensorValue_whenConstructorArgumentsAreValid() {
        // Arrange
        double value = -273.15;

        // Act
        TemperatureSensorValue result = new TemperatureSensorValue(value);

        // Assert
        assertNotNull(result);
    }

    /**
     * Tests the instantiation of TemperatureSensorValue when the constructor arguments are invalid.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenConstructorArgumentsAreInvalid() {
        // Arrange
        double value = -273.16;
        String expectedMessage = "Temperature value must be above or equal to -273.15";

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new TemperatureSensorValue(value));

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Tests the toString method of TemperatureSensorValue.
     */
    @Test
    void shouldReturnStringValue_whenToStringIsCalled() {
        // Arrange
        double value = -273.15;
        TemperatureSensorValue temperatureSensorValue = new TemperatureSensorValue(value);

        // Act
        String result = temperatureSensorValue.toString();

        // Assert
        assertEquals("-273.15", result);
    }

}