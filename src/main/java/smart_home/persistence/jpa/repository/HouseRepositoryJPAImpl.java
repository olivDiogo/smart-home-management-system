package smart_home.persistence.jpa.repository;

import jakarta.persistence.*;
import smart_home.domain.house.House;
import smart_home.domain.repository.IHouseRepository;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.HouseDataModel;
import smart_home.value_object.HouseID;

import java.util.List;
import java.util.Optional;

public class HouseRepositoryJPAImpl implements IHouseRepository {
        private IDataModelAssembler<HouseDataModel, House> dataModelAssembler;
        private EntityManagerFactory factory;

        /**
         * HouseRepositoryJPAImpl constructor
         * @param dataModelAssembler the converter to transform data models to domain models and vice versa
         */

        public HouseRepositoryJPAImpl(IDataModelAssembler<HouseDataModel, House> dataModelAssembler) {
            validateDataModelAssembler(dataModelAssembler);
            this.dataModelAssembler = dataModelAssembler;
            factory = Persistence.createEntityManagerFactory("smart_home");
        }

        /**
         * Validates the data model converter.
         * @param dataModelAssembler the data model converter to validate
         * @throws IllegalArgumentException if the data model converter is null
         */
        private void validateDataModelAssembler(IDataModelAssembler<HouseDataModel, House> dataModelAssembler) {
            if (dataModelAssembler == null) {
                throw new IllegalArgumentException("Data model assembler cannot be null.");
            }
        }

        /**
         * Method to get entity manager
         * @return EntityManager
         */
        private EntityManager getEntityManager() {
            EntityManager manager = factory.createEntityManager();
            return manager;
        }

        /**
         * Method to save house
         * @param house the domain entity to be saved
         * @return House
         */
        @Override
        public House save(House house) {
            if (house == null) {
                throw new IllegalArgumentException();
            }
            HouseDataModel houseDataModel = new HouseDataModel(house);
            EntityManager em = getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(houseDataModel);
            tx.commit();
            em.close();
            return house;
        }

        /**
         * Method to find all houses
         * @return List<House>
         */
        @Override
        public List<House> findAll() {
            Query query = getEntityManager().createQuery(
                    "SELECT e FROM HouseDataModel e");
            List<HouseDataModel> listDataModel = query.getResultList();
            List<House> listDomain = dataModelAssembler.toDomain(listDataModel);
            return listDomain;
        }

        /**
         * Method to find house by identity
         * @param objectID the identity of the house
         * @return Optional<House>
         */
        @Override
        public Optional<House> ofIdentity(HouseID objectID) {
            HouseDataModel houseDataModel = getEntityManager().find(HouseDataModel.class, objectID);
            if (houseDataModel == null) {
                return Optional.empty();
            }
            else {
                House house = dataModelAssembler.toDomain(houseDataModel);
                return Optional.of(house);
            }
        }

        /**
         * Method to check if house contains identity
         * @param objectID the identity of the house
         * @return boolean
         */
        @Override
        public boolean containsOfIdentity(HouseID objectID) {
            Optional<House> houseDataModel = ofIdentity(objectID);
            return houseDataModel.isPresent();
        }
    }

