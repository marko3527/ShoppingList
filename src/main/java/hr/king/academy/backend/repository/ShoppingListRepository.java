package hr.king.academy.backend.repository;

import hr.king.academy.backend.domain.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

    List<ShoppingList> findAllByDraftTrue();
    List<ShoppingList> findAllByDraftFalse();
}
