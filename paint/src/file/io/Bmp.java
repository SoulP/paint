package file.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import file.format.BMPheader;

/**
 * <b>BMP入出力</b><br>
 * date: 2017/10/12 last_date: 2017/10/16
 * 
 * @author ソウルP
 * @version 1.0 2017/10/12 Bmp作成
 */
public class Bmp extends BMPheader {
    private int         fileSize;                                                            // ファイルサイズ (byte)
    private int         width;                                                               // 画像の幅 (ピクセル)
    private int         height;                                                              // 画像の高さ (ピクセル)
    private short       bitCount;                                                            // 1画素あたりのデータサイズ (bit)
    private int         compression;                                                         // 圧縮形式
    private int         imageSize;                                                           // 画像データ部のサイズ (byte)
    private int         pixPerMeterX;                                                        // 横方向解像度 (1mあたりの画素数)
    private int         pixPerMeterY;                                                        // 縦方向解像度 (1mあたりの画素数)
    private int         clrUsed;                                                             // 格納されているパレット数 (使用色数)
    private int         cirImportant;                                                        // 重要なパレットのインデックス
    List<byte[]>        colors;                                                              // カラーパレット
    List<byte[]>        image;                                                               // イメージ
    private int         infoHeaderSize   = INFO_HEADER_SIZE;                                 // 情報ヘッダサイズ

    static final String ERROR_FILE_OR    = "ファイルの破損もしくは、";
    static final String ERROR_IS_NOT_BMP = ERROR_FILE_OR + "拡張子がBMPではありません。";
    static final String ERROR_BITCOUNT   = ERROR_FILE_OR + "ビットの深さが対応されていない値になっている可能性があります。";

