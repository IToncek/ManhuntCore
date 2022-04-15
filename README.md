# Manhunt Core
If you want to implement / modify any part of my code, here is the maven configuration:
```maven
    <repository>
        <id>YOUR-PROJECT-NAME-mvn-repo</id>
        <url>https://github.com/YOUR-USERNAME/YOUR-PROJECT-NAME/raw/mvn-repo/</url>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </snapshots>
    </repository>
```
and here's the dependency:
```maven
    <dependency>
        <groupId>me.itoncek</groupId>
        <artifactId>manhuntcore</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
 ```
