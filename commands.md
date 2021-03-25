Input_path = /home/sas/durga_ffmpeg/pptx_video/input/test.mp4 
Output_path = /home/sas/durga_ffmpeg/pptx_video/output/ 
filename = test

mkdir -p $Output_path/$filename

-------------------------------------------------------------
Video transcoding 

ffmpeg -i $Input_path -vcodec libx264 -qp 30 -acodec copy -pix_fmt yuv420p -y $Output_path/$filename/output.mp4
---------------------------------------------------------------
Get your video with Zoho logo embeding

ffmpeg -v verbose -i $Input_path -i $Output_path/zoho-logo.png -filter_complex "[1:v]scale=-1:60[watermark];[0:v][watermark]overlay=10:10:alpha=1" -vcodec libx264 -qp 30 -acodec copy -y $Output_path/$filename/output.mp4
---------------------------------------------------------------
FFmepg Retro Video Filter


---------------------------------------------------------------
Unblurring video by sharpening.

ffmpeg -i $Input_path -filter "unsharp=lx=7:ly=7:la=1.5" -y $Output_path/$filename/output.mp4
---------------------------------------------------------------
Reverse video.

ffmpeg -i $Input_path -vf reverse -af areverse -c:v libx264 -qp 30 -y $Output_path/$filename/output.mp4
---------------------------------------------------------------
soble filter 

ffmpeg -i $Input_path -vf "sobel=scale=2:delta=10" -c:v libx264 -qp 30 -y $Output_path/$filename/output.mp4
---------------------------------------------------------------
