package file.format.bmp;

import java.nio.ByteBuffer;
import java.util.Arrays;

import file.Tools;
import file.io.BMP;

/**
 * <b>BMP Windows V4</b><br>
 * date: 2017/10/18 last_date: 2017/10/24<br>
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
 * <td>108</td>
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
 * <tr>
 * <td>0x0036</td>
 * <td>4 バイト</td>
 * <td>赤成分のカラーマスク</td>
 * <td></td>
 * <td>bV4RedMask</td>
 * </tr>
 * <tr>
 * <td>0x003A</td>
 * <td>4 バイト</td>
 * <td>緑成分のカラーマスク</td>
 * <td></td>
 * <td>bV4GreenMask</td>
 * </tr>
 * <tr>
 * <td>0x003E</td>
 * <td>4 バイト</td>
 * <td>青成分のカラーマスク</td>
 * <td></td>
 * <td>bV4BlueMask</td>
 * </tr>
 * <tr>
 * <td>0x0042</td>
 * <td>4 バイト</td>
 * <td>α成分のカラーマスク</td>
 * <td></td>
 * <td>bV4AlphaMask</td>
 * </tr>
 * <tr>
 * <td>0x0046</td>
 * <td>4 バイト</td>
 * <td>色空間</td>
 * <td>0 -ヘッダ内で定義</td>
 * <td>bV4CSType</td>
 * </tr>
 * <tr>
 * <td>0x004A</td>
 * <td>36 バイト</td>
 * <td>CIEXYZTRIPLE構造体</td>
 * <td>色空間が0の場合のみ有効</td>
 * <td>bV4Endpoints</td></td>
 * </tr>
 * <tr>
 * <td>0x006E</td>
 * <td>4 バイト</td>
 * <td>赤成分のガンマ値</td>
 * <td rowspan="3">色空間が0の場合のみ有効<br>
 * 16.16の固定小数点数</td>
 * <td>bV4GammaRed</td>
 * </tr>
 * <tr>
 * <td>0x0072</td>
 * <td>4 バイト</td>
 * <td>緑成分のガンマ値</td>
 * <td>bV4GammaGreen</td>
 * </tr>
 * <tr>
 * <td>0x0076</td>
 * <td>4 バイト</td>
 * <td>青成分のガンマ値</td>
 * <td>bV4GammaBlue</td>
 * </tr>
 * </table>
 * 
 * @author ソウルP
 * @version 1.0 2017/10/18 BMP_V4作成
 */
public class BMP_V4 extends BMP_V3 {
    // 情報ヘッダ
    protected byte[] bV4RedMask;    // 赤成分のカラーマスク
    protected byte[] bV4GreenMask;  // 緑成分のカラーマスク
    protected byte[] bV4BlueMask;   // 青成分のカラーマスク
    protected byte[] bV4AlphaMask;  // α成分のカラーマスク
    protected byte[] bV4CSType;     // 色空間
    protected byte[] bV4Endpoints;  // CIEXYZTRIPLE構造体
    protected byte[] bV4GammaRed;   // 赤成分のガンマ値
    protected byte[] bV4GammaGreen; // 緑成分のガンマ値
    protected byte[] bV4GammaBlue;  // 青成分のガンマ値

    /**
     * <b>BMP - Windows V4</b>
     */
    public BMP_V4() {
        clear();
    }

    /**
     * <b>BMP - Windows V4</b>
     * 
     * @param data
     *            データ
     */
    public BMP_V4(byte[] data) {
        clear();
        set(data);
    }

    /**
     * <b>BMP - Windows V4</b>
     * 
     * @param bmp
     *            BMPのオブジェクト
     */
    public BMP_V4(BMP bmp) {
        clear();
        set(bmp);
    }

    @Override
    public void clear() {
        super.clear();
        setInfoHeaderSize(INFO_HEADER_SIZE_V4);
        bV4RedMask = Tools.int2bytes(0);
        bV4GreenMask = Tools.int2bytes(0);
        bV4BlueMask = Tools.int2bytes(0);
        bV4AlphaMask = Tools.int2bytes(0);
        bV4CSType = Tools.int2bytes(0);
        bV4Endpoints = ByteBuffer.allocate(32).putLong(0).putLong(0).putLong(0).putLong(0).array();
        bV4GammaRed = Tools.float2bytes(0.0f);
        bV4GammaGreen = Tools.float2bytes(0.0f);
        bV4GammaBlue = Tools.float2bytes(0.0f);
    }

    /**
     * @return 赤成分のカラーマスク
     */
    public byte[] getRedMask() {
        return bV4RedMask;
    }

