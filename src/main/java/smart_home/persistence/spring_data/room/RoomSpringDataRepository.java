package smart_home.persistence.spring_data.room;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import smart_home.domain.repository.IRoomRepository;
import smart_home.domain.room.Room;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.RoomDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.RoomID;

import java.util.List;
import java.util.Optional;

public class RoomSpringDataRepository implements IRoomRepository {
    IRoomSpringDataRepository _repository;
    EntityManagerFactory _factory;
    IDataModelAssembler <RoomDataModel, Room> _assembler;

    /**
     * Constructor of the RoomSpringDataRepository.
     *
     * @param repository is the room spring repository.
     * @param assembler is the room data model assembler.
     */
    public RoomSpringDataRepository (IRoomSpringDataRepository repository, IDataModelAssembler assembler) {
        this._factory = Persistence.createEntityManagerFactory("smart_home");

        Validator.validateNotNull(repository, "Room repository");
        this._repository = repository;
        Validator.validateNotNull(assembler, "Room data model assembler");
        this._assembler = assembler;
    }

    /**
     * Method to get the Entity Manager.
     */
    private EntityManager getEntityManager() {
        try  {
            EntityManager manager = _factory.createEntityManager();
            return manager;
        } catch (Exception e) {
            throw new RuntimeException("Error creating the entity manager", e);
        }
    }

    /**
     * Method to save a domain entity.
     *
     * @param entity is the domain entity to be saved.
     * @return the saved domain entity.
     */
    @Override
    public Room save(Room entity) {
        Validator.validateNotNull(entity, "Room");

        RoomDataModel dataModel = new RoomDataModel(entity);

        _repository.save(dataModel);
        return entity;
    }

    /**
     * Method to find all domain entities.
     *
     * @return a list with all domain entities.
     */
    @Override
    public List<Room> findAll() {
        List<RoomDataModel> listRoomDataModelSaved = _repository.findAll();
        List<Room> listDomain = _assembler.toDomain(listRoomDataModelSaved);
        return listDomain;
    }

    /**
     * Method to find a domain entity by its unique identifier.
     *
     * @param objectID is the unique identifier of the domain entity.
     * @return the domain entity.
     */
    @Override
    public Optional<Room> ofIdentity(RoomID objectID) {
        Optional<RoomDataModel> dataModelSaved = _repository.findById(objectID.getID());

        if(dataModelSaved.isPresent()){
            Room domain = _assembler.toDomain(dataModelSaved.get());
            return Optional.of(domain);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Method to check if a domain entity exists by its unique identifier.
     *
     * @param objectID is the unique identifier of the domain entity.
     * @return true if the domain entity exists, false otherwise.
     */
    @Override
    public boolean containsOfIdentity(RoomID objectID) {
        return _repository.existsById(objectID.getID());
    }

    /**
     * Method to update a domain entity.
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
               _repository.save(roomDataModel);

               return room;
           } else {
               return null;
           }
        } else {
            return null;
        }
    }
}