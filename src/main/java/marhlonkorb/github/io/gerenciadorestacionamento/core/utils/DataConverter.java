package marhlonkorb.github.io.gerenciadorestacionamento.core.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DataConverter {

    final static String pattern = "dd/MM/yyyy";
    public static LocalDate converteStringParaData(String date){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(pattern);
        if(validaData(date, pattern)) {
            return LocalDate.parse(date, formato);
        }
        return null;
    }

    private static boolean validaData(String dataString, String formato) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
            LocalDate data = LocalDate.parse(dataString, formatter);
            LocalDate dataMinima = LocalDate.of(1900, 1, 1);
            LocalDate dataMaxima = LocalDate.of(2100, 12, 31);
            return !data.isBefore(dataMinima) && !data.isAfter(dataMaxima);
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
