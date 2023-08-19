package pageobjects.todopage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TodoPageObject {
    WebDriver driver;

    public void navigate(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost:5173/");
    }

    public void closeBrowse(){
        driver.close();
    }

    public void inputTitle(String title){
        WebElement titleInput = driver.findElement(By.id("title"));
        titleInput.sendKeys(title);
    }

    public void inputTodoItem(String todoItem){
        WebElement todoInput = driver.findElement(By.id("todoInput"));
        todoInput.sendKeys(todoItem);

        WebElement addingtodo = driver.findElement(By.id("addtodo"));
        addingtodo.click();
    }

    public void selectCategory(Category category){
        WebElement categoryElement = driver.findElement(By.id(category.getId()));
        categoryElement.click(); 
    }

    public void addTodo(){
        WebElement addingtodo = driver.findElement(By.id("addtodo"));
        addingtodo.click();
    }

    public void strikeThrough(){
        WebElement strike = driver.findElement(By.id("checkbox"));
        strike.click();
    }

    public void deleteTodoItem(){
        WebElement deleteItem = driver.findElement(By.id("deletetodo"));
        deleteItem.click();
    }

    public boolean hasStrikeThrough(String id){
        WebElement strikeThroughElement = driver.findElement(By.id(id));
        String textDecoration = strikeThroughElement.getCssValue("text-decoration");

        if(textDecoration.contains("line-through")){
            return true;
        }
        
        return false;
    }
    
}
