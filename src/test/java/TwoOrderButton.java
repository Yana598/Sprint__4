import pageObj.OrderPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class TwoOrderButton {
    private WebDriver driver;
    private final String colorS;
    private final String rentS;
    private final String metroSt;
    private final String classB;


    public TwoOrderButton(String classB, String colorS, String metroSt, String rentS){
        this.classB =classB;
        this.colorS =colorS;
        this.metroSt=metroSt;
        this.rentS =rentS;

    }
    @Parameterized.Parameters
    public static Object[][] data() {
        //Сгенерируй тестовые данные
        return new Object[][]{
                {"Button_Button__ra12g","black","Деловой центр","четверо суток"},
        {"Button_Button__ra12g Button_Middle__1CSJM","grey","Сокольники","двое суток"}
        };
}
    @Before
    public void initTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //WebDriverManager.firefoxdriver().setup();
        //driver=new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://qa-scooter.praktikum-services.ru");}

    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }

    @Test()
 public void orderIsOrder(){
    OrderPage orderPage =new OrderPage(driver);
    //нажимаем на кнопку заказать
    orderPage.clickButtonOrder(classB);
    //заполняем имя.фамилию.адрес.телефон
    orderPage.login("Яна","Ковязина","Конева", "89642500709");
    //выбираем станцию метро
    orderPage.setMetro(metroSt);
    //убираем окно с куки
     orderPage.clickCookie();
    //нажимаем далее
    orderPage.clickNext();
    //выбираем дату(например 10 число)
    orderPage.selectData();
    //выбираем цвет самоката
    orderPage.selectColor(colorS);
    //выбираем на какое время будем арендовать
    orderPage.selectTimeRent(rentS);
    //нажимаем заказать(проверка 2 кнопок)
    orderPage.clickButtonOrder(classB);
    //выбираем оформить заказ и ждем чтоб появился элемент с текстом заказ оформлен
    orderPage.yesButton();
    }
}

