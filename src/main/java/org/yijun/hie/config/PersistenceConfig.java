package org.yijun.hie.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by liuyijun on 14-11-8.
 */

@Configuration
public class PersistenceConfig {
    @Autowired
    private DataSource pooledDataSource;


    @Bean
    public SessionFactory createSessionFactory () {
        LocalSessionFactoryBuilder sessionFactoryBuilder = new LocalSessionFactoryBuilder(pooledDataSource);

        // scan all entity classes
        sessionFactoryBuilder.scanPackages("org.yijun.hie.persistence");
        sessionFactoryBuilder.setProperty("hibernate.show_sql", "true");
        sessionFactoryBuilder.setProperty("hibernate.format_sql", "true");
        return sessionFactoryBuilder.buildSessionFactory();
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        //return new JtaTransactionManager();
        return new HibernateTransactionManager(sessionFactory);
    }
}
