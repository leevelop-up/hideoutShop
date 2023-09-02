//package com.example.hideoutshop.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//
//@Configuration
//@EnableJpaRepositories(
//        basePackages = {"com.example.hideoutshop.repository.member"},
//        // 23.08.11 hyuna - package에 추가필요
//        entityManagerFactoryRef = "entityManagerFactoryBean",
//        transactionManagerRef = "tmJpa"
//)
//
//public class JpaConfig {
//    @Bean
//    public DataSource dataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUsername("");
//        dataSource.setPassword("!");
//        dataSource.setDriverClassName("com.mariadb.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mariadb://leevelop.com:2906/modooagit?useUnicode=true&characterEncoding=UTF-8");
//        return dataSource;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan("com.example.hideoutshop.repository.member");
//
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
//        properties.put("hibernate.format_sql", "true");
//        properties.put("hibernate.use_sql_comment", "true");
//
//        em.setJpaPropertyMap(properties);
//
//        return em;
//    }
//
//    @Bean(name = "tmJpa")
//    public PlatformTransactionManager transactionManager(DataSource dataSource) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactoryBean(dataSource).getObject());
//        return transactionManager;
//    }
//
//}
