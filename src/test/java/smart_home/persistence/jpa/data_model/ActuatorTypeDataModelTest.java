package smart_home.persistence.jpa.data_model;

import org.junit.jupiter.api.Test;
import smart_home.domain.actuator_type.ActuatorType;
import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.TypeDescription;
import smart_home.value_object.UnitID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ActuatorTypeDataModelTest {

    /**
     * Test for the empty constructor of the {@link ActuatorTypeDataModel} class.
     */
    @Test
    void shouldInstantiateActuatorTypeDataModel_whenEmptyConstructorIsCalled(){
        // Arrange
        ActuatorTypeDataModel actuatorTypeDataModel = new ActuatorTypeDataModel();

        // Act
        assertNotNull(actuatorTypeDataModel);
    }

    /**
     * Test for the constructor of the {@link ActuatorTypeDataModel} class.
     */
    @Test
    void shouldInstantiateActuatorTypeDataModel_WhenAttributesAreValid() {
        //Arrange
        String strActuatorTypeID = "123";
        String strTypeDescription = "DewPoint";
        String strUnitID = "Celsius";

        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitIDDouble = mock(UnitID.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);


        when(actuatorTypeIDDouble.getID()).thenReturn(strActuatorTypeID);
        when(typeDescriptionDouble.getID()).thenReturn(strTypeDescription);
        when(unitIDDouble.getID()).thenReturn(strUnitID);

        when(actuatorTypeDouble.getID()).thenReturn(actuatorTypeIDDouble);
        when(actuatorTypeDouble.getActuatorTypeName()).thenReturn(typeDescriptionDouble);
        when(actuatorTypeDouble.getUnit()).thenReturn(unitIDDouble);

        //Act
        ActuatorTypeDataModel actuatorTypeDataModel = new ActuatorTypeDataModel(actuatorTypeDouble);

        //Assert
        assertNotNull(actuatorTypeDataModel);
    }

    /**
     * Test for the constructor of the {@link ActuatorTypeDataModel} class when actuatorType is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorTypeIsNull() {
        //Arrange
        ActuatorType actuatorTypeDouble = null;

        String expectedMessage = "ActuatorType cannot be null";

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorTypeDataModel(actuatorTypeDouble));

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test for the getActuatorTypeID method of the {@link ActuatorTypeDataModel} class.
     */
    @Test
    void shouldReturnActuatorTypeID_WhenGetActuatorTypeID() {
        //Arrange
        String strActuatorTypeID = "123";
        String strTypeDescription = "DewPoint";
        String strUnitID = "Celsius";

        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitIDDouble = mock(UnitID.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        when(actuatorTypeIDDouble.getID()).thenReturn(strActuatorTypeID);
        when(typeDescriptionDouble.getID()).thenReturn(strTypeDescription);
        when(unitIDDouble.getID()).thenReturn(strUnitID);

        when(actuatorTypeDouble.getID()).thenReturn(actuatorTypeIDDouble);
        when(actuatorTypeDouble.getActuatorTypeName()).thenReturn(typeDescriptionDouble);
        when(actuatorTypeDouble.getUnit()).thenReturn(unitIDDouble);

        ActuatorTypeDataModel actuatorTypeDataModel = new ActuatorTypeDataModel(actuatorTypeDouble);

        //Act
        String result = actuatorTypeDataModel.getActuatorTypeID();

        //Assert
        assertEquals(strActuatorTypeID, result);
    }

    /**
     * Test for the getActuatorTypeName method of the {@link ActuatorTypeDataModel} class.
     */
    @Test
    void shouldReturnActuatorTypeName_WhenGetActuatorTypeName() {
            //Arrange
            String strActuatorTypeID = "123";
            String strTypeDescription = "DewPoint";
            String strUnitID = "Celsius";

            ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
            TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
            UnitID unitIDDouble = mock(UnitID.class);
            ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

            when(actuatorTypeIDDouble.getID()).thenReturn(strActuatorTypeID);
            when(typeDescriptionDouble.toString()).thenReturn(strTypeDescription);
            when(unitIDDouble.getID()).thenReturn(strUnitID);

            when(actuatorTypeDouble.getID()).thenReturn(actuatorTypeIDDouble);
            when(actuatorTypeDouble.getActuatorTypeName()).thenReturn(typeDescriptionDouble);
            when(actuatorTypeDouble.getUnit()).thenReturn(unitIDDouble);

            ActuatorTypeDataModel actuatorTypeDataModel = new ActuatorTypeDataModel(actuatorTypeDouble);

            //Act
            String result = actuatorTypeDataModel.getActuatorTypeName();

            //Assert
            assertEquals(strTypeDescription, result);
        }

    /**
     * Test for the getUnitID method of the {@link ActuatorTypeDataModel} class.
     */
    @Test
    void shouldReturnUnitID_WhenGetUnitID() {
        //Arrange
        String strActuatorTypeID = "123";
        String strTypeDescription = "DewPoint";
        String strUnitID = "Celsius";

        ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitIDDouble = mock(UnitID.class);
        ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

        when(actuatorTypeIDDouble.getID()).thenReturn(strActuatorTypeID);
        when(typeDescriptionDouble.getID()).thenReturn(strTypeDescription);
        when(unitIDDouble.getID()).thenReturn(strUnitID);

        when(actuatorTypeDouble.getID()).thenReturn(actuatorTypeIDDouble);
        when(actuatorTypeDouble.getActuatorTypeName()).thenReturn(typeDescriptionDouble);
        when(actuatorTypeDouble.getUnit()).thenReturn(unitIDDouble);

        ActuatorTypeDataModel actuatorTypeDataModel = new ActuatorTypeDataModel(actuatorTypeDouble);

        //Act
        String result = actuatorTypeDataModel.getUnitID();

        //Assert
        assertEquals(strUnitID, result);
    }
}