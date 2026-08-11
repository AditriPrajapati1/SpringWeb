package io.herald.SpringWeb.Configuration;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
        public Cloudinary cloudinary(){

            return new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "t62opt33",
                    "api_key", "997464368181545",
                    "api_secret", "0ejsQzxtoZpDyE6PrnXMA4j4ZYM",
                    "secure", true

            ));
    }
}
