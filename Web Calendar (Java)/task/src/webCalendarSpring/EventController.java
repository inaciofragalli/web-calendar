package webCalendarSpring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {

    @GetMapping("/events/today")
    public List<Object> getTodayEvents() {
        return List.of();
    }
}
