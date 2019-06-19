import org.apache.http.HttpHost;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.management.MBeanServer;
import javax.persistence.EntityManagerFactory;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.management.ManagementFactory;

@SpringBootApplication
@ComponentScan(basePackages = {"data", "controller", "business", "log"})
//@ComponentScan({"controller","security.*"})
//@EntityScan("security.domain")
//@EnableJpaRepositories("security.repository")
@EnableTransactionManagement
@EnableRetry
@EnableScheduling
public class Application {

//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource mainDataSource() {
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }

/*
    @Autowired
    private EntityManager entityManager;


    @Bean
    public EntityManager entityManager(){
        return Persistence.createEntityManagerFactory("movieverse").createEntityManager();
    }
*/

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
        em.setPersistenceUnitName("movieverse");
        em.setPackagesToScan("controller2", "data.daos.*", "data.daos.impl.*", "controller","business");
        return em;
    }


    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }


/*
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalEntityManagerFactoryBean factory = new LocalEntityManagerFactoryBean();
        factory.setPersistenceUnitName("movieverse");
        return factory;
    }
*/
 /*
    @PersistenceContext(unitName = "movieverse")
    private EntityManager entityManager;


    */

    /*
    @Bean
    public LocalEntityManagerFactoryBean geEntityManagerFactoryBean() {
       LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
       factoryBean.setPersistenceUnitName("movieverse");
       return factoryBean;
    }
    */

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}