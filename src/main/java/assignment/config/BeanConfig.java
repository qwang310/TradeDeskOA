package assignment.config;

import assignment.dao.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
    public class BeanConfig {

    @Bean
    public Map<String, Result> wordMap() {
        return new HashMap<String, Result>();
    }

}
