package com.example.onlineshoppingdemo.controller;

import com.example.onlineshoppingdemo.ds.Category;
import com.example.onlineshoppingdemo.service.CategoryService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void should_get_category() throws Exception{
        Mockito.when(categoryService.getAllCategory()).thenReturn(List.of(new Category(1,"BOOK")));

    var jsonResponse = mvc.perform(
            MockMvcRequestBuilders.get("/api/admin/categories")).andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn().getResponse().getContentAsString();
            AssertionsForClassTypes.assertThat(jsonResponse).contains(
                    "{\"id\":1,\"name\":\"BOOK\"}");
        verify(categoryService).getAllCategory();
    }
    @Test
    public void should_add_category() throws Exception{
        mvc.perform(
                MockMvcRequestBuilders.post("/api/admin/add/category")
                    .content("{\"name\":\"BOOK\"}")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(categoryService).addCategory(new Category(null,"BOOK"));
    }
    @Test
    public void should_delete_category_by_id() throws Exception{
        mvc.perform(
                MockMvcRequestBuilders.get("/api/admin/delete/category/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(categoryService).deleteCategoryById(1);
    }
    @Test
    public void should_update_category_by_id() throws Exception{
        Mockito.when(categoryService.getCategoryById(1)).thenReturn(Optional.of(new Category(null,"BOOK")));
        mvc.perform(
                MockMvcRequestBuilders.patch("/api/admin/update/category/1")
                .content("{\"name\":\"BOOK\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
       Mockito.verify(categoryService).addCategory(new Category(null,"BOOK"));
    }

}
