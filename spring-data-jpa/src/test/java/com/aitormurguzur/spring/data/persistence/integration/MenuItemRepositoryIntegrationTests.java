package com.aitormurguzur.spring.data.persistence.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aitormurguzur.spring.data.persistence.repository.mongo.MenuItemRepository;

import static com.aitormurguzur.spring.data.persistence.domain.fixture.PersistenceFixture.standardItem;
import static junit.framework.TestCase.assertEquals;

@ContextConfiguration(locations = { "/spring/app-config.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MenuItemRepositoryIntegrationTests {

	@Autowired
	MenuItemRepository menuItemRepository;

	@Autowired
	MongoOperations mongo;

	@Before
	public void setup() throws Exception {
		mongo.dropCollection("menu");
	}

	@After
	public void teardown() {
		mongo.dropCollection("menu");
	}

	@Test
	public void thatItemIsInsertedIntoRepoWorks() throws Exception {
		assertEquals(0, mongo.getCollection("menu").count());
		menuItemRepository.save(standardItem());
		assertEquals(1, mongo.getCollection("menu").count());
	}
}