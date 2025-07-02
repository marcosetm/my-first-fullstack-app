# Vacation Planner Web App

The Vacation Planner Web App is a full-stack application that allows users to manage their vacation plans and associated excursions. Built with Angular on the frontend and Spring Boot on the backend, it uses PostgreSQL for data persistence.

---

## Getting Started

Follow the steps below to run the app locally.

### 1. Clone the Repository

Clone the working branch of the repository to your local machine:

```bash
git clone -b working https://gitlab.com/wgu-gitlab-environment/student-repos/mmarti58/d424-software-engineering-capstone.git
cd d424-software-engineering-capstone
```

---

### 2. Set Up PostgreSQL Server Using Docker

Make sure Docker is installed and running. Then, in the terminal, run:

```bash
docker run --name vacation-planner-db \
  -e POSTGRES_USER=devuser \
  -e POSTGRES_PASSWORD=devpass \
  -e POSTGRES_DB=vacation_planner_db \
  -p 5432:5432 \
  -d postgres
```

This command will create and start a PostgreSQL container on port `5432`.

---

### 3. Confirm PostgreSQL Is Running

Verify that the container is running:

```bash
docker ps
```

You should see `vacation-planner-db` listed.

---

### 4. Run the Backend

Open the `/backend` directory in your preferred Java IDE (such as IntelliJ IDEA). Ensure that your `application.properties` is configured to connect to the PostgreSQL server with the credentials:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/vacation_planner
spring.datasource.username=devuser
spring.datasource.password=devpass
```

Then run the Spring Boot application (`VacationPlannerApplication.java`).

---

### 5. Run the Frontend

Open the `/frontend/vacation-planner-frontend` directory in a TypeScript IDE such as VS Code.

In the terminal, install dependencies and start the Angular dev server:

```bash
npm install
ng serve
```

The frontend will be available at `http://localhost:4200`.

---

## Tech Stack

* **Frontend:** Angular, TypeScript
* **Backend:** Java, Spring Boot
* **Database:** PostgreSQL (Docker)
* **Tools:** Docker, IntelliJ, VS Code

---

## License

This project is for educational and demonstration purposes.
