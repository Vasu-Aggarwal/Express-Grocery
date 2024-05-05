package com.express.grocery.Express.Grocery.util;

import com.express.grocery.Express.Grocery.dto.request.AddUpdateProductRequest;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductExcelHelper {

    public static Boolean checkExcelExtension(MultipartFile file){
        String contentType = file.getContentType();
        assert contentType != null;
        return contentType.equals("application/vnd.ms-excel")
                || contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    //convert excel to list of products

    public static List<AddUpdateProductRequest> convertExcelToProduct(InputStream inputStream, String added_by){
        List<AddUpdateProductRequest> productList = new ArrayList<>();

        try{

            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet product_data = workbook.getSheet("product_data");
            int rowNumber = 0;
            Iterator<Row> iterator = product_data.iterator();

            while(iterator.hasNext()){
                Row row = iterator.next();

                if (rowNumber==0){
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellIterator = row.iterator();
                int cid = 0;
                AddUpdateProductRequest p = new AddUpdateProductRequest();
                p.setAdded_by(added_by);
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cid){
                        case 0:
                            break;
                        case 1:
                            p.setProduct_name(cell.getStringCellValue());
                            break;
                        case 2:
                            p.setAbout_product(cell.getStringCellValue());
                            break;
                        case 3:
                            p.setProduct_price(cell.getNumericCellValue());
                            break;
                        case 4:
                            p.setIn_stock_quantity((int) cell.getNumericCellValue());
                            break;
                        case 5:
                            p.setIs_available(cell.getBooleanCellValue());
                            break;
                        case 6:
                            p.setProduct_img(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                productList.add(p);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return productList;
    }

}
