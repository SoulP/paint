package file.format.bmp;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import file.Tools;
import file.io.BMP;

/**
 * <b>BMP - OS/2 V2</b><br>
 * date: 2017/10/18 last_date: 2018/03/28<br>
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
 * <dd>BM (0x42, 0x4d)（ビットマップ）</dd>
 * <dd>IC (0x49, 0x43)（モノクロアイコン）</dd>
 * <dd>CI (0x43, 0x49)（カラーアイコン）</dd>
 * <dd>PT (0x50, 0x54)（モノクロポインタ）</dd>
 * <dd>CP (0x43, 0x50)（カラーポインタ）</dd>
 * 
 * <dt>bV2HeaderSize</dt>
 * <dd>0x0002</dd>
 * <dd>4 バイト</dd>
 * <dd>ヘッダサイズ</dd>
 * <dd>ファイルヘッダと情報ヘッダの合計サイズを格納する（単位はバイト）</dd>
 * 
 * <dt>bV2HotspotX</dt>
 * <dd>0x0006</dd>
 * <dd>2 バイト</dd>
 * <dd>ホットスポット x</dd>
 * <dd>ポインタのホットスポットの x 座標</dd>
 * 
 * <dt>bV2HotspotY</dt>
 * <dd>0x0008</dd>
 * <dd>2 バイト</dd>
 * <dd>ホットスポット y</dd>
 * <dd>ポインタのホットスポットの y 座標</dd>
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
 * <dd>16～64（可変長）</dd>
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
 * <dd>１ピクセルあたりビット数</dd>
 * <dd>1, 4, 8, 24</dd>
 * 
 * <dt>biCompression</dt>
 * <dd>0x001E</dd>
 * <dd>4 バイト</dd>
 * <dd>圧縮形式</dd>
 * <dd>0 - RGB （非圧縮）</dd>
 * <dd>1 - RLE8 （8ビット/ピクセル）</dd>
 * <dd>2 - RLE4 （4ビット/ピクセル）</dd>
 * <dd>3 - HUFFMAN_1D （1ビットハフマン符号化）</dd>
 * <dd>4 - RLE_24 （24ビット/ピクセル）</dd>
 * 
 * <dt>biSizeImage</dt>
 * <dd>0x0022</dd>
 * <dd>4 バイト</dd>
 * <dd>画像データサイズ</dd>
 * <dd>単位はバイト。非圧縮の場合は0を入れても良い</dd>
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
 * <dt>bV2Resolution</dt>
 * <dd>0x0036</dd>
 * <dd>2 バイト</dd>
 * <dd>解像度の単位</dd>
 * <dd>0 - ピクセル/m</dd>
 * 
 * <dt>BV2_RESERVED</dt>
 * <dd>0x0038</dd>
 * <dd>2 バイト</dd>
 * <dd>予約領域</dd>
 * <dd>常に0</dd>
 * 
 * <dt>bV2Format</dt>
 * <dd>0x003A</dd>
 * <dd>2 バイト</dd>
 * <dd>記録方式</dd>
 * <dd>0 - ボトムアップ</dd>
 * 
 * <dt>bV2Halftone</dt>
 * <dd>0x003C</dd>
 * <dd>2 バイト</dd>
 * <dd>ハーフトーンの方式</dd>
 * <dd>0 - ハーフトーンなし</dd>
 * <dd>1 - 誤差拡散法</dd>
 * <dd>2 - PANDA</dd>
 * <dd>3 - Super Circle</dd>
 * 
 * <dt>bV2HalftoneParam1</dt>
 * <dd>0x003E</dd>
 * <dd>4 バイト</dd>
 * <dd>ハーフトーン時のパラメータ1</dd>
 * 
 * <dt>bV2HalftoneParam2</dt>
 * <dd>0x0042</dd>
 * <dd>4 バイト</dd>
 * <dd>ハーフトーン時のパラメータ2</dd>
 * <dd>誤差拡散法の場合は無視される</dd>
 * 
 * <dt>bV2Encoding</dt>
 * <dd>0x0046</dd>
 * <dd>4 バイト</dd>
 * <dd>符号化方式</dd>
 * <dd>0 - RGB2、RGBQUAD</dd>
 * <dd>-1 - パレット方式</dd>
 * 
 * <dt>bV2Id</dt>
 * <dd>0x004A</dd>
 * <dd>4 バイト</dd>
 * <dd>識別子</dd>
 * <dd>アプリケーションが独自に使用してもよい領域</dd>
 * </dl>
 * 
 * @author ソウルP
 * @version 1.0 2017/10/18 BMP_V2作成
 */
