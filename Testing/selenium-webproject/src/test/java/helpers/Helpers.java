package helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Helpers{

    public static String getInputValue(WebDriver driver, WebElement element, String getValue) {
        return (String) ((JavascriptExecutor) driver).executeScript(getValue, element);
    }
}