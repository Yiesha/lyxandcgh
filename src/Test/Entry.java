package Test;
import java.util.Scanner;
//入口
public class Entry {
    public static void main(String[] args) {
        int choose;
        Scanner scanner=new Scanner(System.in);
        int n , r;
        System.out.println("-------四则运算生成器-------\n");

        System.out.print("请输入生成题目的数量n，n为正整数:");
        n = scanner.nextInt();
        while (n<=0){
            System.out.print("输入有误，请重新输入. n: ");
            n = scanner.nextInt();
        }
        //数值范围
        System.out.print("请输入题目数值的范围r，r为自然数:");
        r = scanner.nextInt();
        while(r < 0){
            System.out.print("输入有误，请重新输入. r: ");
            r = scanner.nextInt();
        }
        Create.creat(n, r);

        System.out.println("文件相关路径如下：");
        System.out.println("题目路径：E:/Exercises.txt");
        System.out.println("答案路径：E:/Answers.txt");

        System.out.println("---------------------------");
        do{
            System.out.println("1.答题");
            System.out.println("2.校验答案");
            System.out.println("3.退出");
            System.out.println("4.重新生成四则运算题目");
            System.out.print("\n请选择功能:");
            choose=scanner.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("注：每一道题目都必须有答案，不可有空白！");
                    Check.inPutAnswers();
                    break;
                case 2:
                    Check.result();
                    break;
                case 3:
                    System.out.println("程序成功退出！");
                    break;
                case 4:
                    System.out.print("生成题目的数量n，n为正整数:");
                    n = scanner.nextInt();
                    while (n<=0){
                        System.out.print("输入有误，请重新输入. n: ");
                        n = scanner.nextInt();
                    }
                    //数值范围
                    System.out.print("题目数值的范围r，r为自然数:");
                    r = scanner.nextInt();
                    while(r < 0){
                        System.out.print("输入有误，请重新输入. r: ");
                        r = scanner.nextInt();
                    }
                    Create.creat(n, r);
                default:
                    System.out.println("指令有误！");

            }
        }while (choose != 4);
    }
}
