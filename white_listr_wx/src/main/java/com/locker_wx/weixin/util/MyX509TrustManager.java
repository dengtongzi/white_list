package com.locker_wx.weixin.util;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
* @author 作者 dengtongzi
* @describe 证书信任管理器（用于https请求）
* @version 创建时间：2019年6月17日 下午5:27:07
*/
public class MyX509TrustManager  implements X509TrustManager {

    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

}
