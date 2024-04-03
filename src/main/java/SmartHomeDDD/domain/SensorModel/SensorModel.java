package SmartHomeDDD.domain.SensorModel;

import SmartHomeDDD.ddd.AggregateRoot;
import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorModelID;
import SmartHomeDDD.valueObject.SensorModelName;
import SmartHomeDDD.valueObject.SensorTypeID;


public class SensorModel implements AggregateRoot<SensorModelID> {
    private SensorModelID _sensorModelID;
    private SensorModelName _sensorModelName;
    private ModelPath _modelPath;
    private SensorTypeID _sensorTypeID;

    public SensorModel(SensorModelName sensorModelName, ModelPath modelPath, SensorTypeID sensorTypeID) {
        validateSensorModelName(sensorModelName);
        validateModelPath(modelPath);
        validateSensorTypeID(sensorTypeID);
        generateID(modelPath);
    }
    private void generateID(ModelPath modelPath){
        _sensorModelID = new SensorModelID(modelPath.toString());
    }
    private void validateSensorTypeID(SensorTypeID sensorTypeID) {
        if (sensorTypeID == null) {
            throw new IllegalArgumentException("Please enter a valid sensor type ID.");
        } else {
            this._sensorTypeID = sensorTypeID;
        }
    }
    private void validateSensorModelName(SensorModelName sensorModelName) {
        if (sensorModelName == null) {
            throw new IllegalArgumentException("Please enter a valid sensor model name.");
        } else {
            this._sensorModelName = sensorModelName;
        }
    }
    private void validateModelPath(ModelPath modelPath) {
        if (modelPath == null) {
            throw new IllegalArgumentException("Please enter a valid model path.");
        } else {
            this._modelPath = modelPath;
        }
    }
    public SensorTypeID getSensorTypeID() {
        return _sensorTypeID;
    }
    public SensorModelName getSensorModelName() {
        return _sensorModelName;
    }
    public ModelPath getModelPath() {
        return _modelPath;
    }
    @Override
    public SensorModelID getID() {
        return _sensorModelID;
    }
    @Override
    public boolean equals(Object o) {
        if ( o instanceof SensorModel castedSensorModel) {
            return _sensorModelID.equals(castedSensorModel._sensorModelID);
        }
        return false;
    }
    @Override
    public String toString() {
        return "SensorModel{" +
                "_sensorModelID=" + _sensorModelID +
                ", _sensorModelName=" + _sensorModelName +
                ", _modelPath=" + _modelPath +
                '}';
    }
}
