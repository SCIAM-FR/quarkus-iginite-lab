package fr.sciam.lab.ignite.tp3.client.service;


import org.apache.ignite.Ignite;
import org.apache.ignite.lang.IgniteCallable;
import org.apache.ignite.resources.IgniteInstanceResource;

public class GetNodeUID implements IgniteCallable<String> {
    @IgniteInstanceResource
    Ignite ignite;
    @Override
    public String call() throws Exception {
        return ignite.cluster().localNode().id().toString();
    }
}
