package smart_home.persistence.assembler;

import org.junit.jupiter.api.Test;
import smart_home.domain.house.House;
import smart_home.domain.house.HouseFactoryImpl;
import smart_home.domain.house.IHouseFactory;
import smart_home.persistence.jpa.data_model.HouseDataModel;
import smart_home.value_object.Address;
import smart_home.value_object.GPS;
import smart_home.value_object.HouseID;
import smart_home.value_object.IPostalCodeFactory;
import smart_home.value_object.PostalCodeFactory;
import smart_home.value_object.PostalCodePTImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HouseDataModelAssemblerTest {

  @Test
  void shouldInstantiateHouseDataModelAssembler_whenHouseFactoryIsValid() {
    // Arrange
    IHouseFactory houseFactory = mock(HouseFactoryImpl.class);

    // Act
    HouseDataModelAssembler houseDataModelAssembler = new HouseDataModelAssembler(houseFactory);

    // Assert
    assertNotNull(houseDataModelAssembler);
  }

  @Test
  void shouldThrowIllegalArgumentException_whenHouseFactoryIsNull() {
    // Arrange
    IHouseFactory houseFactory = null;
    String expectedMessage = "House Factory cannot be null.";

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new HouseDataModelAssembler(houseFactory));

    // Assert
    String actualMessage = exception.getMessage();
    assertNotNull(expectedMessage, actualMessage);
  }

  @Test
  void shouldReturnHouse_whenGivenValidHouseDataModel() {
    // Arrange
    HouseFactoryImpl houseFactoryDouble = mock(HouseFactoryImpl.class);
    when(houseFactoryDouble.createHouse(any(Address.class), any(GPS.class))).thenReturn(mock(House.class));

    HouseDataModelAssembler houseDataModelAssembler = new HouseDataModelAssembler(
        houseFactoryDouble);

    HouseDataModel houseDataModelDouble = mock(HouseDataModel.class);

    HouseID houseIDDouble = mock(HouseID.class);
    when(houseIDDouble.getID()).thenReturn("1L");

    IPostalCodeFactory postalCodeFactoryDouble = mock(PostalCodeFactory.class);
    when(postalCodeFactoryDouble.createPostalCode(any(String.class), any(String.class)))
        .thenReturn(new PostalCodePTImpl("1234-599"));

    Address addressDouble = mock(Address.class);
    when(addressDouble.getStreet()).thenReturn("street");
    when(addressDouble.getDoorNumber()).thenReturn("1");

    GPS gpsDouble = mock(GPS.class);
    when(gpsDouble.getLatitude()).thenReturn(1.0);
    when(gpsDouble.getLongitude()).thenReturn(1.0);

    when(houseDataModelDouble.getHouseID()).thenReturn("1L");
    when(houseDataModelDouble.getStreet()).thenReturn("street");
    when(houseDataModelDouble.getDoorNumber()).thenReturn("1");
    when(houseDataModelDouble.getPostalCode()).thenReturn("1234-599");
    when(houseDataModelDouble.getCountryCode()).thenReturn("PT");
    when(houseDataModelDouble.getLatitude()).thenReturn(1.0);
    when(houseDataModelDouble.getLongitude()).thenReturn(1.0);

    House expected = houseFactoryDouble.createHouse(houseIDDouble, addressDouble, gpsDouble);


    // Act
    House actual = houseDataModelAssembler.toDomain(houseDataModelDouble);

    // Assert
    assertEquals(expected, actual);
  }

  @Test
  void shouldReturnListOfHouses_WhenGivenListOfHousesDataModels () {
    // Arrange
    HouseFactoryImpl houseFactoryDouble = mock(HouseFactoryImpl.class);
    when(houseFactoryDouble.createHouse(any(Address.class), any(GPS.class))).thenReturn(mock(House.class));

    HouseDataModelAssembler houseDataModelAssembler = new HouseDataModelAssembler(
        houseFactoryDouble);

    HouseDataModel houseDataModelDouble = mock(HouseDataModel.class);

    HouseID houseIDDouble = mock(HouseID.class);
    when(houseIDDouble.getID()).thenReturn("1L");

    IPostalCodeFactory postalCodeFactoryDouble = mock(PostalCodeFactory.class);
    when(postalCodeFactoryDouble.createPostalCode(any(String.class), any(String.class)))
        .thenReturn(new PostalCodePTImpl("1234-599"));

    Address addressDouble = mock(Address.class);
    when(addressDouble.getStreet()).thenReturn("street");
    when(addressDouble.getDoorNumber()).thenReturn("1");

    GPS gpsDouble = mock(GPS.class);
    when(gpsDouble.getLatitude()).thenReturn(1.0);
    when(gpsDouble.getLongitude()).thenReturn(1.0);

    when(houseDataModelDouble.getHouseID()).thenReturn("1L");
    when(houseDataModelDouble.getStreet()).thenReturn("street");
    when(houseDataModelDouble.getDoorNumber()).thenReturn("1");
    when(houseDataModelDouble.getPostalCode()).thenReturn("1234-599");
    when(houseDataModelDouble.getCountryCode()).thenReturn("PT");
    when(houseDataModelDouble.getLatitude()).thenReturn(1.0);
    when(houseDataModelDouble.getLongitude()).thenReturn(1.0);

    List<HouseDataModel> houseDataModels = List.of(houseDataModelDouble);

    House expected = houseFactoryDouble.createHouse(houseIDDouble, addressDouble, gpsDouble);

    // Act
    List<House> houses = houseDataModelAssembler.toDomain(houseDataModels);

    // Assert
    assertEquals(expected, houses.get(0));
  }


}