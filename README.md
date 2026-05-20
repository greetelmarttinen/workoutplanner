# Workout Planner - Back End Programming Course Project

The goal was to build a simple workout planner application that allows users to create 
their own training programs with just a few clicks. Users can browse a list 
of exercises, add them to a specific program, and define sets, reps, weights, 
dates, and comments. Exercises can also be edited and removed from programs.

## Technologies
- **Language & Framework:** Java, Spring Boot
- **Architecture:** MVC
- **Build tool:** Maven
- **View:** Thymeleaf, Bootstrap
- **Database:** H2, JPA
- **Deployment:** on Render
- **Version control:** Git

## Features
- Create and manage workout programs
- Add exercises to workout programs
- Role-based access control (admin/user)

## User roles
| Role  | Exercises     | Workout programs |
|-------|---------------|------------------|
| Admin |Full CRUD      |Full CRUD + Can see all created programs |
| User  |Read only      |Full CRUD (own programs)|


## Test accounts
| Username | Role |
|----------|------|
| user1    | User |
| user2    | User |
| admin    | Admin |

### See how it works:
https://workoutplanner-wkrw.onrender.com/

> ⚠️ Hosted on Render's free tier – may take ~30 seconds to wake up on first load.

## Future Development Ideas
- **Progress tracking** – collect saved data (weights over time) and visualize 
  it with charts to track progression
- **Admin permissions** – admin can view all programs but may not be able to 
  edit or delete other users' programs
- **User visibility for admin** – show which username a program belongs to 
  (still considering privacy and necessity)
- **Exercise detail page** – each exercise opens its own page with a log of 
  historical data, useful for tracking weight progression over time
- **Expanded exercise library** – more exercises with filtering and search 
  by muscle group
- **External database**


