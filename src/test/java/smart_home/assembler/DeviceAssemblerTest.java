package smart_home.assembler;

import org.junit.jupiter.api.Test;
import smart_home.domain.device.Device;
import smart_home.dto.DeviceDTO;
import smart_home.value_object.DeviceID;
import smart_home.value_object.DeviceName;
import smart_home.value_object.DeviceStatus;
import smart_home.value_object.RoomID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeviceAssemblerTest {

    /**
     * Test if the domainToDTO method returns a DeviceDTO object when the device is valid.
     */
    @Test
    void shouldReturnADeviceDTO_WhenGivenADevice() {
        //Arrange
        String deviceID = "1";
        String roomID = "1";
        String deviceName = "Test Device";
        String deviceStatus = "Off";

        Device device = mock(Device.class);

        when(device.getID()).thenReturn(mock(DeviceID.class));
        when(device.getID().toString()).thenReturn(deviceID);

        when(device.getRoomID()).thenReturn(mock(RoomID.class));
        when(device.getRoomID().toString()).thenReturn(roomID);

        when(device.getDeviceName()).thenReturn(mock(DeviceName.class));
        when(device.getDeviceName().toString()).thenReturn(deviceName);

        when(device.getDeviceStatus()).thenReturn(mock(DeviceStatus.class));
        when(device.getDeviceStatus().toString()).thenReturn(deviceStatus);

        DeviceAssembler deviceAssembler = new DeviceAssembler();

        DeviceDTO expectedDevice = new DeviceDTO(deviceID, roomID, deviceName, deviceStatus);
        String expected = deviceID + " " + roomID + " " + deviceName + " " + deviceStatus;

        //Act
        DeviceDTO deviceDTO = deviceAssembler.domainToDTO(device);

        //Assert
        assertEquals(expected, deviceDTO.toString());
    }

    /**
     * Test if the domainToDTO method throws an IllegalArgumentException when the device is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullDevice() {
        //Arrange
        Device device = null;
        DeviceAssembler deviceAssembler = new DeviceAssembler();

        String expectedMessage = "The Device cannot be null.";

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> deviceAssembler.domainToDTO(device));

        //Assert
        String result = exception.getMessage();
        assertEquals(expectedMessage, result);
    }

    /**
     * Test if the domainToDTO method returns a list of DeviceDTO objects when the list of devices is valid.
     */
    @Test
    void shouldReturnListOfDeviceDTO_WhenGivenListOfDevices() {
        //Arrange
        String deviceID = "1";
        String roomID = "1";
        String deviceName = "Test Device";
        String deviceStatus = "Off";

        Device device = mock(Device.class);
        when(device.getID()).thenReturn(mock(DeviceID.class));
        when(device.getID().toString()).thenReturn(deviceID);

        when(device.getRoomID()).thenReturn(mock(RoomID.class));
        when(device.getRoomID().toString()).thenReturn(roomID);

        when(device.getDeviceName()).thenReturn(mock(DeviceName.class));
        when(device.getDeviceName().toString()).thenReturn(deviceName);

        when(device.getDeviceStatus()).thenReturn(mock(DeviceStatus.class));
        when(device.getDeviceStatus().toString()).thenReturn(deviceStatus);

        List<Device> devices = Arrays.asList(device);

        DeviceAssembler deviceAssembler = new DeviceAssembler();

        DeviceDTO expectedDevice = new DeviceDTO(deviceID, roomID, deviceName, deviceStatus);
        List<DeviceDTO> expected = List.of(expectedDevice);

        //Act
        List<DeviceDTO> deviceDTOs = deviceAssembler.domainToDTO(devices);

        //Assert
        assertEquals(expected.toString(), deviceDTOs.toString());
    }

    /**
     * Test if the domainToDTO method throws an IllegalArgumentException when the list of devices is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullDeviceList() {
        //Arrange
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        List<Device> devices = null;

        String expectedMessage = "The list of Devices cannot be null or empty.";

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> deviceAssembler.domainToDTO(devices));

        //Assert
        String result = exception.getMessage();
        assertEquals(expectedMessage, result);
    }

    /**
     * Test if the domainToDTO method throws an IllegalArgumentException when the list of devices is empty.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenEmptyDeviceList() {
        //Arrange
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        List<Device> devices = new ArrayList<>();

        String expectedMessage = "The list of Devices cannot be null or empty.";

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> deviceAssembler.domainToDTO(devices));

        //Assert
        String result = exception.getMessage();
        assertEquals(expectedMessage, result);
    }

    /**
     * Test if the domainToDTO method throws an IllegalArgumentException when the list of devices contains null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenDeviceListWithNull() {
        //Arrange
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        Device device = mock(Device.class);
        List<Device> devices = new ArrayList<>();
        devices.add(device);
        devices.add(null);

        String expectedMessage = "The list of Devices cannot be null or empty.";

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> deviceAssembler.domainToDTO(devices));

        //Assert
        String result = exception.getMessage();
        assertEquals(expectedMessage, result);
    }
}