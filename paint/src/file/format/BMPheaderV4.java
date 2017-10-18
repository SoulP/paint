package file.format;

/**
 * <b>BMP Windows V4</b><br>
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
 * <td>40</td>
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
 * @version 1.0 2017/10/18 BMPheaderV4作成
 */
public class BMPheaderV4 extends BMPheaderV3 {
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
}
