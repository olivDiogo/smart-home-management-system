package smart_home.controller;

import org.junit.jupiter.api.Test;
import smart_home.assembler.RoomAssembler;
import smart_home.domain.house.House;
import smart_home.domain.house.HouseFactoryImpl;
import smart_home.domain.room.Room;
import smart_home.domain.room.RoomFactoryImpl;
import smart_home.dto.RoomDTO;
import smart_home.persistence.mem.HouseRepository;
import smart_home.persistence.mem.RoomRepository;
import smart_home.service.HouseService;
import smart_home.service.RoomService;
import smart_home.value_object.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the AddRoomToHouseController class.
 */
class AddRoomToHouseControllerTest {

    /**
     * Tests the instantiation of AddRoomToHouseController with valid parameters.
     */
    @Test
    void shouldInstantiateAddRoomToHouseController_WhenParametersAreValid() {
        // Arrange
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);

        //Act
        AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(roomService, roomAssembler);

        // Assert
        assertNotNull(addRoomToHouseController);

    }

    /**
     * Tests throwing an exception when house is not found.
     */
    @Test
    void shouldThrowException_WhenHouseIsNotFound() {
        // Arrange
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(roomService, roomAssembler);

        // Act
        assertThrows(IllegalArgumentException.class, () -> {addRoomToHouseController.addRoom("1", "Living Room", 1, 10, 10, 10);});

    }

    /**
     * Tests returning a RoomDTO when a room is successfully added.
     */
    @Test
    void shouldReturnRoomDTO_WhenRoomIsAdded() {
        // Arrange
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();

        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
        HouseService houseService = new HouseService(houseFactory, houseRepository);


        String street = "Rua Isep";
        String doorNumber = "122A";
        String postalCode = "4000-009";
        String countryCode = "PT";

        Address newAddress = new Address(street, doorNumber, postalCode, countryCode, new PostalCodeFactory());


        double latitude = 41.178;
        double longitude = -8.608;
        GPS newGPS = new GPS(latitude, longitude);

        House house = houseService.addHouse(newAddress, newGPS);

        HouseID houseID = house.getID();

        String houseIDS = String.valueOf(houseID);
        String name = "Living Room";
        int floor = 1;
        int width = 10;
        int length = 10;
        int height = 10;

        HouseID houseID1 = new HouseID(houseIDS);
        RoomName roomName = new RoomName(name);
        RoomFloor roomFloor = new RoomFloor(floor);
        Dimension dimension = new Dimension(width, length, height);

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(roomService, roomAssembler);

        Room room = roomService.addRoom(houseID1, roomName, dimension, roomFloor);

        RoomDTO expectedRoomDTO = roomAssembler.domainToDTO(room);

        // Act
        RoomDTO roomDTO = addRoomToHouseController.addRoom(String.valueOf(houseID), name, floor, width, length, height);

        // Assert
        assertEquals(expectedRoomDTO.roomName, roomDTO.roomName);
    }

    /**
     * Tests throwing an exception when room name is not provided.
     */
    @Test
    void shouldThrowException_WhenRoomNameIsNotProvided() {
        // Arrange
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(roomService, roomAssembler);

        // Act
        assertThrows(IllegalArgumentException.class, () -> {addRoomToHouseController.addRoom("1", null, 1, 10, 10, 10);});

    }

    /**
     * Tests throwing an exception when room floor is not valid.
     */
    @Test
    void shouldThrowException_WhenRoomFloorIsNotValid() {
        // Arrange
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(roomService, roomAssembler);

        // Act
        assertThrows(IllegalArgumentException.class, () -> {addRoomToHouseController.addRoom("1", "Living Room", -1000, 10, 10, 10);});
    }

    /**
     * Tests throwing an exception when room width is not valid.
     */
    @Test
    void shouldThrowException_WhenRoomWidthIsNotValid() {
        // Arrange
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(roomService, roomAssembler);

        // Act
        assertThrows(IllegalArgumentException.class, () -> {addRoomToHouseController.addRoom("1", "Living Room", 1, -1000, 10, 10);});
    }

    /**
     * Tests throwing an exception when room length is not valid.
     */
    @Test
    void shouldThrowException_WhenRoomLengthIsNotValid() {
        // Arrange
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(roomService, roomAssembler);

        // Act
        assertThrows(IllegalArgumentException.class, () -> {addRoomToHouseController.addRoom("1", "Living Room", 1, 10, -1000, 10);});
    }

    /**
     * Tests throwing an exception when room height is not valid.
     */
    @Test
    void shouldThrowException_WhenRoomHeightIsNotValid() {
        // Arrange
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        AddRoomToHouseController addRoomToHouseController = new AddRoomToHouseController(roomService, roomAssembler);

        // Act
        assertThrows(IllegalArgumentException.class, () -> {addRoomToHouseController.addRoom("1", "Living Room", 1, 10, 10, -1000);});
    }

}