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

  public static final String RANDOM_INTEGERS = "randomIntegers";
  private final Ignite ignite;

  public void onBoot(@Observes StartupEvent startupEvent) {
    log.info("Boot, create a Map of Random Integers");
    IgniteCache<UUID, Integer> randomIntegers = ignite.getOrCreateCache(RANDOM_INTEGERS);
    Random random = new Random();
    AtomicInteger sum=new AtomicInteger(0);
    for ( int index=0 ; index < 1000 ; index++) {
      int val = random.nextInt(500);
      sum.addAndGet(val);
      randomIntegers.put(UUID.randomUUID(), val);
    }
    log.info("Sum is equal to {}",sum.get());

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