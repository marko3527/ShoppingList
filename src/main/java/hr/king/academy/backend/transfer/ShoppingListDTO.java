package hr.king.academy.backend.transfer;


import java.util.List;

public class ShoppingListDTO {

    private String listName;

    private List<ArticleDTO> articles;

    private String id;

    private boolean draft;

    public ShoppingListDTO(String name, List<ArticleDTO> articles, String id, boolean draft) {
        this.listName = name;
        this.articles = articles;
        this.id = id;
        this.draft = draft;
    }

    public ShoppingListDTO() {

    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public List<ArticleDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleDTO> articles) {
        this.articles = articles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }
}
