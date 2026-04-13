package backend.workoutplanner.web;

import backend.workoutplanner.domain.ExerciseRepository;
import backend.workoutplanner.domain.User;
import backend.workoutplanner.domain.UserRepository;
import backend.workoutplanner.domain.WorkoutProgramExerciseRepository;
import backend.workoutplanner.domain.WorkoutProgramRepository;
import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;
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
public class WorkoutProgramController {

    private final WorkoutProgramExerciseRepository workoutProgramExerciseRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutProgramRepository workoutProgramRepository;
    private final UserRepository userRepository;

    WorkoutProgramController(WorkoutProgramRepository workoutProgramRepository, ExerciseRepository exerciseRepository,
            WorkoutProgramExerciseRepository workoutProgramExerciseRepository, UserRepository userRepository) {
        this.workoutProgramRepository = workoutProgramRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutProgramExerciseRepository = workoutProgramExerciseRepository;
        this.userRepository = userRepository;
    }

    // 'kotisivu'
    @GetMapping({ "/", "/index" })
    public String mainPage(Model model, Authentication authentication) {

        // haetaan kirjautuneen käyttäjän tiedot
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        // jos käyttäjä on admin
        if (user.getRole().equals("ADMIN")) {
            // lista KAIKISTA tallennetuista ohjelmista
            model.addAttribute("workoutPrograms", workoutProgramRepository.findAll());
        } else {
            // muuten näkyviin vain käyttäjän omat ohjelmat
            model.addAttribute("workoutPrograms", workoutProgramRepository.findByUser(user));
        }

        // uuden ohjelman tallentaminen/lisäys listaan
        model.addAttribute("workoutProgram", new WorkoutProgram());

        return "index"; // index.html
    }

    // ohjelman tallennus
    @PostMapping("/saveprogram")
    public String saveProgram(@Valid @ModelAttribute WorkoutProgram workoutProgram, BindingResult bindingResult,
            Model model, Authentication authentication) {

        // tarkistetaaan kirjautunut käyttäjä
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);

        // virheidenkäsittely
        if (bindingResult.hasErrors()) {

            if (user.getRole().equals("ADMIN")) {
                // jos kirjatunut käyttäjä on ADMIN -> näytetään kaikki tietokantaan tallennetut
                // ohjelmat kaikilta käyttäjiltä
                model.addAttribute("workoutPrograms", workoutProgramRepository.findAll());

            } else {
                // muu kirjautunut käyttäjä -> näkyy vain käyttäjän omat ohjelmat
                model.addAttribute("workoutPrograms", workoutProgramRepository.findByUser(user));
            }
            model.addAttribute("workoutProgram", workoutProgram);
            return "index"; // index.html
        }

        // tallennetaan ohjelma tietylle/kyseiselle kirjautuneelle käyttäjälle
        workoutProgram.setUser(user);

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
