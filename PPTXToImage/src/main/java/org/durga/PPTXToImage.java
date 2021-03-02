package org.durga;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.*;

public class PPTXToImage {
    public static void main(String[] args) throws IOException{

        String inputFilePath = "./";
        String outputFilePath =  "./";
        if(args.length > 0){
            inputFilePath = args[0] != null? args[0]: inputFilePath;
            outputFilePath= args[1] != null? args[1]: outputFilePath;
        }

        File folder = new File(inputFilePath);
        String[] files = folder.list();

        for (String file : files)
        {
            System.out.println(file);
            if(file.endsWith(".pptx")){
                //creating an empty presentation
                File pptxFile=new File(inputFilePath+file.toString());
                // System.out.println(pptxFile.toString());
                XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(pptxFile));

                //getting the dimensions and size of the slide
                Dimension pgsize = ppt.getPageSize();
                java.util.List<XSLFSlide> slide = ppt.getSlides();
                
                if (pgsize.width % 2 != 0) {
                    pgsize.width = pgsize.width + 1;
                }
                if (pgsize.height % 2 != 0) {
                    pgsize.height = pgsize.height + 1;
                }
                DecimalFormat formatter = new DecimalFormat("000");
                for (int i = 0; i < slide.size(); i++) {
                    BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
                    // BufferedImage img = new BufferedImage(1280, 720, BufferedImage.TYPE_INT_RGB);
                    Graphics2D graphics = img.createGraphics();

                    //clear the drawing area
                    graphics.setPaint(Color.white);
                    graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));

                    String imageOut = outputFilePath+ "/"+file.toString().replaceFirst("[.][^.]+$", "");
                    //render
                    System.out.println(outputFilePath);
                    slide.get(i).draw(graphics);
                    File directory = new File(imageOut);
                    if (! directory.exists()){
                        directory.mkdir();
                        // If you require it to make the entire directory path including parents,
                        // use directory.mkdirs(); here instead.
                    }

                    //creating an image file as output
                    String aFormatted = formatter.format(i);
                    FileOutputStream out = new FileOutputStream(imageOut+"/ppt_image_" + aFormatted + ".png");
                    javax.imageio.ImageIO.write(img, "png", out);
                    // System.out.println("Image successfully created");
                    out.close();
                }
                System.out.println("Images successfully created");
            }
        }
    }
}
