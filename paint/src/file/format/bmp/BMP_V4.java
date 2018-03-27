package file.format.bmp;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

import file.Tools;
import file.io.BMP;

/**
 * <b>BMP Windows V4</b><br>
 * date: 2017/10/18 last_date: 2018/03/27<br>
 * <dl>
 * <h1>ファイルヘッダ</h1>
 * 
 * <dt>変数</dt>
 * <dd>オフセット</dd>
 * <dd>サイズ</dd>
 * <dd>格納する情報</dd>
 * <dd>値・備考</dd>
 * 
 * <dt>bfType</dt>
 * <dd>0x0000</dd>
 * <dd>2 バイト</dd>
 * <dd>ファイルタイプ</dd>
 * <dd>常にBM (0x42, 0x4d)</dd>
 * 
 * <dt>bfSize</dt>
 * <dd>0x0002</dd>
 * <dd>4 バイト</dd>
 * <dd>ファイルサイズ</dd>
 * <dd>ビットマップファイルのサイズを格納する（単位はバイト）</dd>
 * 
 * <dt>BF_RESERVED_1</dt>
 * <dd>0x0006</dd>
 * <dd>2 バイト</dd>
 * <dd>予約領域1</dd>
 * <dd>常に0</dd>
 * 
 * <dt>BF_RESERVED_2</dt>
 * <dd>0x0008</dd>
 * <dd>2 バイト</dd>
 * <dd>予約領域2</dd>
 * <dd>常に0</dd>
 * 
 * <dt>bfOffBits</dt>
 * <dd>0x000A</dd>
 * <dd>4 バイト</dd>
 * <dd>オフセット</dd>
 * <dd>ファイルヘッダの先頭アドレスからビットマップデータの先頭アドレスまでのオフセット（単位はバイト）</dd>
 * </dl>
 * <dl>
 * <h1>情報ヘッダ</h1>
 * 
 * <dt>変数</dt>
 * <dd>オフセット</dd>
 * <dd>サイズ</dd>
 * <dd>格納する情報</dd>
 * <dd>値・備考</dd>
 * 
 * <dt>bcSize</dt>
 * <dd>0x000E</dd>
 * <dd>4 バイト</dd>
 * <dd>ヘッダサイズ</dd>
 * <dd>40</dd>
 * 
 * <dt>bcWidth</dt>
 * <dd>0x0012</dd>
 * <dd>4 バイト</dd>
 * <dd>ビットマップの横幅</dd>
 * <dd>単位はピクセル</dd>
 * 
 * <dt>bcHeight</dt>
 * <dd>0x0016</dd>
 * <dd>4 バイト</dd>
 * <dd>ビットマップの縦幅</dd>
 * <dd>単位はピクセル</dd>
 * 
 * <dt>BC_PLANES</dt>
 * <dd>0x001A</dd>
 * <dd>2 バイト</dd>
 * <dd>プレーン数</dd>
 * <dd>常に1</dd>
 * 
 * <dt>bcBitCount</dt>
 * <dd>0x001C</dd>
 * <dd>2 バイト</dd>
 * <dd>1ピクセルあたりのビット数</dd>
 * <dd>0, 1, 4, 8, 16, 24, 32</dd>
 * 
 * <dt>biCompression</dt>
 * <dd>0x001E</dd>
 * <dd>4 バイト</dd>
 * <dd>圧縮形式</dd>
 * <dd>0 - BI_RGB （非圧縮）</dd>
 * <dd>1 - BI_RLE8 （8ビット/ピクセル）</dd>
 * <dd>2 - BI_RLE4 （4ビット/ピクセル）</dd>
 * <dd>3 - BI_BITFIELDS （ビットフィールド）</dd>
 * <dd>4 - BI_JPEG</dd>
 * <dd>5 - BI_PNG</dd>
 * 
 * <dt>biSizeImage</dt>
 * <dd>0x0022</dd>
 * <dd>4 バイト</dd>
 * <dd>画像データサイズ</dd>
 * <dd>単位はバイト</dd>
 * 
 * <dt>biXPelsPerMeter</dt>
 * <dd>0x0026</dd>
 * <dd>4 バイト</dd>
 * <dd>水平方向の解像度</dd>
 * <dd>単位はピクセル/m</dd>
 * 
 * <dt>biYPelsPerMeter</dt>
 * <dd>0x002A</dd>
 * <dd>4 バイト</dd>
 * <dd>垂直方向の解像度</dd>
 * <dd>単位はピクセル/m</dd>
 * 
 * <dt>biClrUsed</dt>
 * <dd>0x002E</dd>
 * <dd>4 バイト</dd>
 * <dd>使用する色数</dd>
 * <dd>ビットマップで実際に使用するカラーパレット内のカラーインデックスの数</dd>
 * 
 * <dt>biCirImportant</dt>
 * <dd>0x0032</dd>
 * <dd>4 バイト</dd>
 * <dd>重要な色数</dd>
 * <dd>ビットマップを表示するために必要なカラーインデックスの数</dd>
 * 
 * <dt>bV4RedMask</dt>
 * <dd>0x0036</dd>
 * <dd>4 バイト</dd>
 * <dd>赤成分のカラーマスク</dd>
 * 
 * <dt>bV4GreenMask</dt>
 * <dd>0x003A</dd>
 * <dd>4 バイト</dd>
 * <dd>緑成分のカラーマスク</dd>
 * 
 * <dt>bV4BlueMask</dt>
 * <dd>0x003E</dd>
 * <dd>4 バイト</dd>
 * <dd>青成分のカラーマスク</dd>
 * 
 * <dt>bV4AlphaMask</dt>
 * <dd>0x0042</dd>
 * <dd>4 バイト</dd>
 * <dd>α成分のカラーマスク</dd>
 * 
 * <dt>bV4CSType</dt>
 * <dd>0x0046</dd>
 * <dd>4 バイト</dd>
 * <dd>色空間</dd>
 * <dd>0 -ヘッダ内で定義</dd>
 * 
 * <dt>bV4Endpoints</dt>
 * <dd>0x004A</dd>
 * <dd>36 バイト</dd>
 * <dd>CIEXYZTRIPLE構造体</dd>
 * <dd>色空間が0の場合のみ有効</dd>
 * 
 * <dt>bV4GammaRed</dt>
 * <dd>0x006E</dd>
 * <dd>4 バイト</dd>
 * <dd>赤成分のガンマ値</dd>
 * <dd>色空間が0の場合のみ有効</dd>
 * <dd>16.16の固定小数点数</dd>
 * 
 * <dt>bV4GammaGreen</dt>
 * <dd>0x0072</dd>
 * <dd>4 バイト</dd>
 * <dd>緑成分のガンマ値</dd>
 * 
 * <dt>bV4GammaBlue</dt>
 * <dd>0x0076</dd>
 * <dd>4 バイト</dd>
 * <dd>青成分のガンマ値</dd>
 * </dl>
 * 
 * @author ソウルP
 * @version 1.0 2017/10/18 BMP_V4作成
 * @version 1.1 2017/10/25 ビットフィールド追加 BMP_V3 から継承
 * @version 1.2 2017/10/27 GAP1 と GAP2 （オプショナル）追加 BMP_V3 から継承
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
        colors = new ArrayList<>();
        image = new ArrayList<>();
        clear();
    }

    /**
     * <b>BMP - Windows V4</b>
     * 
     * @param data
     *            データ
     */
    public BMP_V4(byte[] data) {
        this();
        set(data);
    }

    /**
     * <b>BMP - Windows V4</b>
     * 
     * @param bmp
     *            BMPのオブジェクト
     */
    public BMP_V4(BMP bmp) {
        this();
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
        bV4Endpoints = ByteBuffer.allocate(36).putLong(0).putLong(0).putLong(0).putLong(0).putInt(0).array();
        bV4GammaRed = Tools.float2bytes(0.0f);
        bV4GammaGreen = Tools.float2bytes(0.0f);
        bV4GammaBlue = Tools.float2bytes(0.0f);
    }

    /**
     * &nbsp;
     * 
     * @deprecated
     *             <ul>
     *             以下のメソッドを使ってください
     *             <li>{@link #getRedMask()}</li>
     *             <li>{@link #getGreenMask()}</li>
     *             <li>{@link #getBlueMask()}</li>
     *             <li>{@link #getAlphaMask()}</li>
     *             </ul>
     */
    @Deprecated
    @Override
    public byte[] getBitFields() {
        return null;
    }

    /**
     * &nbsp;
     * 
     * @deprecated
     *             <ul>
     *             以下のメソッドを使ってください
     *             <li>{@link #setRedMask(byte[])}</li>
     *             <li>{@link #setGreenMask(byte[])}</li>
     *             <li>{@link #setBlueMask(byte[])}</li>
     *             <li>{@link #setAlphaMask(byte[])}</li>
     *             </ul>
     */
    @Deprecated
    @Override
    public void setBitFields(byte[] biBitFields) {
    }

    /**
     * @deprecated
     */
    @Deprecated
    @Override
    public boolean isEmptyBitFields() {
        return true;
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
        int endHeaderOffset = FILE_HEADER_SIZE + INFO_HEADER_SIZE_V4;
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
                    gap1 = Arrays.copyOfRange(data, endHeaderOffset, getOffset());
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
        bV4RedMask = Arrays.copyOfRange(data, offset, offset += 4);
        bV4GreenMask = Arrays.copyOfRange(data, offset, offset += 4);
        bV4BlueMask = Arrays.copyOfRange(data, offset, offset += 4);
        bV4AlphaMask = Arrays.copyOfRange(data, offset, offset += 4);
        bV4CSType = Arrays.copyOfRange(data, offset, offset += 4);
        bV4Endpoints = Arrays.copyOfRange(data, offset, offset += 36);
        bV4GammaRed = Arrays.copyOfRange(data, offset, offset += 4);
        bV4GammaGreen = Arrays.copyOfRange(data, offset, offset += 4);
        bV4GammaBlue = Arrays.copyOfRange(data, offset, offset += 4);
        return offset;
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
        ByteBuffer buff = ByteBuffer.allocate(FILE_HEADER_SIZE + INFO_HEADER_SIZE_V4 + optSize + +imageSize);
        buff.put(getBitmapHeader());
        if (bitcount <= 8) {
            colors.forEach(color -> {
                buff.put(color);
            });
        } else if ((compression == 3 || compression == 6) && (bitcount == 16 || bitcount == 32)) {
            if (biBitFields != null) buff.put(biBitFields);
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
        if (gap1 != null) {
            for (int i = 0; i < gap1.length;) {
                buff.append(String.format(STR_16BIT_FORMAT, gap1[i]));
                if (++i % 8 == 0) buff.append(STR_NEW_LINE);
            }
        }

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
}
