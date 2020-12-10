package string;

import java.io.Serializable;

public class MyString implements Comparable<MyString>, Serializable {

    private final char[] value;  //字符数组,私有最终变量,只能复制一次

    public MyString() {  //构造空串"",串长度为0

        this.value = new char[0];
    }

    public MyString(String str){ //由字符串常量构造串

        this.value = new char[str.length()]; //申请字符数组并复制str串的所有字符串

        for(int i=0;i<this.value.length;i++){
            this.value[i] = str.charAt(i);
        }
    }

    /**以value数组从i开始的n个字符构造串,i>=0,n>=0, i+n<=value.length
     * 若i与n指定序号越界,则抛出字符串序号越界异常
     */
    public MyString(char[] value,int i,int n){

        if(i >= 0 && n >= 0 && i + n <= value.length){
            this.value = new char[n]; //申请字符数组并复制所有的字符

            for(int j=0;j<n;j++){
                this.value[j] = value[i+j];
            }
        }else{
            throw new StringIndexOutOfBoundsException("i="+i+", n="+n+", i+n="+(i+n));
        }
    }


    //以字符数组构造串
    public MyString(char[] value){

        this(value,0,value.length);
    }

    //拷贝构造方法,深度拷贝,复制字符
    public MyString(MyString str){

        this(str.value);
    }

    //由StringBuffer对象构造String对象
    public MyString(MyStringBuffer stringBuffer){

        this.value = new char[stringBuffer.capacity()]; //初始化

        for(int i = 0;i < stringBuffer.length();i++){
            this.value[i] = stringBuffer.charAt(i);
        }

    }

    //返回字符串的长度,即字符数组的长度
    public int length(){

        return this.value.length;
    }

    //返回第i个字符,0<=i<length(),若i越界,则抛出字符串序号越界异常
    public char charAt(int i){

        if(i >= 0 && i < this.value.length){
            return this.value[i];
        }

        throw new StringIndexOutOfBoundsException(i);
    }

    /**字符串截取,即求子串 区间[begin,end)
     * 返回序号从begin至end-1的子串,
     * 0<=begin<length(),0<=end<length(),begin<end
     * 否则抛出字符串序号越界异常
     * @param begin     起始位置
     * @param end       结束位置
     * @return          子串
     */
    public MyString subString(int begin,int end){

        if(begin == 0 && end == this.value.length){
            return this;
        }

        return new MyString(this.value,begin,end-begin);
    }

    //返回序号从begin至串尾的子串
    public MyString subString(int begin){

        return subString(begin,this.value.length);
    }

    //返回this与str串连接生成的串,若str == null,则插入"null"
    public MyString concat(MyString str){

        if(str == null){
            str = new MyString("null");
        }

        char[] buffer = new char[this.value.length + str.length()];

        int i;

        //复制当前串
        for(i = 0;i < this.value.length;i++){
            buffer[i] = this.value[i];
        }

        //复制指定串
        for(int j = 0;j < str.length();j++){
            buffer[i+j] = str.value[j];
        }

        //以字符数组构造串对象
        return new MyString(buffer);
    }

    //返回当前串删除所有空格的字符串
    public MyString trim(){

        int i = 0;

        char[] buffer = new char[this.value.length];

        //把不是空格的元素复制到buffer数组中去
        for (char c : this.value) {
            if(c != ' '){
                buffer[i++] = c;
            }
        }

        //声明一个容量为i的数组
        char[] temp = new char[i];

        //复制
        for (int j = 0; j < temp.length; j++) {
            temp[j] = buffer[j];
        }

        return new MyString(temp);
    }

    //返回将所有小写字母转成大写的串
    public MyString toUpperCase(){

        for (int i = 0; i < this.value.length; i++) {
            if(this.value[i] >= 'a' && this.value[i] <= 'z'){//是小写字母
                this.value[i] -= 32;
            }
        }

        return this;
    }

    //返回将所有大写字母转成小写字母
    public MyString toLowerCase(){

        for (int i = 0; i < this.value.length; i++) {
            if(this.value[i] >= 'A' && this.value[i] <= 'Z'){//是大写字母
                this.value[i] += 32;
            }
        }

        return this;
    }

    //比较串与s是否相等,忽略大小写
    public boolean equalsIgnoreCase(MyString s){

        if(s == null){
            throw new NullPointerException("s == null");
        }

        if(s == this){
            return true;
        }

        if(s.length() == this.length()){
            for(int i = 0;i < this.length();i++){
                if(this.value[i] >= 'a' && this.value[i] <= 'z' && s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'){
                    if(this.value[i] - s.charAt(i) != 32){
                        return false;
                    }
                }else if(this.value[i] >= 'A' && this.value[i] <= 'Z' && s.charAt(i) >= 'a' && s.charAt(i) <= 'z'){
                   if(s.charAt(i) - this.value[i] != 32){
                       return false;
                   }
                }else if(this.value[i] != s.charAt(i)){
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    //比较两串大小忽略字母大小写
    public int compareToIgnoreCase(MyString s){

        for(int i = 0;i < this.value.length && i < s.value.length;i++){
            if(this.value[i] >= 'a' && this.value[i] <= 'z' && s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'){
                if(this.value[i] - s.charAt(i) != 32){
                    return this.value[i] - s.value[i]; //返回两串第一个不同字符的差值
                }
            }else if(this.value[i] >= 'A' && this.value[i] <= 'Z' && s.charAt(i) >= 'a' && s.charAt(i) <= 'z'){
                if(s.charAt(i) - this.value[i] != 32){
                    return this.value[i] - s.value[i]; //返回两串第一个不同字符的差值
                }
            }else if(this.value[i] != s.charAt(i)){
                return this.value[i] - s.value[i]; //返回两串第一个不同字符的差值
            }
        }

        return this.value.length - s.value.length; //前缀子串,返回两串长度的差值
    }

    //以regex为分割字符串,返回拆分的子串数组
    public MyString[] split(MyString regex){



        return null;
    }

    //返回将串s中各单词改为大写字母的串,单词以空格分隔
    public MyString upperFirst(MyString s){

        return null;
    }

    //将串s反序输出,从后向前
    public void printPrevious(MyString s){

    }

    //返回两串中所有相同字符(不重复)
    public MyString getSameChars(MyString sa,MyString sb){

        return null;
    }

    //返回将串s逆转的串
    public MyString reverse(MyString s){

        return null;
    }

    //判断字符串str是否为标识符
    public static boolean isIdentifier(MyString str){
        
        return false;
    }

    //返回在字符串str中识别出的所有表示符
    public static MyString[] toIdentifier(MyString str){

        return null;
    }



    //比较this与str串的大小,返回两者差值
    @Override
    public int compareTo(MyString str) {

        for(int i = 0;i < this.value.length && i < str.value.length;i++){
            if(this.value[i] != str.value[i]){
                return this.value[i] - str.value[i]; //返回两串第一个不同字符的差值
            }
        }

        return this.value.length - str.value.length; //前缀子串,返回两串长度的差值
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == this){
            return true;
        }

        if(obj instanceof MyString){

            MyString str = (MyString) obj;

            if(str.length() != this.length()){
                return false;
            }

            for(int i = 0;i < this.value.length;i++){
                if(this.value[i] != str.value[i]){
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public String toString() {

        return new String(this.value);
    }
}
