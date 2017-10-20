package file.format.bmp;

/**
 * <b>BMP </b><br>
 * date: 2017/10/20 last_date: 2017/10/20
 * 
 * @author ソウルP
 * @version 1.0 2017/10/20 BMPable作成
 */
public interface BMPable {
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
     * @return BMPのデータ
     */
    public byte[] get();

    /**
     * @param ファイルヘッダのデータ
     */
    public void setFileHeader(byte[] data);

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
}
