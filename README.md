# LCIM-Low-Cost-High-Utility-Itemsets-Mining
![image](https://github.com/HiIAmHuong/LCIM-Low-Cost-High-Utility-Itemsets-Mining/assets/124865073/133a9611-8a54-4b5f-acc7-408eb968a509)

Figure 1: LCIM Algorithm

## Proposed methodology 
![image](https://github.com/HiIAmHuong/LCIM-Low-Cost-High-Utility-Itemsets-Mining/assets/124865073/4eac6fa9-50a1-4b48-9ef6-780e37041c8a)

Figure 2. Proposed methodology

As depicted in Figure 1 above, this section delineates the comprehensive methodology employed to accomplish the research objectives. The methodology consists of four meticulous stages, each thoughtfully crafted and executed to ensure the integrity and precision of the results. These stages encompass data selection, exploratory data analysis (EDA), data transformation, algorithmic application, and culminate in conclusive shopping cart and pattern analyses.

## Experimental Result and Analysis STAGE
### Pre-processing
The data transformation process comprises the following steps:

Step 0: Creation of a Package and Java File within the Package
The initial step involves creating a designated package and a Java file within it to facilitate organized code structuring.

Step 1: Importing Relevant Libraries
Appropriate libraries are imported to enable the necessary functionalities. These include java.io.FileReader, java.io.FileWriter, java.io.IOException, java.io.Reader, java.io.BufferedReader, java.util.HashMap, java.util.Map, and java.util.TreeMap.

Step 2: Parsing CSV Data
The program reads the CSV file named "RetailSales.csv". The Apache Commons CSV library is utilized to parse the CSV file's syntax using CSVParser. The default format and headers are employed for this parsing.

Step 3: Data Processing and Result Storage
Each record (row) in the CSV file is processed individually. Essential fields (such as SalesOrderNumber, ProductID, ExtendedAmount, TotalProductCost) are extracted from the CSVRecord. The program executes necessary transformations and calculations, saving the results in a Map<Integer, Transaction> named transactionsMap.

Step 4: Transaction Class Creation
A Transaction class is established to manage and process data for each transaction, identified by SalesOrderID. This class encompasses methods to aggregate total lines and costs for different products, compute transaction utility, retrieve ProductID lists, and obtain costs of individual products within a transaction.

Step 5: Writing Results to Output File

Upon processing all records in the CSV file, the program writes the outcomes into an output file named "input_file.txt" using FileWriter. The results are formatted specifically as "ProductIDs:TransactionUtility:TotalCostOfEachItem".

By meticulously following these steps, the data transformation phase ensures the conversion of the initial tabular data into a structured text format that is optimally suited for subsequent analysis using LCIM (Low-Cost High Utility Cost) algorithms. This transformation process ensures data integrity and appropriateness for the overarching analytical objectives of the study.

![image](https://github.com/HiIAmHuong/LCIM-Low-Cost-High-Utility-Itemsets-Mining/assets/124865073/c817a92a-cdee-4424-9ce9-21b03f1a0f98)
Table 1. A part of original dataset

As indicated in Table 1, the initial dataset is presented in a tabular format. However, following the completion of the transformation stage, the dataset undergoes a conversion process resulting in a text-based format, such as the example:
"359 474 475 476:3258:2504 209 79 26". 
The significance of the data line is outlined below:
- "359 474 475 476" constitutes a set of items representing nominal values, specifically denoting the ProductID associated with each item within the transaction.
- "3258" represents a utility value expressed as an integer. This value is attributed to the transaction, signifying the total revenue generated from the transaction.
- "2504 209 79 26" indicates cost values expressed as integers corresponding to each individual item within the transaction. In precise terms, these values denote the cost associated with each respective ProductID within the transaction.

Through this comprehensive explanation, the intricate components of the transformed dataset become clear. The adoption of this structured text format serves to facilitate subsequent analysis employing the Low-Cost High Utility Cost (LCIM) algorithms, allowing for the identification of latent patterns and associations within the dataset.

## LCIM algorithm and evaluator
After we got the converted input txt file with standard format, we continue to set the appropriate parameters.

![image](https://github.com/HiIAmHuong/LCIM-Low-Cost-High-Utility-Itemsets-Mining/assets/124865073/ba5ac9fa-606e-4222-acb0-08d98b363b8e)
Table 2: Parameters value of LCIM algorithm

As depicted in Table X, this entails that the resulting outcome needs to meet the following criteria:
- The average utility must not fall below the minimum threshold of 40,000 monetary units.
- The average cost must not exceed the maximum threshold of 10,000 monetary units.
- The support (frequency of occurrence) must be greater than or equal to the minimum threshold of 10% within the dataset set.

![image](https://github.com/HiIAmHuong/LCIM-Low-Cost-High-Utility-Itemsets-Mining/assets/124865073/3b941eab-3139-4c17-81db-55bee00f8a6a)
Table 3. The result of using LCIM algorithm

As shown in table X, the aforementioned outcomes encompass all the category groups representing several effective product clusters within sales. These groups consist of product combinations that lead to high profitability and do not entail significant expenditures within a designated timeframe.
For a more thorough clarification of the outcomes, consider the instance presented in the sixth row:
Data Line: "233 458 224 #AUTIL: 40859.324120603014 #ACOST: 503.68090452261305 #SUP: 398"
- An "itemset" constitutes a collection of "productID" entities, as demonstrated by "233 458 224".
- The "Utility" of an "itemset" represents the cumulative revenue generated by the given "itemset". In this instance, the value of "AUTIL" stands at "40,859", computed by aggregating the utilities of all "itemsets" and subsequently dividing it by its own "support".
- The "Cost" associated with this "itemset" pertains to the expenses incurred for each individual product. "ACOST", quantified at "503", represents the average cost of this specific "itemset". This is determined by dividing the total cost by its own "support".
- Consequently, the "support" metric of this "itemset" serves as the count of transactions that include this particular "itemset". In this context, the value is "398", indicating the presence of this "itemset" within a total of 398 transactions.
This illustrative example offers a clear breakdown of how the various components interrelate within the context of an "itemset", emphasizing the role of "utility", "cost", and "support" in comprehending its significance within the dataset.

## Pattern analysis

![image](https://github.com/HiIAmHuong/LCIM-Low-Cost-High-Utility-Itemsets-Mining/assets/124865073/e98b19a6-0e2d-404c-b275-01a36f46f390)
Table 4: The trade-off of each pattern

As evident from the data presented in Table 4, it is notable that the first data line possesses the lowest trade-off value, measuring at an impressively minimal "0.0037". This value epitomizes excellence and stands as an exemplar of optimal equilibrium between cost and benefit. The significance of this observation lies in its ability to yield the most favorable outcome, aligning seamlessly with the principles of Low-Cost High Revenue strategy. This unique attribute positions the identified data line as a prime candidate for strategic consideration by businesses aiming to elevate their revenue streams. The data's inherent potential to deliver high revenue while maintaining prudent expenditure places it in a strategic advantage, urging enterprises to carefully deliberate upon this particular itemset with the prospect of fostering significant revenue growth.
