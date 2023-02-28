package test_string;

import org.junit.Test;
import org.codeart.datastructure.string.IStringBuffer;

public class TestMyStringBuffer {

    @Test
    public void testStringBuffer(){
        IStringBuffer sbuf = new IStringBuffer(8);

        System.out.println("空串, \""+sbuf+"\", length()="+sbuf.length()+", capacity()="+sbuf.capacity());

        sbuf.insert(0,"abcdef");

        System.out.println("插入, \""+sbuf+"\", length()="+sbuf.length()+", capacity()="+sbuf.capacity());

        String[] str = {"xy",null};

        int i = 2;

        for(int j = 0;j < str.length;j++){
            System.out.println(
                    "插入, \"" + sbuf + "\".insert("+i+",\"" + str[j]+"\")=\"" + sbuf.insert(i,str[j]) + "\", length()=" +
                    sbuf.length()+", capacity()="+sbuf.capacity());
        }

        int[] begin = {2,4,2}, end = {6,10,2};

        for(int j = 0;j < begin.length;j++){
            System.out.println(
                    "删除, \"" + sbuf + "\".delete("+begin[j] + "," + end[j] + ")=\"" + sbuf.delete(begin[j],end[j]) + "\", length()="+
                    sbuf.length() + ", capacity()="+sbuf.capacity());
        }


    }
}
