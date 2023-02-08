package com.liuche.pojo;

import com.liuche.annotation.Book;

@Book(name = "武林外传", authors = {"文强", "武强"}, price = 99.8)
public class Store {
    @Book(name = "三体", authors = {"汪淼", "叶文洁"}, price = 399.8)
    public void test() {

    }
}
