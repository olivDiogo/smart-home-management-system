package smart_home.domain.sensor.wind_sensor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindSensorValueTest {

  /** Test if the constructor is properly created. */
  @Test
  void shouldInstantiateWindSensorValue() {
    // Arrange
    double speed = 10.5;
    double direction = 20.5;

    // Act
    WindSensorValue result = new WindSensorValue(speed, direction);

    // Assert
    assertNotNull(result);
  }

  /** Test if the getSpeed method returns the correct speed. */
  @Test
  void shouldReturnSpeed() {
    // Arrange
    double speed = 10.5;
    double direction = 20.5;

    WindSensorValue windSensorValue = new WindSensorValue(speed, direction);

    // Act
    double result = windSensorValue.getSpeed();

    // Assert
    assertEquals(speed, result);
  }

  /** Test if the getDirection method returns the correct direction. */
  @Test
  void shouldReturnDirection() {
    // Arrange
    double speed = 10.5;
    double direction = 20.5;

    WindSensorValue windSensorValue = new WindSensorValue(speed, direction);

    // Act
    double result = windSensorValue.getDirection();

    // Assert
    assertEquals(direction, result);
  }

  /** Test if the equals method returns true when the objects are equal. */
  @Test
  void shouldReturnTrue_whenObjectsAreEqual() {
    // Arrange
    double speed = 10.5;
    double direction = 20.5;

    WindSensorValue windSensorValue1 = new WindSensorValue(speed, direction);
    WindSensorValue windSensorValue2 = new WindSensorValue(speed, direction);

    // Act
    boolean result = windSensorValue1.equals(windSensorValue2);

    // Assert
    assertEquals(true, result);
  }

  /** Test if the equals method returns false when the objects are not equal. */
  @Test
  void shouldReturnFalse_whenObjectsAreNotEqual() {
    // Arrange
    double speed = 10.5;
    double direction = 20.5;

    WindSensorValue windSensorValue = new WindSensorValue(speed, direction);
    WindSensorValue other = new WindSensorValue(20.5, 10.5);

    // Act
    boolean result = windSensorValue.equals(other);

    // Assert
    assertEquals(false, result);
  }

  /** Test if the equals method returns false when the object is not a WindSensorValue. */
  @Test
  void shouldReturnFalse_whenObjectsAreNotWindSensorValue() {
    // Arrange
    double speed = 10.5;
    double direction = 20.5;

    WindSensorValue windSensorValue = new WindSensorValue(speed, direction);

    // Act
    boolean result = windSensorValue.equals(new Object());

    // Assert
    assertFalse(result);
  }

  /** Test if the hashCodes of two different objects are the same. */
  @Test
  void equalsObjectsShouldReturnTheSameHash() {
    // Arrange
    double speed = 10.5;
    double direction = 20.5;

    WindSensorValue windSensorValue = new WindSensorValue(speed, direction);
    WindSensorValue windSensorValue2 = new WindSensorValue(speed, direction);

    boolean expected = windSensorValue.equals(windSensorValue2);

    // Act
    boolean result = windSensorValue.hashCode() == windSensorValue2.hashCode();

    // Assert
    assertEquals(expected, result);
  }

    /**
     * Test if the hashCodes of two equal objects are equal.
     */
  @Test
  void shouldReturnHashCode_WhenHashCodeIsCalled() {
    // Arrange
    double speed = 10.5;
    double direction = 20.5;

    WindSensorValue windSensorValue = new WindSensorValue(speed, direction);
    WindSensorValue windSensorValue2 = new WindSensorValue(speed, direction);

    // Act
    int result = windSensorValue.hashCode();
    int result2 = windSensorValue2.hashCode();

    // Assert
    assertEquals(result, result2);
  }

    /**
     * Test if the hashCodes of two different objects are different.
     */
  @Test
  void shouldReturnNotEquals_WhenHashCodeCalledIsDifferent() {
    // Arrange
    WindSensorValue windSensorValue = new WindSensorValue(10.5, 20.5);
    int expected = Double.hashCode(10.5) - Double.hashCode(20.5);
    // Act
    int result = windSensorValue.hashCode();

    // Assert
    assertNotEquals(expected, result);
  }

  /** Test if the hashCodes of two different objects are different, when one is zero. */
  @Test
  void shouldNotReturnZero_WhenValuesAreNonZero() {
    // Arrange
    WindSensorValue windSensorValue = new WindSensorValue(10.5, 20.5);

    // Act
    int hashCode = windSensorValue.hashCode();

    // Assert
    assertNotEquals(0, hashCode);
  }
}
