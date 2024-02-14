CREATE TABLE writers(
                        id SERIAL PRIMARY KEY,
                        first_name VARCHAR,
                        last_name VARCHAR,
                        status VARCHAR
);

CREATE TABLE posts(
                      id SERIAL PRIMARY KEY,
                      title VARCHAR,
                      content VARCHAR,
                      status VARCHAR,
                      writer_id BIGINT REFERENCES writers(id)
);

CREATE TABLE labels(
                       id SERIAL PRIMARY KEY,
                       name VARCHAR,
                       status VARCHAR
);




CREATE TABLE post_labels(
                            post_id BIGINT REFERENCES posts(id),
                            label_id BIGINT REFERENCES labels(id),
                            UNIQUE (post_id, label_id)
);