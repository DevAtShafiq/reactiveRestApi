package com.example.person;

import com.mysql.cj.xdevapi.Schema;
import com.mysql.cj.xdevapi.Session;
import com.mysql.cj.xdevapi.SessionFactory;
import com.mysql.cj.xdevapi.Table;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfiguration {

    @Bean
    public Session mySession() {
        return new SessionFactory().getSession("mysqlx://localhost/33060/app?user=root&password=root");
    }

    @Bean
    public Schema schema() {
        return mySession().getSchema("app");
    }

    @Bean
    public Table people() {
        return schema().getTable("people");
    }
}
