package ca.pfv.createinput;
import org.apache.commons.csv.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.util.TreeMap;
import ca.pfv.spmf.algorithms.classifiers.data.CSVDataset;
import java.text.DecimalFormat;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.HashSet;
public class CSVToInputFileConverter {

    public static void main(String[] args) {
        // Get the path of the CSV file in the same directory as the Java source file
//        String csvFilePath = CSVToInputFileConverter.class.getResource("SalesOrderDetailCSV.csv").getFile();
        String csvFilePath = CSVToInputFileConverter.class.getResource("RetailSales.csv").getFile();

        // Replace "input_file.txt" with the desired output file name
        String outputFileName = "input_file.txt";
        
//        String outputDirectoryPath = "ca.pfv.createinput";      
//        String outputFilePath = Paths.get(outputDirectoryPath, outputFileName).toString();
//        String currentWorkingDirectory = System.getProperty("user.dir");
//        String fullPath = Paths.get(currentWorkingDirectory, outputFilePath).toString();
     // Print the full path for outputFilePath
        
//        System.out.println("Full Path: " + outputFilePath);
//        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] rowData = line.split(","); // Adjust the delimiter based on your CSV file format
//                for (String data : rowData) {
//                    System.out.print(data + " ");
//                }
//                System.out.println(); // Move to the next line for the next row
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try (Reader reader = new FileReader(csvFilePath);
        		CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
                FileWriter writer = new FileWriter(outputFileName)) {
	        	// Print the header
//	        	System.out.println("CSV Header: " + csvParser.getHeaderMap());
//	        	Path outputDirectory = Paths.get(outputDirectoryPath);
//	            if (!Files.exists(outputDirectory)) {
//	                Files.createDirectories(outputDirectory);
//	            }
	            // Print the content
//	            for (CSVRecord record : csvParser) {
//	                int salesOrderID = Integer.parseInt(record.get("﻿SalesOrderID"));
//	                int salesOrderDetailID = Integer.parseInt(record.get("SalesOrderDetailID"));
//	                String carrierTrackingNumber = record.get("CarrierTrackingNumber");
//	                // Continue with the other columns as needed...
//	
//	                // Print the values for each record
//	                System.out.println("SalesOrderID: " + salesOrderID +
//	                                   ", SalesOrderDetailID: " + salesOrderDetailID +
//	                                   ", CarrierTrackingNumber: " + carrierTrackingNumber);
//	                // Continue with the other columns as needed...
//	            }

            Map<Integer, Transaction> transactionsMap = new TreeMap<>();
//               Set<Integer> salesOrderIDs = new HashSet<>();
               // Process each record in the CSV file
               for (CSVRecord record : csvParser) {
            	   String salesOrderNumber = record.get("SalesOrderNumber").trim();
            	   salesOrderNumber = salesOrderNumber.replaceAll("\\D+", ""); // Remove all non-numeric characters
            	   int salesOrderID = Integer.parseInt(salesOrderNumber);
//                   int salesOrderID = Integer.parseInt(record.get("﻿SalesOrderNumber"));
                   int productID = Integer.parseInt(record.get("ProductID"));
//                   double lineTotal = Double.parseDouble(record.get("LineTotal"));
                   int lineTotal = (int) Math.round(Double.parseDouble(record.get("﻿ExtendedAmount")));
                   int totalProductCost = (int) Math.round(Double.parseDouble(record.get("TotalProductCost")));
                   transactionsMap.computeIfAbsent(salesOrderID, k -> new Transaction())
                           .addLineTotal(productID, lineTotal);
                   transactionsMap.computeIfAbsent(salesOrderID, k -> new Transaction())
                   .addTotalCost(productID, totalProductCost);
                   
//                   salesOrderIDs.add(salesOrderID);
                   
               }
//               System.out.println("Unique SalesOrderIDs:");
//               for (int salesOrderID : salesOrderIDs) {
//                   System.out.println(salesOrderID);
//               }
//            // Print the results
//               for (Map.Entry<Integer, Transaction> entry : transactionsMap.entrySet()) {
//                   int salesOrderID = entry.getKey();
//                   Transaction transaction = entry.getValue();
//
//                   System.out.print(salesOrderID + " ");
//                   System.out.print(transaction.getItems() + ":");
//                   System.out.printf("%.2f", transaction.getTransactionUtility());
//                   System.out.println();
//               }
               // Write the results to the output file
               for (Map.Entry<Integer, Transaction> entry : transactionsMap.entrySet()) {
                   int salesOrderID = entry.getKey();
                   Transaction transaction = entry.getValue();

                   int transactionUtility = transaction.getTransactionUtility();
                   String items = transaction.getItems();
                   
//                   String lineTotalOfEachItem = transaction.getLineTotalOfItems();
//                   writer.write(salesOrderID + " " + items + ":" + transactionUtility + ":" + lineTotalOfEachItem + "\n");
                  
                   String totalCostOfEachItem = transaction.getCostOfProduct();
//                   writer.write(salesOrderID + " " + items + ":" + transactionUtility + ":" + totalCostOfEachItem + "\n");
 
                   writer.write(salesOrderID + " " + items + ":" + transactionUtility + ":" + totalCostOfEachItem + "\n");

               }

           } catch (IOException e) {
               e.printStackTrace();
           }

           // Read and print the output file
        try (BufferedReader reader = new BufferedReader(new FileReader(outputFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static class Transaction {
        private Map<Integer, Double> lineTotalsMap = new HashMap<>();
        private Map<Integer, Integer> totalCostMap = new HashMap<>();

        void addLineTotal(int productID, double lineTotal) {
            lineTotalsMap.put(productID, lineTotal);
        }
        void addTotalCost(int productID, int totalCost) {
        	totalCostMap.put(productID,totalCost);
        }

        int getTransactionUtility() {
            int sum = lineTotalsMap.values().stream()
                    .mapToInt(Double::intValue) // Convert each value to int (truncating decimal part)
                    .sum();
            return sum; // Return the computed sum

//            DecimalFormat decimalFormat = new DecimalFormat("#.##");
//            return Double.parseDouble(decimalFormat.format(sum));
        }

        String getItems() {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<Integer, Double> entry : lineTotalsMap.entrySet()) {
                sb.append(entry.getKey()).append(" ");
            }

            // Remove the last space if the StringBuilder is not empty
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }

            return sb.toString();
        }


//        String getLineTotalOfItems() {
//            StringBuilder sb = new StringBuilder();
//            int sumOfLineTotals = 0;
//
//            for (Map.Entry<Integer, Double> entry : lineTotalsMap.entrySet()) {
//                int productID = entry.getKey();
//                int lineTotal = entry.getValue().intValue(); // Get integer value
//                sb.append(lineTotal).append(" ");
//                sumOfLineTotals += lineTotal;
//            }
//            return sb.toString();
//        }
        String getCostOfProduct() {
            StringBuilder sb = new StringBuilder();

            for (Map.Entry<Integer, Integer> entry : totalCostMap.entrySet()) {
                int productID = entry.getKey();
                int totalCost = entry.getValue().intValue(); // Get integer value
                sb.append(totalCost).append(" ");
            }
            return sb.toString();
        }

    }
}
