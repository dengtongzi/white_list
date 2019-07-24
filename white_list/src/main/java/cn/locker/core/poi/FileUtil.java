package cn.locker.core.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
* @author 作者 dengtongzi
* @describe
* @version 创建时间：2019年7月21日 下午7:51:11
*/
public class FileUtil {
	 public static void inputStreamToFile(InputStream ins, File file) {
	        try {
	            OutputStream os = new FileOutputStream(file);
	            int bytesRead = 0;
	            byte[] buffer = new byte[8192];
	            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
	                os.write(buffer, 0, bytesRead);
	            }
	            os.close();
	            ins.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 }
}
