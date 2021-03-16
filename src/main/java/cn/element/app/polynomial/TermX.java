package cn.element.app.polynomial;

/**
 * 一元多项式类
 * 实现可比较和可相加接口
 */
public class TermX implements Comparable<TermX>,Addable<TermX>{

    protected int coef,xexp; //系数,x指数(可为正,0),系数可为double

    //构造一项
    public TermX(int coef,int xexp){

        this.coef = coef;
        this.xexp = xexp;
    }

    //拷贝构造方法
    public TermX(TermX term){

        this.coef = term.coef;
        this.xexp = term.xexp;
    }

    @Override
    public int add(TermX termX) {

        if(termX.xexp == this.xexp){

            this.coef += termX.coef;
        }

        return this.coef;
    }

    @Override
    public boolean removable() {
        return false;
    }

    @Override
    public int compareTo(TermX o) {

        return this.xexp - o.xexp;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == this){
            return true;
        }

        TermX termX = null;

        if(obj instanceof TermX){
            termX = (TermX) obj;
        }

        if(this.xexp != termX.xexp){
            return false;
        }

        return this.coef == termX.coef;
    }

    //格式化代数项
    @Override
    public String toString() {

        //先处理特殊情况
        if(this.xexp > 0 && this.xexp != 1){
            if(this.coef == 1){
                return "x^" + this.xexp;
            }else if(this.coef == -1){
                return "-x^" + this.xexp;
            }
        }else if(this.xexp == 0){
            if(this.coef == 1){
                return "1";
            }else if(this.coef == -1){
                return "-1";
            }else{
                if(this.coef > 0){
                    return "+"+this.coef+"";
                }

                return this.coef+"";
            }
        }else if(this.xexp == 1){
            if(this.coef == 1){
                return "+x";
            }else if(this.coef == -1){
                return "-x";
            }else{
                if(this.coef > 0){
                    return "+"+this.coef+"x";
                }

                return this.coef+"x";
            }
        }

        //给正项加一个 + 号
        if(this.coef > 0){
            return "+"+this.coef+"x^"+this.xexp;
        }

        return this.coef+"x^"+this.xexp;
    }
}
