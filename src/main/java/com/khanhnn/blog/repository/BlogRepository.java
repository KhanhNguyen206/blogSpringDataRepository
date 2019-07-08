package com.khanhnn.blog.repository;

import com.khanhnn.blog.model.Blog;
import com.khanhnn.blog.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepository extends PagingAndSortingRepository<Blog, Long> {

    Iterable<Blog> findAllByCategory(Category category);

}