package file.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 共通化 (テスト用)<br>
 * date: 2017/11/14 last_date: 2017/11/14
 * 
 * @author ソウルP
 */
public abstract class Basic extends Settings {
    // イメージ
    public static List<byte[]> image_1bit;
    public static List<byte[]> image_4bit;
    public static List<byte[]> image_8bit;
    public static List<byte[]> image_16bit;
    public static List<byte[]> image_16bit_BitFields;
    public static List<byte[]> image_24bit;
    public static List<byte[]> image_32bit;
    public static List<byte[]> image_32bit_BitFields;
    public static byte[][]     image_4bit_BI_RLE4;
    public static byte[][]     image_8bit_BI_RLE8;

    // カラーマスク
    public static byte[][]     r5g5b5MaskV3;
    public static byte[][]     r5g5b5MaskV4;
    public static byte[][]     bitFields32bitMaskV3;
    public static byte[][]     bitFields32bitMaskV4;

    static {
        image_1bit = new ArrayList<>();
        image_1bit.add(new byte[] { (byte) 0xa0, 0x00, 0x00, 0x00 });
        image_1bit.add(new byte[] { 0x40, 0x00, 0x00, 0x00 });
        image_1bit.add(new byte[] { (byte) 0xa0, 0x00, 0x00, 0x00 });
    }

    static {
        image_4bit = new ArrayList<>();
        image_4bit.add(new byte[] { 0x10, 0x10, 0x00, 0x00 });
        image_4bit.add(new byte[] { 0x01, 0x00, 0x00, 0x00 });
        image_4bit.add(new byte[] { 0x10, 0x10, 0x00, 0x00 });
    }

    static {
        image_8bit = new ArrayList<>();
        image_8bit.add(new byte[] { 0x01, 0x00, 0x01, 0x00 });
        image_8bit.add(new byte[] { 0x00, 0x01, 0x00, 0x00 });
        image_8bit.add(new byte[] { 0x01, 0x00, 0x01, 0x00 });
    }

    static {
        // @formatter:off
        // リトルエンディアン
        image_16bit = new ArrayList<>();
        image_16bit.add(new byte[] {
                (byte) 0xff, 0x07,
                0x1f, (byte) 0xf8,
                (byte) 0xff, 0x07,
                0x00, 0x00
            }
        );
        image_16bit.add(new byte[] {
                0x1f, 0x7c,
                (byte) 0xff, 0x07,
                0x1f, 0x7c,
                0x00, 0x00
            }
        );
        image_16bit.add(new byte[] {
                (byte) 0xff, 0x07,
                0x1f, (byte) 0xf8,
                (byte) 0xff, 0x07,
                0x00, 0x00
            }
        );
     // @formatter:on
    }

    static {
        // @formatter:off
        // リトルエンディアン
        image_16bit_BitFields = new ArrayList<>();
        image_16bit_BitFields.add(new byte[] {
                (byte) 0xe0, (byte) 0x7f,
                (byte) 0x1f, 0x7c,
                (byte) 0xe0, (byte) 0x7f,
                0x00, 0x00
            }
        );
        image_16bit_BitFields.add(new byte[] {
                (byte) 0x1f, 0x7c,
                (byte) 0xe0, (byte) 0x7f,
                (byte) 0x1f, 0x7c,
                0x00, 0x00
            }
        );
        image_16bit_BitFields.add(new byte[] {
                (byte) 0xe0, (byte) 0x7f,
                (byte) 0x1f, 0x7c,
                (byte) 0xe0, (byte) 0x7f,
                0x00, 0x00
            }
        );
        // @formatter:on
    }

    static {
        // @formatter:off
        image_24bit = new ArrayList<>();
        image_24bit.add(new byte[] {
                (byte) 0xff, (byte) 0xff, 0x00, (byte) 0xff,
                0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff,
                0x00, 0x00, 0x00, 0x00
            }
        );
        image_24bit.add(new byte[] {
                (byte) 0xff, 0x00, (byte) 0xff, (byte) 0xff,
                (byte) 0xff, 0x00, (byte) 0xff, 0x00,
                (byte) 0xff, 0x00, 0x00, 0x00
            }
        );
        image_24bit.add(new byte[] {
                (byte) 0xff, (byte) 0xff, 0x00, (byte) 0xff,
                0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff,
                0x00, 0x00, 0x00, 0x00
            }
        );
        // @formatter:on
    }

