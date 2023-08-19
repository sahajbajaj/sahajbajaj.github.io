package pageobjects.todopage;

public enum Category {
    BUSINESS("businessbutton"),
    PERSONAL("personalbutton");

    private final String id;

    Category(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }
}
