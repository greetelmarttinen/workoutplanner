package backend.workoutplanner.web;

import backend.workoutplanner.domain.WorkoutProgramExerciseRepository;
import backend.workoutplanner.domain.WorkoutProgramRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    // lista liikkeistä
    @GetMapping("/exerciselist")
    public String getExercises(Model model) {
        model.addAttribute("exercises", exerciseRepository.findAll());
        return "exerciselist"; // exerciselist.html
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

    // ohjelmasisällön luonti (tiettyyn ohjelmaan)
    @GetMapping("/openprogram/{id}")
    public String openProgram(@PathVariable Long id, Model model) {

        // haetaan ohjelma id:n perusteella
        WorkoutProgram wProgram = workoutProgramRepository.findById(id).get();
        // lisätään modeliin
        model.addAttribute("wProgram", wProgram);

        // haetaan lista liikkeistä
        model.addAttribute("workoutProgramExercises", workoutProgramExerciseRepository.findByWorkoutProgram(wProgram));
        // uuden liikkeen lisäys (form)
        model.addAttribute("workoutProgramExercise", new WorkoutProgramExercise());
        // dropdown liikkeistä
        model.addAttribute("exercises", exerciseRepository.findAll());

        return "openprogram"; // openprogram.html
    }

    // liikkeiden tallennus (kyseiseen) ohjelmaan
    @PostMapping("/addexercise/{programId}")
    public String save(@PathVariable Long programId, @ModelAttribute WorkoutProgramExercise addExercise) {

        // haetaan ohjelma id:n perusteella
        WorkoutProgram wProgram = workoutProgramRepository.findById(programId).get();
        // lisätään liike ohjelmaan
        addExercise.setWorkoutProgram(wProgram);
        // tallennetaan
        workoutProgramExerciseRepository.save(addExercise);
        return "redirect:/openprogram/" + programId; // openprogram
    }

    // ohjelman poistaminen
    @GetMapping("/delete/{id}")
    public String deleteProgram(@PathVariable("id") Long id, Model model) {
        workoutProgramRepository.deleteById(id);
        return "redirect:../index"; // index.html
    }

}
