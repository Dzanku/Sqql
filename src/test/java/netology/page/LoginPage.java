package netology.page;

import com.codeborne.selenide.SelenideElement;
import netology.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class LoginPage {
    private SelenideElement heading = $(byText("Интернет Банк"));
    public SelenideElement loginField = $("[data-test-id = 'login'] input");
    public SelenideElement passwordField = $("[data-test-id = 'password'] input");
    private SelenideElement loginButton = $("[data-test-id = 'action-login']");
    private SelenideElement loginNotification = $("[data-test-id='login'] .input__sub");
    private SelenideElement passwordNotification = $("[data-test-id='password'] .input__sub");
    private SelenideElement errorNotification = $("[data-test-id = 'error-notification'] .notification__content");

    public LoginPage() {
        heading.shouldBe(visible);
    }

    public VerificationPage validLogin(DataHelper.AuthInfo authInfo) {
        loginField.setValue(authInfo.getLogin());
        passwordField.setValue(authInfo.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public VerificationPage validLoginWithRandomPassword(DataHelper.AuthInfo authInfo) {
        passwordField.setValue(authInfo.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void shouldBeErrorNotification() {
        errorNotification.shouldBe(visible);
        errorNotification.shouldHave(text("Ошибка! " + "Неверно указан логин или пароль"));
    }

    public void emptyAuthorisation(DataHelper.AuthInfo authInfo) {
        loginButton.click();
        loginNotification.shouldBe(visible);
        passwordNotification.shouldBe(visible);
        loginNotification.shouldHave(text("Поле обязательно для заполнения"));
        passwordNotification.shouldHave(text("Поле обязательно для заполнения"));
    }
}