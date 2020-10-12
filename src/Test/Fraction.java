package Test;
//分数的相关运算
public class Fraction {
    public static StringBuffer add(StringBuffer t1,StringBuffer t2){

        if(t1.toString().equals("0")){
            return t2;
        }
        if(t2.toString().equals("0")){
            return t1;
        }
        //转化为分数
        String[] fraction1 = changeFraction(t1);
        String[] fraction2 = changeFraction(t2);
        //分母
        int denominator= minNum(Integer.valueOf(fraction1[1]),Integer.valueOf(fraction2[1]));
        //分子
        int numerator=denominator/Integer.valueOf(fraction1[1])*Integer.valueOf(fraction1[0])+denominator/Integer.valueOf(fraction2[1])*Integer.valueOf(fraction2[0]);
        //统一格式
        return pattern(denominator,numerator);
    }
    //减法运算
    public static StringBuffer sub(StringBuffer t1,StringBuffer t2){
        if(t1.toString().equals("0")){
            return new StringBuffer("-"+t2.toString());
        }
        if(t2.toString().equals("0")){
            return t1;
        }
        //转化为分数
        String[] fraction1 = changeFraction(t1);
        String[] fraction2 = changeFraction(t2);
        //分母
        int denominator= minNum(Integer.valueOf(fraction1[1]),Integer.valueOf(fraction2[1]));
        //分子
        int numerator=denominator/Integer.valueOf(fraction1[1])*Integer.valueOf(fraction1[0])-denominator/Integer.valueOf(fraction2[1])*Integer.valueOf(fraction2[0]);
        //统一格式
        return pattern(denominator,numerator);
    }
    //乘法运算
    public static StringBuffer multiply(StringBuffer t1, StringBuffer t2){
        if(t1.toString().equals("0")||t2.toString().equals("0")) {
            return new StringBuffer("0");
        }
        //转化为分数
        String[] fraction1 = changeFraction(t1);
        String[] fraction2 = changeFraction(t2);
        //分母
        int denominator=Integer.valueOf(fraction1[1])*Integer.valueOf(fraction2[1]);
        //分子
        int numerator=Integer.valueOf(fraction1[0])*Integer.valueOf(fraction2[0]);
        //统一格式
        return pattern(denominator,numerator);
    }
    //除法运算
    public static StringBuffer divide(StringBuffer t1, StringBuffer t2){
        if(t1.toString().equals("0")){
            return t1;
        }
        //转化为分数
        String[] fraction1 = changeFraction(t1);
        String[] fraction2 = changeFraction(t2);
        //分母
        int denominator=Integer.valueOf(fraction1[1])*Integer.valueOf(fraction2[0]);
        //分子
        int numerator=Integer.valueOf(fraction1[0])*Integer.valueOf(fraction2[1]);
        //统一格式
        return pattern(denominator,numerator);
    }
    //将所有分数转化为统一的纯分数格式
    public static StringBuffer fraction(StringBuffer t){
        //利用/和'进行分隔存入数组
        String[] temp=t.toString().split("'|/");
        //整数
        if(temp.length==1){
            return t.append("/1");
            //不是带分数，可直接返回
        }else if(temp.length==2) {
            return t;
        }else{
            //带分数
            int numerator,denominator;
            //分母
            denominator=Integer.valueOf(temp[2]);
            //分子
            numerator=Integer.valueOf(temp[0])*denominator+Integer.valueOf(temp[1]);
            return new StringBuffer(numerator+"/"+denominator);
        }
    }
    //返回分式子的分子分母
    public static String[] changeFraction(StringBuffer t) {
        //将t化为分式
        StringBuffer a= fraction(t);
        //将分子分母进行分离
        String[] nums=a.toString().split("/");
        //返回分子分母
        return nums;
    }
    //统一格式
    public static StringBuffer pattern(int denominator ,int numerator) {
        //进行约分
        denominator/=maxNum(denominator,numerator);
        numerator/=maxNum(denominator,numerator);
        //如果分数式负数，那么符号加载整个数前
        if(denominator<0){
            denominator=-denominator;
            numerator=-numerator;
        }
        return new StringBuffer(numerator+"/"+denominator);
    }
    //最大公因数
    public static int maxNum(int m, int n){
        if(m==0||n==0) return 1;
        int r;  //余数
        if(m<n){
            r=m;
            m=n;
            n=r;
        }
        while (m%n!=0){
            r=m%n;
            m=n;
            n=r;
        }
        return n;
    }
    //最小公倍数
    public static int minNum(int m, int n){
        return m*n/ maxNum(m,n);
    }
}
