package file.format.bmp;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

import file.Tools;
import file.io.BMP;

/**
 * <b>BMP Windows V5</b><br>
 * date: 2017/10/19 last_date: 2018/03/27<br>
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
 * <dd>0x73524742 - sRGB</dd>
 * <dd>0x57696E20 - Win</dd>
 * <dd>0x4C494E4B - LINK</dd>
 * <dd>0x4D424544 - MBED</dd>
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
 * 
 * <dt>bV5Intent</dt>
 * <dd>0x007A</dd>
 * <dd>4 バイト</dd>
 * <dd>レンダリングの意図</dd>
 * <dd>1, 2, 4, 8</dd>
 * 
 * <dt>bV5ProfileData</dt>
 * <dd>0x007E</dd>
 * <dd>4 バイト</dd>
 * <dd>プロファイルデータのオフセット</dd>
 * <dd>情報ヘッダの先頭アドレスからプロファイルデータの先頭アドレスまでのオフセット（単位はバイト）</dd>
 * 
 * <dt>bV5ProfileSize</dt>
 * <dd>0x0082</dd>
 * <dd>4 バイト</dd>
 * <dd>プロファイルデータのサイズ</dd>
 * <dd>単位はバイト</dd>
 * 
 * <dt>BV5_RESERVED</dt>
 * <dd>0x0086</dd>
 * <dd>4 バイト</dd>
 * <dd>予約領域</dd>
 * <dd>常に0</dd>
 * </dl>
 * 
 * @author ソウルP
 * @version 1.0 2017/10/19 BMP_V5作成
 * @version 1.1 2017/10/25 ビットフィールド追加 BMP_V3 から継承
 * @version 1.2 2017/10/27 GAP1 と GAP2 （オプショナル）追加 BMP_V3 から継承
 * @version 1.3 2017/10/27 プロファイル追加
 */
public class BMP_V5 extends BMP_V4 {
    // 情報ヘッダ
    protected byte[]              bV5Intent;                                 // レンダリングの意図
    protected byte[]              bV5ProfileData;                            // プロファイルデータのオフセット
    protected byte[]              bV5ProfileSize;                            // プロファイルデータのサイズ
    protected static final byte[] BV5_RESERVED = { 0x00, 0x00, 0x00, 0x00 }; // 予約領域
    protected byte[]              profile;                                   // プロファイル

    /**
     * <b>BMP - Windows V5</b>
     */
    public BMP_V5() {
        colors = new ArrayList<>();
        image = new ArrayList<>();
        clear();
    }

    /**
     * <b>BMP - Windows V5</b>
     * 
     * @param data
     *            データ
     */
    public BMP_V5(byte[] data) {
        this();
        set(data);
    }

    /**
     * <b>BMP - Windows V5</b>
     * 
     * @param bmp
     *            BMPのオブジェクト
     */
    public BMP_V5(BMP bmp) {
        this();
        set(bmp);
    }

