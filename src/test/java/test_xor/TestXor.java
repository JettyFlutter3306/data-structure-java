package test_xor;

import cn.element.datastructure.algorithm.xor.SwapByXOR;
import org.junit.Test;

public class TestXor {
    
    @Test
    public void testXorNegation() {
        int a = 0b00110101;
        String b = Integer.toBinaryString(-a);
        System.out.println(b);  // 11001011
    }
    
    @Test
    public void testFindTwoNumbersOddTimes() {
        SwapByXOR.findTheTwoNumbersOddTimes();
    }

}
