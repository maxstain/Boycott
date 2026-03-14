package org.example.boycott.Aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

    @Before("execution(* org.example.boycott.Services.*.*(..))")
    public void afficherMessageAvantService(JoinPoint joinPoint) {

        String nomService = joinPoint.getSignature().getName();

        System.out.println(
                "Bienvenue à l’un des services de l’application Boycott : "
                        + nomService
        );
    }
}