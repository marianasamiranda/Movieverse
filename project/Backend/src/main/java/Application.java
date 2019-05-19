
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication
@ComponentScan(basePackages = {"data", "controller", "business"})
//@ComponentScan({"controller","security.*"})
//@EntityScan("security.domain")
//@EnableJpaRepositories("security.repository")
@EnableTransactionManagement
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
    @Autowired
    public RestHighLevelClient client;

    @Bean
    public RestHighLevelClient client() {
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
    }


    public static void main(String[] args) {

        //EntityManagerFactory entityManagerFactory = ;

        //EntityManager entityManager = entityManagerFactory.createEntityManager();


        SpringApplication.run(Application.class, args);
    }

}