package file.format.bmp;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import file.Tools;
import file.io.BMP;

/**
 * <b>BMP - OS/2 V2</b><br>
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
 * <td>BM (0x42, 0x4d)（ビットマップ）<br>
 * IC (0x49, 0x43)（モノクロアイコン）<br>
 * CI (0x43, 0x49)（カラーアイコン）<br>
 * PT (0x50, 0x54)（モノクロポインタ）<br>
 * CP (0x43, 0x50)（カラーポインタ）</td>
 * <td>bfType</td>
 * </tr>
 * <tr>
 * <td>0x0002</td>
 * <td>4 バイト</td>
 * <td>ヘッダサイズ</td>
 * <td>ファイルヘッダと情報ヘッダの合計サイズを格納する（単位はバイト）</td>
 * <td>bV2HeaderSize</td>
 * </tr>
 * <tr>
 * <td>0x0006</td>
 * <td>2 バイト</td>
 * <td>ホットスポット x</td>
 * <td>ポインタのホットスポットの x 座標</td>
 * <td>bV2HotspotX</td>
 * </tr>
 * <tr>
 * <td>0x0008</td>
 * <td>2 バイト</td>
 * <td>ホットスポット y</td>
 * <td>ポインタのホットスポットの y 座標</td>
 * <td>bV2HotspotY</td>
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
 * <td>16～64（可変長）</td>
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
 * <td>１ピクセルあたりビット数</td>
 * <td>1, 4, 8, 24</td>
 * <td>bcBitCount</td>
 * </tr>
 * <tr>
 * <td>0x001E</td>
 * <td>4 バイト</td>
 * <td>圧縮形式</td>
 * <td>0 - RGB （非圧縮）<br>
 * 1 - RLE8 （8ビット/ピクセル）<br>
 * 2 - RLE4 （4ビット/ピクセル）<br>
 * 3 - HUFFMAN_1D （1ビットハフマン符号化）<br>
 * 4 - RLE_24 （24ビット/ピクセル）</td>
 * <td>biCompression</td>
 * </tr>
 * <tr>
 * <td>0x0022</td>
 * <td>4 バイト</td>
 * <td>画像データサイズ</td>
 * <td>単位はバイト。非圧縮の場合は0を入れても良い</td>
 * <td>biSizeImage</td>
 * </tr>
 * <tr>
 * <td>0x0026</td>
 * <td>4 バイト</td>
 * <td>水平方向の解像度</td>
 * <td>単位はピクセル/m</td>
 * <td>biXPelsPerMeter</td>
 * </tr>
 * <tr>
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
 * <td>2 バイト</td>
 * <td>解像度の単位</td>
 * <td>0 - ピクセル/m</td>
 * <td>bV2Resolution</td>
 * </tr>
 * <tr>
 * <td>0x0038</td>
 * <td>2 バイト</td>
 * <td>予約領域</td>
 * <td>常に0</td>
 * <td>BV2_RESERVED</td>
 * </tr>
 * <tr>
 * <td>0x003A</td>
 * <td>2 バイト</td>
 * <td>記録方式</td>
 * <td>0 - ボトムアップ</td>
 * <td>bV2Format</td>
 * </tr>
 * <tr>
 * <td>0x003C</td>
 * <td>2 バイト</td>
 * <td>ハーフトーンの方式</td>
 * <td>0 - ハーフトーンなし<br>
 * 1 - 誤差拡散法<br>
 * 2 - PANDA<br>
 * 3 - Super Circle</td>
 * <td>bV2Halftone</td>
 * </tr>
 * <tr>
 * <td>0x003E</td>
 * <td>4 バイト</td>
 * <td>ハーフトーン時のパラメータ1</td>
 * <td></td>
 * <td>bV2HalftoneParam1</td>
 * </tr>
 * <tr>
 * <td>0x0042</td>
 * <td>4 バイト</td>
 * <td>ハーフトーン時のパラメータ2</td>
 * <td>誤差拡散法の場合は無視される</td>
 * <td>bV2HalftoneParam2</td>
 * </tr>
 * <tr>
 * <td>0x0046</td>
 * <td>4 バイト</td>
 * <td>符号化方式</td>
 * <td>0 - RGB2、RGBQUAD<br>
 * -1 - パレット方式</td>
 * <td>bV2Encoding</td>
 * </tr>
 * <tr>
 * <td>0x004A</td>
 * <td>4 バイト</td>
 * <td>識別子</td>
 * <td>アプリケーションが独自に使用してもよい領域</td>
 * <td>bV2Id</td>
 * </tr>
 * </table>
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

    @Override
    public byte[] getBitmapHeader() {
        ByteBuffer buff = ByteBuffer.allocate(FILE_HEADER_SIZE + getInfoHeaderSize());
        buff.put(getFileHeader());
        buff.put(getInfoHeader());
        return buff.array();
    }

    @Override
    public void set(byte[] data) {
        byte[] fileHeader = Tools.subbytes(data, 0, FILE_HEADER_SIZE);
        setFileHeader(fileHeader);
        int headerSize = getHeaderSize();
        byte[] infoHeader = Tools.subbytes(data, FILE_HEADER_SIZE, headerSize);
        setInfoHeader(infoHeader);
        int offset = Tools.bytes2int(bfOffBits);
        byte[] bColors = Tools.subbytes(data, headerSize, offset);
        setColors(bColors);
        byte[] imgData = Tools.subbytes(data, offset, data.length);
        setImage(imgData);
    }

    @Override
    public void set(BMP bmp) {
        super.set(bmp);
        setHeaderSize(bmp.getHeaderSize());
        setHotspotX((short) bmp.getHotspotX());
        setHotspotY((short) bmp.getHotspotY());
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
        bfType = Tools.subbytes(data, offset, offset += 2);
        bV2HeaderSize = Tools.subbytes(data, offset, offset += 4);
        bV2HotspotX = Tools.subbytes(data, offset, offset += 2);
        bV2HotspotY = Tools.subbytes(data, offset, offset += 2);
        bfOffBits = Tools.subbytes(data, offset, offset + 4);
        return offset;
    }

    @Override
    public int setInfoHeader(byte[] data) {
        int offset = 0;
        bcSize = Tools.subbytes(data, offset, offset += 4);
        bcWidth = Tools.subbytes(data, offset, offset += 4);
        bcHeight = Tools.subbytes(data, offset, offset += 4);
        bcBitCount = Tools.subbytes(data, offset += 2, offset += 2);
        if (data.length >= 20) biCompression = Tools.subbytes(data, offset, offset += 4); // 16 - 20 (4 バイト)
        if (data.length >= 24) biSizeImage = Tools.subbytes(data, offset, offset += 4); // 20 - 24 (4 バイト)
        if (data.length >= 28) biXPelsPerMeter = Tools.subbytes(data, offset, offset += 4); // 24 - 28 (4 バイト)
        if (data.length >= 32) biYPelsPerMeter = Tools.subbytes(data, offset, offset += 4); // 28 - 32 (4 バイト)
        if (data.length >= 36) biClrUsed = Tools.subbytes(data, offset, offset += 4); // 32 - 36 (4 バイト)
        if (data.length >= 40) biCirImportant = Tools.subbytes(data, offset, offset += 4); // 36 - 40 (4 バイト)
        if (data.length >= 42) bV2Resolution = Tools.subbytes(data, offset, offset += 2); // 40 - 42 (2 バイト)
        if (data.length >= 46) bV2Format = Tools.subbytes(data, offset += 2, offset += 2); // 44 - 46 (2 バイト)
        if (data.length >= 48) bV2Halftone = Tools.subbytes(data, offset, offset += 2); // 46 - 48 (2 バイト)
        if (data.length >= 52) bV2HalftoneParam1 = Tools.subbytes(data, offset, offset += 4); // 48 - 52 (4 バイト)
        if (data.length >= 56) bV2HalftoneParam2 = Tools.subbytes(data, offset, offset += 4); // 52 - 56 (4 バイト)
        if (data.length >= 60) bV2Encoding = Tools.subbytes(data, offset, offset += 4); // 56 - 60 (4 バイト)
        if (data.length >= 64) bV2Id = Tools.subbytes(data, offset, offset += 4); // 60 - 64 (4 バイト)
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
                    for (int a = 0; a < byteArray.length; a++)
                        byteArray[a] = bytes.get(a);
                    bytes.clear();
                    image.add(byteArray);
                    if (bb == 0x01) break;
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
        buff.put(bcSize);
        buff.put(bcWidth);
        buff.put(bcHeight);
        buff.put(BC_PLANES);
        buff.put(bcBitCount);
        if (infoHeaderSize >= 20) buff.put(biCompression); // 16 - 20 (4 バイト)
        if (infoHeaderSize >= 24) buff.put(biSizeImage); // 20 - 24 (4 バイト)
        if (infoHeaderSize >= 28) buff.put(biXPelsPerMeter);// 24 - 28 (4 バイト)
        if (infoHeaderSize >= 32) buff.put(biYPelsPerMeter);// 28 - 32 (4 バイト)
        if (infoHeaderSize >= 36) buff.put(biClrUsed); // 32 - 36 (4 バイト)
        if (infoHeaderSize >= 40) buff.put(biCirImportant); // 36 - 40 (4 バイト)
        if (infoHeaderSize >= 42) buff.put(bV2Resolution); // 40 - 42 (2 バイト)
        if (infoHeaderSize >= 44) buff.put(BV2_RESERVED); // 42 - 44 (2 バイト)
        if (infoHeaderSize >= 46) buff.put(bV2Format); // 44 - 46 (2 バイト)
        if (infoHeaderSize >= 48) buff.put(bV2Halftone); // 46 - 48 (2 バイト)
        if (infoHeaderSize >= 52) buff.put(bV2HalftoneParam1); // 48 - 52 (4 バイト)
        if (infoHeaderSize >= 56) buff.put(bV2HalftoneParam2); // 52 - 56 (4 バイト)
        if (infoHeaderSize >= 60) buff.put(bV2Encoding); // 56 - 60 (4 バイト)
        if (infoHeaderSize >= 64) buff.put(bV2Id); // 60 - 64 (4 バイト)
        return buff.array();
    }

    @Override
    public int getVersion() {
        return 2;
    }

    /**
     * @deprecated V2はファイルサイズ格納する情報はありません。
     */
    @Deprecated
    @Override
    protected void updateFileSize() {
    }

    @Override
    public String toString() {
        byte[] t = getType();
        int imageSize = 0;
        for (byte[] b : image)
            imageSize += b.length;
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
        if (Arrays.equals(t, new byte[] { 0x42, 0x4D })) buff.append(STR_BMP);
        else if (Arrays.equals(t, new byte[] { 0x49, 0x43 })) buff.append(STR_ICO_BLACK_WHITE);
        else if (Arrays.equals(t, new byte[] { 0x43, 0x49 })) buff.append(STR_ICO_COLOR);
        else if (Arrays.equals(t, new byte[] { 0x50, 0x54 })) buff.append(STR_PTR_BLACK_WHITE);
        else if (Arrays.equals(t, new byte[] { 0x43, 0x50 })) buff.append(STR_PTR_COLOR);
        else buff.append(STR_UNKNOWN);
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
}
