package org.maxim.RestApi.utils;

import org.hibernate.cfg.Configuration;
import org.flywaydb.core.Flyway;
import java.util.Properties;

public class DataBaseRun {
    public static void migrateDatabase() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        Properties properties = configuration.getProperties();
        Flyway flyway = Flyway.configure()
                .dataSource(properties.getProperty("hibernate.connection.url"),
                        properties.getProperty("hibernate.connection.username"),
                        properties.getProperty("hibernate.connection.password"))
                .locations("filesystem:src/main/resources/db/migration")
                .validateMigrationNaming(true)
                .load();

        flyway.migrate();
    }
}
