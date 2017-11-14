package file.test.ut;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import file.Tools;
import file.format.bmp.BMP_V4;
import file.test.Basic;

/**
 * <b>BMP V4 の単体テスト</b><br>
 * date: 2017/10/27 last_date 2017/11/14
 * 
 * @author ソウルP
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BMP_V4_UT extends Basic {
    final String     addr = BASIC_ADDR + "BMP\\BMP_V4_UT\\";
    FileOutputStream out;
    FileInputStream  in;
    BMP_V4           bmp;

    @Before
    public void before() {
        new File(addr).mkdirs();
    }

    /**
     * 出力<br>
     * 1bit<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test000_1bit_output() throws IOException {
        int width = 3;
        int height = 3;
        short bitCount = 1;
        List<byte[]> colors = new ArrayList<>();
        colors.add(new byte[] { (byte) 0xff, 0x00, (byte) 0xff, 0x00 }); // 紫色
        colors.add(new byte[] { (byte) 0xff, (byte) 0xff, 0x00, 0x00 }); // アクア色
        List<byte[]> image = image_1bit;

        bmp = setupBmpV4(width, height, bitCount, colors, image);

        out = new FileOutputStream(addr + "test000_1bit.bmp");
        out.write(bmp.get());
        out.flush();
        out.close();
    }

    /**
     * 出力<br>
     * 4bit<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test010_4bit_output() throws IOException {
        int width = 3;
        int height = 3;
        short bitCount = 4;
        List<byte[]> colors = new ArrayList<>();
        colors.add(new byte[] { (byte) 0xff, 0x00, (byte) 0xff, 0x00 }); // 紫色
        colors.add(new byte[] { (byte) 0xff, (byte) 0xff, 0x00, 0x00 }); // アクア色
        List<byte[]> image = image_4bit;

        bmp = setupBmpV4(width, height, bitCount, colors, image);

        out = new FileOutputStream(addr + "test010_4bit.bmp");
        out.write(bmp.get());
        out.flush();
        out.close();
    }

    /**
     * 出力<br>
     * 4bit<br>
     * 圧縮形式: BI_RLE4<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test011_4bit_output() throws IOException {
        int width = 14;
        int height = 14;
        short bitCount = 4;
        List<byte[]> image = new ArrayList<>();
        for (byte[] img : image_4bit_BI_RLE4)
            image.add(img);

        bmp = new BMP_V4();
        for (int i = 255; i >= 21; i -= 18) {
            bmp.addColor(0, i, i); // アクア色
        }

        bmp = setupBmpV4(width, height, bitCount, bmp.getColors(), image);
        bmp.setCompression(2);

        out = new FileOutputStream(addr + "test011_4bit_BI_RLE4.bmp");
        out.write(bmp.get());
        out.flush();
        out.close();
    }

    /**
     * 出力<br>
     * 8bit<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test020_8bit_output() throws IOException {
        int width = 3;
        int height = 3;
        short bitCount = 8;
        List<byte[]> colors = new ArrayList<>();
        colors.add(new byte[] { (byte) 0xff, 0x00, (byte) 0xff, 0x00 }); // 紫色
        colors.add(new byte[] { (byte) 0xff, (byte) 0xff, 0x00, 0x00 }); // アクア色
        List<byte[]> image = image_8bit;

        bmp = setupBmpV4(width, height, bitCount, colors, image);

        out = new FileOutputStream(addr + "test020_8bit.bmp");
        out.write(bmp.get());
        out.flush();
        out.close();
    }

    /**
     * 出力<br>
     * 8bit<br>
     * 圧縮形式: BI_RLE4<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test021_8bit_output() throws IOException {
        int width = 13;
        int height = 13;
        short bitCount = 8;
        List<byte[]> image = new ArrayList<>();
        for (byte[] img : image_8bit_BI_RLE8)
            image.add(img);

        bmp = new BMP_V4();
        for (int i = 255; i >= 27; i -= 19) {
            bmp.addColor(i, 0, 0); // 赤色
            bmp.addColor(0, i, 0); // 緑色
            bmp.addColor(0, 0, i); // 青色
        }
        bmp.addColor(255, 255, 0); // 黄色
        bmp.addColor(0, 255, 255); // アクア色
        bmp.addColor(0, 0, 0); // 黒色
        bmp.addColor(255, 255, 255); // 白色

        bmp = setupBmpV4(width, height, bitCount, bmp.getColors(), image);
        bmp.setCompression(1);

        out = new FileOutputStream(addr + "test021_8bit_BI_RLE8.bmp");
        out.write(bmp.get());
        out.flush();
        out.close();
    }

    /**
     * 出力<br>
     * 16bit<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test030_16bit_output() throws IOException {
        int width = 3;
        int height = 3;
        short bitCount = 16;
        List<byte[]> image = image_16bit;

        bmp = setupBmpV4(width, height, bitCount, null, image);

        out = new FileOutputStream(addr + "test030_16bit.bmp");
        out.write(bmp.get());
        out.flush();
        out.close();
    }

    /**
     * 出力<br>
     * 16bit<br>
     * ビットフィールド<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test031_16bit_output() throws IOException {
        int width = 3;
        int height = 3;
        short bitCount = 16;
        List<byte[]> image = image_16bit_BitFields;

        bmp = setupBmpV4(width, height, bitCount, null, image);
        bmp.setCompression(3);
        bmp.setRedMask(Tools.endian(r5g5b5MaskV4[0]));
        bmp.setGreenMask(Tools.endian(r5g5b5MaskV4[1]));
        bmp.setBlueMask(Tools.endian(r5g5b5MaskV4[2]));

        out = new FileOutputStream(addr + "test031_16bit_BitFields.bmp");
        out.write(bmp.get());
        out.flush();
        out.close();
    }

    /**
     * 出力<br>
     * 24bit<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test040_24bit_output() throws IOException {
        int width = 3;
        int height = 3;
        short bitCount = 24;
        List<byte[]> image = image_24bit;

        bmp = setupBmpV4(width, height, bitCount, null, image);

        out = new FileOutputStream(addr + "test040_24bit.bmp");
        out.write(bmp.get());
        out.flush();
        out.close();
    }

    /**
     * 出力<br>
     * 32bit<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test050_32bit_output() throws IOException {
        int width = 3;
        int height = 3;
        short bitCount = 32;
        List<byte[]> image = image_32bit;

        bmp = setupBmpV4(width, height, bitCount, null, image);

        out = new FileOutputStream(addr + "test050_32bit.bmp");
        out.write(bmp.get());
        out.flush();
        out.close();
    }

    /**
     * 出力<br>
     * 32bit<br>
     * ビットフィールド<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test051_32bit_output() throws IOException {
        int width = 3;
        int height = 3;
        short bitCount = 32;
        List<byte[]> image = image_32bit_BitFields;

        bmp = setupBmpV4(width, height, bitCount, null, image);
        bmp.setCompression(3);
        bmp.setRedMask(bitFields32bitMaskV4[0]);
        bmp.setGreenMask(bitFields32bitMaskV4[1]);
        bmp.setBlueMask(bitFields32bitMaskV4[2]);

        out = new FileOutputStream(addr + "test051_32bit_BitFields.bmp");
        out.write(bmp.get());
        out.flush();
        out.close();
    }

    /**
     * 入力<br>
     * 1bit<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test100_1bit_input() throws IOException {
        in = new FileInputStream(addr + "test000_1bit.bmp");
        byte[] data = new byte[in.available()];
        in.read(data);
        in.close();

        bmp = new BMP_V4(data);

        System.out.println(bmp);
    }

    /**
     * 入力<br>
     * 4bit<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test110_4bit_input() throws IOException {
        in = new FileInputStream(addr + "test010_4bit.bmp");
        byte[] data = new byte[in.available()];
        in.read(data);
        in.close();

        bmp = new BMP_V4(data);

        System.out.println(bmp);
    }

    /**
     * 入力<br>
     * 4bit<br>
     * 圧縮形式: BI_RLE4<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test111_4bit_input() throws IOException {
        in = new FileInputStream(addr + "test011_4bit_BI_RLE4.bmp");
        byte[] data = new byte[in.available()];
        in.read(data);
        in.close();

        bmp = new BMP_V4(data);

        System.out.println(bmp);
    }

    /**
     * 入力<br>
     * 8bit<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test120_8bit_input() throws IOException {
        in = new FileInputStream(addr + "test020_8bit.bmp");
        byte[] data = new byte[in.available()];
        in.read(data);
        in.close();

        bmp = new BMP_V4(data);

        System.out.println(bmp);
    }

    /**
     * 入力<br>
     * 8bit<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test122_8bit_input() throws IOException {
        in = new FileInputStream(addr + "test021_8bit_BI_RLE8.bmp");
        byte[] data = new byte[in.available()];
        in.read(data);
        in.close();

        bmp = new BMP_V4(data);

        System.out.println(bmp);
    }

    /**
     * 入力<br>
     * 16bit<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test130_16bit_input() throws IOException {
        in = new FileInputStream(addr + "test030_16bit.bmp");
        byte[] data = new byte[in.available()];
        in.read(data);
        in.close();

        bmp = new BMP_V4(data);

        System.out.println(bmp);
    }

    /**
     * 入力<br>
     * 16bit<br>
     * ビットフィールド<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test131_16bit_input() throws IOException {
        in = new FileInputStream(addr + "test031_16bit_BitFields.bmp");
        byte[] data = new byte[in.available()];
        in.read(data);
        in.close();

        bmp = new BMP_V4(data);

        System.out.println(bmp);
    }

    /**
     * 入力<br>
     * 24bit<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test140_24bit_input() throws IOException {
        in = new FileInputStream(addr + "test040_24bit.bmp");
        byte[] data = new byte[in.available()];
        in.read(data);
        in.close();

        bmp = new BMP_V4(data);

        System.out.println(bmp);
    }

    /**
     * 入力<br>
     * 32bit<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test150_32bit_input() throws IOException {
        in = new FileInputStream(addr + "test050_32bit.bmp");
        byte[] data = new byte[in.available()];
        in.read(data);
        in.close();

        bmp = new BMP_V4(data);

        System.out.println(bmp);
    }

    /**
     * 入力<br>
     * 32bit<br>
     * ビットフィールド<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test151_32bit_input() throws IOException {
        in = new FileInputStream(addr + "test051_32bit_BitFields.bmp");
        byte[] data = new byte[in.available()];
        in.read(data);
        in.close();

        bmp = new BMP_V4(data);

        System.out.println(bmp);
    }

    private BMP_V4 setupBmpV4(int width, int height, short bitCount, List<byte[]> colors, List<byte[]> image) {
        bmp = new BMP_V4();

        bmp.setWidth(width); // 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(bitCount); // ビットの深さ
        bmp.setImage(image); // イメージ

        int fileSize = bmp.getFileHeaderSize() + bmp.getInfoHeaderSize();

        if (!(colors == null || colors.isEmpty())) {
            bmp.setColors(colors); // 色
            int colorSize = 0;
            for (byte[] c : bmp.getColors())
                colorSize += c.length;
            fileSize += colorSize;
        }

        int imageSize = 0;
        for (byte[] i : image_8bit)
            imageSize += i.length;

        bmp.setOffset(fileSize);
        fileSize += imageSize;
        bmp.setFileSize(fileSize);

        return bmp;
    }
}
