# ORM, JPA, Hibernate, Migrations, Flyway & Indexes

These notes use the Week 13 Capstone project (`Student`, `Course`, `Enrollment`, the repositories, and `application.yaml`) as the examples.

---

# Part 1: The problem behind all of it

Your Java code thinks in **objects**:

```java
Student s = new Student();
s.setName("David");
s.setRegNumber("CIT/2026/001");
```

PostgreSQL thinks in **tables, rows and columns**:

```
student
| id | name  | reg_number    | gpa | password | role    |
|----|-------|---------------|-----|----------|---------|
| 1  | David | CIT/2026/001  | 4.2 | $2a$...  | STUDENT |
```

They're two different worlds. Something has to translate between them. Without help, you would write this by hand for every operation:

```java
PreparedStatement ps = conn.prepareStatement(
  "INSERT INTO student (name, reg_number, gpa, password, role) VALUES (?, ?, ?, ?, ?)");
ps.setString(1, s.getName());
ps.setString(2, s.getRegNumber());
// ... and then the reverse for SELECT: read each column, build a Student, set every field
```

Now picture doing that for every entity, every query, and every relationship (a Student has many Enrollments, and each Enrollment has a Course). It's a lot of repetitive code that breaks easily. **ORM, JPA and Hibernate exist to remove that pain.**

---

# Part 2: ORM, JPA, Hibernate (three layers of one idea)

### ORM: the *idea*
**Object-Relational Mapping** is a concept, not a library. It's the idea of declaring once how a class maps to a table, and letting software write the SQL.

Analogy: a translator between English and Luganda. "ORM" is the *job* of translating. It isn't a specific person.

### JPA: the *rulebook*
**Jakarta Persistence API** is a **specification**. It's a set of interfaces and annotations that says "this is how ORM must work in Java." All of these come from JPA:

```java
import jakarta.persistence.*;   // ← look at your own imports

@Entity                    // "this class maps to a table"
@Table(name = "student")   // "the table is called student"
@Id                        // "this is the primary key"
@Column(nullable = false)  // "this column is NOT NULL"
@ManyToOne / @OneToMany    // "this is a relationship"
```

JPA by itself **does nothing**. It's only interfaces, like a job description with nobody hired yet.

Analogy: the Highway Code. It says what a car must do (brake, signal, steer), but you can't drive the Highway Code.

### Hibernate: the *worker*
**Hibernate** is the actual library that **implements** JPA. It reads your annotations, generates the SQL, runs it, and turns the rows back into objects. When you see this in your logs (you have `show-sql: true`):

```sql
select s1_0.id, s1_0.name, ... from student s1_0 where s1_0.reg_number=?
```

Hibernate wrote that. You never typed it.

Analogy: Toyota is one car maker that follows the Highway Code. EclipseLink is another JPA implementation (another car maker), but Hibernate is the one almost everyone uses and the default in Spring Boot.

### Spring Data JPA: the *convenience layer on top*
This explains your repositories:

```java
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByRegNumber(String regNumber);
}
```

You wrote **no implementation**. Spring Data reads the method *name* (`findBy` + `RegNumber`), works out what you want, and asks Hibernate to run `SELECT ... WHERE reg_number = ?`.

### Putting the stack together
```
Your code:        studentRepository.findByRegNumber("CIT/001")
                         │
Spring Data JPA:  "findBy RegNumber → a query on regNumber"
                         │
JPA (rules):      the annotations / interfaces that define the mapping
                         │
Hibernate (work): generates SQL, runs it, builds a Student object
                         │
JDBC driver:      the postgresql dependency in your pom.xml sends the SQL over the network
                         │
PostgreSQL:       actually stores the data
```

When you explain it to someone: **"ORM is the idea, JPA is the standard, Hibernate is the implementation, Spring Data JPA saves you from writing repository code."**

---

# Part 3: Migrations

### The problem
Hibernate knows how to *talk* to the `student` table. But **who created that table in the first place?**

Look at your `application.yaml`:

```yaml
jpa:
  hibernate:
    ddl-auto: update
```

This line says: "Hibernate, on startup, look at my `@Entity` classes, compare them with the database, and create or alter tables to match." That's why your tables just *appeared*. It feels like magic, and for learning it's fine.

**But consider what happens over time.** Your app is deployed and the real database holds 3,000 real students. Then:

1. **You rename a field** from `regNumber` to `registrationNumber`.
   `update` doesn't understand "rename". It sees a *new* column, adds `registration_number` (empty), and leaves `reg_number` holding all the old data. Your app now reads empty values. Data looks "lost".

2. **You add a required field** `@Column(nullable = false) private String email;`.
   The table already has 3,000 rows with no email. Adding a NOT NULL column fails or gets skipped. What should the existing rows contain? `update` can't decide that for you.

3. **You delete a field.** `update` never drops columns, so junk piles up forever.

4. **Your teammate's laptop, your laptop, and the server each end up with slightly different schemas**, depending on what code each one happened to run. Nobody can say exactly what the production database looks like.

