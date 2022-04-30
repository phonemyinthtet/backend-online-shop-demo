package com.example.onlineshoppingdemo.ds;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

public class CategoryTest {
    private static final Integer ID = 1;
    private static final String NAME = "BOOK";
    @Test
    public void should_hash_code()throws Exception{
        var categoryOne = new Category(1,"BOOK");
        var categoryTwo = new Category(1,"BOOK");
        AssertionsForClassTypes.assertThat(categoryOne.equals(categoryTwo) && categoryTwo.equals(categoryOne));
        AssertionsForClassTypes.assertThat(categoryOne.hashCode() == categoryTwo.hashCode());
    }

    @Test
    public void should_all_arg_constructor_category()throws Exception{
        //given
        var category = new Category(1,"BOOK");

        AssertionsForClassTypes.assertThat(category.getId()).isEqualTo(ID);
        AssertionsForClassTypes.assertThat(category.getName()).isEqualTo(NAME);

    }
    @Test
    public void should_no_arg_and_setter_category() throws Exception{
        var category = new Category();
        category.setId(1);
        category.setName("BOOK");
        AssertionsForClassTypes.assertThat(category.getId()).isEqualTo(ID);
        AssertionsForClassTypes.assertThat(category.getName()).isEqualTo(NAME);
    }
}

