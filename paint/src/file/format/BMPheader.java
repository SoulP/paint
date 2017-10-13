package file.format;

/**
 * <b>BMPファイルフォーマット</b><br>
 * date: 2017/10/12 last_date: 2017/10/13
 * 
 * @author ソウルP
 * @version 1.0 2017/10/12 BMPheader作成
 */
public abstract class BMPheader {
    // ファイルヘッダ
    protected static final byte[] BF_TYPE          = { 0x42, 0x4D };             // ファイルタイプ 通常は'BM'
    protected byte[]              bfSize           = new byte[4];                // ファイルサイズ (byte)
    protected static final byte[] BF_RESERVERD_1   = { 0x00, 0x00 };             // 予約領域 常に 0
    protected static final byte[] BF_RESERVERD_2   = { 0x00, 0x00 };             // 予約領域 常に 0
    protected byte[]              bfOffBits        = new byte[4];                // ファイル先頭から画像データまでのオフセット (byte)

    // 情報ヘッダ
    protected static final byte[] BC_SIZE          = { 0x28, 0x00, 0x00, 0x00 }; // ヘッダサイズ (byte)
    protected byte[]              bcWidth          = new byte[4];                // 画像の幅 (ピクセル)
    protected byte[]              bcHeight         = new byte[4];                // 画像の高さ (ピクセル)
    // bcHeight の値が正数なら，画像データは左下から右上へ
    // bcHeight の値が負数なら，画像データは左上から右下へ
    protected static final byte[] BC_PLANES        = { 0x01, 0x00 };             // プレーン数 常に 1
    protected byte[]              bcBitCount       = new byte[2];                // 1画素あたりのデータサイズ (bit)
    // 例）256 色ビットマップ ＝ 8
    protected byte[]              biCompression    = new byte[4];                // 圧縮形式
    // 0 - BI_RGB (無圧縮)
    // 1 - BI_RLE8 (RunLength 8 bits/pixel)
    // 2 - BI_RLE4 (RunLength 4 bits/pixel)
    // 3 - BI_BITFIELDS (Bitfields)
    protected byte[]              biSizeImage      = new byte[4];                // 画像データ部のサイズ (byte)
    // 0 の場合もある
    // ※biCompression が 0 以外の場合、 ここの値を 0 にすることは出来ない
    protected byte[]              biXPelsPerMeter   = new byte[4];                // 横方向解像度 (1mあたりの画素数)
    // 0 の場合もある
    protected byte[]              biYPelsPerMeter   = new byte[4];                // 縦方向解像度 (1mあたりの画素数)
    // 0 の場合もある
    protected byte[]              biClrUsed        = new byte[4];                // 格納されているパレット数 (使用色数)
    // 0 の場合もある
    protected byte[]              biCirImportant   = new byte[4];                // 重要なパレットのインデックス
    // 0 の場合もある

    protected static final int    FILE_HEADER_SIZE = 14;                         // ファイルヘッダサイズ
    protected static final int    INFO_HEADER_SIZE = 40;                         // 情報ヘッダサイズ
}
