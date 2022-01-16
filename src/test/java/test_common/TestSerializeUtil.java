package test_common;

import cn.element.datastructure.util.SerializeUtil;
import org.junit.Test;

import java.io.IOException;

public class TestSerializeUtil {

    /**
     * 测试序列化和反序列化
     */
    @Test
    public void test01() throws IOException, ClassNotFoundException {

        int[] array1 = new int[]{1,2,3,4,5,6};

        int[] array2 = (int[]) SerializeUtil.deepClone(array1);

        System.out.println(array1 == array2);
    }
}
