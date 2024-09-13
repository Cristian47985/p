package com.example.cqrs.reader.configuration;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.cqrs.reader.repositories",
        entityManagerFactoryRef = "readEntityManagerFactory",
        transactionManagerRef = "readTransactionManager"
)

public class ReadDataSourceConfig {

        @Value("${spring.jpa.datasource-platform}")
        private String dialect;
        @Value("${spring.datasource.read.url}")
        private String databaseUrl;

        @Bean(name = "readDataSource")
        @ConfigurationProperties(prefix = "spring.datasource.read")
        public DataSource dataSource() {
                return DataSourceBuilder.create().url(databaseUrl).build();
        }


        @Bean(name = "readEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean entityManagerFactory(
                EntityManagerFactoryBuilder builder,
                @Qualifier("readDataSource") DataSource dataSource
        ) {

                LocalContainerEntityManagerFactoryBean factoryBean = builder
                        .dataSource(dataSource)
                        .packages("com.example.cqrs.reader.persistence") // Paquete con las entidades de lectura
                        .persistenceUnit("readPU")
                        .build();

                HibernateJpaVendorAdapter vendor = new HibernateJpaVendorAdapter();
                factoryBean.setJpaVendorAdapter(vendor);

                Map<String, Object> properties = new HashMap<>();
                properties.put("hibernate.dialect",dialect);
                factoryBean.setJpaPropertyMap(properties);

                return factoryBean;
        }

        @Bean(name = "readTransactionManager")
        public PlatformTransactionManager transactionManager(
                @Qualifier("readEntityManagerFactory") EntityManagerFactory entityManagerFactory
        ) {
                return new JpaTransactionManager(entityManagerFactory);
        }

}
