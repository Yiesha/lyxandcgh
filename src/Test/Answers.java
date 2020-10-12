package Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
//题目的答案
public class Answers {
        /*逆波兰表达式（后缀表达式）
         *先操作数后操作符，不需要括号来标示操作符的优先级
         * 例：12*（4-3）->12 4 3 -
         */
    public static StringBuffer getAnswers(StringBuffer infix) {
        //栈
        Stack<StringBuffer> s = new Stack();
        //tag用于在处理字符串时标志之前的字符是否为数字。0：不是，1：是
        int tag=0;
        //中缀表达式
        List<StringBuffer> temp= new ArrayList();
        char[] t=infix.toString().toCharArray();
        //后缀表示式（逆波兰表达式）
        List<StringBuffer> suffix=new ArrayList();
        //中缀表达式：将操作数与操作符分离
        for (char c : t) {
            //如果是数字
            if (c >= '0' && c <= '9') {
                if (tag == 0) {
                    //StringBuffer构造方法需要转换为字符串
                    temp.add(new StringBuffer(c + ""));
                    //修改标志
                    tag = 1;
                } else {
                    //之前也是数字，同一个数，进行拼接
                    temp.get(temp.size() - 1).append(c);
                }
            } else {//如果是运算符
                //直接修改标志
                tag = 0;
                temp.add(new StringBuffer(c + ""));
            }
        }
        //由中缀表达式后缀表达式-栈
         /* 运算符号栈S：从栈底到栈顶，总是保持运算符号优先级 严格递增
          * 从左向右依次扫描中缀表达式
          * 若扫描到操作数，直接存入数组
          * 若扫描到运算符，如果S为空，入栈；
          *               如果S非空，比较当前运算符与栈顶运算符的优先级：若高于，则入栈，若等于或低于，则S栈顶符号出栈，直到栈空或栈顶符号优先级低于当前符号，出栈的符号存入数组，当前符号入栈。
          * 若扫描到括号，入栈；直到扫描到另一半括号，此时，S的元素依次出栈存入数组A，直到遇到之前压入的括号，并丢弃之。
          * 扫描完最后一个操作数，将S的元素依次出栈存入数组。扫描结束，此时数组存储了相应的后缀表达式
        */
        for (StringBuffer b : temp) {
            //c为左括号直接入栈
            if (b.toString().equals("(")) {
                s.push(b);
            } else if (b.toString().equals(")")) {
                //栈非空且栈顶元素非'('
                while (!s.peek().toString().equals("(")) {
                    suffix.add(s.pop());
                }
                //栈顶元素出栈拼接到后缀表达式
                s.pop();
            } else if (b.toString().equals("+") || b.toString().equals("-")) {
                //栈不为空时
                while (!s.empty()) {
                    //栈顶符号优先级高于于当前符号
                    if (!s.peek().toString().equals("(")) {
                        //则出栈
                        suffix.add(s.pop());
                    } else {
                        //如果低于,则跳出循环
                        break;
                    }
                }
                //出栈
                s.push(b);
            } else if (b.toString().equals("*") || b.toString().equals("/")) {
                while (!s.empty()) {
                    //栈顶元素优先级高则出栈拼接到后缀表达式
                    if (s.peek().toString().equals("*") || s.peek().toString().equals("/")) {
                        suffix.add(s.pop());
                    } else {
                        break;
                    }
                }
                s.push(b);
            } else {  //c为数字,直接拼接到后缀表达式
                suffix.add(b);
            }
        }
        while (!s.empty()) {  //将栈中剩余的运算符拼接到suffix
            suffix.add(s.pop());
        }
        /*
         * 运算过程：
         * 如果是数据，则我们让它入栈，如果是操作符，我们让数据出栈（此时栈顶为右操作数），和操作符进行运算
         * 最后，再让出栈的2个元素运算的结果入栈
         * */
        StringBuffer t1,t2;  //a,b为两个操作数
        StringBuffer result;  //result为运算结果
        for(StringBuffer b : suffix){
            if(b.toString().equals("+")) {
                t1 = s.pop(); //提取栈顶前两个操作数
                t2 = s.pop();
                result=Fraction.add(t2,t1);
                s.push(result);  //将计算结果入栈
            }else if(b.toString().equals("-")){
                t1 = s.pop(); //提取栈顶前两个操作数
                t2 = s.pop();
                result=Fraction.sub(t2,t1);
                s.push(result);  //将计算结果入栈
            }else if(b.toString().equals("*")){
                t1 = s.pop(); //提取栈顶前两个操作数
                t2 = s.pop();
                result=Fraction.multiply(t2,t1);
                s.push(result);  //将计算结果入栈
            }else if(b.toString().equals("/")){
                t1 = s.pop(); //提取栈顶前两个操作数
                t2 = s.pop();
                result=Fraction.divide(t2,t1);
                s.push(result);  //将计算结果入栈
            } else{
                s.push(b);
            }
        }
        //返回的分式做简单处理
        String[] str=s.peek().toString().split("/");
        if(str.length==1){ //非分式，直接返回
            return s.peek();
        }else{
            //分子
            int numerator=Integer.valueOf(str[0]);
            //分母
            int denominator=Integer.valueOf(str[1]);
            //若为整数，则返回分子
            if(denominator==1){
                return new StringBuffer(str[0]);
            }else if(Math.abs(numerator)<denominator){
                return s.peek();
            }else{
                //若分子绝对值大于分母(分母不为1)，返回时化为真分数
                int r=numerator/denominator; //真数
                numerator = numerator%denominator;
                return new StringBuffer(r + "'" + numerator + "/" + denominator);
            }
        }
    }
}