package smarthome.domain.actuator_model;

import smarthome.ddd.IAggregateRoot;
import smarthome.domain.value_object.ActuatorModelName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;
import smarthome.utils.Validator;

public class ActuatorModel implements IAggregateRoot<ModelPath> {

  private final ActuatorModelName name;
  private final ModelPath modelPath;
  private final ActuatorTypeID actuatorTypeID;

  /**
   * ActuatorModel constructor
   *
   * @param name The name of the actuator model
   * @param modelPath         The path to the model
   */
  ActuatorModel(
      ActuatorModelName name, ModelPath modelPath, ActuatorTypeID actuatorTypeID) {
    Validator.validateNotNull(name, "ActuatorModelName");
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(actuatorTypeID, "ActuatorTypeID");
    this.name = name;
    this.modelPath = modelPath;
    this.actuatorTypeID = actuatorTypeID;
  }


  /**
   * Equals method for actuator model
   *
   * @param object Object
   * @return boolean
   */
  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    ActuatorModel actuatorModel = (ActuatorModel) object;
    return modelPath.equals(actuatorModel.modelPath);
  }

  /**
   * Hash code method
   */
  @Override
  public int hashCode() {
    return modelPath.hashCode();
  }

  /**
   * Get actuator model ID
   *
   * @return ActuatorModelID
   */
  @Override
  public ModelPath getID() {
    return modelPath;
  }

  /**
   * Get actuator model name
   *
   * @return ActuatorModelName
   */
  public ActuatorModelName getName() {
    return name;
  }

  /**
   * method to get sensor type id
   */
  public ActuatorTypeID getActuatorTypeID() {
    return actuatorTypeID;
  }

  /**
   * To string method for actuator model
   *
   * @return String
   */
  @Override
  public String toString() {
    return name + " " + modelPath + " " + actuatorTypeID;
  }
}