package hr.king.academy.backend.service;

import hr.king.academy.backend.domain.Article;
import hr.king.academy.backend.repository.ArticleRepository;
import hr.king.academy.backend.transfer.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;


    /**
     * Method taht returns article with id.
     *
     * @param id
     * @return
     */
    public ArticleDTO getById(String id) {
        Optional<Article> a = articleRepository.findById(Long.parseLong(id));
        Article article = a.get();

        ArticleDTO articleDTO = new ArticleDTO(article.getName(),
                                               article.getAmount(),
                                               article.isAddedToCart(),
                                               article.getShoppingList().getId(),
                                               "" + article.getId());

        return articleDTO;
    }


    /**
     * Adds one article to the database of articles
     *
     * @param articleDTO
     * @return
     */
    public Article addOneArticle(ArticleDTO articleDTO) {
        Article newArticle = new Article();
        newArticle.setName(articleDTO.getName());
        newArticle.setAddedToCart(articleDTO.isAddedToCart());
        newArticle.setAmount(articleDTO.getAmount());

        articleRepository.save(newArticle);
        return newArticle;
    }


    /**
     * Updates status od the article, by negating the current status, that means
     * if article is not added to cart it changes it's status to added to cart.
     *
     * @param id
     * @return
     */
    public Article updateArticleStatus(String id) {

        Optional<Article> a = articleRepository.findById(Long.parseLong(id));
        a.get().setAddedToCart(!a.get().isAddedToCart());
        articleRepository.save(a.get());

        return a.get();

    }


    /**
     * Method that removes article from database
     *
     * @param articleId
     * @return
     */
    public Article removeArticle(String articleId) {
        articleId = articleId.replace("=", "");
        Article article = articleRepository.findById(Long.parseLong(articleId)).get();
        articleRepository.delete(article);

        return article;
    }






}