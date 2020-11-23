package Test;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class DemoSingleTestWithoutSteps extends TestBase{

    @Epic("TESTING FOR https://stage.estate.mts.ru/ tasks")
    @Feature(value = "Проверка успешной авторизации")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Пользователь успешно авторизуется")
    @Story(value = "История Проверка успешной авторизации")

    @Test
    //Проверка возможности успешной авторизации
    public void LoginSuccessfulAuthorization() throws InterruptedException {
        // зайти на страницу
        driver.get(SITE_URL);
        // открывает страницу на весь экран
        driver.manage().window().maximize();
        // нажать кнопку регистрация
        driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/div[3]/div/button")).click();
        //получение страницы авторизации
        driver.get("https://auth.estate.mts.ru/auth/realms/mts-external/protocol/openid-connect/auth?client_id=estate-ext&redirect_uri=https%3A%2F%2Festate.mts.ru%2Fapi%2Fv1%2Fcallback&response_type=code&scope=username&state=abcf74b7-6e40-4d55-9a8e-997406c69670");
        // заполнить поле логин
        driver.findElement(By.id("username")).sendKeys(userMail);
        // заполнить поле пароль
        driver.findElement(By.id("password")).sendKeys(userPassword);
        // нажать кнопку вход
        driver.findElement(By.id("kc-login")).click();
        // клик по кнопке личный кабинет(иконка профиля)
        driver.findElement(By.xpath("//*[@id=\"app-header\"]/div[1]/div/div[3]/div/div[3]/span")).click();
        //пауза сек
        Thread.sleep(1000);
        // клик по "выход" в списке
        driver.findElement(By.xpath("/html/body/ul/span/li")).click();
        // клик по "отмена" в модальном окне "вы действительно хотите выйти"
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div/div[3]/div/button[1]")).click();
    }
}
