package hr.king.academy.backend.transfer;


public class ArticleDTO {

    private String name;

    private int amount;

    private boolean addedToCart;

    private long shoppingListId;

    private String id;



    public ArticleDTO(String name, int amount, boolean addedToCart, long shoppingListId, String id) {
        this.name = name;
        this.amount = amount;
        this.addedToCart = addedToCart;
        this.shoppingListId = shoppingListId;
        this.id = id;
    }

    /**
     * Default constructor.
     */
    public ArticleDTO() {

    }


    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isAddedToCart() {
        return addedToCart;
    }

    public void setAddedToCart(boolean addedToCart) {
        this.addedToCart = addedToCart;
    }

    public long getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(long shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}