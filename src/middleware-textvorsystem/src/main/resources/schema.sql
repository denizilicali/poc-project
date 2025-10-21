CREATE TABLE data_table (
    id BIGSERIAL PRIMARY KEY,
    payload VARCHAR(255) NOT NULL,
    created_at TIMESTAMP
);