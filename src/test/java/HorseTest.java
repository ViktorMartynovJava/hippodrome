import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class HorseTest {


    @Test
    @DisplayName("IllegalArgumentException if the first parameter is null")
    public void nullNameException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null,1,1));
    }

    @Test
    @DisplayName("Message if the first parameter is null")
    public void nullMessageName() {
        try {
            new Horse(null,1,1);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t\t", "\n\n\n"})
    @DisplayName("IllegalArgumentException if the fist parameter is blank")
    public void whenBlankAndSpaceSymbols(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t\t", "\n\n\n"})
    @DisplayName("Message if the fist parameter is blank")
    public void whenBlankAndSpaceSymbolsMessage(String name) {
        String actual = "";
        try {
            new Horse(name,1,1);
        } catch (IllegalArgumentException e) {
            actual = e.getMessage();
        }
        assertEquals("Name cannot be blank.",actual);
    }
    @Test
    public void whenNegativeNumberGetMessage() {
        try {
            new Horse("Name", -2, 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void whenNegativeNumberParameterDistance() {
        assertThrows(IllegalArgumentException.class,() -> new Horse("Name",1,-2));
    }

    @Test
    void whenNegativeNumberParameterDistanceMessage() {
        try {
            new Horse("Name", 1 , -2);
        } catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.",e.getMessage());
        }
    }

    @Test
    void getName() {
        String name = "Text";
        double speed = 1;
        double distance = 3;

        Horse horse = new Horse(name,speed,distance);
        String actualName = horse.getName();
        assertEquals(name,actualName);
    }

    @Test
    void getSpeed() {
        String name = "Text";
        double speed = 1;
        double distance = 3;

        Horse horse = new Horse(name,speed,distance);
        double actualSpeed = horse.getSpeed();
        assertEquals(speed,actualSpeed);
    }

    @Test
    void getDistanceReturnParameterThree() {
        String name = "Text";
        double speed = 1;
        double distance = 3;

        Horse horse = new Horse(name,speed,distance);
        double actualDistance = horse.getDistance();
        assertEquals(distance,actualDistance);
    }
    @Test
    void getDistanceTwoParameter() {
        String name = "Text";
        double speed = 1;

        Horse horse = new Horse(name,speed);
        double actualDistance = horse.getDistance();
        assertEquals(0.0,actualDistance);
    }

    @Test
    void testMoveInvokesGetRandomDoubleWithProperParameters() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Name", 1, 2);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9)); }

    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.8})
    void testMoveUpdatesDistanceCorrectly(double randomValue) {
        try (MockedStatic<Horse> mockObject = mockStatic(Horse.class)) {
            mockObject.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);
            Horse horse = new Horse("Name", 1, 1);
            Double moveValue = horse.getDistance() + horse.getSpeed() * Horse.getRandomDouble(0.2, 0.9);
            horse.move();
            assertEquals(moveValue, horse.getDistance());
        }
    }

}