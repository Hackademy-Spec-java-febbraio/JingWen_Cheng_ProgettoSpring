package it.aulab.xjava.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.aulab.xjava.models.Category;
import it.aulab.xjava.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // 获取所有分类
    public List<Category> readAll() {
        return categoryRepository.findAll();
    }

    // 通过ID获取分类
    public Category read(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);  // 如果没有找到分类，返回null
    }

    // 创建新分类
    public void create(Category category) {
        categoryRepository.save(category);  // 保存新分类
    }

    // 更新分类
    public void update(Long id, Category category) {
        Optional<Category> existingCategoryOpt = categoryRepository.findById(id);
        if (existingCategoryOpt.isPresent()) {
            Category existingCategory = existingCategoryOpt.get();
            existingCategory.setName(category.getName());  // 更新分类名称
            categoryRepository.save(existingCategory);  // 保存更新后的分类
        }
    }

    // 删除分类
    public void delete(Long id) {
        categoryRepository.deleteById(id);  // 删除分类
    }
}
