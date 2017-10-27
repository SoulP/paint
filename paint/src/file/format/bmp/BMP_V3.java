package file.format.bmp;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import file.Tools;
import file.io.BMP;

/**
 * <b>BMP Windows V3</b><br>
 * date: 2017/10/12 last_date: 2017/10/25<br>
 * <style> table, th, td { border: 1px solid; } table { border-collapse:
 * collapse; } </style>
 * <table>
 * <caption>ファイルヘッダ</caption>
 * <tr>
 * <th>オフセット</th>
 * <th>サイズ</th>
 * <th>格納する情報</th>
 * <th>値・備考</th>
 * <th>変数</th>
 * </tr>
 * <tr>
 * <td>0x0000</td>
 * <td>2 バイト</td>
 * <td>ファイルタイプ</td>
 * <td>常にBM (0x42, 0x4d)</td>
 * <td>bfType</td>
 * </tr>
 * <tr>
 * <td>0x0002</td>
 * <td>4 バイト</td>
 * <td>ファイルサイズ</td>
 * <td>ビットマップファイルのサイズを格納する（単位はバイト）</td>
 * <td>bfSize</td>
 * </tr>
 * <tr>
 * <td>0x0006</td>
 * <td>2 バイト</td>
 * <td>予約領域1</td>
 * <td>常に0</td>
 * <td>BF_RESERVED_1</td>
 * </tr>
 * <tr>
 * <td>0x0008</td>
 * <td>2 バイト</td>
 * <td>予約領域2</td>
 * <td>常に0</td>
 * <td>BF_RESERVED_2</td>
 * </tr>
 * <tr>
 * <td>0x000A</td>
 * <td>4 バイト</td>
 * <td>オフセット</td>
 * <td>ファイルヘッダの先頭アドレスからビットマップデータの先頭アドレスまでのオフセット（単位はバイト）</td>
 * <td>bfOffBits</td>
 * </tr>
 * </table>
 * <br>
 * <table>
 * <caption>情報ヘッダ</caption>
 * <tr>
 * <th>オフセット</th>
 * <th>サイズ</th>
 * <th>格納する情報</th>
 * <th>値・備考</th>
 * <th>変数</th>
 * </tr>
 * <tr>
 * <td>0x000E</td>
 * <td>4 バイト</td>
 * <td>ヘッダサイズ</td>
 * <td>40</td>
 * <td>bcSize</td>
 * </tr>
 * <tr>
 * <td>0x0012</td>
 * <td>4 バイト</td>
 * <td>ビットマップの横幅</td>
 * <td>単位はピクセル</td>
 * <td>bcWidth</td>
 * </tr>
 * <tr>
 * <td>0x0016</td>
 * <td>4 バイト</td>
 * <td>ビットマップの縦幅</td>
 * <td>単位はピクセル</td>
 * <td>bcHeight</td>
 * </tr>
 * <tr>
 * <td>0x001A</td>
 * <td>2 バイト</td>
 * <td>プレーン数</td>
 * <td>常に1</td>
 * <td>BC_PLANES</td>
 * </tr>
 * <tr>
 * <td>0x001C</td>
 * <td>2 バイト</td>
 * <td>1ピクセルあたりのビット数</td>
 * <td>0, 1, 4, 8, 16, 24, 32</td>
 * <td>bcBitCount</td>
 * </tr>
 * <tr>
 * <td>0x001E</td>
 * <td>4 バイト</td>
 * <td>圧縮形式</td>
 * <td>0 - BI_RGB （非圧縮）<br>
 * 1 - BI_RLE8 （8ビット/ピクセル）<br>
 * 2 - BI_RLE4 （4ビット/ピクセル）<br>
 * 3 - BI_BITFIELDS （ビットフィールド）<br>
 * 4 - BI_JPEG<br>
 * 5 - BI_PNG</td>
 * <td>biCompression</td>
 * </tr>
 * <td>0x0022</td>
 * <td>4 バイト</td>
 * <td>画像データサイズ</td>
 * <td>単位はバイト</td>
 * <td>biSizeImage</td>
 * </tr>
 * <tr>
 * <td>0x0026</td>
 * <td>4 バイト</td>
 * <td>水平方向の解像度</td>
 * <td>単位はピクセル/m</td>
 * <td>biXPelsPerMeter</td>
 * </tr>
 * <td>0x002A</td>
 * <td>4 バイト</td>
 * <td>垂直方向の解像度</td>
 * <td>単位はピクセル/m</td>
 * <td>biYPelsPerMeter</td>
 * </tr>
 * <tr>
 * <td>0x002E</td>
 * <td>4 バイト</td>
 * <td>使用する色数</td>
 * <td>ビットマップで実際に使用するカラーパレット内のカラーインデックスの数</td>
 * <td>biClrUsed</td>
 * </tr>
 * <tr>
 * <td>0x0032</td>
 * <td>4 バイト</td>
 * <td>重要な色数</td>
 * <td>ビットマップを表示するために必要なカラーインデックスの数</td>
 * <td>biCirImportant</td>
 * </tr>
 * </table>
 * 
 * @author ソウルP
 * @version 1.0 2017/10/12 BMP_V3作成
 * @version 1.1 2017/10/18 ファイルヘッダと一部の情報ヘッダを BMP_V1 に移動
 * @version 1.2 2017/10/25 ビットフィールド追加
 * @version 1.3 2017/10/27 GAP1 と GAP2 （オプショナル）追加
 */
