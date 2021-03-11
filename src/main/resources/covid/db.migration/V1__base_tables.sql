create table Citizens (id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(200) NOT NULL,
  zip CHAR(4) NOT NULL,
  age BIGINT NOT NULL,
  email VARCHAR(200) NOT NULL,
  taj CHAR(9) NOT NULL,
  number_of_vaccination TINYINT UNSIGNED DEFAULT 0,
  last_vaccination DATETIME);

  create table CITIES (id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  zip CHAR(4) NOT NULL,
    name VARCHAR(40) NOT NULL);


    create table Vaccinations (id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    citizen_id BIGINT UNSIGNED NOT NULL NOT NULL,
    vaccination_date DATETIME,
    status ENUM('FIRT_VACCINATED','VACCINATED','NOT_VACCINATED','REFUSED'),
    note VARCHAR(200),
    vaccination_type ENUM('Pfizer-BioNtech','Moderna','AstraZeneca','Szputynik','Sinopharm'),
    CONSTRAINT `fk_citizen`
        FOREIGN KEY (citizen_id) REFERENCES Citizens (id)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
    );