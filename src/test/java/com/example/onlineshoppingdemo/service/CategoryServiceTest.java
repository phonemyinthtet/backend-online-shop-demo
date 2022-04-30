package com.example.onlineshoppingdemo.service;

import com.example.onlineshoppingdemo.dao.CategoryDao;
import com.example.onlineshoppingdemo.ds.Category;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
public class CategoryServiceTest {
    @Mock
    private CategoryDao categoryDao;

    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryService(categoryDao);
    }
    @Test
    public void should_get_all_categorys() throws Exception{
        categoryService.getAllCategory();
        verify(categoryDao).findAll();
    }

    @Test
    public void should_add_category() throws Exception{
        var category = new Category(1,"BOOK");
        categoryService.addCategory(category);

        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        verify(categoryDao).save(categoryArgumentCaptor.capture());
        var capturedCategory = categoryArgumentCaptor.getValue();
        AssertionsForClassTypes.assertThat(capturedCategory).isEqualTo(category);
    }
    @Test
    public void should_delete_by_id() throws Exception{
        var category = new Category(1,"BOOK");
        categoryService.deleteCategoryById(category.getId());
        verify(categoryDao).deleteById(category.getId());
    }
    @Test
    public void should_get_category_by_id() throws Exception{
        var category = new Category(1,"BOOK");
        categoryService.getCategoryById(category.getId());
        verify(categoryDao).findById(category.getId());
    }
}
