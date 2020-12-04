# pptx_video
Convert PPTX to Video

# Run process PPTX to Video conversion
```
git clone https://github.com/durgabhavaniv/pptx_video.git
cd pptx_video
place input file *.pptx in ./input folder
./PPTX_VIDEO.sh Input_filename 
```

# Run web api
```
git clone https://github.com/durgabhavaniv/pptx_video.git
cd pptx_video
cd webCLI-master
modify application.properties file according to folder paths
mvn clean install
cp ./target/webCLI-0.0.1-SNAPSHOT.jar ./
java -jar webCLI-0.0.1-SNAPSHOT.jar --spring.config.location=./application.properties
```

# output
Output video present in ./output/Input_filename/*.mp4

# Rebuild pptx to image
```
cd ./PPTXToImage
./gradlew clean build
```

# Pre-requisite
```
java version --- 1.8.0_275
install ffmpeg
```
