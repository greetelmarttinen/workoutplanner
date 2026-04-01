package backend.workoutplanner.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WorkoutProgramExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workoutPExerciseId;

    private String sets;
    private String reps;
    private String date;
    private String comments;

    // workoutProgramId (FK)
    // exerciseId (FK)

    // parametriton konstruktori
    public WorkoutProgramExercise() {

    }

    // parametrillinen konstruktori
    public WorkoutProgramExercise(String sets, String reps, String date, String comments) {
        this.sets = sets;
        this.reps = reps;
        this.date = date;
        this.comments = comments;
    }

    // getterit
    public Long getWorkoutPExerciseId() {
        return workoutPExerciseId;
    }

    public String getSets() {
        return sets;
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

    public void setSets(String sets) {
        this.sets = sets;
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
        return "WorkoutProgramExercise [workoutPExerciseId=" + workoutPExerciseId + ", sets=" + sets + ", reps=" + reps
                + ", date=" + date + ", comments=" + comments + "]";
    }

}
