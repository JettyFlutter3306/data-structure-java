package test_search;

import cn.element.datastructure.hash.HashMap;
import cn.element.datastructure.common.Map;
import org.junit.Test;

import java.util.Arrays;

public class TestHashMap {

    /**
     * 测试创建HashMap
     */
    @Test
    public void test01(){

        HashMap<String,Integer> map = new HashMap<>();

        map.put("A",1);
        map.put("B",3);
        map.put("C",15);
        map.put("D",89);
        map.put("E",14);
        map.put("F",31);

        System.out.println(map.containsKey("A"));
        System.out.println(Arrays.toString(map.values()));
        System.out.println(map.get("A"));
        System.out.println(map.get("E"));
        System.out.println(map.get("asdcsadc"));
        System.out.println(map.size());
        System.out.println(map.keySet());
        System.out.println(map.remove("D"));
    }

    /**
     * 测试HashMap记录字符出现的次数
     */
    @Test
    public void test02(){

        Map<String,Integer> map = new HashMap<>(10);

        String text = "ascbsadcvbsvdvbasjvcasc";

        for (int i = 0; i < text.length(); i++) {
            String key = text.substring(i,i+1);

            if(map.containsKey(key)){
                map.put(key,map.get(key) + 1);
            }else{
                map.put(key,1);
            }
        }

        System.out.println(map);  //[((d,2)),(),(),(),(),((s,5)),((j,1)),((a,4)),((b,3),(v,4)),((c,4)),]


    }
}
