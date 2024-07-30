package models;

import tools.Tools;

public class TestUserVariables extends Tools {
    /**
     * Add the bare minimum variables for this test assignment
     */
    private String userName;
    private String userPassword;
    private String userBalance;
    private String userBalanceInPostRequest;

    /**
     * create object for thread locals
     *
     * @param userName     = user we will be using
     * @param userPassword = password of the user
     */
    public TestUserVariables(String userName, String userPassword) {
        setUserName(userName);
        setUserPassword(userPassword);
    }

    /**
     * Getters and setters
     */
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserBalance() {
        return this.userBalance;
    }

    public void setUserBalance(String userBalance) {
        this.userBalance = userBalance;
    }

    public String getUserBalanceInPostRequest() {
        return this.userBalanceInPostRequest;
    }

    public void setUserBalanceInPostRequest(String userBalanceInPostRequest) {
        this.userBalanceInPostRequest = userBalanceInPostRequest;
    }
}