public class BMP_V3 extends BMP_V1 {
    // 情報ヘッダ
    protected byte[]           biCompression;                       // 圧縮形式
    protected byte[]           biSizeImage;                         // 画像データサイズ (byte)
    protected byte[]           biXPelsPerMeter;                     // 水平方向の解像度 0の場合もある
    protected byte[]           biYPelsPerMeter;                     // 垂直方向の解像度 0の場合もある
    protected byte[]           biClrUsed;                           // 格納されているパレット数 (使用色数) 0の場合もある
    protected byte[]           biCirImportant;                      // 重要なパレットのインデックス 0の場合もある
    protected byte[]           biBitFields;                         // ビットフィールド形式
    protected byte[]           gap1;                                // オプショナル
    protected byte[]           gap2;                                // オプショナル

    public static final String CAN_NOT_USE_METHOD = "使用できないメソッドです。";

    /**
     * <b>BMP - Windows V3</b>
     */
    public BMP_V3() {
        colors = new ArrayList<>();
        image = new ArrayList<>();
        clear();
    }

    /**
     * <b>BMP - Windows V3</b>
     * 
     * @param data
     *            データ
     */
    public BMP_V3(byte[] data) {
        this();
        set(data);
    }

    /**
     * <b>BMP - Windows V3</b>
     * 
     * @param bmp
     *            BMPのオブジェクト
     */
    public BMP_V3(BMP bmp) {
        this();
        set(bmp);
    }

    @Override
    public void clear() {
        super.clear();
        setInfoHeaderSize(INFO_HEADER_SIZE_V3);
        setWidth(0);
        setHeight(0);
        setCompression(0);
        setSizeImage(0);
        setXPelsPerMeter(0);
        setYPelsPerMeter(0);
        setClrUsed(0);
        setCirImportant(0);
        biBitFields = new byte[0];
        gap1 = new byte[0];
        gap2 = new byte[0];
    }

    /**
     * @deprecated V1用のメソッドです。<br>
     *             int型からshort型にキャストされるため、正確な情報は{@link #getWidth()}から取得お願いします。
     */
    @Deprecated
    @Override
    public final short getWidthV1() {
        return (short) getWidth();
    }

    /**
     * 単位はピクセル
     * 
     * @return ビットマップの横幅
     */
    public int getWidth() {
        return Tools.bytes2int(bcWidth);
    }

    @Override
    public void setWidth(short bcWidth) {
        setWidth((int) bcWidth);
    }

