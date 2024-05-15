package smarthome.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import smarthome.ddd.IAssembler;
import smarthome.domain.exceptions.EmptyReturnException;
import smarthome.domain.house.House;
import smarthome.utils.Validator;
import smarthome.utils.dto.HouseDTO;

@Component
public class HouseAssembler implements IAssembler<House, HouseDTO> {

  /**
   * Method to convert a House into a HouseDTO.
   *
   * @param house is the House to be converted.
   * @return the HouseDTO.
   */
  @Override
  public HouseDTO domainToDTO(final House house) {
    Validator.validateNotNull(house, "House");
    String address = house.getAddress().toString();
    String gps = house.getGps().toString();
    String houseID = house.getID().toString();

    return new HouseDTO(address, gps, houseID);
  }

  /**
   * Method to convert a list of Houses into a list of HouseDTOs.
   *
   * @param houses is the list of Houses to be converted.
   * @return the list of HouseDTOs.
   */
  @Override
  public List<HouseDTO> domainToDTO(final List<House> houses) throws EmptyReturnException {
    if (houses == null) {
      throw new IllegalArgumentException("The list of Houses cannot be null.");
    }

    return houses.stream().map(this::domainToDTO).toList();
  }

}


