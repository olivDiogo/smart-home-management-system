package SmartHome.controller;

import SmartHome.domain.*;
import SmartHome.dto.DeviceDTO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

class DeactivateDeviceControllerTest {

    @Test
    void constructorWithValidArgumentsWorks() {
        //Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        //Act
        new DeactivateDeviceController(house);
    }

    @Test
    void constructorThrowsException() {
        //Arrange
        House house = null;
        String expectedMessage = "Invalid arguments";
        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new DeactivateDeviceController(house));
        assertEquals(expectedMessage, exception.getMessage());
        }

    @Test
    void successfullyReturnsTheDeviceList() {
        //Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        String roomName = "Sala";
        int floor = 2;
        int width = 2;
        int length = 2;
        int height = 2;
        String deviceName = "Luz";
        Room room = house.addRoom(roomName, floor, width, length, height);
        room.addDevice(deviceName,new DeviceFactory() );
        DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(house);
        //Act
        List<DeviceDTO> deviceDTOList = deactivateDeviceController.getDeviceList();
        //Assert
        assertEquals(1, deviceDTOList.size());
    }

    @Test
    void getDeviceListReturnEmptyList() {
        //Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(house);
        //Act
        List<DeviceDTO> deviceList = deactivateDeviceController.getDeviceList();
        //Assert
        assertEquals(0, deviceList.size());
    }

    @Test
    void successfullyDeactivateDevice() {
        //Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        String roomName = "Sala";
        int floor = 2;
        int width = 2;
        int length = 2;
        int height = 2;
        String deviceName = "Luz";
        Room room = house.addRoom(roomName, floor, width, length, height);
        room.addDevice(deviceName, new DeviceFactory());
        DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(house);
        List<DeviceDTO> deviceDTOList = deactivateDeviceController.getDeviceList();
        Optional<DeviceDTO> optionalDeviceDTO = deviceDTOList.stream().filter(deviceDTO -> deviceDTO.toString().contains("Luz")).findFirst();
        DeviceDTO deviceDTO = optionalDeviceDTO.get();
        //Act
        Optional<DeviceDTO> result = deactivateDeviceController.deactivateDevice(deviceDTO);
        //Assert
        assertTrue(result.isPresent(), "Device was not deactivated");
        assertTrue(deviceDTO.toString().contains("status=false"), "Device was deactivated");
    }

    @Test
    void deactivateDeviceReturnEmptyOptional() {
        //Arrange
        String name = "Luz";
        House house = new House(new LocationFactory(), new RoomFactory());
        DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(house);
        DeviceFactory deviceFactory = new DeviceFactory();
        Device device = deviceFactory.createDevice(name);
        DeviceDTO deviceDTO = new DeviceDTO(device);
        deactivateDeviceController.getDeviceList();

        //Act
        Optional<DeviceDTO> optionalDeviceDTO1 = deactivateDeviceController.deactivateDevice(deviceDTO);

        //Assert
        assertTrue(optionalDeviceDTO1.isEmpty());
    }
}