    /**
     * 単位はピクセル
     * 
     * @param bcWidth
     *            ビットマップの横幅
     */
    public void setWidth(int bcWidth) {
        this.bcWidth = Tools.int2bytes(bcWidth);
    }

    /**
     * @deprecated V1用のメソッドです。<br>
     *             int型からshort型にキャストされるため、正確な情報は{@link #getHeight()}から取得お願いします。
     */
    @Deprecated
    @Override
    public final short getHeightV1() {
        return (short) getHeight();
    }

    /**
     * 単位はピクセル
     * 
     * @return ビットマップの縦幅
     */
    public int getHeight() {
        return Tools.bytes2int(bcHeight);
    }

    @Override
    public void setHeight(short bcHeight) {
        setHeight((int) bcHeight);
    }

    /**
     * 単位はピクセル
     * 
     * @param bcHeight
     *            ビットマップの縦幅
     */
    public void setHeight(int bcHeight) {
        this.bcHeight = Tools.int2bytes(bcHeight);
    }

    /**
     * <ul>
     * 圧縮形式
     * <li>0 - BI_RGB （非圧縮）</li>
     * <li>1 - BI_RLE8 （8ビット/ピクセル）</li>
     * <li>2 - BI_RLE4 （4ビット/ピクセル）</li>
     * <li>3 - BI_BITFIELDS （ビットフィールド）</li>
     * <li>4 - BI_JPEG</li>
     * <li>5 - BI_PNG</li> 上記以外の圧縮形式
     * <li>3 - HUFFMAN_1D （1ビットハフマン符号化、 OS/2 2.x）</li>
     * <li>4 - RLE_24 （24ビット/ピクセル、OS/2 2.x）</li>
     * <li>6 - BI_ALPHABITFIELDS （アルファチャンネル付きビットフィールド、Windows CE 5.0）</li>
     * </ul>
     * 
     * @return 圧縮形式
     */
    public int getCompression() {
        return Tools.bytes2int(biCompression);
    }

    /**
     * <ul>
     * 圧縮形式
     * <li>0 - BI_RGB （非圧縮）</li>
     * <li>1 - BI_RLE8 （8ビット/ピクセル）</li>
     * <li>2 - BI_RLE4 （4ビット/ピクセル）</li>
     * <li>3 - BI_BITFIELDS （ビットフィールド）</li>
     * <li>4 - BI_JPEG</li>
     * <li>5 - BI_PNG</li> 上記以外の圧縮形式
     * <li>3 - HUFFMAN_1D （1ビットハフマン符号化、 OS/2 2.x）</li>
     * <li>4 - RLE_24 （24ビット/ピクセル、OS/2 2.x）</li>
     * <li>6 - BI_ALPHABITFIELDS （アルファチャンネル付きビットフィールド、Windows CE 5.0）</li>
     * </ul>
     * 
     * @param biCompression
     *            圧縮形式
     */
    public void setCompression(int biCompression) {
        this.biCompression = Tools.int2bytes(biCompression);
    }

    /**
     * 単位はバイト
     * 
     * @return 画像データサイズ
     */
    public int getSizeImage() {
        return Tools.bytes2int(biSizeImage);
    }

    /**
     * 単位はバイト
     * 
     * @param biSizeImage
     *            画像データサイズ
     */
    public void setSizeImage(int biSizeImage) {
        this.biSizeImage = Tools.int2bytes(biSizeImage);
    }

    /**
     * 単位はピクセル/m
     * 
     * @return 水平方向の解像度
     */
    public int getXPelsPerMeter() {
        return Tools.bytes2int(biXPelsPerMeter);
    }

    /**
     * 単位はピクセル/m
     * 
     * @param biXPelsPerMeter
     *            水平方向の解像度
     */
    public void setXPelsPerMeter(int biXPelsPerMeter) {
        this.biXPelsPerMeter = Tools.int2bytes(biXPelsPerMeter);
    }

    /**
     * 単位はピクセル/m
     * 
     * @return 垂直方向の解像度
     */
    public int getYPelsPerMeter() {
        return Tools.bytes2int(biYPelsPerMeter);
    }

