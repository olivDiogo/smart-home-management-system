package smarthome.utils.dto.data_dto.actuator_data_dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ActuatorDataGenericDTOImp.class, name = "genericActuator"),
    @JsonSubTypes.Type(value = ActuatorDataWithIntegerLimitsDTOImp.class, name = "integerActuator"),
    @JsonSubTypes.Type(value = ActuatorDataWithDecimalLimitsDTOImp.class, name = "decimalActuator"),
})
public interface IActuatorDataDTO {

}
