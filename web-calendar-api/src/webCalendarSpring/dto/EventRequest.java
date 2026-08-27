package webCalendarSpring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record EventRequest (
        @NotBlank(message = "Event cannot be empty")
        String event,
        @NotNull(message = "Date cannot be null")
        LocalDate date
) {}