    /**
     * 単位はピクセル/m
     * 
     * @param biYPelsPerMeter
     *            垂直方向の解像度
     */
    public void setYPelsPerMeter(int biYPelsPerMeter) {
        this.biYPelsPerMeter = Tools.int2bytes(biYPelsPerMeter);
    }

    /**
     * @return 使用する色数
     */
    public int getClrUsed() {
        return Tools.bytes2int(biClrUsed);
    }

    /**
     * @param biClrUsed
     *            使用する色数
     */
    public void setClrUsed(int biClrUsed) {
        this.biClrUsed = Tools.int2bytes(biClrUsed);
    }

    /**
     * @return 重要な色数
     */
    public int getCirImportant() {
        return Tools.bytes2int(biCirImportant);
    }

    /**
     * @param biCirImportant
     *            重要な色数
     */
    public void setCirImportant(int biCirImportant) {
        this.biCirImportant = Tools.int2bytes(biCirImportant);
    }

    @Override
    public void setColors(List<byte[]> colors) {
        this.colors = colors;
        setClrUsed(colors.size());
    }

    @Override
    public void addColor(int r, int g, int b) {
        byte[] color = { (byte) b, (byte) g, (byte) r, 0x00 };
        colors.add(color);
        setClrUsed(colors.size());
    }

    /**
     * 12 バイト (RGB)<br>
     * もしくは<br>
     * 16 バイト (RGBA)
     * 
     * @return ビットフィールド
     */
    public byte[] getBitFields() {
        return Tools.endian(biBitFields);
    }

    /**
     * 12 バイト (RGB)<br>
     * もしくは<br>
     * 16 バイト (RGBA)
     * 
     * @param bitfields
     *            ビットフィールド
     */
    public void setBitFields(byte[] biBitFields) {
        this.biBitFields = Tools.endian(biBitFields);
    }

    /**
     * @return 空の場合 true<br>
     *         空ではない場合 false
     */
    public boolean isEmptyBitFields() {
        return biBitFields == null || biBitFields.length == 0;
    }

    /**
     * @return オプショナル
     */
    public byte[] getGap1() {
        return gap1;
    }

    /**
     * @param gap1
     *            オプショナル
     */
    public void setGap1(byte[] gap1) {
        this.gap1 = gap1;
    }

    /**
     * @return 空の場合 true<br>
     *         空ではない場合 false
     */
    public boolean isEmptyGap1() {
        return gap1 == null || gap1.length == 0;
    }

    /**
     * @return オプショナル
     */
    public byte[] getGap2() {
        return gap2;
    }

    /**
     * @param gap2
     *            オプショナル
     */
    public void setGap2(byte[] gap2) {
        this.gap2 = gap2;
    }

    /**
     * @return 空の場合 true<br>
     *         空ではない場合 false
     */
    public boolean isEmptyGap2() {
        return gap2 == null || gap2.length == 0;
    }

    @Override
    public byte[] getBitmapHeader() {
        ByteBuffer buff = ByteBuffer.allocate(FILE_HEADER_SIZE + INFO_HEADER_SIZE_V3);
        buff.put(getFileHeader());
        buff.put(getInfoHeader());
        return buff.array();
    }

