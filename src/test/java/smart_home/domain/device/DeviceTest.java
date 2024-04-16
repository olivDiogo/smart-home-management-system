package smart_home.domain.device;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import smart_home.value_object.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeviceTest {

    /**
     * Instantiates a new Device object with valid parameters.
     */
    @Test
    void shouldInstantiateANewDevice() {

        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
            //Act
            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

            //Assert
            assertNotNull(device);
        }
    }

    /**
     * Throws an IllegalArgumentException when the constructor is called with a null RoomID.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullRoomID() {

        //Arrange
        RoomID roomID = null;
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
            String expectedMessage = "RoomID is required";

            //Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new Device(roomID, deviceName, deviceStatus, deviceTypeID);
            });

            String actualMessage = exception.getMessage();

            //Assert
            assertEquals(expectedMessage, actualMessage);
        }
    }

    /**
     * Throws an IllegalArgumentException when the constructor is called with a null DeviceName.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullDeviceName() {

        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = null;
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
            String expectedMessage = "DeviceName is required";

            //Act & Assert
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new Device(roomID, deviceName, deviceStatus, deviceTypeID);
            });

            String actualMessage = exception.getMessage();

            //Assert
            assertEquals(expectedMessage, actualMessage);
        }
    }


    /**
     * Throws an IllegalArgumentException when the constructor is called with a null DeviceStatus.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullDeviceStatus() {

        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = null;
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        String expectedMessage = "DeviceState is required";

        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Device(roomID, deviceName, deviceStatus, deviceTypeID);
        });

        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Throws an IllegalArgumentException when the constructor is called with a null DeviceTypeID.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullDeviceTypeID() {

        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = null;

        String expectedMessage = "DeviceTypeID is required";

        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Device(roomID, deviceName, deviceStatus, deviceTypeID);
        });

        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Should return the RoomID of the Device object.
     */
    @Test
    void shouldReturnRoomID() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);
            //Act
            RoomID result = device.getRoomID();

            //Assert
            assertEquals(result, roomID);
        }
    }

    /**
     * Should return the DeviceID of the Device object.
     */
    @Test
    void shouldReturnDeviceID() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        String expectedDeviceID = "123";

        try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class, (mock, context) -> {
            when(mock.getID()).thenReturn(expectedDeviceID);
        })) {
            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

            //Act
            DeviceID result = device.getID();

            //Assert
            assertEquals(expectedDeviceID, result.getID());
        }
    }

    /**
     * Should return the DeviceName of the Device object.
     */
    @Test
    void shouldReturnDeviceName() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);
            //Act
            DeviceName result = device.getDeviceName();
            //Assert
            assertEquals(result, deviceName);
        }
    }

    /**
     * Should return the DeviceStatus of the Device object.
     */
    @Test
    void shouldReturnDeviceStatus() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);
            //Act
            DeviceStatus result = device.getDeviceStatus();
            //Assert
            assertEquals(result, deviceStatus);
        }
    }

    /**
     * Should return true when equals method is called with the same object.
     */
    @Test
    void shouldReturnTrueWhenGivenSameObject() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);
            //Act
            boolean result = device.equals(device);
            //Assert
            assertTrue(result);

        }
    }


    /**
     * Should return false when comparing two objects with different ID.
     */
    @Test
    void shouldReturnFalseWhenComparingTwoObjectsWithDifferentID() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        RoomID roomID2 = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> mocked = mockConstruction(DeviceID.class)) {

            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);
            Device device2 = new Device(roomID2, deviceName, deviceStatus, deviceTypeID);

            //Act
            boolean result = device.equals(device2);

            //Assert
            assertFalse(result);
        }
    }

    @Test
    void shouldReturnFalseWhenComparingObjectWithNull() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> mocked = mockConstruction(DeviceID.class)) {

            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

            //Act
            boolean result = device.equals(null);

            //Assert
            assertFalse(result);
        }
    }

    /**
     * Should return device as a string when toString is called.
     */
    @Test
    void shouldReturnExpectedStringWhenToStringIsCalled() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> mocked = mockConstruction(DeviceID.class)) {
            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

            String expectedString = "Device:" + "roomID=" + roomID + ", deviceID=" + device.getID() + ", deviceName=" + deviceName + ", deviceStatus=" + deviceStatus;

            //Act
            String result = device.toString();

            //Assert
            assertEquals(expectedString, result);
        }
    }

    @Test
    void shouldReturnDeactivatedDeviceStatus_WhenDeactivateDeviceIsCalled() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> deviceIDMocked = mockConstruction(DeviceID.class)) {
            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);

            String expected = "OFF";

            when(deviceStatus.toString()).thenReturn(expected);

            //Act
            DeviceStatus result = device.deactivateDevice();

            //Assert
            assertEquals(expected, result.toString());


        }
    }

    /**
     * Should return the DeviceTypeID of the Device object.
     */
    @Test
    void shouldReturnDeviceTypeID() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> mocked = mockConstruction(DeviceID.class)) {
            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);
            //Act
            DeviceTypeID result = device.getDeviceTypeID();
            //Assert
            assertEquals(result, deviceTypeID);
        }
    }

    @Test
    void shouldReturnDeviceHashCode_whenHashCodeIsCalled() {
        //Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        try (MockedConstruction<DeviceID> mocked = mockConstruction(DeviceID.class)) {
            Device device = new Device(roomID, deviceName, deviceStatus, deviceTypeID);
            int expected = device.getID().hashCode();

            //Act
            int result = device.hashCode();

            //Assert
            assertEquals(expected,result);
        }
    }
}