public class BMP_V2 extends BMP_V3 {
    // ファイルヘッダ
    protected byte[]              bV2HeaderSize;                 // ファイルヘッダと情報ヘッダの合計サイズを格納する (byte)
    protected byte[]              bV2HotspotX;                   // ホットスポット x
    protected byte[]              bV2HotspotY;                   // ホットスポット y

    // 情報ヘッダ
    protected byte[]              bV2Resolution;                 // 解像度の単位
    protected static final byte[] BV2_RESERVED = { 0x00, 0x00 }; // 予約領域 常に0
    protected byte[]              bV2Format;                     // 記録方式
    protected byte[]              bV2Halftone;                   // ハーフトーンの方式
    protected byte[]              bV2HalftoneParam1;             // ハーフトーン時のパラメータ1
    protected byte[]              bV2HalftoneParam2;             // ハーフトーン時のパラメータ2
    protected byte[]              bV2Encoding;                   // 符号化方式
    protected byte[]              bV2Id;                         // 識別子

    /**
     * <b>BMP - OS/2 V2</b>
     */
    public BMP_V2() {
        clear();
    }

    /**
     * <b>BMP - OS/2 V2</b>
     * 
     * @param data
     *            データ
     */
    public BMP_V2(byte[] data) {
        clear();
        set(data);
    }

    /**
     * <b>BMP - OS/2 V2</b>
     * 
     * @param bmp
     *            BMPのオブジェクト
     */
    public BMP_V2(BMP bmp) {
        clear();
        set(bmp);
    }

    @Override
    public void clear() {
        super.clear();
        bV2HeaderSize = Tools.int2bytes(0);
        bV2HotspotX = Tools.short2bytes((short) 0);
        bV2HotspotY = Tools.short2bytes((short) 0);
        bcSize = Tools.int2bytes(INFO_HEADER_SIZE_V2_MIN);
        bV2Resolution = Tools.short2bytes((short) 0);
        bV2Format = Tools.short2bytes((short) 0);
        bV2Halftone = Tools.short2bytes((short) 0);
        bV2HalftoneParam1 = Tools.int2bytes(0);
        bV2HalftoneParam2 = Tools.int2bytes(0);
        bV2Encoding = Tools.int2bytes(0);
        bV2Id = Tools.int2bytes(0);
    }

    /**
     * @deprecated V2はファイルサイズ格納する情報はありません。<br>
     *             その代わり、ヘッダサイズが格納されます。{@link #getHeaderSize()}
     * @return 0
     */
    @Deprecated
    @Override
    public final int getFileSize() {
        return 0;
    }

    /**
     * @deprecated V2はファイルサイズ格納する情報はありません。<br>
     *             その代わり、ヘッダサイズが格納されます。{@link #setHeaderSize(int)}
     * 
     * @param bfSize
     *            ファイルサイズ
     */
    @Deprecated
    @Override
    public final void setFileSize(int bfSize) {
    }

    /**
     * ファイルヘッダと情報ヘッダの合計サイズ
     * 
     * @return ヘッダサイズ
     */
    public int getHeaderSize() {
        return Tools.bytes2int(bV2HeaderSize);
    }

    /**
     * ファイルヘッダと情報ヘッダの合計サイズ
     * 
     * @param bV2HeaderSize
     *            ヘッダサイズ
     */
    public void setHeaderSize(int bV2HeaderSize) {
        this.bV2HeaderSize = Tools.int2bytes(bV2HeaderSize);
    }

    /**
     * ポインタのホットスポットの x 座標
     * 
     * @return ホットスポット x
     */
    public short getHotspotX() {
        return Tools.bytes2short(bV2HotspotX);
    }

    /**
     * ポインタのホットスポットの x 座標
     * 
     * @param bV2HotspotX
     *            ホットスポット x
     */
    public void setHotspotX(short bV2HotspotX) {
        this.bV2HotspotX = Tools.short2bytes(bV2HotspotX);
    }

