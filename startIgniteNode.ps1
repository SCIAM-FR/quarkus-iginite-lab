$env:OPTION_LIBS = 'ignite-rest-http,ignite-calcite'
$env:CONFIG_URI = 'file://config/node-configuration.xml'
$env:JVM_OPTS = '-Xms256m -Xmx512m -server -XX:MaxMetaspaceSize=256m -XX:MaxDirectMemorySize=256m --add-opens java.base/java.lang.invoke=ALL-UNNAMED --add-opens=jdk.management/com.sun.management.internal=ALL-UNNAMED --add-opens=java.base/jdk.internal.misc=ALL-UNNAMED --add-opens=java.base/sun.nio.ch=ALL-UNNAMED --add-opens=java.management/com.sun.jmx.mbeanserver=ALL-UNNAMED --add-opens=jdk.internal.jvmstat/sun.jvmstat.monitor=ALL-UNNAMED --add-opens=java.base/sun.reflect.generics.reflectiveObjects=ALL-UNNAMED --add-opens=java.base/java.io=ALL-UNNAMED --add-opens=java.base/java.nio=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.base/java.util.concurrent=ALL-UNNAMED --add-opens=java.base/java.util.concurrent.locks=ALL-UNNAMED --add-opens=java.base/java.lang=ALL-UNNAMED '
cd ./ignite/target/apache-ignite-2.14.0-bin
./bin/ignite.bat
