package andon.sample.restTemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by Caozheng on 2017/3/6.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
    }
}
