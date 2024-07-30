package tools;

import models.TestUserVariables;

public class ThreadLocals {

    /**
     * Create empty thread local for user
     */
    public static ThreadLocal<TestUserVariables> user = new ThreadLocal<>() {
    };


    /**
     * Set User Object in ThreadLocal list
     *
     * @param userName     = user we will be using
     * @param userPassword = password of the user
     */
    public static void setThreadUser(String userName, String userPassword) {
        TestUserVariables user = new TestUserVariables(userName, userPassword);
        ThreadLocals.user.set(user);
    }

}
