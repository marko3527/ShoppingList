package hr.king.academy.backend.controller;

import hr.king.academy.backend.domain.ShoppingList;
import hr.king.academy.backend.service.ShoppingListService;
import hr.king.academy.backend.transfer.ArticleDTO;
import hr.king.academy.backend.transfer.ShoppingListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ShoppingListController {

    @Autowired
    private ShoppingListService shoppingListService;

    @GetMapping("listItems/{id}")
    public ResponseEntity<ShoppingListDTO> listArticles(@PathVariable("id") String id) {

        ShoppingListDTO articles = shoppingListService.getShoppingList(id);

        ResponseEntity<ShoppingListDTO> response = ResponseEntity.ok(articles);
        return response;
    }

    @GetMapping("showLists")
    public ResponseEntity<List<ShoppingListDTO>> getAllLists() {
        List<ShoppingListDTO> lists = shoppingListService.getAllLists();

        ResponseEntity<List<ShoppingListDTO>> response = ResponseEntity.ok(lists);
        return response;

    }

    @GetMapping("showDrafts")
    public ResponseEntity<List<ShoppingListDTO>> getDrafts() {
        List<ShoppingListDTO> lists = shoppingListService.getDrafts();

        ResponseEntity<List<ShoppingListDTO>> response = ResponseEntity.ok(lists);
        return response;
    }

    @GetMapping("showPublished")
    public ResponseEntity<List<ShoppingListDTO>> getPublished() {
        List<ShoppingListDTO> lists = shoppingListService.getPublished();

        ResponseEntity<List<ShoppingListDTO>> response = ResponseEntity.ok(lists);
        return response;
    }


    @PostMapping("addToList/{id}")
    public ResponseEntity<ArticleDTO> addArticleToList(@RequestBody ArticleDTO articleDTO, @PathVariable("id") String listId) {

        ArticleDTO addedArticle = shoppingListService.addArticleToList(articleDTO, listId);

        ResponseEntity<ArticleDTO> response = ResponseEntity.ok(addedArticle);
        return response;
    }


    @PostMapping("newList")
    public ResponseEntity<ShoppingList> createList(@RequestBody ShoppingList shoppingList) {

        ShoppingList addedList = shoppingListService.createList(shoppingList);

        ResponseEntity<ShoppingList> response = ResponseEntity.ok(addedList);
        return response;

    }

    @PostMapping("draftStatus")
    public ResponseEntity<ShoppingList> updateStatus(@RequestBody String id) {
        ShoppingList list = shoppingListService.updateListStatus(id.replace("=", ""));

        ResponseEntity<ShoppingList> response = ResponseEntity.ok(list);
        return response;
    }

    @PostMapping("removeArticle")
    public ResponseEntity<String> removeArticle(@RequestBody String articleId){

            String responseMessage = shoppingListService.removeArticle(articleId);

            ResponseEntity<String> response = ResponseEntity.ok(responseMessage);
            return response;
    }


    @DeleteMapping("/deleteList/{id}")
    public ResponseEntity<String> deleteList(@PathVariable("id") String listId) {
        String id = shoppingListService.deleteList(listId);

        ResponseEntity<String> response = ResponseEntity.ok(id);
        return response;
    }

}
