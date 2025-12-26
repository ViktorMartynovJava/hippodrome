import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;



class HippodromeTest {

    @Test
    @DisplayName("IllegalArgumentException if parameter is null")
    void trowExceptionIfParameterNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    @DisplayName("Message if parameter is null")
    void messageExceptionIfParameterNull() {
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    @DisplayName("IllegalArgumentException if list is null")
    void trowExceptionIfListNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    @DisplayName("Message if list is null")
    void messageIfListNull() {
        try {
            new Hippodrome(new ArrayList<>());
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Name" + i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horseList.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        hippodrome.move();
        for (Horse horse : horseList) {
            Mockito.verify(horse, Mockito.times(1)).move();
        }
    }

    @Test
    void getWinner() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(String.format("Horse number", i + 1), 1.0d, i + 1));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(30.0d, hippodrome.getWinner().getDistance());
    }
}
