package file.format.bmp;

import file.io.BMP;

/**
 * <b>BMP </b><br>
 * date: 2017/10/20 last_date: 2017/10/23
 * 
 * @author ソウルP
 * @version 1.0 2017/10/20 BMPable作成
 */
public interface BMPable {
    int    FILE_HEADER_SIZE          = 14;                                  // ファイルヘッダサイズ
    int    INFO_HEADER_SIZE_V1       = 12;                                  // 情報ヘッダサイズ
    int    INFO_HEADER_SIZE_V2_MIN   = 16;                                  // 情報ヘッダサイズ 最小
    int    INFO_HEADER_SIZE_V2_MAX   = 64;                                  // 情報ヘッダサイズ 最大
    int    INFO_HEADER_SIZE_V3       = 40;                                  // 情報ヘッダサイズ
    int    INFO_HEADER_SIZE_V4       = 108;                                 // 情報ヘッダサイズ
    int    INFO_HEADER_SIZE_V5       = 124;                                 // 情報ヘッダサイズ
    String ERROR_FILE_OR             = "ファイルの破損もしくは、";
    String ERROR_IS_NOT_BMP          = ERROR_FILE_OR + "拡張子がBMPではありません。";
    String ERROR_INVALID_HEADER_SIZE = ERROR_FILE_OR + "情報ヘッダサイズが正しくありません。";
    String ERROR_UNSUPPORTED_VERSION = "対応されていないバージョンです。";

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
}
