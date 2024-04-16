package smartHome.assembler;

import smartHome.dto.SensorTypeDTO;
import smartHome.domain.sensorType.SensorType;
import smartHome.valueObject.SensorTypeID;
import smartHome.valueObject.TypeDescription;
import smartHome.valueObject.UnitID;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SensorTypeAssemblerTest {

  /** Tests the conversion of a sensor type to a sensor type DTO. */
  @Test
  void shouldConvertSensorTypeToSensorTypeDTO_whenSensorTypeIsValid() {
    // Arrange
    String sensorTypeID = "1";
    String sensorTypeDescription = "Temperature";
    String unit = "Celsius";

    SensorTypeID sensorTypeIdDouble = mock(SensorTypeID.class);
    when(sensorTypeIdDouble.toString()).thenReturn(sensorTypeID);

    TypeDescription sensorTypeDescriptionDouble = mock(TypeDescription.class);
    when(sensorTypeDescriptionDouble.toString()).thenReturn(sensorTypeDescription);

    UnitID unitDouble = mock(UnitID.class);
    when(unitDouble.toString()).thenReturn(unit);

    SensorType sensorTypeDouble = mock(SensorType.class);
    when(sensorTypeDouble.getID()).thenReturn(sensorTypeIdDouble);
    when(sensorTypeDouble.getName()).thenReturn(sensorTypeDescriptionDouble);
    when(sensorTypeDouble.getUnit()).thenReturn(unitDouble);

    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    String expected = sensorTypeID + " " + sensorTypeDescription + " " + unit;

    // Act
    SensorTypeDTO sensorTypeDTO = sensorTypeAssembler.domainToDTO(sensorTypeDouble);

    // Assert
    assertEquals(expected, sensorTypeDTO.toString());
  }

  /** Tests the conversion of a sensor type to a sensor type DTO when the sensor type is null. */
  @Test
  void shouldThrowException_whenSensorTypeIsNull() {
    // Arrange
    SensorType sensorType = null;

    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

    String expected = "Sensor type cannot be null.";

    // Act + Assert
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              sensorTypeAssembler.domainToDTO(sensorType);
            });

    // Assert
    String result = exception.getMessage();
    assertEquals(expected, result);
  }

  /** Tests the conversion of a list of sensor types to a list of sensor type DTOs. */
  @Test
  void shouldConvertListOfSensorTypesToListOfSensorTypeDTOs_whenSensorTypesAreValid() {
    // Arrange
    String sensorTypeID1 = "1";
    String sensorTypeDescription1 = "Temperature";
    String unit1 = "Celsius";

    String sensorTypeID2 = "2";
    String sensorTypeDescription2 = "Humidity";
    String unit2 = "Percentage";

    /* SensorType 1 */
    SensorTypeID sensorTypeIdDouble1 = mock(SensorTypeID.class);
    when(sensorTypeIdDouble1.toString()).thenReturn(sensorTypeID1);

    TypeDescription sensorTypeDescriptionDouble1 = mock(TypeDescription.class);
    when(sensorTypeDescriptionDouble1.toString()).thenReturn(sensorTypeDescription1);

    UnitID unitDouble1 = mock(UnitID.class);
    when(unitDouble1.toString()).thenReturn(unit1);

    SensorType sensorTypeDouble1 = mock(SensorType.class);
    when(sensorTypeDouble1.getID()).thenReturn(sensorTypeIdDouble1);
    when(sensorTypeDouble1.getName()).thenReturn(sensorTypeDescriptionDouble1);
    when(sensorTypeDouble1.getUnit()).thenReturn(unitDouble1);

    /* SensorType 2 */
    SensorTypeID sensorTypeIdDouble2 = mock(SensorTypeID.class);
    when(sensorTypeIdDouble2.toString()).thenReturn(sensorTypeID2);

    TypeDescription sensorTypeDescriptionDouble2 = mock(TypeDescription.class);
    when(sensorTypeDescriptionDouble2.toString()).thenReturn(sensorTypeDescription2);

    UnitID unitDouble2 = mock(UnitID.class);
    when(unitDouble2.toString()).thenReturn(unit2);

    SensorType sensorTypeDouble2 = mock(SensorType.class);
    when(sensorTypeDouble2.getID()).thenReturn(sensorTypeIdDouble2);
    when(sensorTypeDouble2.getName()).thenReturn(sensorTypeDescriptionDouble2);
    when(sensorTypeDouble2.getUnit()).thenReturn(unitDouble2);

    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

    List<SensorType> sensorTypes = List.of(sensorTypeDouble1, sensorTypeDouble2);

    SensorTypeDTO sensorTypeDTO1 = new SensorTypeDTO(sensorTypeID1, sensorTypeDescription1, unit1);
    SensorTypeDTO sensorTypeDTO2 = new SensorTypeDTO(sensorTypeID2, sensorTypeDescription2, unit2);
    List<SensorTypeDTO> expected = List.of(sensorTypeDTO1, sensorTypeDTO2);

    // Act
    List<SensorTypeDTO> sensorTypesDTO = sensorTypeAssembler.domainToDTO(sensorTypes);

    // Assert
    assertEquals(expected.toString(), sensorTypesDTO.toString());
  }

  /**
   * Tests the conversion of a list of sensor types to a list of sensor type DTOs when the list is
   * null.
   */
  @Test
  void shouldThrowException_whenListOfSensorTypesIsNull() {
    // Arrange
    List<SensorType> sensorTypes = null;

    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

    String expected = "The list of sensor types cannot be null or empty.";

    // Act + Assert
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              sensorTypeAssembler.domainToDTO(sensorTypes);
            });

    // Assert
    String result = exception.getMessage();
    assertEquals(expected, result);
  }

  /**
   * Tests the conversion of a list of sensor types to a list of sensor type DTOs when the list is
   * empty.
   */
  @Test
  void shouldThrowException_whenListOfSensorTypesIsEmpty() {
    // Arrange
    List<SensorType> sensorTypes = List.of();

    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

    String expected = "The list of sensor types cannot be null or empty.";

    // Act + Assert
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              sensorTypeAssembler.domainToDTO(sensorTypes);
            });

    // Assert
    String result = exception.getMessage();
    assertEquals(expected, result);
  }

  /**
   * Tests the conversion of a list of sensor types to a list of sensor types DTOs when the list
   * contains a null sensor type.
   */
  @Test
  void shouldThrowException_whenListOfSensorTypesContainsNullSensorType() {
    // Arrange
    String sensorTypeID1 = "1";
    String sensorTypeDescription1 = "Temperature";
    String unit1 = "Celsius";

    SensorTypeID sensorTypeIdDouble1 = mock(SensorTypeID.class);
    when(sensorTypeIdDouble1.toString()).thenReturn(sensorTypeID1);

    TypeDescription sensorTypeDescriptionDouble1 = mock(TypeDescription.class);
    when(sensorTypeDescriptionDouble1.toString()).thenReturn(sensorTypeDescription1);

    UnitID unitDouble1 = mock(UnitID.class);
    when(unitDouble1.toString()).thenReturn(unit1);

    SensorType sensorTypeDouble1 = mock(SensorType.class);
    when(sensorTypeDouble1.getID()).thenReturn(sensorTypeIdDouble1);
    when(sensorTypeDouble1.getName()).thenReturn(sensorTypeDescriptionDouble1);
    when(sensorTypeDouble1.getUnit()).thenReturn(unitDouble1);

    SensorType sensorTypeDouble2 = null;

    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

    List<SensorType> sensorTypes = new ArrayList<>();
    sensorTypes.add(sensorTypeDouble1);
    sensorTypes.add(sensorTypeDouble2);

    String expected = "Sensor type cannot be null.";

    // Act + Assert
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              sensorTypeAssembler.domainToDTO(sensorTypes);
            });

    // Assert
    String result = exception.getMessage();
    assertEquals(expected, result);
  }
}