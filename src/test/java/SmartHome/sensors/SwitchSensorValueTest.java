package SmartHome.sensors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for {@link SwitchSensorValue}.
 * Validates the behavior and functionality of the SwitchSensorValue class.
 */
class SwitchSensorValueTest {

    /**
     * Tests that the SwitchSensorValue constructor correctly initializes with a given boolean value.
     */
    @Test
    void seeIfConstructorWorks(){
        // Arrange
        boolean bValue = true;

        // Act & Assert
        new SwitchSensorValue(bValue);
    }

    /**
     * Tests that the toString method returns "On" when the SwitchSensorValue is initialized to true.
     */
    @Test
    void toStringReturnsON() {
        // Arrange
        boolean bValue = true;
        SwitchSensorValue switchSensorValue = new SwitchSensorValue(bValue);

        // Act
        String actualString = switchSensorValue.toString();

        // Assert
        assertEquals("On", actualString, "toString should return 'On' when the SwitchSensorValue is true.");
    }

    /**
     * Tests that the toString method returns "Off" when the SwitchSensorValue is initialized to false.
     */
    @Test
    void toStringReturnOff() {
        // Arrange
        boolean bValue = false;
        SwitchSensorValue switchSensorValue = new SwitchSensorValue(bValue);

        // Act
        String actualString = switchSensorValue.toString();

        // Assert
        assertEquals("Off", actualString, "toString should return 'Off' when the SwitchSensorValue is false.");
    }

    /**
     * Tests that the clone method creates a new instance of SwitchSensorValue that is equal to the original.
     */
    @Test
    void SeeIfCloneWorks() {
        // Arrange
        boolean bValue = true;
        SwitchSensorValue switchSensorValue = new SwitchSensorValue(bValue);

        // Act
        SwitchSensorValue clonedValue = switchSensorValue.clone();

        // Assert
        assertEquals(switchSensorValue.toString(), clonedValue.toString(), "Cloned SwitchSensorValue should have the same value as the original.");
    }
}