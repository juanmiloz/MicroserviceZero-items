package com.juan.springcloud.msvc.items.services;

import com.juan.springcloud.msvc.items.models.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    public List<Item> findAll();

    public Optional<Item> findById(Long id);

}
