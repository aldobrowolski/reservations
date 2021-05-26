package com.exercise.hotel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabasaLoaderTest {
	
	@Autowired
	DatabaseLoader databaseLoader;

	@Test
	public void testDatabaseLoader() throws Exception {
		//databaseLoader.run(new String[0]);
	}
}
