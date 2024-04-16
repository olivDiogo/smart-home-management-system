package smartHome.controller;

import smartHome.assembler.DeviceAssembler;
import smartHome.domain.device.Device;
import smartHome.dto.DeviceDTO;
import smartHome.service.DeviceService;
import smartHome.valueObject.DeviceID;

import java.util.Collections;
import java.util.List;

public class DeactivateDeviceController {

    private final DeviceService _deviceService;
    private final DeviceAssembler _deviceAssembler;

    /**
     * Constructor for US08DeactivateDevice.
     *
     * @param deviceService   is the service for the device.
     * @param deviceAssembler is the assembler for the device.
     */
    public DeactivateDeviceController(DeviceService deviceService, DeviceAssembler deviceAssembler) {
        validateDeviceService(deviceService);
        _deviceService = deviceService;
        validateDeviceAssembler(deviceAssembler);
        _deviceAssembler = deviceAssembler;
    }

    /**
     * Validates that the DeviceService is not null.
     *
     * @param deviceService the DeviceService to validate
     */
    private void validateDeviceService(DeviceService deviceService) {
        if (deviceService == null) {
            throw new IllegalArgumentException("DeviceService cannot be null.");
        }
    }

    /**
     * Validates that the DeviceAssembler is not null.
     *
     * @param deviceAssembler the DeviceAssembler to validate
     */
    private void validateDeviceAssembler(DeviceAssembler deviceAssembler) {
        if (deviceAssembler == null) {
            throw new IllegalArgumentException("DeviceAssembler cannot be null.");
        }
    }

    /**
     * Requests all devices.
     *
     * @return a list of devices.
     */
    public List<DeviceDTO> requestAllDevices() {
        List<Device> deviceList = _deviceService.getAllDevices();
        if (deviceList.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if there are no devices.
        }
        return List.copyOf(_deviceAssembler.domainToDTO(deviceList));
    }


    public DeviceDTO requestDeactivateDevice(DeviceDTO deviceDTO) {
        try {
            DeviceID deviceID = new DeviceID(deviceDTO.deviceID);
            Device device = _deviceService.deactivateDeviceByID(deviceID);
            return _deviceAssembler.domainToDTO(device);
        } catch (IllegalArgumentException e) {
            return new DeviceDTO("Device not found.", "", "", "");
        }
    }
}
