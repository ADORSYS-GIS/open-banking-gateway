package de.adorsys.fintech.tests.e2e.steps;

import com.tngtech.jgiven.integration.spring.JGivenStage;
import de.adorsys.opba.protocol.xs2a.tests.e2e.sandbox.servers.WebDriverBasedAccountInformation;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.RetryOperations;

import java.net.URI;
import java.time.Duration;

import static de.adorsys.fintech.tests.e2e.steps.FintechStagesUtils.*;
import static de.adorsys.opba.protocol.xs2a.tests.e2e.sandbox.servers.config.RetryableConfig.TEST_RETRY_OPS;


@JGivenStage
@SuppressWarnings("checkstyle:MethodName")
// Jgiven prettifies snake-case names not camelCase
public class WebDriverBasedUserInfoFintech<SELF extends WebDriverBasedUserInfoFintech<SELF>> extends WebDriverBasedAccountInformation<SELF> {

    @Autowired
    @Qualifier(TEST_RETRY_OPS)
    private RetryOperations withRetry;

    @Value("${test.webdriver.timeout}")
    private Duration timeout;

    public SELF user_opens_fintechui_login_page(WebDriver driver) {
        driver.get(FINTECH_UI_URI);
        return self();
    }

    public SELF sandbox_user_selects_sca_method(WebDriver driver) {
        wait(driver);
        clickOnButton(driver, By.xpath("//button[@type='submit']"));
        return self();
    }

    public SELF sandbox_user_clicks_redirect_back_to_tpp_button(WebDriver driver) {
        wait(driver);
        clickOnButton(driver, By.className("btn-primary"), true);
        return self();
    }

    public SELF sandbox_user_provides_sca_challenge_result(WebDriver driver) {
        wait(driver);
        sendText(driver, By.name("authCode"), "123456");
        clickOnButton(driver, By.xpath("//button[@type='submit']"));
        return self();
    }

    public SELF user_sees_account_and_list_transactions(WebDriver webDriver) {
        wait(webDriver);
        performClick(webDriver, By.className("lacc-list-container"));
        waitPlusTimer(webDriver, 20);
        return self();
    }

    public SELF user_inputs_username_and_password_for_redirect(WebDriver driver) {
        waitPlusTimer(driver, timeout.getSeconds());
        clickOnButton(driver, By.name("login"));
        sendText(driver, By.name("login"), OBA_USERNAME);
        sendText(driver, By.name("pin"), "12345");
        clickOnButton(driver, By.xpath("//button[@type='submit']"));
        return self();
    }

    public SELF user_for_embeeded_provided_to_consent_ui_initial_parameters_to_list_transactions_with_all_accounts_consent(WebDriver driver) {
        waitForPageLoadAndUrlEndsWithPath(driver, "entry-consent-transactions");
        sendText(driver, By.id("PSU_ID"), OBA_USERNAME);
        clickOnButton(driver, By.id("ALL_PSD2"));
        clickOnButton(driver, By.id(SUBMIT_ID));
        return self();
    }

    public SELF user_for_embeeded_provided_to_consent_ui_initial_parameters_to_list_transactions_consent(WebDriver driver) {
        waitForPageLoadAndUrlEndsWithPath(driver, "entry-payments");
        sendText(driver, By.id("PSU_ID"), OBA_USERNAME);
        clickOnButton(driver, By.id(SUBMIT_ID));
        return self();
    }

    public SELF user_provides_sca_challenge_result_for_redirect(WebDriver driver) {
        waitPlusTimer(driver, timeout.getSeconds());
        sendText(driver, By.name("authCode"), "123456");
        clickOnButton(driver, By.xpath("//button[@type='submit']"));
        return self();
    }

    public SELF user_navigates_from_consent_ui_to_bank_auth_page(WebDriver driver) {
        waitForPageLoadAndUrlContains(driver, "account-information/login");
        return self();
    }

    public SELF user_in_consent_ui_sees_redirection_info_to_aspsp_and_accepts(WebDriver driver) {
        waitForPageLoadAndUrlEndsWithPath(driver, "/to-aspsp-redirection");
        clickOnButton(driver, By.id(SUBMIT_ID));
        return self();
    }