    @Override
    public void set(byte[] data) {
        int endHeaderOffset = FILE_HEADER_SIZE + INFO_HEADER_SIZE_V3;
        byte[] fileHeader = Arrays.copyOfRange(data, 0, FILE_HEADER_SIZE);
        byte[] infoHeader = Arrays.copyOfRange(data, FILE_HEADER_SIZE, endHeaderOffset);
        setFileHeader(fileHeader);
        setInfoHeader(infoHeader);
        int bitcount = getBitCount();
        int compression = getCompression();
        if (bitcount <= 8) {
            int endColorOffset = endHeaderOffset + getClrUsed() * 4;
            byte[] bColors = Arrays.copyOfRange(data, endHeaderOffset, endColorOffset);
            setColors(bColors);
            gap1 = Arrays.copyOfRange(data, endColorOffset, getOffset());
        } else {
            clearColors();
            if ((bitcount == 16 || bitcount == 32)) {
                if (compression == 3) {
                    int endBitFieldsOffset = endHeaderOffset + 12;
                    biBitFields = Arrays.copyOfRange(data, endHeaderOffset, endBitFieldsOffset);
                    gap1 = Arrays.copyOfRange(data, endBitFieldsOffset, getOffset());
                } else if (compression == 6) {
                    int endBitFieldsOffset = endHeaderOffset + 16;
                    biBitFields = Arrays.copyOfRange(data, endHeaderOffset, endBitFieldsOffset);
                    gap1 = Arrays.copyOfRange(data, endBitFieldsOffset, getOffset());
                } else {
                    biBitFields = null;
                    gap1 = Arrays.copyOfRange(data, endHeaderOffset, getOffset());
                }
            } else {
                biBitFields = null;
                gap1 = Arrays.copyOfRange(data, endHeaderOffset, getOffset());
            }
        }
        int imageSize = getSizeImage();
        byte[] imgData;
        if (imageSize == 0) {
            imgData = Arrays.copyOfRange(data, getOffset(), data.length);
        } else {
            int endImageOffset = getOffset() + imageSize;
            imgData = Arrays.copyOfRange(data, getOffset(), endImageOffset);
            gap2 = Arrays.copyOfRange(data, endImageOffset, data.length);
        }
        setImage(imgData);
    }

    @Override
    public void set(BMP bmp) {
        super.set(bmp);
        setWidth(bmp.getWidth());
        setHeight(bmp.getHeight());
        setCompression(bmp.getCompression());
        setSizeImage(bmp.getImageSize());
        setXPelsPerMeter(bmp.getPixPerMeterX());
        setYPelsPerMeter(bmp.getPixPerMeterY());
        setClrUsed(bmp.getClrUsed());
        setCirImportant(bmp.getCirImportant());
        if (!bmp.isEmptyBitFields()) setBitFields(bmp.getBitFields());
        if (!bmp.isEmptyGap1()) setGap1(bmp.getGap1());
        if (!bmp.isEmptyGap2()) setGap2(bmp.getGap2());
    }

    @Override
    public int setInfoHeader(byte[] data) {
        int offset = 4;
        bcWidth = Arrays.copyOfRange(data, offset, offset += 4);
        bcHeight = Arrays.copyOfRange(data, offset, offset += 4);
        bcBitCount = Arrays.copyOfRange(data, offset += 2, offset += 2);
        biCompression = Arrays.copyOfRange(data, offset, offset += 4);
        biSizeImage = Arrays.copyOfRange(data, offset, offset += 4);
        biXPelsPerMeter = Arrays.copyOfRange(data, offset, offset += 4);
        biYPelsPerMeter = Arrays.copyOfRange(data, offset, offset += 4);
        biClrUsed = Arrays.copyOfRange(data, offset, offset += 4);
        biCirImportant = Arrays.copyOfRange(data, offset, offset += 4);
        return offset;
    }

    @Override
    public void setColors(byte[] data) {
        int clrUsed = Tools.bytes2int(biClrUsed);
        for (int i = 0; i < clrUsed; i++) {
            int colorOffset = i * 4;
            int r = Byte.toUnsignedInt(data[colorOffset + 2]);
            int g = Byte.toUnsignedInt(data[colorOffset + 1]);
            int b = Byte.toUnsignedInt(data[colorOffset]);
            addColor(r, g, b);
        }
    }

    @Override
    public void setImage(byte[] data) {
        if (getCompression() == 1 || getCompression() == 2) {
            List<Byte> bytes = new ArrayList<>();
            byte[] byteArray;
            for (int i = 0; i < data.length; i += 2) {
                byte b = data[i];
                byte bb = data[i + 1];
                bytes.add(b);
                bytes.add(bb);

                if (b == 0x00 & (bb == 0x00 || bb == 0x01)) {
                    byteArray = new byte[bytes.size()];
                    for (int a = 0; a < byteArray.length; a++)
                        byteArray[a] = bytes.get(a);
                    bytes.clear();
                    image.add(byteArray);
                    if (bb == 0x01) break;
                }
            }
        } else {
            super.setImage(data);
        }
    }

