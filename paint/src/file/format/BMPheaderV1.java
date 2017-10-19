package file.format;

import java.nio.ByteBuffer;

import file.Tools;

/**
 * <b>BMP - OS/2 V1</b><br>
 * date: 2017/10/18 last_date: 2017/10/19<br>
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
 * <td>12</td>
 * <td>bcSize</td>
 * </tr>
 * <tr>
 * <td>0x0012</td>
 * <td>2 バイト</td>
 * <td>ビットマップの横幅</td>
 * <td>単位はピクセル</td>
 * <td>bcWidth</td>
 * </tr>
 * <tr>
 * <td>0x0014</td>
 * <td>2 バイト</td>
 * <td>ビットマップの縦幅</td>
 * <td>単位はピクセル</td>
 * <td>bcHeight</td>
 * </tr>
 * <tr>
 * <td>0x0016</td>
 * <td>2 バイト</td>
 * <td>プレーン数</td>
 * <td>常に1</td>
 * <td>BC_PLANES</td>
 * </tr>
 * <tr>
 * <td>0x0018</td>
 * <td>2 バイト</td>
 * <td>1ピクセルあたりのビット数</td>
 * <td>1, 4, 8, 24</td>
 * <td>bcBitCount</td>
 * </tr>
 * </table>
 * 
 * @author ソウルP
 * @version 1.0 2017/10/18 BMPheaderV1作成
 */
public class BMPheaderV1 {
    // ファイルヘッダ
    protected byte[]              bfType;                            // ファイルタイプ 通常は'BM'
    protected byte[]              bfSize;                            // ファイルサイズ (byte)
    protected static final byte[] BF_RESERVED_1    = { 0x00, 0x00 }; // 予約領域 常に 0
    protected static final byte[] BF_RESERVED_2    = { 0x00, 0x00 }; // 予約領域 常に 0
    protected byte[]              bfOffBits;                         // ファイル先頭から画像データまでのオフセット (byte)

    // 情報ヘッダ
    protected byte[]              bcSize;                            // ヘッダサイズ (byte)
    protected byte[]              bcWidth;                           // ビットマップの横幅 (ピクセル)
    protected byte[]              bcHeight;                          // ビットマップの縦幅 (ピクセル)
    protected static final byte[] BC_PLANES        = { 0x01, 0x00 }; // プレーン数 常に 1
    protected byte[]              bcBitCount;                        // 1ピクセルあたりのビット数 (bit)

    protected final int           FILE_HEADER_SIZE = 14;             // ファイルヘッダサイズ
    protected final int           infoHeaderSize   = 12;             // 情報ヘッダサイズ

    /**
     * <b>BMP - OS/2 V1</b>
     */
    public BMPheaderV1() {
        clear();
    }

    /**
     * <b>初期化</b>
     */
    public void clear() {
        setType(new byte[] { 0x42, 0x4D });
        bfSize = Tools.int2bytes(0);
        bfOffBits = Tools.int2bytes(0);
        bcSize = Tools.int2bytes(infoHeaderSize);
        bcWidth = Tools.short2bytes((short) 0);
        bcHeight = Tools.short2bytes((short) 0);
        bcBitCount = Tools.short2bytes((short) 0);
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
     * ファイルヘッダ と 情報ヘッダ
     * 
     * @return ヘッダ情報
     */
    public byte[] getBitmapHeader() {
        ByteBuffer buff = ByteBuffer.allocate(FILE_HEADER_SIZE + infoHeaderSize);
        buff.put(bfType);
        buff.put(bfSize);
        buff.put(BF_RESERVED_1);
        buff.put(BF_RESERVED_2);
        buff.put(bfOffBits);
        buff.put(bcSize);
        buff.put(bcWidth);
        buff.put(bcHeight);
        buff.put(BC_PLANES);
        buff.put(bcBitCount);
        return buff.array();
    }
}
