package com.personal.durdina.di.knight.spring;

import javax.inject.Named;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Named
@Aspect
public class MinstrelAspect {

    @Pointcut("execution(* com.personal.durdina.di.knight.spring.*.*(..))")
    public void anyPublicMethod() {
    }

    @Around("com.personal.durdina.di.knight.spring.MinstrelAspect.anyPublicMethod() && @annotation(minstrelIntercepted)")
    public Object doBasicProfiling(ProceedingJoinPoint pjp, MinstrelIntercepted minstrelIntercepted) throws Throwable {

        Knight knight = (Knight) pjp.getThis();

        System.out.println("La la la, Sir " + knight.getName() + " is so brave!");
        Object rtn = pjp.proceed();
        System.out.println("" + "Tee-he-he, he did " + pjp.getSignature().getName());

        return rtn;
    }

}
