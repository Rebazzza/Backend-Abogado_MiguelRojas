package com.heavylink.service;

import com.heavylink.model.Menu;

import java.util.List;

public interface IMenuService extends IGenericService<Menu, Integer> {
    List<Menu> getMenusByUsername();
}