package file.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import file.Tools;
import file.format.bmp.BMP_V1;
import file.format.bmp.BMP_V2;
import file.format.bmp.BMP_V3;
import file.format.bmp.BMP_V4;
import file.format.bmp.BMP_V5;
import file.format.bmp.BMPable;

/**
 * <b>BMP入出力</b><br>
 * date: 2017/10/12 last_date: 2017/10/24
 * 
 * @author ソウルP
 * @version 1.0 2017/10/12 BMP作成
 * @version 1.1 2017/10/18 各情報ヘッダに対応
 */
public class BMP {
    private BMPable      bmp;
    private byte[]       type;
    private int          fileSize;
    private int          imageOffset;
    private int          infoHeaderSize;
    private int          width;
    private int          height;
    private int          bitCount;
    private int          compression;
    private int          imageSize;
    private int          pixPerMeterX;
    private int          pixPerMeterY;
    private int          cirImportant;
    private byte[]       redMask;
    private byte[]       greenMask;
    private byte[]       blueMask;
    private byte[]       alphaMask;
    private byte[]       csType;
    private byte[]       ciexyzTriple;
    private float        gammaRed;
    private float        gammaGreen;
    private float        gammaBlue;
    private int          intent;
    private int          profileOffset;
    private int          profileSize;
    private int          headerSize;
    private int          hotspotX;
    private int          hotspotY;
    private int          resolution;
    private int          format;
    private int          halftone;
    private int          halftoneParam1;
    private int          halftoneParam2;
    private int          encoding;
    private int          id;

    private List<byte[]> colors;        // カラーパレット
    private List<byte[]> image;         // イメージ

    /**
     * <b>BMP</b>
     */
    public BMP() {
        colors = new ArrayList<>();
        image = new ArrayList<>();
        clear();
    }

    /**
     * 2 バイト
     * 
     * @return ファイルタイプ
     */
    public byte[] getType() {
        return type;
    }

    /**
     * 2 バイト
     * 
     * @param type
     *            ファイルタイプ
     */
    public void setType(byte[] type) {
        this.type = type;
    }

    /**
     * V1, V3 - V5
     * 
     * @return ファイルサイズ
     */
    public int getFileSize() {
        return fileSize;
    }

    /**
     * V1, V3 - V5
     * 
     * @param fileSize
     *            ファイルサイズ
     */
    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return 画像データへのオフセット
     */
    public int getImageOffset() {
        return imageOffset;
    }

    /**
     * @param imageOffset
     *            画像データへのオフセット
     */
    public void setImageOffset(int imageOffset) {
        this.imageOffset = imageOffset;
    }

    /**
     * @return 情報ヘッダのサイズ
     */
    public int getInfoHeaderSize() {
        return infoHeaderSize;
    }

    /**
     * @param infoHeaderSize
     *            情報ヘッダのサイズ
     */
    public void setInfoHeaderSize(int infoHeaderSize) {
        this.infoHeaderSize = infoHeaderSize;
    }

    /**
     * V1: 2 バイト<br>
     * V2 - V5: 4 バイト
     * 
     * @return 幅
     */
    public int getWidth() {
        return width;
    }

    /**
     * V1: 2 バイト<br>
     * V2 - V5: 4 バイト
     * 
     * @param width
     *            幅
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * V1: 2 バイト<br>
     * V2 - V5: 4 バイト
     * 
     * @return 高さ
     */
    public int getHeight() {
        return height;
    }

    /**
     * V1: 2 バイト<br>
     * V2 - V5: 4 バイト
     * 
     * @param height
     *            高さ
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return ビット数
     */
    public int getBitCount() {
        return bitCount;
    }

    /**
     * @param bitCount
     *            ビット数
     */
    public void setBitCount(int bitCount) {
        this.bitCount = bitCount;
    }

    /**
     * V2 - V5
     * 
     * @return 圧縮形式
     */
    public int getCompression() {
        return compression;
    }

    /**
     * V2 - V5
     * 
     * @param compression
     *            圧縮形式
     */
    public void setCompression(int compression) {
        this.compression = compression;
    }

    /**
     * V2 - V5
     * 
     * @return 画像データのサイズ
     */
    public int getImageSize() {
        return imageSize;
    }

