package automationtests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.todopage.Category;
import pageobjects.todopage.TodoPageObject;

public class TodoNgTest {
    @Test
    public void testMethod() {
        
        TodoPageObject todoPage = new TodoPageObject();
        
        String todoItem = "testtodoitem";   

        todoPage.inputTitle("Hi Sahaj");
        todoPage.inputTodoItem(todoItem);

        todoPage.selectCategory(Category.BUSINESS);

        todoPage.addTodo();

        todoPage.strikeThrough();
        // todoPage.deleteTodoItem();

        Assert.assertTrue(todoPage.hasStrikeThrough(todoItem + "Id"), "item does not have strikethrough");

        todoPage.closeBrowse();
    }
}
