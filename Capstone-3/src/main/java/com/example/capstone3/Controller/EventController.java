package com.example.capstone3.Controller;

import com.example.capstone3.ApiResponse.ApiResponse;
import com.example.capstone3.DTO.EventDTO;
import com.example.capstone3.Model.Event;
import com.example.capstone3.Service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/event")

// Bushra

public class EventController {
    private final EventService eventService;

    @GetMapping("/get-all")
    public ResponseEntity getAllEvents() {
        return ResponseEntity.status(200).body(eventService.getAllEvents());
    }

    @GetMapping("/get-from-dates/{from}/{to}")
    public ResponseEntity getEventById(@PathVariable LocalDate from, @PathVariable LocalDate to) {
        return ResponseEntity.status(200).body(eventService.getEventsBetweenDates(from,to));
    }
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity getEventById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(eventService.getEventDetailsById(id));
    }
    @GetMapping("/get-without-role")
    public ResponseEntity getAllEventsWithoutRole() {
        return ResponseEntity.status(200).body(eventService.getAllEventsWithoutRole());
    }
    @GetMapping("/get-volunteer/{event_id}")
    public ResponseEntity getAllVolunteersInEvent(@PathVariable Integer event_id) {
        return ResponseEntity.status(200).body(eventService.getAllVolunteersInEvent(event_id));
    }
    @GetMapping("/get-date-stadium/{date}/{stadium}")
    public ResponseEntity getEventsByDateAndStadium(@PathVariable LocalDate date,@PathVariable Integer stadium) {

        return ResponseEntity.status(200).body(eventService.getEventsByDateAndStadium(stadium, date));
    }

    @GetMapping("/get-by-status/{status}")
    public ResponseEntity getEventsByStatus(@PathVariable String status) {
        return ResponseEntity.status(200).body(eventService.getEventsByStatus(status));
    }

    @GetMapping("/get-between-date/{datefrom}/{dateto}")
    public ResponseEntity getEventsBetweenDate(@PathVariable LocalDate datefrom, @PathVariable LocalDate dateto) {
        return ResponseEntity.status(200).body(eventService.getEventsBetweenDates(datefrom,dateto));
    }

    @PostMapping("/add")
    public ResponseEntity addEvent(@RequestBody @Valid EventDTO eventDTO) {
        eventService.addEvent(eventDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Event added"));
    }

    @PutMapping("/update-status/{id}")
    public ResponseEntity updateEventStatus(@PathVariable Integer id, @RequestBody @Valid String status) {
        eventService.updateEventStatus(id, status);
        return ResponseEntity.status(200).body(new ApiResponse("Event status updated successfully"));
    }

    @PutMapping("/update-date/{id}")
    public ResponseEntity updateEventDate(@PathVariable Integer id, @RequestBody @Valid LocalDate date) {
        eventService.updateEventDate(id, date);
        return ResponseEntity.status(200).body(new ApiResponse("Event date updated successfully"));
    }

    @PutMapping("/update-time/{id}")
    public ResponseEntity updateEventTime(@PathVariable Integer id, @RequestBody @Valid LocalTime startTime, @RequestBody @Valid LocalTime endTime) {
        eventService.updateEventTime(id, startTime, endTime);
        return ResponseEntity.status(200).body(new ApiResponse("Event time updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEvent(@PathVariable Integer id) {
        eventService.deleteEventById(id);
        return ResponseEntity.status(200).body(new ApiResponse("Event deleted successfully"));
    }



    //list of all upcoming (future)  events for this stadium  (Aishtiaq-4)

    @GetMapping("/stadium-upcoming-Events/{stadiumId}")
    public ResponseEntity<List<Event>> getUpcomingEventsForStadium(@PathVariable Integer stadiumId) {
        List<Event> events = eventService.getUpcomingEventsForStadium(stadiumId);
        return ResponseEntity.status(200).body(events);
    }


    // Get a list of Full Events (Aishtiaq-9)
    @GetMapping("/full")
    public ResponseEntity<List<Event>> getFullEvents() {
        List<Event> events = eventService.getFullEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/get-past")
    public ResponseEntity getPastEvents() {
        return ResponseEntity.status(200).body(eventService.getPastEvents());
    }
}
