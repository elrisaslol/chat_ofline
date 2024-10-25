package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeAdapter {
    /**
     * Convierte el LocalTime a String porque no permite en XML
     * @param time La fecha del mensaje
     * @return Un String que es una fecha
     */
    public static String convertLocalTimeToString(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return time.format(formatter);
    }

    public static LocalDateTime convertStringToLocalTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.parse(timeString, formatter);
    }



}

