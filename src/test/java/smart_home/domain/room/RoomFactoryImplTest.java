package smart_home.domain.room;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import smart_home.value_object.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Tests for the {@link RoomFactoryImpl} class, ensuring that rooms are created correctly under various conditions
 * and that appropriate exceptions are thrown when invalid parameters are provided.
 */
class RoomFactoryImplTest {

    /**
     * Test to ensure that a Room can be created successfully when {@link RoomFactoryImpl#createRoom(HouseID, RoomName, Dimension, RoomFloor)}
     * is called with valid parameters. This test verifies that no exceptions are thrown during the creation process.
     */
    @Test
    void shouldCreateRoom_WhenCreateRoomIsCalledWithValidParameters(){
        // Arrange
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = mock(RoomFloor.class);
        String roomID = "1";

        try (MockedConstruction<RoomID> mockedConstruction = mockConstruction(RoomID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(roomID);
        })) {
            RoomFactoryImpl factory = new RoomFactoryImpl();

            // Act
            Room room = factory.createRoom(houseID, roomName, dimension, roomFloor);

            // Assert
            assertEquals(roomID, room.getID().toString());
        }
    }

    /**
     * Test to create a Room with second constructor
     */
    @Test
    void shouldCreateRoom_WhenCreateRoomIsCalledWithValidParametersAndRoomID(){
        // Arrange
        HouseID houseID = mock(HouseID.class);
        RoomName roomName = mock(RoomName.class);
        Dimension dimension = mock(Dimension.class);
        RoomFloor roomFloor = mock(RoomFloor.class);
        RoomID roomID = mock(RoomID.class);

        RoomFactoryImpl factory = new RoomFactoryImpl();

        // Act
        Room room = factory.createRoom(houseID, roomName, dimension, roomFloor, roomID);

        // Assert
        assertEquals(roomID, room.getID());
    }
}