package backend.workoutplanner.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import backend.workoutplanner.domain.ExerciseRepository;

@Controller
// käsittelee clientin pyynnöt/requestit ja välittää takaisin
// vastaukset/responset
public class PlannerController {
    private ExerciseRepository exerciseRepository;

    // konstruktorin injektointi
    public PlannerController(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @GetMapping({ "/", "/index" })
    public String mainPage() {
        return "index"; // index.html
    }

}
