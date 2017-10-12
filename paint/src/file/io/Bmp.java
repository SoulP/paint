package file.io;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import file.format.BMPheader;

/**
 * <b>BMP入出力</b><br>
 * date: 2017/10/12 last_date: 2017/10/12
 * 
 * @author ソウルP
 * @version 1.0 2017/10/12 Bmp作成
 */
public class Bmp extends BMPheader {
    private int                               width;                                                            // 画像の幅 (ピクセル)
    private int                               height;                                                           // 画像の高さ (ピクセル)
    private int                               fileSize;                                                         // ファイルサイズ (byte)
    private short                             bitCount;                                                         // 1画素あたりのデータサイズ (bit)
    private int                               compression;                                                      // 圧縮形式
    private int                               imageSize;                                                        // 画像データ部のサイズ (byte)
    private int                               pixPerMeterX;                                                     // 横方向解像度 (1mあたりの画素数)
    private int                               pixPerMeterY;                                                     // 縦方向解像度 (1mあたりの画素数)
    private int                               clrUsed;                                                          // 格納されているパレット数 (使用色数)
    private int                               cirImportant;                                                     // 重要なパレットのインデックス
    List<byte[]>                              colors;                                                           // カラーパレット
    List<byte[]>                              image;                                                            // イメージ (横の配列)

    private final BiConsumer<byte[], Integer> inputInt   = (var, val) -> {
                                                             var = ByteBuffer.allocate(var.length).putInt(val)
                                                                     .order(ByteOrder.LITTLE_ENDIAN).array();
                                                         };
    private final BiConsumer<byte[], Short>   inputShort = (var, val) -> {
                                                             var = ByteBuffer.allocate(var.length).putShort(val)
                                                                     .order(ByteOrder.LITTLE_ENDIAN).array();
                                                         };

    /**
     * <b>BMP</b>
     */
    public Bmp() {
        cirImportant = clrUsed = pixPerMeterY = pixPerMeterX = imageSize = compression = fileSize = height = width = bitCount = 0;
        colors = new ArrayList<>();
        image = new ArrayList<>();
    }

    /**
     * <b>出力</b>
     * 
     * @return 画像の幅 (ピクセル)
     */
    public final int getWitdh() {
        return width;
    }

    /**
     * <b>入力</b>
     * 
     * @param width
     *            画像の幅 (ピクセル)
     */
    public final void setWidth(int width) {
        this.width = width;
        inputInt.accept(bcWidth, width);
    }

    /**
     * <b>出力</b><br>
     * bcHeight の値が正数なら，画像データは左下から右上へ<br>
     * bcHeight の値が負数なら，画像データは左上から右下へ
     * 
     * @return 画像の高さ (ピクセル)
     */
    public final int getHeight() {
        return height;
    }

    /**
     * <b>入力</b><br>
     * bcHeight の値が正数なら，画像データは左下から右上へ<br>
     * bcHeight の値が負数なら，画像データは左上から右下へ
     * 
     * @param height
     *            画像の高さ (ピクセル)
     */
    public final void setHeight(int height) {
        this.height = height;
        inputInt.accept(bcHeight, height);
    }

    /**
     * <b>出力</b><br>
     * 例）256 色ビットマップ ＝ 8
     * 
     * @return 1画素あたりのデータサイズ (bit)
     */
    public final short getBitCount() {
        return bitCount;
    }

    /**
     * <b>入力</b><br>
     * 例）256 色ビットマップ ＝ 8
     * 
     * @param bitCount
     *            1画素あたりのデータサイズ (bit)
     */
    public final void setBitCount(short bitCount) {
        this.bitCount = bitCount;
        inputShort.accept(bcBitCount, bitCount);
    }

    /**
     * <b>出力</b><br>
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
    public final int getCompression() {
        return compression;
    }

    /**
     * <b>入力</b><br>
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
    public final void setCompression(int compression) {
        this.compression = compression;
        inputInt.accept(biCompression, compression);
    }

    private final void updateFileSize() {
        fileSize = FILE_HEADER_SIZE + INFO_HEADER_SIZE + imageSize;
        inputInt.accept(bfSize, fileSize);
    }

    private final void updateImageSize(int compression) {
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
        inputInt.accept(biSizeImage, imageSize);
    }
}
