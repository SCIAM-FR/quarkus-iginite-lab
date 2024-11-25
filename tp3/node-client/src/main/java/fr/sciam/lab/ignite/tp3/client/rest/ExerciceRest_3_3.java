package fr.sciam.lab.ignite.tp3.client.rest;

import fr.sciam.lab.ignite.tp3.client.service.GetNodeUID;
import fr.sciam.lab.ignite.tp3.service.MySimpleService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.Ignite;
import org.apache.ignite.lang.IgniteCallable;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.slf4j.LoggerFactory;

@Slf4j
@Path("/ignite-tp3")
@RequiredArgsConstructor
@Produces(MediaType.TEXT_PLAIN)
public class ExerciceRest_3_3 {
    private final Ignite ignite;
    private final ManagedExecutor managedExecutor;

    @GET
    @Path("/helloWorld/broadcastAll")
    public Response helloWorldBroadcastAll() {
        ignite.compute().broadcast(() -> LoggerFactory.getLogger("TESTREMOTE").info("===> Hello world !!"));
        return Response.ok().build();
    }

    @GET
    @Path("/helloWorld/broadcast")
    public Response helloWorldBroadcast() {
        ignite.compute(
                ignite.cluster().forRemotes()
        ).broadcast(() -> LoggerFactory.getLogger("TESTREMOTE").info("===> Hello world !!"));
        return Response.ok().build();
    }

    @GET
    @Path("/getNodeUID")
    public Response getNodeUID() {
        StringBuilder sb = new StringBuilder();
        ignite.compute(ignite.cluster().forServers())
                .broadcast(new GetNodeUID())
                .stream()
                .forEach(returned-> sb.append("Node UID = ").append(returned).append("\n"));
        return Response.ok(sb.toString()).build();
    }

    @GET
    @Path("/anonymousClass")
    public Response anonymousClass() {
        ignite.compute(ignite.cluster().forServers())
                .broadcast(new IgniteCallable<String>() {
                               @IgniteInstanceResource
                               Ignite ignite;
                               @Override
                               public String call() throws Exception {
                                   return ignite.cluster().localNode().id().toString();
                               }
                           }
                )
                .stream()
                .forEach(returned -> log.info("Captured = {}'", returned));
        return Response.ok().build();
    }

    @GET
    @Path("/async")
    public Response async() {
        ignite.compute(ignite.cluster().forServers())
                .runAsync(() -> LoggerFactory.getLogger("listen").info("hello from client"))
                .listen(after -> log.info("return of listen"));
        return Response.ok().build();
    }

    @GET
    @Path("listenAsyncManagedExecutor")
    public void listenAsyncManagedExecutor() throws InterruptedException {
        log.info("Current Thread {}", Thread.currentThread());
        ignite.compute(ignite.cluster().forServers())
                .runAsync(() -> LoggerFactory.getLogger("listenAsyncManagedExecutor").info("hello from client"))
                .listenAsync(
                        after -> log.info("Return handled in thread {}", Thread.currentThread()),
                        managedExecutor
                );
        Thread.sleep(20000); // this guarantees that the current thread will not be used when the response comes
    }

    @GET
    @Path("/callRemoteAdd/{a}/{b}")
    public void callRemoteAdd(@PathParam("a")int a, @PathParam("b")int b){
        MySimpleService mySimpleService = ignite.services(ignite.cluster().forAttribute("nodeType", "server"))
                .serviceProxy("MySimpleService", MySimpleService.class, false);
        log.info("Remote Call {} plus {} = {}", a, b, mySimpleService.add(a, b));

    }

}