CREATE TABLE summary
(
    id      SERIAL PRIMARY KEY,
    user_id VARCHAR(511) NOT NULL,
    CREATE TABLE summary (
        id SERIAL PRIMARY KEY,
        user_id VARCHAR (511) NOT NULL,
        url VARCHAR (511) NOT NULL,
        title VARCHAR (511) NOT NULL,
        text VARCHAR (10000) NOT NULL,
        date TIMESTAMP NOT NULL DEFAULT NOW()
        );