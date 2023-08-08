package com.smallworld.services.impl;

import com.smallworld.data.DataSource;
import com.smallworld.data.Dto.TransactionDto;
import com.smallworld.data.Transaction;
import com.smallworld.services.TransactionDataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class TransactionDataFetcherImpl implements TransactionDataFetcher {

    @Autowired
    private DataSource dataSource;

    /**
     * Returns the sum of the amounts of all transactions
     */
    @Override
    public double getTotalTransactionAmount() {
        return dataSource.getProcessedTransactions().stream().mapToDouble(TransactionDto::getAmount).sum();
    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    @Override
    public double getTotalTransactionAmountSentBy(String senderFullName) {
        return dataSource.getProcessedTransactions().stream()
                .filter(transaction -> transaction.getSenderFullName().equalsIgnoreCase(senderFullName))
                .mapToDouble(TransactionDto::getAmount).sum();
    }

    /**
     * Returns the highest transaction amount
     */
    @Override
    public double getMaxTransactionAmount() {
        return dataSource.getProcessedTransactions().stream()
                .max(Comparator.comparingDouble(TransactionDto::getAmount)).get().getAmount();
    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    @Override
    public long countUniqueClients() {
        Set<String> clients = new HashSet<>();
        for (TransactionDto transaction : dataSource.getProcessedTransactions()) {
            clients.add(transaction.getSenderFullName());
            clients.add(transaction.getBeneficiaryFullName());
        }
        return clients.size();
    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    @Override
    public boolean hasOpenComplianceIssues(String clientFullName) {
        for (Transaction transaction : dataSource.getAllTransactions()) {
            if ((clientFullName.equalsIgnoreCase(transaction.getSenderFullName()) || clientFullName.equalsIgnoreCase(transaction.getBeneficiaryFullName()))
                    && !transaction.isIssueSolved()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    @Override
    public Map<String, List<TransactionDto>> getTransactionsByBeneficiaryName() {
        Map<String, List<TransactionDto>> transactionsMap= new HashMap<>();
        for (TransactionDto transaction : dataSource.getProcessedTransactions()) {
            if(!transactionsMap.containsKey(transaction.getBeneficiaryFullName())){
                transactionsMap.put(transaction.getBeneficiaryFullName(), new ArrayList<>());
            }
            transactionsMap.get(transaction.getBeneficiaryFullName()).add(transaction);
        }
        return transactionsMap;
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    @Override
    public Set<Integer> getUnsolvedIssueIds() {
        return dataSource.getProcessedTransactions().stream().filter(transactionDto -> !transactionDto.isOpen()).map(TransactionDto::getMtn).collect(Collectors.toSet());
    }

    /**
     * Returns a list of all solved issue messages
     */
    @Override
    public List<String> getAllSolvedIssueMessages() {
        List<String> messageList = new ArrayList<>();

        dataSource.getAllTransactions().forEach(transaction -> {
            if (transaction.isIssueSolved() && transaction.getIssueMessage() != null)
                messageList.add(transaction.getIssueMessage());
        });
        return messageList;
    }

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    @Override
    public List<TransactionDto> getTop3TransactionsByAmount() {
        return dataSource.getProcessedTransactions().stream().sorted(Comparator.comparingDouble(TransactionDto::getAmount).reversed()).limit(3).collect(Collectors.toList());
    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    @Override
    public Optional<String> getTopSender() {
        Map<String, TransactionDto> senderAmountMap = new HashMap<>();
        dataSource.getProcessedTransactions().forEach(transactionDto -> {
                if(!senderAmountMap.containsKey(transactionDto.getSenderFullName())){
                    senderAmountMap.put(transactionDto.getSenderFullName(), transactionDto);
                }
            TransactionDto tran = senderAmountMap.get(transactionDto.getSenderFullName());
            tran.setSumAmount(tran.getSumAmount() + transactionDto.getAmount());
        });

        return dataSource.getProcessedTransactions().stream()
                .max(Comparator.comparingDouble(TransactionDto::getAmount)).map(TransactionDto::getSenderFullName);
    }

}
