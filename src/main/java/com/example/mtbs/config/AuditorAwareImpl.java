package com.example.mtbs.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component // bean name -> auditAwareImpl
public class AuditorAwareImpl implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {
        String username = Optional.ofNullable(SecurityContextHolder)
        return Optional.empty();
    }
}


