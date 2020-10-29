package Test;// Java -jar Jenkins.war терминал из директории с файлом страница http://localhost:8080

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBase {
	String userMail = "991mavrodi@gmail.com";
	String userPassword = "mavrodi";
	String userInn = "7707083893";
	String userKpp = "773601001";
	String userBik = "123456789";
	String officeNumber = "73";
	String userName = "Сергей";
	String userSurname = "Мавроди";
	String userPatronymic = "Пантелеевич";
	String userBirth = "27102004";
	String userTelNumber = "0123456789";
	String userAddress = "Московская область, г. Москва, ул. Ковальчука, д. 12, кв. 1";
	String cadastralNumber = "";
	String SITE_URL = "https://estate.mts.ru/";
	WebDriver driver;
	ChromeOptions options;
	boolean uiModeHeadless = false;

	@BeforeEach //перед каждым тестом
	public void start() {
		//System.setProperty("webdriver.chrome.driver", "C:/Users/Dmitriy/IdeaProject/AutotestsEstate/chromedriver.exe"); //локальный файл хромдрайвера
		WebDriverManager.chromedriver().setup();
		options = new ChromeOptions();
		options.setHeadless(uiModeHeadless);// переключение режима интерфейса
		driver = new ChromeDriver(options);
	}

//	@AfterEach //после каждого теста
//	public void finish() {
//		driver.close();
//		driver.quit();
//	}

	@Attachment(value = "Failed test screenshot")
	public byte[] attachScreenshot() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	// форма пустышка для теста----------------------------------------------------------------
	@Epic("TESTING FOR https://estate.mts.ru/ tasks")
	@Feature(value = "ОБРАЗЕЦ")
	@Severity(SeverityLevel.MINOR)
	@Description("ОБРАЗЕЦ")
	@Story(value = "ОБРАЗЕЦ")

	@Test
	public void Example() {

	}
	// форма пустышка для теста----------------------------------------------------------------

	//образец шагов для теста, отображение в аллюр каждого шага и/или вводимых данных
	@Step(value = "Fill in login with {0}")
	public void fillInLogin(String login) {
		driver.findElement(By.id("LoginForm_username")).sendKeys(login);
	}

	@Step(value = "Fill in password with {0}")
	public void fillInPassword(String password) {
		driver.findElement(By.id("LoginForm_password")).sendKeys(password);
	}

	@Step(value = "Click button Login")
	public void loginButtonClick() {
		driver.findElement(By.id("LoginForm_save")).click();
	}

	@Step(value = "Choose element")
	public void chooseElement() {
		driver.findElement(By.linkText("ОБРАЗЕЦ")).click();
	}

	@Step(value = "Login was successful")
	public void isLoginSuccessful() {
		assertTrue(driver.findElement(By.linkText("ВИДИМ ЛИ ЭЛЕМЕНТ")).isDisplayed());
	}

	@Step(value = "Login was unsuccessful")
	public void isLoginUnsuccessful() {
		assertFalse(driver.findElements(By.linkText("ЕСЛИ ЭЛЕМЕНТ НЕВИДИМ")).isEmpty());
	}
//образец шагов для теста, отображение в аллюр каждого шага и вводимых данных

	@Epic("TESTING FOR https://estate.mts.ru/ tasks")
	@Feature(value = "Регистрация пользователя")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Попытка регистрации пользователя с корректными данными")
	@Story(value = "История Пользователь регистрируется с корректными данными")

	@Test    // пример тест-кейса
	public void ExampleUserRegistration() {
		//зайти на сайт
		//нажать на кнопку регистрация
		//получить ссылку страницы регистрации
		//нажать на ссылку зарегистрироваться
		//ввести имя
		//ввести фамилию
		//ввести мейл
		//ввести пароль
		//ввести подтверждение пароля
		//нажать кнопку зарегистрироваться
		//проверка видимости элемента иконка профиля, статус виден
	}

	@Test
	public void UserRegistration() {
		//зайти на сайт
		driver.get(SITE_URL);
		//нажать на кнопку регистрация
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/section/div/button[1]")).click();
		//получить ссылку страницы регистрации
		driver.get("https://auth.estate.mts.ru/auth/realms/mts-external/protocol/openid-connect/auth?client_id=estate-ext&redirect_uri=https%3A%2F%2Festate.mts.ru%2Fapi%2Fv1%2Fcallback&response_type=code&scope=username&state=e03539a6-ae2a-444c-80fd-023833f90bf2");
		//нажать на ссылку зарегистрироваться
		driver.findElement(By.xpath("//*[@id=\"kc-registration\"]/span/a")).click();
		//ввести имя
		driver.findElement(By.id("firstName")).sendKeys(userName);
		//ввести фамилию
		driver.findElement(By.id("lastName")).sendKeys(userSurname);
		//ввести мейл
		driver.findElement(By.id("email")).sendKeys(userMail);
		//ввести пароль
		driver.findElement(By.id("password")).sendKeys(userPassword);
		//ввести подтверждение пароля
		driver.findElement(By.id("password-confirm")).sendKeys(userPassword);
		//нажать кнопку зарегистрироваться
		driver.findElement(By.xpath("//*[@id=\"kc-form-buttons\"]/input")).click();
		//проверка видимости элемента иконка профиля, статус виден
		assertTrue(driver.findElement(By.xpath("//*[@id=\"app-header\"]/div[1]/div/div[3]/div/div[3]/span/div")).isDisplayed());
	}

	@Epic("TESTING FOR https://estate.mts.ru/ tasks")
	@Feature(value = "Авторизация, заполнение данных для вкладок моя организация и мой профиль")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Авторизация, заполнение вкладок моя организация и мой профиль с корректными данными")
	@Story(value = "История Авторизация, заполнение вкладок моя организация и мой профиль с корректными данными")

	@Test
	//Авторизация, заполнение данных для вкладок моя организация и мой профиль
	public void Login() throws InterruptedException {
		// заходим на страницу
		driver.get(SITE_URL);
		// открывает страницу на весь экран
		driver.manage().window().maximize();
		// находим элемент кнопка войти, нажимаем на него
		driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/div[3]/div/button")).click();
		//переход на страницу авторизации
		driver.get("https://auth.estate.mts.ru/auth/realms/mts-external/protocol/openid-connect/auth?client_id=estate-ext&redirect_uri=https%3A%2F%2Festate.mts.ru%2Fapi%2Fv1%2Fcallback&response_type=code&scope=username&state=abcf74b7-6e40-4d55-9a8e-997406c69670");
		// вводит инфо в поле логин/почта
		driver.findElement(By.id("username")).sendKeys(userMail);
		// вводит инфо в поле пароль
		driver.findElement(By.id("password")).sendKeys(userPassword);
		// нажать кнопку вход
		driver.findElement(By.id("kc-login")).click();
		// пауза сек
		Thread.sleep(2000);
		//клик по вкладке моя организация
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/aside/div[1]/a[2]/span[2]")).click();
		//клик по полю инн, ввод инфо
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div[1]/div[1]/div/div/div[1]/input")).sendKeys(userInn);
		//клик по полю кпп, ввод инфо
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div[1]/div[2]/div/div/div/input")).sendKeys(userKpp);
		//клик по кнопке загрузить данные
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div[1]/div[3]/div/div/button")).click();
		// пауза сек
		Thread.sleep(2000);
		//клик по полю телефон, ввод данных
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/div/div/main/main/div[2]/div[1]/div[1]/div[2]/form/div/div[1]/div/div/div[1]/input")).sendKeys(userTelNumber);
		//клик по полю мейл, ввод данных
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/div/div/main/main/div[2]/div[1]/div[1]/div[2]/form/div/div[2]/div/div/div[1]/input")).sendKeys(userMail);
		//клик по полю бик,ввод данных
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/div/div/main/main/div[2]/div[1]/div[1]/div[1]/form/div/div[5]/div/div/div[1]/input")).sendKeys(userBik);
		//клик по полю юридический адрес/офис, ввод данных
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/div/div/main/main/div[2]/div[1]/div[1]/div[3]/form/div/div[6]/div/div/div[1]/input")).sendKeys(officeNumber);
		//клик по полю фактический адрес/офис, ввод данных
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/div/div/main/main/div[2]/div[1]/div[1]/div[4]/form/div/div[6]/div/div/div[1]/input")).sendKeys(officeNumber);
		//чекбокс "Нажимая сохранить"
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/div/div/main/main/div[2]/div[1]/div[2]/label/span[1]/span")).click();
		//нажать кнопку сохранить данные
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/div/div/main/main/div[2]/div[1]/button")).click();
		//клик по вкладке мой профиль
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/div/aside/div[1]/a[1]")).click();
		//клик по полю фамилия, очистка данных, ввод данных
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[1]/div/div/div/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[1]/div/div/div/input")).sendKeys(userSurname);
		//клик по полю имя, очистка данных, ввод данных
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[2]/div/div/div/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[2]/div/div/div/input")).sendKeys(userName);
		//клик по полю отчество, очистка данных, ввод данных
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[3]/div/div/div/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[3]/div/div/div/input")).sendKeys(userPatronymic);
		//клик по дата рождения, ввод данных
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[4]/div/div/div/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[4]/div/div/div/input")).sendKeys(userBirth);
		//клик по мейл, ввод данных
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[5]/div/div/div/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[5]/div/div/div/input")).sendKeys(userMail);
		//клик по полю телефон, ввод данных
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[6]/div/div/div/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[6]/div/div/div/input")).sendKeys(userTelNumber);
		//клик по полю мейл, ввод данных
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[2]/div/div/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[2]/div/div/input")).sendKeys(userAddress);
		//клик по полю чекбокс
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[3]/div/label/span[1]/span")).click();
		//клик кнопка сохранить данные
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/button")).click();
		//перехват данных текста алерта??

	}

	@Epic("TESTING FOR https://estate.mts.ru/ tasks")
	@Feature(value = "Проверка успешной авторизации")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Пользователь успешно авторизуется")
	@Story(value = "История Проверка успешной авторизации")

	@Test
	public void LoginExit() throws InterruptedException {
		options.setHeadless(uiModeHeadless);// переключение режима интерфейса
		driver.get(SITE_URL);// заходим на страницу
		driver.manage().window().maximize();// открывает страницу на весь экран
		driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/div[3]/div/button")).click();// находим элемент по кспасу. click нажимаем на него
		driver.get("https://auth.estate.mts.ru/auth/realms/mts-external/protocol/openid-connect/auth?client_id=estate-ext&redirect_uri=https%3A%2F%2Festate.mts.ru%2Fapi%2Fv1%2Fcallback&response_type=code&scope=username&state=abcf74b7-6e40-4d55-9a8e-997406c69670");
		driver.findElement(By.id("username")).sendKeys(userMail);// вводит инфо в поле логин
		driver.findElement(By.id("password")).sendKeys(userPassword);// вводит инфо в поле пароль
		driver.findElement(By.id("kc-login")).click();// нажать кнопку вход
		driver.findElement(By.xpath("//*[@id=\"app-header\"]/div[1]/div/div[3]/div/div[3]/span")).click();// клик по кнопке личный кабинет(иконка профиля)
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/ul/span/li")).click();// клик по "выход" в списке
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div/div[3]/div/button[1]")).click();// клик по "отмена" в модальном окне "вы действительно хотите выйти"
	}

}
