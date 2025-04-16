package it.aulab.xjava.dtos;

import java.time.LocalDate;
import it.aulab.xjava.models.Category;
import it.aulab.xjava.models.User;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String subtitle;
    private String body;
    private LocalDate publishDate;
    private User user;
    private Category category;
    private String imagePath; // ✅ 新增字段
}

