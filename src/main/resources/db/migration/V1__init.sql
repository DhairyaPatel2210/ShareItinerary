CREATE SEQUENCE itinerary_seq;

CREATE TABLE IF NOT EXISTS  itinerary  (
    id bigint NOT NULL primary key,
    name VARCHAR(255) NOT NULL,
    transportation_mode VARCHAR(255) NOT NULL,
    travel_days integer NOT NULL CHECK (travel_days > 0)
);


