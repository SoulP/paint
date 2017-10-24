package file.format.bmp;

import java.nio.ByteBuffer;

import file.Tools;
import file.io.BMP;

/**
 * <b>BMP Windows V5</b><br>
 * date: 2017/10/19 last_date: 2017/10/24<br>
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
 * <td>124</td>
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
 * <td>0 -ヘッダ内で定義<br>
 * 0x73524742 - sRGB<br>
 * 0x57696E20 - Win <br>
 * 0x4C494E4B - LINK<br>
 * 0x4D424544 - MBED</td>
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
 * <tr>
 * <td>0x007A</td>
 * <td>4 バイト</td>
 * <td>レンダリングの意図</td>
 * <td>1, 2, 4, 8</td>
 * <td>bV5Intent</td>
 * </tr>
 * <tr>
 * <td>0x007E</td>
 * <td>4 バイト</td>
 * <td>プロファイルデータのオフセット</td>
 * <td>情報ヘッダの先頭アドレスからプロファイルデータの先頭アドレスまでのオフセット（単位はバイト）</td>
 * <td>bV5ProfileData</td>
 * </tr>
 * <tr>
 * <td>0x0082</td>
 * <td>4 バイト</td>
 * <td>プロファイルデータのサイズ</td>
 * <td>単位はバイト</td>
 * <td>bV5ProfileSize</td>
 * </tr>
 * <td>0x0086</td>
 * <td>4 バイト</td>
 * <td>予約領域</td>
 * <td>常に0</td>
 * <td>BV5_RESERVED</td>
 * </tr>
 * </table>
 * 
 * @author ソウルP
 * @version 1.0 2017/10/19 BMP_V5作成
 */
public class BMP_V5 extends BMP_V4 {
    // 情報ヘッダ
    protected byte[]              bV5Intent;                                 // レンダリングの意図
    protected byte[]              bV5ProfileData;                            // プロファイルデータのオフセット
    protected byte[]              bV5ProfileSize;                            // プロファイルデータのサイズ
    protected static final byte[] BV5_RESERVED = { 0x00, 0x00, 0x00, 0x00 }; // 予約領域

    /**
     * <b>BMP - Windows V5</b>
     */
    public BMP_V5() {
        clear();
    }

    /**
     * <b>BMP - Windows V5</b>
     * 
     * @param data
     *            データ
     */
    public BMP_V5(byte[] data) {
        clear();
        set(data);
    }

    /**
     * <b>BMP - Windows V5</b>
     * 
     * @param bmp
     *            BMPのオブジェクト
     */
    public BMP_V5(BMP bmp) {
        clear();
        set(bmp);
    }

    @Override
    public void clear() {
        super.clear();
        setInfoHeaderSize(INFO_HEADER_SIZE_V5);
        bV5Intent = Tools.int2bytes(0);
        bV5ProfileData = Tools.int2bytes(0);
        bV5ProfileSize = Tools.int2bytes(0);
    }

    /**
     * @return レンダリングの意図
     */
    public int getIntent() {
        return Tools.bytes2int(bV5Intent);
    }

    /**
     * @param bV5Intent
     *            レンダリングの意図
     */
    public void setIntent(int bV5Intent) {
        this.bV5Intent = Tools.int2bytes(bV5Intent);
    }

    /**
     * 単位はバイト
     * 
     * @return プロファイルデータのオフセット
     */
    public int getProfileData() {
        return Tools.bytes2int(bV5ProfileData);
    }

    /**
     * 単位はバイト
     * 
     * @param bV5ProfileData
     *            プロファイルデータのオフセット
     */
    public void setProfileData(int bV5ProfileData) {
        this.bV5ProfileData = Tools.int2bytes(bV5ProfileData);
    }

    /**
     * 単位はバイト
     * 
     * @return プロファイルデータのサイズ
     */
    public int getProfileSize() {
        return Tools.bytes2int(bV5ProfileSize);
    }

    /**
     * 単位はバイト
     * 
     * @param bV5ProfileSize
     *            プロファイルデータのサイズ
     */
    public void setProfileSize(int bV5ProfileSize) {
        this.bV5ProfileSize = Tools.int2bytes(bV5ProfileSize);
    }

    @Override
    public byte[] getBitmapHeader() {
        ByteBuffer buff = ByteBuffer.allocate(FILE_HEADER_SIZE + INFO_HEADER_SIZE_V5);
        buff.put(super.getBitmapHeader());
        buff.put(bV5Intent);
        buff.put(bV5ProfileData);
        buff.put(bV5ProfileSize);
        buff.put(BV5_RESERVED);
        return buff.array();
    }

    @Override
    public void set(byte[] data) {
        byte[] fileHeader = Tools.subbytes(data, 0, FILE_HEADER_SIZE);
        byte[] infoHeader = Tools.subbytes(data, FILE_HEADER_SIZE, FILE_HEADER_SIZE + INFO_HEADER_SIZE_V5);
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
        setIntent(bmp.getIntent());
        setProfileData(bmp.getProfileOffset());
        setProfileSize(bmp.getProfileSize());
    }

    @Override
    public int setInfoHeader(byte[] data) {
        int offset = super.setInfoHeader(data);
        bV5Intent = Tools.subbytes(data, offset, offset += 4);
        bV5ProfileData = Tools.subbytes(data, offset, offset += 4);
        bV5ProfileSize = Tools.subbytes(data, offset, offset += 4);
        offset += 4;
        return offset;
    }

    @Override
    public byte[] get() {
        int imageSize = 0;
        for (byte[] b : image)
            imageSize += b.length;
        setSizeImage(imageSize);
        ByteBuffer buff = ByteBuffer.allocate(FILE_HEADER_SIZE + INFO_HEADER_SIZE_V5 + colors.size() * 4 + imageSize);
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
        ByteBuffer buff = ByteBuffer.allocate(INFO_HEADER_SIZE_V5);
        buff.put(super.getInfoHeader());
        buff.put(bV5Intent);
        buff.put(bV5ProfileData);
        buff.put(bV5ProfileSize);
        buff.put(BV5_RESERVED);
        return buff.array();
    }

    @Override
    public int getVersion() {
        return 5;
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
        buff.append(STR_INTENT);
        buff.append(getIntent());
        buff.append(STR_NEW_LINE);

        buff.append(STR_PROFILE_DATA_OFFSET);
        buff.append(Integer.toHexString(getProfileData()).toUpperCase());
        buff.append(STR_NEW_LINE);

        buff.append(STR_PROFILE_DATA_SIZE);
        buff.append(getProfileSize());
        buff.append(STR_BYTE);
        buff.append(STR_NEW_LINE);

        return buff.toString();
    }

    @Override
    protected void updateFileSize() {
        int fileSize = FILE_HEADER_SIZE + INFO_HEADER_SIZE_V5 + getSizeImage() + colors.size() * 4;
        bfSize = Tools.int2bytes(fileSize);
    }
}