# Capstone-3
read me 

models 

Attendance
Event
Role
Stadium

DTO

AttendanceDTO
AttendanceDTOout
EventDTO
EventDTOout
RoleDTO
RoleDTOout
StadiumDTO
StadiumDTOout

Repository

AttendanceRepository
EventRepository
RoleRepository
StadiumRepositor

Service

AttendanceService
EventService
RoleService
StadiumService

Endpoints

AttendanceController:

GET /api/v1/attendance/get-Absent-count/{volunteer_Id}

GET /api/v1/attendance/get-by-volunteer/{volunteerId}

PUT /api/v1/attendance/absent/{volunteerId}/{eventId}

PUT /api/v1/attendance/mark-check-in/{volunteer_id}/{event_id}

PUT /api/v1/attendance/mark-check-out/{volunteer_id}/{event_id}


EventController:

GET /api/v1/event/get-without-role

GET /api/v1/event/get-volunteer/{event_id}

GET /api/v1/event/get-date-stadium/{date}/{stadium}

GET /api/v1/event/get-by-status/{status}

GET /api/v1/event/get-between-date/{datefrom}/{dateto}

PUT /api/v1/event/update-status/{id}

PUT /api/v1/event/update-date/{id}

PUT /api/v1/event/update-time/{id}

GET /api/v1/event/get-past

RoleController:

GET /api/v1/roles/get-by-volunteer/{volunteerId}

PUT /api/v1/roles/update-description/{id}

StadiumController:

GET /api/v1/stadium/get-by-city/{city}

GET /api/v1/stadium/get-by-status/{status}

GET /api/v1/stadium/get-largest

PUT /api/v1/stadium/update-name/{id}

PUT /api/v1/stadium/update-city/{id}

PUT /api/v1/stadium/update-location/{id}

PUT /api/v1/stadium/update-capacity/{id}

PUT /api/v1/stadium/update-status/{id}

DELETE /api/v1/stadium/delete-by-status/{status}




