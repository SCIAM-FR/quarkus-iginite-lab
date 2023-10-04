
create table if not exists ACCOUNTS
(
    id varchar(40) primary key,
    amount decimal(10,2)
) with "TEMPLATE=REPLICATED,BACKUPS=2,AFFINITY_KEY=id,CACHE_NAME=accounts,VALUE_TYPE=fr.sciam.lab.ignite.model.Account,KEY_TYPE=fr.sciam.lab.ignite.model.AccountKey";



