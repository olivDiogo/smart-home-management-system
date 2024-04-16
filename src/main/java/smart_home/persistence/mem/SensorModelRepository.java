package smart_home.persistence.mem;

import smart_home.domain.repository.ISensorModelRepository;
import smart_home.domain.sensor_model.SensorModel;
import smart_home.value_object.ModelPath;
import smart_home.value_object.SensorTypeID;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SensorModelRepository implements ISensorModelRepository {
    final private Map<ModelPath, SensorModel> DATA = new LinkedHashMap<>();

    @Override
    public SensorModel save(SensorModel entity) {
        if (entity == null)
            throw new IllegalArgumentException("SensorModel cannot be null.");
        else if (containsOfIdentity(entity.getID()))
            throw new IllegalArgumentException("SensorModel already exists.");
        else

            DATA.put(entity.getModelPath(), entity);
        return entity;
    }

    @Override
    public List<SensorModel> findAll() {
        return List.copyOf(DATA.values().stream().toList());
    }

    @Override
    public Optional<SensorModel> ofIdentity(ModelPath objectID) {
        return Optional.ofNullable(DATA.get(objectID));
    }

    @Override
    public boolean containsOfIdentity(ModelPath objectID) {
        return DATA.containsKey(objectID);
    }


    @Override
    public List<SensorModel> findBySensorTypeId(SensorTypeID sensorTypeID) {
        return DATA.values().stream().filter(sensorModel -> sensorModel.getSensorTypeID().equals(sensorTypeID)).toList();
    }
}