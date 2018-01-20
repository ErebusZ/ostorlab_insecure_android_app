package co.ostorlab.insecure_app.bugs.calls;

import android.util.Base64;
import android.util.Log;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import co.ostorlab.insecure_app.BugRule;


public final class AESCipher extends BugRule {

    private static final String TAG = "RULE";

    @Override
    public String getDescription() {
        return "Use of insecure ECB Mode";
    }

    @Override
    public void run() throws Exception{
        String clearText = "Jan van Eyck was here 1434";
        String key = "ThisIs128bitSize";
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encryptedMessage = cipher.doFinal(clearText.getBytes());
        Log.i(TAG, String.format("Message: %s", Base64.encodeToString(encryptedMessage, Base64.DEFAULT)));
    }
}
