package webCalendarSpring.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import webCalendarSpring.dto.EventDeletionResponse;
import webCalendarSpring.dto.EventRequest;
import webCalendarSpring.dto.EventResponse;
import webCalendarSpring.exception.EventNotFoundException;
import webCalendarSpring.model.Event;
import webCalendarSpring.repository.EventRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Service
public class EventService {
    private final EventRepository repository;

    public EventService(EventRepository repo) {
        this.repository = repo;
    }

    public Page<EventResponse> getAllEvents(Pageable pageable) {
        Page<Event> eventPage = repository.findAll(pageable);

        return eventPage.map(event -> new EventResponse(
                event.getId(),
                event.getEvent(),
                event.getDate()
        ));
    }

    public List<EventResponse> getTodayEvents() {
        List<Event> todayEvents = repository.findByDate(LocalDate.now());

        return todayEvents.stream().map(event -> new EventResponse(
                event.getId(),
                event.getEvent(),
                event.getDate()
        )).toList();
    }

    public List<EventResponse> getEventBetween(LocalDate startTime, LocalDate endTime) {
        List<Event> eventList = repository.findByDateBetween(startTime, endTime);

        return eventList.stream().map(event -> new EventResponse(
                event.getId(),
                event.getEvent(),
                event.getDate()
        )).toList();
    }

    public EventResponse getEventById(Long id) {
        Event event = repository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id " + id));

        return new EventResponse(
                event.getId(),
                event.getEvent(),
                event.getDate()
        );
    }

    public EventResponse createEvent(EventRequest req) {
        Event newEvent = new Event();
        newEvent.setEvent(req.event());
        newEvent.setDate(req.date());

        repository.save(newEvent);

        return new EventResponse(
                newEvent.getId(),
                newEvent.getEvent(),
                newEvent.getDate()
        );
    }

    public EventResponse deleteEventById(Long id) {
        Event event = repository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event with id " + id + "was not found"));

        repository.deleteById(id);

        return new EventResponse(
                event.getId(),
                event.getEvent(),
                event.getDate()
        );
    }
}