    public SELF sandbox_user_inputs_username_and_password(WebDriver driver) {
        wait(driver);
        clickOnButton(driver, By.name("login"));
        sendText(driver, By.name("login"), OBA_USERNAME);
        sendText(driver, By.name("pin"), "12345");
        clickOnButton(driver, By.xpath("//button[@type='submit']"));
        return self();
    }

    public SELF user_in_consent_ui_sees_sca_select_and_confirm_type_email2_to_redirect_authorization(WebDriver driver) {
        waitForPageLoadAndUrlEndsWithPath(driver, "select-sca");
        clickOnButton(driver, By.xpath("//button[@type='submit']"));
        return self();
    }

    public SELF user_in_consent_ui_sees_thank_you_for_consent_and_clicks_to_tpp_for_redirect(WebDriver driver) {
        wait(driver);
        clickOnButton(driver, By.className("btn-primary"), true);
        return self();
    }

    public SELF user_in_consent_ui_reviews_transaction_consent_and_accepts_for_redirect(WebDriver driver) {
        waitForPageLoadAndUrlEndsWithPath(driver, "entry-consent-transactions/review-consent");
        clickOnButton(driver, By.id(SUBMIT_ID));
        return self();
    }

    public SELF user_provided_to_consent_ui_initial_parameters_to_list_accounts_with_all_accounts_transactions_consent_for_redirect(WebDriver driver) {
        waitForPageLoadAndUrlEndsWithPath(driver, "entry-consent-transactions");
        sendText(driver, By.id("PSU_ID"), OBA_USERNAME);
        clickOnButton(driver, By.id("ALL_PSD2"));
        clickOnButton(driver, By.id(SUBMIT_ID));
        return self();
    }

    public SELF user_login_with_its_credentials(WebDriver driver, String username) {
        sendText(driver, By.id("username"), username);
        sendText(driver, By.id("password"), PIN);
        clickOnButton(driver, By.xpath("//button[@type='submit']"));
        return self();
    }

    public SELF user_wait_for_the_result_in_bank_search(WebDriver webDriver) {
        wait(webDriver);
        clickOnButton(webDriver, By.className("bank-list"), false);
        return self();
    }

    public SELF user_in_consent_ui_provides_pin_for_embeeded(WebDriver driver) {
        waitForPageLoadAndUrlContains(driver, "authenticate");
        sendText(driver, By.id("pin"), PIN_VALUE);
        clickOnButton(driver, By.id(SUBMIT_ID));
        return self();
    }

    public SELF user_in_consent_ui_provides_sca_result_to_embedded_authorization_for_redirect(WebDriver driver) {
        waitForPageLoadAndUrlEndsWithPath(driver, "sca-result");
        sendText(driver, By.id("tan"), TAN_VALUE);
        clickOnButton(driver, By.id(SUBMIT_ID));
        return self();
    }

    public SELF user_user_provided_to_consent_ui_initial_parameters_to_list_accounts_with_all_accounts_transactions_consent(WebDriver driver) {
        waitForPageLoadAndUrlEndsWithPath(driver, "entry-consent-transactions");
        sendText(driver, By.id("PSU_ID"), OBA_USERNAME);
        clickOnButton(driver, By.id("ALL_PSD2"));
        clickOnButton(driver, By.id(SUBMIT_ID));
        return self();
    }

    public SELF user_user_in_consent_ui_reviews_transaction_consent_and_accepts(WebDriver driver) {
        waitForPageLoadAndUrlEndsWithPath(driver, "entry-consent-transactions/review-consent");
        clickOnButton(driver, By.id(SUBMIT_ID));
        return self();
    }

