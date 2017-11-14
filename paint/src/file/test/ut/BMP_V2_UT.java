package file.test.ut;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import file.format.bmp.BMP_V2;
import file.test.Settings;

/**
 * <b>BMP V2 の単体テスト</b><br>
 * date: 2017/10/27 last_date 2017/11/14
 * 
 * @author ソウルP
 */
public class BMP_V2_UT extends Settings {
    final String     addr = BASIC_ADDR + "BMP\\BMP_V2_UT\\";
    FileOutputStream out;
    FileInputStream  in;
    BMP_V2           bmp;

    @Before
    public void before() {
        File check = new File(addr);
        if (!check.exists()) check.mkdirs();
    }

    @Test
    public void test000_Output() throws FileNotFoundException {
        bmp = new BMP_V2();

        int width = 3;
        int height = 3;
        short bitCount = 8;
        short hotspotX = 0;
        short hotspotY = 0;
        int infoHeaderSize = 40;

        // イメージ情報
        byte[] img01 = { 0x01, 0x00, 0x01, 0x00 };
        byte[] img02 = { 0x00, 0x01, 0x00, 0x00 };
        byte[] img03 = { 0x01, 0x00, 0x01, 0x00 };
        List<byte[]> image = new ArrayList<>();
        image.add(img01);
        image.add(img02);
        image.add(img03);

        bmp.setHeaderSize(bmp.getFileHeaderSize() + infoHeaderSize);
        bmp.setHotspotX(hotspotX);
        bmp.setHotspotY(hotspotY);
        bmp.setInfoHeaderSize(infoHeaderSize);
        bmp.setBitCount(bitCount); // ビットの深さ
        bmp.setWidth(width); // 幅
        bmp.setHeight(height); // 高さ
        bmp.setCompression(0); // 圧縮形式
        bmp.addColor(255, 0, 255); // 紫色
        bmp.addColor(0, 255, 255); // アクア色
        bmp.setImage(image); // イメージ

        int fileSize = bmp.getFileHeaderSize() + infoHeaderSize;

        int colorSize = 0;
        for (byte[] c : bmp.getColors())
            colorSize += c.length;
        fileSize += colorSize;

        int imageSize = 0;
        for (byte[] i : image)
            imageSize += i.length;
        bmp.setSizeImage(imageSize);

        bmp.setOffset(fileSize);
        fileSize += imageSize;

        try {
            out = new FileOutputStream(addr + "test000_Output.bmp");
            out.write(bmp.get());
            out.flush();
        } catch (IOException e) {
            fail(e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    out = null;
                }
            }
        }
    }

    @Test
    public void test010_Input() {
        try {
            in = new FileInputStream(addr + "test000_Output.bmp");
            byte[] data = new byte[in.available()];
            in.read(data);

            bmp = new BMP_V2(data);

            System.out.println(bmp);
        } catch (IOException e) {
            fail(e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    in = null;
                }
            }
        }
    }
}
