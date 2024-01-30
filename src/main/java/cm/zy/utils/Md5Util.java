package cm.zy.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
    /**
     * Conjunto predeterminado de caracteres utilizado para convertir bytes a representación hexadecimal,
     * Apache utiliza este conjunto para verificar la corrección de la descarga de archivos.
     */
    protected static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    protected static MessageDigest messagedigest = null;

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            System.err.println(Md5Util.class.getName() + " inicialización fallida, MessageDigest no admite MD5Util.");
            nsaex.printStackTrace();
        }
    }

    /**
     * Genera el valor de verificación MD5 de una cadena.
     *
     * @param s La cadena a procesar.
     * @return El valor MD5 en formato de cadena.
     */
    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    /**
     * Comprueba si el código de verificación MD5 de una cadena coincide con un código MD5 conocido.
     *
     * @param password  La cadena a verificar.
     * @param md5PwdStr El código de verificación MD5 conocido.
     * @return true si coincide, false en caso contrario.
     */
    public static boolean checkPassword(String password, String md5PwdStr) {
        String s = getMD5String(password);
        return s.equals(md5PwdStr);
    }

    /**
     * Genera el valor de verificación MD5 de un array de bytes.
     *
     * @param bytes El array de bytes a procesar.
     * @return El valor MD5 en formato de cadena.
     */
    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuilder stringbuffer = new StringBuilder(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuilder stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }
}
