-- Add admin table to the database
CREATE TABLE IF NOT EXISTS admin (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL
);

-- Insert the single admin (this is the only admin that will exist)
INSERT INTO admin (username, password) VALUES ('Admin', 'MSI@2025');