package com.khanhnn.blog.service;

import com.khanhnn.blog.model.Blog;
import com.khanhnn.blog.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    Iterable<Blog> findAll();

    Blog findById(Long id);

    void save(Blog blog);

    void remove(Long id);

    Iterable<Blog> findAllByCategory (Category category);

    Page<Blog> findAll(Pageable pageable);
}
