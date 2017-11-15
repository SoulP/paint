package file.test.ut;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import file.Tools;

/**
 * Toolsの単体テスト<br>
 * date: 2017/11/15 last_date: 2017/11/15
 * 
 * @author ソウルP
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tools_UT {

    /**
     * {@link Tools#endian(byte[])}<br>
     * 成功テスト
     */
    @Test
    public void test000_endian() {
        byte[] before = { 0x00, 0x07, (byte) 0xe1, (byte) 0xff, 0x40 };
        byte[] after = { 0x40, (byte) 0xff, (byte) 0xe1, 0x07, 0x00 };
        byte[] temp = Tools.endian(before);
        assertTrue(Arrays.equals(after, temp));
    }

    /**
     * {@link Tools#short2bytes(short)}<br>
     * 成功テスト
     */
    @Test
    public void test010_short2bytes() {
        short before = 277;
        byte[] after = { 0x15, 0x01 };
        byte[] temp = Tools.short2bytes(before);
        assertTrue(Arrays.equals(after, temp));
    }

    /**
     * {@link Tools#bytes2short(byte[])}<br>
     * 成功テスト
     */
    @Test
    public void test020_bytes2short() {
        byte[] before = { 0x64, 0x01 };
        short after = 356;
        short temp = Tools.bytes2short(before);
        assertEquals(after, temp);
    }

    /**
     * {@link Tools#long2bytes(long)}<br>
     * 成功テスト
     */
    @Test
    public void test030_long2bytes() {
        long before = 5764607523034234880l;
        byte[] after = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x50 };
        byte[] temp = Tools.long2bytes(before);
        assertTrue(Arrays.equals(after, temp));
    }

    /**
     * {@link Tools#bytes2long(byte[])}<br>
     * 成功テスト
     */
    @Test
    public void test040_bytes2long() {
        byte[] before = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x0e, 0x06, 0x22 };
        long after = 2451662440312602624l;
        long temp = Tools.bytes2long(before);
        assertEquals(after, temp);
    }

    /**
     * {@link Tools#float2bytes(float)}<br>
     * 成功テスト
     */
    @Test
    public void test050_float2bytes() {
        float before = 3.14f;
        byte[] after = { (byte) 0xc3, (byte) 0xf5, 0x48, 0x40 };
        byte[] temp = Tools.float2bytes(before);
        assertTrue(Arrays.equals(after, temp));
    }

    /**
     * {@link Tools#bytes2float(byte[])}<br>
     * 成功テスト
     */
    @Test
    public void test060_bytes2float() {
        byte[] before = { (byte) 0xd1, 0x22, (byte) 0x92, 0x41 };
        float after = 18.267f;
        float temp = Tools.bytes2float(before);
        assertThat(after, is(temp));
    }

    /**
     * {@link Tools#double2bytes(double)}<br>
     * 成功テスト
     */
    @Test
    public void test070_double2bytes() {
        double before = 1725.2112d;
        byte[] after = { (byte) 0xa9, 0x13, (byte) 0xd0, 0x44, (byte) 0xd8, (byte) 0xf4, (byte) 0x9a, 0x40 };
        byte[] temp = Tools.double2bytes(before);
        assertTrue(Arrays.equals(after, temp));
    }

    /**
     * {@link Tools#bytes2double(byte[])}<br>
     * 成功テスト
     */
    @Test
    public void test080_bytes2double() {
        byte[] before = { 0x64, 0x5d, (byte) 0xdc, 0x46, 0x6a, (byte) 0x91, (byte) 0xe4, 0x40 };
        double after = 42123.32115d;
        double temp = Tools.bytes2double(before);
        assertThat(after, is(temp));
    }

    /**
     * {@link Tools#bytes2primBytes(Byte[])}<br>
     * 成功テスト
     */
    @Test
    public void test090_bytes2primBytes() {
        Byte[] bytes = { 0x00, 0x23, (byte) 0xfa, (byte) 0xe1 };
        byte[] primBytes = { 0x00, 0x23, (byte) 0xfa, (byte) 0xe1 };
        byte[] temp = Tools.bytes2primBytes(bytes);
        assertTrue(Arrays.equals(primBytes, temp));
    }

    /**
     * {@link Tools#primBytes2Bytes(byte[])}<br>
     * 成功テスト
     */
    @Test
    public void test100_primBytes2bytes() {
        byte[] primBytes = { 0x11, 0x63, 0x22, 0x37 };
        Byte[] bytes = { 0x11, 0x63, 0x22, 0x37 };
        Byte[] temp = Tools.primBytes2Bytes(primBytes);
        assertTrue(Arrays.equals(bytes, temp));
    }

    /**
     * {@link Tools#bytes2D2bytes1D(byte[][])}<br>
     * 成功テスト
     */
    @Test
    public void test110_bytes2D2bytes1D() {
        byte[][] before = { { 0x00, (byte) 0xff, 0x34, 0x42 }, { 0x21, 0x31 } };
        byte[] after = { 0x00, (byte) 0xff, 0x34, 0x42, 0x21, 0x31 };
        byte[] temp = Tools.bytes2D2bytes1D(before);
        assertTrue(Arrays.equals(after, temp));
    }
}
