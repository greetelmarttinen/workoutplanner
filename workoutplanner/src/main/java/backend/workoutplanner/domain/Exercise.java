package backend.workoutplanner.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Exercise {

    @Id // yksilöivä id / PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long exerciseId;

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @Size(max = 100)
    private String muscleGroup;

    // jätin tästä nyt vielä pois description ja equipment

    // FK workoutprogram -entityyn
    @ManyToOne // suhde WorkoutProgram -entityyn
    @JoinColumn(name = "workoutProgramId")
    private WorkoutProgram workoutProgram;

    // getterit ja setterit wp -entityyn
    public WorkoutProgram getWorkoutProgram() {
        return workoutProgram;
    }

    public void setWorkoutProgram(WorkoutProgram workoutProgram) {
        this.workoutProgram = workoutProgram;
    }

    // FK wpe -entityyn
    @JsonIgnoreProperties("exercise")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exercise")
    private List<WorkoutProgramExercise> workoutProgramExercises;

    // getterit ja setterit wpe -entityyn
    public List<WorkoutProgramExercise> getWorkoutProgramExercises() {
        return workoutProgramExercises;
    }

    public void setWorkoutProgramExercises(List<WorkoutProgramExercise> workoutProgramExercises) {
        this.workoutProgramExercises = workoutProgramExercises;
    }

    // parametriton konstruktori
    public Exercise() {

    }

    // parametrillinen konstruktori
    public Exercise(String name, String muscleGroup) {
        this.name = name;
        this.muscleGroup = muscleGroup;
    }

    // getterit ja setterit
    public Long getExerciseId() {
        return exerciseId;
    }

    public String getName() {
        return name;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    // toString
    @Override
    public String toString() {
        return "Exercise [exerciseId=" + exerciseId + ", name=" + name + ", muscleGroup=" + muscleGroup + "]";
    }

}
