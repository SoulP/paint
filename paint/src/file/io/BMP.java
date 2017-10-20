package file.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import file.Tools;
import file.format.bmp.BMP_V3;

/**
 * <b>BMP入出力</b><br>
 * date: 2017/10/12 last_date: 2017/10/20
 * 
 * @author ソウルP
 * @version 1.0 2017/10/12 BMP作成
 * @version 1.1 2017/10/18 各情報ヘッダに対応
 */
public class BMP extends BMP_V3 {

    /**
     * <b>BMP</b>
     */
    public BMP() {
        clear();
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
        try {
            in = new FileInputStream(file);
            int fileSize = in.available();
            byte[] data = new byte[fileSize];
            in.read(data);

            int offset = 0;
            byte[] bm = Tools.subbytes(data, offset, offset += bfType.length);
            if (!Arrays.equals(bm, bfType)) throw new IOException(ERROR_IS_NOT_BMP);
            offset += bfSize.length;
            offset += BF_RESERVED_1.length;
            offset += BF_RESERVED_2.length;
            int imageOffset = Tools.bytes2int(Tools.subbytes(data, offset, offset += 4));
            bcSize = Tools.subbytes(data, offset, offset += 4);
            int infoHSize = Tools.bytes2int(bcSize);
            if (infoHSize == 12) {
                setWidth(Tools.bytes2short(Tools.subbytes(data, offset, offset += 2)));
                setHeight(Tools.bytes2short(Tools.subbytes(data, offset, offset += 2)));
                offset += BC_PLANES.length;
                setBitCount(Tools.bytes2short(Tools.subbytes(data, offset, offset += 2)));
                for (int i = offset; i < imageOffset; i += 3) {
                    int r = Byte.toUnsignedInt(data[i + 2]);
                    int g = Byte.toUnsignedInt(data[i + 1]);
                    int b = Byte.toUnsignedInt(data[i]);
                    addColor(r, g, b);
                }
            } else {
                setWidth(Tools.bytes2int(Tools.subbytes(data, offset, offset += 4)));
                setHeight(Tools.bytes2int(Tools.subbytes(data, offset, offset += 4)));
                offset += BC_PLANES.length;
                setBitCount(Tools.bytes2short(Tools.subbytes(data, offset, offset += 2)));
                setCompression(Tools.bytes2int(Tools.subbytes(data, offset, offset += 4)));
                biSizeImage = Tools.subbytes(data, offset, offset += 4);
                setXPelsPerMeter(Tools.bytes2int(Tools.subbytes(data, offset, offset += 4)));
                setYPelsPerMeter(Tools.bytes2int(Tools.subbytes(data, offset, offset += 4)));
                biClrUsed = Tools.subbytes(data, offset, offset += 4);
                int clrUsed = Tools.bytes2int(biClrUsed);
                setCirImportant(Tools.bytes2int(Tools.subbytes(data, offset, offset += 4)));
                if (clrUsed > 0) {
                    int colorSize = clrUsed;
                    for (int i = 0; i < colorSize; i++) {
                        int color = i * 4 + offset;
                        int r = Byte.toUnsignedInt(data[color + 2]);
                        int g = Byte.toUnsignedInt(data[color + 1]);
                        int b = Byte.toUnsignedInt(data[color]);
                        addColor(r, g, b);
                    }
                }
            }

            if (getCompression() == 1 || getCompression() == 2) {
                List<Byte> bytes = new ArrayList<>();
                byte[] byteArray;
                int endOffset = imageOffset + getSizeImage();
                for (int i = imageOffset; i < endOffset; i += 2) {
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
            } else {
                int tempW = 0;
                int bitCount = getBitCount();
                int width = getWidth();
                if (bitCount == 1) tempW = (width % 32 == 0) ? width : (32 - width % 32 + width) / 8;
                if (bitCount == 4) tempW = (width % 8 == 0) ? width : (8 - width % 8 + width) / 2;
                if (bitCount == 8) tempW = (width % 4 == 0) ? width : (4 - width % 4 + width);
                if (bitCount == 24) tempW = (width * 3 % 4 == 0) ? width : 4 - width * 3 % 4 + width * 3;
                if (bitCount == 32) tempW = width * 4;
                if (tempW <= 0) throw new IOException(ERROR_BITCOUNT);
                for (int i = imageOffset; i < fileSize; i += tempW) {
                    byte[] img = new byte[tempW];
                    for (int w = 0; w < tempW; w++) {
                        img[w] = data[i + w];
                    }
                    image.add(img);
                }
            }
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    in = null;
                }
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
        int offset = FILE_HEADER_SIZE + infoHeaderSize + colors.size() * 4;
        bfOffBits = Tools.int2bytes(offset);
        updateFileSize();

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            // ファイルヘッダ
            out.write(bfType);
            out.write(bfSize);
            out.write(BF_RESERVED_1);
            out.write(BF_RESERVED_2);
            out.write(bfOffBits);
            // 情報ヘッダ
            out.write(bcSize);
            out.write(bcWidth);
            out.write(bcHeight);
            out.write(BC_PLANES);
            out.write(bcBitCount);
            out.write(biCompression);
            out.write(biSizeImage);
            out.write(biXPelsPerMeter);
            out.write(biYPelsPerMeter);
            out.write(biClrUsed);
            out.write(biCirImportant);
            // カラーパレット
            if (!colors.isEmpty()) for (byte[] c : colors)
                out.write(c);
            // イメージ
            for (byte[] img : image)
                out.write(img);
            out.flush();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    out = null;
                }
            }
        }
    }

    /**
     * <b>初期化</b>
     */
    @Override
    public void clear() {
        super.clear();
        colors = new ArrayList<>();
        image = new ArrayList<>();
        clearColors();
    }
}
