package webCalendarSpring.dto;

import java.time.LocalDate;

public record EventResponse(
   Long id,
   String event,
   LocalDate date
) {}
