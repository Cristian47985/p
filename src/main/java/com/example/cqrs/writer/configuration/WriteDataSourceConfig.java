package com.example.cqrs.writer.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.cqrs.writer.repositories",
        entityManagerFactoryRef = "writeEntityManagerFactory",
        transactionManagerRef = "writeTransactionManager"
)
public class WriteDataSourceConfig  {
        @Primary
        @Bean(name = "writeDataSource")
        @ConfigurationProperties(prefix = "spring.datasource.write")
        public DataSource dataSource() {
            return DataSourceBuilder.create().build();
        }

        @Value("${spring.jpa.datasource-platform}")
        private String dialect;

        @Primary
        @Bean(name = "writeEntityManagerFactory")
        public LocalContainerEntityManagerFactoryBean entityManagerFactory(
                EntityManagerFactoryBuilder builder,
                @Qualifier("writeDataSource") DataSource dataSource
        ) {
                LocalContainerEntityManagerFactoryBean factoryBean =builder
                    .dataSource(dataSource)
                    .packages("com.example.cqrs.writer.persistence") // Paquete con las entidades de escritura
                    .persistenceUnit("writePU")
                    .build();
                HibernateJpaVendorAdapter vendor = new HibernateJpaVendorAdapter();
                factoryBean.setJpaVendorAdapter(vendor);

                Map<String, Object> properties = new HashMap<>();
                properties.put("hibernate.dialect",dialect);
                factoryBean.setJpaPropertyMap(properties);

                return factoryBean;
        }

        @Primary
        @Bean(name = "writeTransactionManager")
        public PlatformTransactionManager transactionManager(
                @Qualifier("writeEntityManagerFactory") EntityManagerFactory entityManagerFactory
        ) {
            return new JpaTransactionManager(entityManagerFactory);
        }

}
