package org.codeart.datastructure.polynomial;

import org.codeart.datastructure.common.Addable;

/**
 * 一元多项式类
 * 实现可比较和可相加接口
 */
public class TermX implements Comparable<TermX>, Addable<TermX> {

    protected int coefficient;  //系数,系数可为double

    protected int exponent;  //指数(可为正,0)

    //构造一项
    public TermX(int coefficient,int exponent){
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    //拷贝构造方法
    public TermX(TermX term){
        this.coefficient = term.coefficient;
        this.exponent = term.exponent;
    }

    @Override
    public int add(TermX termX) {
        if (termX.exponent == this.exponent) {
            this.coefficient += termX.coefficient;
        }

        return this.coefficient;
    }

    @Override
    public boolean removable() {
        return this.coefficient == 0;
    }

    @Override
    public int compareTo(TermX o) {
        return this.exponent - o.exponent;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof TermX) {
            TermX termX = (TermX) obj;

            if (this.exponent != termX.exponent) {
                return false;
            }

            return this.coefficient == termX.coefficient;
        }

        return false;
    }

    //格式化代数项
    @Override
    public String toString() {
        //先处理特殊情况
        if (this.exponent > 0 && this.exponent != 1) {
            if (this.coefficient == 1) {
                return "x^" + this.exponent;
            } else if(this.coefficient == -1) {
                return "-x^" + this.exponent;
            }
        } else if (this.exponent == 0) {
            if (this.coefficient == 1) {
                return "1";
            } else if (this.coefficient == -1) {
                return "-1";
            } else {
                if (this.coefficient > 0) {
                    return "+"+this.coefficient+"";
                }

                return this.coefficient+"";
            }
        } else if (this.exponent == 1) {
            if (this.coefficient == 1) {
                return "+x";
            } else if (this.coefficient == -1) {
                return "-x";
            } else {
                if (this.coefficient > 0) {
                    return "+"+this.coefficient+"x";
                }

                return this.coefficient+"x";
            }
        }

        //给正项加一个 + 号
        if (this.coefficient > 0) {
            return "+"+this.coefficient + "x^" + this.exponent;
        }

        return this.coefficient + "x^" + this.exponent;
    }
}
