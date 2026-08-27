package webCalendarSpring.dto;

public record EventDeletionResponse(
        String message,
        String event,
        String date
) {
}
