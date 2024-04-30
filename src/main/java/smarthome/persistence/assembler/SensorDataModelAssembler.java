package smarthome.persistence.assembler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.sensor.ISensorFactory;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.persistence.jpa.data_model.SensorDataModel;
import smarthome.utils.Validator;

public class SensorDataModelAssembler implements IDataModelAssembler<SensorDataModel, ISensor> {

  private final ISensorFactory sensorFactory;
  private final List<Object> parameters = new ArrayList<>();

  public SensorDataModelAssembler(ISensorFactory sensorFactory) {
    Validator.validateNotNull(sensorFactory, "Sensor factory");
    this.sensorFactory = sensorFactory;
  }

  public ISensor toDomain(SensorDataModel sensorDataModel) {
    Validator.validateNotNull(sensorDataModel, "Sensor data model");
    parameters.clear();
    getDeviceID(sensorDataModel);
    getModelPath(sensorDataModel);
    getSensorTypeID(sensorDataModel);
    getSensorName(sensorDataModel);
    getGPS(sensorDataModel);
    getDatePeriod(sensorDataModel);
    getSensorID(sensorDataModel);
    return sensorFactory.create(parameters.toArray());
  }

  public List<ISensor> toDomain(List<SensorDataModel> sensorDataModels) {
    List<ISensor> sensors = new ArrayList<>();
    for (SensorDataModel sensorDataModel : sensorDataModels) {
      ISensor sensor = toDomain(sensorDataModel);
      sensors.add(sensor);
    }
    return sensors;
  }

  private boolean getDeviceID(SensorDataModel sensorDataModel) {
    DeviceID deviceID = new DeviceID(sensorDataModel.getDeviceID());
    parameters.add(deviceID);
    return true;
  }

  private boolean getModelPath(SensorDataModel sensorDataModel) {
    ModelPath modelPath = new ModelPath(sensorDataModel.getModelPath());
    parameters.add(modelPath);
    return true;
  }

  private boolean getSensorTypeID(SensorDataModel sensorDataModel) {
    SensorTypeID sensorTypeID = new SensorTypeID(sensorDataModel.getSensorTypeID());
    parameters.add(sensorTypeID);
    return true;
  }

  private boolean getSensorName(SensorDataModel sensorDataModel) {
    SensorName sensorName = new SensorName(sensorDataModel.getSensorName());
    parameters.add(sensorName);
    return true;
  }

  private boolean getGPS(SensorDataModel sensorDataModel) {
    if (sensorDataModel.getLatitude() != null && sensorDataModel.getLongitude() != null) {
      double latitude = Double.parseDouble(sensorDataModel.getLatitude());
      double longitude = Double.parseDouble(sensorDataModel.getLongitude());
      GPS gps = new GPS(latitude, longitude);
      parameters.add(gps);
      return true;
    }
    return false;
  }

  private boolean getDatePeriod(SensorDataModel sensorDataModel) {
    if (sensorDataModel.getStartDate() != null && sensorDataModel.getEndDate() != null) {
      LocalDateTime startDate = LocalDateTime.parse(sensorDataModel.getStartDate());
      LocalDateTime endDate = LocalDateTime.parse(sensorDataModel.getEndDate());
      DatePeriod datePeriod = new DatePeriod(startDate, endDate);
      parameters.add(datePeriod);
      return true;
    }
    return false;
  }

  private boolean getSensorID(SensorDataModel sensorDataModel) {
    SensorID sensorID = new SensorID(sensorDataModel.getSensorID());
    parameters.add(sensorID);
    return true;
  }
}