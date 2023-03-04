import Sprint4.Homepage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(Parameterized.class)
    public class QuestionsTest {
    private final String textV;
    private final String textO;

   private WebDriver driver;
    String URL="https://qa-scooter.praktikum-services.ru";

    public QuestionsTest(String textV, String textO) {

            this.textV = textV;
            this.textO = textO;
        }

        //параметры для поиска вопросов и ответов
        @Parameterized.Parameters
        public static Object[][] data() {
            //Сгенерируй тестовые данные
            return new Object[][]{
                    {"Сколько это стоит? И как оплатить?","Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                    {"Хочу сразу несколько самокатов! Так можно?","Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                    {"Как рассчитывается время аренды?","Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                    {"Можно ли заказать самокат прямо на сегодня?","Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                    {"Можно ли продлить заказ или вернуть самокат раньше?","Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                    {"Вы привозите зарядку вместе с самокатом?","Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                    {"Можно ли отменить заказ?","Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                    {"Я жизу за МКАДом, привезёте?","Да, обязательно. Всем самокатов! И Москве, и Московской области."}
            };
        }
    //перед каждым тестом запускаем браузер на нужной странице(URL)
@Before
public void initTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.get(URL);
    }
    @Test
        public void checkTest() {
        //создаем объект класса домашней страницы
            Homepage homepage = new Homepage(driver);
        //ищем текст ответа на вопрос(передаем параметры)
            String actual = homepage.clickQuestion(textV,textO);
            //сравниваем полученный и ожидаемый результаты
            assertThat("не совпадает",textO,is(actual) );
        }
    @After
    public void  teardown() {
        // Закрываем браузер
        driver.quit();
    }
}
