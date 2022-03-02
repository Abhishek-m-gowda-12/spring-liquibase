package com.abhishek.springliquibase;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication(scanBasePackages = {"com.abhishek.springliquibase"})
public class SpringLiquibaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringLiquibaseApplication.class, args);
    }

    @Bean
    public SpringLiquibase secondaryDatabase(@Qualifier("secondaryDataSource") DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();

        liquibase.setTag("secondary");
        liquibase.setBeanName("secondary");
        liquibase.setChangeLog("classpath:db/changelog/secondary-db.changelog-master.xml");
        liquibase.setDataSource(dataSource);

        return liquibase;
    }

    @Bean
    public SpringLiquibase primaryDatabase(@Qualifier("applicationDataSource") DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();

        liquibase.setTag("primary");
        liquibase.setBeanName("primary");
        liquibase.setChangeLog("classpath:db/changelog/primary-db.changelog-master.xml");
        liquibase.setDataSource(dataSource);

        return liquibase;
    }
}
