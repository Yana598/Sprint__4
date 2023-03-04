package Sprint4;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Homepage {
        private WebDriver driver;

        public Homepage(WebDriver driver){

        this.driver = driver;
}
        public String clickQuestion(String text,String text1){
            //локатор для поля вопрос
            By question =By.xpath(".//div[(text()='"+text+"')]");
            //локатор для кнопки ответ
            By answer = By.xpath(".//p[(text()='"+text1+"')]");
            //прокручиваем до вопроса с определенным текстом
         ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(question));
            //ожидаем когда вопрос будет видим
          new WebDriverWait(driver, 3).until(driver -> (driver.findElement(question).isDisplayed()));
            //кликаем по вопросу
          driver.findElement(question).click();
          //ожидаем когда будет виден ответ
            new WebDriverWait(driver, 3).until(driver -> (driver.findElement(answer).isDisplayed()));
            //получаем текст ответа и возвращаем его для сравнения с параметром
          return driver.findElement(answer).getText();
        }
}

