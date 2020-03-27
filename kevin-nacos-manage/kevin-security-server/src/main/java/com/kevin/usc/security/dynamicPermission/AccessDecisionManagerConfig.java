package com.kevin.usc.security.dynamicPermission;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AccessDecisionManagerConfig {

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<>();
        decisionVoters.add(new AuthenticatedVoter());
        decisionVoters.add(new WebExpressionVoter());
        decisionVoters.add(new DynamicVoter());
        return new AffirmativeBased(decisionVoters);
    }
}
