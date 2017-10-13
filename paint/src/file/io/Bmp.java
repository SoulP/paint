package file.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import file.format.BMPheader;

/**
 * <b>BMP入出力</b><br>
 * date: 2017/10/12 last_date: 2017/10/13
 * 
 * @author ソウルP
 * @version 1.0 2017/10/12 Bmp作成
 */
public class Bmp extends BMPheader {
    private int   width;        // 画像の幅 (ピクセル)
    private int   height;       // 画像の高さ (ピクセル)
    private int   fileSize;     // ファイルサイズ (byte)
    private short bitCount;     // 1画素あたりのデータサイズ (bit)
    private int   compression;  // 圧縮形式
    private int   imageSize;    // 画像データ部のサイズ (byte)
    private int   pixPerMeterX; // 横方向解像度 (1mあたりの画素数)
    private int   pixPerMeterY; // 縦方向解像度 (1mあたりの画素数)
    private int   clrUsed;      // 格納されているパレット数 (使用色数)
    private int   cirImportant; // 重要なパレットのインデックス
    List<byte[]>  colors;       // カラーパレット
    List<byte[]>  image;        // イメージ

    /**
     * <b>BMP</b>
     */
    public Bmp() {
        colors = new ArrayList<>();
        image = new ArrayList<>();
        imageSize = fileSize = 0;
        setWidth(0);
        setHeight(0);
        setBitCount((short) 0);
        setCompression(0);
        setPixPerMeterX(0);
        setPixPerMeterY(0);
        clearColors();
        setCirImportant(0);
        biImageSize = Tools.INPUT_INT.apply(biImageSize, imageSize);
        bfSize = Tools.INPUT_INT.apply(bfSize, fileSize);
    }

    /**
     * @return 画像の幅 (ピクセル)
     */
    public int getWitdh() {
        return width;
    }

    /**
     * @param width
     *            画像の幅 (ピクセル)
     */
    public void setWidth(int width) {
        this.width = width;
        bcWidth = Tools.INPUT_INT.apply(bcWidth, width);
    }

    /**
     * bcHeight の値が正数なら，画像データは左下から右上へ<br>
     * bcHeight の値が負数なら，画像データは左上から右下へ
     * 
     * @return 画像の高さ (ピクセル)
     */
    public int getHeight() {
        return height;
    }

    /**
     * bcHeight の値が正数なら，画像データは左下から右上へ<br>
     * bcHeight の値が負数なら，画像データは左上から右下へ
     * 
     * @param height
     *            画像の高さ (ピクセル)
     */
    public void setHeight(int height) {
        this.height = height;
        bcHeight = Tools.INPUT_INT.apply(bcHeight, height);
    }

    /**
     * <b>出力</b><br>
     * 例）256 色ビットマップ ＝ 8
     * 
     * @return 1画素あたりのデータサイズ (bit)
     */
    public short getBitCount() {
        return bitCount;
    }

    /**
     * 例）256 色ビットマップ ＝ 8
     * 
     * @param bitCount
     *            1画素あたりのデータサイズ (bit)
     */
    public void setBitCount(int bitCount) {
        this.bitCount = (short) bitCount;
        bcBitCount = Tools.ENDIAN.apply(ByteBuffer.allocate(2).putShort(this.bitCount).array());
    }

    /**
     * <ul>
     * 圧縮形式
     * <li>0 - BI_RGB (無圧縮)</li>
     * <li>1 - BI_RLE8 (RunLength 8 bits/pixel)</li>
     * <li>2 - BI_RLE4 (RunLength 4 bits/pixel)</li>
     * <li>3 - BI_BITFIELDS (Bitfields)</li>
     * </ul>
     * 
     * @return 圧縮形式
     */
    public int getCompression() {
        return compression;
    }

    /**
     * <ul>
     * 圧縮形式
     * <li>0 - BI_RGB (無圧縮)</li>
     * <li>1 - BI_RLE8 (RunLength 8 bits/pixel)</li>
     * <li>2 - BI_RLE4 (RunLength 4 bits/pixel)</li>
     * <li>3 - BI_BITFIELDS (Bitfields)</li>
     * </ul>
     * 
     * @param compression
     *            圧縮形式
     */
    public void setCompression(int compression) {
        this.compression = compression;
        biCompression = Tools.INPUT_INT.apply(biCompression, compression);
    }

    /**
     * @return 横方向解像度 (1mあたりの画素数)
     */
    public int getPixPerMeterX() {
        return pixPerMeterX;
    }

