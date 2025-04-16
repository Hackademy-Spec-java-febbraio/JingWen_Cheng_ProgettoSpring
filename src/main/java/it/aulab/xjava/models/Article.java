package it.aulab.xjava.models;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotEmpty
    @Size(max = 100)
    private String title;

    @Column(nullable = false, length = 100)
    @NotEmpty
    @Size(max = 100)
    private String subtitle;

    @Column(nullable = false, length = 1000)
    @NotEmpty
    @Size(max = 1000)
    private String body; // 确保这里是 body 而不是 content

    @Column(nullable = true)
    @NotNull
    private LocalDate publishDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"articles"})
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "category_id")
private Category category;


    @Column(nullable = true, length = 255)
    private String imagePath; // 新增字段用于存储图像路径

    // 获取图像路径
    public String getImagePath() {
        return imagePath;
    }

    // 设置图像路径
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
}
