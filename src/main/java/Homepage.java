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
            //прокручиваем до вопроса с определенным текстом
         ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath(".//div[(text()='"+text+"')]")));
            //кликаем по вопросу
          driver.findElement(By.xpath(".//div[(text()='"+text+"')]")).click();
          //ожидаем когда будет виден ответ
            new WebDriverWait(driver, 3).until(driver -> (driver.findElement(By.xpath(".//p[(text()='"+text1+"')]")).isDisplayed()));
            //получаем текст ответа и возвращаем его для сравнения с параметром
          return driver.findElement(By.xpath(".//p[(text()='"+text1+"')]")).getText();
        }
}

