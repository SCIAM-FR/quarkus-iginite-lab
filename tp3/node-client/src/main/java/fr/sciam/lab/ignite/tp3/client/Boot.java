package fr.sciam.lab.ignite.tp3.client;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteAtomicLong;

@ApplicationScoped
@RequiredArgsConstructor
@Slf4j
public class Boot {

  public static final String RANDOM_INTEGERS = "randomIntegers";
  private final Ignite ignite;
  public void onBoot(@Observes StartupEvent startupEvent) {
    log.info("Boot");
    IgniteAtomicLong counter = ignite.atomicLong("counter", 0L, true);
    counter.incrementAndGet();
    log.info("==>Server {} started",counter.get());
  }
}