    /**
     * V2 - V5
     * 
     * @param imageSize
     *            画像データのサイズ
     */
    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }

    /**
     * V2 - V5
     * 
     * @return 水平方向の解像度
     */
    public int getPixPerMeterX() {
        return pixPerMeterX;
    }

    /**
     * V2 - V5
     * 
     * @param pixPerMeterX
     *            水平方向の解像度
     */
    public void setPixPerMeterX(int pixPerMeterX) {
        this.pixPerMeterX = pixPerMeterX;
    }

    /**
     * V2 - V5
     * 
     * @return 垂直方向の解像度
     */
    public int getPixPerMeterY() {
        return pixPerMeterY;
    }

    /**
     * V2 - V5
     * 
     * @param pixPerMeterY
     *            垂直方向の解像度
     */
    public void setPixPerMeterY(int pixPerMeterY) {
        this.pixPerMeterY = pixPerMeterY;
    }

    /**
     * V2 - V5
     * 
     * @return 使用する色数
     */
    public int getClrUsed() {
        return colors.size();
    }

    /**
     * V2 - V5<br>
     * <br>
     * 全てが必要な場合、値は0
     * 
     * @return 重要な色数
     */
    public int getCirImportant() {
        return cirImportant;
    }

    /**
     * V2 - V5<br>
     * <br>
     * 全てが必要な場合、値は0
     * 
     * @param cirImportant
     *            重要な色数
     */
    public void setCirImportant(int cirImportant) {
        this.cirImportant = cirImportant;
    }

    /**
     * V4 - V5<br>
     * <br>
     * 4 バイト
     * 
     * @return 赤成分のカラーマスク
     */
    public byte[] getRedMask() {
        return redMask;
    }

    /**
     * V4 - V5<br>
     * <br>
     * 4 バイト
     * 
     * @param redMask
     *            赤成分のカラーマスク
     */
    public void setRedMask(byte[] redMask) {
        this.redMask = redMask;
    }

    /**
     * V4 - V5<br>
     * <br>
     * 4 バイト
     * 
     * @return 緑成分のカラーマスク
     */
    public byte[] getGreenMask() {
        return greenMask;
    }

    /**
     * V4 - V5<br>
     * <br>
     * 4 バイト
     * 
     * @param greenMask
     *            緑成分のカラーマスク
     */
    public void setGreenMask(byte[] greenMask) {
        this.greenMask = greenMask;
    }

    /**
     * V4 - V5<br>
     * <br>
     * 4 バイト
     * 
     * @return 青成分のカラーマスク
     */
    public byte[] getBlueMask() {
        return blueMask;
    }

    /**
     * V4 - V5<br>
     * <br>
     * 4 バイト
     * 
     * @param blueMask
     *            青成分のカラーマスク
     */
    public void setBlueMask(byte[] blueMask) {
        this.blueMask = blueMask;
    }

    /**
     * V4 - V5<br>
     * <br>
     * 4 バイト
     * 
     * @return α成分のカラーマスク
     */
    public byte[] getAlphaMask() {
        return alphaMask;
    }

    /**
     * V4 - V5<br>
     * <br>
     * 4 バイト
     * 
     * @param alphaMask
     *            α成分のカラーマスク
     */
    public void setAlphaMask(byte[] alphaMask) {
        this.alphaMask = alphaMask;
    }

    /**
     * V4 - V5<br>
     * <br>
     * ４ バイト
     * 
     * @return 色空間
     */
    public byte[] getCSType() {
        return csType;
    }

    /**
     * V4 - V5<br>
     * <br>
     * ４ バイト
     * 
     * @param csType
     *            色空間
     */
    public void setCSType(byte[] csType) {
        this.csType = csType;
    }

    /**
     * V4 - V5<br>
     * <br>
     * 36 バイト
     * 
     * @return CIEXYZTRIPLE構造体
     */
    public byte[] getCiexyzTriple() {
        return ciexyzTriple;
    }

    /**
     * V4 - V5<br>
     * <br>
     * 36 バイト
     * 
     * @param ciexyzTriple
     *            CIEXYZTRIPLE構造体
     */
    public void setCiexyzTriple(byte[] ciexyzTriple) {
        this.ciexyzTriple = ciexyzTriple;
    }

    /**
     * V4 - V5
     * 
     * @return 赤成分のガンマ値
     */
    public float getGammaRed() {
        return gammaRed;
    }

    /**
     * V4 - V5
     * 
     * @param gammaRed
     *            赤成分のガンマ値
     */
    public void setGammaRed(float gammaRed) {
        this.gammaRed = gammaRed;
    }

    /**
     * V4 - V5
     * 
     * @return 緑成分のガンマ値
     */
    public float getGammaGreen() {
        return gammaGreen;
    }

    /**
     * V4 - V5
     * 
     * @param gammaGreen
     *            緑成分のガンマ値
     */
    public void setGammaGreen(float gammaGreen) {
        this.gammaGreen = gammaGreen;
    }

    /**
     * V4 - V5
     * 
     * @return 青成分のガンマ値
     */
    public float getGammaBlue() {
        return gammaBlue;
    }

    /**
     * V4 - V5
     * 
     * @param gammaBlue
     *            青成分のガンマ値
     */
    public void setGammaBlue(float gammaBlue) {
        this.gammaBlue = gammaBlue;
    }

    /**
     * V5
     * 
     * @return レンダリングの意図
     */
    public int getIntent() {
        return intent;
    }

    /**
     * V5
     * 
     * @param intent
     *            レンダリングの意図
     */
    public void setIntent(int intent) {
        this.intent = intent;
    }

    /**
     * V5
     * 
     * @return プロファイルデータのオフセット
     */
    public int getProfileOffset() {
        return profileOffset;
    }

    /**
     * V5
     * 
     * @param profileOffset
     *            プロファイルデータのオフセット
     */
    public void setProfileOffset(int profileOffset) {
        this.profileOffset = profileOffset;
    }

    /**
     * V5
     * 
     * @return プロファイルデータのサイズ
     */
    public int getProfileSize() {
        return profileSize;
    }

    /**
     * V5
     * 
     * @param profileSize
     *            プロファイルデータのサイズ
     */
    public void setProfileSize(int profileSize) {
        this.profileSize = profileSize;
    }

    /**
     * V2
     * 
     * @return ファイルヘッダと情報ヘッダの合計サイズ
     */
    public int getHeaderSize() {
        return headerSize;
    }

    /**
     * V2
     * 
     * @param headerSize
     *            ファイルヘッダと情報ヘッダの合計サイズ
     */
    public void setHeaderSize(int headerSize) {
        this.headerSize = headerSize;
    }

    /**
     * V2
     * 
     * @return ポインタのホットスポットの x 座標
     */
    public int getHotspotX() {
        return hotspotX;
    }

    /**
     * V2
     * 
     * @param hotspotX
     *            ポインタのホットスポットの x 座標
     */
    public void setHotspotX(int hotspotX) {
        this.hotspotX = hotspotX;
    }

    /**
     * V2
     * 
     * @return ポインタのホットスポットの y 座標
     */
    public int getHotspotY() {
        return hotspotY;
    }

    /**
     * V2
     * 
     * @param hotspotY
     *            ポインタのホットスポットの y 座標
     */
    public void setHotspotY(int hotspotY) {
        this.hotspotY = hotspotY;
    }

    /**
     * V2
     * 
     * @return 解像度の単位
     */
    public int getResolution() {
        return resolution;
    }

    /**
     * V2
     * 
     * @param resolution
     *            解像度の単位
     */
    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    /**
     * V2
     * 
     * @return 記録方式
     */
    public int getFormat() {
        return format;
    }

    /**
     * V2
     * 
     * @param format
     *            記録方式
     */
    public void setFormat(int format) {
        this.format = format;
    }

    /**
     * V2
     * 
     * @return ハーフトーンの方式
     */
    public int getHaltone() {
        return halftone;
    }

    /**
     * V2
     * 
     * @param halftone
     *            ハーフトーンの方式
     */
    public void setHalftone(int halftone) {
        this.halftone = halftone;
    }

    /**
     * V2
     * 
     * @return ハーフトーン時のパラメータ1
     */
    public int getHalftoneParam1() {
        return halftoneParam1;
    }

    /**
     * V2
     * 
     * @param halftoneParam1
     *            ハーフトーン時のパラメータ1
     */
    public void setHalftoneParam1(int halftoneParam1) {
        this.halftoneParam1 = halftoneParam1;
    }

    /**
     * V2
     * 
     * @return ハーフトーン時のパラメータ2
     */
    public int getHalftoneParam2() {
        return halftoneParam2;
    }

    /**
     * V2
     * 
     * @param halftoneParam2
     *            ハーフトーン時のパラメータ2
     */
    public void setHalftoneParam2(int halftoneParam2) {
        this.halftoneParam2 = halftoneParam2;
    }

    /**
     * V2
     * 
     * @return 符号化方式
     */
    public int getEncoding() {
        return encoding;
    }

    /**
     * V2
     * 
     * @param encoding
     *            符号化方式
     */
    public void setEncoding(int encoding) {
        this.encoding = encoding;
    }

    /**
     * V2
     * 
     * @return 識別子
     */
    public int getId() {
        return id;
    }

    /**
     * V2
     * 
     * @param id
     *            識別子
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return カラーパレット
     */
    public List<byte[]> getColors() {
        return colors;
    }

    /**
     * @param colors
     *            カラーパレット
     */
    public void setColors(List<byte[]> colors) {
        this.colors = colors;
    }

    /**
     * <b>カラーパレット 全消去</b>
     */
    public void clearColors() {
        colors.clear();
    }

    /**
     * <b>カラーパレットに色追加</b>
     * 
     * @param r
     *            赤 0 - 255
     * @param g
     *            緑 0 - 255
     * @param b
     *            青 0 - 255
     */
    public void addColor(int r, int g, int b) {
        byte[] color = { (byte) b, (byte) g, (byte) r, 0x00 };
        colors.add(color);
    }

    /**
     * Listが縦、byte[]が横
     * 
     * @return ビットマップデータ
     */
    public List<byte[]> getImage() {
        return image;
    }

    /**
     * Listが縦、byte[]が横
     * 
     * @param image
     *            ビットマップデータ
     */
    public void setImage(List<byte[]> image) {
        this.image = image;
    }

    /**
     * 1次元目は縦、2次元目は横
     * 
     * @param image
     *            ビットマップデータ
     */
    public void setImage(byte[][] image) {
        this.image.clear();
        for (byte[] i : image)
            this.image.add(i);
    }

    /**
     * <b>画像データ 消去</b>
     */
    public void clearImage() {
        image.clear();
    }

    /**
     * <b>入力</b>
     * 
     * @param file
     *            ファイル先
     * @throws IOException
     */
    public void input(String file) throws IOException {
        clear();
        FileInputStream in = null;
        in = new FileInputStream(file);
        int fileSize = in.available();
        byte[] data = new byte[fileSize];
        in.read(data);
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                in = null;
            }
        }

        int infoHeaderSize = Tools.bytes2int(Tools.subbytes(data, 0x000E, 0x000E + 4));
        switch (infoHeaderSize) {
            case BMPable.INFO_HEADER_SIZE_V3:
                bmp = new BMP_V3(data);
                break;
            case BMPable.INFO_HEADER_SIZE_V4:
                bmp = new BMP_V4(data);
                break;
            case BMPable.INFO_HEADER_SIZE_V5:
                bmp = new BMP_V5(data);
                break;
            case BMPable.INFO_HEADER_SIZE_V1:
                bmp = new BMP_V1(data);
                break;
            default:
                if (infoHeaderSize >= BMPable.INFO_HEADER_SIZE_V2_MIN
                        && infoHeaderSize <= BMPable.INFO_HEADER_SIZE_V2_MAX) {
                    bmp = new BMP_V2(data);
                } else {
                    bmp = null;
                    throw new IOException(BMPable.ERROR_INVALID_HEADER_SIZE);
                }
                break;
        }
        BMP_V1 b1 = (BMP_V1) bmp;
        setType(b1.getType());
        setFileSize(b1.getFileSize());
        setImageOffset(b1.getOffset());
        setInfoHeaderSize(b1.getInfoHeaderSize());
        setWidth(b1.getWidthV1());
        setHeight(b1.getHeightV1());
        setBitCount(b1.getBitCount());
        setColors(b1.getColors());
        setImage(b1.getImage());
        if (bmp.getVersion() >= 2) {
            BMP_V3 b3 = (BMP_V3) bmp;
            setWidth(b3.getWidth());
            setHeight(b3.getHeight());
            setCompression(b3.getCompression());
            setImageSize(b3.getSizeImage());
            setPixPerMeterX(b3.getXPelsPerMeter());
            setPixPerMeterY(b3.getYPelsPerMeter());
            setCirImportant(b3.getCirImportant());
            if (bmp.getVersion() >= 3) {
                setInfoHeaderSize(b3.getInfoHeaderSize());
                if (bmp.getVersion() >= 4) {
                    BMP_V4 b4 = (BMP_V4) bmp;
                    setRedMask(b4.getRedMask());
                    setGreenMask(b4.getGreenMask());
                    setBlueMask(b4.getBlueMask());
                    setAlphaMask(b4.getAlphaMask());
                    setCSType(b4.getCSType());
                    setCiexyzTriple(b4.getEndpoints());
                    setGammaRed(b4.getGammaRed());
                    setGammaGreen(b4.getGammaGreen());
                    setGammaBlue(b4.getGammaBlue());
                    if (bmp.getVersion() == 5) {
                        BMP_V5 b5 = (BMP_V5) bmp;
                        setIntent(b5.getIntent());
                        setProfileOffset(b5.getProfileData());
                        setProfileSize(b5.getProfileSize());
                    }
                }
            } else {
                BMP_V2 b2 = (BMP_V2) bmp;
                setHeaderSize(b2.getHeaderSize());
                setHotspotX(b2.getHotspotX());
                setHotspotY(b2.getHotspotY());
                setResolution(b2.getResolution());
                setFormat(b2.getFormat());
                setHalftone(b2.getHalftone());
                setHalftoneParam1(b2.getHalftoneParam1());
                setHalftoneParam2(b2.getHalftoneParam2());
                setEncoding(b2.getEncoding());
                setId(b2.getId());
            }
        }
    }

    /**
     * <b>出力</b>
     * 
     * @param file
     *            ファイル先
     * @throws IOException
     */
    public void output(String file) throws IOException {
        output(file, 3);
    }

    /**
     * <b>出力</b>
     * 
     * @param file
     *            ファイル先
     * @param version
     *            バージョン
     * @throws IOException
     */
    public void output(String file, int version) throws IOException {
        if (version != 2) {
            switch (version) {
                case 1:
                    infoHeaderSize = BMPable.INFO_HEADER_SIZE_V1;
                    break;
                case 3:
                    infoHeaderSize = BMPable.INFO_HEADER_SIZE_V3;
                    break;
                case 4:
                    infoHeaderSize = BMPable.INFO_HEADER_SIZE_V4;
                    break;
                case 5:
                    infoHeaderSize = BMPable.INFO_HEADER_SIZE_V5;
                    break;
                default:
                    throw new IOException(BMPable.ERROR_UNSUPPORTED_VERSION);
            }
        } else {
            if (infoHeaderSize < BMPable.INFO_HEADER_SIZE_V2_MIN) infoHeaderSize = BMPable.INFO_HEADER_SIZE_V2_MIN;
            if (infoHeaderSize > BMPable.INFO_HEADER_SIZE_V2_MAX) infoHeaderSize = BMPable.INFO_HEADER_SIZE_V2_MAX;
        }
        imageSize = 0;
        for (byte[] img : image)
            imageSize += img.length;
        imageOffset = BMPable.FILE_HEADER_SIZE + infoHeaderSize + colors.size() * ((version == 1) ? 3 : 4);
        fileSize = imageOffset + imageSize;

        switch (version) {
            case 1:
                bmp = new BMP_V1(this);
                break;
            case 2:
                bmp = new BMP_V2(this);
                break;
            case 3:
                bmp = new BMP_V3(this);
                break;
            case 4:
                bmp = new BMP_V4(this);
                break;
            case 5:
                bmp = new BMP_V5(this);
                break;
            default:
                throw new IOException(BMPable.ERROR_UNSUPPORTED_VERSION);
        }

        FileOutputStream out = null;
        out = new FileOutputStream(file);
        out.write(bmp.get());
        out.flush();
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                out = null;
            }
        }
    }

    /**
     * <b>初期化</b>
     */
    public void clear() {
        bmp = new BMP_V3();
        type = new byte[] { 0x42, 0x4D };
        fileSize = 0;
        imageOffset = 0;
        infoHeaderSize = 0;
        width = 0;
        height = 0;
        bitCount = 0;
        compression = 0;
        imageSize = 0;
        pixPerMeterX = 0;
        pixPerMeterY = 0;
        cirImportant = 0;
        redMask = ByteBuffer.allocate(4).putInt(0).array();
        greenMask = ByteBuffer.allocate(4).putInt(0).array();
        blueMask = ByteBuffer.allocate(4).putInt(0).array();
        alphaMask = ByteBuffer.allocate(4).putInt(0).array();
        csType = ByteBuffer.allocate(4).putInt(0).array();
        ciexyzTriple = ByteBuffer.allocate(32).putLong(0).putLong(0).putLong(0).putLong(0).array();
        gammaRed = 0.0f;
        gammaGreen = 0.0f;
        gammaBlue = 0.0f;
        intent = 0;
        profileOffset = 0;
        profileSize = 0;
        headerSize = 0;
        hotspotX = 0;
        hotspotY = 0;
        resolution = 0;
        format = 0;
        halftone = 0;
        halftoneParam1 = 0;
        halftoneParam2 = 0;
        encoding = 0;
        id = 0;
        colors.clear();
        image.clear();
    }

    /**
     * @return バージョン
     */
    public int getVersion() {
        return bmp.getVersion();
    }

    @Override
    public String toString() {
        return bmp.toString();
    }
}
