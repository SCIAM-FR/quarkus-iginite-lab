package fr.sciam.lab.ignite.tp3.client.config;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import org.apache.ignite.Ignition;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;

public class IgniteProducer {
    @Produces
    @ApplicationScoped
    public IgniteClient igniteClient() {
        ClientConfiguration cfg = new ClientConfiguration().setAddresses("127.0.0.1:10800");
        return Ignition.startClient(cfg);
    }

    public void disposeIgniteClient(@Disposes IgniteClient igniteClient) {
        igniteClient.close();
    }
}
