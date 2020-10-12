package Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//生成四则运算
public class Create {
    //四种运算符
    public static final char[] Operator ={'+','-','*','/'};
    public static void creat(int n, int r) {
        Random random = new Random();
        //题目集合
        List<StringBuffer> listQuestions = new ArrayList();
        //答案集合
        List<StringBuffer> listAnswers = new ArrayList();

        StringBuffer t;  //temp用于拼接StringBuffer
        int num,operator,operatorNumbers,brackets;

        //n个运算式
        for (int i = 0; i < n; i++) {
            //随机生成运算符的数目
            operatorNumbers = random.nextInt(3) + 1;
            t = new StringBuffer();
            //第一个随机数
            int previous = random.nextInt(r);
            t.append(previous);

            for (int j = 0; j < operatorNumbers; j++) {
                //随机生成运算符
                operator=random.nextInt(4);
                //生成随机数
                num=random.nextInt(r);
                //保证除法运算的除数不为0,且结果必须为真分数
                if(operator==3&&((num==0)||(previous-num)>=1)){
                    j--;
                    continue;
                }
                //保证减法运算结果为正数
                if(operator==1&&((num - previous)<=0)){
                    j--;
                    continue;
                }
                //连接运算符
                t.append(Operator[operator]);
                //连接随机数
                t.append(num);
                previous = num;
            }
            //随机产生括号
            brackets=random.nextInt(2);
            char[] temp=t.toString().toCharArray();
            int j;
            //查找加减号的位置
            for(j=0;j<temp.length;j++){
                if(brackets==1&&(temp[j]=='-'||temp[j]=='+')){
                    break;
                }
            }
            int a=0,b=j+2;
            //插入括号
            if (j == temp.length) {
            }else{
                for(int k=0;k<j;k++){
                    if(temp[k]<'0'||temp[k]>'9'){
                        a=k+1;
                    }
                }
                for(int k=j+2;k<temp.length;k++){
                    if(temp[k]>='0'&&temp[k]<='9'){
                        b++;
                    }else{
                        break;
                    }
                }
                //保证除数不为0，并且括号不在首个数字生成
                if(a!=0&&t.toString().charAt(a-1)!='/') {
                    t.insert(a, '(');
                    t.insert((b + 1), ')');
                }
            }
            //结果不可为负数
            if(Answers.getAnswers(t).toString().charAt(0)=='-'){
                i--;
                continue;
            }
            //将表达式添加到题目集合中
            listQuestions.add(t);
        }
        //生成答案
        for(StringBuffer b: listQuestions){
            //将答案添加到答案集合中
            listAnswers.add(Answers.getAnswers(b));
        }
        //将题目写入文件
        TxtOpera.write(listQuestions,"E:/Exercises.txt");
        //将答案写入文件
        TxtOpera.write(listAnswers,"E:/Answers.txt");
        //弹出Exercises.txt
        try {
            Process process = Runtime.getRuntime().exec("cmd.exe  /c notepad e:/Exercises.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}