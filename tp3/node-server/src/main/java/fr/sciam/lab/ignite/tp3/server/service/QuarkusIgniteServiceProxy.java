package fr.sciam.lab.ignite.tp3.server.service;


import fr.sciam.lab.ignite.tp3.service.MySimpleService;
import org.apache.ignite.services.Service;

public class QuarkusIgniteServiceProxy implements Service, MySimpleService {

    @Override
    public Integer add(Integer a, Integer b) {
        return null;
    }
}
