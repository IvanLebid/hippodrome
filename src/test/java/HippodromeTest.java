import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class HippodromeTest {
    @Test
    public  void constructor_NullParamsPassed_IllegalArgumentException(){
        List<Horse> horseList = null;
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(horseList));
        String expectedExceptionMessage = "Horses cannot be null.";
        String actualExceptionMessage = exception.getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @Test
    public void constructor_EmptyParamsPassed_IllegalArgumentException(){
        List<Horse> emptyHorseList = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(emptyHorseList));
        String expectedExceptionMessage = "Horses cannot be empty.";
        String actualExceptionMessage = exception.getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @Test
    void getHorses() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horseList.add(new Horse("horse" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);

        for (int i = 0; i < 10; i++) {
            assertEquals("horse" + i, hippodrome.getHorses().get(i).getName());
        }

    }

    @Test
    void move() {
        List<Horse> horseList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
           horseList.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        hippodrome.move();
        for (Horse mockedHorse : horseList) {
            Mockito.verify(mockedHorse, Mockito.times(1)).move();
        }

    }

    @Test
    void getWinner() {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("horse1", 2, 1));
        horses.add(new Horse("horse2", 3, 2));
        horses.add(new Horse("horse3", 1, 5));
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals("horse3", hippodrome.getWinner().getName());

    }
}