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
	final String SITE_URL = "https://estate.mts.ru/";
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

//образец шагов для теста, отображение в аллюр каждого шага и вводимых данных
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
		// вводит инфо в поле логин
		driver.findElement(By.id("username")).sendKeys("dg2estate@gmail.com");
		// вводит инфо в поле пароль
		driver.findElement(By.id("password")).sendKeys("dvgorbu6");
		// нажать кнопку вход
		driver.findElement(By.id("kc-login")).click();
		// пауза сек
		Thread.sleep(2000);
		//клик по вкладке моя организация
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/aside/div[1]/a[2]/span[2]")).click();
		//клик по полю инн, ввод инфо
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div[1]/div[1]/div/div/div[1]/input")).sendKeys("7707083893");
		//клик по полю кпп, ввод инфо
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div[1]/div[2]/div/div/div/input")).sendKeys("773643001");
		//клик по кнопке загрузить данные
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div[1]/div[3]/div/div/button")).click();
		// пауза сек
		Thread.sleep(2000);
		//клик по полю телефон, ввод данных
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/div/div/main/main/div[2]/div[1]/div[1]/div[2]/form/div/div[1]/div/div/div[1]/input")).sendKeys("0123456789");
		//клик по полю мейл, ввод данных
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/div/div/main/main/div[2]/div[1]/div[1]/div[2]/form/div/div[2]/div/div/div[1]/input")).sendKeys("dg2estate@gmail.com");
		//клик по полю бик,ввод данных
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/div/div/main/main/div[2]/div[1]/div[1]/div[1]/form/div/div[5]/div/div/div[1]/input")).sendKeys("012345678");
		//клик по полю юридический адрес/офис, ввод данных
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/div/div/main/main/div[2]/div[1]/div[1]/div[3]/form/div/div[6]/div/div/div[1]/input")).sendKeys("73");
		//клик по полю фактический адрес/офис, ввод данных
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/div/div/main/main/div[2]/div[1]/div[1]/div[4]/form/div/div[6]/div/div/div[1]/input")).sendKeys("37");
		//чекбокс "Нажимая сохранить"
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/div/div/main/main/div[2]/div[1]/div[2]/label/span[1]/span")).click();
		//нажать кнопку сохранить данные
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/div/div/main/main/div[2]/div[1]/button")).click();
		//клик по вкладке мой профиль
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/div/aside/div[1]/a[1]")).click();
		//клик по полю фамилия, очистка данных, ввод данных
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[1]/div/div/div/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[1]/div/div/div/input")).sendKeys("Мавроди");
		//клик по полю имя, очистка данных, ввод данных
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[2]/div/div/div/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[2]/div/div/div/input")).sendKeys("Сергей");
		//клик по полю отчество, очистка данных, ввод данных
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[3]/div/div/div/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[3]/div/div/div/input")).sendKeys("Пантелеевич");
		//клик по дата рождения, ввод данных
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[4]/div/div/div/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[4]/div/div/div/input")).sendKeys("27102004");
		//клик по мейл, ввод данных
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[5]/div/div/div/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[5]/div/div/div/input")).sendKeys("dg2estate@gmail.com");
		//клик по полю телефон, ввод данных
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[6]/div/div/div/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[1]/div[6]/div/div/div/input")).sendKeys("0123456789");
		//клик по полю мейл, ввод данных
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[2]/div/div/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[2]/div/div/input")).sendKeys("Московская область, г. Москва, ул. Ковальчука, д. 12, кв. 1");
		//клик по полю чекбокс
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/div[3]/div/label/span[1]/span")).click();
		//клик кнопка сохранить данные
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/div/main/main/div/form/div/button")).click();
		//перехват данных текста алерта

	}

	@Epic("TESTING FOR https://estate.mts.ru/ tasks")
	@Feature(value = "Проверка успешной авторизации")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Пользователь успешно авторизуется")
	@Story(value = "История Проверка успешной авторизации")

	@Test
	public void LoginExit() {
		options.setHeadless(uiModeHeadless);// переключение режима интерфейса
		driver.get(SITE_URL);// заходим на страницу
		driver.manage().window().maximize();// открывает страницу на весь экран
		driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/div[3]/div/button")).click();// находим элемент по кспасу. click нажимаем на него
		driver.get("https://auth.estate.mts.ru/auth/realms/mts-external/protocol/openid-connect/auth?client_id=estate-ext&redirect_uri=https%3A%2F%2Festate.mts.ru%2Fapi%2Fv1%2Fcallback&response_type=code&scope=username&state=abcf74b7-6e40-4d55-9a8e-997406c69670");
		driver.findElement(By.id("username")).sendKeys("dg2estate@gmail.com");// вводит инфо в поле логин
		driver.findElement(By.id("password")).sendKeys("dvgorbu6");// вводит инфо в поле пароль
		driver.findElement(By.id("kc-login")).click();// нажать кнопку вход
		driver.findElement(By.xpath("//*[@id=\"app-header\"]/div[1]/div/div[3]/div/div[3]/span")).click();// клик по кнопке личный кабинет(иконка профиля)
		try {// пауза 1 сек
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath("/html/body/ul/span/li")).click();// клик по "выход" в списке
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div/div[3]/div/button[1]")).click();// клик по "отмена" в модальном окне "вы действительно хотите выйти"
	}

}
