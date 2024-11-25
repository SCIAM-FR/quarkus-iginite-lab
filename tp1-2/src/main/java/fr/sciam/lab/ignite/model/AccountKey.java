package fr.sciam.lab.ignite.model;

import lombok.Data;
import org.apache.ignite.cache.affinity.AffinityKeyMapped;

import java.io.Serializable;

@Data
public class AccountKey implements Serializable {
    @AffinityKeyMapped
    private String id;
}