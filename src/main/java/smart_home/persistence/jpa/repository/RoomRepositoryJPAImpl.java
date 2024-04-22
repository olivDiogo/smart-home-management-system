package smart_home.persistence.jpa.repository;

import jakarta.persistence.*;
import smart_home.domain.repository.IRoomRepository;
import smart_home.domain.room.Room;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.RoomDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.RoomID;

import java.util.List;
import java.util.Optional;

public class RoomRepositoryJPAImpl implements IRoomRepository {
    private EntityManagerFactory _factory;
    private IDataModelAssembler<RoomDataModel, Room> _dataModelConverter;

    /**
     * RepositoryRoomJPAImpl constructor
     * @param dataModelConverter
     */
    public RoomRepositoryJPAImpl(IDataModelAssembler<RoomDataModel, Room> dataModelConverter) {
        Validator.validateNotNull(dataModelConverter, "Data model assembler");

        _dataModelConverter = dataModelConverter;
        _factory = Persistence.createEntityManagerFactory("smart_home");
    }

    /**
     * Method to get entity manager
     * @return EntityManager
     */
    private EntityManager getEntityManager() {
        EntityManager manager = _factory.createEntityManager();
        return manager;
    }

    /**
     * Method to save room
     * @param room is the domain entity to be saved.
     * @return Room
     */
    @Override
    public Room save(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }

        RoomDataModel roomDataModel = new RoomDataModel(room);

        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(roomDataModel);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }

        return room;
    }

    /**
     * Method to find all rooms
     *
     * @return List<Room>
     */
    @Override
    public List<Room> findAll() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery(
                    "SELECT e FROM RoomDataModel e");

            List<RoomDataModel> listDataModel = query.getResultList();

            List<Room> listDomain = _dataModelConverter.toDomain(listDataModel);

            return listDomain;
        } finally {
            em.close();
        }
    }

    /**
     * Method to get room by identity
     *
     * @param objectID RoomID
     * @return Optional<Room>
     */
    @Override
    public Optional<Room> ofIdentity(RoomID objectID) {
        EntityManager em = getEntityManager();

        try{
            RoomDataModel roomDataModel = em.find(RoomDataModel.class, objectID);

            if (roomDataModel == null)
                return Optional.empty();

            Room room = _dataModelConverter.toDomain(roomDataModel);

            return Optional.of(room);
        } finally {
            em.close();
        }
    }

    /**
     * Method to check if room contains identity
     * @param objectID is the unique identifier of the domain entity.
     * @return boolean
     */
    @Override
    public boolean containsOfIdentity(RoomID objectID) {
        Optional<Room> roomDataModel = ofIdentity(objectID);

        return roomDataModel.isPresent();
    }

    /**
     * Method to update room
     *
     * @param room is the room to be updated.
     * @return the updated room.
     */
    @Override
    public Room update(Room room) {
        RoomDataModel roomDataModel = getEntityManager().find(RoomDataModel.class, room.getID().getID());

        if(roomDataModel != null) {
            boolean isUpdated = roomDataModel.updateFromDomain(room);

            if(isUpdated) {
                EntityManager em = getEntityManager();
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                em.merge(roomDataModel);
                tx.commit();
                em.close();

                return room;
            } else {
                return null;
            }
        } else {
            return null;
        }

    }
}