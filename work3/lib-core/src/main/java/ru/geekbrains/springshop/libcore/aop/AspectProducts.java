package ru.geekbrains.springshop.libcore.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import ru.geekbrains.springshop.libcore.dtos.ProductDTO;

import java.util.stream.Collectors;

@Aspect
@Component
public class AspectProducts {

    @Before("execution(public * ru.geekbrains.springshop.msproduct.controller.ProductController.getProducts(..))")
    public void beforeGetAllProducts(JoinPoint joinPoint) {
        StringBuilder sb = new StringBuilder("Before: getProducts\n")
                .append("signature: " + joinPoint.getSignature() + "\n");
        Object[] args = joinPoint.getArgs();
        for (Object arg: args) {
            sb.append(arg + "\n");
        }
        System.out.println(sb);
    }

    @AfterReturning(
            pointcut = "execution(public * ru.geekbrains.springshop.msproduct.controller.ProductController.getProducts(..))",
            returning = "result"
    )
    public void afterRerurnungGetAllProducts(JoinPoint joinPoint, Page<ProductDTO> result) {
        System.out.println("getProducts returning: ");
        System.out.println(result.get().collect(Collectors.toList()));
    }
}
