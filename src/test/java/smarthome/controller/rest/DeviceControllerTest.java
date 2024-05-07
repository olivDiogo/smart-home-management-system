package smarthome.controller.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.domain.device.Device;
import smarthome.domain.device.DeviceFactoryImpl;
import smarthome.domain.device.IDeviceFactory;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.device_type.DeviceTypeFactoryImpl;
import smarthome.domain.device_type.IDeviceTypeFactory;
import smarthome.domain.house.House;
import smarthome.domain.house.HouseFactoryImpl;
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.repository.IDeviceTypeRepository;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.repository.IRoomRepository;
import smarthome.domain.room.IRoomFactory;
import smarthome.domain.room.Room;
import smarthome.domain.room.RoomFactoryImpl;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceStatus;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.persistence.mem.DeviceTypeRepository;
import smarthome.persistence.mem.HouseRepository;
import smarthome.persistence.mem.RoomRepository;
import smarthome.utils.dto.DeviceDataDTO;
import smarthome.utils.dto.RoomDataDTO;

@SpringBootTest
@AutoConfigureMockMvc
class DeviceControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private IHouseFactory houseFactory;

  @Autowired
  private IRoomFactory roomFactory;

  @Autowired
  private IDeviceFactory deviceFactory;

  @Autowired
  private IDeviceTypeFactory deviceTypeFactory;

  @MockBean
  private IDeviceRepository deviceRepository;

  @MockBean
  private IHouseRepository houseRepository;

  @MockBean
  private IRoomRepository roomRepository;

  @MockBean
  private IDeviceTypeRepository deviceTypeRepository;


  House setupHouse() {
    // Arrange
    String street = "Rua de Sao Bento";
    String doorNumber = "123";
    String postalCode = "1200-109";
    String countryCode = "PT";
    double latitude = 38.7143;
    double longitude = -9.1459;
    Address address = new Address(street, doorNumber, postalCode, countryCode,
        new PostalCodeFactory());
    GPS gps = new GPS(latitude, longitude);
    return houseFactory.createHouse(address, gps);
  }

  Room setupRoom(RoomDataDTO roomDataDTO) {
    HouseID houseID = new HouseID(roomDataDTO.houseID);
    RoomName name = new RoomName(roomDataDTO.name);
    RoomFloor floor = new RoomFloor(roomDataDTO.floor);
    Dimension dimension = new Dimension(roomDataDTO.width, roomDataDTO.length, roomDataDTO.height);
    return roomFactory.createRoom(houseID, name, dimension, floor);
  }

  DeviceType setupDeviceType() {
    TypeDescription typeDescription = new TypeDescription("Bulb");
    return deviceTypeFactory.createDeviceType(typeDescription);
  }

  DeviceDataDTO setupDeviceDataDTO(Room room, DeviceType deviceType) {
    String deviceTypeStr = deviceType.getID().toString();
    String deviceName = "Light";
    boolean deviceStatus = true;
    String roomIDStr = room.getID().toString();
    return new DeviceDataDTO(deviceTypeStr, deviceName, deviceStatus,roomIDStr);
  }

  Device setupDevice(DeviceDataDTO deviceDataDTO) {
    RoomID roomID = new RoomID(deviceDataDTO.roomID);
    DeviceName deviceName = new DeviceName(deviceDataDTO.deviceName);
    DeviceTypeID deviceTypeID = new DeviceTypeID(deviceDataDTO.deviceTypeID);
    DeviceStatus deviceStatus = new DeviceStatus(deviceDataDTO.deviceStatus);
    return deviceFactory.createDevice(roomID, deviceName, deviceStatus, deviceTypeID);
  }

  RoomDataDTO setupRoomDataDTO(House house) {
    String houseIDStr = house.getID().toString();
    String name = "Living Room";
    int floor = 1;
    int width = 10;
    int length = 10;
    int height = 3;
    return new RoomDataDTO(houseIDStr, name, floor, width, length, height);
  }


  /**
   * Verify that a Device is correctly added to the Room
   */
  @Test
  void shouldReturnDeviceDTO_whenDeviceIsAddedToRoom() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    Room room = setupRoom(roomDataDTO);
    DeviceType deviceType = setupDeviceType();
    DeviceDataDTO deviceDataDTO = setupDeviceDataDTO(room, deviceType);
    Device device = setupDevice(deviceDataDTO);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));
    when(deviceRepository.save(device)).thenReturn(device);

    // Act & Assert
    mockMvc.perform(post("/device/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(deviceDataDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.deviceName").value("Light"));
  }

  /**
   * Verify that a Device is correctly retrieved by its ID
   */
  @Test
  void shouldReturnDeviceDTO_whenGetDeviceById() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    Room room = setupRoom(roomDataDTO);
    DeviceType deviceType = setupDeviceType();
    DeviceDataDTO deviceDataDTO = setupDeviceDataDTO(room, deviceType);
    Device device = setupDevice(deviceDataDTO);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(get("/device/" + device.getID()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.deviceName").value("Light"));
  }

  /**
   * Verify that a Device is not added to the Room when the Room does not exist
   */
  @Test
  void shouldReturnBadRequest_whenRoomDoesNotExist() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    DeviceType deviceType = setupDeviceType();
    DeviceDataDTO deviceDataDTO = setupDeviceDataDTO(setupRoom(roomDataDTO), deviceType);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(roomRepository.ofIdentity(new RoomID(deviceDataDTO.roomID))).thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(post("/device/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(deviceDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Verify that a Device is not added to the Room when the DeviceType does not exist
   */
  @Test
  void shouldReturnBadRequest_whenDeviceTypeDoesNotExist() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    DeviceType deviceType = setupDeviceType();
    DeviceDataDTO deviceDataDTO = setupDeviceDataDTO(setupRoom(roomDataDTO), deviceType);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(new DeviceTypeID(deviceDataDTO.deviceTypeID)))
        .thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(post("/device/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(deviceDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Verify that a Device is not added to the Room when the House does not exist
   */
  @Test
  void shouldReturnBadRequest_whenHouseDoesNotExist() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    DeviceType deviceType = setupDeviceType();
    DeviceDataDTO deviceDataDTO = setupDeviceDataDTO(setupRoom(roomDataDTO), deviceType);

    when(houseRepository.ofIdentity(new HouseID(roomDataDTO.houseID))).thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(post("/device/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(deviceDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Test getDeviceID method when the device does not exist
   */
  @Test
  void shouldReturnNotFound_whenDeviceDoesNotExist() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    DeviceType deviceType = setupDeviceType();
    DeviceDataDTO deviceDataDTO = setupDeviceDataDTO(setupRoom(roomDataDTO), deviceType);
    Device device = setupDevice(deviceDataDTO);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(roomRepository.ofIdentity(setupRoom(roomDataDTO).getID())).thenReturn(Optional.of(setupRoom(roomDataDTO)));
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(get("/device/" + device.getID()))
        .andExpect(status().isNotFound());
  }

  /**
   * Test getDeviceID when deviceType does not exist
   */
  @Test
  void shouldReturnNotFound_whenDeviceTypeDoesNotExist() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    DeviceType deviceType = setupDeviceType();
    DeviceDataDTO deviceDataDTO = setupDeviceDataDTO(setupRoom(roomDataDTO), deviceType);
    Device device = setupDevice(deviceDataDTO);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(get("/device/" + device.getID()))
        .andExpect(status().isNotFound());
  }


}




