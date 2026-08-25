package webCalendarSpring.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import webCalendarSpring.dto.EventRequest;

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
                .orElseThrow(() -> new EventNotFoundException("The event doesn't exist"));
        
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

        repository.save(newEvent);

        return Map.of(
                "message", "The event has been added!",
                "event", req.getEvent(),
                "date", req.getDate().toString()
        );
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<Event> deleteEventById(@PathVariable("id") Long id) {
        ResponseEntity<Event> event = getEventById(id);
        repository.deleteById(id);

        return event;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Void> handleValidationExceptions() {
        // This intercepts the validation error and returns a 400 status with an empty body
        return ResponseEntity.badRequest().build();
    }
}
