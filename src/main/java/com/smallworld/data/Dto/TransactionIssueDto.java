package com.smallworld.data.Dto;

import com.smallworld.data.Transaction;

import java.util.Objects;

public class TransactionIssueDto {

    int mtn;
    int issueId;
    boolean issueSolved;
    String issueMessage;


    public TransactionIssueDto(Transaction transaction) {
        this.mtn = transaction.getMtn();
        this.issueId = transaction.getIssueId();
        this.issueSolved = transaction.isIssueSolved();
        this.issueMessage = transaction.getIssueMessage();
    }

    public int getMtn() {
        return mtn;
    }

    public void setMtn(int mtn) {
        this.mtn = mtn;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public boolean isIssueSolved() {
        return issueSolved;
    }

    public void setIssueSolved(boolean issueSolved) {
        this.issueSolved = issueSolved;
    }

    public String getIssueMessage() {
        return issueMessage;
    }

    public void setIssueMessage(String issueMessage) {
        this.issueMessage = issueMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionIssueDto)) return false;
        TransactionIssueDto that = (TransactionIssueDto) o;
        return issueId == that.issueId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(issueId);
    }
}
