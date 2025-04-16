package it.aulab.xjava.controllers;

import it.aulab.xjava.dtos.CategoryDto;
import it.aulab.xjava.models.Category;
import it.aulab.xjava.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showAllCategories(Model model) {
        model.addAttribute("categories", categoryService.readAll());
        return "category/index"; // 返回所有分类页面
    }

    @GetMapping("/create")
    public String createCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/create"; // 创建分类页面
    }

    @PostMapping("/create")
    public String createCategory(@ModelAttribute Category category) {
        categoryService.create(category, null, null); // 创建分类
        return "redirect:/categories"; // 创建成功后重定向到分类列表页
    }

    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.read(id)); // 获取分类并显示编辑表单
        return "category/edit"; // 编辑分类页面
    }

    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute Category category) {
        categoryService.update(id, category, null); // 更新分类
        return "redirect:/categories"; // 更新成功后重定向到分类列表页
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.delete(id); // 删除分类
        return "redirect:/categories"; // 删除后重定向到分类列表页
    }
}
