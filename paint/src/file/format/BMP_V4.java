package file.format;

import java.nio.ByteBuffer;

import file.Tools;

/**
 * <b>BMP Windows V4</b><br>
 * date: 2017/10/18 last_date: 2017/10/20<br>
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
    protected byte[]    bV4RedMask;           // 赤成分のカラーマスク
    protected byte[]    bV4GreenMask;         // 緑成分のカラーマスク
    protected byte[]    bV4BlueMask;          // 青成分のカラーマスク
    protected byte[]    bV4AlphaMask;         // α成分のカラーマスク
    protected byte[]    bV4CSType;            // 色空間
    protected byte[]    bV4Endpoints;         // CIEXYZTRIPLE構造体
    protected byte[]    bV4GammaRed;          // 赤成分のガンマ値
    protected byte[]    bV4GammaGreen;        // 緑成分のガンマ値
    protected byte[]    bV4GammaBlue;         // 青成分のガンマ値

    protected final int infoHeaderSize = 108; // 情報ヘッダサイズ

    @Override
    public void clear() {
        super.clear();
        setInfoHeaderSize(infoHeaderSize);
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
    public int getRedMask() {
        return Tools.bytes2int(bV4RedMask);
    }

    /**
     * @param bV4RedMask
     *            赤成分のカラーマスク
     */
    public void setRedMask(int bV4RedMask) {
        this.bV4RedMask = Tools.int2bytes(bV4RedMask);
    }

    /**
     * @return 緑成分のカラーマスク
     */
    public int getGreenMask() {
        return Tools.bytes2int(bV4GreenMask);
    }

    /**
     * @param bV4GreenMask
     *            緑成分のカラーマスク
     */
    public void setGreenMask(int bV4GreenMask) {
        this.bV4GreenMask = Tools.int2bytes(bV4GreenMask);
    }

    /**
     * @return 青成分のカラーマスク
     */
    public int getBlueMask() {
        return Tools.bytes2int(bV4BlueMask);
    }

    /**
     * @param bV4BlueMask
     *            青成分のカラーマスク
     */
    public void setBlueMask(int bV4BlueMask) {
        this.bV4BlueMask = Tools.int2bytes(bV4BlueMask);
    }

    /**
     * @return α成分のカラーマスク
     */
    public int getAlphaMask() {
        return Tools.bytes2int(bV4AlphaMask);
    }

    /**
     * @param bV4AlphaMask
     *            α成分のカラーマスク
     */
    public void setAlphaMask(int bV4AlphaMask) {
        this.bV4AlphaMask = Tools.int2bytes(bV4AlphaMask);
    }

    /**
     * <ul>
     * 色空間
     * <li>0 - ヘッダ内で定義</li>
     * </ul>
     * 
     * @return 色空間
     */
    public int getCSType() {
        return Tools.bytes2int(bV4CSType);
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
    public void setCSType(int bV4CSType) {
        this.bV4CSType = Tools.int2bytes(bV4CSType);
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
        ByteBuffer buff = ByteBuffer.allocate(FILE_HEADER_SIZE + infoHeaderSize);
        buff.put(super.getBitmapHeader());
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
}