    static {
        // @formatter:off
        image_32bit = new ArrayList<>();
        image_32bit.add(new byte[] {
                (byte) 0xff, (byte) 0xff, 0x00, 0x00,
                (byte) 0xff, 0x00, (byte) 0xff, 0x00,
                (byte) 0xff, (byte) 0xff, 0x00, 0x00
            }
        );
        image_32bit.add(new byte[] {
                (byte) 0xff, 0x00, (byte) 0xff, 0x00,
                (byte) 0xff, (byte) 0xff, 0x00, 0x00,
                (byte) 0xff, 0x00, (byte) 0xff, 0x00
            }
        );
        image_32bit.add(new byte[] {
                (byte) 0xff, (byte) 0xff, 0x00, 0x00,
                (byte) 0xff, 0x00, (byte) 0xff, 0x00,
                (byte) 0xff, (byte) 0xff, 0x00, 0x00
            }
        );
        // @formatter:on
    }

    static {
        // @formatter:off
        image_32bit_BitFields = new ArrayList<>();
        image_32bit_BitFields.add(new byte[] {
                0x00, 0x0f, (byte) 0xf0, (byte) 0xff,
                (byte) 0xff, 0x00, 0x00, (byte) 0xff,
                0x00, 0x0f, (byte) 0xf0, (byte) 0xff
            }
        );
        image_32bit_BitFields.add(new byte[] {
                (byte) 0xff, 0x00, 0x00, (byte) 0xff,
                0x00, 0x0f, (byte) 0xf0, (byte) 0xff,
                (byte) 0xff, 0x00, 0x00, (byte) 0xff
            }
        );
        image_32bit_BitFields.add(new byte[] {
                0x00, 0x0f, (byte) 0xf0, (byte) 0xff,
                (byte) 0xff, 0x00, 0x00, (byte) 0xff,
                0x00, 0x0f, (byte) 0xf0, (byte) 0xff
            }
        );
        // @formatter:on
    }

    static {
        // @formatter:off
        // イメージ情報
        image_4bit_BI_RLE4 = new byte[][]{
            { 0x0e, (byte) 0xdd, 0x00, 0x00 },
            { 0x0e, (byte) 0xcc, 0x00, 0x00 },
            { 0x02, (byte) 0xbb, 0x0a, 0x22, 0x02, (byte) 0xbb, 0x00, 0x00 },
            { 0x02, (byte) 0xaa, 0x01, 0x30, 0x08, (byte) 0xaa, 0x01, 0x30, 0x02, (byte) 0xaa, 0x00, 0x00 },
            { 0x02, (byte) 0x99, 0x02, 0x49, 0x06, 0x44, 0x02, (byte) 0x94, 0x02, (byte) 0x99, 0x00, 0x00 },
            { 0x02, (byte) 0x88, 0x03, 0x58, 0x04, (byte) 0x88, 0x03, 0x58, 0x02, (byte) 0x88, 0x00, 0x00 },
            { 0x02, 0x77, 0x04, 0x67, 0x02, 0x66, 0x04, 0x76, 0x02, 0x77, 0x00, 0x00 },
            { 0x02, 0x66, 0x04, (byte) 0x76, 0x02, (byte) 0x77, 0x04, 0x67, 0x02, 0x66, 0x00, 0x00 },
            { 0x02, 0x55, 0x03, (byte) 0x85, 0x04, 0x55, 0x03, (byte) 0x85, 0x02, 0x55, 0x00, 0x00 },
            { 0x02, 0x44, 0x02, (byte) 0x94, 0x06, (byte) 0x99, 0x02, 0x49, 0x02, 0x44, 0x00, 0x00 },
            { 0x02, 0x33, 0x01, (byte) 0xa0, 0x08, 0x33, 0x01, (byte) 0xa0, 0x02, 0x33, 0x00, 0x00 },
            { 0x02, 0x22, 0x0a, (byte) 0xbb, 0x02, 0x22, 0x00, 0x00 },
            { 0x0e, 0x11, 0x00, 0x00 },
            { 0x0e, 0x00, 0x00, 0x01 }
        };
        // @formatter:on
    }

