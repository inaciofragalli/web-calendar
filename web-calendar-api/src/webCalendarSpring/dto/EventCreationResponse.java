package webCalendarSpring.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"message", "event", "date"})
public record EventCreationResponse(
        String message,
        String event,
        String date
) {}