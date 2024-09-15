---Adding Location table
CREATE TABLE location (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    longitude DOUBLE PRECISION NOT NULL,
    latitude DOUBLE PRECISION NOT NULL,
    added_on TIMESTAMP DEFAULT NOW(),
    name VARCHAR(255) NOT NULL,
    activity_id UUID
);

--- Adding Image table
CREATE TABLE image (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    link VARCHAR(255) NOT NULL,
    uploaded_on TIMESTAMP DEFAULT NOW(),
    activity_id UUID
);

--- Adding Activity table
CREATE TABLE activity (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    day_id UUID
);

--- Adding Day table
CREATE TABLE day (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    day_no INT NOT NULL CHECK (day_no >= 1),
    date DATE NOT NULL,
    itinerary_id UUID
);

---Adding Location table
CREATE TABLE itinerary (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    summary VARCHAR(255) NOT NULL
);

ALTER TABLE day
ADD CONSTRAINT fk_itinerary_id FOREIGN KEY (itinerary_id) REFERENCES itinerary (id);


ALTER TABLE activity
ADD CONSTRAINT fk_day_id FOREIGN KEY (day_id) REFERENCES day (id);

ALTER TABLE location
ADD CONSTRAINT fk_location_activity FOREIGN KEY (activity_id) REFERENCES activity (id);

ALTER TABLE image
ADD CONSTRAINT fk_image_activity FOREIGN KEY (activity_id) REFERENCES activity (id);





