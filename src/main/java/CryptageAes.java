import com.google.common.base.Charsets;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class CryptageAes{

    public static final String CRYPTAES_ALGO = "AES";
    public static final String KEY_ALGO = "AES";
    public static final byte[] MYSTERY_KEY = "PBKDF2WithHmacSH".getBytes(Charsets.UTF_8);

    public String decrypt(String input) {
        try {
            Cipher cipher = Cipher.getInstance(CRYPTAES_ALGO);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(MYSTERY_KEY, KEY_ALGO));
            return new String(cipher.doFinal(Base64.decodeBase64(input)), Charsets.UTF_8);

        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String encrypt(String input) {
        try {
            Cipher cipher = Cipher.getInstance(CRYPTAES_ALGO);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(MYSTERY_KEY, KEY_ALGO));
            return Base64.encodeBase64URLSafeString(cipher.doFinal(input.getBytes(Charsets.UTF_8)));

        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}