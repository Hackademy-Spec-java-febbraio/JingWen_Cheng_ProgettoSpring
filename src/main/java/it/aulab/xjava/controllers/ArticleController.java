package it.aulab.xjava.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.aulab.xjava.dtos.ArticleDto;
import it.aulab.xjava.models.Article;
import it.aulab.xjava.services.ArticleService;
import it.aulab.xjava.services.CategoryService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/create")
    public String createArticleForm(Model model) {
        model.addAttribute("title", "Crea un articolo");
        model.addAttribute("article", new Article());
        model.addAttribute("categories", categoryService.readAll());
        return "article/create";
    }

    @PostMapping
    public String createArticle(
        @Valid @ModelAttribute("article") Article article,
        BindingResult result,
        @RequestParam("file-image") MultipartFile file,
        RedirectAttributes redirectAttributes,
        Principal principal,
        Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Crea un articolo");
            model.addAttribute("categories", categoryService.readAll());
            return "article/create";
        }
    
        articleService.create(article, principal, file);
    
        redirectAttributes.addFlashAttribute("successMessage", "Articolo aggiunto con successo!");
        return "redirect:/articles";
    }
    
    @GetMapping
    public String showAllArticles(Model model) {
        model.addAttribute("title", "Articoli");
        model.addAttribute("articles", articleService.readAll());
        return "article/index";
    }
    
    @GetMapping("/{id}")
    public String showArticle(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.read(id));
        return "article/show";
    }

    @GetMapping("/edit/{id}")
    public String editArticleForm(@PathVariable Long id, Model model) {
        ArticleDto article = articleService.read(id);
        model.addAttribute("title", "Modifica articolo");
        model.addAttribute("article", article);
        model.addAttribute("categories", categoryService.readAll());
        return "article/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateArticle(
        @PathVariable Long id,
        @Valid @ModelAttribute("article") Article article,
        BindingResult result,
        @RequestParam("file-image") MultipartFile file,
        RedirectAttributes redirectAttributes,
        Model model
    ) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("title", "Modifica articolo");
            model.addAttribute("categories", categoryService.readAll());
            return "article/edit";
        }

        articleService.update(id, article, file);
    
        redirectAttributes.addFlashAttribute("successMessage", "Articolo aggiornato con successo!");
        return "redirect:/articles";
    }
    
    @PostMapping("/delete/{id}")
    public String deleteArticle(@PathVariable Long id, RedirectAttributes redirectAttributes) throws IOException {
        articleService.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "Articolo eliminato con successo!");
        return "redirect:/articles";
    }
}
