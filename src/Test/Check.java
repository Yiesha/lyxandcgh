package Test;
import java.util.ArrayList;
import java.util.List;
//校对答案
public class Check {
    //打开UserAnswers.txt文件让用户输入答案
    public static void inPutAnswers(){
        try {
            Process process = Runtime.getRuntime().exec("cmd.exe  /c notepad e:/UserAnswers.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //核对答案
    public static void result(){
        //题目答案
        List<StringBuffer> standardAnswers=TxtOpera.read("E:/Answers.Txt");
        //用户答案
        List<StringBuffer> userAnswers=TxtOpera.read("E:/UserAnswers.Txt");
        //答对的题目集合
        List<Integer> wrong=new ArrayList();
        //答错的题目集合
        List<Integer> correct=new ArrayList();
        //遍历对比
        for(int i=0;i<standardAnswers.size();i++){
            //如果一样，则答案正确
            if(standardAnswers.get(i).toString().equals(userAnswers.get(i).toString())){
                correct.add(i+1);
            }else{
                wrong.add(i+1);
            }
        }
        //生成成绩
        List<StringBuffer> grade=new ArrayList();
        grade.add(new StringBuffer("Correct:"+correct));
        grade.add(new StringBuffer("Wrong:"+wrong));
        TxtOpera.write(grade,"E:/Grade.Txt");
        //弹出Grade.txt
        try {
            Process process = Runtime.getRuntime().exec("cmd.exe  /c notepad e:/Grade.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
