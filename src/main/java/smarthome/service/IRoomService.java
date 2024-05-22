package smarthome.service;

import java.util.List;
import java.util.Optional;
import smarthome.ddd.IService;
import smarthome.domain.room.Room;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;

public interface IRoomService extends IService {

  /**
   * Adds a new room to the house with the provided house ID.
   *
   * @param roomName
   * @param dimension
   * @param roomFloor
   * @return the room that was added.
   */
  Room addRoom(RoomName roomName, Dimension dimension, RoomFloor roomFloor);

  /**
   * Returns all the rooms in the repository.
   *
   * @return a list of all rooms.
   */
  List<Room> getAllRooms();

  /**
   * Returns the room with the provided room ID.
   *
   * @param roomID
   * @return the room with the provided room ID.
   */
  Optional<Room> getRoomById(RoomID roomID);
}