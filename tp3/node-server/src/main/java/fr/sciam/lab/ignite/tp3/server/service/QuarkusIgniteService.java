package fr.sciam.lab.ignite.tp3.server.service;


import fr.sciam.lab.ignite.tp3.service.MySimpleService;
import io.quarkus.arc.Unremovable;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
@RequiredArgsConstructor
@Unremovable
public class QuarkusIgniteService implements MySimpleService {
    @Override
    public Integer add(Integer a, Integer b) {
        log.info("Call add for {} and {}", a, b);
        return a + b;
    }
}