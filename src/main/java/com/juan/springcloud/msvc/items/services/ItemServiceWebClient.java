package com.juan.springcloud.msvc.items.services;

import com.juan.springcloud.msvc.items.models.Item;
import com.juan.springcloud.msvc.items.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.*;

//@Primary
@Service
public class ItemServiceWebClient implements ItemService{

    private final WebClient.Builder client;

    public ItemServiceWebClient(Builder client) {
        this.client = client;
    }

    @Override
    public List<Item> findAll() {
        return this.client.build()
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Product.class)
                .map(product -> new Item(product, new Random().nextInt(10) + 1 ))
                .collectList()
                .block();
    }

    @Override
    public Optional<Item> findById(Long id) {
        Map<String, Object> params = new HashMap();
        params.put("id", id);
        try{
            return Optional.ofNullable(
                    this.client.build()
                            .get()
                            .uri("/{id}", params)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(Product.class)
                            .map(product -> new Item(product, new Random().nextInt(10) + 1 ))
                            .block()
            );
        }catch (WebClientResponseException e){
            return Optional.empty();
        }
    }
}
