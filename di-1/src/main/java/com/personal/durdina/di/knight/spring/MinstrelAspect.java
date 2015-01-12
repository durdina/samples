package com.personal.durdina.di.knight.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MinstrelAspect {

    @Pointcut("execution(* *.embarkOnQuest(..))")
    public void businessService() {}
    
    @Around("com.personal.durdina.di.knight.spring.MinstrelAspect.businessService()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {

        Knight knight = (Knight) pjp.getThis();

        System.out.println("La la la, Sir " + knight.getName() + " is so brave!");
        Object rtn = pjp.proceed();
        System.out.println("" + "Tee-he-he, he did " + pjp.getSignature().getName());

        return rtn;
    }
    
}
