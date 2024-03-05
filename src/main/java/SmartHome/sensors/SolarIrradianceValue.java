package SmartHome.sensors;

import SmartHome.domain.Value;

public class SolarIrradianceValue implements Value, Cloneable {
    private double _dValue;

    /**
     * Constructor for SolarIrradianceValue
     *
     * @param nValue is the value of the solar irradiance
     */
    public SolarIrradianceValue(double nValue) {
        setValue(nValue);
    }

    /**
     * Method to set the value of the solar irradiance
     *
     * @param nValue is the value of the solar irradiance
     */
    private void setValue(double nValue) {
        this._dValue = nValue;
    }


    /**
     * Method to get the value of the solar irradiance
     *
     * @return double
     */
    public String toString() {
        return this._dValue + "";
    }

    /**
     * Method to clone the SolarIrradianceValue
     *
     * @return SolarIrradianceValue
     */
    @Override
    public SolarIrradianceValue clone() {
        try {
                SolarIrradianceValue cloned = (SolarIrradianceValue) super.clone();
                cloned._dValue = this._dValue;
                return cloned;
        } catch (CloneNotSupportedException e) {
                throw new AssertionError();
        }

    }

}

