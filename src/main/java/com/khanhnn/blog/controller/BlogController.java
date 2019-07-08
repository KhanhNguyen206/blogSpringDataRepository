package com.khanhnn.blog.controller;


import com.khanhnn.blog.model.Blog;
import com.khanhnn.blog.model.Category;
import com.khanhnn.blog.service.BlogService;
import com.khanhnn.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }

    @GetMapping("/blog")
    public ModelAndView index(Pageable pageable){
        Page<Blog> blogs= blogService.findAll(pageable);
        ModelAndView modelAndView=new ModelAndView("blog/index");
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @GetMapping("/blog/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("blog/create", "blog", new Blog());
        return modelAndView;
    }

    @PostMapping("/blog/create")
    public String add(@ModelAttribute Blog blog, RedirectAttributes redirectAttributes){
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("success", "A blog was created");
        return "redirect:/blog";
    }

    @GetMapping("/blog/detail/{id}")
    public ModelAndView detail(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("blog/detail");
        modelAndView.addObject("blog", blogService.findById(id));
        return modelAndView;
    }

    @GetMapping("/blog/delete/{id}")
    public ModelAndView delete(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("blog/delete");
        modelAndView.addObject("blog", blogService.findById(id));
        return modelAndView;
    }

    @PostMapping("/blog/delete")
    public String remove(@ModelAttribute Blog blog, RedirectAttributes redirectAttributes){
        blogService.remove(blog.getId());
        redirectAttributes.addFlashAttribute("success", "A blog was deleted");
        return "redirect:/blog";
    }

    @GetMapping("/blog/edit/{id}")
    public ModelAndView update(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("blog/edit");
        modelAndView.addObject("blog", blogService.findById(id));
        return modelAndView;
    }

    @PostMapping("/blog/edit")
    public String edit(@ModelAttribute Blog blog, RedirectAttributes redirectAttributes){
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("success", "A blog was edited");
        return "redirect:/blog";
    }
}
