import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication
@ComponentScan(basePackages = {"controller2", "data.daos.*", "data.daos.impl.*", "controller","business"})

//@ComponentScan({"controller","security.*"})
//@EntityScan("security.domain")
//@EnableJpaRepositories("security.repository")
public class Application {

//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource mainDataSource() {
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }

    @Autowired
    private EntityManager entityManager;


    @Bean
    public EntityManager entityManager(){
        return Persistence.createEntityManagerFactory("movieverse").createEntityManager();
    }




    public static void main(String[] args) {

/*
        Configuration configuration = new Configuration().configure();


        // Extract the properties from the configuration file.
		Properties prop = configuration.getProperties();

		// Create StandardServiceRegistryBuilder using the properties.
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		builder.applySettings(prop);

		// Build a ServiceRegistry
		ServiceRegistry registry = builder.build();

		// Create the SessionFactory using the ServiceRegistry
        SessionFactory SESSION_FACTORY = configuration.buildSessionFactory(registry);
*/
/*
        SessionFactory factory = configuration.buildSessionFactory();
		// 3. Retrieve a Session
		Session session = factory.openSession();
		// 4. Start a Transaction
		Transaction tx = session.beginTransaction();
		// 5. Check the status of the transaction and session
		System.out.println("tx.isActive() => " + tx.isActive());
		System.out.println("session.isConnected() => " + session.isConnected());
		// 6. Commit the transaction
		tx.commit();
		System.out.println("tx.isActive() => " + tx.isActive());
		// 7. Close the session
		session.close();
		// 8. Close the transaction
        factory.close();
*/

        //EntityManagerFactory entityManagerFactory = ;

        //EntityManager entityManager = entityManagerFactory.createEntityManager();


        SpringApplication.run(Application.class, args);
    }

}