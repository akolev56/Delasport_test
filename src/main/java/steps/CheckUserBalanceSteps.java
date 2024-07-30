package steps;

import io.cucumber.java.*;
import io.cucumber.java.en.*;
import tools.Tools;

import static pageObjects.LoginPagePO.*;
import static pageObjects.MainPagePO.*;
import static tools.ThreadLocals.setThreadUser;
import static tools.ThreadLocals.user;
import static tools.WebDriverManager.*;


public class CheckUserBalanceSteps extends Tools {

    @Given("^Open page and Login with test username: (.*?) and password: (.*?)$")
    public void open_page_and_login_with_test_username_and_password(String userName, String userPassword) {
        setThreadUser(userName, userPassword);
        enableNetworkTracking();
        addRequestListener();
        getWebDriver().get("https://luckybandit.club.test-delasport.com/en");
        loginWithUser(); //can be set as Scenario Outline

    }

    @When("Get POST request for user balance")
    public void Get_POST_request_for_user_balance() {
        addResponseListener("getMemberBalance");

        user.get().setUserBalance(getUserBalanceValue().getText().split("€")[1]);
        parseEndPointResponse();

        verifyWebElementPresent(getUserBalanceValue());
    }

    @Then("verify user balance")
    public void verify_user_balance() {
        assertTextsAreEqual(user.get().getUserBalance(), user.get().getUserBalanceInPostRequest());
    }

    @Before
    public void beforeAll() {
        buildDriver();
    }

    @After
    public void afterAll() {
        quitDriver();
    }


}
