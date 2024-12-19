package com.example.capstone3.Service;

import com.example.capstone3.ApiResponse.ApiException;
import com.example.capstone3.DTO.TrainerDTO;
import com.example.capstone3.DTO.TrainingDTO;
import com.example.capstone3.Model.Training;
import com.example.capstone3.Model.Volunteer;
import com.example.capstone3.Repository.TrainingRepository;
import com.example.capstone3.Repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

// Aishtiaq

public class TrainingService {

    private final TrainingRepository trainingRepository;
    private final VolunteerRepository volunteerRepository;


    public List<Training> getAllTraining(){

        return trainingRepository.findAll();
    }


    public List<TrainingDTO> getTrainingDTO(){


        List<Training>trainings=trainingRepository.findAll();

        List<TrainingDTO>trainingDTOS=new ArrayList<>();

        for(Training t:trainings){

            TrainingDTO trainingDTO=new TrainingDTO(t.getTitle(),t.getDescription(),t.isCompleted());

            trainingDTOS.add(trainingDTO);
        }

        return trainingDTOS;
    }

    public void addTraining(Training training){

        trainingRepository.save(training);
    }

    public void updateTraining(Integer id, Training training){

        Training training1=trainingRepository.findTrainingById(id);

        if ((training1==null)){
            throw  new ApiException("Training not found");
        }

        training1.setTitle(training.getTitle());
        training1.setDescription(training.getDescription());
        training1.setStartDate(training.getStartDate());
        training1.setEndDate(training.getEndDate());
        training1.setCompleted(training.isCompleted());

        trainingRepository.save(training1);
    }


    public void deleteVolunteer(Integer id){

        Training training1=trainingRepository.findTrainingById(id);

        if(training1==null){

            throw new ApiException("Training not found ");
        }

        trainingRepository.delete(training1);
    }

    //  get Trainer's Most Recent Upcoming Event (Aishtiaq-6)
    public Training getMostRecentUpcomingEvent(Integer trainerId) {
        List<Training> upcomingEvents = trainingRepository.findUpcomingEventsByTrainerId(trainerId);

        if (upcomingEvents.isEmpty()) {
            throw new RuntimeException("No upcoming events found for trainer ID: " + trainerId);
        }
        return upcomingEvents.get(0);
    }

    // Ahmed (9)
    public List<Volunteer> getAllVolunteerInTraining(Integer trainingId){
       Training training1=trainingRepository.findTrainingById(trainingId);
       if (training1==null){
           throw new ApiException("Training not found");
       }
        List<Volunteer> volunteers = volunteerRepository.findVolunteerByTraining(training1);
       if (volunteers.isEmpty()){
           throw new RuntimeException("No volunteers found for training ID: " + trainingId);
       }
       return volunteers;
     }
}
