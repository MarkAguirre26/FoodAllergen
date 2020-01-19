package com.thesis.pdm.hallergen;

public class ModelsContact {

    private String contactUID;
    private String accountID;
    private String contactName;
    private String contactNumber;
    private String contactRelation;
    private boolean isReceiveMessage;


    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getContactUID() { return contactUID; }

    public void setContactUID(String contactUID) { this.contactUID = contactUID; }

    public String getContactName() { return contactName; }

    public void setContactName(String contactName) { this.contactName = contactName; }

    public String getContactNumber() { return contactNumber; }

    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getContactRelation() { return contactRelation; }

    public void setContactRelation(String contactRelation) { this.contactRelation = contactRelation; }

    public boolean isReceiveMessage() { return isReceiveMessage; }

    public void setReceiveMessage(boolean receiveMessage) { isReceiveMessage = receiveMessage; }





}
