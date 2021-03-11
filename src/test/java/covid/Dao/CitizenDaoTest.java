package covid.Dao;

import covid.Models.Citizen;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import static org.junit.jupiter.api.Assertions.*;

class CitizenDaoTest {
    private CitizenDao citizenDao;
    @BeforeEach
    public void init(){
        MariaDbDataSource dataSource = new DatabaseConnection().getDataSource();

        Flyway flyway = Flyway.configure()
                .locations("filesystem:./src/main/resources/covid/db.migration")
                .dataSource(dataSource)
                .load();
        flyway.clean();
        flyway.migrate();
        citizenDao = new CitizenDao();
    }

    @Test
    public void testInsert() {
        Citizen citizen = new Citizen("Test Citizen", "zip", 23, "email", "taj");
        citizenDao.saveCitizen(citizen);
        assertEquals("Test Citizen",citizenDao.findCitizenByTaj("taj").getName());
    }

}