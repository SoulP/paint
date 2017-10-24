package file.format.bmp;

import file.io.BMP;

/**
 * <b>BMP </b><br>
 * date: 2017/10/20 last_date: 2017/10/24
 * 
 * @author ソウルP
 * @version 1.0 2017/10/20 BMPable作成
 */
public interface BMPable {
    int    FILE_HEADER_SIZE          = 14;                                                       // ファイルヘッダサイズ
    int    INFO_HEADER_SIZE_V1       = 12;                                                       // 情報ヘッダサイズ
    int    INFO_HEADER_SIZE_V2_MIN   = 16;                                                       // 情報ヘッダサイズ 最小
    int    INFO_HEADER_SIZE_V2_MAX   = 64;                                                       // 情報ヘッダサイズ 最大
    int    INFO_HEADER_SIZE_V3       = 40;                                                       // 情報ヘッダサイズ
    int    INFO_HEADER_SIZE_V4       = 108;                                                      // 情報ヘッダサイズ
    int    INFO_HEADER_SIZE_V5       = 124;                                                      // 情報ヘッダサイズ
    // エラー
    String ERROR_FILE_OR             = "ファイルの破損もしくは、";
    String ERROR_IS_NOT_BMP          = ERROR_FILE_OR + "拡張子がBMPではありません。";
    String ERROR_INVALID_HEADER_SIZE = ERROR_FILE_OR + "情報ヘッダサイズが正しくありません。";
    String ERROR_UNSUPPORTED_VERSION = "対応されていないバージョンです。";
    // ログ
    String STR_NEW_LINE              = "\n";
    String STR_SPACE                 = " ";
    String STR_COLON_SPACE           = ":" + STR_SPACE;
    String STR_BRACKET_LEFT          = "(";
    String STR_BRACKET_RIGHT         = ")";
    String STR_BMP                   = "BMP";
    String STR_BMP_V                 = STR_BMP + " V";
    String STR_ICO                   = "ICO";
    String STR_ICON                  = "アイコン";
    String STR_BLACK_WHITE           = "モノクロ";
    String STR_COLOR                 = "カラー";
    String STR_ICO_BLACK_WHITE       = STR_BLACK_WHITE + STR_ICON;
    String STR_ICO_COLOR             = STR_COLOR + STR_ICON;
    String STR_PTR                   = "PTR";
    String STR_POINTER               = "ポインタ";
    String STR_PTR_BLACK_WHITE       = STR_BLACK_WHITE + STR_POINTER;
    String STR_PTR_COLOR             = STR_COLOR + STR_POINTER;
    String STR_FILE_TYPE             = "ファイルタイプ" + STR_COLON_SPACE;
    String STR_UNKNOWN               = "不明";
    String STR_BYTE                  = STR_SPACE + "byte";
    String STR_BIT                   = STR_SPACE + "bit";
    String STR_PIXEL                 = STR_SPACE + "pixel";
    String STR_FILE_SIZE             = "ファイルサイズ" + STR_COLON_SPACE;
    String STR_INFO_HEADER_SIZE      = "情報ヘッダサイズ" + STR_COLON_SPACE;
    String STR_HEADER_SIZE           = "ファイルヘッダと情報ヘッダの合計サイズ" + STR_COLON_SPACE;
    String STR_WIDTH                 = "幅" + STR_COLON_SPACE;
    String STR_HEIGHT                = "高さ" + STR_COLON_SPACE;
    String STR_PLANES                = "プレーン数" + STR_COLON_SPACE;
    String STR_BITCOUNT              = "ビットの深さ" + STR_COLON_SPACE;
    String STR_COMPRESSION           = "圧縮形式" + STR_COLON_SPACE;
    String STR_RGB                   = "RGB （非圧縮）";
    String STR_RLE8                  = "RLE8 （8ビット/ピクセル）";
    String STR_RLE4                  = "RLE4 （4ビット/ピクセル）";
    String STR_RLE24                 = "RLE24 （24ビット/ピクセル）";
    String STR_HUFFMAN_1D            = "HUFFMAN_1D （1ビットハフマン符号化）";
    String STR_BI                    = "BI_";
    String STR_BI_RGB                = STR_BI + STR_RGB;
    String STR_BI_RLE8               = STR_BI + STR_RLE8;
    String STR_BI_RLE4               = STR_BI + STR_RLE4;
    String STR_BI_BITFIELDS          = STR_BI + "BITFIELDS （ビットフィールド）";
    String STR_BI_ALPHABITFIELDS     = STR_BI + "ALPHABITFIELDS （アルファチャンネル付きビットフィールド）";
    String STR_BI_JPEG               = STR_BI + "JPEG";
    String STR_BI_PNG                = STR_BI + "PNG";
    String STR_IMAGE_SIZE            = "画像データサイズ" + STR_COLON_SPACE;
    String STR_PIX_PER_METER_X       = "水平方向の解像度" + STR_COLON_SPACE;
    String STR_PIX_PER_METER_Y       = "垂直方向の解像度" + STR_COLON_SPACE;
    String STR_PIXEL_M               = STR_SPACE + "pixel/m";
    String STR_COLOR_USED            = "使用する色数" + STR_COLON_SPACE;
    String STR_COLOR_IMPORTANT       = "重要な色数" + STR_COLON_SPACE;
    String STR_RESOLUTION            = "解像度の単位" + STR_COLON_SPACE;
    String STR_FORMAT                = "記録方式" + STR_COLON_SPACE;
    String STR_FORMAT_BOTTOM_UP      = "ボトムアップ";
    String STR_HALFTONE              = "ハーフトーン";
    String STR_OF                    = "の";
    String STR_HALFTONE_METHOD       = STR_HALFTONE + STR_OF + "方式" + STR_COLON_SPACE;
    String STR_PARAM                 = "パラメーター";
    String STR_HALFTONE_PARAM1       = STR_HALFTONE + STR_OF + STR_PARAM + "1" + STR_COLON_SPACE;
    String STR_HALFTONE_PARAM2       = STR_HALFTONE + STR_OF + STR_PARAM + "2" + STR_COLON_SPACE;
    String STR_NO_HALFTONE           = STR_HALFTONE + "なし";
    String STR_RANDOM_DITHER         = "誤差拡散法";
    String STR_PANDA                 = "PANDA";
    String STR_SUPER_CIRCLE          = "Super Circle";
    String STR_ENCODING              = "符号化方式" + STR_COLON_SPACE;
    String STR_RGB2_RGBQUAD          = "RGB2、RGBQUAD";
    String STR_PALLET                = "パレット方式";
    String STR_ID                    = "識別子" + STR_COLON_SPACE;
    String STR_COLOR_PALLET          = "【カラーパレット】";
    String STR_IMAGE_DATA            = "【画像データ】";
    String STR_SPACE_EQUAL_SPACE     = STR_SPACE + "=" + STR_SPACE;
    String STR_RGB_R                 = "R" + STR_SPACE_EQUAL_SPACE;
    String STR_RGB_G                 = "G" + STR_SPACE_EQUAL_SPACE;
    String STR_RGB_B                 = "B" + STR_SPACE_EQUAL_SPACE;
    String STR_COMMA_SPACE           = "," + STR_SPACE;
    String STR_COLOR_FORMAT          = "%3s";
    String STR_16BIT_FORMAT          = "%02X ";
    String STR_0X                    = "0x";
    String STR_COLOR_MASK            = "カラーマスク" + STR_COLON_SPACE;
    String STR_RED_MATERIAL          = "赤成分";
    String STR_GREEN_MATERIAL        = "緑成分";
    String STR_BLUE_MATERIAL         = "青成分";
    String STR_ALPHA_MATERIAL        = "α成分";
    String STR_RED_MASK              = STR_RED_MATERIAL + STR_OF + STR_COLOR_MASK;
    String STR_GREEN_MASK            = STR_GREEN_MATERIAL + STR_OF + STR_COLOR_MASK;
    String STR_BLUE_MASK             = STR_BLUE_MATERIAL + STR_OF + STR_COLOR_MASK;
    String STR_ALPHA_MASK            = STR_ALPHA_MATERIAL + STR_OF + STR_COLOR_MASK;
    String STR_CSTYPE                = "色空間";
    String STR_CSTYPE_HEADER         = "ヘッダ内で定義";
    String STR_GAMMA_VALUE           = "ガンマ値" + STR_COLON_SPACE;
    String STR_GAMMA_RED             = STR_RED_MATERIAL + STR_OF + STR_GAMMA_VALUE;
    String STR_GAMMA_GREEN           = STR_GREEN_MATERIAL + STR_OF + STR_GAMMA_VALUE;
    String STR_GAMMA_BLUE            = STR_BLUE_MATERIAL + STR_OF + STR_GAMMA_VALUE;
    String STR_CIEXYZTRIPLE          = "【CIEXYZTRIPLE構造体】";
    String STR_INTENT                = "レンダリングの意図" + STR_COLON_SPACE;
    String STR_PROFILE_DATA_OFFSET   = "プロファイルデータのオフセット" + STR_COLON_SPACE;
    String STR_PROFILE_DATA_SIZE     = "プロファイルデータのサイズ" + STR_COLON_SPACE;
    String STR_ALL                   = "全て";

    /**
     * <b>初期化</b>
     */
    public void clear();

    /**
     * @param data
     *            BMPのデータ
     */
    public void set(byte[] data);

    /**
     * @param bmp
     *            BMPのオブジェクト
     */
    public void set(BMP bmp);

    /**
     * @return BMPのデータ
     */
    public byte[] get();

    /**
     * @param ファイルヘッダのデータ
     */
    public int setFileHeader(byte[] data);

    /**
     * @return ファイルヘッダのデータ
     */
    public byte[] getFileHeader();

    /**
     * @param data
     *            情報ヘッダのデータ
     * @return オフセット
     */
    public int setInfoHeader(byte[] data);

    /**
     * @return 情報ヘッダのデータ
     */
    public byte[] getInfoHeader();

    /**
     * @param data
     *            カラーパレットのデータ
     */
    public void setColors(byte[] data);

    /**
     * @param data
     *            ビットマップデータ
     */
    public void setImage(byte[] data);

    /**
     * ファイルヘッダ と 情報ヘッダ
     * 
     * @return ヘッダ情報
     */
    public byte[] getBitmapHeader();

    /**
     * @return バージョン
     */
    public int getVersion();

    @Override
    public String toString();
}
