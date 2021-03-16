package cn.element.matrix;

/**
 * 矩阵类
 */
public class Matrix {

    private int rows,columns; //矩阵行数,列数

    private int[][] element; //二维数组,存储矩阵元素

    public Matrix(int m,int n){ //构造m * n的零矩阵,若m或n为负数,Java抛出负数数组长度异常

        this.element = new int[m][n]; //数组元素初始值为0
        this.rows = m;
        this.columns = n;
    }

    public Matrix(int n){   //构造n * n的方阵

        this(n,n);
    }

    public Matrix(int m,int n,int[][] value){ //构造m * n的矩阵,有value[][]提供元素

        this(m,n);

        for(int i = 0;i < value.length && i < m;i++){
            for(int j = 0;j < value[i].length && j < n;j++){
                this.element[i][j] = value[i][j];
            }
        }
    }

    public int getRows(){ //返回矩阵行数

        return this.rows;
    }

    public int getColumns(){ //返回矩阵的列数

        return this.columns;
    }

    //返回矩阵第i行第j列元素,若i,j越界,抛出序号越界异常
    public int get(int i,int j){

        if(i >= 0 && i < this.rows && j >= 0 && j < this.columns){
            return this.element[i][j];
        }

        throw new IndexOutOfBoundsException("i = " + i + ", j = " + j);
    }

    //设置矩阵第i行,第j列元素为x,若i,j越界,抛出序号越界异常
    public void set(int i,int j,int x){

        if(i >= 0 && i < this.rows && j >= 0 && j < this.columns){
            this.element[i][j] = x;
        }else{
            throw new IndexOutOfBoundsException("i="+i+",j="+j);
        }
    }

    //返回矩阵元素描述字符串,行主序遍历
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("矩阵"+this.getClass().getName()+" ("+this.rows+"*"+this.columns+" ): \n");

        for(int i = 0;i < this.rows;i++){
            for(int j = 0;j < this.columns;j++){
                sb.append(String.format("%6d",this.element[i][j]));//"%6d表示十进制数占6列
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    //设置矩阵为m行n列,若参数指定行列数较大,则将矩阵扩容,并复制矩阵的元素,用邻接矩阵的存储结构
    public void setRowsColumns(int m,int n){

        if(m > 0 && n > 0){
            if(m > this.element.length || n > this.element[0].length){
                int[][] source = this.element;      //参数指定的行数或列数比较大时,扩充二位数组容量

                this.element = new int[m][n];       //重新申请二维数组空间,元素初始值为0

                for(int i = 0;i < this.rows;i++){   //复制原二维数组的元素
                    for(int j = 0;j < this.columns;j++){
                        this.element[i][j] = source[i][j];
                    }
                }
            }

            this.rows = m;
            this.columns = n;
        }else{
            throw new IllegalArgumentException("矩阵行列数不能 <= 0, m = " + m + ", n = " + n);
        }
    }




}
