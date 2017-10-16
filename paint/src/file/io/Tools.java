package file.io;

import java.nio.ByteBuffer;

/**
 * <b>ツール</b><br>
 * 変換など<br>
 * date: 2017/10/13 last_date: 2017/10/16
 * 
 * @author ソウルP
 * @version 1.0 2017/10/13 Tools作成
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
     * <b>部分バイト配列の取得</b>
     * 
     * @param bytes
     *            バイト配列
     * @param beginIndex
     *            開始インデックス (この値を含む)
     * @param endIndex
     *            終了インデックス (この値を含まない)
     * @return 指定された部分バイト配列
     */
    public static byte[] subbytes(byte[] bytes, int beginIndex, int endIndex) {
        int size;
        if ((size = endIndex - beginIndex) <= 0) throw new IndexOutOfBoundsException();
        byte[] b = new byte[size];
        int count = 0;
        for (int i = beginIndex; i < endIndex; i++)
            b[count++] = bytes[i];
        return b;
    }
}
