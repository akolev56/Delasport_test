package tools;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v127.network.Network;
import org.openqa.selenium.devtools.v127.network.model.Request;
import org.openqa.selenium.devtools.v127.network.model.RequestId;
import org.openqa.selenium.devtools.v127.network.model.Response;


import java.util.Optional;

import static tools.ThreadLocals.user;

public class WebDriverManager extends Tools {
    private static WebDriver driver;
    private static DevTools devTools;
    private static String endPointResponseBody;

    /**
     * Build webDriver with specific options and dev tools
     */
    public static void webDriver() {
        if (driver == null) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-search-engine-choice-screen");
            chromeOptions.addArguments("--window-size=1920,1080");
            chromeOptions.addArguments("--disable-popup-blocking");
            chromeOptions.addArguments("--incognito");
            driver = new ChromeDriver(chromeOptions);
            devTools = ((ChromeDriver) driver).getDevTools();
            devTools.createSession();

        }
    }

    /**
     * Enable Network Tracking for request and response listeners
     */
    public static void enableNetworkTracking() {
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
    }


    /**
     * Add requestListener if needed for debugging or checking the requests
     */
    public static void addRequestListener() {
        devTools.addListener(Network.requestWillBeSent(), requestConsumer -> {
            Request res = requestConsumer.getRequest();
            System.out.println("Request: " + res.getUrl());
        });
    }

    /**
     * Add response listener to collect and save responseBody of specific end point
     *
     * @param containsEndPoint = end point from url that contains specific request
     *                         Example: getMemberBalance
     */
    public static void addResponseListener(String containsEndPoint) {
        devTools.addListener(Network.responseReceived(), responseConsumer -> {
            Response res = responseConsumer.getResponse();
            RequestId requestId = responseConsumer.getRequestId();
            System.out.println("Response: " + res.getStatus() + " " + res.getUrl());

            if (res.getUrl().contains(containsEndPoint)) {
                System.out.println("Response: " + res.getStatus() + " " + res.getUrl());
                String responseBody = devTools.send(Network.getResponseBody(requestId)).getBody();
                System.out.println("Response Body for endPoint '" + containsEndPoint + "' is: " + responseBody);
                endPointResponseBody = responseBody; // Store the response
            }
        });
    }

    /**
     * This method saves the response body from addResponseListener and is used when parsing the response
     *
     * @return responseBody
     */
    private static String getEndPointResponseBody() {
        return endPointResponseBody;
    }

    /**
     * This method will parse the response and will get raw_amount for Balance creditType
     * It can be modified to be much more powerful and check all the data and return whatever is needed.
     * I use it to set the POST value of getMemberBalance to users threadLocal
     */
    public static void parseEndPointResponse() {
        String balanceRawAmount = null;
        try {
            // Parse the response body as a JSON object
            JSONObject jsonResponse = new JSONObject(getEndPointResponseBody());

            // Navigate to the "data" object
            JSONObject data = jsonResponse.getJSONObject("data");

            // Extract "balance" raw_amount
            for (String key : data.keySet()) {
                JSONObject info = data.getJSONObject(key).getJSONObject("info");
                String creditType = info.optString("creditType");

                // Check if the current item is the "balance"
                if ("Balance".equals(creditType)) {
                    Object rawAmountObj = info.opt("raw_amount");
                    if (rawAmountObj instanceof String rawAmountStr) {
                        // Parse it as a BigDecimal
                        try {
                            balanceRawAmount = rawAmountStr;
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid number format for raw_amount: " + rawAmountStr);
                        }
                    }
                    break; // Exit loop once the balance is found
                }
            }

            // Set the extracted balance raw amount in the TestUserVariables instance
            if (balanceRawAmount != null) {
                System.out.println("Extracted balance raw amount: " + balanceRawAmount);
            } else {
                System.err.println("Balance information not found in response.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        user.get().setUserBalanceInPostRequest(balanceRawAmount);
    }


    public static void quitDriver() {
        driver.quit();
    }

    public static WebDriver getWebDriver() {
        return driver;
    }


}
