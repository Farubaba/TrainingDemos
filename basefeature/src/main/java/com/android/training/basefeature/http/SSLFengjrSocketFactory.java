package com.android.training.basefeature.http;


import android.content.Context;

import com.android.training.basefeature.R;
import com.android.training.basefeature.log.LogManager;

import org.apache.http.conn.ssl.SSLSocketFactory;

import java.io.InputStream;
import java.security.KeyStore;

/**
 * 
 * @author gaoge
 * android 平台支持bks格式证书文件
 * 生成bks文件命令：
 * 
 * keytool -importcert -v -trustcacerts -alias certfengjr -file server.crt 
 * -keystore fengjr.bks -storetype BKS 
 * -providerclass org.bouncycastle.jce.provider.BouncyCastleProvider -providerpath ./bcprov-jdk15on-146.jar 
 * -storepass fengjr601
 * 
 * 其中server.crt是原格式证书
 * fengjr.bks是目标生成证书
 * fengjr601是bks格式文件证书密码
 *
 */
public class SSLFengjrSocketFactory extends SSLSocketFactory {
    private static final String TAG = "SSLCustomSocketFactory";

    private static final String KEY_PASS = "fengjr601";

    public SSLFengjrSocketFactory(KeyStore trustStore) throws Throwable {
        super(trustStore);
    }

    public static SSLSocketFactory getSocketFactory(Context context) {
        try {
            InputStream ins = context.getResources().openRawResource(R.raw.fengjr);

            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            try {
                trustStore.load(ins, KEY_PASS.toCharArray());
            }
            finally {
                ins.close();
            }
            SSLSocketFactory factory = new SSLFengjrSocketFactory(trustStore);
            return factory;
        } catch (Throwable e) {
            LogManager.getInstance().d(TAG, e.getMessage());
            LogManager.getInstance().e(TAG, "Exception" + e.getMessage());
        }
        return null;
    }
}
