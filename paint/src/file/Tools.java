package file;

import java.nio.ByteBuffer;

/**
 * <b>ツール</b><br>
 * 変換など<br>
 * date: 2017/10/13 last_date: 2017/10/27
 * 
 * @author ソウルP
 * @version 1.0 2017/10/13 Tools作成
 * @version 1.1 2017/10/19 byte[] と Byte[] の変換追加
 * @version 1.2 2017/10/25 バイト配列の2次元から１次元に変換追加
 * @version 1.3 2017/10/27 subbytes削除
 */
public interface Tools {
    /**
     * <b>エンディアン変換</b><br>
     * ビッグエンディアン → リトルエンディアン<br>
     * リトルエンディアン → ビッグエンディアン
     * 
     * @param bytes
     *            バイト配列
     * @return 変換された後のバイト配列
     */
    public static byte[] endian(byte[] bytes) {
        byte[] b = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++)
            b[i] = bytes[bytes.length - 1 - i];
        return b;
    }

    /**
     * <b>short → リトルエンディアンのバイト配列</b>
     * 
     * @param bytes
     *            バイト配列
     * @param value
     *            値
     * @return リトルエンディアンのバイト配列
     */
    public static byte[] short2bytes(short value) {
        return endian(ByteBuffer.allocate(2).putShort(value).array());
    }

    /**
     * <b>int → リトルエンディアンのバイト配列</b>
     * 
     * @param bytes
     *            バイト配列
     * @param value
     *            値
     * @return リトルエンディアンのバイト配列
     */
    public static byte[] int2bytes(int value) {
        return endian(ByteBuffer.allocate(4).putInt(value).array());
    }

    /**
     * <b>long → リトルエンディアンのバイト配列</b>
     * 
     * @param bytes
     *            バイト配列
     * @param value
     *            値
     * @return リトルエンディアンのバイト配列
     */
    public static byte[] long2bytes(long value) {
        return endian(ByteBuffer.allocate(8).putLong(value).array());
    }

    /**
     * <b>float → リトルエンディアンのバイト配列</b>
     * 
     * @param bytes
     *            バイト配列
     * @param value
     *            値
     * @return リトルエンディアンのバイト配列
     */
    public static byte[] float2bytes(float value) {
        return endian(ByteBuffer.allocate(4).putFloat(value).array());
    }

    /**
     * <b>double → リトルエンディアンのバイト配列</b>
     * 
     * @param bytes
     *            バイト配列
     * @param value
     *            値
     * @return リトルエンディアンのバイト配列
     */
    public static byte[] double2bytes(double value) {
        return endian(ByteBuffer.allocate(8).putDouble(value).array());
    }

    /**
     * <b>リトルエンディアンのバイト配列 → short</b>
     * 
     * @param bytes
     *            リトルエンディアンのバイト配列
     * @return 値
     */
    public static short bytes2short(byte[] bytes) {
        return ByteBuffer.wrap(endian(bytes)).getShort();
    }

    /**
     * <b>リトルエンディアンのバイト配列 → int</b>
     * 
     * @param bytes
     *            リトルエンディアンのバイト配列
     * @return 値
     */
    public static int bytes2int(byte[] bytes) {
        return ByteBuffer.wrap(endian(bytes)).getInt();
    }

    /**
     * <b>リトルエンディアンのバイト配列 → long</b>
     * 
     * @param bytes
     *            リトルエンディアンのバイト配列
     * @return 値
     */
    public static long bytes2long(byte[] bytes) {
        return ByteBuffer.wrap(endian(bytes)).getLong();
    }

    /**
     * <b>リトルエンディアンのバイト配列 → float</b>
     * 
     * @param bytes
     *            リトルエンディアンのバイト配列
     * @return 値
     */
    public static float bytes2float(byte[] bytes) {
        return ByteBuffer.wrap(endian(bytes)).getFloat();
    }

    /**
     * <b>リトルエンディアンのバイト配列 → double</b>
     * 
     * @param bytes
     *            リトルエンディアンのバイト配列
     * @return 値
     */
    public static double bytes2double(byte[] bytes) {
        return ByteBuffer.wrap(endian(bytes)).getDouble();
    }

    /**
     * Byte[] から byte[] に変換
     * 
     * @param bytes
     *            Byte[]
     * @return byte[]
     */
    public static byte[] bytes2primBytes(Byte[] bytes) {
        byte[] primBytes = new byte[bytes.length];
        for (int i = 0; i < primBytes.length; i++)
            primBytes[i] = bytes[i];
        return primBytes;
    }

    /**
     * byte[] から Byte[] に変換
     * 
     * @param primBytes
     *            byte[]
     * @return Byte[]
     */
    public static Byte[] primBytes2Bytes(byte[] primBytes) {
        Byte[] bytes = new Byte[primBytes.length];
        for (int i = 0; i < bytes.length; i++)
            bytes[i] = primBytes[i];
        return bytes;
    }

    /**
     * 2次元バイト配列 → 1次元バイト配列に変換
     * 
     * @param bytes2D
     *            2次元バイト配列
     * @return １次元バイト配列
     */
    public static byte[] bytes2D2bytes1D(byte[][] bytes2D) {
        int length = 0;
        for (byte[] b : bytes2D)
            length += b.length;
        byte[] bytes1D = new byte[length];
        int b2d = 0;
        for (int i = 0; i < bytes2D.length; i++) {
            for (int a = 0; a < bytes2D[i].length; a++) {
                bytes1D[b2d + a] = bytes2D[i][a];
            }
            b2d += bytes2D[i].length;
        }
        return bytes1D;
    }
}
