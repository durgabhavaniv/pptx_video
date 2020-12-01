# pptx_video
Convert PPTX to Video

# Run process
```
git clone https://github.com/durgabhavaniv/pptx_video.git
cd pptx_video
place input file *.pptx in ./input folder
./PPTX_VIDEO.sh Input_filename 
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
