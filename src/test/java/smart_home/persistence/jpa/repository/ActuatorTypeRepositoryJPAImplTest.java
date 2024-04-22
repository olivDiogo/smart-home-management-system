package smart_home.persistence.jpa.repository;

import org.junit.jupiter.api.Test;
import smart_home.domain.actuator_type.ActuatorType;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.ActuatorTypeDataModel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ActuatorTypeRepositoryJPAImplTest {

    /**
     * Test to verify if the RepositoryActuatorTypeJPAImpl is instantiated correctly
     */
    @Test
    void shouldInstantiateRepositoryActuatorTypeJPAImpl_WHenGivenDataModelAssembler() {
        //Arrange
        IDataModelAssembler<ActuatorTypeDataModel, ActuatorType> dataModelAssembler = mock(IDataModelAssembler.class);

        //Act
        ActuatorTypeRepositoryJPAImpl repositoryActuatorTypeJPA = new ActuatorTypeRepositoryJPAImpl(dataModelAssembler);

        //Assert
        assertNotNull(repositoryActuatorTypeJPA);
    }

    /**
     * Test to verify if the RepositoryActuatorTypeJPAImpl throws an IllegalArgumentException when given a null data model assembler
     */
    @Test
    void shouldThrowIllegalArgumentException_whenGivenNullDataModelConverter() {
        //Arrange
        IDataModelAssembler<ActuatorTypeDataModel, ActuatorType> dataModelAssembler = null;

        String expectedMessage = "The data model converter must not be null.";

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorTypeRepositoryJPAImpl(dataModelAssembler));

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}