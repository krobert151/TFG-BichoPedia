package com.robertorebolledonaharro.bichoapi.config;

import com.robertorebolledonaharro.bichoapi.common.error.exeptions.user.model.User;
import lombok.extern.java.Log;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.*;

import java.util.Optional;
import java.util.UUID;

@Log
public class AuditorAwareImpl implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .filter(principal -> principal instanceof User)
                .map(User.class::cast)
                .map(User::getId)
                .map(UUID::toString);


    }
}
