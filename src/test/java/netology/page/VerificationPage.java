package netology.page;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;



public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");
    private SelenideElement emptyCodeNotification = $("[data-test-id='code'] .input__sub");

    public void verificationPageVisibility() {
        codeField.shouldBe(visible);
    }

    public void validVerify(String verificationCode) {
        codeVerify(verificationCode);
        new DashboardPage();
    }

    public void codeVerify(String verificationCode) {
        codeField.setValue(verificationCode);
        verifyButton.click();
    }

    public void codeEmpty() {
        emptyCodeNotification.shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    public void incorrectCode() {
        errorNotification.shouldBe(visible);
        errorNotification.shouldHave(Condition.text("Ошибка! " + "Неверно указан код! Попробуйте ещё раз."));
    }
}