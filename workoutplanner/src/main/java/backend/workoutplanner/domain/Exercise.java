package backend.workoutplanner.domain;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Exercise {

    @Id // yksilöivä id / PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long exerciseId;

    private String name;
    private String muscleGroup;

    // jätin tästä nyt vielä pois description ja equipment

    // parametriton konstruktori
    public Exercise() {

    }

    // parametrillinen konstruktori
    public Exercise(String name, String muscleGroup) {
        this.name = name;
        this.muscleGroup = muscleGroup
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
