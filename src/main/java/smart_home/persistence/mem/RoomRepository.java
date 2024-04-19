package smart_home.persistence.mem;

import smart_home.ddd.IRepository;
import smart_home.domain.repository.IRoomRepository;
import smart_home.domain.room.Room;
import smart_home.utils.Validator;
import smart_home.value_object.RoomID;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RoomRepository implements IRoomRepository {
    private final Map<RoomID, Room> DATA = new LinkedHashMap<RoomID, Room>();

    /**
     * Saves an object of type Room in the repository
     *
     * @param entity Room
     * @return Room
     */
    public Room save(Room entity) {
        Validator.validateNotNull(entity, "Room");

        if (containsOfIdentity(entity.getID()))
            throw new IllegalArgumentException("Room already exists");
        else
            DATA.put(entity.getID(), entity);
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

    /**
     * Updates the room in the repository
     *
     * @param room Room
     * @return Room
     */
    @Override
    public Room update(Room room) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
