package file.format.bmp;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import file.Tools;
import file.io.BMP;

/**
 * <b>BMP - OS/2 V1</b><br>
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
 * <dd>12</dd>
 * 
 * <dt>bcWidth</dt>
 * <dd>0x0012</dd>
 * <dd>2 バイト</dd>
 * <dd>ビットマップの横幅</dd>
 * <dd>単位はピクセル</dd>
 * 
 * <dt>bcHeight</dt>
 * <dd>0x0014</dd>
 * <dd>2 バイト</dd>
 * <dd>ビットマップの縦幅</dd>
 * <dd>単位はピクセル</dd>
 * 
 * <dt>BC_PLANES</dt>
 * <dd>0x0016</dd>
 * <dd>2 バイト</dd>
 * <dd>プレーン数</dd>
 * <dd>常に1</dd>
 * 
 * <dt>bcBitCount</dt>
 * <dd>0x0018</dd>
 * <dd>2 バイト</dd>
 * <dd>1ピクセルあたりのビット数</dd>
 * <dd>1, 4, 8, 24</dd>
 * </dl>
 * 
 * @author ソウルP
 * @version 1.0 2017/10/18 BMP_V1作成
 */
public class BMP_V1 implements BMPable {
    // ファイルヘッダ
    protected byte[]              bfType;                         // ファイルタイプ 通常は'BM'
    protected byte[]              bfSize;                         // ファイルサイズ (byte)
    protected static final byte[] BF_RESERVED_1 = { 0x00, 0x00 }; // 予約領域 常に 0
    protected static final byte[] BF_RESERVED_2 = { 0x00, 0x00 }; // 予約領域 常に 0
    protected byte[]              bfOffBits;                      // ファイル先頭から画像データまでのオフセット (byte)

    // 情報ヘッダ
    protected byte[]              bcSize;                         // ヘッダサイズ (byte)
    protected byte[]              bcWidth;                        // ビットマップの横幅 (ピクセル)
    protected byte[]              bcHeight;                       // ビットマップの縦幅 (ピクセル)
    protected static final byte[] BC_PLANES     = { 0x01, 0x00 }; // プレーン数 常に 1
    protected byte[]              bcBitCount;                     // 1ピクセルあたりのビット数 (bit)

    protected List<byte[]>        colors;                         // カラーパレット
    protected List<byte[]>        image;                          // イメージ

    /**
     * <b>BMP - OS/2 V1</b>
     */
    public BMP_V1() {
        colors = new ArrayList<>();
        image = new ArrayList<>();
        clear();
    }

    /**
     * <b>BMP - OS/2 V1</b>
     * 
     * @param bmp
     *            BMPのデータ
     */
    public BMP_V1(byte[] data) {
        this();
        set(data);
    }

    /**
     * <b>BMP - OS/2 V1</b>
     * 
     * @param data
     *            BMPのオブジェクト
     */
    public BMP_V1(BMP bmp) {
        this();
        set(bmp);
    }

    @Override
    public void clear() {
        setType(new byte[] { 0x42, 0x4D });
        setFileSize(0);
        setOffset(0);
        setInfoHeaderSize(INFO_HEADER_SIZE_V1);
        setWidth((short) 0);
        setHeight((short) 0);
        setBitCount((short) 0);
    }

    /**
     * @return ファイルタイプ
     */
    public byte[] getType() {
        return bfType;
    }

    /**
     * @param bfType
     *            ファイルタイプ
     */
    public void setType(byte[] bfType) {
        this.bfType = bfType;
    }

    /**
     * 単位はバイト
     * 
     * @return ファイルサイズ
     */
    public int getFileSize() {
        return Tools.bytes2int(bfSize);
    }

    /**
     * 単位はバイト
     * 
     * @param bfSize
     *            ファイルサイズ
     */
    public void setFileSize(int bfSize) {
        this.bfSize = Tools.int2bytes(bfSize);
    }

    /**
     * 単位はバイト
     * 
     * @return オフセット
     */
    public int getOffset() {
        return Tools.bytes2int(bfOffBits);
    }

    /**
     * 単位はバイト
     * 
     * @param bfOffBits
     *            オフセット
     */
    public void setOffset(int bfOffBits) {
        this.bfOffBits = Tools.int2bytes(bfOffBits);
    }

    /**
     * 単位はバイト
     * 
     * @return ファイルヘッダサイズ
     */
    public int getFileHeaderSize() {
        return FILE_HEADER_SIZE;
    }

    /**
     * 単位はバイト
     * 
     * @return 情報ヘッダサイズ
     */
    public int getInfoHeaderSize() {
        return Tools.bytes2int(bcSize);
    }

    /**
     * 単位はバイト
     * 
     * @param bcSize
     *            情報ヘッダサイズ
     */
    public void setInfoHeaderSize(int bcSize) {
        this.bcSize = Tools.int2bytes(bcSize);
    }

