package backend.workoutplanner.web;

import backend.workoutplanner.domain.WorkoutProgramExerciseRepository;
import backend.workoutplanner.domain.WorkoutProgramRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import backend.workoutplanner.domain.ExerciseRepository;
import backend.workoutplanner.domain.WorkoutProgram;
import backend.workoutplanner.domain.WorkoutProgramExercise;

@Controller
// käsittelee clientin pyynnöt/requestit ja välittää takaisin
// vastaukset/responset
public class PlannerController {
    private final WorkoutProgramExerciseRepository workoutProgramExerciseRepository;
    private final WorkoutProgramRepository workoutProgramRepository;
    private ExerciseRepository exerciseRepository;

    // konstruktorin injektointi
    public PlannerController(ExerciseRepository exerciseRepository, WorkoutProgramRepository workoutProgramRepository,
            WorkoutProgramExerciseRepository workoutProgramExerciseRepository) {
        this.exerciseRepository = exerciseRepository;
        this.workoutProgramRepository = workoutProgramRepository;
        this.workoutProgramExerciseRepository = workoutProgramExerciseRepository;
    }

    // 'kotisivu'
    @GetMapping({ "/", "/index" })
    public String mainPage(Model model) {
        model.addAttribute("workoutPrograms", workoutProgramRepository.findAll());
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
        return "redirect:/index"; // index.html
    }

    // ohjelmasisällön luonti
    @GetMapping("/openprogram/{id}")
    public String openProgram(Model model) {
        // haetaan lista liikkeistä
        model.addAttribute("workoutProgramExercises", workoutProgramExerciseRepository.findAll());
        // uuden liikkeen lisäys (form)
        model.addAttribute("workoutProgramExercise", new WorkoutProgramExercise());
        // dropdown liikkeistä
        model.addAttribute("exercises", exerciseRepository.findAll());

        return "openprogram"; // openprogram.html
    }

    // liikkeiden tallennus ohjelmaan
    @PostMapping("/addexercise")
    public String save(@ModelAttribute WorkoutProgramExercise addExercise) {
        workoutProgramExerciseRepository.save(addExercise);
        return "redirect:/openprogram"; // openprogram
    }

}
