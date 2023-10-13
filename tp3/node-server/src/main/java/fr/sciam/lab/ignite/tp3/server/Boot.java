package fr.sciam.lab.ignite.tp3.server;

import fr.sciam.lab.ignite.tp3.server.service.QuarkusIgniteServiceProxy;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cluster.ClusterGroup;
import org.apache.ignite.services.ServiceConfiguration;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class Boot {
  private final Ignite ignite;

  public void onBoot(@Observes StartupEvent startupEvent) {
    ClusterGroup grp = ignite.cluster().forAttribute("nodeType", "server");
    log.info("grp {}",grp.nodes());
    ignite.services(grp).deploy(
        new ServiceConfiguration()
            .setName("MySimpleService")
            .setMaxPerNodeCount(1)
            .setService(new QuarkusIgniteServiceProxy())
    );
  }
}