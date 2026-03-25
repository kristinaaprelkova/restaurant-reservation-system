# Restaurant Reservation Application

This project is a full-stack web application for restaurant table reservations.

### It allows users to:

- choose number of guests
- select date, time, and zone
- see available tables on a map
- select a table
- make a reservation


### Technologies

* Backend
* Java 25
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Gradle
* Frontend
* Vue 3
* JavaScript
* CSS


### How to run the application

1. Clone project
   git clone <your-repository-link>
   cd restaurant-reservation
2. Start backend
   cd backend
   ./gradlew bootRun

Backend will run on:

http://localhost:8080
3. Start frontend

Open new terminal:

cd frontend
npm install
npm run dev

Frontend will run on:

http://localhost:5173

4. Database setup

Make sure PostgreSQL is running.

Create database:

CREATE DATABASE restaurant_reservation;

Check application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/restaurant_reservation
spring.datasource.username=postgres
spring.datasource.password=student123

Tables will be created automatically (ddl-auto=update).

Initial tables are inserted via DataInitializer.

### Functionality
Table filtering

# Tables are filtered based on:

Guest count (recommended table sizes)
- Date
- Zone
- Time (excludes reserved tables)
- 
# Table recommendation logic
1–3 guests → tables for 2 or 4
4–5 guests → tables for 4 or 6
6–7 guests → tables for 6 or 8
8+ guests → tables for 8 or 10

### Time spent ~30–35 hours

### Challenges
1. Frontend ↔ Backend communication

It was initially difficult to correctly pass data between:

ReservationForm
TableMap
ReservationView

Solution:

Used Vue props + emit
Centralized state in ReservationView

2. Table filtering logic

Initially filtering was simple (seats >= guestCount), but task required more complex logic.

Solution:

Implemented custom recommendation logic in backend service

3. Selecting and saving table

Problem:

How to send selected table to backend

Solution:

Passed table ID from frontend
Loaded table entity in backend before saving

### External help

During development, I used:

ChatGPT (AI assistance for debugging, architecture, and code generation)
VALI IT course materials (general issues and solutions, architecture, and code generation)

All generated code was reviewed and adapted manually.

### Possible improvements

- show occupied tables in red
- disable unavailable tables
- add reservation duration
- add user authentication
- admin panel for managing tables
- better responsive UI


