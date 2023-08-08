package com.smallworld.data;

import com.google.gson.Gson;
import com.smallworld.data.Dto.TransactionDto;
import com.smallworld.data.Dto.TransactionIssueDto;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


@Service
public class DataSource {

    private final String JSON_FILE_PATH = "transactions.json";
    private List<Transaction> allTransactions;
    private List<TransactionDto> processedTransactions;
    private boolean isProcessed = false;

    public boolean loadData() {
        File jsonFile = new File(JSON_FILE_PATH);
        StringBuilder sb;
        Scanner scanner = null;
        try {
            scanner = new Scanner(jsonFile);
            sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }
            Transaction[] transactionData = new Gson().fromJson(String.valueOf(sb), Transaction[].class);
            allTransactions = new ArrayList<>(Arrays.asList(transactionData));
            this.isProcessed = false;
        } catch (FileNotFoundException e) {
            System.out.println("Error Loading File Data: " + e.getMessage());
            return false;
        } finally {
            scanner.close();
        }
        return true;
    }

    public List<Transaction> getAllTransactions() {
        if(allTransactions == null){
            loadData();
        }
        return allTransactions;
    }

    private void processTransactions(){
        processedTransactions = new ArrayList<>();
        getAllTransactions().forEach(transaction -> {
            TransactionDto currentObj = new TransactionDto(transaction);
            if(!processedTransactions.contains(currentObj)){
                processedTransactions.add(currentObj);
            }else{
                processedTransactions.get(processedTransactions.indexOf(currentObj)).addIssue(new TransactionIssueDto(transaction));
            }
        });
        this.isProcessed = true;
    }

    public List<TransactionDto> getProcessedTransactions() {
        if(processedTransactions == null || !isProcessed){
            processTransactions();
        }
        return processedTransactions;
    }


}
