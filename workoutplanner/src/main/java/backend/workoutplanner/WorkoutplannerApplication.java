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
			WorkoutProgramRepository wpRepo, UserRepository userRepo) {
		return (args) -> {

			// käyttäjien luonti
			log.info("Save some sample users");
			User user1 = new User("user1", "$2a$10$UURCCD.X2b/EQvadUPaR3.tWDH5P6pQ2fQ8MG.sr1RkWatQZ6pq4W",
					"user1@email.com", "USER");
			User user2 = new User("admin", "$2a$10$8TKtqPsy5F.4xSYinVNrh.zDy9yQ.pJUCGwSz9dt6GbCwGo3T9te.",
					"admin@email.com", "ADMIN");
			User user3 = new User("user2", "$2a$10$PhYsDhM7A3QHlFT/uzkZpO6R918gl4Cj23yiEscxsVPF9hMlB1g6K",
					"user2@email.com", "USER");
			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);

			// ohjelmia käyttäjille
			log.info("Save some sample workouts for users");

			WorkoutProgram legDay1 = wpRepo.save(new WorkoutProgram("Leg day 1", "1.4.2026", user1));
			WorkoutProgram upperBody = wpRepo.save(new WorkoutProgram("Upper body", "1.4.2026", user1));
			WorkoutProgram legDay2 = wpRepo.save(new WorkoutProgram("Leg day 2", "1.4.2026", user1));

			WorkoutProgram fullBody1 = wpRepo.save(new WorkoutProgram("Full body workout 1", "11.4.2026", user3));
			WorkoutProgram fullBody2 = wpRepo.save(new WorkoutProgram("Full body workout 2", "11.4.2026", user3));

			// liikkeitä
			log.info("Save some sample exercises");
			Exercise e1 = new Exercise("Bicep curl", "Biceps");
			eRepo.save(e1);
			Exercise e2 = new Exercise("Military press", "Shoulders");
			eRepo.save(e2);
			Exercise e3 = new Exercise("Bench press", "Chest");
			eRepo.save(e3);
			Exercise e4 = new Exercise("Pull-up", "Back");
			eRepo.save(e4);
			Exercise e5 = new Exercise("Seated row", "Back");
			eRepo.save(e5);
			Exercise e6 = new Exercise("Lateral raises", "Shoulders");
			eRepo.save(e6);
			Exercise e7 = new Exercise("Tricep pushdown", "Triceps");
			eRepo.save(e7);
			Exercise e8 = new Exercise("Squat", "Quads, glutes");
			eRepo.save(e8);
			Exercise e9 = new Exercise("Leg press", "Quads");
			eRepo.save(e9);
			Exercise e10 = new Exercise("Leg curl", "Hamstrings");
			eRepo.save(e10);
			Exercise e11 = new Exercise("Leg extension", "Quads");
			eRepo.save(e11);

			log.info("Save some workouts (including exercises)");
			// leg day 1
			WorkoutProgramExercise wpe2 = new WorkoutProgramExercise(
					"10, 8, 6", "25, 30, 40", "1.4.2026", "");
			wpe2.setWorkoutProgram(legDay1);
			wpe2.setExercise(e11);
			wpeRepo.save(wpe2);

			WorkoutProgramExercise wpe3 = new WorkoutProgramExercise(
					"10, 10, 10", "50, 55, 65", "1.4.2026", "Narrow stance");
			wpe3.setWorkoutProgram(legDay1);
			wpe3.setExercise(e9);
			wpeRepo.save(wpe3);

			WorkoutProgramExercise wpe4 = new WorkoutProgramExercise(
					"8, 8, 6", "15, 15, 20", "1.4.2026", "");
			wpe4.setWorkoutProgram(legDay1);
			wpe4.setExercise(e10);
			wpeRepo.save(wpe4);

			// upper body
			WorkoutProgramExercise wpe5 = new WorkoutProgramExercise(
					"10, 10, 10", "12, 12, 15", "1.4.2026", "");
			wpe5.setWorkoutProgram(upperBody);
			wpe5.setExercise(e2);
			wpeRepo.save(wpe5);

			WorkoutProgramExercise wpe6 = new WorkoutProgramExercise(
					"10, 10, 10", "15, 15, 15", "1.4.2026", "");
			wpe6.setWorkoutProgram(upperBody);
			wpe6.setExercise(e3);
			wpeRepo.save(wpe6);

			WorkoutProgramExercise wpe7 = new WorkoutProgramExercise(
					"12, 10, 8", "Body weight", "1.4.2026", "");
			wpe7.setWorkoutProgram(upperBody);
			wpe7.setExercise(e4);
			wpeRepo.save(wpe7);

			WorkoutProgramExercise wpe8 = new WorkoutProgramExercise(
					"12, 12, 12", "15, 15, 20", "1.4.2026", "");
			wpe8.setWorkoutProgram(upperBody);
			wpe8.setExercise(e7);
			wpeRepo.save(wpe8);

			// leg day 2
			WorkoutProgramExercise wpe1 = new WorkoutProgramExercise(
					"10, 8, 6", "20, 25, 30", "1.4.2026", "Wide stance squat");
			wpe1.setWorkoutProgram(legDay2);
			wpe1.setExercise(e8);
			wpeRepo.save(wpe1);

			WorkoutProgramExercise wpe9 = new WorkoutProgramExercise(
					"10, 8, 6", "25, 30, 40", "1.4.2026", "");
			wpe9.setWorkoutProgram(legDay2);
			wpe9.setExercise(e11);
			wpeRepo.save(wpe9);

			// full body 1
			WorkoutProgramExercise wpe10 = new WorkoutProgramExercise(
					"10, 8, 6", "5, 7, 10", "11.4.2026", "DB curl");
			wpe10.setWorkoutProgram(fullBody1);
			wpe10.setExercise(e1);
			wpeRepo.save(wpe10);

			WorkoutProgramExercise wpe11 = new WorkoutProgramExercise(
					"10, 8, 6 + drop set", "5, 7, 12 + 10", "11.4.2026", "Drop set");
			wpe11.setWorkoutProgram(fullBody1);
			wpe11.setExercise(e6);
			wpeRepo.save(wpe11);

			WorkoutProgramExercise wpe12 = new WorkoutProgramExercise(
					"10, 8, 8", "20, 25, 25", "11.4.2026", "Smith machine");
			wpe12.setWorkoutProgram(fullBody1);
			wpe12.setExercise(e8);
			wpeRepo.save(wpe12);

			// full body 2
			WorkoutProgramExercise wpe13 = new WorkoutProgramExercise(
					"10, 10, 10", "50, 55, 65", "11.4.2026", "Wide stance");
			wpe13.setWorkoutProgram(fullBody2);
			wpe13.setExercise(e9);
			wpeRepo.save(wpe13);

			WorkoutProgramExercise wpe14 = new WorkoutProgramExercise(
					"10, 10, 10", "20, 25, 30", "11.4.2026", "Narrow grip");
			wpe14.setWorkoutProgram(fullBody2);
			wpe14.setExercise(e5);
			wpeRepo.save(wpe14);

			WorkoutProgramExercise wpe15 = new WorkoutProgramExercise(
					"10, 10, 10", "12, 12, 15", "11.4.2026", "");
			wpe15.setWorkoutProgram(fullBody2);
			wpe15.setExercise(e2);
			wpeRepo.save(wpe15);

		};
	}

}
