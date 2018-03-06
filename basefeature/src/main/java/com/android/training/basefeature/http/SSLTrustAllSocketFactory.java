package com.android.training.basefeature.http;


import android.content.Context;


import com.android.training.basefeature.log.LogManager;

import org.apache.http.conn.ssl.SSLSocketFactory;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SSLTrustAllSocketFactory extends SSLSocketFactory {
    private static final String TAG = "SSLTrustAllSocketFactory";
    private SSLContext mSSLContext;
    private Context mCtx;
    public static long ONE_DAY_INTERVAL = 1000 * 60 * 60 * 24;


    public SSLTrustAllSocketFactory(KeyStore truststore, Context ctx)
            throws Throwable {
        super(truststore);
        try {
        	mCtx = ctx;
            mSSLContext = SSLContext.getInstance("TLS");
            mSSLContext.init(null, new TrustManager[]{new SSLTrustAllManager()},
                    null);
        } catch (Exception ex) {
        }
    }
    
    public class SSLTrustAllManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] certificates, String arg)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] certificates, String arg)
                throws CertificateException {

        	if(certificates.length>0){
                /**
                 * 下面注释代码释放，并完成错误代码修复则是完整功能代码
                 */
//                boolean found = false;
//                ApplicationInfo info = mCtx.getApplicationInfo();
//                boolean isDebug = (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0  ? true : false;
//                AppPreferences pre = new AppPreferences(mCtx);
//
//                //最后一次提示时间
//                long lastTime = pre.getLong(AppPreferences.LAST_UNTRUSTED_POPUP);
//
//                //首次使用初始为0
//                if(lastTime < 0){
//                    lastTime = 0;
//                }
//
//                if(!isDebug && DeviceUtils.isNetworkTypeWifi(mCtx)){
//                    for(X509Certificate cer : certificates){
//                        String publicKey = cer.getPublicKey().toString();
//
//                        String md5 = MD5Util.toMD5(publicKey);
//
//                        if(md5.equals("cf312b4477d5ea68953e741b2f806a8c")){
//                            found = true;
//                            pre.save(AppPreferences.UNTRUSTED_STATISTIC, "");
//                            break;
//                        }else{
//                            String stat = "size:"+certificates.length+":md5:"+md5;
//                            pre.save(AppPreferences.UNTRUSTED_STATISTIC, stat);
//                        }
//                    }
//
//                    if(!found &&(System.currentTimeMillis() - lastTime) > ONE_DAY_INTERVAL ){
//                        throw new CertificateException("Certificate error");
//                    }
//                }
        	}
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

    }
    
    public static SSLSocketFactory getSocketFactory(Context ctx) {
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);
            SSLSocketFactory factory = new SSLTrustAllSocketFactory(trustStore, ctx);
            return factory;
        } catch (Throwable e) {
            LogManager.getInstance().d(TAG, e.getMessage());
            //e.printStackTrace();
        }
        return null;
    }
    


    @Override
    public Socket createSocket(Socket socket, String host,
                               int port, boolean autoClose)
            throws IOException, UnknownHostException {
        return mSSLContext.getSocketFactory().createSocket(socket, host, port, autoClose);
    }

    @Override
    public Socket createSocket() throws IOException {
        return mSSLContext.getSocketFactory().createSocket();
    }

   

}
