package hr.king.academy.backend.service;

import hr.king.academy.backend.domain.Article;
import hr.king.academy.backend.domain.ShoppingList;
import hr.king.academy.backend.repository.ShoppingListRepository;
import hr.king.academy.backend.transfer.ArticleDTO;
import hr.king.academy.backend.transfer.ShoppingListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShoppingListService {

    @Autowired
    private ShoppingListRepository shopListRepository;

    @Autowired
    private ArticleService articleService;


    /**
     * Method that returns one list with given id
     *
     * @param listId
     * @return
     */
    public ShoppingListDTO getShoppingList(String listId) {
        ShoppingList shoppingList = shopListRepository.findById(Long.parseLong(listId)).get();
        List<ArticleDTO> articleDTOS = transferArticles(shoppingList.getArticles(), shoppingList.isDraft());
        ShoppingListDTO shoppingListDTO = new ShoppingListDTO(shoppingList.getListName(), articleDTOS, listId, shoppingList.isDraft());
        return shoppingListDTO;
    }


    /**
     * Method that adds one article to the list
     *
     * @param articleDTO
     * @param listId
     * @return
     */
    public ArticleDTO addArticleToList(ArticleDTO articleDTO, String listId) {
        Long shoppingListId = Long.parseLong(listId);
        Optional<ShoppingList> optionalList = shopListRepository.findById(shoppingListId);
        ShoppingList shoppingList = optionalList.get();

        List<Article> articles = shoppingList.getArticles();
        Article newArticle = articleService.addOneArticle(articleDTO);
        newArticle.setShoppingList(shoppingList);

        articles.add(newArticle);
        shoppingList.setArticles(articles);


        shopListRepository.save(shoppingList);

        return articleDTO;

    }


    /**
     * Method for creating a new database
     *
     * @param shoppingList
     * @return
     */
    public ShoppingList createList(ShoppingList shoppingList) {
        shopListRepository.save(shoppingList);
        return shoppingList;
    }


    /**
     * Deletes list from database if the list has all of its articles not added to cart
     *
     * @param listId
     * @return
     */
    public String deleteList(String listId) {
        ShoppingList shoppingList = shopListRepository.findById(Long.parseLong(listId)).get();
        if(cartHasArticles(shoppingList.getArticles())){
            return "List can't be deleted! It contains articles flagged as added to cart!";
        }
        shopListRepository.delete(shoppingList);

        return "List with name " + shoppingList.getListName() + " was succesfully deleted!";
    }


    /**
     * Method that gets all of the shopping list from database
     *
     * @return
     */
    public List<ShoppingListDTO> getAllLists() {
        List<ShoppingList> lists = shopListRepository.findAll();
        List<ShoppingListDTO> transferLists = makeTransferLists(lists);

        return transferLists;
    }


    /**
     * Method that makes transfer lists for frontend
     *
     * @param lists
     * @return
     */
    private List<ShoppingListDTO> makeTransferLists(List<ShoppingList> lists) {

        List<ShoppingListDTO> transferLists = new ArrayList<>();

        for(ShoppingList list : lists) {
            transferLists.add(new ShoppingListDTO(list.getListName(),
                    transferArticles(list.getArticles(),list.isDraft()),
                    "" + list.getId(), list.isDraft()));
        }
        Collections.sort(transferLists, new Comparator<ShoppingListDTO>() {
            @Override
            public int compare(ShoppingListDTO o1, ShoppingListDTO o2) {
                return o1.getListName().compareTo(o2.getListName());
            }
        });
        return transferLists;

    }


    /**
     * Method that returns all shopping lists that are flagged as draft
     *
     * @return
     */
    public List<ShoppingListDTO> getDrafts() {
        List<ShoppingList> lists = shopListRepository.findAllByDraftFalse();
        List<ShoppingListDTO> transferLists = makeTransferLists(lists);

        return transferLists;
    }


    /**
     * Method that returns all shopping lists that are flagged as published
     *
     * @return
     */
    public List<ShoppingListDTO> getPublished() {
        List<ShoppingList> lists = shopListRepository.findAllByDraftTrue();
        List<ShoppingListDTO> transferLists = makeTransferLists(lists);

        return transferLists;
    }


    /**
     * Method that transform a list of Articles into list of transfer articles
     *
     * @param articles
     * @return
     */
    private List<ArticleDTO> transferArticles(List<Article> articles, boolean isDraft) {

        List<ArticleDTO> articleDTOS = new ArrayList<>();

        for(Article article : articles) {
            articleDTOS.add(new ArticleDTO(article.getName(),
                    article.getAmount(),
                    article.isAddedToCart(),
                    article.getShoppingList().getId(),
                    "" + article.getId()));
        }

        Collections.sort(articleDTOS, new Comparator<ArticleDTO>() {
            @Override
            public int compare(ArticleDTO o1, ArticleDTO o2) {
                if(!isDraft) {
                    return o1.getName().compareTo(o2.getName());
                }
                else{
                    int booleanCompare = Boolean.compare(o1.isAddedToCart(), o2.isAddedToCart());
                    if(booleanCompare == 0){
                        return o1.getName().compareTo(o2.getName());
                    }
                    else return booleanCompare;
                }
            }
        });
        return articleDTOS;

    }


    /**
     * Method that deletes article from the shopping list
     *
     * @param articleId
     * @return
     */
    public String removeArticle(String articleId) {
        Article articleToRemove = articleService.removeArticle(articleId);
        ShoppingList shopList = articleToRemove.getShoppingList();

        if(shopList.getArticles().remove(articleToRemove)){
            shopListRepository.save(shopList);
            return "Obrisan artikl " + articleToRemove.getName();
        }

        return "Brisanje nije uspjelo";



    }


    /**
     * Method that updates list status from 'draft' to 'published'
     *
     * @param id
     * @return
     */
    public ShoppingList updateListStatus(String id) {

        Optional<ShoppingList> a = shopListRepository.findById(Long.parseLong(id));
        a.get().setDraft(!a.get().isDraft());
        shopListRepository.save(a.get());

        return a.get();

    }


    /**
     * Method that checks if there is any article that is flagged as added to cart in list of articles
     *
     * @param articles
     * @return
     */
    private boolean cartHasArticles(List<Article> articles) {
        for(Article article : articles) {
            if(article.isAddedToCart()){
                return true;
            }
        }
        return false;
    }
}
