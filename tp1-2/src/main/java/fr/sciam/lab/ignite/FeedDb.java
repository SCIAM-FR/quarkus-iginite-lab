package fr.sciam.lab.ignite;

import fr.sciam.lab.ignite.model.Account;
import fr.sciam.lab.ignite.model.AccountKey;
import fr.sciam.lab.ignite.model.Operation;
import fr.sciam.lab.ignite.model.OperationKey;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

@Path("/ignite/tp2/feedDb")
@Slf4j
@RequiredArgsConstructor
public class FeedDb {
    public static final BigDecimal _100 = new BigDecimal(100);
    private final IgniteClient igniteClient;
    @SuppressWarnings("BigDecimalMethodWithoutRoundingCalled")
    @GET
    @Path("/{nbAccounts}/{nbAccountsInError}/{nbOperationsPerAccount}")
    public Response feedTheDb(@PathParam("nbAccounts") Integer nbAccounts, @PathParam("nbAccountsInError") Integer nbAccountsInError, @PathParam("nbOperationsPerAccount") Integer nbOperationsPerAccount) {
        ClientCache<AccountKey, Account> accounts = igniteClient.getOrCreateCache("accounts");
        ClientCache<OperationKey, Operation> operations = igniteClient.getOrCreateCache("operations");
        log.info("Get caches {} , {}",accounts,operations);
        for (int index = 0; index < nbAccounts + nbAccountsInError; index++) {
            AccountKey accountKey = new AccountKey();
            if (index < nbAccounts)
                accountKey.setId("GOOD-"+index);
            else
                accountKey.setId("BAD-"+index);
            Account account = new Account();
            account.setAmount(new BigDecimal(0));
            for (int indexOp = 0; indexOp < nbOperationsPerAccount; indexOp++) {
                Operation operation = new Operation();
                OperationKey operationKey = new OperationKey();
                operationKey.setAccountId(accountKey.getId());
                operationKey.setId(UUID.randomUUID().toString());
                operation.setAmount(new BigDecimal(new Random().nextInt(100000)).divide(_100));
                if (index < nbAccounts)
                    account.setAmount(account.getAmount().add(operation.getAmount()));
                operations.put(operationKey, operation);
                log.info("Put {}={}",operationKey,operation);
            }
            accounts.put(accountKey, account);
            log.info("Put {}={}",accountKey,account);
        }
        return Response.ok().build();
    }
}