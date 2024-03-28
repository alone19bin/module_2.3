CREATE TABLE files (
                       id INT PRIMARY KEY,
                       name VARCHAR(50) NOT NULL,
                       file_path VARCHAR(255) NOT NULL,
                       status VARCHAR(7) NOT NULL
);

CREATE TABLE users (
                       id INT   PRIMARY KEY,
                       name VARCHAR(100) UNIQUE NOT NULL,
                       status VARCHAR(7) NOT NULL
);

CREATE TABLE events (
                        id INT  PRIMARY KEY,
                        user_id INT NOT NULL,
                        file_id INT NOT NULL,
                        status VARCHAR(7) NOT NULL,
                        FOREIGN KEY(user_id) REFERENCES users(id),
                        FOREIGN KEY(file_id) REFERENCES files(id)
);