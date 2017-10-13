package file.io;

import java.nio.ByteBuffer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * <b>ツール</b><br>
 * 変換など<br>
 * date: 2017/10/13 last_date: 2017/10/13
 * 
 * @author ソウルP
 * @version 1.0 2017/10/13 Tools作成
 */
public interface Tools {
    Function<byte[], byte[]>            ENDIAN    = e -> {
                                                      byte[] b = new byte[e.length];
                                                      for (int i = 0; i < e.length; i++)
                                                          b[i] = e[e.length - 1 - i];
                                                      return b;
                                                  };

    BiFunction<byte[], Integer, byte[]> INPUT_INT = (var, val) -> {
                                                      return ENDIAN.apply(
                                                              ByteBuffer.allocate(var.length).putInt(val).array());
                                                  };
}
