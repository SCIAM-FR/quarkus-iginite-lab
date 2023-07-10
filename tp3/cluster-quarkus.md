# Lancement votre cluster Quarkus

Pour le déployer, veuillez exécuter :

Pour linux/mac :
```shell
./mvnw -pl node-server quarkus:dev -Dnode.name=serverNode1
```
```shell
./mvnw -pl node-server quarkus:dev  -Dnode.name=serverNode2 -Dquarkus.http.port=8082 -Ddebug=5007
```
```shell
./mvnw -pl node-client quarkus:dev  -Dnode.name=clientNode1 -Dquarkus.http.port=8085 -Ddebug=5010
```

Pour windows powershell :
```shell
./mvnw -pl node-server quarkus:dev "-Dnode.name=serverNode1"
```
```shell
./mvnw -pl node-server quarkus:dev  "-Dnode.name=serverNode2" "-Dquarkus.http.port=8082" "-Ddebug=5007"
```
```shell
./mvnw -pl node-client quarkus:dev  "-Dnode.name=clientNode1" "-Dquarkus.http.port=8085" "-Ddebug=5010"
```