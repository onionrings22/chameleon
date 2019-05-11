package web.service;

import db.dao.CategoryRepository;
import db.dao.ItemRepository;
import db.model.Category;
import db.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private ItemRepository itemRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ItemRepository itemRepository) {
        this.categoryRepository = categoryRepository;
        this.itemRepository = itemRepository;
    }

    //get the name of the current category
    public Category getCategory() {
        return categoryRepository.findByActiveTrue();
    }

    //get all logged in users
    public List<String> getItemsByCategory(String category) {
        List<Item> items = itemRepository.findByCategoryOrderById(category);
        List<String> itemTexts = new ArrayList<>();
        for (Item item: items) {
            itemTexts.add(item.getText());
        }
        return itemTexts;
    }

    //set a random category and item to be active
    @Transactional
    public void randomCategoryItem() {
        List<Category> categories = categoryRepository.findAllByLastIsNullOrLastFalse();
        Random rand = new Random();
        int index = rand.nextInt(categories.size());
        int itemNum = rand.nextInt(25);
        Category category = categories.get(index);
        category.setActive(true);
        category.setNum(itemNum);
    }

    //reset the category from active and mark it as last
    @Transactional
    public void resetCategory() {
        Category cur = categoryRepository.findByActiveTrue();
        Category prev = categoryRepository.findByLastTrue();
        if(prev != null) {
            prev.setLast(false);
            categoryRepository.save(prev);
        }

        cur.setLast(true);
        cur.setActive(false);
        categoryRepository.save(cur);
    }
}
