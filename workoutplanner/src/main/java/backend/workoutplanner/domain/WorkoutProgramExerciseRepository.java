package backend.workoutplanner.domain;

import org.springframework.data.repository.CrudRepository;

public interface WorkoutProgramExerciseRepository extends CrudRepository<WorkoutProgramExercise, Long> {
    // tähän pitäis lisätä joku crud toiminto, mil löytyis ne liikkee jo
    // pitäiskö se olla findbyname?

}
