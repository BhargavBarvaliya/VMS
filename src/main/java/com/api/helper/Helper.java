package com.api.helper;

import com.api.entity.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Helper {

//    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    //check that file is of Excel type or not
    public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") ||
                contentType.equals("text/csv")
        )
        {
            return true;
        } else {
            return false;
        }

    }


    //convert excel to list of products

    public static List<Product> convertExcelToListOfProduct(InputStream is) {
        List<Product> list = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheetAt(0);
            System.out.println(workbook.getSheetAt(0));

            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();
                int cid = 0;
                Product p = new Product();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                        case 0:
                            p.setSKU(cell.getStringCellValue());
                            break;
                        case 1:
                            p.setUPC(cell.getStringCellValue());
                            break;
                        case 2:
                            p.setProductName(cell.getStringCellValue());
                            break;
                        case 3:
                            p.setProductPrice(cell.getNumericCellValue());
                            break;
//                        case 4:
//                            p.setProductWeight(cell.getNumericCellValue());
//                            break;
                        case 4:
                            p.setProductStocks((int)cell.getNumericCellValue());
                            break;
                        case 5:
                            p.setProductManufacturer(cell.getStringCellValue());
                            break;
                        case 6:
                            p.setGender(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
    // Convert CSV to list of products
    public static List<Product> convertCsvToListOfProduct(InputStream is) {
        List<Product> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
//                Iterable<CSVRecord> csvRecords = csvParser.getRecords();

                String[] data = line.split(",");
                Product p = new Product();
                p.setSKU(data[0]);
                p.setUPC(data[1]);
                p.setProductName(data[2]);
                p.setProductPrice(Double.parseDouble(data[3]));
                p.setProductStocks(Integer.parseInt(data[4]));
               // p.setProductWeight(Double.parseDouble(data[5]));
                p.setProductManufacturer(data[5]);
                p.setGender(data[6]);
                //p.setAddedDate(LocalDateTime.parse(data[7]));
                // Parse the date field if exists
//                if (p.isSet("addedDate")) {
//                    LocalDateTime addedDate = LocalDateTime.parse(csvRecord.get("addedDate"), DATE_FORMATTER);
//                    p.setAddedDate(addedDate);
//                }
                list.add(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
