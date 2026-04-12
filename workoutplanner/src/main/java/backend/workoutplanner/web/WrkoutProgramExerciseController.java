package backend.workoutplanner.web;

import backend.workoutplanner.domain.ExerciseRepository;
import backend.workoutplanner.domain.WorkoutProgramExerciseRepository;
import backend.workoutplanner.domain.WorkoutProgramRepository;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import backend.workoutplanner.domain.WorkoutProgram;
import backend.workoutplanner.domain.WorkoutProgramExercise;

@Controller
// käsittelee clientin pyynnöt/requestit ja välittää takaisin
// vastaukset/responset
public class WrkoutProgramExerciseController {
    private final ExerciseRepository exerciseRepository;
    private final WorkoutProgramExerciseRepository workoutProgramExerciseRepository;
    private final WorkoutProgramRepository workoutProgramRepository;

    // konstruktorin injektointi
    public WrkoutProgramExerciseController(
            WorkoutProgramRepository workoutProgramRepository,
            WorkoutProgramExerciseRepository workoutProgramExerciseRepository, ExerciseRepository exerciseRepository) {

        this.workoutProgramRepository = workoutProgramRepository;
        this.workoutProgramExerciseRepository = workoutProgramExerciseRepository;
        this.exerciseRepository = exerciseRepository;
    }

    // liikkeiden tallennus (kyseiseen) ohjelmaan
    @PostMapping("/addexercise/{programId}")
    public String save(@PathVariable Long programId, @Valid @ModelAttribute WorkoutProgramExercise addExercise,
            BindingResult bindingResult, Model model) {
        // virheidenkäsittely
        if (bindingResult.hasErrors()) {

            WorkoutProgram wProgram = workoutProgramRepository.findById(programId).orElse(null);

            model.addAttribute("wProgram", wProgram);
            model.addAttribute("wProgram", workoutProgramRepository.findById(programId).orElse(null));
            model.addAttribute("exercises", exerciseRepository.findAll());
            return "openprogram";

        }

        // haetaan ohjelma id:n perusteella
        WorkoutProgram wProgram = workoutProgramRepository.findById(programId).get();
        // lisätään liike ohjelmaan
        addExercise.setWorkoutProgram(wProgram);
        // tallennetaan
        workoutProgramExerciseRepository.save(addExercise);
        return "redirect:/openprogram/" + programId; // openprogram
    }

    // liikkeen poisto ohjelmasta
    @GetMapping("/deleteWPE/{id}")
    public String deleteExerciseFromProgram(@PathVariable("id") Long id, Model model) {
        // haetaan kyseinen liike
        WorkoutProgramExercise wpe = workoutProgramExerciseRepository.findById(id).get();

        // tallennetaan ohjelman id
        Long programId = wpe.getWorkoutProgram().getWorkoutProgramId();

        // poistetaan liike ohjelmasta
        workoutProgramExerciseRepository.deleteById(id);

        return "redirect:/openprogram/" + programId; // openprogram.html
    }

}
