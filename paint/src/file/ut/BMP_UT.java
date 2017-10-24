package file.ut;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import file.io.BMP;

/**
 * <b>BMPの単体テスト</b><br>
 * date: 2017/10/13 last_date 2017/10/24
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
        String file = "C:\\Users\\ユーザ名\\Desktop\\java_1bit.bmp"; // 出力先
        int width = 3;
        int height = 3;

        // イメージ情報
        byte[] img01 = { (byte) 0xa0, 0x00, 0x00, 0x00 };
        byte[] img02 = { 0x40, 0x00, 0x00, 0x00 };
        byte[] img03 = { (byte) 0xa0, 0x00, 0x00, 0x00 };
        List<byte[]> image = new ArrayList<>();
        image.add(img01);
        image.add(img02);
        image.add(img03);

        BMP bmp = new BMP();
        bmp.setWidth(width); // 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount((short) 1); // ビットの深さ
        bmp.addColor(255, 0, 255); // 紫色
        bmp.addColor(0, 255, 255); // アクア色
        bmp.setImage(image);// イメージ
        try {
            bmp.output(file);// 出力
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * <b>BMP 出力</b><br>
     * 4bit<br>
     * 成功テスト
     */
    @Test
    public void bmpOutput_4bit() {
        String file = "C:\\Users\\ユーザ名\\Desktop\\java_4bit.bmp"; // 出力先
        int width = 3;
        int height = 3;
        byte[] img01 = { 0x10, 0x10, 0x00, 0x00 };
        byte[] img02 = { 0x01, 0x00, 0x00, 0x00 };
        byte[] img03 = { 0x10, 0x10, 0x00, 0x00 };
        List<byte[]> image = new ArrayList<>();
        image.add(img01);
        image.add(img02);
        image.add(img03);

        BMP bmp = new BMP();
        bmp.setWidth(width); // 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount((short) 4); // ビットの深さ
        bmp.addColor(255, 0, 255); // 紫色
        bmp.addColor(0, 255, 255); // アクア色
        bmp.setImage(image);// イメージ
        try {
            bmp.output(file);// 出力
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * <b>BMP 出力</b><br>
     * 8bit<br>
     * 成功テスト
     */
    @Test
    public void bmpOutput_8bit() {
        String file = "C:\\Users\\ユーザ名\\Desktop\\java_8bit.bmp"; // 出力先
        int width = 3;
        int height = 3;

        // イメージ情報
        byte[] img01 = { 0x01, 0x00, 0x01, 0x00 };
        byte[] img02 = { 0x00, 0x01, 0x00, 0x00 };
        byte[] img03 = { 0x01, 0x00, 0x01, 0x00 };
        List<byte[]> image = new ArrayList<>();
        image.add(img01);
        image.add(img02);
        image.add(img03);

        BMP bmp = new BMP();
        bmp.setWidth(width); // 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount((short) 8); // ビットの深さ
        bmp.addColor(255, 0, 255); // 紫色
        bmp.addColor(0, 255, 255); // アクア色
        bmp.setImage(image);// イメージ
        try {
            bmp.output(file);// 出力
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * <b>BMP 出力</b><br>
     * 24bit<br>
     * 成功テスト
     */
    @Test
    public void bmpOutput_24bit() {
        String file = "C:\\Users\\ユーザ名\\Desktop\\java_24bit.bmp"; // 出力先
        int width = 3;
        int height = 3;
        // @formatter:off
        // イメージ情報
        byte[] img01 = {
                    (byte) 0xff, (byte) 0xff, 0x00,
                    (byte) 0xff, 0x00, (byte) 0xff,
                    (byte) 0xff, (byte) 0xff, 0x00,
                    0x00, 0x00, 0x00
                };
        byte[] img02 = {
                    (byte) 0xff, 0x00, (byte) 0xff,
                    (byte) 0xff, (byte) 0xff, 0x00,
                    (byte) 0xff, 0x00, (byte) 0xff,
                    0x00, 0x00, 0x00
                };
        byte[] img03 = {
                    (byte) 0xff, (byte) 0xff, 0x00,
                    (byte) 0xff, 0x00, (byte) 0xff,
                    (byte) 0xff, (byte) 0xff, 0x00,
                    0x00, 0x00, 0x00
                };
        // @formatter:on

        List<byte[]> image = new ArrayList<>();
        image.add(img01);
        image.add(img02);
        image.add(img03);

        BMP bmp = new BMP();
        bmp.setWidth(width); // 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount((short) 24); // ビットの深さ
        bmp.setImage(image);// イメージ
        try {
            bmp.output(file);// 出力
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * <b>BMP 出力</b><br>
     * 32bit<br>
     * 成功テスト
     */
    @Test
    public void bmpOutput_32bit() {
        String file = "C:\\Users\\ユーザ名\\Desktop\\java_32bit.bmp"; // 出力先
        int width = 3;
        int height = 3;
        // @formatter:off
        // イメージ情報
        byte[] img01 = {
                    (byte) 0xff, (byte) 0xff, 0x00, 0x00,
                    (byte) 0xff, 0x00, (byte) 0xff, 0x00,
                    (byte) 0xff, (byte) 0xff, 0x00, 0x00
                };
        byte[] img02 = {
                    (byte) 0xff, 0x00, (byte) 0xff, 0x00,
                    (byte) 0xff, (byte) 0xff, 0x00, 0x00,
                    (byte) 0xff, 0x00, (byte) 0xff, 0x00
                };
        byte[] img03 = {
                    (byte) 0xff, (byte) 0xff, 0x00, 0x00,
                    (byte) 0xff, 0x00, (byte) 0xff, 0x00,
                    (byte) 0xff, (byte) 0xff, 0x00, 0x00
                };
        // @formatter:on

        List<byte[]> image = new ArrayList<>();
        image.add(img01);
        image.add(img02);
        image.add(img03);

        BMP bmp = new BMP();
        bmp.setWidth(width); // 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount((short) 32); // ビットの深さ
        bmp.setImage(image);// イメージ
        try {
            bmp.output(file);// 出力
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * <b>BMP 出力</b><br>
     * 8bit<br>
     * 圧縮形式: BI_RLE8<br>
     * 成功テスト
     */
    @Test
    public void bmpOutput_8bit_BI_RLE8() {
        String file = "C:\\Users\\ユーザ名\\Desktop\\java_8bit_BI_RLE8.bmp"; // 出力先
        int width = 13;
        int height = 13;
        // @formatter:off
        // イメージ情報
        byte[][] img = {
                { 0x0d, 0x28, 0x00, 0x00 },
                { 0x03, 0x28, 0x0a, 0x00, 0x00, 0x00 },
                { 0x08, 0x00, 0x05, 0x27, 0x00, 0x00 },
                { 0x0d, 0x27, 0x00, 0x00 },
                { 0x00, 0x0d, 0x1a, 0x1b, 0x1c, 0x1d, 0x1e, 0x1f, 0x20, 0x21, 0x22, 0x23, 0x24, 0x25, 0x26, 0x00, 0x00, 0x00 },
                { 0x00, 0x0d, 0x0d, 0x0e, 0x0f, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19, 0x00, 0x00, 0x00 },
                { 0x00, 0x0d, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x00, 0x00, 0x00 },
                { 0x0d, 0x2a, 0x00, 0x00 },
                { 0x0d, 0x29, 0x00, 0x00 },
                { 0x06, 0x29, 0x01, 0x2a, 0x06, 0x29, 0x00, 0x00 },
                { 0x0d, 0x29, 0x00, 0x00 },
                { 0x02, 0x29, 0x01, 0x2a, 0x07, 0x29, 0x01, 0x2a, 0x02, 0x29, 0x00, 0x00 },
                { 0x0d, 0x29, 0x00, 0x01 }
            };
        // @formatter:on

        BMP bmp = new BMP();
        bmp.setWidth(width); // 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount((short) 8); // ビットの深さ
        bmp.setCompression(1); // 圧縮形式
        for (int i = 255; i >= 27; i -= 19) {
            bmp.addColor(i, 0, 0); // 赤色
            bmp.addColor(0, i, 0); // 緑色
            bmp.addColor(0, 0, i); // 青色
        }
        bmp.addColor(255, 255, 0); // 黄色
        bmp.addColor(0, 255, 255); // アクア色
        bmp.addColor(0, 0, 0); // 黒色
        bmp.addColor(255, 255, 255); // 白色
        bmp.setImage(img);// イメージ
        try {
            bmp.output(file);// 出力
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * <b>BMP 出力</b><br>
     * 4bit<br>
     * 圧縮形式: BI_RLE4<br>
     * 成功テスト
     */
    @Test
    public void bmpOutput_4bit_BI_RLE4() {
        String file = "C:\\Users\\ユーザ名\\Desktop\\java_4bit_BI_RLE4.bmp"; // 出力先
        int width = 14;
        int height = 14;
        // @formatter:off
        // イメージ情報
        byte[][] img = {
                { 0x0e, (byte) 0xdd, 0x00, 0x00 },
                { 0x0e, (byte) 0xcc, 0x00, 0x00 },
                { 0x02, (byte) 0xbb, 0x0a, 0x22, 0x02, (byte) 0xbb, 0x00, 0x00 },
                { 0x02, (byte) 0xaa, 0x01, 0x30, 0x08, (byte) 0xaa, 0x01, 0x30, 0x02, (byte) 0xaa, 0x00, 0x00 },
                { 0x02, (byte) 0x99, 0x02, 0x49, 0x06, 0x44, 0x02, (byte) 0x94, 0x02, (byte) 0x99, 0x00, 0x00 },
                { 0x02, (byte) 0x88, 0x03, 0x58, 0x04, (byte) 0x88, 0x03, 0x58, 0x02, (byte) 0x88, 0x00, 0x00 },
                { 0x02, 0x77, 0x04, 0x67, 0x02, 0x66, 0x04, 0x76, 0x02, 0x77, 0x00, 0x00 },
                { 0x02, 0x66, 0x04, (byte) 0x76, 0x02, (byte) 0x77, 0x04, 0x67, 0x02, 0x66, 0x00, 0x00 },
                { 0x02, 0x55, 0x03, (byte) 0x85, 0x04, 0x55, 0x03, (byte) 0x85, 0x02, 0x55, 0x00, 0x00 },
                { 0x02, 0x44, 0x02, (byte) 0x94, 0x06, (byte) 0x99, 0x02, 0x49, 0x02, 0x44, 0x00, 0x00 },
                { 0x02, 0x33, 0x01, (byte) 0xa0, 0x08, 0x33, 0x01, (byte) 0xa0, 0x02, 0x33, 0x00, 0x00 },
                { 0x02, 0x22, 0x0a, (byte) 0xbb, 0x02, 0x22, 0x00, 0x00 },
                { 0x0e, 0x11, 0x00, 0x00 },
                { 0x0e, 0x00, 0x00, 0x01 }
        };
        // @formatter:on

        BMP bmp = new BMP();
        bmp.setWidth(width); // 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount((short) 4); // ビットの深さ
        bmp.setCompression(2); // 圧縮形式
        for (int i = 255; i >= 21; i -= 18) {
            bmp.addColor(0, i, i); // アクア色
        }
        bmp.setImage(img);// イメージ
        try {
            bmp.output(file);// 出力
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * <b>BMP 入力</b><br>
     * 成功テスト
     */
    @Test
    public void bmpInput() {
        String file = "C:\\Users\\ユーザ名\\Desktop\\java_4bit_BI_RLE4.bmp";
        String comp = "";

        BMP bmp = new BMP();
        try {
            bmp.input(file);
        } catch (IOException e) {
            fail(e.getMessage());
        }

        System.out.println(file);
        System.out.println("BMP V" + bmp.getVersion());
        System.out.println("ファイルサイズ: " + bmp.getFileSize());
        System.out.println("幅: " + bmp.getWidth());
        System.out.println("高さ: " + bmp.getHeight());
        System.out.println("ビットの深さ: " + bmp.getBitCount() + "bit");
        switch (bmp.getCompression()) {
            case 0:
                comp = "BI_RGB （非圧縮）";
                break;
            case 1:
                comp = "BI_RLE8 （8ビット/ピクセル）";
                break;
            case 2:
                comp = "BI_RLE4 （4ビット/ピクセル）";
                break;
            case 3:
                comp = "BI_BITFIELDS （ビットフィールド）";
                break;
            case 4:
                comp = "BI_JPEG";
                break;
            case 5:
                comp = "BI_PNG";
                break;
            case 6:
                comp = "BI_ALPHABITFIELDS （アルファチャンネル付きビットフィールド）";
                break;
            default:
                comp = "不明";
                break;
        }
        System.out.println("圧縮形式: " + comp);
        System.out.println("画像データサイズ: " + bmp.getImageSize());
        System.out.println("水平方向の解像度: " + bmp.getPixPerMeterX());
        System.out.println("垂直方向の解像度: " + bmp.getPixPerMeterY());
        System.out.println("使用する色数: " + bmp.getColors().size());
        System.out.println("重要な色数: " + bmp.getCirImportant());
        System.out.println();
        System.out.println("【カラーパレット】");
        bmp.getColors().forEach(color -> {
            System.out.print("R = " + String.format("%3s", Byte.toUnsignedInt(color[2])) + ", ");
            System.out.print("G = " + String.format("%3s", Byte.toUnsignedInt(color[1])) + ", ");
            System.out.print("B = " + String.format("%3s", Byte.toUnsignedInt(color[0])));
            System.out.println();
        });
        System.out.println();
        System.out.println("【イメージ】");
        bmp.getImage().forEach(bA -> {
            for (byte b : bA)
                System.out.print(String.format("%02X ", b));
            System.out.println();
        });
    }

    /**
     * <b>BMP 入力</b><br>
     * 成功テスト
     */
    @Test
    public void bmpInput_02() {
        String file = "C:\\Users\\ユーザ名\\Desktop\\java_4bit_BI_RLE4.bmp";

        BMP bmp = new BMP();
        try {
            bmp.input(file);
        } catch (IOException e) {
            fail(e.getMessage());
        }

        System.out.println(file);
        System.out.println(bmp);
    }
}