    /**
     * ポインタのホットスポットの y 座標
     * 
     * @return ホットスポット y
     */
    public short getHotspotY() {
        return Tools.bytes2short(bV2HotspotY);
    }

    /**
     * ポインタのホットスポットの y 座標
     * 
     * @param bV2HotspotY
     *            ホットスポット y
     */
    public void setHotspotY(short bV2HotspotY) {
        this.bV2HotspotX = Tools.short2bytes(bV2HotspotY);
    }

    /**
     * <ul>
     * 解像度の単位
     * <li>0 - ピクセル/m</li>
     * </ul>
     * 
     * @return 解像度の単位
     */
    public short getResolution() {
        return Tools.bytes2short(bV2Resolution);
    }

    /**
     * <ul>
     * 解像度の単位
     * <li>0 - ピクセル/m</li>
     * </ul>
     * 
     * @param bV2Resolution
     *            解像度の単位
     */
    public void setResolution(short bV2Resolution) {
        this.bV2Resolution = Tools.short2bytes(bV2Resolution);
    }

    /**
     * <ul>
     * 記録方式
     * <li>0 - ボトムアップ</li>
     * </ul>
     * 
     * @return 記録方式
     */
    public short getFormat() {
        return Tools.bytes2short(bV2Format);
    }

    /**
     * <ul>
     * 記録方式
     * <li>0 - ボトムアップ</li>
     * </ul>
     * 
     * @param bV2Format
     *            記録方式
     */
    public void setFormat(short bV2Format) {
        this.bV2Format = Tools.short2bytes(bV2Format);
    }

    /**
     * <ul>
     * ハーフトーンの方式
     * <li>0 - ハーフトーンなし</li>
     * <li>1 - 誤差拡散法</li>
     * <li>2 - PANDA</li>
     * <li>3 - Super Circle</li>
     * </ul>
     * 
     * @return ハーフトーンの方式
     */
    public short getHalftone() {
        return Tools.bytes2short(bV2Halftone);
    }

    /**
     * <ul>
     * ハーフトーンの方式
     * <li>0 - ハーフトーンなし</li>
     * <li>1 - 誤差拡散法</li>
     * <li>2 - PANDA</li>
     * <li>3 - Super Circle</li>
     * </ul>
     * 
     * @param bV2Halftone
     *            ハーフトーンの方式
     */
    public void setHalftone(short bV2Halftone) {
        this.bV2Halftone = Tools.short2bytes(bV2Halftone);
    }

    /**
     * @return ハーフトーン時のパラメータ1
     */
    public int getHalftoneParam1() {
        return Tools.bytes2int(bV2HalftoneParam1);
    }

    /**
     * @param bV2HalftoneParam1
     *            ハーフトーン時のパラメータ1
     */
    public void setHalftoneParam1(int bV2HalftoneParam1) {
        this.bV2HalftoneParam1 = Tools.int2bytes(bV2HalftoneParam1);
    }

    /**
     * 誤差拡散法の場合は無視される
     * 
     * @return ハーフトーン時のパラメータ2
     */
    public int getHalftoneParam2() {
        return Tools.bytes2int(bV2HalftoneParam2);
    }

    /**
     * 誤差拡散法の場合は無視される
     * 
     * @param bV2HalftoneParam2
     *            ハーフトーン時のパラメータ2
     */
    public void setHalftoneParam2(int bV2HalftoneParam2) {
        this.bV2HalftoneParam2 = Tools.int2bytes(bV2HalftoneParam2);
    }

    /**
     * <ul>
     * <li>0 - RGB2, RGBQUAD</li>
     * <li>-1 - パレット方式</li>
     * </ul>
     * 
     * @return 符号化方式
     */
    public int getEncoding() {
        return Tools.bytes2int(bV2Encoding);
    }

    /**
     * <ul>
     * <li>0 - RGB2, RGBQUAD</li>
     * <li>-1 - パレット方式</li>
     * </ul>
     * 
     * @param bV2Encoding
     *            符号化方式
     */
    public void setEncoding(int bV2Encoding) {
        this.bV2Encoding = Tools.int2bytes(bV2Encoding);
    }

    /**
     * @return 識別子
     */
    public int getId() {
        return Tools.bytes2int(bV2Id);
    }

