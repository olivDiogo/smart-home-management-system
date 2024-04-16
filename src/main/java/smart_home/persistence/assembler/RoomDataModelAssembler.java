package smart_home.persistence.assembler;

import smart_home.domain.room.IRoomFactory;
import smart_home.domain.room.Room;
import smart_home.persistence.jpa.data_model.RoomDataModel;
import smart_home.value_object.*;

import java.util.ArrayList;
import java.util.List;

public class RoomDataModelAssembler implements IDataModelAssembler<RoomDataModel, Room> {
    private IRoomFactory _roomFactory;

    /**
     * Class constructor
     *
     * @param roomFactory is the factory used to create Room instances.
     */
    public RoomDataModelAssembler(IRoomFactory roomFactory) {
        validateRoomFactory(roomFactory);
        _roomFactory = roomFactory;
    }

    /**
     * Validates Room Factory
     * @param roomFactory
     */
    private void validateRoomFactory(IRoomFactory roomFactory) {
        if (roomFactory == null) {
            throw new IllegalArgumentException("Room factory cannot be null");
        }
    }

    /**
     * Converts a RoomDataModel instance to a Room instance.
     *
     * @param roomDataModel is the domain entity to be converted.
     * @return a Room instance.
     */
    @Override
    public Room toDomain(RoomDataModel roomDataModel) {
        if(roomDataModel == null){
            throw new IllegalArgumentException("RoomDataModel cannot be null.");
        }

        RoomID roomID = new RoomID(roomDataModel.getRoomID());
        HouseID houseID = new HouseID(roomDataModel.getHouseID());
        RoomFloor roomFloor = new RoomFloor(roomDataModel.getFloor());
        RoomName roomName = new RoomName(roomDataModel.getRoomName());
        Dimension dimension = new Dimension(roomDataModel.getWidth(), roomDataModel.getDepth(), roomDataModel.getHeight());

        Room room = _roomFactory.createRoom(houseID, roomName, dimension, roomFloor, roomID);

        return room;
    }

    /**
     * Converts a list of RoomDataModel instances to a list of Room instances.
     *
     * @param roomDataModels is the list of domain entities to be converted.
     * @return a list of Room instances.
     */
    @Override
    public List<Room> toDomain(List<RoomDataModel> roomDataModels) {
        List <Room> rooms = new ArrayList<>();

        for (RoomDataModel roomDataModel : roomDataModels) {
            Room room = toDomain(roomDataModel);
            rooms.add(room);
        }

        return rooms;
    }
}