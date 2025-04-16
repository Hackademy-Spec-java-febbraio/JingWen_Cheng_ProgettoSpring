package it.aulab.xjava.controllers;

import it.aulab.xjava.models.Category;
import it.aulab.xjava.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;  // 修改为jakarta.validation.Valid
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 显示所有分类
    @GetMapping
    public String getAllCategories(Model model) {
        model.addAttribute("categories", categoryService.readAll());
        return "category/index";  // 返回分类列表视图
    }

    // 创建新分类的表单
    @GetMapping("/create")
    public String createCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/create";  // 返回分类创建视图
    }

    // 保存新分类
    @PostMapping("/create")
    public String createCategory(@Valid @ModelAttribute("category") Category category,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "category/create";  // 如果验证错误，返回创建页面
        }
        categoryService.create(category);  // 调用服务层创建分类
        return "redirect:/categories";  // 创建成功后重定向到分类列表页
    }

    // 编辑分类的表单
    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable Long id, Model model) {
        Category category = categoryService.read(id);
        if (category == null) {
            // 如果找不到该分类，重定向到列表页或显示错误页面
            return "redirect:/categories";
        }
        model.addAttribute("category", category);
        return "category/edit";  // 返回分类编辑页面
    }

    // 更新分类
    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable Long id, @Valid @ModelAttribute("category") Category category,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "category/edit";  // 如果验证错误，返回编辑页面
        }
        categoryService.update(id, category);  // 调用服务层更新分类
        return "redirect:/categories";  // 更新成功后重定向到分类列表页
    }

    // 删除分类
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);  // 调用服务层删除分类
        return "redirect:/categories";  // 删除成功后重定向到分类列表页
    }
}
