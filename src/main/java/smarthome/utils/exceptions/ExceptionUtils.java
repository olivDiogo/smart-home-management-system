/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils.exceptions;

public class ExceptionUtils {
  public static String generateNotFoundMessage(String domainObject, String objectId) {
    return "No " + domainObject + " found with ID " + objectId;
  }
}
