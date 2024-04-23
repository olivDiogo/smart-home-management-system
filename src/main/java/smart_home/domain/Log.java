package smart_home.domain;

import smart_home.ddd.IAggregateRoot;
import smart_home.utils.Validator;
import smart_home.value_object.DeviceID;
import smart_home.value_object.LogID;
import smart_home.value_object.SensorID;

import java.time.LocalDateTime;
import java.util.UUID;

public class Log implements IAggregateRoot<LogID> {
    private LogID _logID;
    private DeviceID _deviceID;
    private SensorID _sensorID;
    private LocalDateTime _localDateTime;
    private String _reading;

    /**
     * Constructs a new Log instance with the specified device ID, sensor ID, timestamp, and value.
     */
    Log(DeviceID deviceID, SensorID sensorID, LocalDateTime localDateTime, String value) {
        Validator.validateNotNull(deviceID, "Device ID");
        this._deviceID = deviceID;
        Validator.validateNotNull(sensorID, "Sensor ID");
        this._sensorID = sensorID;
        Validator.validateNotNull(localDateTime, "Timestamp");
        this._localDateTime = localDateTime;
        Validator.validateNotNull(value, "Value");
        this._reading = value;
        generateLogID();
    }

    /**
     * Constructs a new Log instance with the specified log ID, device ID, sensor ID, timestamp, and value.
     */
    Log(LogID logID, DeviceID deviceID, SensorID sensorID, LocalDateTime localDateTime, String value) {
        Validator.validateNotNull(logID, "Log ID");
        this._logID = logID;
        Validator.validateNotNull(deviceID, "Device ID");
        this._deviceID = deviceID;
        Validator.validateNotNull(sensorID, "Sensor ID");
        this._sensorID = sensorID;
        Validator.validateNotNull(localDateTime, "Timestamp");
        this._localDateTime = localDateTime;
        Validator.validateNotNull(value, "Value");
        this._reading = value;
    }

    /**
     * Generate a new LogID object.
     */
    private void generateLogID() {
        _logID = new LogID(UUID.randomUUID().toString());
    }



    /**
     * @return the _logID
     */
    @Override
    public LogID getID() {
        return _logID;
    }

    /**
     * @return the _deviceID
     */
    public DeviceID getDeviceID() {
        return _deviceID;
    }

    /**
     * @return the _sensorID
     */
    public SensorID getSensorID() {
        return _sensorID;
    }

    /**
     * @return the _timeStamp
     */
    public LocalDateTime getTimeStamp() {
        return _localDateTime;
    }

    /**
     * @return the _value
     */
    public String getValue() {
        return _reading;
    }

    /**
     * Compares the current object with another object of the same type.
     * @param obj is the object to be compared.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Log log) {
            return this._logID.equals(log.getID());
        }
        return false;
    }

    /**
     * Gets the hash code value of the object.
     * @return the hash code value of the object.
     */
    @Override
    public int hashCode() {
        return _logID.hashCode();
    }

    /**
     * Gets the string representation of the object.
     * @return the string representation of the object.
     */
    @Override
    public String toString() {
        return _logID + " " + _deviceID + " " + _sensorID + " " + _localDateTime + " " + _reading;
    }
}