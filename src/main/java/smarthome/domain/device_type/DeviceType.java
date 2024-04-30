package smarthome.domain.device_type;

import java.util.UUID;
import smarthome.ddd.IAggregateRoot;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.utils.Validator;

public class DeviceType implements IAggregateRoot<DeviceTypeID> {

  private final TypeDescription description;
  private DeviceTypeID id;

  /**
   * Creates a new instance of the {@link DeviceType} class.
   *
   * @param deviceTypeDescription The description of the device type.
   */
  DeviceType(TypeDescription deviceTypeDescription) {
    generateDeviceTypeID();
    Validator.validateNotNull(deviceTypeDescription, "DeviceTypeDescription");
    this.description = deviceTypeDescription;
  }

  /**
   * Creates a new instance of the {@link DeviceType} class, with the specified ID and description.
   */
  DeviceType(DeviceTypeID id, TypeDescription description) {
    this.id = id;
    this.description = description;
  }


  /**
   * Generates a new device type ID.
   */
  private void generateDeviceTypeID() {
    this.id = new DeviceTypeID(UUID.randomUUID().toString());
  }

  /**
   * Gets the device type ID.
   *
   * @return The device type ID.
   */
  @Override
  public DeviceTypeID getID() {
    return id;
  }

  /**
   * Gets the description of the device type.
   *
   * @return The description.
   */
  public TypeDescription getDescription() {
    return description;
  }

  /**
   * Compares this instance with another instance.
   *
   * @param object is the object to be compared.
   * @return true if the instances are equal, false otherwise.
   */
  @Override
  public boolean equals(Object object) {
    if (object instanceof DeviceType deviceType) {
      return this.id.equals(deviceType.getID());
    }
    return false;
  }

  /**
   * Generates a hash code for the device type.
   *
   * @return The hash code.
   */
  @Override
  public int hashCode() {
    return this.id.hashCode();
  }

  /**
   * Returns a string representation of the device type.
   *
   * @return The string representation.
   */
  public String toString() {
    return "Device Type:  Device Description= "
        + description.getDescription()
        + " ID= "
        + id.getID();
  }
}