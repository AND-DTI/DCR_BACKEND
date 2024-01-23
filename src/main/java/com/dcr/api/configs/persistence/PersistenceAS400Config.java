package com.dcr.api.configs.persistence;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "com.dcr.api.repository.as400", entityManagerFactoryRef = "as400_EntityManager", transactionManagerRef = "as400_TransactionManager")
public class PersistenceAS400Config {

    @Autowired
    Environment env;
    String ds_dialect = "as400.datasource.database-platform";
    String ds_ddlauto = "as400.datasource.hibernate.ddl-auto";
    String ds_modelPackages = "com.dcr.api.model.as400";

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "as400.datasource")
    public DataSource as400_DataSource() {

        return DataSourceBuilder.create().build();

    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean as400_EntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(as400_DataSource());
        em.setPackagesToScan(
                new String[] { ds_modelPackages });

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty(ds_ddlauto));
        properties.put("hibernate.dialect", env.getProperty(ds_dialect));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean
    public PlatformTransactionManager as400_TransactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                as400_EntityManager().getObject());
        return transactionManager;
    }

}
