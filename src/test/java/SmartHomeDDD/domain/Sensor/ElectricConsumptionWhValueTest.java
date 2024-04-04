package SmartHomeDDD.domain.Sensor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectricConsumptionWhValueTest {
    @Test
    void shouldInstantiateElectricConsumptionWhValue() {
        //Arrange
        int value = 0;
        //Act
        ElectricConsumptionWhValue whValue = new ElectricConsumptionWhValue(value);
        //Assert
        assertNotNull(whValue);
    }
    @Test
    void shouldThrowExceptionWhenValueIsNegative() {
        //Arrange
        int value = -1;
        String expectedMessage = "Consumption cannot be negative.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ElectricConsumptionWhValue(value));
        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void shouldReturnElectricConsumptionWh() {
        //Arrange
        int value = 5;
        ElectricConsumptionWhValue electricConsumptionWhValue = new ElectricConsumptionWhValue(value);
        String expected = "ElectricConsumptionWh{5}";
        //Act
        String actual = electricConsumptionWhValue.toString();
        //Assert
        assertEquals(expected, actual);
    }

}