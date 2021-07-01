package cn.element.util;

import java.io.*;

/**
 * 序列化工具类
 */
public class SerializeUtil {

    private static OutputStream os;

    /**
     * 序列化
     */
    public static <T> void serialize(T obj) throws IOException {

        //将对象写到流里面
        os = new ByteArrayOutputStream();

        ObjectOutputStream oo = new ObjectOutputStream(os);

        oo.writeObject(obj);
    }

    /**
     * 反序列化
     */
    public static <T> T deserialize(OutputStream os) throws IOException, ClassNotFoundException {

        //从流里面读取出来
        InputStream is = new ByteArrayInputStream(((ByteArrayOutputStream) os).toByteArray());

        ObjectInputStream oi = new ObjectInputStream(is);

        T obj = (T) oi.readObject();

        os.close();
        is.close();
        oi.close();

        return obj;
    }

    /**
     * 使用序列化和反序列化实现深拷贝
     */
    public static <T> T deepClone(T obj) throws IOException, ClassNotFoundException {

        serialize(obj);

        return deserialize(os);
    }


}
