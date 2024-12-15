CREATE TABLE Users (
                       id UUID PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       token VARCHAR(255),
                       last_login TIMESTAMP,
                       is_active BOOLEAN DEFAULT true NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       CONSTRAINT unique_email UNIQUE (email)
);

CREATE INDEX idx_user_email ON Users (email);

CREATE TABLE Phones (
                        id UUID PRIMARY KEY,
                        user_id UUID NOT NULL,
                        number VARCHAR(255) NOT NULL,
                        city_code VARCHAR(255) NOT NULL,
                        country_code VARCHAR(255) NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        CONSTRAINT fk_phone_user FOREIGN KEY(user_id) REFERENCES Users(id) ON DELETE CASCADE
);