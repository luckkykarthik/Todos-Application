# Todo App Documentation

## Technologies Used

### Frontend
- ReactJS: A JavaScript library for building user interfaces.
- HTML: HyperText Markup Language for structuring web content.
- CSS: Cascading Style Sheets for styling web pages.
- JavaScript: A programming language for enhancing interactivity on the client-side.
- Bootstrap: A front-end framework for designing responsive and mobile-first websites.

### Backend
- Spring Boot: A framework for building Java-based, production-grade Spring applications.
- Spring Security: Provides security services for Java EE-based enterprise software applications.
- Hibernate: An object-relational mapping (ORM) framework for Java applications.
- MySQL: A relational database management system.

## How to Run Locally

### Frontend

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/luckkykarthik/Todos-Application.git
   cd Todos-Application/frontend
   ```

2. **Install Dependencies:**
   ```bash
   npm install
   ```

3. **Start the Development Server:**
   ```bash
   npm start
   ```
   The application will be accessible at `http://localhost:3000`.

### Backend

1. **Navigate to the Backend Directory:**
   ```bash
   cd ../backend
   ```

2. **Configure Database:**
   - Update the database configurations in `src/main/resources/application.properties`.

3. **Build and Run the Spring Boot Application:**
   ```bash
   ./mvnw spring-boot:run
   ```
   The backend server will start at `http://localhost:8080`.

4. **Access the Todo App:**
   Open your web browser and go to `http://localhost:3000` to use the Todo application locally.

## Additional Notes

- Ensure that you have Node.js and npm installed for the frontend.
- Make sure MySQL is installed and running with the appropriate database configurations for the backend.

Feel free to customize the configurations according to your environment.
