/**
 * Copyright 2018-2020 dengtongzi  
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.locker.core.util;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.date.DateUtil;
import cn.locker.core.config.properties.AppNameProperties;
import cn.locker.core.config.properties.DruidProperties;
import cn.locker.kernel.model.exception.ServiceException;
import cn.locker.kernel.model.exception.enums.CoreExceptionEnum;
import cn.locker.kernel.model.util.ValidateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.*;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;

/**
 * 高频方法集合
 *
 * @author dengtongzi
 * 
 */
public class ToolUtil extends ValidateUtil {

    /**
     * 默认密码盐长度
     */
    public static final int SALT_LENGTH = 6;

    /**
     * 获取随机字符,自定义长度
     *
     * @author dengtongzi
     * 
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * md5加密(加盐)
     *
     * @author dengtongzi
     * 
     */
    public static String md5Hex(String password, String salt) {
        return md5Hex(password + salt);
    }

    /**
     * md5加密(不加盐)
     *
     * @author dengtongzi
     * 
     */
    public static String md5Hex(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(str.getBytes());
            StringBuffer md5StrBuff = new StringBuffer();
            for (int i = 0; i < bs.length; i++) {
                if (Integer.toHexString(0xFF & bs[i]).length() == 1)
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & bs[i]));
                else
                    md5StrBuff.append(Integer.toHexString(0xFF & bs[i]));
            }
            return md5StrBuff.toString();
        } catch (Exception e) {
            throw new ServiceException(CoreExceptionEnum.ENCRYPT_ERROR);
        }
    }

    /**
     * 过滤掉掉字符串中的空白
     *
     * @author dengtongzi
     * 
     */
    public static String removeWhiteSpace(String value) {
        if (isEmpty(value)) {
            return "";
        } else {
            return value.replaceAll("\\s*", "");
        }
    }

    /**
     * 获取某个时间间隔以前的时间 时间格式：yyyy-MM-dd HH:mm:ss
     *
     * @author dengtongzi
     * 
     */
    public static String getCreateTimeBefore(int seconds) {
        long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();
        Date date = new Date(currentTimeInMillis - seconds * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 获取异常的具体信息
     *
     * @author dengtongzi
     * 
     * @version 2.0
     */
    public static String getExceptionMsg(Throwable e) {
        StringWriter sw = new StringWriter();
        try {
            e.printStackTrace(new PrintWriter(sw));
        } finally {
            try {
                sw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return sw.getBuffer().toString().replaceAll("\\$", "T");
    }

    /**
     * 获取应用名称
     *
     * @author dengtongzi
     * 
     */
    public static String getApplicationName() {
        try {
            AppNameProperties appNameProperties =
                    SpringContextHolder.getBean(AppNameProperties.class);
            if (appNameProperties != null) {
                return appNameProperties.getName();
            } else {
                return "";
            }
        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger(ToolUtil.class);
            logger.error("获取应用名称错误！", e);
            return "";
        }
    }

    /**
     * 获取ip地址
     *
     * @author yaoliguo
     * 
     */
    public static String getIP() {
        try {
            StringBuilder IFCONFIG = new StringBuilder();
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {
                        IFCONFIG.append(inetAddress.getHostAddress().toString() + "\n");
                    }

                }
            }
            return IFCONFIG.toString();

        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 拷贝属性，为null的不拷贝
     *
     * @author dengtongzi
     * 
     */
    public static void copyProperties(Object source, Object target) {
        BeanUtil.copyProperties(source, target, CopyOptions.create().setIgnoreNullValue(true));
    }

    /**
     * 判断是否是windows操作系统
     *
     * @author dengtongzi
     * 
     */
    public static Boolean isWinOs() {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取临时目录
     *
     * @author dengtongzi
     * 
     */
    public static String getTempPath() {
        return System.getProperty("java.io.tmpdir");
    }

    /**
     * 把一个数转化为int
     *
     * @author dengtongzi
     * 
     */
    public static Integer toInt(Object val) {
        if (val instanceof Double) {
            BigDecimal bigDecimal = new BigDecimal((Double) val);
            return bigDecimal.intValue();
        } else {
            return Integer.valueOf(val.toString());
        }

    }

    /**
     * 是否为数字
     *
     * @author dengtongzi
     * 
     */
    public static boolean isNum(Object obj) {
        try {
            Integer.parseInt(obj.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 获取项目路径
     *
     * @author dengtongzi
     * 
     */
    public static String getWebRootPath(String filePath) {
        try {
            String path = ToolUtil.class.getClassLoader().getResource("").toURI().getPath();
            path = path.replace("/WEB-INF/classes/", "");
            path = path.replace("/target/classes/", "");
            path = path.replace("file:/", "");
            if (ToolUtil.isEmpty(filePath)) {
                return path;
            } else {
                return path + "/" + filePath;
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取文件后缀名 不包含点
     *
     * @author dengtongzi
     * 
     */
    public static String getFileSuffix(String fileWholeName) {
        if (ToolUtil.isEmpty(fileWholeName)) {
            return "none";
        }
        int lastIndexOf = fileWholeName.lastIndexOf(".");
        return fileWholeName.substring(lastIndexOf + 1);
    }

    /**
     * 判断一个对象是否是时间类型
     *
     * @author dengtongzi
     * 
     */
    public static String dateType(Object o) {
        if (o instanceof Date) {
            return DateUtil.formatDate((Date) o);
        } else {
            return o.toString();
        }
    }

    /**
     * 当前时间
     *
     * @author dengtongzi
     * 
     */
    public static String currentTime() {
        return DateUtil.formatDateTime(new Date());
    }

    /**
     * 对象组中是否存在 Empty Object
     *
     * @param os 对象组
     * @return
     */
    public static boolean isOneEmpty(Object... os) {
        for (Object o : os) {
            if (isEmpty(o)) {
                return true;
            }
        }
        return false;
    }

}
