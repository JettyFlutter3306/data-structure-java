package org.codeart.datastructure.string;

import java.io.Serializable;

/**
 * 变量字符换类,最终类,实现序列化接口
 */
public final class IStringBuffer implements Serializable {
    
    private char[] value;  //字符数组,私有成员变量

    private int n;      //串长度

    /**
     * 构造方法
     */
    public IStringBuffer(int capacity) {
        this.value = new char[capacity];
        this.n = 0;
    }

    /**
     * 以默认容量构造空串
     */
    public IStringBuffer() {
        this(16);
    }

    /**
     * 以字符串常量构造串
     */
    public IStringBuffer(String str) {
        this(str.length() + 16);
        this.n = str.length();

        //复制str串所有字符
        for (int i = 0; i < this.n; i++) {
            this.value[i] = str.charAt(i);
        }
    }

    /**
     * 返回字符串长度
     */
    public int length() {
        return this.n;
    }

    /**
     * 返回字符数组容量
     */
    public int capacity() {
        return this.value.length;
    }

    /**
     * 返回第i个字符
     */
    public synchronized char charAt(int i) {
        return this.value[i];
    }

    /**
     * 设置第i个字符为ch
     */
    public void setCharAt(int i, char ch) {
        this.value[i] = ch;
    }

    /**
     * 在第i个字符处插入str串,0<=i<length(),若i序号越界,抛出异常,若str==null,插入"null"
     * @param i             索引
     * @param str           待插入串
     * @return              返回新串
     */
    public synchronized IStringBuffer insert(int i, String str) {
        if (this.n == 0 && i == 0 || this.n > 0 && i >= 0 && i <= this.n) {
            if (str == null) {
                str = "null";
            }

            char[] temp = this.value;

            if (this.value.length < this.n + str.length()) { //若数组空间不足则扩容
                this.value = new char[(this.value.length + str.length()) * 2]; //重新申请字符数组空间

                //复制当前串前i-1个字符
                System.arraycopy(temp, 0, this.value, 0, i);
            }

            //从i开始至串尾的子串向后移动,次序从后向前
            for (int j = this.n - 1; j >= i; j--) {
                this.value[j + str.length()] = temp[j];
            }

            //插入str串
            for (int j = 0;j < str.length();j++) {
                this.value[i + j] = str.charAt(j);
            }

            this.n += str.length();

            return this;
        } else {
            throw new StringIndexOutOfBoundsException("i="+i);
        }
    }

    /**
     * 在i处插入变量值转换成的串
     * @param i     索引
     * @param b     布尔
     * @return      返回新的buffer
     */
    public synchronized IStringBuffer insert(int i, boolean b) {
        return this.insert(i,b ? "true" : "false");
    }

    /**
     * 添加str串
     */
    public synchronized IStringBuffer append(String str) {
        return this.insert(this.n,str);
    }

    /**
     * 删除从begin到end-1的子串,0<=begin<length(),
     * end>=0, begin <= end
     * 若end >= length(),删除到串尾
     * 若begin越界,或者begin>end抛出字符串序号越界异常
     */
    public synchronized IStringBuffer delete(int begin, int end) {
        if (begin >= 0 && begin < this.n && end >= 0 && begin <= end) {
            if (end > this.n) { //end超长容错
                end = this.n;
            }

            //从end开始至串尾的子串向前移动
            if (this.n - end >= 0) {
                System.arraycopy(this.value, end, this.value, begin, this.n - end);
            }

            this.n -= end - begin;

            return this;
        } else {
            throw new StringIndexOutOfBoundsException("begin="+begin+", end="+end+", end-begin="+(end-begin));
        }
    }

    //将从begin到end-1子串全都替换为s串
    public IStringBuffer replace(int begin, int end, String s) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");

        if (this.value != null && this.n != 0) {
            for (int i = 0; i < this.n; i++) {
                sb.append(this.value[i]);
            }
        }

        return sb.toString();
    }
}