    /**
     * @param pixPerMeterX
     *            横方向解像度 (1mあたりの画素数)
     */
    public void setPixPerMeterX(int pixPerMeterX) {
        this.pixPerMeterX = pixPerMeterX;
        biPixPerMeterX = Tools.INPUT_INT.apply(biPixPerMeterX, pixPerMeterX);
    }

    /**
     * @return 縦方向解像度 (1mあたりの画素数)
     */
    public int getPixPerMeterY() {
        return pixPerMeterY;
    }

    /**
     * @param pixPerMeterY
     *            縦方向解像度 (1mあたりの画素数)
     */
    public void setPixPerMeterY(int pixPerMeterY) {
        this.pixPerMeterY = pixPerMeterY;
        biPixPerMeterY = Tools.INPUT_INT.apply(biPixPerMeterY, pixPerMeterY);
    }

    /**
     * @return 重要なパレットのインデックス
     */
    public int getCirImportant() {
        return cirImportant;
    }

    /**
     * @param cirImportant
     *            重要なパレットのインデックス
     */
    public void setCirImportant(int cirImportant) {
        this.cirImportant = cirImportant;
    }

    /**
     * @return カラーパレット
     */
    public List<byte[]> getColors() {
        return colors;
    }

    /**
     * @param colors
     *            カラーパレット
     */
    public void setColors(List<byte[]> colors) {
        this.colors = colors;
        updateClrUsed();
    }

    /**
     * <b>カラーパレット 全消去</b>
     */
    public void clearColors() {
        colors.clear();
        updateClrUsed();
    }

    /**
     * <b>カラーパレットに色追加</b>
     * 
     * @param r
     *            赤 0 - 255
     * @param g
     *            緑 0 - 255
     * @param b
     *            青 0 - 255
     */
    public void addColor(int r, int g, int b) {
        byte[] color = { (byte) b, (byte) g, (byte) r, 0x00 };
        colors.add(color);
        updateClrUsed();
    }

    /**
     * byte[]が横、Listが縦
     * 
     * @return イメージ
     */
    public List<byte[]> getImage() {
        return image;
    }

    /**
     * @param image
     *            イメージ
     */
    public void setImage(List<byte[]> image) {
        this.image = image;
        updateImageSize(compression);
        updateFileSize();
    }

    /**
     * <b>入力</b>
     * 
     * @param file
     *            ファイル先
     */
    public void input(String file) {

    }

    /**
     * <b>出力</b>
     * 
     * @param file
     *            ファイル先
     */
    public void output(String file) {
        int offset = FILE_HEADER_SIZE + INFO_HEADER_SIZE + colors.size() * 4;
        bfOffBits = Tools.INPUT_INT.apply(bfOffBits, offset);

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            // ファイルヘッダ
            out.write(BF_TYPE);
            out.write(bfSize);
            out.write(BF_RESERVERD_1);
            out.write(BF_RESERVERD_2);
            out.write(bfOffBits);
            // 情報ヘッダ
            out.write(BC_SIZE);
            out.write(bcWidth);
            out.write(bcHeight);
            out.write(BC_PLANES);
            out.write(bcBitCount);
            out.write(biCompression);
            out.write(biImageSize);
            out.write(biPixPerMeterX);
            out.write(biPixPerMeterY);
            out.write(biClrUsed);
            out.write(biCirImportant);
            // カラーパレット
            if (!colors.isEmpty()) for (byte[] c : colors)
                out.write(c);
            // イメージ
            for (byte[] img : image)
                out.write(img);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    out = null;
                }
            }
        }
    }

    private void updateFileSize() {
        fileSize = FILE_HEADER_SIZE + INFO_HEADER_SIZE + imageSize + colors.size() * 4;
        bfSize = Tools.INPUT_INT.apply(bfSize, fileSize);
    }

    private void updateImageSize(int compression) {
        int tempW;
        switch (compression) {
            case 0:
                tempW = (width % 4 == 0) ? width : 4 - (width % 4) + width;
                imageSize = tempW * height;
                break;
            case 1:
            case 2:
            case 3:
                imageSize = 0;
                image.forEach(b -> {
                    imageSize += b.length;
                });
                break;
            default:
                imageSize = 0;
                break;
        }
        biImageSize = Tools.INPUT_INT.apply(biImageSize, imageSize);
    }

    private void updateClrUsed() {
        clrUsed = colors.size();
        biClrUsed = Tools.INPUT_INT.apply(biClrUsed, clrUsed);
    }
}
