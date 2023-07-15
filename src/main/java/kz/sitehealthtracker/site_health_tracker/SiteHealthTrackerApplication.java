package kz.sitehealthtracker.site_health_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SiteHealthTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiteHealthTrackerApplication.class, args);
    }

}
