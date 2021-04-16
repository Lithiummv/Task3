package task3;

import java.security.SecureRandom;

public class RandomKey {
    public static byte[] generateRandomKey() {
        SecureRandom random = new SecureRandom();
        byte[] randomKey = new byte[16];
        random.nextBytes(randomKey);
        return randomKey;
    }
}
