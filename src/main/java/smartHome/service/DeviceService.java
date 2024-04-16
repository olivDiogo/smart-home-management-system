package smartHome.service;

import smartHome.ddd.IRepository;
import smartHome.domain.device.Device;
import smartHome.domain.device.IDeviceFactory;
import smartHome.domain.repository.IDeviceRepository;
import smartHome.domain.room.Room;
import smartHome.valueObject.*;

import java.util.List;
import java.util.Optional;

public class DeviceService {
    private final IDeviceRepository _deviceRepository;
    private final IDeviceFactory _deviceFactory;
    private final IRepository<RoomID, Room> _roomRepository;

    /**
     * Constructor for DeviceService.
     *
     * @param deviceRepository is the repository for the device.
     * @param deviceFactory    is the factory for the device.
     * @param roomRepository   is the repository for the room.
     */
    public DeviceService(IDeviceRepository deviceRepository, IDeviceFactory deviceFactory, IRepository<RoomID, Room> roomRepository) {
        _deviceRepository = deviceRepository;
        _deviceFactory = deviceFactory;
        _roomRepository = roomRepository;
    }

    /**
     * Adds a new device to the room with the provided room ID.
     *
     * @param roomID       is the room ID where the device is located.
     * @param deviceName   is the name of the device.
     * @param deviceStatus is the state of the device.
     * @return the newly created device.
     */
    public Device addDevice(RoomID roomID, DeviceName deviceName, DeviceStatus deviceStatus, DeviceTypeID deviceTypeID) {
        Optional<Room> roomOptional = _roomRepository.ofIdentity(roomID);
        if (roomOptional.isEmpty()) {
            throw new IllegalArgumentException("Room with ID " + roomID + " not found.");
        }

        Device device = _deviceFactory.createDevice(roomID, deviceName, deviceStatus, deviceTypeID);
        _deviceRepository.save(device);
        return device;
    }

    /**
     * Deactivates the device with the provided device ID.
     *
     * @param deviceID is the unique identifier of the device.
     * @return the updated device.
     */
    public Device deactivateDeviceByID(DeviceID deviceID) {
        Optional<Device> deviceOptional = getDeviceByID(deviceID);
        if (deviceOptional.isPresent()) {
            Device device = deviceOptional.get();
            device.deactivateDevice();
            //_deviceRepository.save(device);
            return device;
        } else {
            throw new IllegalArgumentException("Device with ID " + deviceID + " not found.");
        }
    }

    /**
     * Returns all the devices in the repository.
     *
     * @return a list of devices.
     */
    public List<Device> getAllDevices() {
        return _deviceRepository.findAll();
    }

    /**
     * Returns the device with the provided device ID.
     *
     * @param deviceId is the unique identifier of the device.
     * @return an optional containing the device if found, empty otherwise.
     */
    public Optional<Device> getDeviceByID(DeviceID deviceId) {
        return _deviceRepository.ofIdentity(deviceId);
    }

    /**
     * Returns the devices in the room with the provided room ID.
     *
     * @param roomId is the unique identifier of the room.
     * @return a list of devices in the room.
     */
    public List<Device> getDevicesByRoomId(RoomID roomId) {
        return _deviceRepository.getDevicesByRoomId(roomId);
    }

}