    /**
     * 単位はピクセル
     * 
     * @return ビットマップの横幅
     */
    public short getWidthV1() {
        return Tools.bytes2short(bcWidth);
    }

    /**
     * 単位はピクセル
     * 
     * @param bcWidth
     *            ビットマップの横幅
     */
    public void setWidth(short bcWidth) {
        this.bcWidth = Tools.short2bytes(bcWidth);
    }

    /**
     * 単位はピクセル
     * 
     * @return ビットマップの縦幅
     */
    public short getHeightV1() {
        return Tools.bytes2short(bcHeight);
    }

    /**
     * 単位はピクセル
     * 
     * @param bcHeight
     *            ビットマップの縦幅
     */
    public void setHeight(short bcHeight) {
        this.bcHeight = Tools.short2bytes(bcHeight);
    }

    /**
     * @return プレーン数
     */
    public short getPlanes() {
        return Tools.bytes2short(BC_PLANES);
    }

    /**
     * 単位はビット
     * 
     * @return 1ピクセルあたりのビット数
     */
    public short getBitCount() {
        return Tools.bytes2short(bcBitCount);
    }

    /**
     * 単位はビット
     * 
     * @param bcBitCount
     *            1ピクセルあたりのビット数
     */
    public void setBitCount(short bcBitCount) {
        this.bcBitCount = Tools.short2bytes(bcBitCount);
    }

    /**
     * @param colors
     *            カラーパレット
     */
    public void setColors(List<byte[]> colors) {
        this.colors = colors;
    }

    /**
     * @return カラーパレット
     */
    public List<byte[]> getColors() {
        return colors;
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
        byte[] color = { (byte) b, (byte) g, (byte) r };
        colors.add(color);
    }

    /**
     * カラーパレット 全消去
     */
    public void clearColors() {
        colors.clear();
    }

    /**
     * Listが縦、byte[]が横
     * 
     * @return ビットマップデータ
     */
    public List<byte[]> getImage() {
        return image;
    }

    /**
     * Listが縦、byte[]が横
     * 
     * @param image
     *            ビットマップデータ
     */
    public void setImage(List<byte[]> image) {
        this.image = image;
    }

    @Override
    public byte[] getBitmapHeader() {
        ByteBuffer buff = ByteBuffer.allocate(FILE_HEADER_SIZE + INFO_HEADER_SIZE_V1);
        buff.put(getFileHeader());
        buff.put(getInfoHeader());
        return buff.array();
    }

    @Override
    public void set(byte[] data) {
        int endHeaderOffset = FILE_HEADER_SIZE + INFO_HEADER_SIZE_V1;
        byte[] fileHeader = Arrays.copyOfRange(data, 0, FILE_HEADER_SIZE);
        byte[] infoHeader = Arrays.copyOfRange(data, FILE_HEADER_SIZE, endHeaderOffset);
        setFileHeader(fileHeader);
        setInfoHeader(infoHeader);
        int bitCount = getBitCount();
        if (bitCount <= 8) {
            byte[] bColors = Arrays.copyOfRange(data, endHeaderOffset, getOffset());
            setColors(bColors);
        }
        byte[] imgData = Arrays.copyOfRange(data, getOffset(), getFileSize());
        setImage(imgData);
    }

    @Override
    public void set(BMP bmp) {
        setType(bmp.getType());
        setFileSize(bmp.getFileSize());
        setOffset(bmp.getImageOffset());
        setWidth((short) bmp.getWidth());
        setHeight((short) bmp.getHeight());
        setBitCount((short) bmp.getBitCount());
        bmp.getColors().forEach(color -> {
            addColor(color[0], color[1], color[2]);
        });
        setImage(bmp.getImage());
    }

    @Override
    public int setFileHeader(byte[] data) {
        int offset = 0;
        bfType = Arrays.copyOfRange(data, offset, offset += 2);
        bfSize = Arrays.copyOfRange(data, offset, offset += 4);
        bfOffBits = Arrays.copyOfRange(data, offset += 4, offset + 4);
        return offset;
    }

    @Override
    public int setInfoHeader(byte[] data) {
        int offset = 4;
        bcWidth = Arrays.copyOfRange(data, offset, offset += 2);
        bcHeight = Arrays.copyOfRange(data, offset, offset += 2);
        bcBitCount = Arrays.copyOfRange(data, offset += 2, offset += 2);
        return offset;
    }

    @Override
    public void setColors(byte[] data) {
        for (int i = 0; i < data.length; i += 3) {
            int r = Byte.toUnsignedInt(data[i + 2]);
            int g = Byte.toUnsignedInt(data[i + 1]);
            int b = Byte.toUnsignedInt(data[i]);
            addColor(r, g, b);
        }
    }