5. **There's no history.** You can't answer "what did the database look like in version 1.2?"

### The solution: migrations
A **migration** is a small, numbered SQL script that describes **one change** to the database, written once and never edited afterwards.

```
V1__create_student_table.sql
V2__create_course_table.sql
V3__create_enrollment_table.sql
V4__add_email_to_student.sql
V5__rename_reg_number.sql
```

The database keeps a record of which ones it has already run. When the app starts, it runs **only the new ones, in order**.

**The best analogy is Git.** Git doesn't store "the current code". It stores a list of *changes* (commits), and replaying them in order gives you the current code. Migrations do the same for your database schema: replay V1 → V5 on an empty database and you get exactly the production structure, on any machine, every time.

Another analogy from outside programming: renovating a house. You don't knock the house down and rebuild it every time you want a new window (that would be dropping and recreating). You keep a logbook: "Step 1: foundation. Step 2: walls. Step 3: add window to bedroom." A new builder can read the logbook and know exactly what was done and in what order.

Here's how migrations solve the problems above:
- **Rename** → `ALTER TABLE student RENAME COLUMN reg_number TO registration_number;` The data moves with it.
- **New required column** → you decide how existing rows get filled:
  ```sql
  ALTER TABLE student ADD COLUMN email VARCHAR(255);
  UPDATE student SET email = reg_number || '@cit.ac.ug';   -- fill old rows
  ALTER TABLE student ALTER COLUMN email SET NOT NULL;     -- now enforce it
  ```
  A human made that decision. Hibernate never could.

---

# Part 4: Flyway (the tool that runs migrations)

A migration is the *idea* of versioned SQL scripts. **Flyway** is the *tool* that manages them. (Liquibase is a competitor that does the same job.)

