CREATE TABLE Locations
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(30) UNIQUE NOT NULL
);

CREATE TABLE Companies
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(100) UNIQUE NOT NULL,
    email VARCHAR(100)        NOT NULL,
    phone INT                 NOT NULL
);

CREATE TABLE Buses
(
    id         SERIAL PRIMARY KEY,
    company_id INT NOT NULL,
    number     INT NOT NULL UNIQUE,
    wc         BOOLEAN DEFAULT FALSE,
    ac         BOOLEAN DEFAULT FALSE,
    wifi       BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (company_id) REFERENCES Companies (id)
);

CREATE TABLE Routes
(
    id               SERIAL PRIMARY KEY,
    location_from_id INT           NULL,
    location_to_id   INT           NULL,
    price            DECIMAL(9, 2) NOT NULL,
    bus_id           INT           NOT NULL,
    departure_date   TIMESTAMP     NOT NULL,
    arrival_date     TIMESTAMP     NOT NULL,
    FOREIGN KEY (location_from_id) REFERENCES Locations (id),
    FOREIGN KEY (location_to_id) REFERENCES Locations (id),
    FOREIGN KEY (bus_id) REFERENCES Buses (id)
)

CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100),
    password VARCHAR(100)
)