package smart_home.domain.room;

import smart_home.value_object.*;

/**
 * Interface defining a factory for creating {@link Room} instances.
 * Provides a method to create a room with specific house ID, room name, dimension, and room floor.
 */

public interface IRoomFactory {
    /**
     * Creates and returns a new {@link Room} instance with the provided house ID, room name, dimension, and room floor.
     *
     * @param houseID   the house ID where the room is located
     * @param roomName  the name of the room
     * @param dimension the dimension of the room
     * @param roomFloor the floor where the room is located
     * @return a newly created Room instance
     */
    Room createRoom(HouseID houseID, RoomName roomName, Dimension dimension, RoomFloor roomFloor);

    /**
     * Creates and returns a new {@link Room} instance with the provided house ID, room name, dimension, room floor, and room ID.
     * @param houseID
     * @param roomName
     * @param dimension
     * @param roomFloor
     * @param roomID
     * @return
     */
    Room createRoom(HouseID houseID, RoomName roomName, Dimension dimension, RoomFloor roomFloor, RoomID roomID);

}