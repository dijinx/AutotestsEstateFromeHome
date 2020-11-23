package Test;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class DemoSingleTestWithSteps extends TestBase {

    @Epic("TESTING FOR https://stage.estate.mts.ru/ tasks")
    @Feature(value = "Проверка успешной авторизации")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Пользователь успешно авторизуется")
    @Story(value = "История Проверка успешной авторизации")

    //образец шагов для теста, отображение в аллюр каждого шага и/или вводимых данных
    @Step(value = "Get URL")
    public void getUrl(String SITE_URL) {
        driver.get(SITE_URL);
    }

    @Step(value = "Press Registration Button")
    public void pressRegistrationButton() {
        driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/div[3]/div/button")).click();
    }

    @Step(value = "Get URL registration page")
    public void getURLRegistrationPage() {
        driver.get("https://auth.estate.mts.ru/auth/realms/mts-external/protocol/openid-connect/auth?client_id=estate-ext&redirect_uri=https%3A%2F%2Festate.mts.ru%2Fapi%2Fv1%2Fcallback&response_type=code&scope=username&state=abcf74b7-6e40-4d55-9a8e-997406c69670");
    }

    @Step(value = "Fill in login")
    public void fillInLogin(String userMail) {
        driver.findElement(By.id("username")).sendKeys(userMail);
    }

    @Step(value = "Fill in password")
    public void fillInPassword(String userPassword) {
        driver.findElement(By.id("password")).sendKeys(userPassword);
    }

    @Step(value = "Press button enter")
    public void pressButtonEnter() {
        driver.findElement(By.id("kc-login")).click();
    }

    @Step(value = "Press profile icon")
    public void pressProfileIcon() {
        driver.findElement(By.xpath("//*[@id=\"app-header\"]/div[1]/div/div[3]/div/div[3]/span")).click();
    }

    @Step(value = "Press exit in drop-down menu")
    public void pressExitInDropDownMenu() {
        driver.findElement(By.xpath("/html/body/ul/span/li")).click();
    }

    @Step(value = "Press cancel in drop-down menu")
    public void pressCancelInDropDownMenu() {
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div/div[3]/div/button[1]")).click();
    }

//образец шагов для теста, отображение в аллюр каждого шага и вводимых данных

    @Test
    //Проверка успешной авторизации c отображением шагов теста в аллюр
    public void LoginSuccessfulAuthorization2() throws InterruptedException {

        getUrl(SITE_URL);
        pressRegistrationButton();
        getURLRegistrationPage();
        fillInLogin(userMail);
        fillInPassword(userPassword);
        pressButtonEnter();
        Thread.sleep(2000);
        pressProfileIcon();
        pressExitInDropDownMenu();
        pressCancelInDropDownMenu();
    }
}
