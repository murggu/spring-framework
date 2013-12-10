package com.aitormurguzur.spring.data.persistence.repository.mongo;

import org.springframework.data.repository.CrudRepository;

import com.aitormurguzur.spring.data.persistence.domain.MenuItem;

import java.util.List;

public interface MenuItemRepository extends CrudRepository<MenuItem, String>, AnalyseIngredients {

	public List<MenuItem> findByIngredientsNameIn(String... name);
}