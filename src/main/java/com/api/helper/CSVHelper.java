package com.api.helper;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CSVHelper {

    public static ByteArrayInputStream duplicateUPCsToCSV(List<Object[]> data) {
        final CSVFormat format = CSVFormat.DEFAULT.withHeader("Sr. No", "UPC","No of UPCs","SKU 1","Price 1","Stock 1","SKU 2","Price 2","Stock 2","SKU 3","Price 3","Stock 3","SKU 4","Price 4","Stock 4","SKU 5","Price 5","Stock 5");

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
            for (Object[] record : data) {
                csvPrinter.printRecord(record);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate CSV", e);
        }
    }
    public static ByteArrayInputStream notDuplicateUPCsToCSV(List<Object[]> data) {
        final CSVFormat format = CSVFormat.DEFAULT.withHeader("Sr. No", "SKU","UPC", "Gender","Product Name","Manufacturer Name","Price","Stock");

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
            for (Object[] record : data) {
                csvPrinter.printRecord(record);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate CSV", e);
        }
    }

    public static ByteArrayInputStream lowestPricesToCSV(List<Object[]> data) {
        final CSVFormat format = CSVFormat.DEFAULT.withHeader("Sr. No","SKU", "UPC","Gender","Product Name","Manufacturer Name","Price","Stock");

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
            for (Object[] record : data) {
                csvPrinter.printRecord(record);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate CSV", e);
        }
    }

    public static ByteArrayInputStream highestPricesToCSV(List<Object[]> data) {
        final CSVFormat format = CSVFormat.DEFAULT.withHeader("Sr. No","SKU", "UPC","Gender","Product Name","Manufacturer Name","Price", "Stock");

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
            for (Object[] record : data) {
                csvPrinter.printRecord(record);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate CSV", e);
        }
    }

    public static ByteArrayInputStream Bottom10PercentileProductsByPrice(List<Object[]> data) {
        final CSVFormat format = CSVFormat.DEFAULT.withHeader("Sr.No","SKU", "UPC","Gender","Product Name","Manufacturer Name","Price", "Stock");

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
            for (Object[] record : data) {
                csvPrinter.printRecord(record);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate CSV", e);
        }
    }

    public static ByteArrayInputStream Highest10PercentileProductsByPrice(List<Object[]> data) {
        final CSVFormat format = CSVFormat.DEFAULT.withHeader("Sr. No","SKU", "UPC","Gender","Product Name","Manufacturer Name","Price", "Stock");

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
            for (Object[] record : data) {
                csvPrinter.printRecord(record);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate CSV", e);
        }
    }

    public static ByteArrayInputStream outOfStockUPCsToCSV(List<Object[]> data) {
        final CSVFormat format = CSVFormat.DEFAULT.withHeader("SKU", "UPC","Gender","Manufacturer","Name","Price", "Stock_Available");

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
            for (Object[] record : data) {
                csvPrinter.printRecord(record);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate CSV", e);
        }
    }
}