    /**
     * <b>BMP</b>
     */
    public Bmp() {
        initialize();
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
        bcWidth = Tools.int2bytes(width);
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
        bcHeight = Tools.int2bytes(height);
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
        bcBitCount = Tools.short2bytes(this.bitCount);
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
        biCompression = Tools.int2bytes(compression);
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
        biXPelsPerMeter = Tools.int2bytes(pixPerMeterX);
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
        biYPelsPerMeter = Tools.int2bytes(pixPerMeterY);
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
     * @param image
     *            イメージ
     */
    public void setImage(byte[][] image) {
        this.image.clear();
        for (byte[] i : image)
            this.image.add(i);
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
        initialize();
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            fileSize = in.available();
            byte[] data = new byte[fileSize];
            in.read(data);

            int offset = 0;
            byte[] bm = Tools.subbytes(data, offset, offset += BF_TYPE.length);
            if (!Arrays.equals(bm, BF_TYPE)) throw new IOException(ERROR_IS_NOT_BMP);
            offset += bfSize.length;
            offset += BF_RESERVERD_1.length;
            offset += BF_RESERVERD_2.length;
            int imageOffset = Tools.bytes2int(Tools.subbytes(data, offset, offset += 4));
            offset += BC_SIZE.length;
            byte[] bcSize = Tools.subbytes(data, offset, offset += 4);
            infoHeaderSize = Tools.bytes2int(bcSize);
            if (infoHeaderSize == 12) {
                setWidth(Tools.bytes2short(Tools.subbytes(data, offset, offset += 2)));
                setHeight(Tools.bytes2short(Tools.subbytes(data, offset, offset += 2)));
                offset += BC_PLANES.length;
                setBitCount(Tools.bytes2short(Tools.subbytes(data, offset, offset += 2)));
                for (int i = offset; i < imageOffset; i += 3) {
                    int r = Byte.toUnsignedInt(data[i + 2]);
                    int g = Byte.toUnsignedInt(data[i + 1]);
                    int b = Byte.toUnsignedInt(data[i]);
                    addColor(r, g, b);
                }
            } else {
                setWidth(Tools.bytes2int(Tools.subbytes(data, offset, offset += 4)));
                setHeight(Tools.bytes2int(Tools.subbytes(data, offset, offset += 4)));
                offset += BC_PLANES.length;
                setBitCount(Tools.bytes2short(Tools.subbytes(data, offset, offset += 2)));
                setCompression(Tools.bytes2int(Tools.subbytes(data, offset, offset += 4)));
                biSizeImage = Tools.subbytes(data, offset, offset += 4);
                imageSize = Tools.bytes2int(biSizeImage);
                setPixPerMeterX(Tools.bytes2int(Tools.subbytes(data, offset, offset += 4)));
                setPixPerMeterY(Tools.bytes2int(Tools.subbytes(data, offset, offset += 4)));
                biClrUsed = Tools.subbytes(data, offset, offset += 4);
                clrUsed = Tools.bytes2int(biClrUsed);
                setCirImportant(Tools.bytes2int(Tools.subbytes(data, offset, offset += 4)));
                if (clrUsed > 0) {
                    for (int i = 0; i < clrUsed; i++) {
                        int color = i * 4 + offset;
                        int r = Byte.toUnsignedInt(data[color + 2]);
                        int g = Byte.toUnsignedInt(data[color + 1]);
                        int b = Byte.toUnsignedInt(data[color]);
                        addColor(r, g, b);
                    }
                }
            }
            if (compression == 0) {
                int tempW = 0;
                if (bitCount == 1) tempW = (width % 32 == 0) ? width : (32 - width % 32 + width) / 8;
                if (bitCount == 4) tempW = (width % 8 == 0) ? width : (8 - width % 8 + width) / 2;
                if (bitCount == 8) tempW = (width % 4 == 0) ? width : (4 - width % 4 + width);
                if (bitCount == 24) tempW = (width * 3 % 4 == 0) ? width : 4 - width * 3 % 4 + width * 3;
                if (bitCount == 32) tempW = width * 4;
                if (tempW <= 0) throw new IOException(ERROR_BITCOUNT);

                for (int i = imageOffset; i < fileSize; i += tempW) {
                    byte[] img = new byte[tempW];
                    for (int w = 0; w < tempW; w++) {
                        img[w] = data[i + w];
                    }
                    image.add(img);
                }
            }
            if(compression == 1) {
                
            }
        } catch (IOException e) {
            e.printStackTrace();
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

    /**
     * <b>出力</b>
     * 
     * @param file
     *            ファイル先
     */
    public void output(String file) {
        int offset = FILE_HEADER_SIZE + INFO_HEADER_SIZE + colors.size() * 4;
        bfOffBits = Tools.int2bytes(offset);

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
            out.write(biSizeImage);
            out.write(biXPelsPerMeter);
            out.write(biYPelsPerMeter);
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
                    out = null;
                }
            }
        }
    }

    private void initialize() {
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
        biSizeImage = Tools.int2bytes(imageSize);
        bfSize = Tools.int2bytes(fileSize);
    }

    private void updateFileSize() {
        fileSize = FILE_HEADER_SIZE + INFO_HEADER_SIZE + imageSize + colors.size() * 4;
        bfSize = Tools.int2bytes(fileSize);
    }

    private void updateImageSize(int compression) {
        int tempW = 0;
        switch (compression) {
            case 0:
                if (bitCount == 1) tempW = (width % 32 == 0) ? width : (32 - width % 32 + width) / 8;
                if (bitCount == 4) tempW = (width % 8 == 0) ? width : (8 - width % 8 + width) / 2;
                if (bitCount == 8) tempW = (width % 4 == 0) ? width : (4 - width % 4 + width);
                if (bitCount == 24) tempW = (width * 3 % 4 == 0) ? width : 4 - width * 3 % 4 + width * 3;
                if (bitCount == 32) tempW = width * 4;
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
        biSizeImage = Tools.int2bytes(imageSize);
    }

    private void updateClrUsed() {
        clrUsed = colors.size();
        biClrUsed = Tools.int2bytes(clrUsed);
    }
}
