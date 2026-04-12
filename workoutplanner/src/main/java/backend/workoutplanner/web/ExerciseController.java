package backend.workoutplanner.web;

import backend.workoutplanner.domain.ExerciseRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import backend.workoutplanner.domain.Exercise;
import org.springframework.ui.Model;

@Controller
public class ExerciseController {
    private final ExerciseRepository exerciseRepository;

    ExerciseController(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    // lista liikkeistä
    @GetMapping("/exerciselist")
    public String getExercises(Model model) {
        // haetaan liikkeet tietokannasta
        model.addAttribute("exercises", exerciseRepository.findAll());

        // uuden liikkeen lisäys listaan
        model.addAttribute("exercise", new Exercise());

        return "exerciselist"; // exerciselist.html
    }

    // liikkeen poistaminen listasta, ADMIN oikeus
    @GetMapping("/deleteExercise/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteExercise(@PathVariable("id") Long exerciseId, Model model) {
        exerciseRepository.deleteById(exerciseId);
        return "redirect:../exerciselist"; // exerciselist.html
    }

    // liikkeen tietojen muokkaaminen, ADMIN oikeus
    @GetMapping("/editExercise/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editExercise(@PathVariable("id") Long exerciseId, Model model) {
        // haetaan liike id:n perusteella ja tallennetaan modeliin
        Exercise exercise = exerciseRepository.findById(exerciseId).orElse(null);
        model.addAttribute("exercise", exercise);

        // lista kaikista liikkeistä
        model.addAttribute("exercises", exerciseRepository.findAll());

        return "exerciselist"; // exerciselist.html
    }

    // uuden liikkeen lisääminen listaan
    @PostMapping("/saveexercise")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveExercise(@ModelAttribute Exercise exercise) {
        exerciseRepository.save(exercise);
        return "redirect:/exerciselist"; // exerciselist.html

    }

}