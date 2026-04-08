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

@Entity
public class WorkoutProgram {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workoutProgramId;

    private String name;
    private String date;

    // userId (FK)
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    // getterit ja setterit user -entitylle
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // workoutProgramExercise (FK)
    @JsonIgnoreProperties("workoutProgram")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workoutProgram")
    private List<WorkoutProgramExercise> workoutProgramExercises;

    // getterit ja setterit wpe -entitylle
    public List<WorkoutProgramExercise> getWorkoutProgramExercises() {
        return workoutProgramExercises;
    }

    public void setWorkoutProgramExercises(List<WorkoutProgramExercise> workoutProgramExercises) {
        this.workoutProgramExercises = workoutProgramExercises;
    }

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
