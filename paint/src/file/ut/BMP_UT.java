package file.ut;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import file.io.Bmp;

/**
 * <b>BMPの単体テスト</b><br>
 * date: 2017/10/13 last_date 2017/10/13
 * 
 * @author ソウルP
 */
public class BMP_UT {

    /**
     * <b>BMP 出力</b><br>
     * 1bit<br>
     * 成功テスト
     */
    @Test
    public void bmpOutput_1bit() {
        String file = "C:\\Users\\ユーザ名\\Desktop\\java_1bit.bmp";
        int width = 3;
        int height = 3;
        byte[] img01 = { (byte) 0xa0, 0x00, 0x00, 0x00 };
        byte[] img02 = { 0x40, 0x00, 0x00, 0x00 };
        byte[] img03 = { (byte) 0xa0, 0x00, 0x00, 0x00 };
        List<byte[]> image = new ArrayList<>();
        image.add(img01);
        image.add(img02);
        image.add(img03);

        Bmp bmp = new Bmp();
        bmp.setWidth(width); // 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(1); // ビットの深さ
        bmp.addColor(255, 0, 255); // 色 - 紫
        bmp.addColor(0, 255, 255); // 色 - アクア
        bmp.setImage(image);// イメージ
        bmp.output(file); // 出力
    }

    /**
     * <b>BMP 出力</b><br>
     * 4bit<br>
     * 成功テスト
     */
    @Test
    public void bmpOutput_4bit() {
        String file = "C:\\Users\\ユーザ名\\Desktop\\java_4bit.bmp";
        int width = 3;
        int height = 3;
        byte[] img01 = { 0x10, 0x10, 0x00, 0x00 };
        byte[] img02 = { 0x01, 0x00, 0x00, 0x00 };
        byte[] img03 = { 0x10, 0x10, 0x00, 0x00 };
        List<byte[]> image = new ArrayList<>();
        image.add(img01);
        image.add(img02);
        image.add(img03);

        Bmp bmp = new Bmp();
        bmp.setWidth(width); // 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(4); // ビットの深さ
        bmp.addColor(255, 0, 255); // 色 - 紫
        bmp.addColor(0, 255, 255); // 色 - アクア
        bmp.setImage(image);// イメージ
        bmp.output(file); // 出力
    }

    /**
     * <b>BMP 出力</b><br>
     * 8bit<br>
     * 成功テスト
     */
    @Test
    public void bmpOutput_8bit() {
        String file = "C:\\Users\\ユーザ名\\Desktop\\java_8bit.bmp";
        int width = 3;
        int height = 3;
        byte[] img01 = { 0x01, 0x00, 0x01, 0x00 };
        byte[] img02 = { 0x00, 0x01, 0x00, 0x00 };
        byte[] img03 = { 0x01, 0x00, 0x01, 0x00 };
        List<byte[]> image = new ArrayList<>();
        image.add(img01);
        image.add(img02);
        image.add(img03);

        Bmp bmp = new Bmp();
        bmp.setWidth(width); // 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(8); // ビットの深さ
        bmp.addColor(255, 0, 255); // 色 - 紫
        bmp.addColor(0, 255, 255); // 色 - アクア
        bmp.setImage(image);// イメージ
        bmp.output(file); // 出力
    }

    /**
     * <b>BMP 出力</b><br>
     * 24bit<br>
     * 成功テスト
     */
    @Test
    public void bmpOutput_24bit() {
        String file = "C:\\Users\\ユーザ名\\Desktop\\java_24bit.bmp";
        int width = 3;
        int height = 3;
        byte[] img01 = { (byte) 0xff, (byte) 0xff, 0x00, (byte) 0xff, 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff, 0x00,
                0x00, 0x00, 0x00 };
        byte[] img02 = { (byte) 0xff, 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff, 0x00, (byte) 0xff, 0x00, (byte) 0xff,
                0x00, 0x00, 0x00 };
        byte[] img03 = { (byte) 0xff, (byte) 0xff, 0x00, (byte) 0xff, 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff, 0x00,
                0x00, 0x00, 0x00 };
        List<byte[]> image = new ArrayList<>();
        image.add(img01);
        image.add(img02);
        image.add(img03);

        Bmp bmp = new Bmp();
        bmp.setWidth(width); // 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(24); // ビットの深さ
        bmp.setImage(image);// イメージ
        bmp.output(file); // 出力
    }

    /**
     * <b>BMP 出力</b><br>
     * 32bit<br>
     * 成功テスト
     */
    @Test
    public void bmpOutput_32bit() {
        String file = "C:\\Users\\ユーザ名\\Desktop\\java_32bit.bmp";
        int width = 3;
        int height = 3;
        byte[] img01 = { (byte) 0xff, (byte) 0xff, 0x00, 0x00, (byte) 0xff, 0x00, (byte) 0xff, 0x00, (byte) 0xff,
                (byte) 0xff, 0x00, 0x00 };
        byte[] img02 = { (byte) 0xff, 0x00, (byte) 0xff, 0x00, (byte) 0xff, (byte) 0xff, 0x00, 0x00, (byte) 0xff, 0x00,
                (byte) 0xff, 0x00 };
        byte[] img03 = { (byte) 0xff, (byte) 0xff, 0x00, 0x00, (byte) 0xff, 0x00, (byte) 0xff, 0x00, (byte) 0xff,
                (byte) 0xff, 0x00, 0x00 };
        List<byte[]> image = new ArrayList<>();
        image.add(img01);
        image.add(img02);
        image.add(img03);

        Bmp bmp = new Bmp();
        bmp.setWidth(width); // 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(32); // ビットの深さ
        bmp.setImage(image);// イメージ
        bmp.output(file); // 出力
    }
}
