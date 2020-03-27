package com.kevin.usc.base.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class PoiUtils {

    /**
     * @param jsonStr
     * @desc 本地扫描文件夹手续json文件
     */
    public static void jsonToExcel(String jsonStr) throws IOException {

        // 标题
        String [] titleColumn = new String[]{"key", "value"};
        // 创建HSSFWorkbook对象
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建HSSFSheet对象
        HSSFSheet sheet = wb.createSheet("sheet");

       //创建titleRow 
        HSSFRow titleRow = sheet.createRow(0);
        HSSFCell titleCell;
        for(int i =  0; i < titleColumn.length; i++) {
            titleCell = titleRow.createCell(i);
            titleCell.setCellValue(titleColumn[i]);
        }
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
         AtomicReference<Integer> i = new AtomicReference<>(1);
        Set<Map.Entry<String, Object>> entries = jsonObject.entrySet();
        jsonObject.forEach((k,v)->{
            HSSFRow columRow = sheet.createRow(i.get());
            HSSFCell kCell = columRow.createCell(0);
            kCell.setCellValue(k);
            HSSFCell vCell = columRow.createCell(1);
            vCell.setCellValue(v.toString());
            i.getAndSet(i.get() + 1);
        });

        // 输出Excel文件
        FileOutputStream output = new FileOutputStream("D://en-US.xls");
        wb.write(output);
        output.flush();
        output.close();
    }

    public static void ReplaceWord(String fileUrl) throws FileNotFoundException {

        File filesource=new File("D://testJson//json//bcareAccount.json");
        Scanner input=new Scanner(filesource);
        StringBuffer temp=new StringBuffer();
        while(input.hasNext())
        {
            String tem=input.nextLine();
            String replaceTem = tem.replaceAll("\"", "")+"\r\n".replaceAll(":","\t");
            temp.append(replaceTem);
        }
        PrintWriter output=new PrintWriter(filesource);
        output.print(temp);
        input.close();
        output.close();
    }

    public static void readfile(String filepath) throws FileNotFoundException, IOException {
        try {

            File file = new File(filepath);
            if (!file.isDirectory()) {
                System.out.println("文件");
                //System.out.println("path=" + file.getPath());
                //System.out.println("absolutepath=" + file.getAbsolutePath());
                //System.out.println("name=" + file.getName());

            } else if (file.isDirectory()) {
                System.out.println("文件夹");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "\\" + filelist[i]);
                    if (!readfile.isDirectory()) {
                        //System.out.println("path=" + readfile.getPath());
                        //System.out.println("absolutepath="
                                //+ readfile.getAbsolutePath());
                        System.out.println("name=" + readfile.getName());

                    } else if (readfile.isDirectory()) {
                        readfile(filepath + "\\" + filelist[i]);
                    }
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
        }
    }

    public static String ReadFile(String Path){
        BufferedReader reader = null;
        String laststr = "";
        try{
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while((tempString = reader.readLine()) != null){
                laststr += tempString;
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }
    /*public static void main(String[] args) throws IOException {
        //PoiUtils.ReplaceWord("test");

        String[] strArray ={"a", "c", "b"};
        Arrays.sort(strArray, (s1,s2)->s1.compareTo(s2));

    }*/
}