    /**
     * @param bV4RedMask
     *            赤成分のカラーマスク
     */
    public void setRedMask(byte[] bV4RedMask) {
        this.bV4RedMask = bV4RedMask;
    }

    /**
     * @return 緑成分のカラーマスク
     */
    public byte[] getGreenMask() {
        return bV4GreenMask;
    }

    /**
     * @param bV4GreenMask
     *            緑成分のカラーマスク
     */
    public void setGreenMask(byte[] bV4GreenMask) {
        this.bV4GreenMask = bV4GreenMask;
    }

    /**
     * @return 青成分のカラーマスク
     */
    public byte[] getBlueMask() {
        return bV4BlueMask;
    }

    /**
     * @param bV4BlueMask
     *            青成分のカラーマスク
     */
    public void setBlueMask(byte[] bV4BlueMask) {
        this.bV4BlueMask = bV4BlueMask;
    }

    /**
     * @return α成分のカラーマスク
     */
    public byte[] getAlphaMask() {
        return bV4AlphaMask;
    }

    /**
     * @param bV4AlphaMask
     *            α成分のカラーマスク
     */
    public void setAlphaMask(byte[] bV4AlphaMask) {
        this.bV4AlphaMask = bV4AlphaMask;
    }

    /**
     * <ul>
     * 色空間
     * <li>0 - ヘッダ内で定義</li>
     * </ul>
     * 
     * @return 色空間
     */
    public byte[] getCSType() {
        return bV4CSType;
    }

    /**
     * <ul>
     * 色空間
     * <li>0 - ヘッダ内で定義</li>
     * </ul>
     * 
     * @param bV4CSType
     *            色空間
     */
    public void setCSType(byte[] bV4CSType) {
        this.bV4CSType = bV4CSType;
    }

    /**
     * @return CIEXYZTRIPLE構造体
     */
    public byte[] getEndpoints() {
        return bV4Endpoints;
    }

    /**
     * @param bV4Endpoints
     *            CIEXYZTRIPLE構造体
     */
    public void setEndpoints(byte[] bV4Endpoints) {
        this.bV4Endpoints = bV4Endpoints;
    }

    /**
     * @return 赤成分のガンマ値
     */
    public float getGammaRed() {
        return Tools.bytes2float(bV4GammaRed);
    }

    /**
     * @param bV4GammaRed
     *            赤成分のガンマ値
     */
    public void setGammaRed(float bV4GammaRed) {
        this.bV4GammaRed = Tools.float2bytes(bV4GammaRed);
    }

    /**
     * @return 緑成分のガンマ値
     */
    public float getGammaGreen() {
        return Tools.bytes2float(bV4GammaGreen);
    }

    /**
     * @param bV4GammaGreen
     *            緑成分のガンマ値
     */
    public void setGammaGreen(float bV4GammaGreen) {
        this.bV4GammaGreen = Tools.float2bytes(bV4GammaGreen);
    }

    /**
     * @return 青成分のガンマ値
     */
    public float getGammaBlue() {
        return Tools.bytes2float(bV4GammaBlue);
    }

    /**
     * @param bV4GammaBlue
     *            青成分のガンマ値
     */
    public void setGammaBlue(float bV4GammaBlue) {
        this.bV4GammaBlue = Tools.float2bytes(bV4GammaBlue);
    }

    @Override
    public byte[] getBitmapHeader() {
        ByteBuffer buff = ByteBuffer.allocate(FILE_HEADER_SIZE + INFO_HEADER_SIZE_V4);
        buff.put(getFileHeader());
        buff.put(getInfoHeader());
        return buff.array();
    }

    @Override
    public void set(byte[] data) {
        byte[] fileHeader = Tools.subbytes(data, 0, FILE_HEADER_SIZE);
        byte[] infoHeader = Tools.subbytes(data, FILE_HEADER_SIZE, FILE_HEADER_SIZE + INFO_HEADER_SIZE_V4);
        setFileHeader(fileHeader);
        setInfoHeader(infoHeader);
        byte[] bColors = Tools.subbytes(data, 0x0036, Tools.bytes2int(bfOffBits));
        setColors(bColors);
        byte[] imgData = Tools.subbytes(data, Tools.bytes2int(bfOffBits), getFileSize());
        setImage(imgData);
    }

    @Override
    public void set(BMP bmp) {
        super.set(bmp);
        setRedMask(bmp.getRedMask());
        setGreenMask(bmp.getGreenMask());
        setBlueMask(bmp.getBlueMask());
        setAlphaMask(bmp.getAlphaMask());
        setCSType(bmp.getCSType());
        setEndpoints(bmp.getCiexyzTriple());
        setGammaRed(bmp.getGammaRed());
        setGammaGreen(bmp.getGammaGreen());
        setGammaBlue(bmp.getGammaBlue());
    }