    static {
        // @formatter:off
        image_8bit_BI_RLE8 = new byte[][] {
            { 0x0d, 0x28, 0x00, 0x00 },
            { 0x03, 0x28, 0x0a, 0x00, 0x00, 0x00 },
            { 0x08, 0x00, 0x05, 0x27, 0x00, 0x00 },
            { 0x0d, 0x27, 0x00, 0x00 },
            { 0x00, 0x0d, 0x1a, 0x1b, 0x1c, 0x1d, 0x1e, 0x1f, 0x20, 0x21, 0x22, 0x23, 0x24, 0x25, 0x26, 0x00, 0x00, 0x00 },
            { 0x00, 0x0d, 0x0d, 0x0e, 0x0f, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19, 0x00, 0x00, 0x00 },
            { 0x00, 0x0d, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x00, 0x00, 0x00 },
            { 0x0d, 0x2a, 0x00, 0x00 },
            { 0x0d, 0x29, 0x00, 0x00 },
            { 0x06, 0x29, 0x01, 0x2a, 0x06, 0x29, 0x00, 0x00 },
            { 0x0d, 0x29, 0x00, 0x00 },
            { 0x02, 0x29, 0x01, 0x2a, 0x07, 0x29, 0x01, 0x2a, 0x02, 0x29, 0x00, 0x00 },
            { 0x0d, 0x29, 0x00, 0x01 }
        };
        // @formatter:on
    }

    static {
        // @formatter:off
        // ビッグエンディアン （マスク入れる時はリトルエンディアン）
        r5g5b5MaskV3 = new byte[][] {
                { 0x00, 0x00, 0x7c, 0x00 },         // 赤マスク
                { 0x00, 0x00, 0x03, (byte) 0xe0 },  // 緑マスク
                { 0x00, 0x00, 0x00, 0x1f }          // 青マスク
        };
        // @formatter:on
    }

    static {
        // @formatter:off
        /*
         * ビッグエンディアン （マスク入れる時はリトルエンディアン）
         * V3は青から赤へ処理されるが、V4以上は情報ヘッダに各色マスクがあるので、
         * 赤から青へ処理される
         * V3 = BGRA
         * V4以上 = RGBA
         */
        r5g5b5MaskV4 = new byte[][] {
                { 0x00, 0x00, 0x00, 0x1f },         // 赤マスク
                { 0x00, 0x00, 0x03, (byte) 0xe0 },  // 緑マスク
                { 0x00, 0x00, 0x7c, 0x00 }          // 青マスク
        };
        // @formatter:on
    }

    static {
        // @formatter:off
        // ビッグエンディアン （マスク入れる時はリトルエンディアン）
        // ビットフィールドのカラーマスク
        /*
         * ビッグエンディアンだが、緑マスクの0x000FF000の場合、
         * ビッグエンディアンからリトルエンディアン変換でバイト毎として 0f f0 と分かれる為、
         * リトルエンディアンに変換される時は、 f0 0f となってしまい、
         * 正しくない緑マスクに設定されので、予め、その一部だけリトルエンディアンにしておくと良い。
         * 
         * 要するに、バイト単位はビッグエンディアン、ビット単位はリトルエンディアンとして値を入れると良い
         * 【例え】
         * 0xff000000 → 0xff000000
         * 0x0ff00000 → 0xf00f0000 と カラーマスク値を入れる
         */
        bitFields32bitMaskV3 = new byte[][] {
                { (byte) 0xff, 0x00, 0x00, 0x00 },  // 赤マスク
                { 0x00, (byte) 0xf0, 0x0f,  0x00 }, // 緑マスク
                { 0x00, 0x00, 0x00, (byte) 0xff }   // 青マスク
        };
        // @formatter:on
    }

    static {
        // @formatter:off
        // ビッグエンディアン
        // ビットフィールドのカラーマスク
        /*
         * 情報ヘッダの中に各色マスク格納できるので、
         * ビッグエンディアンのままでOK
         */
        bitFields32bitMaskV4 = new byte[][] {
                { (byte) 0xff, 0x00, 0x00, 0x00 },  // 赤マスク
                { 0x00, 0x0f, (byte) 0xf0,  0x00 }, // 緑マスク
                { 0x00, 0x00, 0x00, (byte) 0xff }   // 青マスク
        };
        // @formatter:on
    }
}
