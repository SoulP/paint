package file.test.ita;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import file.Tools;
import file.io.BMP;
import file.test.Basic;

/**
 * <b>BMPの内部結合テスト</b><br>
 * date: 2017/10/13 last_date: 2018/03/27
 * 
 * @author ソウルP
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BMP_ITa extends Basic {
    public final String addr = BASIC_ADDR + "BMP\\BMP_ITa\\";

    /**
     * <b>BMP 出力</b><br>
     * 1bit<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test000_bmpOutput_1bit() throws IOException {
        String file = addr + "java_1bit.bmp";
        int width = 3;
        int height = 3;

        BMP bmp = new BMP();
        bmp.setWidth(width); // 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(1); // ビットの深さ
        bmp.addColor(255, 0, 255); // 紫色
        bmp.addColor(0, 255, 255); // アクア色
        bmp.setImage(image_1bit);// イメージ

        bmp.output(file);// 出力
    }

    /**
     * <b>BMP 出力</b><br>
     * 4bit<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test010_bmpOutput_4bit() throws IOException {
        String file = addr + "java_4bit.bmp";
        int width = 3;
        int height = 3;

        BMP bmp = new BMP();
        bmp.setWidth(width); // 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(4); // ビットの深さ
        bmp.addColor(255, 0, 255); // 紫色
        bmp.addColor(0, 255, 255); // アクア色
        bmp.setImage(image_4bit);// イメージ

        bmp.output(file);// 出力
    }

    /**
     * <b>BMP 出力</b><br>
     * 4bit<br>
     * 圧縮形式: BI_RLE4<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test011_bmpOutput_4bit_BI_RLE4() throws IOException {
        String file = addr + "java_4bit_BI_RLE4.bmp";
        int width = 14;
        int height = 14;

        BMP bmp = new BMP();
        bmp.setWidth(width); // 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount((short) 4); // ビットの深さ
        bmp.setCompression(2); // 圧縮形式
        for (int i = 255; i >= 21; i -= 18) {
            bmp.addColor(0, i, i); // アクア色
        }
        bmp.setImage(image_4bit_BI_RLE4);// イメージ

        bmp.output(file);// 出力
    }

    /**
     * <b>BMP 出力</b><br>
     * 8bit<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test020_bmpOutput_8bit() throws IOException {
        String file = addr + "java_8bit.bmp";
        int width = 3;
        int height = 3;

        BMP bmp = new BMP();
        bmp.setWidth(width); // 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(8); // ビットの深さ
        bmp.addColor(255, 0, 255); // 紫色
        bmp.addColor(0, 255, 255); // アクア色
        bmp.setImage(image_8bit);// イメージ

        bmp.output(file);// 出力
    }

    /**
     * <b>BMP 出力</b><br>
     * 8bit<br>
     * 圧縮形式: BI_RLE8<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test021_bmpOutput_8bit_BI_RLE8() throws IOException {
        String file = addr + "java_8bit_BI_RLE8.bmp";
        int width = 13;
        int height = 13;

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
        bmp.setImage(image_8bit_BI_RLE8);// イメージ

        bmp.output(file);// 出力
    }

    /**
     * <b>BMP 出力</b><br>
     * 16 bit<br>
     * 16 bit の場合、画像データの１ピクセルのデータに対してリトルエンディアンになる<br>
     * デフォルト: R5 G6 B5<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test030_bmpOutput_16bit() throws IOException {
        String file = addr + "java_16bit.bmp";
        int width = 3;
        int height = 3;

        BMP bmp = new BMP();
        bmp.setWidth(width);// 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(16); // ビットの深さ
        bmp.setImage(image_16bit); // イメージ

        bmp.output(file);// 出力
    }

    /**
     * <b>BMP 出力</b><br>
     * 16 bit<br>
     * 16 bit の場合、画像データの１ピクセルのデータに対してリトルエンディアンになる<br>
     * 同じくBitFieldsのRGB順のデータをリトルエンディアンになる<br>
     * ビットフィールドで設定: R5 G5 B5<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test031_bmpOutput_16bit_BitFields() throws IOException {
        String file = addr + "java_16bit_BitFields.bmp";
        int width = 3;
        int height = 3;

        byte[] bitfields = Tools.bytes2D2bytes1D(r5g5b5MaskV3); // ビットフィールド

        BMP bmp = new BMP();
        bmp.setWidth(width);// 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(16); // ビットの深さ
        bmp.setCompression(3); // 圧縮形式
        bmp.setBitFields(bitfields); // ビットフィールド
        bmp.setImage(image_16bit_BitFields); // イメージ

        bmp.output(file);// 出力
    }

    /**
     * <b>BMP 出力</b><br>
     * 24bit<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test040_bmpOutput_24bit() throws IOException {
        String file = addr + "java_24bit.bmp";
        int width = 3;
        int height = 3;

        BMP bmp = new BMP();
        bmp.setWidth(width); // 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(24); // ビットの深さ
        bmp.setImage(image_24bit);// イメージ

        bmp.output(file);// 出力
    }

    /**
     * <b>BMP 出力</b><br>
     * 32bit<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test050_bmpOutput_32bit() throws IOException {
        String file = addr + "java_32bit.bmp";
        int width = 3;
        int height = 3;

        BMP bmp = new BMP();
        bmp.setWidth(width); // 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(32); // ビットの深さ
        bmp.setImage(image_32bit);// イメージ

        bmp.output(file);// 出力
    }

    /**
     * <b>BMP 出力</b><br>
     * 16 bit<br>
     * 16 bit の場合、画像データの１ピクセルのデータに対してリトルエンディアンになる<br>
     * 同じくBitFieldsのRGB順のデータをリトルエンディアンになる<br>
     * ビットフィールドで設定: R5 G5 B5<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test051_bmpOutput_32bit_BitFields() throws IOException {
        String file = addr + "java_32bit_BitFields.bmp";
        int width = 3;
        int height = 3;

        byte[] bitfields = Tools.bytes2D2bytes1D(bitFields32bitMaskV3); // ビットフィールド

        BMP bmp = new BMP();
        bmp.setWidth(width);// 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(32); // ビットの深さ
        bmp.setCompression(3); // 圧縮形式
        bmp.setBitFields(bitfields); // ビットフィールド
        bmp.setImage(image_32bit_BitFields); // イメージ

        bmp.output(file);// 出力
    }

    /**
     * <b>BMP 入力</b><br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test100_bmpInput() throws IOException {
        String file = addr + "java_4bit_BI_RLE4.bmp";

        BMP bmp = new BMP();
        bmp.input(file);

        System.out.println(file);
        System.out.println("BMP V" + bmp.getVersion());
        System.out.println("ファイルサイズ: " + bmp.getFileSize());
        System.out.println("幅: " + bmp.getWidth());
        System.out.println("高さ: " + bmp.getHeight());
        System.out.println("ビットの深さ: " + bmp.getBitCount() + "bit");
        System.out.println("圧縮形式: " + bmp.getCompression());
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
     * 
     * @throws IOException
     */
    @Test
    public void test110_bmpInput() throws IOException {
        String file = addr + "java_8bit_BI_RLE8.bmp";

        BMP bmp = new BMP();
        bmp.input(file);

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
     * 
     * @throws IOException
     */
    @Test
    public void test400_bmpV3Gap1() throws IOException {
        String file = addr + "java_bmpV3Gap1.bmp";
        String str = "生麦生米生卵";

        // 出力
        byte[] gap1 = null;
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
    }

    /**
     * <b>オプショナルデータ 入出力確認</b><br>
     * GAP2だけ<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test401_bmpV3Gap2() throws IOException {
        String file = addr + "java_bmpV3Gap2.bmp";
        String str = "赤巻紙青巻紙黄巻紙";

        // 出力
        byte[] gap2 = null;
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
    }

    /**
     * <b>オプショナルデータ 入出力確認</b><br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test402_bmpV3Gap() throws IOException {
        String file = addr + "java_bmpV3Gap.bmp";
        String str1 = "生麦生米生卵";
        String str2 = "YEAH!!";

        // 出力
        byte[] gap1 = null;
        byte[] gap2 = null;
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
    }

    /**
     * <b>オプショナルデータ 入出力確認</b><br>
     * プロファイルなし<br>
     * 成功テスト
     * 
     * @throws IOException
     */
    @Test
    public void test403_bmpV5Gap() throws IOException {
        String file = addr + "bmpV5Gap.bmp";
        String str1 = "生麦生米生卵";
        String str2 = "Hello World!!";

        // 出力
        byte[] gap1 = null;
        byte[] gap2 = null;
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
    }

    /**
     * 出力する際にバージョン指定<br>
     * 出力: バージョン1<br>
     * 成功テスト
     */
    @Test
    public void test500() throws IOException {
        String file = addr + "bmpV3-V1.bmp";

        // イメージ情報
        int width = 3;
        int height = 3;

        BMP bmp = new BMP();
        bmp.setWidth(width);// 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(16); // ビットの深さ
        bmp.setImage(image_16bit); // イメージ

        bmp.output(file, 1);// 出力

        // 入力
        bmp = new BMP(file);

        System.out.println(bmp);
        System.out.println();
    }

    /**
     * 出力する際にバージョン指定<br>
     * 出力: バージョン2<br>
     * 成功テスト
     */
    @Test
    public void test501() throws IOException {
        String file = addr + "bmpV3-V2.bmp";

        // イメージ情報
        int width = 3;
        int height = 3;

        BMP bmp = new BMP();
        bmp.setInfoHeaderSize(40);// 情報ヘッダサイズ
        bmp.setWidth(width);// 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(16); // ビットの深さ
        bmp.setImage(image_16bit); // イメージ

        bmp.output(file, 2);// 出力

        // 入力
        bmp = new BMP(file);

        System.out.println(bmp);
        System.out.println();
    }

    /**
     * 出力する際にバージョン指定<br>
     * 出力: バージョン4<br>
     * 成功テスト
     */
    @Test
    public void test502() throws IOException {
        String file = addr + "bmpV3-V4.bmp";

        // イメージ情報
        int width = 3;
        int height = 3;

        BMP bmp = new BMP();
        bmp.setWidth(width);// 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(16); // ビットの深さ
        bmp.setImage(image_16bit); // イメージ

        bmp.output(file, 4);// 出力

        // 入力
        bmp = new BMP(file);

        System.out.println(bmp);
        System.out.println();
    }

    /**
     * 出力する際にバージョン指定<br>
     * 出力: バージョン5<br>
     * 成功テスト
     */
    @Test
    public void test503() throws IOException {
        String file = addr + "bmpV3-V5.bmp";

        // イメージ情報
        int width = 3;
        int height = 3;

        BMP bmp = new BMP();
        bmp.setWidth(width);// 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(16); // ビットの深さ
        bmp.setImage(image_16bit); // イメージ

        bmp.output(file, 5);// 出力

        // 入力
        bmp = new BMP(file);

        System.out.println(bmp);
        System.out.println();
    }

    /**
     * 出力する際にバージョン指定<br>
     * 出力: バージョン3<br>
     * 成功テスト
     */
    @Test
    public void test504() throws IOException {
        String file = addr + "bmpV5-V3.bmp";

        // イメージ情報
        int width = 3;
        int height = 3;

        BMP bmp = new BMP(5);
        bmp.setWidth(width);// 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(16); // ビットの深さ
        bmp.setImage(image_16bit); // イメージ

        bmp.output(file, 3);// 出力

        // 入力
        bmp = new BMP(file);

        System.out.println(bmp);
        System.out.println();
    }

    /**
     * バージョン2 入出力<br>
     * 成功テスト
     */
    @Test
    public void test600() throws IOException {
        String file = addr + "java_bmpV2.bmp";

        // イメージ情報
        int width = 3;
        int height = 3;

        BMP bmp = new BMP(2);
        bmp.setInfoHeaderSize(40);
        bmp.setWidth(width);// 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(16); // ビットの深さ
        bmp.setImage(image_16bit); // イメージ

        bmp.output(file);// 出力

        bmp.setInfoHeaderSize(100);

        bmp.output(file);// 出力

        bmp = new BMP(file);// 入力
        System.out.println(bmp);
    }

    /**
     * 出力失敗テスト
     */
    @Test(expected = IOException.class)
    public void test800_failed() throws IOException {
        String file = addr + "test800_failed.bmp";

        // イメージ情報
        int width = 3;
        int height = 3;

        BMP bmp = new BMP(5);
        bmp.setWidth(width);// 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(16); // ビットの深さ
        bmp.setImage(image_16bit); // イメージ

        bmp.output(file, 8);// 出力
    }

    /**
     * 入力失敗テスト<br>
     * 空ファイル必須
     */
    @Test(expected = IOException.class)
    public void test801_failed() throws IOException {
        String file = addr + "test801_failed.bmp";

        new BMP(file);
    }

    /**
     * 入力失敗テスト<br>
     * 存在しないファイル
     */
    @Test(expected = IOException.class)
    public void test802_failed() throws IOException {
        String file = addr + "test802_failed.bmp";

        new BMP(file);
    }

    /**
     * 入力失敗テスト<br>
     * 破損したファイル（情報ヘッダサイズ不正）必要
     */
    @Test(expected = IOException.class)
    public void test803_failed() throws IOException {
        String file = addr + "test803_failed.bmp";

        new BMP(file);
    }

    /**
     * 入力失敗テスト<br>
     * 破損したファイル（情報ヘッダサイズ不正）必要
     */
    @Test(expected = IOException.class)
    public void test804_failed() throws IOException {
        String file = addr + "test804_failed.bmp";

        new BMP(file);
    }

    /**
     * カバレッジ埋め (入出力なし)
     */
    @Test
    public void test900() throws IOException {
        // イメージ情報
        int width = 3;
        int height = 3;
        byte[] img01 = { 0x01, 0x00, 0x01, 0x00 };
        byte[] img02 = { 0x00, 0x01, 0x00, 0x00 };
        byte[] img03 = { 0x01, 0x00, 0x01, 0x00 };
        List<byte[]> image = new ArrayList<>();
        image.add(img01);
        image.add(img02);
        image.add(img03);

        BMP bmp = new BMP(2);
        bmp.setWidth(width); // 幅
        bmp.setHeight(height); // 高さ
        bmp.setBitCount(8); // ビットの深さ
        bmp.clearColors();
        bmp.addColor(255, 0, 255); // 紫色
        bmp.addColor(0, 255, 255); // アクア色
        bmp.clearImage();
        bmp.setImage(image);// イメージ

        int testInfoHeaderSize = 32;
        int testHeaderSize = 64;
        int testHotspotX = 2;
        int testHotspotY = 3;
        int testResolution = 200;
        int testFormat = 1;
        int testHalftone = 1;
        int testHalftone1 = 1;
        int testHalftone2 = 1;
        int testEncoding = 1;
        int testId = 114514;

        System.out.println("--- BMP Test 900 ---");
        System.out.println("情報ヘッダサイズ: " + bmp.getInfoHeaderSize() + " byte");
        System.out.println("情報ヘッダサイズ = " + testInfoHeaderSize);
        bmp.setInfoHeaderSize(testInfoHeaderSize);
        System.out.println("情報ヘッダサイズ: " + bmp.getInfoHeaderSize() + " byte");
        System.out.println("ヘッダサイズ: " + bmp.getHeaderSize() + " byte");
        System.out.println("ヘッダサイズ = " + testHeaderSize);
        bmp.setHeaderSize(testHeaderSize);
        System.out.println("ヘッダサイズ: " + bmp.getHeaderSize() + " byte");
        System.out.println("Hotspot X: " + bmp.getHotspotX());
        System.out.println("Hotspot X = " + testHotspotX);
        bmp.setHotspotX(testHotspotX);
        System.out.println("Hotspot X: " + bmp.getHotspotX());
        System.out.println("Hotspot Y: " + bmp.getHotspotY());
        System.out.println("Hotspot Y = " + testHotspotY);
        bmp.setHotspotY(testHotspotY);
        System.out.println("Hotspot Y: " + bmp.getHotspotY());
        System.out.println("解像度: " + bmp.getResolution());
        System.out.println("解像度 = " + testResolution);
        bmp.setResolution(testResolution);
        System.out.println("解像度: " + bmp.getResolution());
        System.out.println("フォーマット: " + bmp.getFormat());
        System.out.println("フォーマット = " + testFormat);
        bmp.setFormat(testFormat);
        System.out.println("フォーマット: " + bmp.getFormat());
        System.out.println("ハーフトーンの方式: " + bmp.getHaltone());
        System.out.println("ハーフトーンの方式 = " + testHalftone);
        bmp.setHalftone(testHalftone);
        System.out.println("ハーフトーンの方式: " + bmp.getHaltone());
        System.out.println("ハーフトーン1: " + bmp.getHalftoneParam1());
        System.out.println("ハーフトーン1 = " + testHalftone1);
        bmp.setHalftoneParam1(testHalftone1);
        System.out.println("ハーフトーン1: " + bmp.getHalftoneParam1());
        System.out.println("ハーフトーン2: " + bmp.getHalftoneParam2());
        System.out.println("ハーフトーン2 = " + testHalftone2);
        bmp.setHalftoneParam2(testHalftone2);
        System.out.println("ハーフトーン2: " + bmp.getHalftoneParam2());
        System.out.println("符号化方式: " + bmp.getEncoding());
        System.out.println("符号化方式 = " + testEncoding);
        bmp.setEncoding(testEncoding);
        System.out.println("符号化方式: " + bmp.getEncoding());
        System.out.println("ID: " + bmp.getId());
        System.out.println("ID = " + testId);
        bmp.setId(testId);
        System.out.println("ID: " + bmp.getId());

        assertEquals(bmp.getInfoHeaderSize(), testInfoHeaderSize);
        assertEquals(bmp.getHeaderSize(), testHeaderSize);
        assertEquals(bmp.getHotspotX(), testHotspotX);
        assertEquals(bmp.getHotspotY(), testHotspotY);
        assertEquals(bmp.getResolution(), testResolution);
        assertEquals(bmp.getFormat(), testFormat);
        assertEquals(bmp.getHaltone(), testHalftone);
        assertEquals(bmp.getHalftoneParam1(), testHalftone1);
        assertEquals(bmp.getHalftoneParam2(), testHalftone2);
        assertEquals(bmp.getEncoding(), testEncoding);
        assertEquals(bmp.getId(), testId);
        assertTrue(bmp.isEmptyBitFields());
        bmp.setBitFields(new byte[] {});
        assertTrue(bmp.isEmptyBitFields());
        bmp.setBitFields(new byte[] { 1 });
        assertFalse(bmp.isEmptyBitFields());

        bmp.setVersion(1);
        bmp.setVersion(4);
        bmp.setVersion(5);
        assertTrue(bmp.isEmptyProfile());
        bmp.setProfile(new byte[] {});
        assertTrue(bmp.isEmptyProfile());
        bmp.setProfile(new byte[] { 1 });
        assertFalse(bmp.isEmptyProfile());
        System.out.println("プロファイル: " + bmp.getProfile()[0]);
        System.out.println();
    }
}
