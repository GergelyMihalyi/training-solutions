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
  )