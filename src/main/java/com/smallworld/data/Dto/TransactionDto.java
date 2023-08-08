package com.smallworld.data.Dto;

import com.smallworld.data.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransactionDto {
    private int mtn;
    private double amount;
    private String senderFullName;
    private int senderAge;
    private String beneficiaryFullName;
    private int beneficiaryAge;
    private double sumAmount = 0;

    List<TransactionIssueDto> transactionIssueDtos = new ArrayList<>();

    public TransactionDto(Transaction transaction) {
        this.mtn = transaction.getMtn();
        this.amount = transaction.getAmount();
        this.senderFullName = transaction.getSenderFullName();
        this.senderAge = transaction.getSenderAge();
        this.beneficiaryFullName = transaction.getBeneficiaryFullName();
        this.beneficiaryAge = transaction.getBeneficiaryAge();
        this.transactionIssueDtos.add(new TransactionIssueDto(transaction));
    }

    public int getMtn() {
        return mtn;
    }

    public void setMtn(int mtn) {
        this.mtn = mtn;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSenderFullName() {
        return senderFullName;
    }

    public void setSenderFullName(String senderFullName) {
        this.senderFullName = senderFullName;
    }

    public int getSenderAge() {
        return senderAge;
    }

    public void setSenderAge(int senderAge) {
        this.senderAge = senderAge;
    }

    public String getBeneficiaryFullName() {
        return beneficiaryFullName;
    }

    public void setBeneficiaryFullName(String beneficiaryFullName) {
        this.beneficiaryFullName = beneficiaryFullName;
    }

    public int getBeneficiaryAge() {
        return beneficiaryAge;
    }

    public void setBeneficiaryAge(int beneficiaryAge) {
        this.beneficiaryAge = beneficiaryAge;
    }

    public List<TransactionIssueDto> getTransactionIssueDtos() {
        return transactionIssueDtos;
    }

    public void setTransactionIssueDtos(List<TransactionIssueDto> transactionIssueDtos) {
        this.transactionIssueDtos = transactionIssueDtos;
    }

    public void addIssue(TransactionIssueDto issueDto) {
        if (!this.transactionIssueDtos.contains(issueDto)) {
            this.transactionIssueDtos.add(issueDto);
        }
    }

    public boolean isOpen() {
        for (TransactionIssueDto transactionIssueDto : getTransactionIssueDtos()) {
            if (!transactionIssueDto.issueSolved) {
                return false;
            }
        }
        return true;
    }

    public double getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(double sumAmount) {
        this.sumAmount = sumAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionDto)) return false;
        TransactionDto that = (TransactionDto) o;
        return mtn == that.mtn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mtn);
    }
}
