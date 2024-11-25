package fr.sciam.lab.ignite;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.ignite.cache.query.FieldsQueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.ClientCacheConfiguration;
import org.apache.ignite.client.IgniteClient;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

@Path("/ignite/tp2/createSchemas")
@Slf4j
@RequiredArgsConstructor
public class CreateSchemas {
    private final IgniteClient igniteClient;

    @GET
    public Response createSchemas() throws IOException {
        ClientCache<Long, String> cache = igniteClient
                .getOrCreateCache(new ClientCacheConfiguration().setName("PUBLIC"));
        createTable(cache, "operations");
        createTable(cache, "accounts");
        return Response.ok().build();
    }

    private void createTable(ClientCache<Long, String> cache, String table) throws IOException {
        try (InputStream resourceAsStream = Objects.requireNonNull(getClass().getResourceAsStream("/create-schema-" + table + ".sql"))) {
            String sql = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);
            log.info("Apply sql : {}", sql);
            try (FieldsQueryCursor<List<?>> query = cache.query(new SqlFieldsQuery(sql))) {
                query.getAll();
            }
        }
    }
}