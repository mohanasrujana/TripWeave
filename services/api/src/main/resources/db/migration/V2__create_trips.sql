CREATE TABLE trips (
    id UUID PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    time_zone VARCHAR(64) NOT NULL,
    creator_id UUID NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT trips_name_not_blank
        CHECK (char_length(trim(name)) > 0),

    CONSTRAINT trips_date_range_valid
        CHECK (end_date >= start_date)
);