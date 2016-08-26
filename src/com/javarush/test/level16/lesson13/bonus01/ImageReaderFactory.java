package com.javarush.test.level16.lesson13.bonus01;

import com.javarush.test.level16.lesson13.bonus01.common.*;

/**
 * Created by 309_newpower on 14.09.2015.
 */
public class ImageReaderFactory
{
    protected static ImageReader getReader(ImageTypes imageType){
            if (imageType == ImageTypes.BMP) return new BmpReader();
            else if (imageType == ImageTypes.JPG) return new JpgReader();
            else if (imageType == ImageTypes.PNG) return new PngReader();
            else
            {
                throw new IllegalArgumentException("Неизвестный тип картинки");
            }
    }
}
