import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.context.annotation.ComponentScan;


import javax.sql.DataSource;


@SpringBootApplication
@ComponentScan({"controller"})
//@EntityScan("security.domain")
//@EnableJpaRepositories("security.repository")
public class Application {

   /* @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource mainDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }*/



    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}