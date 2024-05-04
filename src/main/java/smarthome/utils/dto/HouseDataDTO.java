package smarthome.utils.dto;

import jakarta.validation.constraints.NotBlank;


/**
 * Data Transfer Object (DTO) representing information about a house.
 */
public class HouseDataDTO {

  /**
   * The street where the house is located.
   */
  @NotBlank(message = "Street cannot be empty")
  public final String street;

  /**
   * The door number of the house.
   */
  @NotBlank(message = "Door number cannot be empty")
  public final String doorNumber;

  /**
   * The postal code of the house.
   */
  @NotBlank(message = "Postal code cannot be empty")
  public final String postalCode;

  /**
   * The country code of the house.
   */
  @NotBlank(message = "Country code cannot be empty")
  public final String countryCode;

  /**
   * The latitude coordinate of the house location.
   */
  public final double latitude;

  /**
   * The longitude coordinate of the house location.
   */
  public final double longitude;

  /**
   * Constructs a new HouseDataDTO object with the specified details.
   *
   * @param street      The street where the house is located.
   * @param doorNumber  The door number of the house.
   * @param postalCode  The postal code of the house.
   * @param countryCode The country code of the house.
   * @param latitude    The latitude coordinate of the house location.
   * @param longitude   The longitude coordinate of the house location.
   */
  public HouseDataDTO(String street, String doorNumber, String postalCode, String countryCode,
      double latitude, double longitude) {
    this.street = street;
    this.doorNumber = doorNumber;
    this.postalCode = postalCode;
    this.countryCode = countryCode;
    this.latitude = latitude;
    this.longitude = longitude;
  }
}

