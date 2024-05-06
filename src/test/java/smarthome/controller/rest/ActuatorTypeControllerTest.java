package smarthome.controller.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.actuator_type.ActuatorTypeFactoryImpl;
import smarthome.domain.actuator_type.IActuatorTypeFactory;
import smarthome.domain.service.IActuatorTypeService;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.mapper.ActuatorTypeAssembler;
import smarthome.utils.dto.ActuatorTypeDTO;

@SpringBootTest
@AutoConfigureMockMvc
class ActuatorTypeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private IActuatorTypeService actuatorTypeService;

  @MockBean
  private ActuatorTypeAssembler actuatorTypeAssembler;


  /**
   * This test case verifies that the ActuatorTypeController returns a list of actuator types when
   * they are found.
   */
  @Test
  void shouldReturnActuatorTypes_WhenFound() throws Exception {
    ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO("actuatorTypeID",
        "actuatorTypeDescription", "unit");
    List<ActuatorTypeDTO> actuatorTypeDTOs = List.of(actuatorTypeDTO);
    IActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    TypeDescription typeDescription = new TypeDescription("typeDescription");
    UnitID unitID = new UnitID("unitID");
    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(typeDescription, unitID);
    when(actuatorTypeService.getAllActuatorTypes()).thenReturn(List.of(actuatorType));
    when(actuatorTypeAssembler.domainToDTO((List<ActuatorType>) any())).thenReturn(
        actuatorTypeDTOs);

    mockMvc.perform(get("/actuator-type/all")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._links.self").exists());
  }

  /**
   * This test case verifies that the ActuatorTypeController returns a 404 Not Found status when no
   * actuator types are available.
   */
  @Test
  void shouldReturnNotFoundWhenNoActuatorTypesAvailable() throws Exception {
    // Arrange: Configure the service to return an empty list when called
    when(actuatorTypeService.getAllActuatorTypes()).thenReturn(List.of());

    // Act & Assert: Perform the request and expect a 404 Not Found status
    mockMvc.perform(get("/actuator-type/all")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

}