package marhlonkorb.github.io.gerenciadorestacionamento.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private final List<String> origins = List.of("http://localhost:4200");

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        origins.forEach(origin -> registry.addMapping("/**").allowedOrigins(origin).allowedMethods("*"));
    }
}

