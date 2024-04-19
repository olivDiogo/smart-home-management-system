package smart_home.persistence.mem;

import org.apache.commons.lang3.Validate;
import smart_home.domain.device.Device;
import smart_home.domain.repository.IDeviceRepository;
import smart_home.utils.Validator;
import smart_home.value_object.DeviceID;
import smart_home.value_object.RoomID;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DeviceRepository implements IDeviceRepository {

    /**
     * Map to store the device data.
     */
    private final Map<DeviceID, Device> _deviceData = new LinkedHashMap<>();

    /**
     * Method to save a domain entity.
     *
     * @param device is the domain entity to be saved.
     * @return the saved domain entity.
     */
    @Override
    public Device save(Device device) {
        Validator.validateNotNull(device, "Device");

        if (containsOfIdentity(device.getID())) {
            throw new IllegalArgumentException("Device already exists.");
        } else {
            _deviceData.put(device.getID(), device);
        }
        return device;
    }

    /**
     * Method to find all domain entities.
     *
     * @return
     */
    @Override
    public List<Device> findAll() {
        List<Device> allDevices = _deviceData.values().stream().toList();
        return allDevices;
    }

    /**
     * Method to find a domain entity by its unique identifier.
     *
     * @param deviceID is the unique identifier of the domain entity.
     * @return
     */
    @Override
    public Optional<Device> ofIdentity(DeviceID deviceID) {
        Optional<Device> device = Optional.ofNullable(_deviceData.get(deviceID));
        return device;
    }

    /**
     * Method to check if a domain entity exists by its unique identifier.
     *
     * @param deviceID is the unique identifier of the domain entity.
     * @return
     */
    @Override
    public boolean containsOfIdentity(DeviceID deviceID) {
        return _deviceData.containsKey(deviceID);
    }

    /**
     * Method to find all devices in a room.
     *
     * @param roomId is the unique identifier of the room.
     * @return a list of devices in the room.
     */

    @Override
    public List<Device> findBy_roomID(RoomID roomId) {
        List<Device> devices = _deviceData.values().stream().filter(device -> device.getRoomID().equals(roomId)).toList();
        return devices;
    }
    /**
     * Method to update a device
     */
    @Override
    public Device update(Device device) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
