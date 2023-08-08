package com.smallworld.services;

import com.smallworld.data.Dto.TransactionDto;
import com.smallworld.data.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


public interface TransactionDataFetcher {

    /**
     * Returns the sum of the amounts of all transactions
     */
    public double getTotalTransactionAmount() throws UnsupportedOperationException;

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    public double getTotalTransactionAmountSentBy(String senderFullName);

    /**
     * Returns the highest transaction amount
     */
    public double getMaxTransactionAmount();

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    public long countUniqueClients();

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    public boolean hasOpenComplianceIssues(String clientFullName);

    /**
     * Returns all transactions indexed by beneficiary name
     */
    public Map<String, List<TransactionDto>> getTransactionsByBeneficiaryName(); // changed for multi transaction scenario for single beneficiary

    /**
     * Returns the identifiers of all open compliance issues
     */
    public Set<Integer> getUnsolvedIssueIds();

    /**
     * Returns a list of all solved issue messages
     */
    public List<String> getAllSolvedIssueMessages();

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    public List<TransactionDto> getTop3TransactionsByAmount();

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    public Optional<String> getTopSender();

}
