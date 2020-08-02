CREATE TABLE Locations
(
    id   INT IDENTITY (1, 1),
    name VARCHAR(30) UNIQUE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Companies
(
    id    INT IDENTITY (1, 1) PRIMARY KEY,
    name  VARCHAR(100) UNIQUE NOT NULL,
    email VARCHAR(100)        NOT NULL,
    phone INT                 NOT NULL
);

CREATE TABLE Buses
(
    id         int IDENTITY (1, 1) PRIMARY KEY,
    company_id INT NOT NULL,
    number     INT NOT NULL UNIQUE,
    wc         BIT NOT NULL DEFAULT 0,
    ac         BIT NOT NULL DEFAULT 0,
    wifi       BIT NOT NULL DEFAULT 0,
    FOREIGN KEY (company_id) REFERENCES Companies (id)
)

-- CREATE TABLE Routes
-- (
--     id               INT IDENTITY (1, 1) PRIMARY KEY,
--     location_from_id INT            NULL,
--     location_to_id   INT            NULL,
--     price            DECIMAL(4, 2)  NOT NULL,
--     bus_id           INT            NOT NULL,
--     departure_date   DATETIMEOFFSET NOT NULL,
--     arrival_date     DATETIMEOFFSET NOT NULL,
--     FOREIGN KEY (location_from_id) REFERENCES Locations (id),
--     FOREIGN KEY (location_to_id) REFERENCES Locations (id),
--     FOREIGN KEY (bus_id) REFERENCES Buses (id)
-- )