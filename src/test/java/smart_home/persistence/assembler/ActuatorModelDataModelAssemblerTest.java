package smart_home.persistence.assembler;

import org.junit.jupiter.api.Test;
import smart_home.domain.actuator_model.IActuatorModelFactory;
import smart_home.domain.actuator_model.ActuatorModel;
import smart_home.persistence.jpa.data_model.ActuatorModelDataModel;
import smart_home.value_object.ModelPath;
import smart_home.value_object.ActuatorModelName;
import smart_home.value_object.ActuatorTypeID;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActuatorModelDataModelAssemblerTest {
  /**
   * Test to check if the ActuatorModelDataModelAssembler is instantiated when the ActuatorModelFactory is valid
   */
  @Test
  void shouldThrowException_whenActuatorModelFactoryIsNull() {
    // Arrange
    IActuatorModelFactory actuatorModelFactory = null;

    String expected = "Actuator Model Factory is required";

    // Act
    Exception exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorModelDataModelAssembler(actuatorModelFactory));

    // Assert
    String actual = exception.getMessage();

    assertEquals(expected, actual);
  }

  /**
   * Test to check if the ActuatorModelDataModelAssembler is instantiated when the ActuatorModelFactory is valid
   */
  @Test
  void shouldInstantiateActuatorModelDataModelAssembler_whenActuatorModelFactoryIsValid() {
    // Arrange
    IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);

    // Act
    ActuatorModelDataModelAssembler actuatorModelDataModelAssembler = new ActuatorModelDataModelAssembler(actuatorModelFactory);

    // Assert
    assertNotNull(actuatorModelDataModelAssembler);
  }

  /**
   * Test to check if the ActuatorModelDataModelAssembler is instantiated when the ActuatorModelFactory is valid
   */
  @Test
  void shouldConvertActuatorModelDataModelToDomain_WhenActuatorModelDataModelIsValid() {
    //Arrange
    ModelPath modelPathDouble = mock(ModelPath.class);
    ActuatorModelName actuatorModelNameDouble = mock(ActuatorModelName.class);
    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

    ActuatorModelDataModel actuatorModelDataModelDouble = mock(ActuatorModelDataModel.class);
    when(actuatorModelDataModelDouble.get_modelPath()).thenReturn("modelPath");
    when(actuatorModelDataModelDouble.get_actuatorModelName()).thenReturn("actuatorModelName");
    when(actuatorModelDataModelDouble.get_actuatorTypeID()).thenReturn("actuatorTypeID");

    IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);

    ActuatorModelDataModelAssembler actuatorModelDataModelAssembler = new ActuatorModelDataModelAssembler(actuatorModelFactory);

    ActuatorModel expected = actuatorModelFactory.createActuatorModel(actuatorModelNameDouble, modelPathDouble, actuatorTypeIDDouble);

    //Act
    ActuatorModel result = actuatorModelDataModelAssembler.toDomain(actuatorModelDataModelDouble);

    //Assert
    assertEquals(expected, result);
  }

  /**
   * Test to check if the ActuatorModelDataModelAssembler throws an exception when the ActuatorModelDataModel is null
   */
  @Test
  void shouldThrowException_WhenActuatorDataModelisNull() {
    //Arrange
    ActuatorModelDataModel actuatorModelDataModel = null;

    IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);
    ActuatorModelDataModelAssembler actuatorModelDataModelAssembler = new ActuatorModelDataModelAssembler(actuatorModelFactory);

    String expected = "Actuator Model Data Model is required";

    //Act
    Exception exception = assertThrows(IllegalArgumentException.class, () -> actuatorModelDataModelAssembler.toDomain(actuatorModelDataModel));

    //Assert
    String actual = exception.getMessage();
    assertEquals(expected, actual);
  }

  @Test
  void shouldConvertActuatorModelDataModelListToDomainList_WhenActuatorModelDataModelListIsValid() {
    //Arrange
    //ActuatorModel 1
    ModelPath modelPathDouble = mock(ModelPath.class);
    ActuatorModelName actuatorModelNameDouble = mock(ActuatorModelName.class);
    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

    ActuatorModelDataModel actuatorModelDataModelDouble = mock(ActuatorModelDataModel.class);
    when(actuatorModelDataModelDouble.get_modelPath()).thenReturn("modelPath");
    when(actuatorModelDataModelDouble.get_actuatorModelName()).thenReturn("actuatorModelName");
    when(actuatorModelDataModelDouble.get_actuatorTypeID()).thenReturn("actuatorTypeID");

    //ActuatorModel 2
    ModelPath modelPathDouble2 = mock(ModelPath.class);
    ActuatorModelName actuatorModelNameDouble2 = mock(ActuatorModelName.class);
    ActuatorTypeID actuatorTypeIDDouble2 = mock(ActuatorTypeID.class);

    ActuatorModelDataModel actuatorModelDataModelDouble2 = mock(ActuatorModelDataModel.class);
    when(actuatorModelDataModelDouble2.get_modelPath()).thenReturn("modelPath2");
    when(actuatorModelDataModelDouble2.get_actuatorModelName()).thenReturn("actuatorModelName2");
    when(actuatorModelDataModelDouble2.get_actuatorTypeID()).thenReturn("actuatorTypeID2");

    IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);

    ActuatorModelDataModelAssembler actuatorModelDataModelAssembler = new ActuatorModelDataModelAssembler(actuatorModelFactory);

    List<ActuatorModelDataModel> actuatorModelDataModelList = new ArrayList<>();
    actuatorModelDataModelList.add(actuatorModelDataModelDouble);
    actuatorModelDataModelList.add(actuatorModelDataModelDouble2);

    //Expected ActuatorModels
    ActuatorModel expected1 = mock(ActuatorModel.class);
    ActuatorModel expected2 = mock(ActuatorModel.class);

    when(actuatorModelFactory.createActuatorModel(any(ActuatorModelName.class), any(ModelPath.class), any(ActuatorTypeID.class))).thenReturn(expected1, expected2);

    List<ActuatorModel> expectedList = List.of(expected1, expected2);


    //Act
    List<ActuatorModel> resultList = actuatorModelDataModelAssembler.toDomain(actuatorModelDataModelList);

    //Assert
    assertEquals(expectedList, resultList);
  }

  /**
   * Test to check if the ActuatorModelDataModelAssembler throws an exception when the list of ActuatorModelDataModel is null
   */
  @Test
  void shouldThrowException_WhenListOfActuatorModelDataModelsIsNull() {
    //Arrange
    List<ActuatorModelDataModel> actuatorModelDataModelList = null;

    IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);
    ActuatorModelDataModelAssembler actuatorModelDataModelAssembler = new ActuatorModelDataModelAssembler(actuatorModelFactory);

    String expected = "The list of actuator models cannot be null or empty.";

    //Act
    Exception exception = assertThrows(IllegalArgumentException.class, () -> actuatorModelDataModelAssembler.toDomain(actuatorModelDataModelList));

    //Assert
    String actual = exception.getMessage();
    assertEquals(expected, actual);
  }

  /**
   * Test to check if the ActuatorModelDataModelAssembler throws an exception when the list of ActuatorModelDataModel is empty
   */
  @Test
  void shouldThrowException_WhenListOfActuatorModelDataModelsIsEmpty() {
    //Arrange
    List<ActuatorModelDataModel> actuatorModelDataModelList = new ArrayList<>();

    IActuatorModelFactory actuatorModelFactory = mock(IActuatorModelFactory.class);
    ActuatorModelDataModelAssembler actuatorModelDataModelAssembler = new ActuatorModelDataModelAssembler(actuatorModelFactory);

    String expected = "The list of actuator models cannot be null or empty.";

    //Act
    Exception exception = assertThrows(IllegalArgumentException.class, () -> actuatorModelDataModelAssembler.toDomain(actuatorModelDataModelList));

    //Assert
    String actual = exception.getMessage();
    assertEquals(expected, actual);
  }
}