package smarthome.domain.actuator_type;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;

class ImpActuatorTypeFactoryTest {

  /**
   * Test to ensure that an ActuatorType can be created successfully in the first method.
   */
  @Test
  void shouldCreateActuatorTypeInTheFirstMethod_whenAttributesAreValid() {
    // Arrange
    TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
    ActuatorTypeFactoryImpl factory = new ActuatorTypeFactoryImpl();
    UnitID unitID = mock(UnitID.class);

    // Act
    ActuatorType actuatorType = factory.createActuatorType(typeDescriptionDouble, unitID);

    // Assert
    assertEquals(typeDescriptionDouble, actuatorType.getActuatorTypeName());
  }

  /**
   * Test to ensure that an ActuatorType can be created successfully in the second method.
   */
  @Test
  void shouldCreateActuatorTypeInTheSecondMethod_whenAttributesAreValid() {
    // Arrange
    TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
    ActuatorTypeFactoryImpl factory = new ActuatorTypeFactoryImpl();
    UnitID unitID = mock(UnitID.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

    // Act
    ActuatorType actuatorType = factory.createActuatorType(typeDescriptionDouble, unitID,
        actuatorTypeID);

    // Assert
    assertEquals(typeDescriptionDouble, actuatorType.getActuatorTypeName());
  }
}