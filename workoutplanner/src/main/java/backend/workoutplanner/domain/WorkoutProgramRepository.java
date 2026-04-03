package backend.workoutplanner.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface WorkoutProgramRepository extends CrudRepository<WorkoutProgram, Long> {

    List<WorkoutProgram> findByName(String name);
}
