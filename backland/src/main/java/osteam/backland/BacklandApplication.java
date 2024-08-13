package osteam.backland;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class BacklandApplication {

    public static void main(String[] args) {
        SpringApplication.run(BacklandApplication.class, args);
    }

}
