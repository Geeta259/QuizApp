# Quiz Application - README

## Overview
This Quiz Application is built using Spring Boot and provides an API for managing and playing quizzes. Below is a detailed explanation of the application features, each function, and how they operate.

---

## Functions and Their Procedures

### 1. **Start a User Session**
**Endpoint:** `POST /api/quiz/create`

- **Description:** Creates a new user session.
- **Steps:**
  1. A new `UserSession` object is created.
  2. It is saved to the database using `userSessionRepository.save(user)`.
  3. Returns the created `UserSession` object.

---

### 2. **Create a Question**
**Endpoint:** `POST /api/quiz/create-question`

- **Description:** Adds a new question to the database.
- **Steps:**
  1. Validates the `Question` object to ensure all fields (text, options, correct) are provided.
  2. Saves the `Question` object to the database using `questionRepository.save(question)`.
  3. Returns the saved `Question` object.

---

### 3. **Fetch a Random Question for a User**
**Endpoint:** `GET /api/quiz/get-question/{userId}`

- **Description:** Retrieves a random question for a given user.
- **Steps:**
  1. Verifies that the user exists using `userSessionRepository.findById(userId)`.
  2. Fetches all questions from the database using `questionRepository.findAll()`.
  3. Selects a random question using `new Random().nextInt(questions.size())`.
  4. Constructs a response with the question details.
  5. Returns the response.

---

### 4. **Submit an Answer**
**Endpoint:** `POST /api/quiz/answer`

- **Description:** Submits an answer for a question and updates the user session stats.
- **Steps:**
  1. Verifies the user exists using `userSessionRepository.findById(id)`.
  2. Fetches the question by ID using `questionRepository.findByQtnId(qtnid)`.
  3. Compares the user's answer with the correct answer.
     - If correct: Updates the `correct` count in the `UserSession`.
     - If incorrect: Updates the `incorrect` count in the `UserSession`.
  4. Updates the total number of questions attempted.
  5. Saves the updated user session to the database.
  6. Constructs a response containing the question, options, and updated stats.
  7. Returns the response.

---

### 5. **Update a Question**
**Endpoint:** `PUT /api/quiz/update-question/{questionId}`

- **Description:** Updates an existing question in the database.
- **Steps:**
  1. Fetches the existing question by ID using `questionRepository.findByQtnId(questionId)`.
  2. Updates the question's `text`, `options`, and `correct` fields with new values.
  3. Saves the updated question to the database using `questionRepository.save(existingQuestion)`.
  4. Returns the updated `Question` object.

---

### 6. **Delete a Question**
**Endpoint:** `DELETE /api/quiz/delete-question/{questionId}`

- **Description:** Deletes a question by its ID.
- **Steps:**
  1. Fetches the question by ID using `questionRepository.findByQtnId(questionId)`.
  2. Deletes the question using `questionRepository.delete(question)`.
  3. Returns a success message.

---

### 7. **Get User Session Statistics**
**Endpoint:** `GET /api/quiz/user/{id}`

- **Description:** Fetches the statistics of a specific user session.
- **Steps:**
  1. Fetches the user session by ID using `userSessionRepository.findById(id)`.
  2. Returns the `UserSession` object containing stats like total, correct, and incorrect answers.

---

## Entities

### **Question**
- Fields:
  - `qtnid`: Unique ID of the question.
  - `text`: Text of the question.
  - `correct`: Correct answer.
  - `options`: List of answer options.

### **UserSession**
- Fields:
  - `id`: Unique session ID.
  - `total`: Total questions attempted.
  - `correct`: Total correct answers.
  - `incorrect`: Total incorrect answers.

---

## Repositories

### **QuestionRepository**
- Custom Query:
  - `findByQtnId(int qtnid)`: Fetches a question by its ID.

### **UserSessionRepository**
- Custom Query:
  - `findById(int id)`: Fetches a user session by its ID.

---

## Technologies Used
- **Spring Boot** for API development.
- **JPA/Hibernate** for ORM.
- **MySQL** as the database.
- **H2 Database** for in-memory testing.
---

### How to Run and Test the Application

1. **Start the Spring Boot Application:**
   - Locate the main class, `QuizApplicationApplication.java`, in project.
   - Run the application using your IDE or execute the `mvn spring-boot:run` command in the terminal.
   - Ensure the application starts successfully and that the server is running.

2. **Setup Postman for API Testing:**
   - Install [Postman](https://www.postman.com/downloads/) if not already available.
 

3. **Test API Endpoints:**

   a. **Create a User Session:**
      - **Method:** `POST`
      - **Endpoint:** `/api/quiz/create`
      - **Description:** Creates a new user session. Copy the `userId` from the response for subsequent requests.

   b. **Add a Question:**
      - **Method:** `POST`
      - **Endpoint:** `/api/quiz/create-question`
      - **Body Example (JSON):**
        ```json
        {
          "text": "What is the capital of France?",
          "options": ["Paris", "London", "Berlin", "Madrid"],
          "correct": "Paris"
        }
        ```

   c. **Fetch a Random Question:**
      - **Method:** `GET`
      - **Endpoint:** `/api/quiz/get-question/{userId}`
      - Replace `{userId}` with the `userId` retrieved earlier.

   d. **Submit an Answer:**
      - **Method:** `POST`
      - **Endpoint:** `/api/quiz/answer`
      - **Body Example (JSON):**
        ```json
        {
          "userId": 1,
          "qtnId": 101,
          "answer": "Paris"
        }
        ```

   e. **Update a Question:**
      - **Method:** `PUT`
      - **Endpoint:** `/api/quiz/update-question/{questionId}`
      - **Body Example (JSON):**
        ```json
        {
          "text": "What is the capital of Germany?",
          "options": ["Paris", "London", "Berlin", "Madrid"],
          "correct": "Berlin"
        }
        ```

   f. **Delete a Question:**
      - **Method:** `DELETE`
      - **Endpoint:** `/api/quiz/delete-question/{questionId}`
      - Replace `{questionId}` with the ID of the question to delete.

   g. **View User Stats:**
      - **Method:** `GET`
      - **Endpoint:** `/api/quiz/user/{id}`
      - Replace `{id}` with the `userId`.

---
### Folder Structure
- **QuizApp/src/main/java/com/quiz/**
  - This folder contains the main application class (`QuizApplicationApplication.java`), controllers, DAOs, entities, and other components required for the project.

---