    public SELF user_already_login_in_bank_profile(WebDriver firefoxDriver, String username, String fintech, String profile) {
        user_opens_fintechui_login_page(firefoxDriver)
                .and()
                .user_login_with_its_credentials(firefoxDriver, username)
                .and()
                .user_looks_for_a_bank_in_the_bank_search_input_place(firefoxDriver, profile)
                .and()
                .user_wait_for_the_result_in_bank_search(firefoxDriver)
                .and()
                .user_navigates_to_page(firefoxDriver)
                .and()
                .user_select_account_button(firefoxDriver)
                .and()
                .user_accepts_to_get_redirected_to_consentui(firefoxDriver)
                .and()
                .user_click_on_register_button(firefoxDriver)
                .and()
                .user_register_on_consent(firefoxDriver, fintech)
                .and()
                .user_navigates_to_page(firefoxDriver)
                .and()
                .user_login_in_consent(firefoxDriver, fintech);
        return self();
    }

    public SELF user_back_to_bank_search(WebDriver webDriver) {
        wait(webDriver);
        performClick(webDriver, By.linkText("Bank search"));
        return self();
    }

    public SELF user_navigates_to_page(WebDriver driver) {
        wait(driver);
        return self();
    }

    public SELF user_provided_to_consent_ui_initial_parameters_to_list_transactions_with_all_accounts_consent(WebDriver driver) {
        waitForPageLoadAndUrlEndsWithPath(driver, "entry-consent-transactions");
        sendText(driver, By.id("PSU_ID"), OBA_USERNAME);
        clickOnButton(driver, By.id("ALL_PSD2"));
        clickOnButton(driver, By.id(SUBMIT_ID));
        return self();
    }

    public SELF user_in_consent_ui_sees_sca_select_and_selected_type_email2_to_embedded_authorization(WebDriver driver) {
        waitForPageLoadAndUrlEndsWithPath(driver, "select-sca-method");
        selectByVisibleInDropdown(driver, By.id("scaMethod"), "EMAIL:htc@mail.de");
        clickOnButton(driver, By.id(SUBMIT_ID));
        return self();
    }

    public SELF user_looks_for_a_bank_in_the_bank_search_input_place(WebDriver driver, String profile) {
        waitPlusTimer(driver, timeout.getSeconds());
        driver.findElement(By.name("searchValue")).clear();
        waitPlusTimer(driver, timeout.getSeconds());
        sendTestInSearchInput(driver, By.name("searchValue"), profile);
        return self();
    }

    public SELF user_after_login_wants_to_logout(WebDriver webDriver) {
        wait(webDriver);
        clickOnButton(webDriver, By.id("dropdownMenuButton"));
        return self();
    }

    public SELF user_click_on_logout_button(WebDriver webDriver) {
        wait(webDriver);
        clickOnButton(webDriver, By.className("nav__title"), false);
        return self();
    }

    public SELF user_confirm_login(WebDriver webDriver) {
        wait(webDriver);
        clickOnButton(webDriver, By.xpath("//button[@type='submit']"));
        return self();
    }

    public SELF user_accepts_to_get_redirected_to_consentui(WebDriver webDriver) {
        wait(webDriver);
        performClick(webDriver, By.xpath("//button[@class='btn btn-primary btn-center']"));
        return self();
    }

    public SELF user_select_account_button(WebDriver webDriver) {
        performClick(webDriver, By.linkText("Accounts"));
        return self();
    }

    public SELF user_select_transfert_button(WebDriver webDriver) {
        wait(webDriver).until(ExpectedConditions.elementToBeClickable(By.linkText("Transfer")));
        performClick(webDriver, By.linkText("Transfer"));
        return self();
    }

    public SELF user_click_on_register_button(WebDriver webDriver) {
        performClick(webDriver, By.id("register"));
        return self();
    }

    public SELF user_click_on_confirm_button(WebDriver webDriver) {
        performClick(webDriver, By.id(SUBMIT_ID));
        return self();
    }

    public SELF user_login_in_consent(WebDriver driver, String fintech) {
        waitForPageLoadAndUrlContains(driver, "/login");
        sendText(driver, By.id("username"), fintech);
        sendText(driver, By.id("password"), "12345");
        clickOnButton(driver, By.xpath("//button[@type='submit']"));
        return self();
    }

