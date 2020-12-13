package iit.utils;
import iit.models.Cust;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

import java.util.ArrayList;
import java.util.List;


public class ExcelUtil {

    public static int totalRows; //sheet中总行数
    public static int totalCells; //每一行总单元格数


    /**
     * read the Excel .xlsx,.xls
     * @return
     * @throws IOException
     */
    public static List<Cust> readExcel(File file) throws IOException {
        if(file==null){
            return null;
        }else{
            String postfix = ExcelTool.getPostfix(file.getName());
            if(!ExcelTool.EMPTY.equals(postfix)){
                if(ExcelTool.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)){
                    return readXls(file);
                }else if(ExcelTool.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)){
                    return readXlsx(file);
                }else{
                    return null;
                }
            }
        }
        return null;
    }


    /**
     * read the Excel 2010 .xlsx
     * @param file
     * @return
     * @throws IOException
     */
    @SuppressWarnings("deprecation")
    public static List<Cust> readXlsx(File file){
        Cust cust;

        List<Cust> custList = new ArrayList<>();
        // IO流读取文件
        InputStream input = null;
        XSSFWorkbook wb = null;
        ArrayList<String> rowList = null;
        try {
            input = new FileInputStream(file);
            // 创建文档
            wb = (XSSFWorkbook) WorkbookFactory.create(input);
            //读取sheet(页)
            for(int numSheet=0;numSheet<wb.getNumberOfSheets();numSheet++){
                XSSFSheet xssfSheet = wb.getSheetAt(numSheet);
                if(xssfSheet == null){
                    continue;
                }
                totalRows = xssfSheet.getLastRowNum();
                //读取Row,从第二行开始
                for(int rowNum = 0;rowNum <= totalRows;rowNum++){
                    XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                    if(xssfRow!=null){

                        cust = new Cust();
                        totalCells = xssfRow.getLastCellNum();
                        if(IitUtil.isNumeric(ExcelTool.getXValue( xssfRow.getCell(0)).trim())) {
                            for (int c = 0; c <= totalCells + 1; c++) {
                                XSSFCell cell = xssfRow.getCell(c);

                                switch (c) {
                                    case 0:
                                        cust.setPhone(ExcelTool.getXValue(cell).trim());
                                        break;
                                    case 1:
                                        cust.setName(ExcelTool.getXValue(cell).trim());
                                        break;
                                    case 2:
                                        cust.setSex(ExcelTool.getXValue(cell).trim());
                                        break;
                                    case 3:
                                        cust.setAddress(ExcelTool.getXValue(cell).trim());
                                        break;
                                    case 4:
                                        cust.setProducts(ExcelTool.getXValue(cell).trim());
                                        break;
                                    default:
                                        break;
                                }

                            }
                            custList.add(cust);
                        }
                    }
                }
            }
            return custList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }
    /**
     * read the Excel 2003-2007 .xls
     * @param file
     * @return
     * @throws IOException
     */
    public static List<Cust> readXls(File file){
        Cust cust;

        List<Cust> custList = new ArrayList<>();
        // IO流读取文件
        InputStream input = null;
        HSSFWorkbook wb = null;
        ArrayList<String> rowList = null;
        try {
            input = new FileInputStream(file);
            // 创建文档
            wb = (HSSFWorkbook) WorkbookFactory.create(input);
            //读取sheet(页)
            for(int numSheet=0;numSheet<wb.getNumberOfSheets();numSheet++){
                HSSFSheet hssfSheet = wb.getSheetAt(numSheet);
                if(hssfSheet == null){
                    continue;
                }
                totalRows = hssfSheet.getLastRowNum();
                //读取Row,从第二行开始
                for(int rowNum = 0;rowNum <= totalRows;rowNum++){
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if(hssfRow!=null){
                        cust = new Cust();
                        totalCells = hssfRow.getLastCellNum();
                            if(IitUtil.isNumeric(ExcelTool.getHValue( hssfRow.getCell(0)).trim()))
                            {
                                for (short c = 0; c <= totalCells + 1; c++) {
                                    HSSFCell cell = hssfRow.getCell(c);
                                    switch (c) {
                                        case 0:
                                            cust.setPhone(ExcelTool.getHValue(cell).trim());
                                            break;
                                        case 1:
                                            cust.setName(ExcelTool.getHValue(cell).trim());
                                            break;
                                        case 2:
                                            cust.setSex(ExcelTool.getHValue(cell).trim());
                                            break;
                                        case 3:
                                            cust.setAddress(ExcelTool.getHValue(cell).trim());
                                            break;
                                        case 4:
                                            cust.setProducts(ExcelTool.getHValue(cell).trim());
                                            break;
                                        default:
                                            break;
                                    }
                                }
                                custList.add(cust);
                            }
                    }
                }
            }
            return custList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



}