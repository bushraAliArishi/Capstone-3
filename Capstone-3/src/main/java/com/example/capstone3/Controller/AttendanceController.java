package com.example.capstone3.Controller;

import com.example.capstone3.ApiResponse.ApiResponse;
import com.example.capstone3.DTO.AttendanceDTO;
import com.example.capstone3.DTO.AttendanceDTOout;
import com.example.capstone3.Service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendance")
@RequiredArgsConstructor

// Bushra

public class AttendanceController {
    private final AttendanceService attendanceService;

    @GetMapping("/get-all")
    public ResponseEntity getAllAttendances() {
        List<AttendanceDTOout> attendances = attendanceService.getAllAttendances();
        return ResponseEntity.status(200).body(attendances);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity getAttendanceById(@PathVariable Integer id) {
        AttendanceDTOout attendance = attendanceService.getAttendanceById(id);
        return ResponseEntity.status(200).body(attendance);
    }

    @GetMapping("/get-by-event/{eventId}")
    public ResponseEntity getAttendancesByEvent(@PathVariable Integer eventId) {
        List<AttendanceDTOout> attendances = attendanceService.getAttendancesByEvent(eventId);
        return ResponseEntity.status(200).body(attendances);
    }
    @GetMapping("/get-Absent-count/{volunteer_Id}")
    public ResponseEntity getAttendanceCountAbsent(@PathVariable Integer volunteer_Id) {
        return ResponseEntity.status(200).body((new ApiResponse("the total Absent "+attendanceService.getAttendanceCountAbsent(volunteer_Id))));
    }

    @GetMapping("/get-by-volunteer/{volunteerId}")
    public ResponseEntity getAttendancesByVolunteer(@PathVariable Integer volunteerId) {
        List<AttendanceDTOout> attendances = attendanceService.getAttendancesByVolunteer(volunteerId);
        return ResponseEntity.status(200).body(attendances);
    }

    // this is for CRUD only but it is not actually used
    @PostMapping("/add")
    public ResponseEntity addAttendance(@RequestBody @Valid AttendanceDTO attendanceDTO) {
        attendanceService.addAttendance(attendanceDTO);
        return ResponseEntity.status(201).body(new ApiResponse("Attendance added successfully"));
    }

    @PutMapping("/absent/{volunteerId}/{eventId}")
    public ResponseEntity absentAttendance(@PathVariable Integer volunteerId, @PathVariable Integer eventId) {
        attendanceService.updateAttendanceStatusAbsent(volunteerId, eventId);
        return ResponseEntity.status(201).body(new ApiResponse("Attendance absent successfully"));
    }

    // endpoint to mark the volunteer check in time  --- Bushra (1)
    @PutMapping("/mark-check-in/{volunteer_id}/{event_id}")
    public ResponseEntity markAttendanceCheckIn(@PathVariable Integer volunteer_id, @PathVariable Integer event_id) {
        attendanceService.markAttendanceCheckedIn(volunteer_id, event_id);
        return ResponseEntity.status(200).body(new ApiResponse("Attendance marked as checked-in"));
    }

    @PutMapping("/mark-check-out/{volunteer_id}/{event_id}")
    public ResponseEntity markAttendanceCheckOut(@PathVariable Integer volunteer_id, @PathVariable Integer event_id) {
        attendanceService.markAttendanceCheckedOut(volunteer_id, event_id);
        return ResponseEntity.status(200).body(new ApiResponse("Attendance marked as checked-out"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAttendance(@PathVariable Integer id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.status(200).body("Attendance deleted");
    }



    //(Aishtiaq-2)
    @GetMapping("/find-replacement/{eventId}/{absentVolunteerId}")
    public ResponseEntity findReplacement(@PathVariable Integer eventId, @PathVariable Integer absentVolunteerId) {
        attendanceService.findReplacement(eventId, absentVolunteerId);
        return ResponseEntity.status(200).body(new ApiResponse("Replacement completed successfully"));
    }

}