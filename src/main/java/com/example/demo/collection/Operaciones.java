package com.example.demo.collection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Operaciones {
    public int sumar(int a , int b )
    {
        log.info("------- En sumar ----");
        return a+b;
    }
}