    /**
     * @param bV2Id
     *            識別子
     */
    public void setId(int bV2Id) {
        this.bV2Id = Tools.int2bytes(bV2Id);
    }

    /**
     * @deprecated
     * @return null
     */
    @Deprecated
    @Override
    public byte[] getGap1() {
        return null;
    }

    /**
     * @deprecated
     */
    @Deprecated
    @Override
    public void setGap1(byte[] gap1) {
    }

    /**
     * @deprecated
     * @return true
     */
    @Deprecated
    @Override
    public boolean isEmptyGap1() {
        return true;
    }

    @Override
    public byte[] getBitmapHeader() {
        ByteBuffer buff = ByteBuffer.allocate(FILE_HEADER_SIZE + getInfoHeaderSize());
        buff.put(getFileHeader());
        buff.put(getInfoHeader());
        return buff.array();
    }

    @Override
    public void set(byte[] data) {
        byte[] fileHeader = Arrays.copyOfRange(data, 0, FILE_HEADER_SIZE);
        setFileHeader(fileHeader);
        int headerSize = getHeaderSize();
        byte[] infoHeader = Arrays.copyOfRange(data, FILE_HEADER_SIZE, headerSize);
        if (setInfoHeader(infoHeader) != -1) {
            int offset = Tools.bytes2int(bfOffBits);
            byte[] bColors = Arrays.copyOfRange(data, headerSize, offset);
            setColors(bColors);
            byte[] imgData = Arrays.copyOfRange(data, offset, data.length);
            setImage(imgData);
        } else {
            clear();
        }
    }

    @Override
    public void set(BMP bmp) {
        super.set(bmp);
        setHeaderSize(bmp.getHeaderSize());
        setHotspotX((short) bmp.getHotspotX());
        setHotspotY((short) bmp.getHotspotY());
        setInfoHeaderSize(bmp.getInfoHeaderSize());
        setResolution((short) bmp.getResolution());
        setFormat((short) bmp.getFormat());
        setHalftone((short) bmp.getHaltone());
        setHalftoneParam1(bmp.getHalftoneParam1());
        setHalftoneParam2(bmp.getHalftoneParam2());
        setEncoding(bmp.getEncoding());
        setId(bmp.getId());
    }

    @Override
    public int setFileHeader(byte[] data) {
        int offset = 0;
        bfType = Arrays.copyOfRange(data, offset, offset += 2);
        bV2HeaderSize = Arrays.copyOfRange(data, offset, offset += 4);
        bV2HotspotX = Arrays.copyOfRange(data, offset, offset += 2);
        bV2HotspotY = Arrays.copyOfRange(data, offset, offset += 2);
        bfOffBits = Arrays.copyOfRange(data, offset, offset + 4);
        return offset;
    }

    @Override
    public int setInfoHeader(byte[] data) {
        int offset = 0;
        try {
            bcSize = Arrays.copyOfRange(data, offset, offset += 4); // 0 - 3 (4 バイト)
            bcWidth = Arrays.copyOfRange(data, offset, offset += 4); // 4 - 7 (4 バイト)
            bcHeight = Arrays.copyOfRange(data, offset, offset += 4); // 8 - 11 (4 バイト)
            bcBitCount = Arrays.copyOfRange(data, offset += 2, offset + 2); // 13 - 15 (2 バイト)
            switch (data.length) {
                case 64:
                    bV2Id = Arrays.copyOfRange(data, 60, 64); // 60 - 63 (4 バイト)
                case 60:
                    bV2Encoding = Arrays.copyOfRange(data, 56, 60); // 56 - 59 (4 バイト)
                case 56:
                    bV2HalftoneParam2 = Arrays.copyOfRange(data, 52, 56); // 52 - 55 (4 バイト)
                case 52:
                    bV2HalftoneParam1 = Arrays.copyOfRange(data, 48, 52); // 48 - 51 (4 バイト)
                case 48:
                    bV2Halftone = Arrays.copyOfRange(data, 46, 48); // 46 - 47 (2 バイト)
                case 46:
                    bV2Format = Arrays.copyOfRange(data, 44, 46); // 44 - 45 (2 バイト)
                case 44: // 42 - 44 (2バイト) 予約領域
                case 42:
                    bV2Resolution = Arrays.copyOfRange(data, 40, 42); // 40 - 41 (2 バイト)
                case 40:
                    biCirImportant = Arrays.copyOfRange(data, 36, 40); // 36 - 39 (4 バイト)
                case 36:
                    biClrUsed = Arrays.copyOfRange(data, 32, 36); // 32 - 35 (4 バイト)
                case 32:
                    biYPelsPerMeter = Arrays.copyOfRange(data, 28, 32); // 28 - 31 (4 バイト)
                case 28:
                    biXPelsPerMeter = Arrays.copyOfRange(data, 24, 28); // 24 - 27 (4 バイト)
                case 24:
                    biSizeImage = Arrays.copyOfRange(data, 20, 24); // 20 - 23 (4 バイト)
                case 20:
                    biCompression = Arrays.copyOfRange(data, 16, 20); // 16 - 19 (4 バイト)
                    break;
                default:
                    throw new Exception(ERROR_INVALID_HEADER_SIZE);
            }
            offset = data.length;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            offset = -1;
        }
        return offset;
    }

