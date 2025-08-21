package org.izeaux.jsscdemo;

public class Utils {
	
    /**
     *  Converts a byte array to a hex string (2 chars per byte) separated by space
     * @param bytes
     * @return
     */
    public static String byteArrayToHexStr(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 3);
        for (byte b : bytes) {
            sb.append(String.format("%02X", b)).append(' ');
        }
        return sb.toString();
    }
    
    /**
     *  Converts a byte to hex string 
     * @param bytes
     * @return
     */
    public static String byteToHexStr(byte b) {
        return String.format("%02X", b);
    }
    



    /**
     * Converts a string of hexadecimal numbers (2 chars per byte, possibly separated by spaces)
     * into a byte array.
     * Example: "0A FF 12" or "0AFF12" -> byte[]{0x0A, 0xFF, 0x12}
     * @param hex
     * @return
     */
    public static byte[] hexStringToBytes(String hex) {

        // Remove all spaces
        String cleaned = hex.replaceAll("\\s+", "");
        int len = cleaned.length();
        if (len % 2 != 0) {
            throw new IllegalArgumentException("Hex string must have even length");
        }
        byte[] result = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            result[i / 2] = (byte) Integer.parseInt(cleaned.substring(i, i + 2), 16);
        }
        return result;
    }
    
   public static void main(String[] args) {
	   String str1="96F8 4C59FF 96F8 9600 C600 96F8 4C 59 FF";
	   byte[] tb1= hexStringToBytes(str1);
	   String str2= byteArrayToHexStr(tb1);
	   
	   System.out.println(str1);
	   System.out.println(str2);
   }
}
