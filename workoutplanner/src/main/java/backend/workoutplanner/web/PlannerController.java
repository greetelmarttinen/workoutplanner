package backend.workoutplanner.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import backend.workoutplanner.domain.ExerciseRepository;
import backend.workoutplanner.domain.WorkoutProgram;

@Controller
// käsittelee clientin pyynnöt/requestit ja välittää takaisin
// vastaukset/responset
public class PlannerController {
    private ExerciseRepository exerciseRepository;

    // konstruktorin injektointi
    public PlannerController(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    // 'kotisivu'
    @GetMapping({ "/", "/index" })
    public String mainPage() {
        return "index"; // index.html
    }

    // uuden ohjelman luonti (form)
    @GetMapping("/createprogram")
    public String createWorkout(Model model) {
        model.addAttribute("workoutProgram", new WorkoutProgram());

        return "createprogram"; // createprogram.html
    }

    // ohjelman tallennus
    @PostMapping("/saveprogram")
    public String save(@ModelAttribute WorkoutProgram workoutProgram) {
        workoutProgramRepository.save(workoutProgram);
        return "redirect../index"; // index.html
    }

}
