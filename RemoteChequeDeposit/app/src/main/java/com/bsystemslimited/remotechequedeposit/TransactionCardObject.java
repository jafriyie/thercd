package com.bsystemslimited.remotechequedeposit;

/**
 * Created by Jason on 08/07/2016.
 */
public class TransactionCardObject {

    private int TransactionCardObjectId;
    private String Status;
    private String TransactionID;
    private String RecipientAccount;
    private String DateOfTransaction;
    private String MoneyTransfered;

    public TransactionCardObject(int TransactionCardObjectId, String TransactionID, String RecipientAccount,String DateOfTransaction,String MoneyTransfered, String Status)
    {
        this.TransactionCardObjectId = TransactionCardObjectId;
        this.TransactionID = TransactionID;
        this.RecipientAccount = RecipientAccount;
        this.MoneyTransfered = MoneyTransfered;
        this.DateOfTransaction = DateOfTransaction;
        this.Status = Status;
    }

    public int getTransactionCardObjectId() { return TransactionCardObjectId; }
    public String getTransactionID() { return TransactionID; }
    public String getRecipientAccount() { return RecipientAccount; }
    public String getMoneyTransfered() { return MoneyTransfered; }
    public String getDateOfTransaction() { return DateOfTransaction; }
    public String getStatus(){return Status;}

}
