package com.example.shopeefood.service.shop;

import com.example.shopeefood.model.Menu;
import com.example.shopeefood.model.Shop;
import com.example.shopeefood.service.IGenerateService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IShopService extends IGenerateService<Shop> {

}

