package Test;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

//文件的相关操作
public class TxtOpera {
    //写入到文件
    public static void write(List<StringBuffer> list,String filePath){
        try {
            StringBuffer temp = new StringBuffer();
            //是否在原文件上操作
            FileOutputStream fileOutputStream = new FileOutputStream(filePath, false);
            //将信息写入文件的格式，并规定编码的格式
            OutputStreamWriter streamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            //依次取出进行遍历
            for(int i=0;i<list.size();i++){
                //进行拼接
                temp.append(list.get(i)+"\n");
            }
            streamWriter.write(temp.toString());
            streamWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //从文件中读取信息
    public static List<StringBuffer> read(String filePath){
        List<StringBuffer> list =new ArrayList<>();
        try {
            String temp;
            FileInputStream inputStream=new FileInputStream(filePath);
            //编码格式
            InputStreamReader reader=new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader=new BufferedReader(reader);
            //读取每一行的字符串，如果不为空
            while((temp=bufferedReader.readLine())!=null){
                list.add(new StringBuffer(temp));
            }
            //关闭流
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
