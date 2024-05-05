package com.express.grocery.Express.Grocery.util;

import com.express.grocery.Express.Grocery.dto.request.AddUpdateProductRequest;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
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

            for (Row row : product_data) {
                if (rowNumber == 0) { // Skip header row
                    rowNumber++;
                    continue;
                }

                AddUpdateProductRequest p = new AddUpdateProductRequest();
                p.setAddedBy(added_by);

                for (int cid = 0; cid <= 7; cid++) {
                    Cell cell = row.getCell(cid, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                    switch (cid) {
                        case 0:
                            break; // Skip the first cell
                        case 1:
                            p.setProductName(cell.getStringCellValue());
                            break;
                        case 2:
                            p.setAboutProduct(cell.getStringCellValue());
                            break;
                        case 3:
                            if (cell.getCellType() == CellType.NUMERIC) {
                                p.setProductPrice(cell.getNumericCellValue());
                            } else {
                                // Handle non-numeric values or empty cells
                            }
                            break;
                        case 4:
                            if (cell.getCellType() == CellType.NUMERIC) {
                                p.setInStockQuantity((int) cell.getNumericCellValue());
                            } else {
                                // Handle non-numeric values or empty cells
                            }
                            break;
                        case 5:
                            p.setIsAvailable(cell.getBooleanCellValue());
                            break;
                        case 6:
                            p.setProductImg(cell.getStringCellValue());
                            break;
                        case 7:
                            String categoriesCellValue = cell.getStringCellValue();
                            if (!categoriesCellValue.isEmpty()) {
                                List<String> categories = List.of(categoriesCellValue.split(",\\s*"));
                                p.setCategories(categories);
                            }
                            break;
                        default:
                            break;
                    }
                }

                productList.add(p);
                rowNumber++;
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return productList;
    }

}
