package SmartHomeDDD.ValueObject;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class DimensionTest {

    /**
     * Test to check if the object is created with valid arguments.
     */
    @Test
    public void shouldGetValidObject_whenUsingValidArguments() {
        //Arrange
        int width = 13;
        int height = 15;
        int depth = 17;

        //Act
        new Dimension(width, height, depth);
    }

    /**
     * Test to check if the object is created with invalid width
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenUsingInvalidWidth() {
        //Arrange
        int width = -13;
        int height = 15;
        int depth = 17;

        String exepectedMessage = "'Width' must be positive.";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Dimension(width, height, depth);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(exepectedMessage));
    }

    /**
     * Test to check if the object is created with invalid height
     */
    @Test
    public void shouldThrowIllegalArgumentException_WhenUsingInvalidHeight() {
        //Arrange
        int width = 13;
        int height = -15;
        int depth = 17;

        String exepectedMessage = "'Height' must be positive.";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Dimension(width, height, depth);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(exepectedMessage));
    }

    /**
     * Test to check if the object is created with invalid depth
     */
    @Test
    public void shouldThrowIllegalArgumentException_WhenUsingInvalidDepth() {
        //Arrange
        int width = 13;
        int height = 15;
        int depth = -17;

        String exepectedMessage = "'Depth' must be positive";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Dimension(width, height, depth);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(exepectedMessage));
    }

    /**
     * Test to check if the object is equal to itself.
     */
    @Test
    public void shouldReturnTrue_whenComparingTheSameDimensionObject() {
        //Arrange
        int width = 13;
        int height = 15;
        int depth = 17;

        Dimension dimension = new Dimension(width, height, depth);

        //Act
        boolean result = dimension.equals(dimension);

        //Assert
        assertTrue(result);
    }

    /**
     * Test to check if the object is equal to another object with the same values.
     */
    @Test
    public void shouldReturnTrue_whenComparingTwoEqualDimensions() {
        //Arrange
        int width = 13;
        int height = 15;
        int depth = 17;

        Dimension dimension = new Dimension(width, height, depth);
        Dimension dimension2 = new Dimension(width, height, depth);

        //Act
        boolean result = dimension.equals(dimension2);

        //Assert
        assertTrue(result);
    }

    /**
     * Test to check if the object is different from another object with different values.
     */
    @Test
    public void shouldReturnFalse_whenComparingTwoDifferentDimensions() {
        //Arrange
        int width = 13;
        int height = 15;
        int depth = 17;

        int falseWidth = 10;
        int falseHeight = 15;
        int falseDepth = 17;

        Dimension dimension = new Dimension(width, height, depth);
        Dimension dimension2 = new Dimension(falseWidth, falseHeight, falseDepth);

        //Act
        boolean result = dimension.equals(dimension2);

        //Assert
        assertTrue(!result);
    }

    /**
     * Test to set dimension to string.
     */
    @Test
    public void shouldReturnDimensionToString() {
        //Arrange
        int width = 13;
        int height = 15;
        int depth = 17;

        Dimension dimension = new Dimension(width, height, depth);

        String expected = "Width: 13, Height: 15, Depth: 17";

        //Act
        String result = dimension.toString();

        //Assert
        assertTrue(result.equals(expected));
    }


}