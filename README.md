### Example Bot ###

This bot is created to demonstrate [telegram-bots-client](https://github.com/bibarsov/telegram-bots-client) usage.

How to start:
1. Ensure you have Java 11 on your machine
2. Pull [telegram-bots-client](https://github.com/bibarsov/telegram-bots-client) and [telegram-bots-common](https://github.com/bibarsov/telegram-bots-common) repositories
3. Build both of them: `mvn clean install`
4. Pull current repository
5. Replace values in `src/main/resources/examplebot.properties`
6. Build the project using `mvn clean package` 
7. Run the bot using `java -jar target/telegram-bots-example-unspecified-jar-with-dependencies.jar`
