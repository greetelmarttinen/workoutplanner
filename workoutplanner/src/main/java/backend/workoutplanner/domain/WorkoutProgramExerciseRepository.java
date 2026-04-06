package backend.workoutplanner.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface WorkoutProgramExerciseRepository extends CrudRepository<WorkoutProgramExercise, Long> {

    List<WorkoutProgramExercise> findByWorkoutProgram(WorkoutProgram workoutProgram);

}
