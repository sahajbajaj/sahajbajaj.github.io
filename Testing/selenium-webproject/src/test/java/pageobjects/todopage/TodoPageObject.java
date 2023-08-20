package pageobjects.todopage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import helpers.Helpers;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TodoPageObject {
    WebDriver driver;

    public TodoPageObject() {
        super();
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.get("https://sahajbajaj.github.io/");

    }

    public void closeBrowser(){
        driver.close();
    }

    public String inputTitle(String title){
        WebElement titleInput = driver.findElement(By.id("title"));
        titleInput.sendKeys(title);

        String inputValue = Helpers.getInputValue(driver, titleInput, "return arguments[0].value;");

        return inputValue;
    }

    public String inputTodoItem(String todoItem){
        WebElement todoInput = driver.findElement(By.id("todoInput"));
        todoInput.sendKeys(todoItem);

        String inputValue = Helpers.getInputValue(driver, todoInput, "return arguments[0].value;");

        return inputValue;

    }

    public Boolean selectCategory(Category category){
        WebElement categoryButton = driver.findElement(By.id(category.getId() + "button"));
        categoryButton.click(); 

        WebElement categoryInput = driver.findElement(By.id(category.getId()));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean isSelected = (boolean) jsExecutor.executeScript("return arguments[0].checked;", categoryInput);

        return isSelected;
    }

    public void addTodo(){
        WebElement addingtodo = driver.findElement(By.id("addtodo"));
        addingtodo.click();

    }

    public String findTextById(String id) {
        try {
            WebElement addingTodo = driver.findElement(By.id(id + "Id"));
            return Helpers.getInputValue(driver, addingTodo, "return arguments[0].value;");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return null; 
        } catch (Exception e) {
            System.out.println("findTextById has thrown an exception: " + e.getMessage());
            return null;
        }
    }

    public void strikeThrough(){
        WebElement strike = driver.findElement(By.id("checkbox"));
        strike.click();
    }

    public void deleteTodoItem(String id){
        WebElement deleteItem = driver.findElement(By.id(id + "deleteId"));
        deleteItem.click();
    }

    public boolean hasStrikeThrough(String id){
        WebElement strikeThroughElement = driver.findElement(By.id(id + "Id"));
        String textDecoration = strikeThroughElement.getCssValue("text-decoration");
        if(textDecoration.contains("line-through")){
            return true;
        }
        
        return false;
    }  
}