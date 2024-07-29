package steps;

import io.cucumber.java.*;
import io.cucumber.java.en.*;
import tools.Tools;

import static pageObjects.LoginPagePO.*;
import static tools.WebDriverManager.*;

public class LoginSteps extends Tools {


    @Given("Lucky Bandit page is opened")
    public void lucky_bandit_page_is_opened() {
        getWebDriver().get("https://luckybandit.club.test-delasport.com/en");
    }

    @When("Login with test user")
    public void login_with_test_user() {
        loginWithUser("tu_nino", "Pass112#"); //can be set as Scenario Outline

    }

    @Then("verify user balance")
    public void verify_user_balance() {
        // Write code here that turns the phrase above into concrete actions

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
