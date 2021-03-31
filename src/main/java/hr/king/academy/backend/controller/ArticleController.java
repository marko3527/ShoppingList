package hr.king.academy.backend.controller;

import hr.king.academy.backend.domain.Article;
import hr.king.academy.backend.service.ArticleService;
import hr.king.academy.backend.transfer.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/article/{id}")
    public ResponseEntity<ArticleDTO> getById(@PathVariable("id") String id) {
        ArticleDTO article = articleService.getById(id);

        ResponseEntity<ArticleDTO> response = ResponseEntity.ok(article);
        return response;
    }

    @PostMapping("/addArticle")
    public ResponseEntity<Article> addArticle(@RequestBody ArticleDTO articleDTO) {
        Article article = articleService.addOneArticle(articleDTO);

        ResponseEntity<Article> response = ResponseEntity.ok(article);
        return response;
    }

    @PostMapping("/changeStatus")
    public ResponseEntity<Article> changeStatus(@RequestBody String articleId) {

        Article article = articleService.updateArticleStatus(articleId.replace("=",""));

        ResponseEntity<Article> response = ResponseEntity.ok(article);
        return response;
    }

}