### How it works
1. You put SQL files in `src/main/resources/db/migration/`.
2. You name them `V<number>__<description>.sql` (that's **two** underscores).
3. On app startup, Flyway:
   - creates a table called `flyway_schema_history` in your database if it doesn't exist,
   - checks which versions are already recorded there,
   - runs any new files in order,
   - records each one with a checksum.

```
flyway_schema_history
| version | description            | checksum   | success |
|---------|------------------------|------------|---------|
| 1       | create student table   | 129384712  | true    |
| 2       | create course table    | -98234     | true    |
| 3       | create enrollment table| 55123      | true    |
```

**The checksum matters.** If you go back and edit `V1` after it has run, Flyway notices the file changed and **refuses to start**. That's on purpose: history must never be rewritten. If you need a change, you write `V4`.

### "Do I run a command, or do I write the SQL myself?"

**You write the SQL yourself.** That's the honest answer, and it's the point of the approach. Flyway doesn't look at your models at all. It just runs your scripts. You stay in control because *you* know whether something is a rename or a new column, and how old data should be filled.

**You don't need to run a command, though.** In Spring Boot, once Flyway is on the classpath it runs automatically when the app starts. You just start the app.

Some helpers exist (IntelliJ's JPA Buddy plugin, or Hibernate's schema export) that can **generate a draft** SQL from your entities. That's useful for writing V1, but you still read and fix it yourself.

### What V1 would look like for the Capstone project
```sql
-- V1__create_initial_schema.sql
CREATE TABLE student (
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    reg_number  VARCHAR(255) NOT NULL UNIQUE,
    gpa         DOUBLE PRECISION NOT NULL,
    password    VARCHAR(255) NOT NULL,
    role        VARCHAR(50)  NOT NULL
);

CREATE TABLE course (
    id        BIGSERIAL PRIMARY KEY,
    code      VARCHAR(8)   NOT NULL UNIQUE,
    name      VARCHAR(20)  NOT NULL,
    hashauth  VARCHAR(255) NOT NULL
);

CREATE TABLE enrollment (
    id          BIGSERIAL PRIMARY KEY,
    student_id  BIGINT REFERENCES student(id),
    course_id   BIGINT REFERENCES course(id)
);
```

Compare it line by line with your entities: `@Column(nullable = false, length = 100)` → `VARCHAR(100) NOT NULL`, `GenerationType.IDENTITY` → `BIGSERIAL`, `@JoinColumn(name = "student_id")` → `student_id BIGINT REFERENCES student(id)`. **The entity and the SQL describe the same table from two sides.**

### Switching the project over
```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-flyway</artifactId>
</dependency>
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-database-postgresql</artifactId>
</dependency>
```
```yaml
jpa:
  hibernate:
    ddl-auto: validate    # ← changed from update
```

`validate` is the key change. Hibernate **stops creating tables** and only *checks* on startup that the tables Flyway built match your entities. If they don't (say you added a field and forgot the migration), the app refuses to start and tells you. That's a safety net.

**Roles after the switch:** Flyway *builds* the database, and Hibernate *uses* it.

(Since the current database was already created by `update`, the easiest path while learning is a fresh empty database, so Flyway can build it from V1. Flyway also has a `baseline` option for databases that already exist.)

---

# Part 5: Indexes

### What they are
Picture a 900-page textbook with no index at the back, and you need every page that mentions "polymorphism". You have to read **all 900 pages**. With an index, you look up "P → polymorphism → pages 112, 340" and jump straight there.

A database index is exactly that. Without one, when you call:

```java
studentRepository.findByName("David");
// SELECT * FROM student WHERE name = 'David'
```

PostgreSQL does a **sequential scan**: it reads every single row and checks the name. With 50 students you won't notice. With 2 million, the query is painfully slow. An index on `name` is a separate sorted structure (a B-tree) that lets Postgres jump straight to the "David" rows, the way you'd find a word in a sorted dictionary without reading from page one.

### The trade-off (why you don't just index everything)
An index is extra data that has to stay in sync. Every `INSERT`, `UPDATE` or `DELETE` on the table also updates every index on it. A book with an index for every single word would be twice as thick and a nightmare to reprint. So:
- **Reads get faster**
- **Writes get a bit slower and storage grows**

### When to add one: ask "do I search, join or sort by this column often?"
Good candidates are columns used in:
- `WHERE` (your `findByX` methods)
- `JOIN ... ON` (foreign keys)
- `ORDER BY` (sorting, which pagination uses)

Poor candidates: columns you never filter on, tiny tables, or columns with only a couple of possible values (like `role`, which is just STUDENT or ADMIN, so the index barely narrows anything down).

### Checking the Capstone project
| Query in the code | Column | Indexed? |
|---|---|---|
| `findByRegNumber`, `existsByRegNumber` | `reg_number` | ✅ **Already.** `unique = true` makes Postgres create an index automatically |
| looking up a course by code | `course.code` | ✅ Already, because it's also `unique` |
| `findById` | `id` | ✅ Primary keys are always indexed |
| `findByName` | `student.name` | ❌ **No.** This is a full scan |
| `findByGpaGreaterThanEqual` | `gpa` | ❌ No (range queries can use an index too) |
| `JOIN FETCH e.student`, `JOIN FETCH e.course` | `enrollment.student_id`, `course_id` | ❌ **No.** PostgreSQL does **not** index foreign keys automatically. Many people get caught by this one. |

### Where to add them
**Option A, in a Flyway migration (the real one):**
```sql
-- V4__add_indexes.sql
CREATE INDEX idx_student_name        ON student(name);
CREATE INDEX idx_enrollment_student  ON enrollment(student_id);
CREATE INDEX idx_enrollment_course   ON enrollment(course_id);

-- Bonus: a student shouldn't be able to enrol in the same course twice.
-- A unique index on BOTH columns together enforces that rule AND speeds up lookups.
CREATE UNIQUE INDEX uq_enrollment_student_course ON enrollment(student_id, course_id);
```

**Option B, on the entity:**
```java
@Table(name = "student", indexes = @Index(name = "idx_student_name", columnList = "name"))
```
This only *creates* anything when Hibernate is generating the schema (`ddl-auto: update`). Once Flyway is in charge, the migration is what really creates the index, and the annotation is just documentation.

### How to see it working
Run this in psql or pgAdmin:
```sql
EXPLAIN ANALYZE SELECT * FROM student WHERE name = 'David';
```
Before the index you'll see `Seq Scan on student`, and after it `Index Scan using idx_student_name`. On a small table Postgres may still pick a Seq Scan, because reading 20 rows directly is faster. Indexes pay off as tables grow.

---

# Part 6: How it all fits together

```
You write @Entity Student         ← JPA annotations: what the table looks like to Java
        │
You write V1__create_student.sql  ← Flyway migration: actually BUILDS the table (+ indexes)
        │
App starts
   ├─ Flyway runs new migrations, records them in flyway_schema_history
   ├─ Hibernate (ddl-auto: validate) checks the entities match the tables
   └─ Spring Data builds your repository implementations
        │
Request comes in → controller → service → studentRepository.findByName("David")
        │
Hibernate generates SQL → Postgres uses idx_student_name → fast result → Student object
```

**Day-to-day workflow when you add a field later:**
1. Add `private String email;` to `Student.java`
2. Write `V5__add_student_email.sql`
3. Start the app. Flyway applies V5 and Hibernate validates it.
4. Commit **both** files together, so your teammates and the server get the same change.

---

# Deployment readiness notes

- The project already has layered architecture, DTOs, mappers, JWT security and tests. Swapping `ddl-auto: update` for Flyway + `validate` is the main gap between a learning project and a deployable one.
- The DB password and JWT secret are written directly in `application.yaml`. Before deploying, they should come from environment variables, e.g. `password: ${DB_PASSWORD}`.

# Next steps to pick up later

- Add Flyway to the Week 13 project step by step (write the migrations yourself, then get them reviewed).
- Go deeper on: how Hibernate decides when to run SQL (the **persistence context**), and why the `JOIN FETCH` queries exist (the **N+1 problem**).
