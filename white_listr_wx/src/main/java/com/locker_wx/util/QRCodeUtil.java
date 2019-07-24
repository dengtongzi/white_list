package com.locker_wx.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 * @author 作者 : 邓通资 dtz 
 * @version 创建时间：2017年11月3日 下午2:33:21 
 * @description 生成二维码工具类 
 */
public class QRCodeUtil {  
	  
    /** 
     * 生成图像 
     *  
     * @throws WriterException 
     * @throws IOException 
     */  
    public static String qRCodeEncode(String content){  
    	String qrcodeFilePath = null;
		try {
			int width = 300; // 图像宽度  
			int height = 300; // 图像高度  
			String format = "png";// 图像类型  
			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵  
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					image.setRGB(x, y,bitMatrix.get(x, y) == true ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
				}
			}
			String path = "D:\\temp\\"/*GlobalConfig.getProperty("QR_CODE_PATH")*/;
			File QrcodeFile = new File(path + System.currentTimeMillis() + "." + format);
			ImageIO.write(image, format, QrcodeFile);
			qrcodeFilePath = QrcodeFile.getAbsolutePath();
			System.out.println("输出成功 --> Path = " + qrcodeFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qrcodeFilePath;
    }  
  
    /** 
     * 解析图像 
     */  
    public static void qRCodeDecode(String filePath) {  
        //String filePath = "E://temp//1509689242450.png";  
        BufferedImage image;  
        try {  
            image = ImageIO.read(new File(filePath));  
            LuminanceSource source = new BufferedImageLuminanceSource(image);  
            Binarizer binarizer = new HybridBinarizer(source);  
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);  
            Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();  
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");  
            Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码  
            //JSONObject content = JSONObject.parseObject(result.getText());  
            System.out.println("图片中内容：  " + result.getText());  
            //System.out.println("图片中格式：  " + result.getBarcodeFormat());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
    }
    
    public static void main(String[] args) throws WriterException, IOException {
    	qRCodeEncode("http://www.baidu.com");
    	//qRCodeDecode("D:\\temp\\1563934921414.png");
	}
}  