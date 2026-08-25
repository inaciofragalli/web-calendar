package webCalendarSpring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class EventRequest {
    @NotBlank
    private String event;
    @NotNull
    private LocalDate date;

    public @NotBlank String getEvent() {
        return event;
    }

    public void setEvent(@NotBlank String event) {
        this.event = event;
    }

    public @NotNull LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull LocalDate date) {
        this.date = date;
    }
}
