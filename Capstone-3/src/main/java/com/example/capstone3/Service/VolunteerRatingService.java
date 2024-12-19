package com.example.capstone3.Service;

import com.example.capstone3.ApiResponse.ApiException;
import com.example.capstone3.Model.Event;
import com.example.capstone3.Model.Stadium;
import com.example.capstone3.Model.Volunteer;
import com.example.capstone3.Model.VolunteerRating;
import com.example.capstone3.Repository.EventRepository;
import com.example.capstone3.Repository.StadiumRepository;
import com.example.capstone3.Repository.VolunteerRatingRepository;
import com.example.capstone3.Repository.VolunteerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor

// Ahmed

public class VolunteerRatingService {

    private final VolunteerRatingRepository volunteerRatingRepository;
    private final VolunteerRepository volunteerRepository;
    private final StadiumRepository stadiumRepository;
    private final EventRepository eventRepository;


    // method to get ALL the volunteers rating
    public List<VolunteerRating> getAllVolunteerRating(){
        return volunteerRatingRepository.findAll();
    }

    // method to get ALL Ratings for a specific volunteer
    public List<VolunteerRating> getAllVolunteerRatingByVolunteerId(Integer volunteerId){

        Volunteer volunteer = volunteerRepository.findVolunteerById(volunteerId);
        if (volunteer == null){
           throw new ApiException("no volunteer with this id was found");
        }
        return volunteerRatingRepository.findVolunteerRatingByVolunteerId(volunteerId);

    }

    // method to add ration to a volunteer
    public void addVolunteerRating(VolunteerRating volunteerRating, Integer volunteerId, Integer eventId){

        Event event = eventRepository.findEventById(eventId);
        Volunteer volunteer = volunteerRepository.findVolunteerById(volunteerId);

        if (volunteer == null || event == null){
            throw new ApiException("no volunteer or event was found");
        }
        // assign upon creation
        volunteerRating.setVolunteer(volunteer);
        volunteerRating.setEvent(event);
        volunteerRatingRepository.save(volunteerRating);

    }

    // update volunteer rating
    public void updateVolunteerRating(VolunteerRating volunteerRating){

        VolunteerRating oldVolunteerRating = volunteerRatingRepository.findVolunteerRatingById(volunteerRating.getId());
        if (oldVolunteerRating == null){
            throw new ApiException("no volunteer with this id was found");
        }
        oldVolunteerRating.setFeedback(volunteerRating.getFeedback());
        oldVolunteerRating.setRating(volunteerRating.getRating());
        volunteerRatingRepository.save(oldVolunteerRating);
    }

    // method to delete volunteer rating
    public void deleteVolunteerRating(Integer volunteerRatingId){
        VolunteerRating volunteerRating = volunteerRatingRepository.findVolunteerRatingById(volunteerRatingId);
        if (volunteerRating == null){
            throw new ApiException("no volunteer with this id was found");
        }
        volunteerRatingRepository.delete(volunteerRating);
    }



    // method to get the average rating for a volunteer (Aishtiaq1)
     public double volunteersByTheHighestAverageOfRatings(Integer volunteerId){
      List<VolunteerRating> volunteerRating=volunteerRatingRepository.findVolunteerRatingByVolunteerId(volunteerId);
       ArrayList<Integer>sumArrayList=new ArrayList<>();
       if(volunteerRating==null){
           throw new ApiException("no volunteer with this id was found");
       }
       int sum=0;
       double average=0;
       for (VolunteerRating v : volunteerRating){
        sum+=v.getRating() ;
           sumArrayList.add(sum);
           average=sumArrayList.size();

      }
       return sum/average;


       }

    // method volunteers with average rating higher than or equal (Aishtiaq-5)
    public List<Volunteer> getVolunteersWithAverageRatingHigherThan(double minAverageRating) {
        List<Volunteer> allVolunteers = volunteerRepository.findAll();
        List<Volunteer> qualifiedVolunteers = new ArrayList<>();

        for (Volunteer volunteer : allVolunteers) {
            List<VolunteerRating> volunteerRatings = volunteerRatingRepository.findVolunteerRatingByVolunteerId(volunteer.getId());

            if (volunteerRatings != null && !volunteerRatings.isEmpty()) {
                double averageRating = calculateAverageRating(volunteerRatings);
                if (averageRating >= minAverageRating) {
                    qualifiedVolunteers.add(volunteer);
                }
            }
        }

        return qualifiedVolunteers;
    }

    private double calculateAverageRating(List<VolunteerRating> ratings) {
        int sum = 0;
        for (VolunteerRating rating : ratings) {
            sum += rating.getRating();
        }
        return (double) sum / ratings.size();
    }
}






