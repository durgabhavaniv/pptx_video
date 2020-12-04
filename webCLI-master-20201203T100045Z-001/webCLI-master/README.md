# webCLI

git clone https://github.com/durgabhavaniv/webCLI.git

vim application.properties - modify accordingly

mvn clean install

cp ./target/webCLI-0.0.1-SNAPSHOT.jar ./

chmod +x ./webCLI-0.0.1-SNAPSHOT.jar

java -jar webCLI-0.0.1-SNAPSHOT.jar --spring.config.location=./application.properties
