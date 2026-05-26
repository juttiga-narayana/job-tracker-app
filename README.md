# Job Tracker Full Stack Application

A full-stack Job Tracking platform built using React and Spring Boot that helps users manage and track job applications efficiently. The application includes secure JWT-based authentication, job status management, profile management.

---

## Features

- Secure User Authentication using JWT
- User Registration and Login
- Add, Update, and Delete Job Applications
- Track Job Application Status
- Profile Management
- RESTful API Integration
- Responsive User Interface
- Environment Variable Configuration
- Cloud Deployment Support

---

## Tech Stack

### Frontend

- React
- Vite
- Axios
- CSS

### Backend

- Spring Boot
- Spring Security
- JWT Authentication
- REST APIs

### Database

- MySQL
- TiDB Cloud

### Deployment

- Netlify (Frontend)
- Render (Backend)

---

## Project Structure

```plaintext
JobTrackerApp/
│
├── JobTracker-frontend/
│
└── JobTracker-backend/
```

---

## Environment Variables

### Frontend

```env
VITE_API_URL=your_backend_url
```

### Backend

```env
DATASOURCE_URL=your_database_url
DATASOURCE_USERNAME=your_username
DATASOURCE_PASSWORD=your_password
```

---

## Installation & Setup

### Clone Repository

```bash
git clone https://github.com/juttiga-narayana/job-tracker-app.git
```

---

### Frontend Setup

```bash
cd JobTracker-frontend
npm install
npm run dev
```

---

### Backend Setup

```bash
cd JobTracker-backend
mvn spring-boot:run
```

---

## Live Demo

Application:
https://jobtracker-service.netlify.app/

---

## Future Improvements

- Email Notifications
- Advanced Analytics Dashboard
- Resume Upload Feature
- Search and Filter Functionality
- Role-Based Authorization
- Dark Mode Support

---

## Author

Narayana Juttiga

GitHub:
https://github.com/juttiga-narayana
