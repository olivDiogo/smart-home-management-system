package SmartHomeDDD.domain.SensorType;


import SmartHomeDDD.valueObject.SensorTypeID;
import SmartHomeDDD.valueObject.TypeDescription;
import SmartHomeDDD.valueObject.UnitID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SensorTypeAggregateTest {

    /**
     * Test of constructor of class SensorType, when arguments are valid.
     */
    @Test
    void shouldCreateSensorType_whenAttributesAreValid() {
        // Arrange
        String typeDescription = "DewPointSensor";
        String unitID = "Celsius";

        TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
        UnitID unitDouble = new UnitID(unitID);

        // Act
        SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

        // Assert
        assertNotNull(sensorType);
    }


    /**
     * Test of getID method, of class SensorType.
     */
    @Test
    void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() {
        // Arrange
        String typeDescription = "DewPointSensor";
        String unitID = "Celsius";

        TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
        UnitID unitDouble = new UnitID(unitID);

        SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

        // Act
        SensorTypeID result = sensorType.getID();

        // Assert
        assertTrue(sensorType.toString().contains(result.toString()));
    }


    /**
     * Test of getName method, of class SensorType.
     */
    @Test
    void shouldReturnSensorTypeName_whenGetSensorTypeNameIsCalled() {
        // Arrange
        String typeDescription = "DewPointSensor";
        String unitID = "Celsius";

        TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
        UnitID unitDouble = new UnitID(unitID);


        SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

        // Act
        TypeDescription sensorTypeName = sensorType.getName();

        // Assert
        assertEquals(sensorTypeName, typeDescriptionDouble);

    }

    /**
     * Test of getUnit method, of class SensorType.
     */
    @Test
    void shouldReturnUnit_whenGetUnitIsCalled() {
        // Arrange
        String typeDescription = "DewPointSensor";
        String unitID = "Celsius";

        TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
        UnitID unitDouble = new UnitID(unitID);

        SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

        // Act
        UnitID unit = sensorType.getUnit();

        // Assert
        assertEquals(unit, unitDouble);

    }


    /**
     * Test of equals method, of class SensorType, when comparing sensorType with itself.
     */
    @Test
    void shouldGetTrue_whenSensorTypeIsEqualToItself() {
        // Arrange
        String typeDescription = "DewPointSensor";
        String unitID = "Celsius";

        TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
        UnitID unitDouble = new UnitID(unitID);

        String sensorTypeID = "1";

        SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

        // Act
        boolean result = sensorType.equals(sensorType);

        // Assert
        assertTrue(result);
    }


    /**
     * Test of equals method, of class SensorType, when comparing sensorType with another object with same ID.
     */
    @Test
    void shouldGetTrue_whenSensorTypeIsEqualToAnotherSensorTypeWithSameID() {
        // Arrange
        String typeDescription = "DewPointSensor";
        String unitID = "Celsius";

        TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
        UnitID unitDouble = new UnitID(unitID);

        TypeDescription typeDescriptionDouble2 = new TypeDescription(typeDescription);
        UnitID unitDouble2 = new UnitID(unitID);

        String sensorTypeID = "1";

        SensorType sensorType1 = new SensorType(typeDescriptionDouble, unitDouble);
        SensorType sensorType2 = new SensorType(typeDescriptionDouble2, unitDouble2);

        // Act
        boolean result = sensorType1.equals(sensorType2);

        // Assert
        assertTrue(result);
    }


    /**
     * Test of equals method, of class SensorType, when comparing sensorType with another object with different ID.
     */
    @Test
    void shouldGetFalse_whenSensorTypeIsComparedToAnotherSensorTypeWithDifferentID() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        String typeDescription = "DewPointSensor";
        String typeDescription2 = "TemperatureSensor";
        String unitID = "Celsius";

        TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
        UnitID unitDouble = new UnitID(unitID);

        TypeDescription typeDescriptionDouble2 = new TypeDescription(typeDescription2);
        UnitID unitDouble2 = new UnitID(unitID);

        SensorType sensorType1 = new SensorType(typeDescriptionDouble, unitDouble);
        SensorType sensorType2 = new SensorType(typeDescriptionDouble2, unitDouble2);

        // Act
        boolean result = sensorType1.equals(sensorType2);

        // Assert
        assertFalse(result);

    }

    /**
     * Test of equals method, of class SensorType, when comparing sensorType with null object.
     */
    @Test
    void shouldReturnFalse_WhenComparingWithNullObject() {
        //Arrange
        String typeDescription = "DewPointSensor";
        String unitID = "Celsius";

        TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
        UnitID unitDouble = new UnitID(unitID);

        SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

        //Act
        boolean result = sensorType.equals(null);

        //Assert
        assertFalse(result);
    }

    /**
     * Should return the ID of the sensor type.
     */
    @Test
    void shouldReturnID_whenGetIDIsCalled() {
        // Arrange
        String typeDescription = "DewPointSensor";
        String unitID = "Celsius";

        TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
        UnitID unitDouble = new UnitID(unitID);

        String sensorTypeIDExpected = "DewPointSensor";

        SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

        // Act
        SensorTypeID result = sensorType.getID();

        // Assert
        assertEquals(sensorTypeIDExpected, result.toString());


    }

    /**
     * Test of toString method, of class SensorType.
     */
    @Test
    void shouldGetStringWithAttributes_whenToStringIsCalled() {
        // Arrange
        String typeDescription = "DewPointSensor";
        String unitID = "Celsius";

        TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
        UnitID unitDouble = new UnitID(unitID);

        SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

        String expected = "SensorType:" +
                "id=" + sensorType.getID() +
                ", name=" + sensorType.getName() +
                ", unit=" + sensorType.getUnit();

        // Act
        String result = sensorType.toString();

        // Assert
        assertEquals(expected, result);
    }


    /**
    * Test of hashCode method, of class SensorType.
    */
    @Test
    void shouldReturnHashCode_whenHashCodeIsCalled() {
    // Arrange
    String typeDescription = "DewPointSensor";
    String unitID = "Celsius";

    TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
    UnitID unitDouble = new UnitID(unitID);

    SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

    int expected = sensorType.getID().hashCode();

    // Act
    int result = sensorType.hashCode();

    // Assert
    assertEquals(expected, result);
}

}