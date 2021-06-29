package co.com.foodbank.user.sdk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author mauricio.londono@gmail.com co.com.foodbank.contribution.sdk.config
 *         15/06/2021
 */
@Configuration
public class ConfigRestTemplateSDKUser {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
