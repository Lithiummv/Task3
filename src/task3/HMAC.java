package task3;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HMAC {
    public static byte[] getHMAC(byte[] randomKey, String message) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(randomKey, "HmacSHA256");
        mac.init(secretKeySpec);
        return mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }
}
