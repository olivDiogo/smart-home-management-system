package SmartHomeDDD.domain.ActuatorType;

import SmartHomeDDD.ValueObject.TypeDescription;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ActuatorTypeTest {

        /**
        * Test of constructor of class ActuatorType, when arguments are valid.
        */
        @Test
        void shouldCreateActuatorType_whenAttributesAreValid() {
            // Arrange
            TypeDescription typeDescriptionDouble = mock(TypeDescription.class);

            // Act
            new ActuatorType(typeDescriptionDouble);
        }

        /**
         * Test of constructor of class ActuatorType, when typeDescription is null.
         * @throws Exception
         */
        @Test
        void shouldThrowIllegalArgumentException_whenTypeDescriptionIsNull() {
            // Arrange
            TypeDescription typeDescriptionDouble = null;

            String expectedMessage = "Actuator type name must not be null.";

            // Act
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new ActuatorType(typeDescriptionDouble);
            });

            // Assert
            String actualMessage = exception.getMessage();

            assertEquals(expectedMessage, actualMessage);
        }

        /**
         * Test of method getActuatorTypeName of class ActuatorType.
         */
        @Test
        void shouldReturnActuatorTypeName_whenGetActuatorTypeNameIsCalled() {
            // Arrange
            TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
            ActuatorType actuatorType = new ActuatorType(typeDescriptionDouble);

            // Act
            TypeDescription actualTypeDescription = actuatorType.getActuatorTypeName();

            // Assert
            assertEquals(typeDescriptionDouble, actualTypeDescription);
        }

        /**
         * Test of method equals of class ActuatorType, when the instances are equal.
         */
        @Test
        void shouldReturnTrue_whenInstancesAreEqual() {
            // Arrange
            TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
            ActuatorType actuatorType1 = new ActuatorType(typeDescriptionDouble);
            ActuatorType actuatorType2 = new ActuatorType(typeDescriptionDouble);

            // Act
            boolean result = actuatorType1.equals(actuatorType2);

            // Assert
            assertTrue(result);
        }

        /**
         * Test of method equals of class ActuatorType, when the instances are not equal.
         */
        @Test
        void shouldReturnFalse_whenInstancesAreNotEqual() {
            // Arrange
            TypeDescription typeDescriptionDouble1 = mock(TypeDescription.class);
            TypeDescription typeDescriptionDouble2 = mock(TypeDescription.class);
            ActuatorType actuatorType1 = new ActuatorType(typeDescriptionDouble1);
            ActuatorType actuatorType2 = new ActuatorType(typeDescriptionDouble2);

            // Act
            boolean result = actuatorType1.equals(actuatorType2);

            // Assert
            assertFalse(result);
        }
}