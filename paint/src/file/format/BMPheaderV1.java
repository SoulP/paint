package file.format;

import file.Tools;

/**
 * <b>BMP OS/2 1.1 V1</b><br>
 * date: 2017/10/18 last_date: 2017/10/18<br>
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
public abstract class BMPheaderV1 {
    // ファイルヘッダ
    protected byte[]              bfType              = { 0x42, 0x4D }; // ファイルタイプ 通常は'BM'
    protected byte[]              bfSize;                               // ファイルサイズ (byte)
    protected static final byte[] BF_RESERVED_1       = { 0x00, 0x00 }; // 予約領域 常に 0
    protected static final byte[] BF_RESERVED_2       = { 0x00, 0x00 }; // 予約領域 常に 0
    protected byte[]              bfOffBits;                            // ファイル先頭から画像データまでのオフセット (byte)

    // 情報ヘッダ
    protected byte[]              bcSize;                               // ヘッダサイズ (byte)
    protected byte[]              bcWidth;                              // 画像の幅 (ピクセル)
    protected byte[]              bcHeight;                             // 画像の高さ (ピクセル)
    protected static final byte[] BC_PLANES           = { 0x01, 0x00 }; // プレーン数 常に 1
    protected byte[]              bcBitCount;                           // 1画素あたりのデータサイズ (bit)

    protected static final int    FILE_HEADER_SIZE    = 14;             // ファイルヘッダサイズ
    protected static final int    INFO_HEADER_SIZE_V1 = 12;             // 情報ヘッダサイズ

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
        bfSize = Tools.int2bytes(0);
        bfOffBits = Tools.int2bytes(0);
        bcSize = Tools.int2bytes(INFO_HEADER_SIZE_V1);
        bcWidth = Tools.short2bytes((short) 0);
        bcHeight = Tools.short2bytes((short) 0);
        bcBitCount = Tools.short2bytes((short) 0);
    }
}
