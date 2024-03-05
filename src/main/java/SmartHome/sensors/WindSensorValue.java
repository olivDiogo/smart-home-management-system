package SmartHome.sensors;

import SmartHome.domain.Value;

public class WindSensorValue implements Value {

    public double _speed;
    public double _direction;

    /**
     * Constructor of the class.
     * @param speed
     * @param direction
     */
    public WindSensorValue(double speed, double direction)
    {
        this._speed = speed;
        this._direction = direction;
    }

    /**
     * Method to get the value of the speed.
     * @return
     */
    public double getSpeed () {
        return this._speed;
    }

    /**
     * Method to get the value of the direction.
     * @return
     */
    public double getDirection () {
        return this._direction;
    }

    /**
     * Method to clone the object.
     * @return
     */
    @Override
    public WindSensorValue clone() {
        try {
            // Call the Object clone() method
            return (WindSensorValue) super.clone();   // (DewPointValue) super.clone();
        } catch (CloneNotSupportedException e) {

            throw new AssertionError();
        }
    }
}