package SmartHomeDDD.service;

import SmartHomeDDD.domain.ActuatorModel.ActuatorModel;
import SmartHomeDDD.domain.ActuatorModel.ActuatorModelFactory;
import SmartHomeDDD.domain.Unit.Unit;
import SmartHomeDDD.domain.Unit.UnitFactory;
import SmartHomeDDD.repository.ActuatorModelRepository;
import SmartHomeDDD.repository.UnitRepository;
import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.ActuatorModelName;
import SmartHomeDDD.valueObject.UnitDescription;
import SmartHomeDDD.valueObject.UnitSymbol;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ActuatorConfigurationServiceTest {

  @Test
  void shouldThrowIllegalArgumentException_WhenActuatorModelRepositoryIsNull() {
    // Arrange
    ActuatorModelRepository ActuatorModelRepository = null;
    UnitRepository unitRepository = mock(UnitRepository.class);
    ActuatorModelFactory ActuatorModelFactory = mock(ActuatorModelFactory.class);
    UnitFactory unitFactory = mock(UnitFactory.class);
    String expectedMessage = "Please enter a valid Actuator model repository.";
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new ActuatorConfigurationService(
                    ActuatorModelRepository, unitRepository, ActuatorModelFactory, unitFactory));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldThrowIllegalArgumentException_WhenMeasurementTypeRepositoryIsNull() {
    // Arrange
    ActuatorModelRepository ActuatorModelRepository = mock(ActuatorModelRepository.class);
    UnitRepository unitRepository = null;
    ActuatorModelFactory ActuatorModelFactory = mock(ActuatorModelFactory.class);
    UnitFactory unitFactory = mock(UnitFactory.class);
    String expectedMessage = "Please enter a valid measurement type repository.";
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new ActuatorConfigurationService(
                    ActuatorModelRepository, unitRepository, ActuatorModelFactory, unitFactory));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldThrowIllegalArgumentException_WhenActuatorModelFactoryIsNull() {
    // Arrange
    ActuatorModelRepository ActuatorModelRepository = mock(ActuatorModelRepository.class);
    UnitRepository unitRepository = mock(UnitRepository.class);
    ActuatorModelFactory ActuatorModelFactory = null;
    UnitFactory unitFactory = mock(UnitFactory.class);
    String expectedMessage = "Please enter a valid Actuator model factory.";
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new ActuatorConfigurationService(
                    ActuatorModelRepository, unitRepository, ActuatorModelFactory, unitFactory));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldThrowIllegalArgumentException_WhenUnitFactoryIsNull() {
    // Arrange
    ActuatorModelRepository ActuatorModelRepository = mock(ActuatorModelRepository.class);
    UnitRepository unitRepository = mock(UnitRepository.class);
    ActuatorModelFactory ActuatorModelFactory = mock(ActuatorModelFactory.class);
    UnitFactory unitFactory = null;
    String expectedMessage = "Please enter a valid unit factory.";
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new ActuatorConfigurationService(
                    ActuatorModelRepository, unitRepository, ActuatorModelFactory, unitFactory));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldLoadDefaultActuatorModels_WhenConfigurationServiceInstantiated()
      throws ConfigurationException {
    // Arrange
    ActuatorModelFactory ActuatorModelFactory = mock(ActuatorModelFactory.class);
    when(ActuatorModelFactory.createActuatorModel(any(), any(), any()))
        .thenReturn(mock(ActuatorModel.class));
    ActuatorModelRepository ActuatorModelRepository = mock(ActuatorModelRepository.class);
    UnitRepository unitRepository = mock(UnitRepository.class);
    UnitFactory unitFactory = mock(UnitFactory.class);

    Configurations configs = new Configurations();
    int defaultActuatorModels =
        configs.properties(new File("config.properties")).getStringArray("Actuator").length;

    try (MockedConstruction<ModelPath> modelPathMockedConstruction =
            mockConstruction(ModelPath.class, (mock, context) -> {});
        MockedConstruction<ActuatorModelName> ActuatorModelMockedConstruction =
            mockConstruction(ActuatorModelName.class, (mock, context) -> {})) {

      // Act
      ActuatorConfigurationService actuatorConfigurationService =
          new ActuatorConfigurationService(
              ActuatorModelRepository, unitRepository, ActuatorModelFactory, unitFactory);
      verify(ActuatorModelFactory, times(defaultActuatorModels))
          .createActuatorModel(any(), any(), any());
    } catch (InstantiationException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void shouldLoadDefaultMeasurementTypes_WhenConfigurationServiceInstantiated()
      throws ConfigurationException {
    // Arrange
    ActuatorModelFactory ActuatorModelFactory = mock(ActuatorModelFactory.class);
    ActuatorModelRepository ActuatorModelRepository = mock(ActuatorModelRepository.class);
    UnitRepository unitRepository = mock(UnitRepository.class);
    UnitFactory unitFactory = mock(UnitFactory.class);
    when(unitFactory.createMeasurement(any(), any())).thenReturn(mock(Unit.class));
    Configurations configs = new Configurations();
    int defaultActuatorModels =
        configs.properties(new File("config.properties")).getStringArray("measurement").length;

    try (MockedConstruction<UnitDescription> measurementDescriptionConstruction =
            mockConstruction(UnitDescription.class, (mock, context) -> {});
        MockedConstruction<UnitSymbol> measurementTUnitConstruction =
            mockConstruction(UnitSymbol.class, (mock, context) -> {})) {

      // Act
      ActuatorConfigurationService ActuatorConfigurationService =
          new ActuatorConfigurationService(
              ActuatorModelRepository, unitRepository, ActuatorModelFactory, unitFactory);
      verify(unitFactory, times(defaultActuatorModels)).createMeasurement(any(), any());
    } catch (InstantiationException e) {
      throw new RuntimeException(e);
    }
  }
}