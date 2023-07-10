# Lancement votre cluster Apache Ignite

Nous avons un projet qui prépare des serveurs ignite.

Pour le déployer, veuillez exécuter :

Pour linux/mac : 
```shell
cd ../ignite
./mvnw clean install
chmod +x ./target/apache-ignite-2.14.0-bin/bin/ignite.sh
```

Pour windows :  
```shell
cd ../ignite
./mvnw.cmd clean install
```

Une fois la préparation faite, nous pouvons lancer 3 fois les commandes suivantes (une fois par terminal)

Pour linux/mac :
```shell
cd ../ignite/target/apache-ignite-2.14.0-bin
export OPTION_LIBS=ignite-rest-http,ignite-calcite
export CONFIG_URI=file://config/node-configuration.xml
export JVM_OPTS="-Xms256m -Xmx512m -server -XX:MaxMetaspaceSize=256m -XX:MaxDirectMemorySize=256m"
./bin/ignite.sh
```

Pour windows powershell :
```shell
$env:OPTION_LIBS = 'ignite-rest-http,ignite-calcite'
$env:CONFIG_URI = 'file://config/node-configuration.xml'
$env:JVM_OPTS = '-Xms256m -Xmx512m -server -XX:MaxMetaspaceSize=256m -XX:MaxDirectMemorySize=256m'
../ignite\target\apache-ignite-2.14.0-bin/bin/ignite.bat
```
 
Dans les logs d'un des nœuds, vous trouverez l'URL pour brancher un outil de monitoring fourni pas Gridgain : 
![img.png](images/screen3.png)

Copiez-collez cette URL dans votre navigateur préféré et inscrivez-vous (gratuitement). Vous pourrez alors explorer votre cluster.
Nous utiliserons Nebula par la suite.

![img.png](images/screen4.png)
![img.png](images/screen5.png)

**Attention** le token d'enregistrement n'est valide que 5 minutes !

Apache ignite fournis en standard une api REST, nous allons l'utiliser pour vérifier le fonctionnement du cluster.
Il y a un fichier contenant l'URL ci-dessous [ici](src/http-requests/ignite-rest/get-version.http) si votre IDE permet de l'exécuter, sinon :

```http request
http://localhost:8080/ignite?cmd=version
```

