package com.my.btstask;

import com.my.btstask.repositories.PersonRepository;
import com.my.btstask.view.MainPage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(scanBasePackages = "com.my.btstask.domain")
public class BtstaskApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(BtstaskApplication.class, args);
    }

}
