package com.aitormurguzur.spring.data.persistence.repository.mongo;

import java.util.Map;

//This is used during step 2/ Map/ Reduce. Ignore until that point.
public interface AnalyseIngredients {

	public Map<String, Integer> analyseIngredientsByPopularity();
}
