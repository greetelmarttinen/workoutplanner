package backend.workoutplanner.web;

import backend.workoutplanner.domain.WorkoutProgramExerciseRepository;
import backend.workoutplanner.domain.WorkoutProgramRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import backend.workoutplanner.domain.Exercise;
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
        return "redirect:exerciselist"; // exerciselist.html

    }

    // uuden ohjelman luonti (form)
    // @GetMapping("/createprogram")
    // public String createWorkout(Model model) {
    // model.addAttribute("workoutProgram", new WorkoutProgram());
    // return "createprogram"; // createprogram.html
    // }

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
