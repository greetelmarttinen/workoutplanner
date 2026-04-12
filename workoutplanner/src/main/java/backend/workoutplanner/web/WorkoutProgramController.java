package backend.workoutplanner.web;

import backend.workoutplanner.domain.ExerciseRepository;
import backend.workoutplanner.domain.WorkoutProgramExerciseRepository;
import backend.workoutplanner.domain.WorkoutProgramRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import backend.workoutplanner.domain.WorkoutProgram;
import backend.workoutplanner.domain.WorkoutProgramExercise;

@Controller
public class WorkoutProgramController {

    private final WorkoutProgramExerciseRepository workoutProgramExerciseRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutProgramRepository workoutProgramRepository;

    WorkoutProgramController(WorkoutProgramRepository workoutProgramRepository, ExerciseRepository exerciseRepository,
            WorkoutProgramExerciseRepository workoutProgramExerciseRepository) {
        this.workoutProgramRepository = workoutProgramRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutProgramExerciseRepository = workoutProgramExerciseRepository;
    }

    // 'kotisivu'
    @GetMapping({ "/", "/index" })
    public String mainPage(Model model) {
        // lista tallennetuista ohjelmista
        model.addAttribute("workoutPrograms", workoutProgramRepository.findAll());

        // uuden ohjelman tallentaminen/lisäys listaan
        model.addAttribute("workoutProgram", new WorkoutProgram());

        return "index"; // index.html
    }

    // ohjelman tallennus
    @PostMapping("/saveprogram")
    public String saveProgram(@ModelAttribute WorkoutProgram workoutProgram) {
        workoutProgramRepository.save(workoutProgram);
        return "redirect:index"; // index.html
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

    // ohjelman poistaminen
    @GetMapping("/delete/{id}")
    public String deleteProgram(@PathVariable("id") Long id, Model model) {
        workoutProgramRepository.deleteById(id);
        return "redirect:../index"; // index.html
    }
}
