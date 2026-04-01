package backend.workoutplanner.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WorkoutProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workoutProgramId;

    private String name;
    private String date;

    // user (FK)
    // workoutProgramExercise (FK)

    // prametriton konstruktori
    public WorkoutProgram() {

    }

    // parametrillinen konstruktori
    public WorkoutProgram(String name, String date) {
        this.name = name;
        this.date = date;
    }

    // getterit
    public Long getWorkoutProgramId() {
        return workoutProgramId;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    // setterit
    public void setWorkoutProgramId(Long workoutProgramId) {
        this.workoutProgramId = workoutProgramId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // toString
    @Override
    public String toString() {
        return "WorkoutProgram [workoutProgramId=" + workoutProgramId + ", name=" + name + ", date=" + date + "]";
    }

}
