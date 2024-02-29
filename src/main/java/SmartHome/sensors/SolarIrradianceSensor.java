package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

public class SolarIrradianceSensor implements Sensor {
    private SolarIrradianceValueFactory _solarIrradianceValueFactory;
    private SensorType _sensorType;
    private SolarIrradianceValue _value;

    /**
     * Constructor for SolarIrradianceSensor
     *
     * @param catalogue is the catalogue of sensors
     */
    public SolarIrradianceSensor(CatalogueSensor catalogue, SolarIrradianceValueFactory solarIrradianceValueFactory, double value) {
        this._solarIrradianceValueFactory = solarIrradianceValueFactory;
        setSensorType(catalogue);
        setValue(value);
    }

    /**
     * Sets the sensor type
     *
     * @param catalogue is the catalogue of sensors
     */
    private void setSensorType(CatalogueSensor catalogue){
        SensorType sensorType = catalogue.getSensorType("SolarIrradiance");

        if (sensorType == null) {
            throw new IllegalArgumentException("SensorType with description 'SolarIrradiance' does not exist.");
        }

        this._sensorType = sensorType;
    }

    /**
     * Method to set the value of the sensor
     */
    private void setValue(double value){
        this._value = _solarIrradianceValueFactory.createSolarIrradianceValue(value);
    }


    /**
     * Method to get the sensor type
     *
     * @return SensorType
     */
    public SensorType getSensorType() {
        return _sensorType;
    }

    /**
     * Method to get the value of the sensor
     *
     * @return Value
     */
    public SolarIrradianceValue getValue() {
        return this._value.clone();
    }
}
