package smart_home.domain.sensor.sunrise_time_sensor;

import org.shredzone.commons.suncalc.SunTimes;
import smart_home.domain.sensor.ISensor;
import smart_home.value_object.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class SunriseTimeSensor implements ISensor {

    private SunriseTimeSensorValue _sunriseTimeValue;
    private SensorTypeID _sensorTypeID;
    private SensorID _sensorID;
    private SensorName _sensorName;
    private DeviceID _deviceID;
    private ModelPath _modelPath;
    private GPS gps;

    /**
     * Creates a new SunriseTimeSensor with a given catalogue.
     *
     * @throws InstantiationException if the SensorType with description 'SunriseTime' does not exist.
     */
    public SunriseTimeSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName, GPS gps) {
        validateDeviceID(deviceID);
        validateSensorTypeID(sensorTypeID);
        validateModelPath(modelPath);
        validateSensorName(sensorName);
        generateSensorID();
        validateGPS(gps);

        this._deviceID = deviceID;
        this._sensorTypeID = sensorTypeID;
        this._modelPath = modelPath;
        this._sensorName = sensorName;
        this.gps = gps;
    }

    /**
     * Creates a new SunriseTimeSensor with a given catalogue.
     *
     * @throws InstantiationException if the SensorType with description 'SunriseTime' does not exist.
     */
    public SunriseTimeSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName, GPS gps, SensorID sensorID) {
        validateDeviceID(deviceID);
        validateSensorTypeID(sensorTypeID);
        validateModelPath(modelPath);
        validateSensorName(sensorName);
        validateGPS(gps);
        validateSensorID(sensorID);

        this._deviceID = deviceID;
        this._sensorTypeID = sensorTypeID;
        this._modelPath = modelPath;
        this._sensorName = sensorName;
        this.gps = gps;
        this._sensorID = sensorID;
    }


    /**
     * Validates the sensorID.
     * @param sensorID The sensorID.
     */
    private void validateSensorID(SensorID sensorID) {
        if (sensorID == null) {
            throw new IllegalArgumentException("SensorID cannot be null.");
        }
    }


    private void validateDeviceID(DeviceID deviceID) {
        if (deviceID == null) {
            throw new IllegalArgumentException("DeviceID cannot be null.");
        }
    }

    private void validateSensorTypeID(SensorTypeID sensorTypeID) {
        if (sensorTypeID == null) {
            throw new IllegalArgumentException("SensorTypeID cannot be null.");
        } else if (!sensorTypeID.getID().equals("SunriseTime")) {
            throw new IllegalArgumentException("SensorTypeID must be 'SunriseTime'.");
        }
    }

    private void validateModelPath(ModelPath modelPath) {
        if (modelPath == null) {
            throw new IllegalArgumentException("ModelPath cannot be null.");
        }
    }

    private void validateSensorName(SensorName sensorName) {
        if (sensorName == null) {
            throw new IllegalArgumentException("SensorName cannot be null");
        }
    }

    private void generateSensorID() {
        _sensorID = new SensorID(java.util.UUID.randomUUID().toString());
    }

    /**
     * Configures the GPS location of the SunriseTimeSensor.
     *
     * @param gps the GPS location to be used.
     * @throws IllegalArgumentException if the GPS location is null.
     */
    private void validateGPS(GPS gps) {
        if (gps == null) {
            throw new IllegalArgumentException("GPS cannot be null.");
        }
    }

    /**
     * Gets the Sunrise Time of the GPS location for a given date.
     *
     * @param date the date to be used.
     * @return the Sunrise Time of the GPS location for a given date.
     */
    private LocalTime getSunriseTime(LocalDate date) {
        SunTimes time = SunTimes.compute().on(date).at(gps.getLatitude(), gps.getLongitude()).execute();
        LocalTime sunrise = Objects.requireNonNull(time.getRise()).toLocalTime();
        return sunrise;
    }

    @Override
    public SensorID getID() {
        return _sensorID;
    }

    @Override
    public SensorName getName() {
        return _sensorName;
    }

    @Override
    public ModelPath getModelPath() {
        return _modelPath;
    }

    @Override
    public SensorTypeID getSensorTypeID() {
        return _sensorTypeID;
    }

    @Override
    public DeviceID getDeviceID() {
        return _deviceID;
    }

    /**
     * Gets the value of the SunriseTimeSensor for the current day.
     *
     * @return the value of the SunriseTimeSensor.
     */
    @Override
    public SunriseTimeSensorValue getValue() {
        LocalTime sunrise = getSunriseTime(LocalDate.now());
        this._sunriseTimeValue = new SunriseTimeSensorValue(sunrise);
        return this._sunriseTimeValue;
    }

    /**
     * Gets the value of the SunriseTimeSensor for a given date.
     *
     * @param date the date to be used.
     * @return the value of the SunriseTimeSensor for a given date.
     */
    public SunriseTimeSensorValue getValue(LocalDate date) {
        LocalTime sunrise = getSunriseTime(date);
        this._sunriseTimeValue = new SunriseTimeSensorValue(sunrise);
        return this._sunriseTimeValue;
    }

    /**
     * Equals method for SunriseTimeSensor.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof SunriseTimeSensor sunriseTimeObject) {
            return _sensorID.equals(sunriseTimeObject._sensorID);
        }
        return false;
    }

    /**
     * HashCode method for SunriseTimeSensor.
     */
    @Override
    public int hashCode() {
        return _sensorID.hashCode();
    }

    /**
     * ToString method for SunriseTimeSensor.
     */
    @Override
    public String toString() {
        return "SunriseTimeSensor:" +
                ", sensorID=" + _sensorID +
                ", sensorName=" + _sensorName +
                ", modelPath=" + _modelPath +
                ", sensorTypeID=" + _sensorTypeID +
                ", deviceID=" + _deviceID +
                ", gps=" + gps;
    }
}
