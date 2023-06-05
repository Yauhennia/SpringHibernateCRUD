CREATE TABLE IF NOT EXISTS positions (
                          id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                          name varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS employees (
                        id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                        first_name varchar(30) NOT NULL,
                        last_name varchar(30) NOT NULL,
                        position_id int,
                        CONSTRAINT fk_emp_pos FOREIGN KEY (position_id)
                        REFERENCES positions (id)
                        ON DELETE RESTRICT

);

CREATE TABLE IF NOT EXISTS projects (
                         id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                         name varchar(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS employees_projects (
                                employee_id int REFERENCES employees(id) ON DELETE CASCADE,
                                project_id int REFERENCES projects(id) ON DELETE CASCADE,
                                PRIMARY KEY(employee_id, project_id)
);