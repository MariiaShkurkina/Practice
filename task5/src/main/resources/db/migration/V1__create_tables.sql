CREATE TYPE cuisine_type AS ENUM ('EUROPEAN', 'ITALIAN', 'ASIAN', 'RUSSIAN');

CREATE TABLE restaurant (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    cuisine_type cuisine_type NOT NULL,
    average_check NUMERIC(15, 2),
    rating NUMERIC(15, 2) DEFAULT 0
);

CREATE TABLE visitor (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    age INTEGER,
    gender VARCHAR(255)
);

CREATE TABLE review (
    id SERIAL PRIMARY KEY,
    visitor_id INTEGER NOT NULL REFERENCES visitor(id) ON DELETE CASCADE,
    restaurant_id INTEGER NOT NULL REFERENCES restaurant(id) ON DELETE CASCADE,
    rating INTEGER NOT NULL,
    comment TEXT
);
