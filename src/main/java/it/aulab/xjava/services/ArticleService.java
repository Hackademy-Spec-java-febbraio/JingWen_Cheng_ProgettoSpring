package it.aulab.xjava.services;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.io.File;
import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import it.aulab.xjava.dtos.ArticleDto;
import it.aulab.xjava.models.Article;
import it.aulab.xjava.models.User;
import it.aulab.xjava.repositories.ArticleRepository;
import it.aulab.xjava.repositories.UserRepository;
import it.aulab.xjava.services.CustomUserDetails;

@Service
public class ArticleService implements CrudService<ArticleDto, Article, Long> {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${file.upload-dir}")
    private String uploadDir;  // 从配置文件读取上传路径

    @PostConstruct
    public void setupMapper() {
        modelMapper.addMappings(new org.modelmapper.PropertyMap<Article, ArticleDto>() {
            @Override
            protected void configure() {
                map(source.getImagePath(), destination.getImagePath());
            }
        });
    }

    @Override
    public List<ArticleDto> readAll() {
        return articleRepository.findAll().stream()
            .map(article -> modelMapper.map(article, ArticleDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public ArticleDto read(Long id) {
        Article article = articleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Article not found"));
        return modelMapper.map(article, ArticleDto.class);
    }

    @Override
    public ArticleDto create(Article article, Principal principal, MultipartFile file) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = userRepository.findById(userDetails.getId()).orElseThrow();
            article.setUser(user);
        }

        if (file != null && !file.isEmpty()) {
            try {
                // 使用从配置文件读取的路径
                String imagePath = uploadDir + "/" + System.currentTimeMillis() + "_" + file.getOriginalFilename();

                // 确保目录存在
                File directory = new File(uploadDir);
                if (!directory.exists()) {
                    directory.mkdirs();  // 如果目录不存在则创建
                }

                // 保存文件
                file.transferTo(new File(imagePath));

                // 设置图片路径
                article.setImagePath(imagePath);
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload image", e);
            }
        }

        // 保存文章
        Article saved = articleRepository.save(article);
        return modelMapper.map(saved, ArticleDto.class);
    }

    @Override
    public ArticleDto update(Long id, Article article, MultipartFile file) {
        // 获取现有文章
        Article existingArticle = articleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Article not found"));

        // 更新文章内容
        existingArticle.setTitle(article.getTitle());
        existingArticle.setSubtitle(article.getSubtitle());
        existingArticle.setBody(article.getBody());
        existingArticle.setCategory(article.getCategory());

        // 如果用户上传了新图片，保存新图片并替换旧路径
        if (file != null && !file.isEmpty()) {
            try {
                String imagePath = uploadDir + "/" + System.currentTimeMillis() + "_" + file.getOriginalFilename();

                // 确保目录存在
                File directory = new File(uploadDir);
                if (!directory.exists()) {
                    directory.mkdirs();  // 如果目录不存在则创建
                }

                // 保存新图片
                file.transferTo(new File(imagePath));

                // 删除旧图片
                if (existingArticle.getImagePath() != null) {
                    File oldImage = new File(existingArticle.getImagePath());
                    if (oldImage.exists()) {
                        oldImage.delete();  // 删除旧图片
                    }
                }

                // 设置新图片路径
                existingArticle.setImagePath(imagePath);
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload image", e);
            }
        }

        // 更新文章到数据库
        Article updatedArticle = articleRepository.save(existingArticle);
        return modelMapper.map(updatedArticle, ArticleDto.class);
    }

    @Override
    public void delete(Long id) {
        Article existingArticle = articleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Article not found"));

        // 删除文章图片文件
        if (existingArticle.getImagePath() != null) {
            File imageFile = new File(existingArticle.getImagePath());
            if (imageFile.exists()) {
                imageFile.delete();  // 删除图片文件
            }
        }

        // 删除文章
        articleRepository.delete(existingArticle);
    }
}
