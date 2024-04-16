package smartHome.domain.actuatorModel;

import smartHome.valueObject.ActuatorModelID;
import smartHome.valueObject.ActuatorModelName;
import smartHome.valueObject.ActuatorTypeID;
import smartHome.valueObject.ModelPath;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ActuatorModelTest {

  /** Test of class ActuatorModel constructor with valid parameters */
  @Test
  void shouldReturnValidActuatorModel_WhenGivenValidParameters() {
    // Arrange
    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

    // Act
    new ActuatorModel(actuatorModelName, modelPath, actuatorTypeID);
  }

  /** Test of class ActuatorModel constructor with null ActuatorModelName */
  @Test
  void shouldThrowIllegalArgumentException_WhenGivenNullActuatorModelName() {
    // Arrange
    ActuatorModelName actuatorModelName = null;
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

    String expectedMessage = "Please enter a valid actuator model name.";
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new ActuatorModel(actuatorModelName, modelPath, actuatorTypeID));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /** Test of class ActuatorModel constructor with null ModelPath */
  @Test
  void shouldThrowIllegalArgumentException_WhenGivenNullModelPath() {
    // Arrange
    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ModelPath modelPath = null;
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    String expectedMessage = "Please enter a valid model path.";
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new ActuatorModel(actuatorModelName, modelPath, actuatorTypeID));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /** Test of class ActuatorModel constructor with valid parameters and null ActuatorTypeID */
  @Test
  void shouldReturnValidActuatorModel_WhenGivenValidParametersAndNullActuatorTypeID() {
    // Arrange
    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = null;
    String expectedMessage = "Please enter a valid actuator type ID.";
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new ActuatorModel(actuatorModelName, modelPath, actuatorTypeID));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /** Test of class ActuatorModel equals method with same object */
  @Test
  void shouldReturnTrue_WhenGivenSameObject() {
    // Arrange
    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

    try (MockedConstruction<ActuatorModelID> actuatorModelIdMocked =
        mockConstruction(ActuatorModelID.class, (mock, context) -> {})) {

      ActuatorModel actuatorModel = new ActuatorModel(actuatorModelName, modelPath, actuatorTypeID);

      // Act
      boolean result = actuatorModel.equals(actuatorModel);

      // Assert
      assertTrue(result);
    }
  }

  /** Test of class ActuatorModel equals method with different object */
  @Test
  void shouldReturnFalse_WhenGivenDifferentObjectWithSamePath() {
    // Arrange
    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ModelPath modelPath2 = mock(ModelPath.class);

    try (MockedConstruction<ActuatorModelID> actuatorModelIdMocked =
        mockConstruction(ActuatorModelID.class, (mock, context) -> {})) {

      ActuatorModel actuatorModel = new ActuatorModel(actuatorModelName, modelPath, actuatorTypeID);
      ActuatorModel actuatorModel2 = new ActuatorModel(actuatorModelName, modelPath2, actuatorTypeID);

      // Act
      boolean result = actuatorModel.equals(actuatorModel2);

      // Assert
      assertFalse(result);
    }
  }

  /** Test of class ActuatorModel equals method with null object */
  @Test
  void shouldReturnFalse_WhenGivenNull() {
    // Arrange
    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

    try (MockedConstruction<ActuatorModelID> actuatorModelIdMocked =
        mockConstruction(ActuatorModelID.class, (mock, context) -> {})) {

      ActuatorModel actuatorModel = new ActuatorModel(actuatorModelName, modelPath, actuatorTypeID);

      // Act
      boolean result = actuatorModel.equals(null);

      // Assert
      assertFalse(result);
    }
  }

  /** Test of class ActuatorModel getID method */
  @Test
  void shouldReturnActuatorModelID_WhenGetIDIsCalled() {
    // Arrange
    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

    try (MockedConstruction<ActuatorModelID> actuatorModelIdMocked =
        mockConstruction(ActuatorModelID.class, (mock, context) -> {})) {

      ActuatorModel actuatorModel = new ActuatorModel(actuatorModelName, modelPath, actuatorTypeID);

      // Act
      ModelPath result = actuatorModel.getID();

      // Assert
      assertNotNull(result);
    }
  }

  /** Test of class ActuatorModel toString method */
  @Test
  void shouldReturnObjectInStringFormat_WhenToStringIsCalled() {
    // Arrange
    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

    try (MockedConstruction<ActuatorModelID> actuatorModelIdMocked =
        mockConstruction(ActuatorModelID.class, (mock, context) -> {})) {

      ActuatorModel actuatorModel = new ActuatorModel(actuatorModelName, modelPath, actuatorTypeID);

      // Act
      String result = actuatorModel.toString();

      // Assert
      assertTrue(result.contains(actuatorModelName.toString()));
      assertTrue(result.contains(modelPath.toString()));
      assertTrue(result.contains(actuatorTypeID.toString()));
    }
  }

  /** Test of class ActuatorModel getActuatorModelName method */
  @Test
  void shouldReturnActuatorModelName_WhenGetActuatorModelNameIsCalled() {
    // Arrange
    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

    try (MockedConstruction<ActuatorModelID> actuatorModelIdMocked =
        mockConstruction(ActuatorModelID.class, (mock, context) -> {})) {

      ActuatorModel actuatorModel = new ActuatorModel(actuatorModelName, modelPath, actuatorTypeID);

      // Act
      ActuatorModelName result = actuatorModel.getActuatorModelName();

      // Assert
      assertEquals(actuatorModelName, result);
    }
  }
}