package backend.workoutplanner.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class WorkoutProgramExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workoutPExerciseId;

    @NotBlank(message = "Reps is required")
    private String reps;

    // painot voi olla vielä tyhjänä, koska ei välttämättä tiedä vielä millä
    // painoilla treenaa
    private String weights;
    private String date;
    private String comments;

    // workoutProgramId (FK)
    @ManyToOne
    @JoinColumn(name = "workoutProgramId")
    private WorkoutProgram workoutProgram;

    // getterit ja setterit wp -entityyn
    public WorkoutProgram getWorkoutProgram() {
        return workoutProgram;
    }

    public void setWorkoutProgram(WorkoutProgram workoutProgram) {
        this.workoutProgram = workoutProgram;
    }

    // exerciseId (FK)
    // @NotNull(message = "Exercise is required")
    @ManyToOne
    @JoinColumn(name = "exerciseId")
    private Exercise exercise;

    // getterit ja setterit exercise -entityyn
    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    // parametriton konstruktori
    public WorkoutProgramExercise() {

    }

    // parametrillinen konstruktori
    public WorkoutProgramExercise(String reps, String weights, String date, String comments) {
        this.weights = weights;
        this.reps = reps;
        this.date = date;
        this.comments = comments;
    }

    // getterit
    public Long getWorkoutPExerciseId() {
        return workoutPExerciseId;
    }

    public String getWeights() {
        return weights;
    }

    public String getReps() {
        return reps;
    }

    public String getDate() {
        return date;
    }

    public String getComments() {
        return comments;
    }

    // setterit
    public void setWorkoutPExerciseId(Long workoutPExerciseId) {
        this.workoutPExerciseId = workoutPExerciseId;
    }

    public void setWeights(String weights) {
        this.weights = weights;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    // toString
    @Override
    public String toString() {
        return "WorkoutProgramExercise [workoutPExerciseId=" + workoutPExerciseId + ", weights=" + weights + ", reps="
                + reps
                + ", date=" + date + ", comments=" + comments + "]";
    }

}