    @Override
    public byte[] get() {
        int imageSize = 0;
        for (byte[] b : image)
            imageSize += b.length;
        setSizeImage(imageSize);
        int bitcount = getBitCount();
        int compression = getCompression();
        int optSize = (bitcount <= 8) ? colors.size() * 4
                : ((bitcount == 16 || bitcount == 32)) ? (compression == 3) ? 12 : (compression == 6) ? 16 : 0 : 0;
        if (!isEmptyGap1()) optSize += gap1.length;
        if (!isEmptyGap2() && imageSize != 0) optSize += gap2.length;
        ByteBuffer buff = ByteBuffer.allocate(FILE_HEADER_SIZE + INFO_HEADER_SIZE_V3 + optSize + imageSize);
        buff.put(getBitmapHeader());
        if (bitcount <= 8) {
            colors.forEach(color -> {
                buff.put(color);
            });
        } else if ((compression == 3 || compression == 6) && (bitcount == 16 || bitcount == 32)) {
            if (!isEmptyBitFields()) buff.put(biBitFields);
        }

        if (!isEmptyGap1()) buff.put(gap1);

        image.forEach(img -> {
            buff.put(img);
        });

        if (!isEmptyGap2() && imageSize != 0) buff.put(gap2);

        return buff.array();
    }

    @Override
    public byte[] getInfoHeader() {
        ByteBuffer buff = ByteBuffer.allocate(INFO_HEADER_SIZE_V3);
        buff.put(bcSize);
        buff.put(bcWidth);
        buff.put(bcHeight);
        buff.put(BC_PLANES);
        buff.put(bcBitCount);
        buff.put(biCompression);
        buff.put(biSizeImage);
        buff.put(biXPelsPerMeter);
        buff.put(biYPelsPerMeter);
        buff.put(biClrUsed);
        buff.put(biCirImportant);
        return buff.array();
    }

    @Override
    public int getVersion() {
        return 3;
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer(toStr());
        buff.append(STR_NEW_LINE);
        buff.append(STR_BITFIELDS);
        if (!isEmptyBitFields()) {
            buff.append(STR_BITFIELDS_RED);
            buff.append(STR_0X);
            byte[] bF = Tools.endian(biBitFields);
            for (int i = 0; i < 4; i++)
                buff.append(String.format(STR_16BIT_FORMAT_NO_SPACE, bF[i]));
            buff.append(STR_NEW_LINE);
            buff.append(STR_BITFIELDS_GREEN);
            for (int i = 4; i < 8; i++)
                buff.append(String.format(STR_16BIT_FORMAT_NO_SPACE, bF[i]));
            buff.append(STR_NEW_LINE);
            buff.append(STR_BITFIELDS_BLUE);
            for (int i = 8; i < 12; i++)
                buff.append(String.format(STR_16BIT_FORMAT_NO_SPACE, bF[i]));
            if (getCompression() == 6) {
                buff.append(STR_NEW_LINE);
                buff.append(STR_BITFIELDS_ALPHA);
                for (int i = 12; i < 16; i++)
                    buff.append(String.format(STR_16BIT_FORMAT_NO_SPACE, bF[i]));
            }
        }
        buff.append(STR_NEW_LINE);
        buff.append(STR_NEW_LINE);
        buff.append(toStrColorImage());
        buff.append(STR_NEW_LINE);
        buff.append(STR_NEW_LINE);
        buff.append(STR_GAP1);
        buff.append(STR_NEW_LINE);
        if (!isEmptyGap1()) {
            for (int i = 0; i < gap1.length;) {
                buff.append(String.format(STR_16BIT_FORMAT, gap1[i]));
                if (++i % 8 == 0) buff.append(STR_NEW_LINE);
            }
        }
        buff.append(STR_NEW_LINE);
        buff.append(STR_NEW_LINE);
        buff.append(STR_GAP2);
        buff.append(STR_NEW_LINE);
        if (!isEmptyGap2()) {
            for (int i = 0; i < gap2.length;) {
                buff.append(String.format(STR_16BIT_FORMAT, gap2[i]));
                if (++i % 8 == 0) buff.append(STR_NEW_LINE);
            }
        }

        return buff.toString();
    }