    @Override
    public void clear() {
        super.clear();
        setInfoHeaderSize(INFO_HEADER_SIZE_V5);
        bV5Intent = Tools.int2bytes(0);
        bV5ProfileData = Tools.int2bytes(0);
        bV5ProfileSize = Tools.int2bytes(0);
        profile = new byte[0];
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

    /**
     * @return プロファイルデータ
     */
    public byte[] getProfile() {
        return profile;
    }

    /**
     * @param profile
     *            プロファイルデータ
     */
    public void setProfile(byte[] profile) {
        this.profile = profile;
        setProfileSize(profile.length);
    }

    /**
     * @return 空の場合 true<br>
     *         空ではない場合 false
     */
    public boolean isEmptyProfile() {
        return profile == null || profile.length == 0;
    }

    @Override
    public byte[] getBitmapHeader() {
        ByteBuffer buff = ByteBuffer.allocate(FILE_HEADER_SIZE + INFO_HEADER_SIZE_V5);
        buff.put(getFileHeader());
        buff.put(getInfoHeader());

        return buff.array();
    }

    @Override
    public void set(byte[] data) {
        int endHeaderOffset = FILE_HEADER_SIZE + INFO_HEADER_SIZE_V5;
        byte[] fileHeader = Arrays.copyOfRange(data, 0, FILE_HEADER_SIZE);
        byte[] infoHeader = Arrays.copyOfRange(data, FILE_HEADER_SIZE, endHeaderOffset);
        setFileHeader(fileHeader);
        setInfoHeader(infoHeader);
        int bitcount = getBitCount();
        int compression = getCompression();
        gap1 = null;
        if (bitcount <= 8) {
            int endColorOffset = endHeaderOffset + getClrUsed() * 4;
            byte[] bColors = Arrays.copyOfRange(data, endHeaderOffset, endColorOffset);
            setColors(bColors);
            if (endColorOffset < getOffset()) {
                gap1 = Arrays.copyOfRange(data, endColorOffset, getOffset());
            }
        } else {
            clearColors();
            if ((bitcount == 16 || bitcount == 32)) {
                if (compression == 3) {
                    int endBitFieldsOffset = endHeaderOffset + 12;
                    biBitFields = Arrays.copyOfRange(data, endHeaderOffset, endBitFieldsOffset);
                    if (endBitFieldsOffset < getOffset()) {
                        gap1 = Arrays.copyOfRange(data, endBitFieldsOffset, getOffset());
                    }
                } else if (compression == 6) {
                    int endBitFieldsOffset = endHeaderOffset + 16;
                    biBitFields = Arrays.copyOfRange(data, endHeaderOffset, endBitFieldsOffset);
                    if (endBitFieldsOffset < getOffset()) {
                        gap1 = Arrays.copyOfRange(data, endBitFieldsOffset, getOffset());
                    }
                } else {
                    biBitFields = null;
                    if (endHeaderOffset < getOffset()) {
                        gap1 = Arrays.copyOfRange(data, endHeaderOffset, getOffset());
                    }
                }
            } else {
                biBitFields = null;
                if (endHeaderOffset < getOffset()) {
                    gap1 = Arrays.copyOfRange(data, endHeaderOffset, getOffset());
                }
            }
        }
        int imageSize = getSizeImage();
        byte[] imgData;
        int profileOffset = FILE_HEADER_SIZE + getProfileData();
        gap2 = null;
        if (imageSize == 0) {
            imgData = Arrays.copyOfRange(data, getOffset(), profileOffset);
        } else {
            int endImageOffset = getOffset() + imageSize;
            imgData = Arrays.copyOfRange(data, getOffset(), endImageOffset);
            if (endImageOffset < profileOffset) {
                gap2 = Arrays.copyOfRange(data, endImageOffset, profileOffset);
            }
        }
        setImage(imgData);
        int profileSize = getProfileSize();
        if (profileSize > 0) {
            profile = Arrays.copyOfRange(data, profileOffset, profileOffset + getProfileSize());
        } else {
            profile = null;
        }
    }

    @Override
    public void set(BMP bmp) {
        super.set(bmp);
        setIntent(bmp.getIntent());
        setProfileData(bmp.getProfileOffset());
        setProfileSize(bmp.getProfileSize());
        if (!bmp.isEmptyProfile()) setProfile(bmp.getProfile());
        if (!bmp.isEmptyGap2()) setGap2(bmp.getGap2());
    }

    @Override
    public int setInfoHeader(byte[] data) {
        int offset = super.setInfoHeader(data);
        bV5Intent = Arrays.copyOfRange(data, offset, offset += 4);
        bV5ProfileData = Arrays.copyOfRange(data, offset, offset += 4);
        bV5ProfileSize = Arrays.copyOfRange(data, offset, offset += 4);
        offset += 4;
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
        if (!isEmptyGap2() && imageSize != 0) optSize += gap2.length;
        ByteBuffer buff = ByteBuffer
                .allocate(FILE_HEADER_SIZE + INFO_HEADER_SIZE_V5 + optSize + imageSize + getProfileSize());
        buff.put(getBitmapHeader());
        if (bitcount <= 8) {
            colors.forEach(color -> {
                buff.put(color);
            });
        }

        if (!isEmptyGap1()) buff.put(gap1);

        image.forEach(img -> {
            buff.put(img);
        });

        if (!isEmptyGap2()) buff.put(gap2);

        if (!isEmptyProfile()) buff.put(profile);

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
        buff.append(STR_NEW_LINE);
        buff.append(STR_NEW_LINE);
        buff.append(STR_PROFILE);
        if (!isEmptyProfile()) {
            buff.append(STR_NEW_LINE);
            for (int i = 0; i < profile.length;) {
                buff.append(String.format(STR_16BIT_FORMAT, profile[i]));
                if (++i % 8 == 0) buff.append(STR_NEW_LINE);
            }
        }
        buff.append(STR_NEW_LINE);
        buff.append(STR_NEW_LINE);
        buff.append(STR_GAP1);
        if (!isEmptyGap1()) {
            buff.append(STR_NEW_LINE);
            for (int i = 0; i < gap1.length;) {
                buff.append(String.format(STR_16BIT_FORMAT, gap1[i]));
                if (++i % 8 == 0) buff.append(STR_NEW_LINE);
            }
        }
        buff.append(STR_NEW_LINE);
        buff.append(STR_NEW_LINE);
        buff.append(STR_GAP2);
        if (!isEmptyGap2()) {
            buff.append(STR_NEW_LINE);
            for (int i = 0; i < gap2.length;) {
                buff.append(String.format(STR_16BIT_FORMAT, gap2[i]));
                if (++i % 8 == 0) buff.append(STR_NEW_LINE);
            }
        }
        buff.append(STR_NEW_LINE);

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
}
