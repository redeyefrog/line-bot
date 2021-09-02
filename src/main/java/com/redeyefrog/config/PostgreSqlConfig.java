package com.redeyefrog.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@EnableJpaRepositories(basePackages = {"com.redeyefrog.persistence.dao"}, entityManagerFactoryRef = "getEntityManagerFactory")
@Configuration
public class PostgreSqlConfig {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSource = DataSourceBuilder.create();

        dataSource.driverClassName("org.postgresql.Driver");

        dataSource.url(System.getenv("JDBC_DATABASE_URL"));

        dataSource.username(System.getenv("JDBC_DATABASE_USERNAME"));

        dataSource.password(System.getenv("JDBC_DATABASE_PASSWORD"));

        return dataSource.build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(EntityManagerFactoryBuilder factory) {

        return factory.dataSource(getDataSource()).packages("com.redeyefrog.persistence.entity").properties(getProperties()).build();
    }

    private Map<String, Object> getProperties() {
        Map<String, Object> props = new HashMap<>();

        props.put("hibernate.format_sql", true);

        props.put("hibernate.jdbc.lob.non_contextual_creation", true);

        return props;
    }

}
