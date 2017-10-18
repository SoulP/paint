package file.format;

import file.Tools;

/**
 * <b>BMP OS/2 2.x V2</b><br>
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
 * <td>bcSize</td>
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
 * @version 1.0 2017/10/18 BMPheaderV2作成
 */
public class BMPheaderV2 extends BMPheaderV3 {
    // ファイルヘッダ
    protected byte[]              bV2HotspotX;                              // ポインタのホットスポットの x 座標
    protected byte[]              bV2HotspotY;                              // ポインタのホットスポットの y 座標

    // 情報ヘッダ
    protected byte[]              bV2Resolution;                            // 解像度の単位
    protected static final byte[] BV2_RESERVED            = { 0x00, 0x00 }; // 予約領域 常に0
    protected byte[]              bV2Format;                                // 記録方式
    protected byte[]              bV2Halftone;                              // ハーフトーンの方式
    protected byte[]              bV2HalftoneParam1;                        // ハーフトーン時のパラメータ1
    protected byte[]              bV2HalftoneParam2;                        // ハーフトーン時のパラメータ2
    protected byte[]              bV2Encoding;                              // 符号化方式
    protected byte[]              bV2Id;                                    // 識別子

    protected static final int    INFO_HEADER_SIZE_V2_MIN = 16;             // 情報ヘッダサイズ 最小
    protected static final int    INFO_HEADER_SIZE_V2_MAX = 64;             // 情報ヘッダサイズ 最大

    public BMPheaderV2() {
        clear();
    }

    /**
     * <b>初期化</b>
     */
    @Override
    public void clear() {
        super.clear();
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
}
