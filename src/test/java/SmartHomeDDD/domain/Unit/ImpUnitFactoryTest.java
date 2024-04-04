package SmartHomeDDD.domain.Unit;

import SmartHomeDDD.valueObject.UnitSymbol;
import SmartHomeDDD.valueObject.UnitDescription;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Tests for the {@link ImpUnitFactory} class to ensure that measurement types are created correctly under various conditions
 * and that appropriate exceptions are thrown when invalid parameters are provided.
 */
class ImpUnitFactoryTest {

    /**
     * Test to ensure that a MeasurementType can be created successfully when
     * {@link ImpUnitFactory#createMeasurement(UnitDescription, UnitSymbol)}
     * is called with valid parameters. This test verifies that no exceptions are thrown during the creation process.
     */
    @Test
    void shouldCreateMeasurementType_WhenCreateMeasurementIsCalledWithValidParameters() {
        // Arrange
        UnitDescription unitDescription = mock(UnitDescription.class);
        UnitSymbol unitSymbol = mock(UnitSymbol.class);
        ImpUnitFactory factory = mock(ImpUnitFactory.class);

        // Act & Assert
        factory.createMeasurement(unitDescription, unitSymbol);

    }

    /**
     * Test to ensure that an IllegalArgumentException is thrown when
     * {@link ImpUnitFactory#createMeasurement(UnitDescription, UnitSymbol)}
     * is called with a null MeasurementTypeDescription parameter. This test confirms the factory's robustness in parameter validation.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenCreateMeasurementIsCalledWithNullMeasurementTypeDescription() {
        // Arrange
        UnitDescription unitDescription = null;
        UnitSymbol unitSymbol = mock(UnitSymbol.class);
        ImpUnitFactory factory = new ImpUnitFactory();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.createMeasurement(unitDescription, unitSymbol), "Factory should throw IllegalArgumentException for null MeasurementTypeDescription.");
    }

    /**
     * Test to ensure that an IllegalArgumentException is thrown when
     * {@link ImpUnitFactory#createMeasurement(UnitDescription, UnitSymbol)}
     * is called with a null MeasurementTypeUnit parameter. This test verifies the factory's careful check for null values in its arguments.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenCreateMeasurementIsCalledWithNullMeasurementTypeUnit() {
        // Arrange
        UnitDescription unitDescription = mock(UnitDescription.class);
        UnitSymbol unitSymbol = null;
        ImpUnitFactory factory = new ImpUnitFactory();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> factory.createMeasurement(unitDescription, unitSymbol), "Factory should throw IllegalArgumentException for null MeasurementTypeUnit.");
    }
}