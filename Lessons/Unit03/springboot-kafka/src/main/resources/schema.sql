CREATE TABLE AUTHOR (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        biography VARCHAR(1000)
);

CREATE TABLE BOOK (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      description VARCHAR(1000),
                      author_id BIGINT,
                      FOREIGN KEY (author_id) REFERENCES AUTHOR(id)
);