    @Override
    public void setImage(byte[] data) {
        int tempW = 0;
        int bitCount = getBitCount();
        short width = getWidthV1();
        if (bitCount == 1) {
            tempW = (width % 32 == 0) ? width : (32 - width % 32 + width) / 8;
        }
        if (bitCount == 4) {
            tempW = (width % 8 == 0) ? width : (8 - width % 8 + width) / 2;
        }
        if (bitCount == 8 || bitCount == 16) {
            tempW = (width % 4 == 0) ? width : (4 - width % 4 + width);
        }
        if (bitCount == 24) {
            tempW = (width * 3 % 4 == 0) ? width : 4 - width * 3 % 4 + width * 3;
        }
        if (bitCount == 32) {
            tempW = width * 4;
        }
        for (int i = 0; i < data.length; i += tempW) {
            byte[] img = new byte[tempW];
            for (int w = 0; w < tempW; w++) {
                img[w] = data[i + w];
            }
            image.add(img);
        }
    }

    @Override
    public byte[] get() {
        int imageSize = 0;
        for (byte[] b : image) {
            imageSize += b.length;
        }
        ByteBuffer buff = ByteBuffer.allocate(FILE_HEADER_SIZE + INFO_HEADER_SIZE_V1 + colors.size() * 3 + imageSize);
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
    public byte[] getFileHeader() {
        ByteBuffer buff = ByteBuffer.allocate(FILE_HEADER_SIZE);
        buff.put(bfType);
        buff.put(bfSize);
        buff.put(BF_RESERVED_1);
        buff.put(BF_RESERVED_2);
        buff.put(bfOffBits);
        return buff.array();
    }

    @Override
    public byte[] getInfoHeader() {
        ByteBuffer buff = ByteBuffer.allocate(INFO_HEADER_SIZE_V1);
        buff.put(bcSize);
        buff.put(bcWidth);
        buff.put(bcHeight);
        buff.put(BC_PLANES);
        buff.put(bcBitCount);
        return buff.array();
    }

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public String toString() {
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
        buff.append(getWidthV1());
        buff.append(STR_PIXEL);
        buff.append(STR_NEW_LINE);

        buff.append(STR_HEIGHT);
        buff.append(getHeightV1());
        buff.append(STR_PIXEL);
        buff.append(STR_NEW_LINE);

        buff.append(STR_PLANES);
        buff.append(getPlanes());
        buff.append(STR_NEW_LINE);

        buff.append(STR_BITCOUNT);
        buff.append(getBitCount());
        buff.append(STR_BIT);
        buff.append(STR_NEW_LINE);
        buff.append(STR_NEW_LINE);

        buff.append(STR_COLOR_PALLET);
        buff.append(STR_NEW_LINE);
        colors.forEach(color -> {
            buff.append(STR_RGB_R);
            buff.append(String.format(STR_COLOR_FORMAT, Byte.toUnsignedInt(color[2])));
            buff.append(STR_COMMA_SPACE);

            buff.append(STR_RGB_G);
            buff.append(String.format(STR_COLOR_FORMAT, Byte.toUnsignedInt(color[1])));
            buff.append(STR_COMMA_SPACE);

            buff.append(STR_RGB_B);
            buff.append(String.format(STR_COLOR_FORMAT, Byte.toUnsignedInt(color[0])));
            buff.append(STR_NEW_LINE);
        });
        buff.append(STR_NEW_LINE);

        buff.append(STR_IMAGE_DATA);
        buff.append(STR_NEW_LINE);
        image.forEach(bA -> {
            for (byte b : bA)
                buff.append(String.format(STR_16BIT_FORMAT, b));
            buff.append(STR_NEW_LINE);
        });

        return buff.toString();
    }

    protected String toStrColorImage() {
        StringBuffer buff = new StringBuffer();
        buff.append(STR_COLOR_PALLET);
        buff.append(STR_NEW_LINE);
        colors.forEach(color -> {
            buff.append(STR_RGB_R);
            buff.append(String.format(STR_COLOR_FORMAT, Byte.toUnsignedInt(color[2])));
            buff.append(STR_COMMA_SPACE);

            buff.append(STR_RGB_G);
            buff.append(String.format(STR_COLOR_FORMAT, Byte.toUnsignedInt(color[1])));
            buff.append(STR_COMMA_SPACE);

            buff.append(STR_RGB_B);
            buff.append(String.format(STR_COLOR_FORMAT, Byte.toUnsignedInt(color[0])));
            buff.append(STR_NEW_LINE);
        });
        buff.append(STR_NEW_LINE);

        buff.append(STR_IMAGE_DATA);
        buff.append(STR_NEW_LINE);
        image.forEach(bA -> {
            for (byte b : bA)
                buff.append(String.format(STR_16BIT_FORMAT, b));
            buff.append(STR_NEW_LINE);
        });

        return buff.toString();
    }
}
