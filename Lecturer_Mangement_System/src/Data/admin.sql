CREATE TABLE IF NOT EXISTS admin (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- Delete existing admin entries and insert the default admin
DELETE FROM admin WHERE username = 'Admin';
INSERT INTO admin (username, password) VALUES ('Admin', 'MSI@2025');