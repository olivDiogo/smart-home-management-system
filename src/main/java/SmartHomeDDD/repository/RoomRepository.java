package SmartHomeDDD.repository;

import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.ValueObject.RoomID;
import SmartHomeDDD.domain.Room.Room;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.List;

public class RoomRepository implements Repository<RoomID, Room>{
    private final Map<RoomID, Room> DATA = new LinkedHashMap<RoomID, Room>();

    /**
     * Saves an object of type Room in the repository
     *
     * @param entity Room
     * @return Room
     */
    public Room save(Room entity) {
        if (entity == null)
            throw new IllegalArgumentException("Room cannot be null");
        else if (containsOfIdentity(entity.getID()))
            throw new IllegalArgumentException("Room already exists");
        else
            DATA.put( entity.getID(), entity );
        return entity;
    }

    /**
     * Returns all the rooms in the repository
     *
     * @return List<Room>
     */
    public List<Room> findAll() {
        return DATA.values().stream().toList();
    }

    /**
     * Returns the room with the given id
     *
     * @param id RoomID
     * @return Optional<Room>
     */
    public Optional<Room> ofIdentity(RoomID id) {
        return Optional.ofNullable(DATA.get(id));
    }

    /**
     * Checks if the repository contains a room with the given id
     *
     * @param id RoomID
     * @return boolean
     */
    public boolean containsOfIdentity(RoomID id) {
        return DATA.containsKey(id);
    }
}