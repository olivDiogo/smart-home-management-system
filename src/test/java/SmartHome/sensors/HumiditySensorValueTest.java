package SmartHome.sensors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link HumiditySensorValue}.
 * This class tests the functionality and validation within the HumiditySensorValue class.
 */
class HumiditySensorValueTest {

    /**
     * Tests that the HumiditySensorValue constructor accepts a value at the upper boundary (100).
     */
    @Test
    void seeIfConstructorWorksWhenValueIs100(){
        //Arrange
        double nValue = 100;

        //Act
        new HumiditySensorValue(nValue);
    }

    /**
     * Tests that the HumiditySensorValue constructor accepts a value at the lower boundary (0).
     */
    @Test
    void seeIfConstructorWorksWhenValueIsZero(){
        //Arrange
        double nValue = 0;

        //Act
        new HumiditySensorValue(nValue);
    }

    /**
     * Tests that the HumiditySensorValue constructor throws an IllegalArgumentException
     * when the value is below the lower boundary (0).
     */
    @Test
    void seeIfConstructorThrowsExceptionWhenValueUnderZero() {
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new HumiditySensorValue(-1));

        //Assert
        assertEquals("Humidity value must be between 0 and 100", exception.getMessage());
    }

    /**
     * Tests that the HumiditySensorValue constructor throws an IllegalArgumentException
     * when the value is above the upper boundary (100).
     */
    @Test
    void seeIfConstructorThrowsExceptionWhenValueOver100() {
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new HumiditySensorValue(101));

        //Assert
        assertEquals("Humidity value must be between 0 and 100", exception.getMessage());
    }

    /**
     * Tests that the clone method of HumiditySensorValue correctly clones the object,
     * resulting in a new object with the same value.
     */
    @Test
    void seeIfCloneWorks() {
        //Arrange
        double dValue = 15.5;
        HumiditySensorValue humiditySensorValue = new HumiditySensorValue(dValue);

        //Act
        HumiditySensorValue clonedValue = humiditySensorValue.clone();

        //Assert
        assertEquals(humiditySensorValue.toString(), clonedValue.toString());
        assertNotSame(humiditySensorValue, clonedValue);
    }

    /**
     * Tests that the toString method of HumiditySensorValue correctly converts the value to a String.
     */
    @Test
    void seeIfToStringWorks() {
        //Arrange
        double dValue = 15.5;
        HumiditySensorValue humiditySensorValue = new HumiditySensorValue(dValue);

        //Act
        String actualString = humiditySensorValue.toString();

        //Assert
        assertEquals("15.5", actualString);
    }
}
