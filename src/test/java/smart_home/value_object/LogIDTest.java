package smart_home.value_object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogIDTest {

    /**
     * Test for LogID constructor
     */
    @Test
    void shouldInstantiateLogID_WhenValidParameters() {
        // Arrange
        String logID = "logID";

        // Act
        LogID logIDObject = new LogID(logID);

        // Assert
        assertNotNull(logIDObject);
    }

    /**
     * Test for LogID constructor when logID is null
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenLogIDIsNull() {
        // Arrange
        String logID = null;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new LogID(logID));

        assertEquals("The value of 'logID' should not null, blank, or empty.", exception.getMessage());
    }

    /**
     * Test for LogID constructor when logID is blank
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenLogIDIsBlank() {
        // Arrange
        String logID = " ";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new LogID(logID));

        assertEquals("The value of 'logID' should not null, blank, or empty.", exception.getMessage());
    }

    /**
     * Test for getID method
     */
    @Test
    void shouldReturnLogID_WhenGetIDIsCalled() {
        // Arrange
        String logID = "logID";
        LogID logIDObject = new LogID(logID);

        // Act
        String result = logIDObject.getID();

        // Assert
        assertEquals(logID, result);
    }

    /**
     * Test for equals method when the object is the same
     */
    @Test
    void shouldReturnTrue_WhenObjectIsTheSame() {
        // Arrange
        String logID = "logID";
        LogID logIDObject = new LogID(logID);

        // Act
        boolean result = logIDObject.equals(logIDObject);

        // Assert
        assertTrue(result);
    }

    /**
     * Test for equals method when the object is not the same
     */
    @Test
    void shouldReturnFalse_WhenObjectIsNotTheSame() {
        // Arrange
        String logID = "logID";
        LogID logIDObject = new LogID(logID);

        // Act
        boolean result = logIDObject.equals(new Object());

        // Assert
        assertFalse(result);
    }

    /**
     * Test for equals method when the object is the same class
     */
    @Test
    void shouldReturnTrue_WhenObjectIsTheSameClass() {
        // Arrange
        String logID = "logID";
        LogID logIDObject = new LogID(logID);
        LogID logIDObject2 = new LogID(logID);

        // Act
        boolean result = logIDObject.equals(logIDObject2);

        // Assert
        assertTrue(result);
    }

    /**
     * Test for hashcode method
     */
    @Test
    void shouldReturnHashCode_WhenHashCodeIsCalled() {
        // Arrange
        String logID = "logID";
        LogID logIDObject = new LogID(logID);

        // Act
        int result = logIDObject.hashCode();

        // Assert
        assertEquals(logID.hashCode(), result);
    }

}