package pageObj;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    WebDriver driver;

    //локатор для поля имя
    private By name = By.xpath(".//input[@placeHolder='* Имя']");

    //локатор для поля фамилия
    private By secondName = By.xpath(".//input[@placeholder='* Фамилия']");

    //локатор для поля адрес
    private By street = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //локатор для поля метро
    private By metro = By.className("select-search__input");

    //локатор для поля телефон
    private By phone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //локатор для кнопки далее
    private By next = By.xpath(".//button[text()='Далее']");

    //локатор для поля дата заказа
    private By whenData = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    //локатор для кнопки срок аренды
    private By timeRent = By.className("Dropdown-placeholder");

    //локатор для кнопки да
    private By yes=By.xpath(".//button[text()='Да']");

    //локатор для всплывающей карточки с текстом "Заказ оформлен"
     private By isGood= By.xpath("//*[text()='Заказ оформлен']");

     //локатор для куки
    private By cookie = By.xpath(".//button[text()='да все привыкли']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //метод для ввода имени
    public void setName(String name1) {
        driver.findElement(name).sendKeys(name1);
    }
    //метод для ввода фамилии
    public void setSecondName(String sname) {
        driver.findElement(secondName).sendKeys(sname);
    }
    //метод для ввода адреса
    public void setStreet(String adress) {
        driver.findElement(street).sendKeys(adress);
    }
    //метод для ввода телефона
    public void setPhone(String number) {
        driver.findElement(phone).sendKeys(number);
    }
    //заполняем первую страницу заказа (имя,фамилия,адрес,телефон)
    public void login(String name1, String sname, String adress, String number) {
        setName(name1);
        setSecondName(sname);
        setStreet(adress);
        setPhone(number);
    }

    //метод для нажатия на кнопку далее
    public void clickNext() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(next));
        driver.findElement(next).click();
    }

    //метод для выбора станции метро *st -параметр,название станции
    public void setMetro(String st) {
        driver.findElement(metro).click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(String.format(".//div[(text()='%s')]", st))));
        driver.findElement(By.xpath(String.format(".//div[(text()='%s')]", st))).click();
    }
    public void clickCookie(){
        if (driver.findElement(cookie).isDisplayed())
        { driver.findElement(cookie).click();}
    }

    //метод для выбора цвета, text - цвет,параметр
    public void selectColor(String text){
       driver.findElement(By.xpath(".//input[@id='"+text+"']")).click();
    }

    //метод для нажатия на кнопку заказать(проверяем обе кнопки. параметризованы по имени класса)
    public void clickButtonOrder(String text){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath(".//button[@class='"+text+"']")));
        driver.findElement(By.xpath(".//button[@class='"+text+"']")).click();

    }

    //метод для выбора даты
    public void selectData(){
        driver.findElement(whenData).click();
        driver.findElement(By.xpath(".//div[@aria-label='Choose пятница, 10-е марта 2023 г.']")).click();
    }

    //метод выбора срока аренды
    public void selectTimeRent(String text) {
        driver.findElement(timeRent).click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(String.format("//*[text()='%s']", text))));
        driver.findElement(By.xpath(String.format("//*[text()='%s']", text))).click();
    }

    //метод нажатия на кнопку да(Оформить заказ?)
    public void yesButton(){
        driver.findElement(yes).click();
        new WebDriverWait(driver, 5).until(driver -> (driver.findElement(isGood).isDisplayed()));
    }

}

