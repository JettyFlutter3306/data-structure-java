package cn.element.string;

import java.io.Serializable;
import java.util.*;

/**
 * 声明字符串类MyString
 * 仿照java.lang.String类构建
 */
public class MyString implements Comparable<MyString>, Serializable {

    private final char[] value;  //字符数组,私有最终变量,只能赋值一次

    public MyString() {  //构造空串"",串长度为0

        this.value = new char[0];
    }

    public MyString(String str){ //由字符串常量构造串

        this.value = new char[str.length()]; //申请字符数组并复制str串的所有字符串

        for(int i = 0;i < this.value.length;i++){
            this.value[i] = str.charAt(i);
        }
    }

    /**
     * 以value数组从i开始的n个字符构造串,i>=0,n>=0, i+n<=value.length
     * 若i与n指定序号越界,则抛出字符串序号越界异常
     */
    public MyString(char[] value,int i,int n){

        if(i >= 0 && n >= 0 && i + n <= value.length){
            this.value = new char[n]; //申请字符数组并复制所有的字符

            for(int j = 0;j < n;j++){
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

    /**
     * 由StringBuffer对象构造String对象
     * @param stringBuffer      StringBuffer对象
     */
    public MyString(MyStringBuffer stringBuffer){

        this.value = new char[stringBuffer.capacity()]; //初始化

        for(int i = 0;i < stringBuffer.length();i++){
            this.value[i] = stringBuffer.charAt(i);
        }

    }

    /**
     * 返回字符串的长度,即字符数组的长度
     * @return      长度
     */
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
     * 0 <= begin < length(),0 <= end < length(),begin < end
     * 否则抛出字符串序号越界异常
     * @param begin     起始位置
     * @param end       结束位置
     * @return          子串
     */
    public MyString subString(int begin,int end){

        if(begin >= end){
            throw new StringIndexOutOfBoundsException("begin >= end");
        }

        if(begin == 0 && end == this.value.length){
            return this;
        }

        return new MyString(this.value,begin,end-begin);
    }

    /**
     * 根据索引删除单个字符
     * @param i         索引
     * @return          当前串
     */
    public MyString deleteString(int i){

        if(i < 0 || i >= this.value.length){
            throw new IndexOutOfBoundsException("i < 0");
        }

        char[] temp = new char[this.value.length - 1];

        for (int j = 0; j < i; j++) {
            temp[j] = this.value[j];
        }

        for (int j = i; j < this.value.length; j++) {
            temp[j] = this.value[j+1];
        }

        return new MyString(temp);
    }

    /**
     * 根据索引删除子串,删除索引[begin,end)
     * @param begin     开始的索引
     * @param end       结束的索引
     * @return          原来的串
     */
    public MyString deleteString(int begin,int end){

        if(begin >= end){
            throw new StringIndexOutOfBoundsException("begin >= end");
        }

        if(begin == 0 && end == this.length()){
            return new MyString();
        }

        int offset = end - begin;  //删除的长度

        char[] temp = new char[this.value.length - offset];  //申请一个新的字符数组

        for (int i = 0; i < begin; i++) {
            temp[i] = this.value[i];
        }

        for (int i = begin; i < temp.length; i++) {
            temp[i] = this.value[i + offset];
        }

        return new MyString(temp);
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

    //分割字符串,以ch为分隔符
    public MyString[] split(char ch){

        List<MyString> stringList = new ArrayList<>();

        int start = 0;

        for(int i = 0;i < this.length();i++){
            char sc = this.charAt(i);

            if(ch == sc){
                if(start < i){
                    stringList.add(this.subString(start,i));
                }

                start = i + 1;
            }
        }

        //start是根据c计数,不是以c结尾的str在这里加个判断
        if(start < this.length()){
            stringList.add(this.subString(start,this.length()));
        }

        return stringList.stream().toArray(MyString[] :: new);
    }

    //返回将串s中各单词改为大写字母的串,单词以空格分隔
    public static MyString upperFirst(MyString s){

        MyString[] s1 = s.split(' ');

        for (int i = 0; i < s1.length; i++) {
            if(isLowerLetter(s1[i].value[0])){
                s1[i].value[0] -= 'a' - 'A';
            }
        }

        String tmp = "";

        for (MyString string : s1) {
            tmp += string.toString() + " ";
        }

        return new MyString(tmp);
    }

    //将串s反序输出,从后向前
    public static void printPrevious(MyString s){

        for (int i = s.value.length - 1; i >= 0; i--) {
            System.out.print(s.value[i]);
        }
    }

    //返回两串中所有相同字符(不重复)
    public static MyString getSameChars(MyString sa,MyString sb){

        Set<Character> set1 = new HashSet<>();

        for (char c : sa.value) {
            set1.add(c);
        }

        char[] ch = new char[Math.max(sa.length(), sb.length())];

        int index = 0;

        Set<Character> set2 = new HashSet<>();

        for (char c : sb.value) {
            if(set1.contains(c)){
                set2.add(c);
            }
        }

        for (char c : set2) {
            ch[index++] = c;
        }

        return new MyString(ch);
    }

    //返回将串s逆转的串
    public static MyString reverse(MyString s){

        for (int i = 0; i < s.value.length / 2; i++) {
            char temp = s.value[i];

            s.value[i] = s.value[s.length() - 1- i];

            s.value[s.length() -1 - i] = temp;
        }

        return s;
    }

    //判断字符串str是否为标识符
    public static boolean isIdentifier(MyString str){

        if(str == null || str.length() == 0){
            return false;
        }

        char[] ch = str.value;

        //是数字
        if(ch[0] >= '0' && ch[0] <= '9'){
            return false;
        }

        for (char c : ch) {
            if(!(Character.isJavaIdentifierPart(c))){
                return false;
            }
        }

        return true;
    }

    //判断是否是大写字母
    public static boolean isUpperLetter(char ch){

        return ch >= 'A' && ch <= 'Z';
    }

    //判断是否是小写字母
    public static boolean isLowerLetter(char ch){

        return ch >= 'a' && ch <= 'z';
    }

    //判断是否是回文字符串 正序和反序一样
    public static boolean isBackFrontSame(MyString s){

        int n = s.length();  //拿到长度

        for (int i = 0; i < n / 2; i++) {
            if(s.charAt(i) != s.charAt(n-i)){
                return false;
            }
        }

        return true;
    }

    /**
     * 返回当前串(目标串)中首个与模式串pattern匹配的子串序号,匹配失败返回-1
     * @param pattern       模式串
     * @return              序号
     */
    public int indexOf(MyString pattern){

        return this.indexOf(pattern,0);
    }

    /**
     * 返回当前串(目标串)从begin开始首个与模式串pattern匹配的子串序号,匹配失败时返回-1
     * 0 <= begin < this.length(),对begin容错,若begin < 0,从0开始,若begin序号越界,查找不成功
     * 若pattern == null,抛出空对象异常
     * @param pattern   模式串
     * @param begin     开始的索引
     * @return          序号
     */
    public int indexOf(MyString pattern,int begin){

        int n = this.length();
        int m = pattern.length();

        if(begin < 0){ //对begin容错,若begin < 0,那么从0开始
            begin = 0;
        }

        if(n == 0 || n < m || begin >= n){ //若目标串为空,较短或begin越界,不需比较
            return -1;
        }

        int i = begin; //i,j分别为目标串和模式串当前字符下标
        int j = 0;
        int count = 0;  //记录比较次数

        while (i < n && j < m){
            count++;

            if(this.charAt(i) == pattern.charAt(j)){//若当前两个字符相等,则继续比较后续字符
                i++;
                j++;
            }else{ //否则i,j回溯,进行下次比较
                i = i - j + 1;  //目标串下标i,返回到下个匹配子串序号

                j = 0;  //模式串下标j,退回到0

                if(i > n - m){ //若目标串剩余子串长度不够,不再比较
                    break;
                }
            }
        }

        if(j == m){ //匹配成功
            return i - j;  //返回匹配的子串序号
        }

        return -1;      //匹配失败返回-1
    }

    /**
     * 返回目标串target中首个与模式串pattern匹配的子串序号,匹配失败时返回-1
     * @param target        目标串
     * @param pattern       模式串
     * @return              索引
     */
    public static int indexOf(String target,String pattern){

        return indexOf(target,pattern,0);
    }

    /**
     * 返回目标串target从begin开始首个与模式串pattern匹配的子串序号,匹配失败时返回-1
     * 0 <= begin < target.length() 对begin容错,若begin < 0,从0开始;若begin越界,查找不成功
     * @param target        目标串
     * @param pattern       模式串
     * @param begin         开始的位置
     * @return              索引
     */
    public static int indexOf(String target,String pattern,int begin){

        int n = target.length(), m = pattern.length();

        if(begin < 0){ //对begin容错,若begin < 0,那么从0开始
            begin = 0;
        }

        if(n == 0 || n < m || begin >= n){//若目标串空,较短或begin越界,不需要比较
            return -1;
        }

        int[] next = getNext(pattern); //返回模式串pattern改进的next数组

        int i = begin,j = 0;  //i,j分别为目标串,模式串比较字符下标

        while(i < n && j < m){
            if(j == -1 || target.charAt(i) == pattern.charAt(j)){ //若当前两字符相等,则继续比较后续字符
                i++;
                j++;
            }else{          //否则下次匹配,目标串下标i不回溯
                j = next[j];  //模式串下标j退回到下次比较字符序号

                if(n-i+1 < m-j+1){ //若目标串剩余子串的长度不够,不在比较
                    break;
                }
            }
        }

        if(j == m){             //匹配成功
            return i - j;       //返回匹配的子串序号
        }

        return -1;              //匹配失败
    }

    /**
     * 返回模式串的next数组
     * @param pattern       模式串
     * @return              next数组
     */
    private static int[] getNext(String pattern){

        int j = 0, k = -1;
        int[] next = new int[pattern.length()];

        next[0] = -1;

        while(j < pattern.length() - 1){
            if(k == -1 || pattern.charAt(j) == pattern.charAt(k)){
                j++;
                k++;

                next[j] = k;
            }else{
                k = next[k];
            }
        }

        return next;
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
