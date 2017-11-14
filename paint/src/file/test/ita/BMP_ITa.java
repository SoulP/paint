package file.test.ita;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import file.Tools;
import file.io.BMP;
import file.test.Settings;

/**
 * <b>BMPの内部結合テスト</b><br>
 * date: 2017/10/13 last_date 2017/11/14
 * 
 * @author ソウルP
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BMP_ITa extends Settings {
    public final String addr = BASIC_ADDR + "BMP\\BMP_ITa\\";

    /**
     * <b>BMP 出力</b><br>
     * 1bit<br>
     * 成功テスト
     */
    @Test
    public void test000_bmpOutput_1bit() {
        String file = addr + "java_1bit.bmp";
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
        bmp.setBitCount(1); // ビットの深さ
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
    public void test010_bmpOutput_4bit() {
        String file = addr + "java_4bit.bmp";
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
        bmp.setBitCount(4); // ビットの深さ
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
     * 圧縮形式: BI_RLE4<br>
     * 成功テスト
     */
    @Test
    public void test011_bmpOutput_4bit_BI_RLE4() {
        String file = addr + "java_4bit_BI_RLE4.bmp";
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
     * <b>BMP 出力</b><br>
     * 8bit<br>
     * 成功テスト
     */
    @Test
    public void test020_bmpOutput_8bit() {
        String file = addr + "java_8bit.bmp";
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
        bmp.setBitCount(8); // ビットの深さ
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
     * 圧縮形式: BI_RLE8<br>
     * 成功テスト
     */
    @Test
    public void test021_bmpOutput_8bit_BI_RLE8() {
        String file = addr + "java_8bit_BI_RLE8.bmp";
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
     * 16 bit<br>
     * 16 bit の場合、画像データの１ピクセルのデータに対してリトルエンディアンになる<br>
     * デフォルト: R5 G6 B5<br>
     * 成功テスト
     */
    @Test
    public void test030_bmpOutput_16bit() {
        String file = addr + "java_16bit.bmp";
        int width = 3;
        int height = 3;
        // @formatter:off
        // イメージ情報 リトルエンディアン
        byte[] img01 = {
                    (byte) 0xff, 0x07,
                    0x1f, (byte) 0xf8,
                    (byte) 0xff, 0x07,
                    0x00, 0x00
                };
        byte[] img02 = {
                    0x1f, 0x7c,
                    (byte) 0xff, 0x07,
                    0x1f, 0x7c,
                    0x00, 0x00
                };
        byte[] img03 = {
                    (byte) 0xff, 0x07,
                    0x1f, (byte) 0xf8,
                    (byte) 0xff, 0x07,
                    0x00, 0x00
                };
        // @formatter:on

        List<byte[]> image = new ArrayList<>();
        image.add(img01);
        image.add(img02);
        image.add(img03);

        BMP bmp = new BMP();
        bmp.setWidth(width);// 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(16); // ビットの深さ
        bmp.setImage(image); // イメージ
        try {
            bmp.output(file);// 出力
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * <b>BMP 出力</b><br>
     * 16 bit<br>
     * 16 bit の場合、画像データの１ピクセルのデータに対してリトルエンディアンになる<br>
     * 同じくBitFieldsのRGB順のデータをリトルエンディアンになる<br>
     * ビットフィールドで設定: R5 G5 B5<br>
     * 成功テスト
     */
    @Test
    public void test031_bmpOutput_16bit_BitFields() {
        String file = addr + "java_16bit_BitFields.bmp";
        int width = 3;
        int height = 3;
        // @formatter:off
        // イメージ情報 リトルエンディアン
        byte[] img01 = {
                    (byte) 0xe0, (byte) 0x7f,
                    (byte) 0x1f, 0x7c,
                    (byte) 0xe0, (byte) 0x7f,
                    0x00, 0x00
                };
        byte[] img02 = {
                    (byte) 0x1f, 0x7c,
                    (byte) 0xe0, (byte) 0x7f,
                    (byte) 0x1f, 0x7c,
                    0x00, 0x00
                };
        byte[] img03 = {
                    (byte) 0xe0, (byte) 0x7f,
                    (byte) 0x1f, 0x7c,
                    (byte) 0xe0, (byte) 0x7f,
                    0x00, 0x00
                };
        // @formatter:on

        List<byte[]> image = new ArrayList<>();
        image.add(img01);
        image.add(img02);
        image.add(img03);

        // @formatter:off
        
        // ビットフィールドのカラーマスク ビッグエンディアン
        byte[][] colormask = {
                { 0x00, 0x00, 0x7c, 0x00 },         // 赤マスク
                { 0x00, 0x00, 0x03, (byte) 0xe0 },  // 緑マスク
                { 0x00, 0x00, 0x00, 0x1f }          // 青マスク
        };
        // @formatter:on

        byte[] bitfields = Tools.bytes2D2bytes1D(colormask); // ビットフィールド

        BMP bmp = new BMP();
        bmp.setWidth(width);// 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(16); // ビットの深さ
        bmp.setCompression(3); // 圧縮形式
        bmp.setBitFields(bitfields); // ビットフィールド
        bmp.setImage(image); // イメージ
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
    public void test040_bmpOutput_24bit() {
        String file = addr + "java_24bit.bmp";
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
        bmp.setBitCount(24); // ビットの深さ
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
    public void test050_bmpOutput_32bit() {
        String file = addr + "java_32bit.bmp";
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
        bmp.setBitCount(32); // ビットの深さ
        bmp.setImage(image);// イメージ
        try {
            bmp.output(file);// 出力
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * <b>BMP 出力</b><br>
     * 16 bit<br>
     * 16 bit の場合、画像データの１ピクセルのデータに対してリトルエンディアンになる<br>
     * 同じくBitFieldsのRGB順のデータをリトルエンディアンになる<br>
     * ビットフィールドで設定: R5 G5 B5<br>
     * 成功テスト
     */
    @Test
    public void test051_bmpOutput_32bit_BitFields() {
        String file = addr + "java_32bit_BitFields.bmp";
        int width = 3;
        int height = 3;
        // @formatter:off
        // イメージ情報
        byte[] img01 = {
                    0x00, 0x0f, (byte) 0xf0, (byte) 0xff,
                    (byte) 0xff, 0x00, 0x00, (byte) 0xff,
                    0x00, 0x0f, (byte) 0xf0, (byte) 0xff
                };
        byte[] img02 = {
                    (byte) 0xff, 0x00, 0x00, (byte) 0xff,
                    0x00, 0x0f, (byte) 0xf0, (byte) 0xff,
                    (byte) 0xff, 0x00, 0x00, (byte) 0xff
                };
        byte[] img03 = {
                    0x00, 0x0f, (byte) 0xf0, (byte) 0xff,
                    (byte) 0xff, 0x00, 0x00, (byte) 0xff,
                    0x00, 0x0f, (byte) 0xf0, (byte) 0xff
                };
        //img01 = Tools.endian(img01);
        //img02 = Tools.endian(img02);
        //img03 = Tools.endian(img03);
        // @formatter:on

        List<byte[]> image = new ArrayList<>();
        image.add(img01);
        image.add(img02);
        image.add(img03);

        // @formatter:off
        
        // ビットフィールドのカラーマスク
        /*
         * ビッグエンディアンだが、緑マスクの0x000FF000の場合、
         * ビッグエンディアンからリトルエンディアン変換でバイト毎として 0f f0 と分かれる為、
         * リトルエンディアンに変換される時は、 f0 0f となってしまい、
         * 正しくない緑マスクに設定されので、予め、その一部だけリトルエンディアンにしておくと良い。
         * 
         * 要するに、バイト単位はビッグエンディアン、ビット単位はリトルエンディアンとして値を入れると良い
         * 【例え】
         * 0xff000000 → 0xff000000
         * 0x0ff00000 → 0xf00f0000 と カラーマスク値を入れる
         */
        byte[][] colormask = {
                { (byte) 0xff, 0x00, 0x00, 0x00 },  // 赤マスク
                { 0x00, (byte) 0xf0, 0x0f,  0x00 }, // 緑マスク
                { 0x00, 0x00, 0x00, (byte) 0xff }   // 青マスク
        };
        // @formatter:on

        byte[] bitfields = Tools.bytes2D2bytes1D(colormask); // ビットフィールド

        BMP bmp = new BMP();
        bmp.setWidth(width);// 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(32); // ビットの深さ
        bmp.setCompression(3); // 圧縮形式
        bmp.setBitFields(bitfields); // ビットフィールド
        bmp.setImage(image); // イメージ
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
    public void test100_bmpInput() {
        String file = addr + "java_4bit_BI_RLE4.bmp";
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
    public void test110_bmpInput() {
        String file = addr + "java_8bit_BI_RLE8.bmp";

        BMP bmp = new BMP();
        try {
            bmp.input(file);
        } catch (IOException e) {
            fail(e.getMessage());
        }

        System.out.println(file);
        System.out.println(bmp);
    }

    /**
     * <b>BMP バージョン変更</b><br>
     * 成功テスト
     */
    @Test
    public void test200_bmpChangeVersion() {
        BMP bmp = new BMP();
        System.out.println("変更前: V" + bmp.getVersion());
        assertEquals(bmp.getVersion(), 3);

        bmp.setVersion(5);
        System.out.println("変更後: V" + bmp.getVersion());
        assertEquals(bmp.getVersion(), 5);
    }

    /**
     * <b>BMP バージョン変更</b><br>
     * 存在しないバージョンに変更し、デフォルトでバージョンが 3 なのか値を確認<br>
     * 成功テスト
     */
    @Test
    public void test210_bmpChangeVersion_out_of_version() {
        BMP bmp = new BMP();
        System.out.println("変更前: V" + bmp.getVersion());
        assertEquals(bmp.getVersion(), 3);

        bmp.setVersion(6);
        System.out.println("変更後: V" + bmp.getVersion());
        assertEquals(bmp.getVersion(), 3);
    }

    /**
     * <b>BMP バージョン変更</b><br>
     * 存在しないバージョンに変更し、デフォルトでバージョンが 3 なのか値を確認<br>
     * 成功テスト
     */
    @Test
    public void test300_bmpChangeVersion_out_of_version() {
        BMP bmp = new BMP();
        System.out.println("変更前: V" + bmp.getVersion());
        assertEquals(bmp.getVersion(), 3);

        bmp.setVersion(0);
        System.out.println("変更後: V" + bmp.getVersion());
        assertEquals(bmp.getVersion(), 3);
    }

    /**
     * <b>BMP バージョン変更</b><br>
     * 存在しないバージョンに変更し、デフォルトでバージョンが 3 なのか値を確認<br>
     * 成功テスト
     */
    @Test
    public void test301_bmpChangeVersion_out_of_version() {
        BMP bmp = new BMP();
        System.out.println("変更前: V" + bmp.getVersion());
        assertEquals(bmp.getVersion(), 3);

        bmp.setVersion(-1);
        System.out.println("変更後: V" + bmp.getVersion());
        assertEquals(bmp.getVersion(), 3);
    }

    /**
     * <b>オプショナルデータ 入出力確認</b><br>
     * GAP1だけ<br>
     * 成功テスト
     */
    @Test
    public void test400_bmpV3Gap1() {
        String file = addr + "java_bmpV3Gap1.bmp";
        String str = "生麦生米生卵";

        // 出力
        byte[] gap1 = null;
        try {
            gap1 = str.getBytes("UTF-8");
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
            bmp.setBitCount(8); // ビットの深さ
            bmp.addColor(255, 0, 255); // 紫色
            bmp.addColor(0, 255, 255); // アクア色
            bmp.setGap1(gap1); // オプショナル
            bmp.setImage(image);// イメージ
            bmp.output(file);// 出力

            // 入力
            bmp = new BMP(file);
            String gap1Str = "";
            gap1Str = new String(bmp.getGap1(), "UTF-8");

            assertEquals(str, gap1Str);

            System.out.println(bmp);
            System.out.println();
            System.out.println("GAP1: " + gap1Str);
            System.out.println();
        } catch (UnsupportedEncodingException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * <b>オプショナルデータ 入出力確認</b><br>
     * GAP2だけ<br>
     * 成功テスト
     */
    @Test
    public void test401_bmpV3Gap2() {
        String file = addr + "java_bmpV3Gap2.bmp";
        String str = "赤巻紙青巻紙黄巻紙";

        // 出力
        byte[] gap2 = null;
        try {
            gap2 = str.getBytes("UTF-8");
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
            bmp.setBitCount(8); // ビットの深さ
            bmp.addColor(255, 0, 255); // 紫色
            bmp.addColor(0, 255, 255); // アクア色
            bmp.setGap2(gap2); // オプショナル
            bmp.setImage(image);// イメージ
            bmp.output(file);// 出力

            // 入力
            bmp = new BMP(file);
            String gap2Str = "";
            gap2Str = new String(bmp.getGap2(), "UTF-8");

            assertEquals(str, gap2Str);

            System.out.println(bmp);
            System.out.println();
            System.out.println("GAP2: " + gap2Str);
            System.out.println();
        } catch (UnsupportedEncodingException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * <b>オプショナルデータ 入出力確認</b><br>
     * 成功テスト
     */
    @Test
    public void test402_bmpV3Gap() {
        String file = addr + "java_bmpV3Gap.bmp";
        String str1 = "生麦生米生卵";
        String str2 = "YEAH!!";

        // 出力
        byte[] gap1 = null;
        byte[] gap2 = null;
        try {
            gap1 = str1.getBytes("UTF-8");
            gap2 = str2.getBytes("UTF-8");
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
            bmp.setBitCount(8); // ビットの深さ
            bmp.addColor(255, 0, 255); // 紫色
            bmp.addColor(0, 255, 255); // アクア色
            bmp.setGap1(gap1); // オプショナル 1
            bmp.setGap2(gap2); // オプショナル 2
            bmp.setImage(image); // イメージ
            bmp.output(file); // 出力

            // 入力
            bmp = new BMP(file);
            String gap1Str = "";
            String gap2Str = "";
            gap1Str = new String(bmp.getGap1(), "UTF-8");
            gap2Str = new String(bmp.getGap2(), "UTF-8");

            assertEquals(str1, gap1Str);
            assertEquals(str2, gap2Str);

            System.out.println(bmp);
            System.out.println();
            System.out.println("GAP1: " + gap1Str);
            System.out.println("GAP2: " + gap2Str);
            System.out.println();
        } catch (UnsupportedEncodingException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * <b>オプショナルデータ 入出力確認</b><br>
     * プロファイルなし<br>
     * 成功テスト
     */
    @Test
    public void test403_bmpV5Gap() {
        String file = addr + "bmpV5Gap.bmp";
        String str1 = "生麦生米生卵";
        String str2 = "Hello World!!";

        // 出力
        byte[] gap1 = null;
        byte[] gap2 = null;
        try {
            gap1 = str1.getBytes("UTF-8");
            gap2 = str2.getBytes("UTF-8");
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

            BMP bmp = new BMP(5);
            bmp.setWidth(width); // 幅
            bmp.setHeight(height); // 高さ
            bmp.setBitCount(8); // ビットの深さ
            bmp.addColor(255, 0, 255); // 紫色
            bmp.addColor(0, 255, 255); // アクア色
            bmp.setGap1(gap1);
            bmp.setGap2(gap2);
            bmp.setImage(image);// イメージ
            bmp.output(file);// 出力

            // 入力
            bmp = new BMP(file);
            String gap1Str = "";
            String gap2Str = "";

            gap1Str = new String(bmp.getGap1(), "UTF-8");
            gap2Str = new String(bmp.getGap2(), "UTF-8");
            assertEquals(str1, gap1Str);
            assertEquals(str2, gap2Str);

            System.out.println(bmp);
            System.out.println();
            System.out.println("GAP1: " + gap1Str);
            System.out.println("GAP2: " + gap2Str);
            System.out.println();
        } catch (UnsupportedEncodingException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
}
