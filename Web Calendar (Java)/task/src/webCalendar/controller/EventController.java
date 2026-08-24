package webCalendarSpring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {

    @GetMapping("/event/today")
    public List<Object> getTodayEvents() {
        return List.of();
    }

    @PostMapping("/event")
    public Map<String, String> createEvent(@Valid @RequestBody EventRequest req) {
        return Map.of(
                "message", "The event has been added!",
                "event", req.getEvent(),
                "date", req.getDate().toString()
        );
    }
}
