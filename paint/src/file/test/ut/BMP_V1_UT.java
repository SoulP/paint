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

import file.format.bmp.BMP_V1;
import file.test.Basic;

/**
 * <b>BMP V1 の単体テスト</b><br>
 * date: 2017/10/27 last_date 2017/11/14
 * 
 * @author ソウルP
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BMP_V1_UT extends Basic {
    final String     addr = BASIC_ADDR + "BMP\\BMP_V1_UT\\";
    FileOutputStream out;
    FileInputStream  in;
    BMP_V1           bmp;

    @Before
    public void before() {
        new File(addr).mkdirs();
    }

    /**
     * 出力<br>
     * 8bit<br>
     * 成功テスト<br>
     * <p>
     * 8ビット以下の場合、OS/2しか対応されていない。<br>
     * 16ビット以上だと、Windowsでも問題なく開ける。
     * </p>
     * 
     * @throws IOException
     */
    @Test
    public void test000_8bit_output() throws IOException {
        short width = 3;
        short height = 3;
        short bitCount = 8;
        List<byte[]> colors = new ArrayList<>();
        colors.add(new byte[] { (byte) 0xff, 0x00, (byte) 0xff }); // 紫色
        colors.add(new byte[] { (byte) 0xff, (byte) 0xff, 0x00 }); // アクア色
        List<byte[]> image = image_8bit;

        bmp = setupBmpV1(width, height, bitCount, colors, image);

        out = new FileOutputStream(addr + "test000_8bit.bmp");
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
    public void test010_16bit_output() throws IOException {
        short width = 3;
        short height = 3;
        short bitCount = 16;
        List<byte[]> image = image_16bit;

        bmp = setupBmpV1(width, height, bitCount, null, image);

        out = new FileOutputStream(addr + "test010_16bit.bmp");
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
    public void test020_24bit_output() throws IOException {
        short width = 3;
        short height = 3;
        short bitCount = 24;
        List<byte[]> image = image_24bit;

        bmp = setupBmpV1(width, height, bitCount, null, image);

        out = new FileOutputStream(addr + "test020_24bit.bmp");
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
    public void test030_32bit_output() throws IOException {
        short width = 3;
        short height = 3;
        short bitCount = 32;
        List<byte[]> image = image_32bit;

        bmp = setupBmpV1(width, height, bitCount, null, image);

        out = new FileOutputStream(addr + "test030_32bit.bmp");
        out.write(bmp.get());
        out.flush();
        out.close();
    }

    /**
     * 入力<br>
     * 8bit<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test100_8bit_input() throws IOException {
        in = new FileInputStream(addr + "test000_8bit.bmp");
        byte[] data = new byte[in.available()];
        in.read(data);
        in.close();

        bmp = new BMP_V1(data);

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
    public void test110_16bit_input() throws IOException {
        in = new FileInputStream(addr + "test010_16bit.bmp");
        byte[] data = new byte[in.available()];
        in.read(data);
        in.close();

        bmp = new BMP_V1(data);

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
    public void test120_24bit_input() throws IOException {
        in = new FileInputStream(addr + "test020_24bit.bmp");
        byte[] data = new byte[in.available()];
        in.read(data);
        in.close();

        bmp = new BMP_V1(data);

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
    public void test130_32bit_input() throws IOException {
        in = new FileInputStream(addr + "test030_32bit.bmp");
        byte[] data = new byte[in.available()];
        in.read(data);
        in.close();

        bmp = new BMP_V1(data);

        System.out.println(bmp);
    }

    private BMP_V1 setupBmpV1(short width, short height, short bitCount, List<byte[]> colors, List<byte[]> image) {
        bmp = new BMP_V1();

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
