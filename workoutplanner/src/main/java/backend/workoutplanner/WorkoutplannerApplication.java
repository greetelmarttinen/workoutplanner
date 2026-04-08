package backend.workoutplanner;

import backend.workoutplanner.domain.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.workoutplanner.domain.Exercise;
import backend.workoutplanner.domain.ExerciseRepository;
import backend.workoutplanner.domain.User;
import backend.workoutplanner.domain.WorkoutProgram;
import backend.workoutplanner.domain.WorkoutProgramExercise;
import backend.workoutplanner.domain.WorkoutProgramExerciseRepository;
// import backend.workoutplanner.domain.WorkoutProgramRepository;
import backend.workoutplanner.domain.WorkoutProgramRepository;

@SpringBootApplication
public class WorkoutplannerApplication {

	private final UserRepository userRepository;
	private static final Logger log = LoggerFactory.getLogger(WorkoutplannerApplication.class);

	WorkoutplannerApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(WorkoutplannerApplication.class, args);
		log.info("Application started");
	}

	// testidata
	@Bean
	public CommandLineRunner createDemoRows(ExerciseRepository eRepo, WorkoutProgramExerciseRepository wpeRepo,
			WorkoutProgramRepository wpRepo) {
		return (args) -> {

			log.info("Save some sample exercises");
			Exercise e1 = new Exercise("Bicep curl", "Biceps");
			eRepo.save(e1);
			Exercise e2 = new Exercise("Military press", "Shoulders");
			eRepo.save(e2);
			Exercise e3 = new Exercise("Squat", "Quads, glutes, hamstrings");
			eRepo.save(e3);

			log.info("Save some workouts (including exercises)");
			wpeRepo.save(new WorkoutProgramExercise("12, 10, 10, 8", "10, 12, 12, 15", "20.3.2026", "Hyvin meni"));

			log.info("Save some sample workouts");
			wpRepo.save(new WorkoutProgram("Leg day 1", "1.4.2026"));
			wpRepo.save(new WorkoutProgram("Upper body", "1.4.2026"));
			wpRepo.save(new WorkoutProgram("Leg day 2", "1.4.2026"));

			// käyttäjien luonti
			User user1 = new User("user1", "$2a$10$UURCCD.X2b/EQvadUPaR3.tWDH5P6pQ2fQ8MG.sr1RkWatQZ6pq4W",
					"user1@email.com", "USER");
			User user2 = new User("admin", "$2a$10$8TKtqPsy5F.4xSYinVNrh.zDy9yQ.pJUCGwSz9dt6GbCwGo3T9te.",
					"admin@email.com", "ADMIN");
			User user3 = new User("user2", "$2a$10$PhYsDhM7A3QHlFT/uzkZpO6R918gl4Cj23yiEscxsVPF9hMlB1g6K",
					"user2@email.com", "USER");
			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);
		};
	}

}
