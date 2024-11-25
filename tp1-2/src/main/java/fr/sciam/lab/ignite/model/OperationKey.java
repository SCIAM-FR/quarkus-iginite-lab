package fr.sciam.lab.ignite.model;

import lombok.Data;
import org.apache.ignite.cache.affinity.AffinityKeyMapped;

import java.io.Serializable;

@Data
public class OperationKey implements Serializable {
    private String id;
    @AffinityKeyMapped
    private String accountId;
}