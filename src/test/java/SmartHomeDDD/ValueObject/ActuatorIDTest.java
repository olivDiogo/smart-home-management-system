package SmartHomeDDD.ValueObject;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

class ActuatorIDTest {

    /**
     * Tests the correct instantiation of a ActuatorID.
     */
    @Test
    public void shouldGetValidObject_whenUsingValidStringInConstructor() {
        // Arrange
        String actuatorIDDescription = "Actuator2GKA";
        // Act
        new ActuatorID(actuatorIDDescription);
    }

    /**
     * Tests if the exception is thrown with a null actuatorID.
     */
    @Test
    public void shouldThrowException_whenActuatorIdIsNull() {
        // Arrange
        String actuatorIDDescription = null;
        String expectedMessage = "'actuatorID' must be a non-empty string.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorID(actuatorIDDescription)
        );

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests if the exception is thrown with a blank actuatorID.
     */
    @Test
    public void shouldThrowException_whenActuatorIdIsBlank() {
        // Arrange
        String actuatorIDDescription = " ";
        String expectedMessage = "'actuatorID' must be a non-empty string.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorID(actuatorIDDescription)
        );

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests if the exception is thrown with an empty actuatorID.
     */
    @Test
    public void shouldThrowException_whenActuatorIdIsEmpty() {
        // Arrange
        String actuatorIDDescription = "";
        String expectedMessage = "'actuatorID' must be a non-empty string.";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorID(actuatorIDDescription)
        );

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests if the ActuatorID is correctly returned.
     */
    @Test
    public void shouldReturnActuatorID() {
        // Arrange
        String actuatorIDDescription = "Actuator2GKA";
        ActuatorID actuatorID = new ActuatorID(actuatorIDDescription);

        String expected = "Actuator2GKA";

        // Act
        String actuatorIDReturned = actuatorID.getId();

        // Assert
        assertEquals(expected, actuatorIDReturned);
    }

    /**
     * Tests if the equals method returns true when the ActuatorID is compared to itself.
     */
    @Test
    public void shouldReturnTrue_WhenActuatorIDIsEqualToItself() {
        // Arrange
        String actuatorIDDescription = "Actuator2GKA";
        ActuatorID actuatorID = new ActuatorID(actuatorIDDescription);

        // Act
        boolean result = actuatorID.equals(actuatorID);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests if the equals method returns true when the ActuatorID is compared to another ActuatorID with the same ID.
     */
    @Test
    public void shouldReturnTrue_WhenActuatorIDIsEqualToOtherActuatorID() {
        // Arrange
        String actuatorIDDescription = "Actuator2GKA";
        ActuatorID actuatorID1 = new ActuatorID(actuatorIDDescription);
        ActuatorID actuatorID2 = new ActuatorID(actuatorIDDescription);

        // Act
        boolean result = actuatorID1.equals(actuatorID2);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests if the equals method returns false when the ActuatorID is compared to another ActuatorID with a different ID.
     */
    @Test
    public void shouldReturnFalse_WhenActuatorIDIsNotEqualToOtherActuatorID() {
        // Arrange
        String actuatorIDDescription1 = "Actuator2GKA";
        String actuatorIDDescription2 = "Actuator2GKB";

        ActuatorID actuatorID1 = new ActuatorID(actuatorIDDescription1);
        ActuatorID actuatorID2 = new ActuatorID(actuatorIDDescription2);

        // Act
        boolean result = actuatorID1.equals(actuatorID2);

        // Assert
        assertFalse(result);
    }

    /**
     * Tests if the hashCode method returns the same value for two ActuatorID with the same ID.
     */
    @Test
    public void shouldReturnHashCode() {
        // Arrange
        String actuatorIDDescription = "Actuator2GKA";
        ActuatorID actuatorID = new ActuatorID(actuatorIDDescription);

        int expected = actuatorIDDescription.hashCode();

        // Act
        int result = actuatorID.hashCode();

        // Assert
        assertEquals(expected, result);
    }


}