package SmartHomeDDD.domain.SensorModel;

import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.valueObject.SensorModelID;
import SmartHomeDDD.valueObject.SensorTypeID;

import java.util.List;

public interface SensorModelRepo extends Repository<SensorModelID, SensorModel> {

    public List<SensorModel> findBySensorTypeId(SensorTypeID sensorModelID);
}