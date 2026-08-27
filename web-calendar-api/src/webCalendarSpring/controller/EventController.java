package webCalendarSpring.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import webCalendarSpring.dto.EventCreationResponse;
import webCalendarSpring.dto.EventDeletionResponse;
import webCalendarSpring.dto.EventRequest;
import webCalendarSpring.dto.EventResponse;
import webCalendarSpring.service.EventService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping()
    @ResponseBody
    public ResponseEntity<Page<EventResponse>> getAllEvents(Pageable pageable) {
        Page<EventResponse> res = service.getAllEvents(pageable);

        if (res.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(res);
    }

    @GetMapping("/between")
    public ResponseEntity<List<EventResponse>> getEventBetween(
            @RequestParam("start_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start_time,
            @RequestParam("end_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end_time) {

        List<EventResponse> res = service.getEventBetween(start_time, end_time);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEventById(@PathVariable("id") Long id) {
        EventResponse res = service.getEventById(id);

        return ResponseEntity.ok(res);
    }

    @GetMapping("/today")
    public ResponseEntity<List<EventResponse>> getTodayEvents() {
        List<EventResponse> todayEvents = service.getTodayEvents();
        return ResponseEntity.ok(todayEvents);
    }

    @PostMapping("/newevent")
    public ResponseEntity<EventCreationResponse> createEvent(@Valid @RequestBody EventRequest req) {
        EventResponse savedEvent = service.createEvent(req);
        EventCreationResponse res = new EventCreationResponse(
                "event was created!",
                savedEvent.event(),
                savedEvent.date().toString()
        );
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EventDeletionResponse> deleteEventById(@PathVariable("id") Long id) {
        EventResponse deletedEvent = service.deleteEventById(id);
        EventDeletionResponse res = new EventDeletionResponse(
                "event was deleted",
                deletedEvent.event(),
                deletedEvent.date().toString()
        );

        return ResponseEntity.ok(res);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Void> handleValidationExceptions() {
        // This intercepts the validation error and returns a 400 status with an empty body
        return ResponseEntity.badRequest().build();
    }
}