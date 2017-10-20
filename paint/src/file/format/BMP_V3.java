package file.format;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import file.Tools;

/**
 * <b>BMP Windows V3</b><br>
 * date: 2017/10/12 last_date: 2017/10/20<br>
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
 */
public class BMP_V3 extends BMP_V1 {
    // 情報ヘッダ
    protected byte[]           biCompression;                       // 圧縮形式
    protected byte[]           biSizeImage;                         // 画像データサイズ (byte)
    protected byte[]           biXPelsPerMeter;                     // 水平方向の解像度 0の場合もある
    protected byte[]           biYPelsPerMeter;                     // 垂直方向の解像度 0の場合もある
    protected byte[]           biClrUsed;                           // 格納されているパレット数 (使用色数) 0の場合もある
    protected byte[]           biCirImportant;                      // 重要なパレットのインデックス 0の場合もある

    protected final int        infoHeaderSize     = 40;             // 情報ヘッダサイズ

    public static final String CAN_NOT_USE_METHOD = "使用できないメソッドです。";

    /**
     * <b>BMP - Windows V3</b>
     */
    public BMP_V3() {
        clear();
    }

    @Override
    public void clear() {
        super.clear();
        bcSize = Tools.int2bytes(infoHeaderSize);
        bcWidth = Tools.int2bytes(0);
        bcHeight = Tools.int2bytes(0);
        biCompression = Tools.int2bytes(0);
        biSizeImage = Tools.int2bytes(0);
        biXPelsPerMeter = Tools.int2bytes(0);
        biYPelsPerMeter = Tools.int2bytes(0);
        biClrUsed = Tools.int2bytes(0);
        biCirImportant = Tools.int2bytes(0);
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
    public void clearColors() {
        super.clearColors();
        setClrUsed(colors.size());
    }

    @Override
    public void addColor(int r, int g, int b) {
        super.addColor(r, g, b);
        setClrUsed(colors.size());
    }

    @Override
    public byte[] getBitmapHeader() {
        ByteBuffer buff = ByteBuffer.allocate(infoHeaderSize);
        buff.put(super.getBitmapHeader());
        buff.put(biCompression);
        buff.put(biSizeImage);
        buff.put(biXPelsPerMeter);
        buff.put(biYPelsPerMeter);
        buff.put(biClrUsed);
        buff.put(biCirImportant);
        return buff.array();
    }

    @Override
    public void set(byte[] data) {
        byte[] fileHeader = Tools.subbytes(data, 0, FILE_HEADER_SIZE);
        byte[] infoHeader = Tools.subbytes(data, FILE_HEADER_SIZE, FILE_HEADER_SIZE + infoHeaderSize);
        setFileHeader(fileHeader);
        setInfoHeader(infoHeader);
        byte[] bColors = Tools.subbytes(data, 0x001A, Tools.bytes2int(bfOffBits));
        setColors(bColors);
        byte[] imgData = Tools.subbytes(data, Tools.bytes2int(bfOffBits), getFileSize());
        setImage(imgData);
    }

    @Override
    public int setInfoHeader(byte[] data) {
        int offset = 0;
        bcSize = Tools.subbytes(data, offset, offset += 4);
        bcWidth = Tools.subbytes(data, offset, offset += 4);
        bcHeight = Tools.subbytes(data, offset, offset += 4);
        bcBitCount = Tools.subbytes(data, offset += 2, offset += 2);
        biCompression = Tools.subbytes(data, offset, offset += 4);
        biSizeImage = Tools.subbytes(data, offset, offset += 4);
        biXPelsPerMeter = Tools.subbytes(data, offset, offset += 4);
        biYPelsPerMeter = Tools.subbytes(data, offset, offset += 4);
        biClrUsed = Tools.subbytes(data, offset, offset += 4);
        biCirImportant = Tools.subbytes(data, offset, offset += 4);
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
            int endOffset = getOffset() + getSizeImage();
            for (int i = getOffset(); i < endOffset; i += 2) {
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
        ByteBuffer buff = ByteBuffer.allocate(FILE_HEADER_SIZE + infoHeaderSize + colors.size() * 4 + imageSize);
        buff.put(getFileHeader());
        buff.put(getInfoHeader());
        colors.forEach(color -> {
            buff.put(color);
        });
        image.forEach(img -> {
            buff.put(img);
        });
        return buff.array();
    }

    @Override
    public byte[] getInfoHeader() {
        ByteBuffer buff = ByteBuffer.allocate(infoHeaderSize);
        buff.put(super.getInfoHeader());
        buff.put(biCompression);
        buff.put(biSizeImage);
        buff.put(biXPelsPerMeter);
        buff.put(biYPelsPerMeter);
        buff.put(biClrUsed);
        buff.put(biCirImportant);
        return buff.array();
    }

    @Override
    protected void updateFileSize() {
        int fileSize = FILE_HEADER_SIZE + infoHeaderSize + getSizeImage() + colors.size() * 4;
        bfSize = Tools.int2bytes(fileSize);
    }
}