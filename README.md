# DOnate

DOnate is a full-stack donation assistant that helps students discover trustworthy charities, track their impact, and stay motivated to give. The application delivers curated nonprofit recommendations, a personalized dashboard, and quick links to Every.org’s secure payment pages so users can start donating in minutes.

---

## 📚 Table of Contents
1. [Problem Statement](#-problem-statement)
2. [Solution Overview](#-solution-overview)
3. [Architecture at a Glance](#-architecture-at-a-glance)
4. [Tech Stack](#-tech-stack)
5. [Prerequisites](#-prerequisites)
6. [Quick Start](#-quick-start)
7. [Detailed Setup Guide](#-detailed-setup-guide)
8. [Optional: Taipy Visualization](#-optional-taipy-visualization)
9. [Sample Accounts & Test Data](#-sample-accounts--test-data)
10. [API Overview](#-api-overview)
11. [Troubleshooting & Solutions](#-troubleshooting--solutions)
12. [Project Structure](#-project-structure)
13. [Next Steps](#-next-steps)

---

## ❗ Problem Statement
Students who want to donate often give up because researching reputable organizations takes too long, verifying impact is confusing, and donation portals feel fragmented. Existing platforms rarely personalize recommendations to a donor’s interests, so contributions remain a one-time action instead of a sustained habit.

## ✅ Solution Overview
DOnate simplifies the journey from intention to impact:
- **Personalized discovery** – users pick causes they care about and instantly receive curated Every.org organizations.
- **One-click giving** – direct links to Every.org’s secure checkout pages mean no payment setup inside the app.
- **Impact tracking** – the dashboard shows donation streaks, top organizations, and preference history so users stay motivated.
- **Future-ready architecture** – Spring Boot + React stack makes it easy to plug in leaderboards, notifications, and in-app payments later.

---

## 🏗️ Architecture at a Glance
```
React Frontend (Vite)  ─┬─>   Spring Boot REST API (port 8080)
                       │        ├─ Auth, Preferences, Organizations, History controllers
                       │        ├─ Spring Data JPA + Hibernate
                       │        └─ MySQL 8 database
                       └─>   Every.org Public API (nonprofit catalogue)

Optional: Taipy dashboard (Python) renders historical donation charts on port 5000.
```

---

## 🛠️ Tech Stack
| Layer        | Technologies |
|--------------|--------------|
| Frontend     | React, React Router, Context API, CSS Modules |
| Backend      | Java 17, Spring Boot 3, Spring MVC, Spring Data JPA |
| Database     | MySQL 8, HikariCP connection pooling |
| Integrations | Every.org REST API (nonprofits), Taipy (optional charts) |
| Tooling      | Maven, npm, Node.js 20, Visual Studio Code / Windsurf |

---

## ✅ Prerequisites
1. **Java 17 or 18** (JDK downloaded and `JAVA_HOME` set)
2. **Node.js 20** and **npm** (`node -v`, `npm -v`)
3. **MySQL Server 8** with a user that can create schemas
4. **Python 3.11** (only if you want the Taipy visualization)
5. Git, VS Code/Windsurf (recommended)

---

## ⚡ Quick Start
```bash
# 1. Database (MySQL shell)
mysql> CREATE DATABASE donate;
# update src/main/resources/application.properties with your MySQL credentials

# 2. Backend (new terminal)
cd DOnate-MUJ
./mvnw spring-boot:run        # or: java -jar target/*.jar

# 3. Frontend (second terminal)
cd DOnate-MUJ/site
npm install
npm start                     # http://localhost:3000

# 4. Login with provided test account (see below)
```

If you only need the backend for integration tests, run `./mvnw test`. For a production build use `npm run build` and copy the build into Spring’s `public` folder (already automated via Maven plugin).

---

## 🧭 Detailed Setup Guide

### 1. Configure MySQL
```sql
CREATE DATABASE donate CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'donate_user'@'localhost' IDENTIFIED BY 'StrongPassword123';
GRANT ALL PRIVILEGES ON donate.* TO 'donate_user'@'localhost';
FLUSH PRIVILEGES;
```
Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/donate
spring.datasource.username=donate_user
spring.datasource.password=StrongPassword123
spring.jpa.hibernate.ddl-auto=update
```

### 2. Start the Spring Boot API
```bash
# From repository root
./mvnw clean spring-boot:run   # first run downloads dependencies

# Alternative (requires prior mvnw package)
java -cp target/classes -jar target/Athena-Hacks-3.1.0-SNAPSHOT.jar
```
API will be available at <http://localhost:8080/project/api>.

### 3. Run the React Frontend
```bash
cd site
npm install
npm start
```
Frontend runs on <http://localhost:3000>. CORS is already configured in `SpringBootAPI` to allow this origin.

### 4. Optional: Connect to Every.org API
No additional setup required—the backend already calls Every.org to enrich organization data. The frontend opens Every.org donation pages in a new tab, so no payment keys are stored in this repo.

---

## 📈 Optional: Taipy Visualization
The original hackathon version embedded a Taipy iframe for donation graphs. Running Taipy is optional.

1. Install Python 3.11 and set it as default (`py -3.11` on Windows).
2. Create a virtual environment and install requirements:
   ```bash
   cd DOnate-MUJ
   py -3.11 -m venv .venv
   .venv\Scripts\activate
   pip install -r requirements.txt   # contains taipy, pandas, numpy
   ```
3. Start the Taipy server:
   ```bash
   python main.py
   ```
4. The React dashboard loads the chart from `http://127.0.0.1:5000`. If it’s not running, the dashboard gracefully shows a summary card instead.

---

## 👥 Sample Accounts & Test Data
| Username | Password | Notes |
|----------|----------|-------|
| `tommy`  | `Trojan` | Pre-seeded preferences, donation streak of 3 months |

Organization recommendations and saved causes are stored in MySQL tables (`organizations`, `preferences`, `dates`). Use MySQL Workbench or `SELECT * FROM organizations;` to verify records.

---

## 🔌 API Overview
Base URL: `http://localhost:8080/project/api`

| Endpoint | Method | Purpose |
|----------|--------|---------|
| `/login` | POST | Authenticate user, returns `id`, `username`, `data` |
| `/register` | POST | Create account |
| `/preferences/post` | POST | Fetch/update user cause selections |
| `/organizations/post` | POST | Fetch curated org list for a user |
| `/date/top` | POST | Fetch most donated organization (streak)
| `/date/post` | POST | Retrieve donation history entries |

All endpoints expect/return JSON. Check `src/main/java/webappdev/**` for request/response schemas.

---

## 🛠 Troubleshooting & Solutions

| Problem | Cause | Solution |
|---------|-------|----------|
| `Access denied for user` in Spring logs | Wrong MySQL credentials | Update `application.properties`, ensure DB exists, rerun app |
| `Table 'donate.users' doesn't exist` | Schema not created | Keep `spring.jpa.hibernate.ddl-auto=update` and restart backend |
| Frontend `ERR_CONNECTION_REFUSED` when calling API | Backend not running / wrong port | Start Spring Boot first; ensure CORS origin is `http://localhost:3000` |
| `npm install` fails on Windows due to permissions | PowerShell execution policy | Run PowerShell as Administrator or use `npm install --force` |
| Taipy iframe shows `127.0.0.1 refused to connect` | Python server not running | Launch `python main.py` or hide the iframe (already handled in React) |
| Java version error when running Maven | JDK < 17 | Install JDK 17+ and update `JAVA_HOME` |

If you need to reset the database, drop and recreate the schema: `DROP DATABASE donate; CREATE DATABASE donate;`.

---

## 🗂 Project Structure
```
DOnate-MUJ/
├── src/main/java/webappdev/        # Spring Boot source
│   ├── history/                    # Donation history entities/services
│   ├── login/                      # Auth and user management
│   ├── organizations/              # Organization CRUD + Every.org integration
│   ├── preferences/                # User cause preferences
│   └── SpringBootAPI.java          # Main application + CORS config
├── src/main/resources/
│   └── application.properties      # Database + server configuration
├── site/                           # React frontend
│   ├── src/components/             # NavBar, Footer, cards, etc.
│   ├── src/pages/                  # Dashboard, Login, Preferences, Rec, Home
│   ├── src/styles/                 # CSS modules
│   └── package.json                # Frontend dependencies
├── requirements.txt                # Optional Taipy dependencies
└── README.md                       # You are here
```

---

## 🚀 Next Steps
- **In-app payments**: Integrate Stripe or Razorpay checkout to avoid redirecting to Every.org.
- **Leaderboard & profile stats**: Already scaffolded in the React codebase, ready for backend endpoints.
- **Notification service**: Use Spring Boot schedulers + email provider for streak reminders.
- **AI-powered recommendations**: Replace static Every.org data with ML-based ranking for richer personalization.

---

- Recent enhancements: automation scripts, documentation, optional Taipy fallback (2025 refresh)

Happy building! Reach out or open an issue if you discover setup gaps we can document further.
