package backend.workoutplanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.workoutplanner.domain.Exercise;
import backend.workoutplanner.domain.ExerciseRepository;
import backend.workoutplanner.domain.WorkoutProgramExercise;
import backend.workoutplanner.domain.WorkoutProgramExerciseRepository;
// import backend.workoutplanner.domain.WorkoutProgramRepository;

@SpringBootApplication
public class WorkoutplannerApplication {

	private static final Logger log = LoggerFactory.getLogger(WorkoutplannerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WorkoutplannerApplication.class, args);
		log.info("Application started");
	}

	// testidata
	@Bean
	public CommandLineRunner createDemoRows(ExerciseRepository eRepo, WorkoutProgramExerciseRepository wpeRepo) {
		return (args) -> {

			log.info("Save some sample exercises");
			Exercise e1 = new Exercise("Bicep curl", "Biceps");
			eRepo.save(e1);
			Exercise e2 = new Exercise("Military press", "Shoulders");
			eRepo.save(e2);
			Exercise e3 = new Exercise("Squat", "Quads, glutes, hamstrings");
			eRepo.save(e3);

			log.info("Save some workouts (including exercises)");
			wpeRepo.save(new WorkoutProgramExercise("4", "12, 10, 10, 8", "20.3.2026", "Hyvin meni"));

		};
	}

}
