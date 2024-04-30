package smarthome.domain.value_object;

import smarthome.ddd.IDomainID;

public class SensorID implements IDomainID {

  private final String id;

  /**
   * Constructor for SensorID
   *
   * @param sensorID String
   */
  public SensorID(String sensorID) {
    validateSensorID(sensorID);
    this.id = sensorID.trim();
  }

  /**
   * Validate the SensorID The value of 'sensorID' should not null, blank, or empty.
   *
   * @param sensorID String
   */
  private void validateSensorID(String sensorID) {
    if (sensorID == null || sensorID.isBlank()) {
      throw new IllegalArgumentException(
          "The value of 'sensorID' should not null, blank, or empty.");
    }
  }

  /**
   * Getter for ID
   *
   * @return id
   */
  public String getID() {
    return id;
  }

  /**
   * Equals method for SensorID
   *
   * @param o Object
   * @return boolean
   */
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o instanceof SensorID objectSensorID) {

      return this.id.equals(objectSensorID.id);
    }
    return false;
  }

  /**
   * HashCode method for SensorID
   *
   * @return the hashcode as an int
   */
  public int hashCode() {
    return id.hashCode();
  }

  /**
   * toString method for SensorID
   *
   * @return the id as a string
   */
  public String toString() {
    return id;
  }
}