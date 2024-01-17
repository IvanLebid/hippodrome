import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    public void nameIsNullException(){
       String name = null;
       double speed = 0.2;
       double distance = 0.5;
      Throwable nullPointerException = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));

        String nullPointerExceptionMessage = nullPointerException.getMessage();
        assertEquals("Name cannot be null.", nullPointerExceptionMessage);
    }
    static Stream<String> whitespaceCharacters(){
        return Stream.of("", " ", "\t", "\n");
    }

    @ParameterizedTest
    @MethodSource("whitespaceCharacters")
    public void spaceException(String name){
        double speed = 0.2;
        double distance = 0.5;
        Throwable blankException = assertThrows(IllegalArgumentException.class, () ->   new Horse(name, speed, distance));

        String blankExceptionMessage = blankException.getMessage();
        assertEquals("Name cannot be blank.", blankExceptionMessage);
    }

    @Test
    public void negativeSpeedException(){
        String name = "horseName";
        double speed = - 1.5;
        double distance = 0.5;
        Throwable negativeSpeedException = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));

        String negativeSpeedExceptionMessage = negativeSpeedException.getMessage();
        assertEquals("Speed cannot be negative.", negativeSpeedExceptionMessage);
    }

@Test
public void negativeDistanceException(){
        String name = "untunder";
        double speed = 0.2;
        double distance = -1.0;
        Throwable negativeDistanceException = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));

        String negativeDistanceExceptionMessage = negativeDistanceException.getMessage();
        assertEquals("Distance cannot be negative.", negativeDistanceExceptionMessage);
}

    @Test
    void getName() {
        String name = "name";
        double speed = 0.5, distance = 0.7;
        Horse horsesName = new Horse(name, speed, distance);
        String actual = horsesName.getName();

        assertEquals(actual, name);
    }

    @Test
    void getSpeed() {
        String name = "name";
        double speed = 0.5, distance = 0.7;
        Horse horsesSpeed = new Horse(name, speed, distance);
        double actual = horsesSpeed.getSpeed();

        assertEquals(actual, speed);
    }

    @Test
    void getDistance() {
        String horseName = "name";
        double horseSpeed = 0.5;
        double distance = 0.7;
        Horse horse1  = new Horse(horseName, horseSpeed, distance);
        double actualDistanceHorse1 = horse1.getDistance();

        assertEquals(actualDistanceHorse1, distance);

        Horse horse2 = new Horse(horseName, horseSpeed);
        double actualDistanceHorse2 = horse2.getDistance();
        double expectedDistanceHorse2 = 0;
        assertEquals(actualDistanceHorse2, expectedDistanceHorse2);

    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9})
    void move(double value) {
        double min = 0.2, max = 0.9;
        String name = "name";
        double speed = 0.5;
        double distance = 0.7;
        try (MockedStatic<Horse> staticHorse = Mockito.mockStatic(Horse.class)) {
            staticHorse.when(() -> Horse.getRandomDouble(min, max)).thenReturn(value);
            Horse horse = new Horse(name, speed, distance);
            double actualDistance = distance + speed * value;
            horse.move();
            staticHorse.verify(() -> Horse.getRandomDouble(min, max));
            assertEquals(actualDistance, horse.getDistance());
        }
    }


}