package fr.sciam.lab.ignite;

import jakarta.enterprise.inject.Disposes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.client.ClientAtomicLong;
import org.apache.ignite.client.IgniteClient;

@Produces(MediaType.APPLICATION_JSON)
@Path("/ignite/testconnect")
@Slf4j
@RequiredArgsConstructor
public class ConnectToIgniteCluster {
  private final IgniteClient igniteClient;

  @GET
  public Response testConnect() {
    ClientAtomicLong hitCounter = igniteClient.atomicLong("hitCounter", 0L, true);
    long counter = hitCounter.incrementAndGet();
    log.info("Current hit {}", counter);
    return Response.ok("{ Hit : \"" + counter + "\"}").build();
  }

}