    @Override
    public void setImage(byte[] data) {
        if (getCompression() == 1 || getCompression() == 2 || getCompression() == 4) {
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
                    for (int a = 0; a < byteArray.length; a++) {
                        byteArray[a] = bytes.get(a);
                    }
                    bytes.clear();
                    image.add(byteArray);
                    if (bb == 0x01) {
                        break;
                    }
                }
            }
        } else if (getCompression() == 3) {
        } else {
            super.setImage(data);
        }
    }

    @Override
    public byte[] get() {
        int imageSize = 0;
        for (byte[] b : image)
            imageSize += b.length;
        ByteBuffer buff = ByteBuffer.allocate(FILE_HEADER_SIZE + getInfoHeaderSize() + colors.size() * 4 + imageSize);
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
        buff.put(bV2HeaderSize);
        buff.put(bV2HotspotX);
        buff.put(bV2HotspotY);
        buff.put(bfOffBits);
        return buff.array();
    }

    @Override
    public byte[] getInfoHeader() {
        int infoHeaderSize = getInfoHeaderSize();
        ByteBuffer buff = ByteBuffer.allocate(infoHeaderSize);
        byte[] data;
        try {
            buff.put(bcSize);
            buff.put(bcWidth);
            buff.put(bcHeight);
            buff.put(BC_PLANES);
            buff.put(bcBitCount);
            data = buff.array();
            switch (infoHeaderSize) {
                case 64:
                    arrayInsert(data, bV2Id, 60); // 60 - 64 (4 バイト)
                case 60:
                    arrayInsert(data, bV2Encoding, 56); // 56 - 60 (4 バイト)
                case 56:
                    arrayInsert(data, bV2HalftoneParam2, 52); // 52 - 56 (4 バイト)
                case 52:
                    arrayInsert(data, bV2HalftoneParam1, 48); // 48 - 52 (4 バイト)
                case 48:
                    arrayInsert(data, bV2Halftone, 46); // 46 - 48 (2 バイト)
                case 46:
                    arrayInsert(data, bV2Format, 44); // 44 - 46 (2 バイト)
                case 44:
                    arrayInsert(data, BV2_RESERVED, 42); // 42 - 44 (2 バイト)
                case 42:
                    arrayInsert(data, bV2Resolution, 40); // 40 - 42 (2 バイト)
                case 40:
                    arrayInsert(data, biCirImportant, 36); // 36 - 40 (4 バイト)
                case 36:
                    arrayInsert(data, biClrUsed, 32); // 32 - 36 (4 バイト)
                case 32:
                    arrayInsert(data, biYPelsPerMeter, 28); // 28 - 32 (4 バイト)
                case 28:
                    arrayInsert(data, biXPelsPerMeter, 24); // 24 - 28 (4 バイト)
                case 24:
                    arrayInsert(data, biSizeImage, 20); // 20 - 24 (4 バイト)
                case 20:
                    arrayInsert(data, biCompression, 16); // 16 - 20 (4 バイト)
                    break;
                default:
                    throw new Exception(ERROR_INVALID_HEADER_SIZE);
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public int getVersion() {
        return 2;
    }

    @Override
    public String toString() {
        byte[] t = getType();
        int imageSize = 0;
        for (byte[] b : image) {
            imageSize += b.length;
        }
        int fileSize = getHeaderSize() + colors.size() * 4 + imageSize;
        StringBuffer buff = new StringBuffer();
        buff.append(STR_BMP_V);
        buff.append(getVersion());
        buff.append(STR_NEW_LINE);

        buff.append(STR_FILE_TYPE);
        buff.append((char) t[0]);
        buff.append((char) t[1]);
        buff.append(STR_SPACE);
        buff.append(STR_BRACKET_LEFT);
        if (Arrays.equals(t, new byte[] { 0x42, 0x4D })) {
            buff.append(STR_BMP);
        } else if (Arrays.equals(t, new byte[] { 0x49, 0x43 })) {
            buff.append(STR_ICO_BLACK_WHITE);
        } else if (Arrays.equals(t, new byte[] { 0x43, 0x49 })) {
            buff.append(STR_ICO_COLOR);
        } else if (Arrays.equals(t, new byte[] { 0x50, 0x54 })) {
            buff.append(STR_PTR_BLACK_WHITE);
        } else if (Arrays.equals(t, new byte[] { 0x43, 0x50 })) {
            buff.append(STR_PTR_COLOR);
        } else {
            buff.append(STR_UNKNOWN);
        }
        buff.append(STR_BRACKET_RIGHT);
        buff.append(STR_NEW_LINE);

        buff.append(STR_FILE_SIZE);
        buff.append(fileSize);
        buff.append(STR_BYTE);
        buff.append(STR_NEW_LINE);

        buff.append(STR_HEADER_SIZE);
        buff.append(getHeaderSize());
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
                buff.append(STR_RGB);
                break;
            case 1:
                buff.append(STR_RLE8);
                break;
            case 2:
                buff.append(STR_RLE4);
                break;
            case 3:
                buff.append(STR_HUFFMAN_1D);
                break;
            case 4:
                buff.append(STR_RLE24);
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
        buff.append(getCirImportant());
        buff.append(STR_NEW_LINE);

        buff.append(STR_RESOLUTION);
        switch (getResolution()) {
            case 0:
                buff.append(STR_PIXEL_M);
                break;
            default:
                buff.append(STR_UNKNOWN);
                break;
        }
        buff.append(STR_NEW_LINE);
        buff.append(STR_FORMAT);
        switch (getFormat()) {
            case 0:
                buff.append(STR_FORMAT_BOTTOM_UP);
                break;
            default:
                buff.append(STR_UNKNOWN);
                break;
        }
        buff.append(STR_NEW_LINE);
        buff.append(STR_HALFTONE);
        switch (getHalftone()) {
            case 0:
                buff.append(STR_NO_HALFTONE);
                break;
            case 1:
                buff.append(STR_RANDOM_DITHER);
                break;
            case 2:
                buff.append(STR_PANDA);
                break;
            case 3:
                buff.append(STR_SUPER_CIRCLE);
                break;
            default:
                buff.append(STR_UNKNOWN);
                break;
        }
        buff.append(STR_NEW_LINE);

        buff.append(STR_HALFTONE_PARAM1);
        buff.append(getHalftoneParam1());
        buff.append(STR_NEW_LINE);

        buff.append(STR_HALFTONE_PARAM2);
        buff.append(getHalftoneParam2());
        buff.append(STR_NEW_LINE);

        buff.append(STR_ENCODING);
        switch (getEncoding()) {
            case 0:
                buff.append(STR_RGB2_RGBQUAD);
                break;
            case -1:
                buff.append(STR_PALLET);
                break;
            default:
                buff.append(STR_UNKNOWN);
                break;
        }
        buff.append(STR_NEW_LINE);

        buff.append(STR_ID);
        buff.append(getId());
        buff.append(STR_NEW_LINE);
        buff.append(STR_NEW_LINE);

        buff.append(toStrColorImage());

        return buff.toString();
    }

    private static void arrayInsert(byte[] src, byte[] arrays, int index) {
        arrayInsert(src, arrays, index, 0, arrays.length);
    }

    private static void arrayInsert(byte[] src, byte[] arrays, int index, int from, int to) {
        for (int i = from; i < to; i++) {
            src[index++] = arrays[i];
        }
    }
}
