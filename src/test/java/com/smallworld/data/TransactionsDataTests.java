package com.smallworld.data;

import com.smallworld.services.TransactionDataFetcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


@SpringBootTest
public class TransactionsDataTests {


    @Autowired
    TransactionDataFetcher transactionDataFetcher;

    @Test
    public void getTotalTransactionAmountTest() {
        Assert.isTrue(transactionDataFetcher.getTotalTransactionAmount() == 2889.17, "Test Failed for getTotalTransactionAmount ");
    }

    /**
     * Returns the sum of the amounts of all transactions sent by the specified client
     */
    @Test
    public void getTotalTransactionAmountSentByTest() {
        Assert.isTrue(transactionDataFetcher.getTotalTransactionAmountSentBy("Tom Shelby") == 678.06, "Test Failed for getTotalTransactionAmountSentBy ");
        Assert.isTrue(transactionDataFetcher.getTotalTransactionAmountSentBy("Aunt Polly") == 101.02, "Test Failed for getTotalTransactionAmountSentBy ");
    }

    /**
     * Returns the highest transaction amount
     */
    @Test
    public void getMaxTransactionAmountTest() {
        Assert.isTrue(transactionDataFetcher.getMaxTransactionAmount() == 985, "Test Failed for getMaxTransactionAmount ");

    }

    /**
     * Counts the number of unique clients that sent or received a transaction
     */
    @Test
    public void countUniqueClientsTest() {
        //Tom Shelby,Alfie Solomons,Arthur Shelby,Aunt Polly,Aberama Gold,Ben Younger,Oswald Mosley,MacTavern,Grace Burgess,Michael Gray,Billy Kimber,Winston Churchill,Major Campbell,Luca Changretta
        Assert.isTrue(transactionDataFetcher.countUniqueClients() == 14, "Test Failed for countUniqueClients ");

    }

    /**
     * Returns whether a client (sender or beneficiary) has at least one transaction with a compliance
     * issue that has not been solved
     */
    @Test
    public void hasOpenComplianceIssuesTest() {
        Assert.isTrue(transactionDataFetcher.hasOpenComplianceIssues("Alfie Solomons") == true, "Test Failed for hasOpenComplianceIssues ");
        Assert.isTrue(transactionDataFetcher.hasOpenComplianceIssues("Aunt Polly") == false, "Test Failed for hasOpenComplianceIssues ");

    }

    /**
     * Returns all transactions indexed by beneficiary name
     */
    @Test
    public void getTransactionsByBeneficiaryNameTest() {
        Assert.isTrue(transactionDataFetcher.getTransactionsByBeneficiaryName().get("Arthur Shelby").size() == 1, "Test Failed for getTransactionsByBeneficiaryName ");
    }

    /**
     * Returns the identifiers of all open compliance issues
     */
    @Test
    public void getUnsolvedIssueIdsTest() {
        Assert.isTrue(transactionDataFetcher.getUnsolvedIssueIds().size() == 4, "Test Failed for getUnsolvedIssueIds ");


    }

    /**
     * Returns a list of all solved issue messages
     */
    @Test
    public void getAllSolvedIssueMessagesTest() {
        Assert.isTrue(transactionDataFetcher.getAllSolvedIssueMessages().size() == 3, "Test Failed for getAllSolvedIssueMessages ");
    }

    /**
     * Returns the 3 transactions with the highest amount sorted by amount descending
     */
    @Test
    public void getTop3TransactionsByAmountTest() {
        //985, 666, 430.2
        Assert.isTrue(transactionDataFetcher.getTop3TransactionsByAmount().get(0).getAmount() == 985, "Test Failed for getTop3TransactionsByAmount ");
        Assert.isTrue(transactionDataFetcher.getTop3TransactionsByAmount().get(1).getAmount() == 666, "Test Failed for getTop3TransactionsByAmount ");
        Assert.isTrue(transactionDataFetcher.getTop3TransactionsByAmount().get(2).getAmount() == 430.2, "Test Failed for getTop3TransactionsByAmount ");
    }

    /**
     * Returns the senderFullName of the sender with the most total sent amount
     */
    @Test
    public void getTopSenderTest() {
        Assert.isTrue(transactionDataFetcher.getTopSender().get().equalsIgnoreCase("Arthur Shelby"), "Test Failed for getTopSender ");
    }


}
