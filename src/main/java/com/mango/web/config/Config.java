package com.mango.web.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Properties;

/**
 * Created by tobywang on 4/10/2018.
 */
@Configuration
@ComponentScan("com.mango")
@EnableWebMvc
@EnableTransactionManagement(proxyTargetClass = true)
public class Config {
    /**
     * jsp视图解析器的bean
     * @return
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    
    /**
     * 配置数据源
     * @return
     **/
    @Bean(name = "dataSource")
    public ComboPooledDataSource getDataSource() {
        try {
            
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mvc");
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setUser("root");
            dataSource.setPassword("1qazxsw2");
            dataSource.setMaxPoolSize(75);
            return dataSource;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 配置sessionFactory
     * @return
     **/
    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
        sfb.setDataSource(getDataSource());
        sfb.setPackagesToScan(new String[]{"com.mango.web.pojo"});
        Properties pros = new Properties();
        pros.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        pros.setProperty("hibernate.show_sql", "true");
        pros.setProperty("hibernate.hbm2ddl.auto", "update");
        pros.setProperty("hibernate.format_sql", "false");
        sfb.setHibernateProperties(pros);
        
        return sfb;
    }
    
    /**
     * 配置transactionManager
     * @return
     **/
    @Bean(name = "transactionManager")
    public HibernateTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
        
        return hibernateTransactionManager;
    }
    
    
    /**
     * 捕获任何平台相关的异常，以Spring统一的非检查型异常的形式重新抛出
     * @return
     */
    @Bean
    public BeanPostProcessor persisteenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    /**
     * 配置Log4j consoleAppender
     * @return
     **/
    @Bean
    public ConsoleAppender consoleAppender() {
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setThreshold(Level.DEBUG);
        PatternLayout patternLayout = new PatternLayout();
        patternLayout.setConversionPattern("%d %-5p  [%c{1}] %m %n");
        consoleAppender.setLayout(patternLayout);
        return consoleAppender;
    }
    
    /**
     * 配置Log4j fileAppender
     * @return
     **/
    @Bean
    public FileAppender fileAppender() {
        RollingFileAppender fileAppender = new RollingFileAppender();
        fileAppender.setThreshold(Level.DEBUG);
        fileAppender.setFile("D:\\Study\\MavenSpringMVC\\logs\\");
        fileAppender.setMaxFileSize("1000000KB");
        fileAppender.setMaxBackupIndex(1);
        PatternLayout patternLayout = new PatternLayout();
        patternLayout.setConversionPattern("%d %-5p  [%c{1}] %m %n");
        fileAppender.setLayout(patternLayout);
        return fileAppender;
    }
}
