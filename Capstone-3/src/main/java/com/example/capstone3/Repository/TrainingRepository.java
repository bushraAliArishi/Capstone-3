package com.example.capstone3.Repository;

import com.example.capstone3.Model.Training;
import com.example.capstone3.Model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

// Aishtiaq

public interface TrainingRepository extends JpaRepository<Training,Integer> {

    Training findTrainingById(Integer id);

    Training findFirstByCompletedFalseAndEnrolledVolunteersLessThan(Integer capacity);

    //  get Trainer's Most Recent Upcoming Event (Aishtiaq-6)
    @Query("SELECT t FROM Training t WHERE t.trainer.id = ?1 AND t.startDate > CURRENT_DATE ORDER BY t.startDate ASC")
    List<Training> findUpcomingEventsByTrainerId(Integer trainerId);


    @Query("SELECT t FROM Training t JOIN FETCH t.volunteers WHERE t.id = :trainingId")
    Training findTrainingWithVolunteers(@Param("trainingId") Integer trainingId);

}
