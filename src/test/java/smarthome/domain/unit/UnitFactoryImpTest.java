package smarthome.domain.unit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.UnitSymbol;

/**
 * Tests for the {@link UnitFactoryImpl} class to ensure that measurement types are created
 * correctly under various conditions and that appropriate exceptions are thrown when invalid
 * parameters are provided.
 */
class UnitFactoryImpTest {

  /**
   * Test to ensure that a Unit can be created successfully when
   * {@link UnitFactoryImpl#createUnit(UnitDescription, UnitSymbol)} is called with valid
   * parameters. This test verifies that no exceptions are thrown during the creation process.
   */
  @Test
  void shouldCreateUnit_WhenCreateUnitIsCalledWithValidParameters() {
    // Arrange
    UnitDescription unitDescription = mock(UnitDescription.class);
    UnitSymbol unitSymbol = mock(UnitSymbol.class);
    UnitFactoryImpl factory = new UnitFactoryImpl();

    // Act
    Unit result = factory.createUnit(unitDescription, unitSymbol);

    // Assert
    assertNotNull(result);
  }

  /**
   * Test to ensure that a Unit can be created successfully when
   * {@link UnitFactoryImpl#createUnit(UnitDescription, UnitSymbol, UnitID)} is called with valid
   * parameters. This test verifies that no exceptions are thrown during the creation process.
   */
  @Test
  void shouldCreateUnit_whenCreateUnitIsCalledWithValidParameters() {
    // Arrange
    UnitDescription unitDescription = mock(UnitDescription.class);
    UnitSymbol unitSymbol = mock(UnitSymbol.class);
    UnitID unitID = mock(UnitID.class);
    UnitFactoryImpl factory = new UnitFactoryImpl();

    // Act
    Unit result = factory.createUnit(unitDescription, unitSymbol, unitID);

    // Assert
    assertNotNull(result);
  }
}