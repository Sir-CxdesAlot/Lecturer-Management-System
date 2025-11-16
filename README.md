# Lecturer Management System

A small Java desktop application built as a learning project by Tayo (CS student). This Lecturer Management System demonstrates a simple desktop UI, a relational database backend, and common application patterns (MVC + DAO). It was created to learn and show hands-on skills in building a CRUD-style management app.

**Highlights**
- **Author:** Tayo (student project)
- **Purpose:** Learn and demonstrate Java desktop development, database integration, and design patterns
- **Status:** Educational project — updates will be added over time as new concepts are learned

**Features**
- **Lecturer management:** Add, view, update, and delete lecturer records
- **Course catalog:** View courses and which lecturers teach them
- **Faculty views:** See lecturers grouped by faculty
- **Authentication:** Basic admin login and password reset screens (demo)
- **SQL scripts included:** Database schema and sample admin/lecturer data in the `database/` folder

**Tech / Tools Used**
- **Language:** Java (JDK 21 used during development)
- **UI:** JavaFX with FXML files (UI screens live in `src/View` and `View`)
- **Database:** Relational SQL (SQL scripts in `database/` and `database/*.sql`)
- **Architecture:** MVC + DAO pattern (see `src/Model`, `src/Controller`, `src/Data`)
- **IDE / Runtime:** Developed and run from IDEs like Eclipse or VS Code; uses JDBC for DB access
- **Version control:** Git

**Project Structure (quick)**
- `src/Controller` : Application entry and controllers (run `Controller.Main` to start the app)
- `src/Model`      : Domain models (e.g., `Lecturer.java`)
- `src/Data`       : DAO classes and SQL helper code
- `database/`      : `schema.sql`, `admin.sql`, `lecturer.sql` — scripts to create and populate the DB
- `View` / `src/View`: JavaFX FXML view files and `style.css`

How to run (quick)
- Ensure a Java JDK (21 recommended) is installed and your IDE is configured.
- Prepare the database by running the SQL scripts in the `database/` folder. Example (MySQL):

```
mysql -u <user> -p < database/schema.sql
mysql -u <user> -p < database/admin.sql
```

- Update database connection settings in `src/Util/DatabaseConnection.java` to match your DB credentials.
- Open the project in your IDE and run the `Controller.Main` class (or run from command line with the proper classpath and JavaFX modules).

Notes & future updates
- This was built as a learning exercise — expect improvements over time (better validation, tests, packaging, and richer backend features).
- If you want, I can add an automated build/run script, a Docker-based database setup, or CI-friendly tests next.

License & contact
- This repository is available to review and learn from. Reach out to Tayo for questions or demos.

Enjoy exploring the code — more updates coming as new concepts are learned.

**Special Thanks**
- **Mr. Paul** : Thank you for your mentorship and guidance throughout this project.