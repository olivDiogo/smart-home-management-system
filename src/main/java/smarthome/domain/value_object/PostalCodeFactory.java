package smarthome.domain.value_object;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PostalCodeFactory implements IPostalCodeFactory {

  /**
   * Creates a PostalCode object based on the given postal code and country code.
   *
   * @param postalCode  The postal code value.
   * @param countryCode The country code to determine the implementation class.
   * @return A PostalCode object instantiated based on the provided postal code and country code.
   * @throws IllegalArgumentException If the postal code implementation for the given country code
   *                                  is not found.
   * @throws RuntimeException         If an error occurs during the instantiation of the postal code
   *                                  implementation.
   */

  public IPostalCode createPostalCode(String postalCode, String countryCode) {
    try {
      String className = "smarthome.domain.value_object.PostalCode" + countryCode + "Impl";
      Class<?> clazz = Class.forName(className);
      Constructor<?> constructor = clazz.getConstructor(String.class);
      return (IPostalCode) constructor.newInstance(postalCode);
    } catch (ClassNotFoundException e) {
      throw new IllegalArgumentException(
          "Postal code implementation not found for country code: " + countryCode);
    } catch (NoSuchMethodException | IllegalAccessException | InstantiationException |
             InvocationTargetException e) {
      throw new RuntimeException(
          "Error instantiating postal code implementation for country code: " + countryCode, e);
    }
  }
}