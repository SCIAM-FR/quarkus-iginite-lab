create table if not exists OPERATIONS
(
    id varchar(40) ,
    accountId varchar(40),
    amount decimal(10,2),
    primary key (id, accountId)
) with "TEMPLATE=PARTITIONED,BACKUPS=2,AFFINITY_KEY=accountId,CACHE_NAME=operations,VALUE_TYPE=fr.sciam.lab.ignite.model.Operation,KEY_TYPE=fr.sciam.lab.ignite.model.OperationKey";
