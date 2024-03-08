package SmartHome.sensors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the PercentagePositionSensorValue class.
 */
class PercentagePositionSensorValueTest {

    /**
     * Tests the constructor of PercentagePositionSensorValue when the value is 100.
     */
    @Test
    void shouldReturnNewPercentagePosicionSensorValueCem_WhenConstructorIsCalled() {
        // Arrange
        double percented = 100;

        // Act
        new PercentagePositionSensorValue(percented);
    }

    /**
     * Tests the constructor of PercentagePositionSensorValue when the value is zero.
     */
    @Test
    void shouldReturnNewPercentagePosicionSensorValueZero_WhenConstructorIsCalled() {
        // Arrange
        double percented = 0;

        // Act
        new PercentagePositionSensorValue(percented);
    }
    /**
     * Tests if the constructor throws an exception when the value is under zero.
     */
    @Test
    void shouldReturnThrowsException_WhenConstructorIsUnderZero() {
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensorValue(-1));

        // Assert
        assertEquals("Percented value must be between 0 and 100", exception.getMessage());
    }

    /**
     * Tests if the constructor throws an exception when the value is over 100.
     */
    @Test
    void shouldReturnThrowsException_WhenConstructorIsOverCem() {
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new PercentagePositionSensorValue(101));

        // Assert
        assertEquals("Percented value must be between 0 and 100", exception.getMessage());
    }

    /**
     * Tests if the toString method returns the correct string representation.
     */
    @Test
    void shouldReturnCorrect_whenToStringWorks() {
        // Arrange
        PercentagePositionSensorValue percentagePositionSensorValue = new PercentagePositionSensorValue(50);

        // Act
        String result = percentagePositionSensorValue.toString();

        // Assert
        assertEquals("50.0%", result);
    }
}