package utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeAdapter {
    /**
     * Convierte el LocalTime a String porque no permite en XML
     * @param time La fecha del mensaje
     * @return Un String que es una fecha
     */
    public static String convertLocalTimeToString(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return time.format(formatter);
    }

    public static LocalTime convertStringToLocalTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalTime.parse(timeString, formatter);
    }

}

