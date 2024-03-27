package SmartHomeDDD.domain.Room;

import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests for the {@link ImpRoomFactory} class, ensuring that rooms are created correctly under various conditions
 * and that appropriate exceptions are thrown when invalid parameters are provided.
 */
class ImpRoomFactoryTest {

    /**
     * Test to ensure that a Room can be created successfully when {@link ImpRoomFactory#createRoom(HouseID, RoomName, Dimension, RoomFloor)}
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
            ImpRoomFactory factory = new ImpRoomFactory();

            // Act
            Room room = factory.createRoom(houseID, roomName, dimension, roomFloor);

            // Assert
            assertEquals(roomID, room.getID().toString());
        }
    }
}
