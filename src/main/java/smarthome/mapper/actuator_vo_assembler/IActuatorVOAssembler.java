package smarthome.mapper.actuator_vo_assembler;

import smarthome.utils.dto.actuator_data_dto.IActuatorDataDTO;

public interface IActuatorVOAssembler {

  Object[] getActuatorParameters(IActuatorDataDTO actuatorDataDTO);
}