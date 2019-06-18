# CodeFellowship Application
The application uses localhost:8080 to display info about the fellowship. The main page also allows the user to sign up or login. Other routes available:
1. /signup - page for user sign up.
2. /users/{id} - page displayed after signing up. Displays basic info.
3. /login - page that allows user to log-in to the site.
4. /myprofile - page that displays the user info.

## To run the application
1. Clone the repo and go to the directory. On the terminal, run `./gradlew bootRun`
2. Open your web browser and go to localhost:8080

## Source Code
* [CodeFellowship classes](./src/main/java/com.fernj.lab401.codefellowship)
* [Integration Tests](./src/test/java/com.fernj.lab401.codefellowship)