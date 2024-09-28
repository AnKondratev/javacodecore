package code.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;

public class DateFormat {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy:MM:dd'##':HH:mm:ss:SSS", locale = "ru_RU")
    private LocalDateTime time = LocalDateTime.now();

    public LocalDateTime getTime() {
        return time;
    }

    private String timeLocaleFormat() {
        DateFormat formatDate = new DateFormat();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            return objectMapper.writeValueAsString(formatDate);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        DateFormat formatDate = new DateFormat();
        System.out.println("Format LocalDateTime: " + formatDate.getTime());
        System.out.println("JSON Format: " + new DateFormat().timeLocaleFormat());
    }
}


