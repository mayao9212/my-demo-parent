package com.mayao.other;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Description:
 * @author: mayao
 * @date: 2018-10-18 9:51
 */
public class ExcuteShTest {


    @Test
    public void excuteShTest(){
        try {
            ProcessBuilder pb = new ProcessBuilder("F:\\dev-tools\\jdk8\\bin\\java.exe","-version");
            Process ps = pb.start();
            InputStream is = ps.getErrorStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = br.readLine()) != null) {
                System.err.println(line);
                Thread.sleep(3000);
            }

            InputStream is1 = ps.getInputStream();
            BufferedReader br1 = new BufferedReader(new InputStreamReader(is1));

            String line1;
            while ((line1 = br1.readLine()) != null) {
                System.out.println(line1);
                Thread.sleep(3000);
            }

            int exitCode = ps.waitFor();
            System.out.println(exitCode);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void stringArrayToStringTest(){
        //
        String[] command = {"a","-b","c"};
        System.out.println(JSON.toJSONString(command));

    }

}