    public SELF user_fills_transfer_formular(WebDriver driver) {
        waitPlusTimer(driver, timeout.getSeconds());
        driver.findElement(By.name("creditorIban")).clear();
        sendText(driver, By.name("creditorIban"), "DE80760700240271232400");
        driver.findElement(By.name("name")).clear();
        sendText(driver, By.name("name"), "anton.brueckner");
        driver.findElement(By.name("purpose")).clear();
        sendText(driver, By.name("purpose"), "test");
        driver.findElement(By.name("amount")).clear();
        sendText(driver, By.name("amount"), "50,00");
        return self();
    }

    public SELF user_select_account_to_proceed_payment_with(WebDriver driver) {
        waitPlusTimer(driver, timeout.getSeconds());
        performClick(driver, By.className("lacc-list-container"));
        waitPlusTimer(driver, 20);
        return self();
    }

    public SELF user_register_on_consent(WebDriver driver, String fintech) {
        sendText(driver, By.id("username"), fintech);
        sendText(driver, By.id("password"), "12345");
        sendText(driver, By.id("confirmPassword"), "12345");
        clickOnButton(driver, By.xpath("//button[@type='submit']"));
        return self();
    }

    //TODO add stages for accountList and TransactionList

    private void clickOnButton(WebDriver driver, By identifier) {
        clickOnButton(driver, identifier, false);
    }

    private void clickOnButton(WebDriver driver, By identifier, boolean allowReachedErrorPage) {
        withRetry.execute(context -> {
            if (allowReachedErrorPage) {
                swallowReachedErrorPageException(() -> performClick(driver, identifier));
            } else {
                performClick(driver, identifier);
            }

            return null;
        });
    }

    private void performClick(WebDriver driver, By identifier) {
        wait(driver).until(ExpectedConditions.elementToBeClickable(identifier));
        driver.findElement(identifier).click();
        withRetry.execute(context -> {
            wait(driver).until(ExpectedConditions.elementToBeClickable(identifier));
            driver.findElement(identifier).click();
            return null;
        });
    }

    private void sendText(WebDriver driver, By identifier, String text) {
        withRetry.execute(context -> {
            wait(driver).until(ExpectedConditions.elementToBeClickable(identifier));
            driver.findElement(identifier).sendKeys(text);
            return null;
        });
    }

    private WebDriverWait wait(WebDriver driver) {
        return new WebDriverWait(driver, timeout.getSeconds());
    }

    private WebDriverWait waitPlusTimer(WebDriver webDriver, long duration) {
        return new WebDriverWait(webDriver, timeout.getSeconds() + duration);
    }

    private void sendTestInSearchInput(WebDriver driver, By id, String visibleText) {
        withRetry.execute(context -> {
            wait(driver).until(ExpectedConditions.elementToBeClickable(id));
            WebElement input = driver.findElement(id);
            input.click();
            input.sendKeys(visibleText);
            return null;
        });
    }

    private void swallowReachedErrorPageException(Runnable action) {
        try {
            action.run();
        } catch (WebDriverException ex) {
            if (null != ex.getMessage() && ex.getMessage().contains("Reached error page")) {
                return;
            }

            throw ex;
        }
    }

    private void selectByVisibleInDropdown(WebDriver driver, By id, String visibleText) {
        withRetry.execute(context -> {
            wait(driver).until(ExpectedConditions.elementToBeClickable(id));
            Select elem = new Select(driver.findElement(id));
            elem.selectByVisibleText(visibleText);
            return null;
        });
    }

    private void waitForPageLoadAndUrlEndsWithPath(WebDriver driver, String urlEndsWithPath) {
        new WebDriverWait(driver, timeout.getSeconds())
                .until(wd ->
                               URI.create(driver.getCurrentUrl()).getPath().endsWith(urlEndsWithPath)
                                       && ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
                );
    }

    private void waitForPageLoadAndUrlContains(WebDriver driver, String urlContains) {
        new WebDriverWait(driver, timeout.getSeconds())
                .until(wd ->
                               driver.getCurrentUrl().contains(urlContains)
                                       && ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
                );
    }

}