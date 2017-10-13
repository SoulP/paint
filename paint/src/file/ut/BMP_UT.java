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
     * <b>BMP 出力</b> 成功テスト
     */
    @Test
    public void bmpOutput_8bits_01() {
        String file = "C:\\Users\\ユーザー名\\Desktop\\java_8bits_01.bmp";
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
        bmp.setWidth(width);
        bmp.setHeight(height);
        bmp.setBitCount(8);
        bmp.addColor(255, 0, 255);
        bmp.addColor(0, 255, 255);
        bmp.setImage(image);
        bmp.output(file);
    }
}
