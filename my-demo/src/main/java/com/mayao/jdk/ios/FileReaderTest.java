package com.mayao.jdk.ios;

import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.util.List;

/**
 * function ：主要处理文本文件
 * @author ：mayao {yao.ma@shenmajr.com}
 * @date ：2018/7/20
 */
@Slf4j
public class FileReaderTest {


    /**
     * 向已有文件追加内容
     */
    @Test
    public void addContentToFile(){




        String path = "C:\\Users\\tend\\Desktop\\page\\CtProject.java";
        //
        File file = new File(path);
        String nameJava = file.getName();
        log.info("目标文件名：{}",nameJava);
        //文件复制
        String name = nameJava.replace(".java","");
        String pageName = name+"Page";
        String pageNameJave = pageName+".java";
        String pagePath = "C:\\Users\\tend\\Desktop\\page\\"+pageNameJave;
        File pageFile = new File(pagePath);

        //读
        FileInputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        //读取文件的缓冲区
        BufferedReader bufferedReader = null;
        //写
        FileOutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        //写入的缓冲区
        BufferedWriter bufferedWriter = null;
        try {
            //源文件
            inputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            //目标文件
            outputStream = new FileOutputStream(pageFile);
            outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            String line;
            while ((line=bufferedReader.readLine())!=null){
                //当前行数据
                log.info(line);
                //BasePage
                if("import com.talkingdata.appbuilder.common.PrimaryKey;".equals(line)){
                    bufferedWriter.write("import com.talkingdata.appbuilder.base.BasePage;");
                    bufferedWriter.newLine();
                }
                //extends
                if(line.startsWith("public class "+name)){
                    line = line.replace("public class "+name,"public class "+pageName+" extends BasePage");
                }
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();

            //文件的循环复制
            File entityFiles = new File("C:\\Users\\tend\\Desktop\\page");
            File[] files = entityFiles.listFiles();
            String newPath = "C:\\Users\\tend\\Desktop\\page2";
            File targetFile = new File(newPath);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            log.info("开始循环复制");
            for(File copyFile:files){
                log.info("文件名：{}",copyFile.getName());
                File target = new File(newPath+File.separator+copyFile.getName());
                Files.copy(copyFile,target);
            }


        }catch (FileNotFoundException e){

        }catch (IOException e){

        }finally {

        }




    }


}
