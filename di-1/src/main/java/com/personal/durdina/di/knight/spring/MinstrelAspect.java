package com.personal.durdina.di.knight.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import javax.inject.Named;

@Named
@Aspect
public class MinstrelAspect {

    @Pointcut("execution(* com.personal.durdina.di.knight.spring.*.*(..))")
    public void anyPublicMethod() {
    }

    @Around("com.personal.durdina.di.knight.spring.MinstrelAspect.anyPublicMethod() && @annotation(minstrelIntercepted)")
    public Object doBasicProfiling(ProceedingJoinPoint pjp, MinstrelIntercepted minstrelIntercepted) throws Throwable {

        Knight knight = (Knight) pjp.getThis();

        System.out.println(String.format("[Intercepted] La la la, Sir %s is so brave!", knight.getName()));
        Object rtn = pjp.proceed();
        System.out.println(String.format("[Intercepted] Tee-he-he, Sir %s really did %s.", knight.getName(), pjp.getSignature().getName()));

        return rtn;
    }

}