    protected String toStr() {
        byte[] t = getType();
        StringBuffer buff = new StringBuffer();
        buff.append(STR_BMP_V);
        buff.append(getVersion());
        buff.append(STR_NEW_LINE);

        buff.append(STR_FILE_TYPE);
        buff.append((char) t[0]);
        buff.append((char) t[1]);
        buff.append(STR_SPACE);

        buff.append(STR_BRACKET_LEFT);
        buff.append(Arrays.equals(t, new byte[] { 0x42, 0x4D }) ? STR_BMP : STR_UNKNOWN);
        buff.append(STR_BRACKET_RIGHT);
        buff.append(STR_NEW_LINE);

        buff.append(STR_FILE_SIZE);
        buff.append(getFileSize());
        buff.append(STR_BYTE);
        buff.append(STR_NEW_LINE);

        buff.append(STR_INFO_HEADER_SIZE);
        buff.append(getInfoHeaderSize());
        buff.append(STR_BYTE);
        buff.append(STR_NEW_LINE);

        buff.append(STR_WIDTH);
        buff.append(getWidth());
        buff.append(STR_PIXEL);
        buff.append(STR_NEW_LINE);

        buff.append(STR_HEIGHT);
        buff.append(getHeight());
        buff.append(STR_PIXEL);
        buff.append(STR_NEW_LINE);

        buff.append(STR_PLANES);
        buff.append(getPlanes());
        buff.append(STR_NEW_LINE);

        buff.append(STR_BITCOUNT);
        buff.append(getBitCount());
        buff.append(STR_BIT);
        buff.append(STR_NEW_LINE);

        buff.append(STR_COMPRESSION);
        switch (getCompression()) {
            case 0:
                buff.append(STR_BI_RGB);
                break;
            case 1:
                buff.append(STR_BI_RLE8);
                break;
            case 2:
                buff.append(STR_BI_RLE4);
                break;
            case 3:
                buff.append(STR_BI_BITFIELDS);
                break;
            case 4:
                buff.append(STR_BI_JPEG);
                break;
            case 5:
                buff.append(STR_BI_PNG);
                break;
            case 6:
                buff.append(STR_BI_ALPHABITFIELDS);
                break;
            default:
                buff.append(STR_UNKNOWN);
                break;
        }
        buff.append(STR_NEW_LINE);

        buff.append(STR_IMAGE_SIZE);
        buff.append(getSizeImage());
        buff.append(STR_BYTE);
        buff.append(STR_NEW_LINE);

        buff.append(STR_PIX_PER_METER_X);
        buff.append(getXPelsPerMeter());
        buff.append(STR_PIXEL_M);
        buff.append(STR_NEW_LINE);

        buff.append(STR_PIX_PER_METER_Y);
        buff.append(getYPelsPerMeter());
        buff.append(STR_PIXEL_M);
        buff.append(STR_NEW_LINE);

        buff.append(STR_COLOR_USED);
        buff.append(colors.size());
        buff.append(STR_NEW_LINE);

        buff.append(STR_COLOR_IMPORTANT);
        int important = getCirImportant();
        if (important == 0) buff.append(STR_ALL);
        else buff.append(important);
        buff.append(STR_NEW_LINE);

        return buff.toString();
    }

    @Override
    protected void updateFileSize() {
        int fileSize = FILE_HEADER_SIZE + INFO_HEADER_SIZE_V3 + getSizeImage() + colors.size() * 4;
        bfSize = Tools.int2bytes(fileSize);
    }
}
