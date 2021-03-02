#!/bin/bash

Input_path=$1
Output_path=$2
filename=$3

Help()
{
# Display Help



        echo    ""
        echo    "Example command"
        echo    
        echo    "./srbash.sh pptx_file_name"
        # echo    "current folder we have the output image"        

}
mkdir -p /home/sas/durga_ffmpeg/pptx_video/output/$filename 

java -jar /home/sas/durga_ffmpeg/pptx_video/PPTXToImage/build/libs/pptxtoimage-0.1.jar /home/sas/durga_ffmpeg/pptx_video/input/ $Output_path

echo    "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"
# Using CPU
ffmpeg -framerate 100 -i $Output_path/base.png -framerate 0.25 -i $Output_path/$filename/ppt_image_%03d.png -c:v libx264 -filter_complex "overlay=x='(W-w)/2':y='(H-h)/2'" -pix_fmt yuv420p -y $Output_path/$filename/output.mp4

# Using CPU
# fmpeg -framerate 100 -i /home/local/ZOHOCORP/durga-7043/ppt_to_video/output/base.png -framerate 0.25 -i /home/local/ZOHOCORP/durga-7043/ppt_to_video/output/$Input_path/ppt_image_%03d.png -c:v libx264 -filter_complex "overlay=x='(W-w)/2':y='(H-h)/2'" -pix_fmt yuv420p -y /home/local/ZOHOCORP/durga-7043/ppt_to_video/output/$Input_path/output.mp4
