package fr.sciam.lab.ignite.tp3.server.service;


import fr.sciam.lab.ignite.tp3.service.MySimpleService;
import jakarta.enterprise.inject.spi.CDI;
import org.apache.ignite.services.Service;

public class QuarkusIgniteServiceProxy implements Service, MySimpleService {
    private QuarkusIgniteService quarkusIgniteService;

    @Override
    public Integer add(Integer a, Integer b) {
        return quarkusIgniteService.add(a, b);
    }

    public void init() throws Exception {
        quarkusIgniteService = CDI.current().select(QuarkusIgniteService.class).get();
    }
}