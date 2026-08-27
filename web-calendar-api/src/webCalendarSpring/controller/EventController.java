package webCalendarSpring.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import webCalendarSpring.dto.EventRequest;
import webCalendarSpring.exception.EventNotFoundException;
import webCalendarSpring.model.Event;
import webCalendarSpring.repository.EventRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class EventController {
    private final EventRepository repository;

    public EventController(EventRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/event")
    @ResponseBody
    public ResponseEntity<List<Event>> getAllEvents(@RequestParam(required = false) LocalDate start_time, LocalDate end_time) {
        if (start_time != null && end_time != null) {
            List<Event> events = repository.findByDateBetween(start_time, end_time);

            if (events.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(events);
        }

        List<Event> events = repository.findAll();

        if (events.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(events);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") Long id) {
        Event event = repository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("The event doesn't exist!"));

        return ResponseEntity.ok(event);
    }

    @GetMapping("/event/today")
    public ResponseEntity<List<Event>> getTodayEvents() {
        List<Event> todayEvents = repository.findByDate(LocalDate.now());
        return ResponseEntity.ok(todayEvents);
    }

    @PostMapping("/event")
    public Map<String, String> createEvent(@Valid @RequestBody EventRequest req) {
        Event newEvent = new Event();
        newEvent.setEvent(req.getEvent());
        newEvent.setDate(req.getDate());

    @PostMapping("/newevent")
    public ResponseEntity<EventCreationResponse> createEvent(@Valid @RequestBody EventRequest req) {
        EventResponse savedEvent = service.createEvent(req);
        EventCreationResponse res = new EventCreationResponse(
                "event was created!",
                savedEvent.event(),
                savedEvent.date().toString()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EventDeletionResponse> deleteEventById(@PathVariable Long id) {
        EventResponse deletedEvent = service.deleteEventById(id);
        EventDeletionResponse res = new EventDeletionResponse(
                "event was deleted",
                deletedEvent.event(),
                deletedEvent.date().toString()
        );

        return event;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Void> handleValidationExceptions() {
        // This intercepts the validation error and returns a 400 status with an empty body
        return ResponseEntity.badRequest().build();
    }
}