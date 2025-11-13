package evn.petproject.just_shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class DataSourceConfig {

    //for fill field with annotation @CreatedBy and @LastModifiedBy
    @Bean
    public AuditorAware<String> auditorProvider() {
        //Security
//        return () -> {
//            try {
//                var auth = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
//                if (auth == null || !auth.isAuthenticated()) {
//                    return Optional.of("system");
//                }
//                Object principal = auth.getPrincipal();
//                if (principal instanceof org.springframework.security.core.userdetails.UserDetails ud) {
//                    return Optional.ofNullable(ud.getUsername());
//                } else {
//                    return Optional.ofNullable(principal.toString());
//                }
//            } catch (Exception ex) {
//                return Optional.of("system");
//            }
//        };

        return () -> Optional.of("unknown");
    }
}
