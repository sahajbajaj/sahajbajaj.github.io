package pageobjects.todopage;

public enum Category {
    BUSINESS("business"),
    PERSONAL("personal");

    private final String id;

    Category(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }
}
