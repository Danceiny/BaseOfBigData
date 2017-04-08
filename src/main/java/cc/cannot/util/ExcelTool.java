package cc.cannot.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Created by huangzhen on 4/6/2017.
 */
public class ExcelTool {
    public static void write2excel(String filename, int rows, int cols, Object[][] value ){
        //create a new file
        FileOutputStream foStream = null;
        File file = new File(filename);

        Workbook wb = null;
        Sheet sheet = null;

        int lastRowNum = 0;

        if(file.exists()){
            try{
                FileInputStream fiStream = new FileInputStream(filename);
                if(filename.endsWith("xls")){
                    wb = new HSSFWorkbook(fiStream);
                }
                else if(filename.endsWith("xlsx")){
                    wb = new XSSFWorkbook(fiStream);
                }
                else{
                    System.out.println("The file type is not supported!!!");
                    return;
                }
                sheet = wb.getSheetAt(0);   //一个xls文件（即一个工作簿）可能有多个工作表

                lastRowNum = sheet.getLastRowNum()+1;
                fiStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            // the file does not exist
            if(filename.endsWith("xls")){
                wb = new HSSFWorkbook();
            }
            else if(filename.endsWith("xlsx")){
                wb = new XSSFWorkbook();
            }
            else{
                System.out.println("The file type is not supported!!!");
                return;
            }
            sheet = wb.createSheet();
        }

        Row row = null;
        for(int i=0; i<rows; i++){
            row = sheet.createRow(lastRowNum+1);
            for(int j=0; j<cols; j++)
            {
                row.createCell(j).setCellValue(convertString(value[i][j]));
            }
        }

        try{
            foStream = new FileOutputStream(file);
            wb.write(foStream);
            wb.close();
            foStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<List<String>> readExcel(String filePath){
        List<List<String>> content = null;
        InputStream iStream = null;
        Workbook wb = null;
        try{
            iStream = new FileInputStream(filePath);
            if(filePath.endsWith(".xls")){
                wb = new HSSFWorkbook(iStream);
            }
            else if (filePath.endsWith(".xlsx")){
                wb = new XSSFWorkbook(iStream);
            }
            else {
                throw new Exception("The file type is not supported!!!");
            }

            content = new ArrayList<List<String>>();
            Sheet sheet = wb.getSheetAt(0);
            List<String> rowList = null;
            for (int i=0; i<sheet.getLastRowNum()+1; i++) {
                Row row = sheet.getRow(i);
                rowList = new ArrayList<String>();
                if (row != null) {
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        Cell cell = row.getCell(j);
                        if (cell == null) {
                            rowList.add("");
                        } else {
                            try {
                                rowList.add(cell.toString());
                            } catch (Exception e) {
                                continue;
                            }
                        }
                    }
                }
                content.add(rowList);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(wb!=null){
                    wb.close();
                }
                if(iStream != null){
                    iStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return content;
    }
    public static String convertString(Object value){
        //TODO
        if(value == null){
            return "";
        }
        else{
            return value.toString();
        }
    }


}
