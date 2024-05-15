package smarthome.mapper;


import java.util.List;
import org.springframework.stereotype.Component;
import smarthome.ddd.IAssembler;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.exceptions.EmptyReturnException;
import smarthome.utils.Validator;
import smarthome.utils.dto.DeviceTypeDTO;

/**
 * The DeviceTypeAssembler class is responsible for converting DeviceType domain entities to
 * DeviceTypeDTO data transfer objects.
 */
@Component
public class DeviceTypeAssembler implements IAssembler<DeviceType, DeviceTypeDTO> {


  /**
   * Converts a DeviceType domain entity to a DeviceTypeDTO data transfer object.
   *
   * @return The DeviceTypeDTO data transfer object.
   * @throws IllegalArgumentException if deviceType is null.
   */
  @Override
  public DeviceTypeDTO domainToDTO(DeviceType domainEntity) {
    Validator.validateNotNull(domainEntity, "DeviceType");

    String deviceTypeDescription = domainEntity.getDescription().toString();

    return new DeviceTypeDTO(deviceTypeDescription);
  }

  /**
   * Converts a list of DeviceType domain entities to a list of DeviceTypeDTO data transfer
   * objects.
   *
   * @param deviceTypes the list of domain entities to be converted.
   * @return The list of DeviceTypeDTO data transfer objects.
   * @throws IllegalArgumentException if deviceTypes is null, empty, or contains null elements.
   */
  @Override
  public List<DeviceTypeDTO> domainToDTO(List<DeviceType> deviceTypes) throws EmptyReturnException {
    if (deviceTypes == null) {
      throw new IllegalArgumentException("The list of DeviceTypes cannot be null.");
    }
    // Convert each DeviceType entity to DeviceTypeDTO using domainToDTO method
    return deviceTypes.stream().map(this::domainToDTO).toList();
  }
}