    @Override
    public int setInfoHeader(byte[] data) {
        int offset = super.setInfoHeader(data);
        bV4RedMask = Tools.subbytes(data, offset, offset += 4);
        bV4GreenMask = Tools.subbytes(data, offset, offset += 4);
        bV4BlueMask = Tools.subbytes(data, offset, offset += 4);
        bV4AlphaMask = Tools.subbytes(data, offset, offset += 4);
        bV4CSType = Tools.subbytes(data, offset, offset += 4);
        bV4Endpoints = Tools.subbytes(data, offset, offset += 36);
        bV4GammaRed = Tools.subbytes(data, offset, offset += 4);
        bV4GammaGreen = Tools.subbytes(data, offset, offset += 4);
        bV4GammaBlue = Tools.subbytes(data, offset, offset += 4);
        return offset;
    }

    @Override
    public byte[] get() {
        int imageSize = 0;
        for (byte[] b : image)
            imageSize += b.length;
        setSizeImage(imageSize);
        ByteBuffer buff = ByteBuffer.allocate(FILE_HEADER_SIZE + INFO_HEADER_SIZE_V4 + colors.size() * 4 + imageSize);
        buff.put(getBitmapHeader());
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
        ByteBuffer buff = ByteBuffer.allocate(INFO_HEADER_SIZE_V4);
        buff.put(super.getInfoHeader());
        buff.put(bV4RedMask);
        buff.put(bV4GreenMask);
        buff.put(bV4BlueMask);
        buff.put(bV4AlphaMask);
        buff.put(bV4CSType);
        buff.put(bV4Endpoints);
        buff.put(bV4GammaRed);
        buff.put(bV4GammaGreen);
        buff.put(bV4GammaBlue);
        return buff.array();
    }

    @Override
    public int getVersion() {
        return 4;
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer(toStr());
        buff.append(STR_NEW_LINE);
        buff.append(toStrColorImage());

        return buff.toString();
    }

    @Override
    protected String toStr() {
        StringBuffer buff = new StringBuffer(super.toStr());
        buff.append(STR_RED_MASK);
        buff.append(STR_0X);
        for (byte b : getRedMask())
            buff.append(String.format("%02X", b));
        buff.append(STR_NEW_LINE);

        buff.append(STR_GREEN_MASK);
        buff.append(STR_0X);
        for (byte b : getGreenMask())
            buff.append(String.format("%02X", b));
        buff.append(STR_NEW_LINE);

        buff.append(STR_BLUE_MASK);
        buff.append(STR_0X);
        for (byte b : getBlueMask())
            buff.append(String.format("%02X", b));
        buff.append(STR_NEW_LINE);

        buff.append(STR_ALPHA_MASK);
        buff.append(STR_0X);
        for (byte b : getAlphaMask())
            buff.append(String.format("%02X", b));
        buff.append(STR_NEW_LINE);

        byte[] cs = getCSType();
        buff.append(STR_CSTYPE);
        if (Arrays.equals(cs, new byte[] { 0x00, 0x00, 0x00, 0x00 })) buff.append(STR_CSTYPE_HEADER);
        else {
            for (byte c : cs)
                buff.append((char) c);
        }
        buff.append(STR_NEW_LINE);
        buff.append(STR_NEW_LINE);

        buff.append(STR_CIEXYZTRIPLE);
        buff.append(STR_NEW_LINE);
        byte[] ciexyz = getEndpoints();
        for (int i = 0; i < ciexyz.length;) {
            buff.append(String.format(STR_16BIT_FORMAT, ciexyz[i]));
            if (++i % 4 == 0) buff.append(STR_NEW_LINE);
        }
        buff.append(STR_NEW_LINE);

        buff.append(STR_GAMMA_RED);
        buff.append(getGammaRed());
        buff.append(STR_NEW_LINE);

        buff.append(STR_GAMMA_GREEN);
        buff.append(getGammaGreen());
        buff.append(STR_NEW_LINE);

        buff.append(STR_GAMMA_BLUE);
        buff.append(getGammaBlue());
        buff.append(STR_NEW_LINE);

        return buff.toString();
    }

    @Override
    protected void updateFileSize() {
        int fileSize = FILE_HEADER_SIZE + INFO_HEADER_SIZE_V4 + getSizeImage() + colors.size() * 4;
        bfSize = Tools.int2bytes(fileSize);
    }
}