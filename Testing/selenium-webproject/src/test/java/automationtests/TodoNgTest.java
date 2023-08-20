package automationtests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.todopage.Category;
import pageobjects.todopage.TodoPageObject;

public class TodoNgTest {
    TodoPageObject todoPage;

    @BeforeMethod
    public void openBrower(){
        todoPage = new TodoPageObject();
    }

    @AfterMethod
    public void closeBrowser(){
        todoPage.closeBrowser();
    }

    @DataProvider(name = "inputNames")
    public static Object[][] fixtureNames() {
        return new Object[][] {
            {"John"},
            {"Mary"},
            {"Dave"}
        };
    }

    @DataProvider(name = "inputItems")
    public static Object[][] fixtureTodoitems() {
        return new Object[][] {
            {"cleanhouse"},
            {"cleanoffice"},
            {"cookdinner"}
        };
    }
    
    @Test(dataProvider = "inputNames", testName = "Enter: {0} Displays On Page")
    public void navigateToTodoPage_EnterName_DisplaysOnPage(String inputTitle) {
        //Arrange as set up via data provider

        //Act
        String inputElemenValue = todoPage.inputTitle(inputTitle);
    

        //Assert
        Assert.assertEquals(inputElemenValue, inputTitle);        
    }

    @Test(dataProvider = "inputItems", testName = "Enter: {0} into Todo Box")
    public void navigateToTodoPage_EnterTodoItem_DisplaysOnPage(String todoItem) {
        //Arrange as set up via data provider 

        //Act
        String todoInputValue = todoPage.inputTodoItem(todoItem);

        //Assert
        Assert.assertEquals(todoInputValue, todoItem);
    }

    @Test(dataProvider = "inputItems", testName = "Enter: {0} into Todo Box and select personal category")
    public void navigateToTodoPage_EnterItemAndSelectPersonalCategory_DisplaysOnPage(String todoItem) {
        //Arrange
        Boolean personalCategoryIsSelected = true;   

        //Act
        todoPage.inputTodoItem(todoItem);
        Boolean categoryElementSelected = todoPage.selectCategory(Category.PERSONAL);

        //Assert
        Assert.assertEquals(categoryElementSelected, personalCategoryIsSelected);
    }

    @Test(dataProvider = "inputItems", testName = "Enter: {0} into Todo Box and select business category")
    public void navigateToTodoPage_EnterItemAndSelectBusinessCategory_DisplaysOnPage(String todoItem) {
        //Arrange
        Boolean businessCategoryIsSelected = true;   

        //Act
        todoPage.inputTodoItem(todoItem);
        Boolean categoryElementSelected = todoPage.selectCategory(Category.BUSINESS);

        //Assert
        Assert.assertEquals(categoryElementSelected, businessCategoryIsSelected); 
    }

    @Test(dataProvider = "inputItems", testName = "Add: {0} to Todo List and click add button")
    public void navigateToTodoPage_AddItemToList_DisplaysOnPage(String todoItem) {
        //Arrange as set up via data provider 
        
        //Act
        todoPage.inputTodoItem(todoItem);
        todoPage.selectCategory(Category.BUSINESS);
        todoPage.addTodo();
        String findItemInTodoList = todoPage.findTextById(todoItem);

        //Assert
        Assert.assertEquals(todoItem, findItemInTodoList);      
    }

    @Test(dataProvider = "inputItems", testName = "Add: {0} to Todo List and click strikethrough")
    public void navigateToTodoPage_AddItemToList_AddStrikeThrough(String todoItem) {
        //Arrange as set up via data provider 
        
        //Act
        todoPage.inputTodoItem(todoItem);
        todoPage.selectCategory(Category.BUSINESS);
        todoPage.addTodo();
        todoPage.strikeThrough();
        Boolean hasStrikeThrough = todoPage.hasStrikeThrough(todoItem);

        //Assert
        Assert.assertTrue(hasStrikeThrough, "Error: the todo item is not striked through");      
    }

    @Test(dataProvider = "inputItems", testName = "Add: {0} to Todo List and click delete button")
    public void navigateToTodoPage_AddItemToList_DeleteItem(String todoItem) {
        //Arrange as set up via data provider 
        
        //Act
        todoPage.inputTodoItem(todoItem);
        todoPage.selectCategory(Category.BUSINESS);
        todoPage.addTodo();
        todoPage.deleteTodoItem(todoItem);
        String deletedItem = todoPage.findTextById(todoItem);

        //Assert
        Assert.assertNull(deletedItem, "Error: the todo item is not deleted");      
    }
}