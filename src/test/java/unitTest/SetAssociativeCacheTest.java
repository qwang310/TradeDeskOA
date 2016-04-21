package unitTest;


import algorithm.Algorithms;
import algorithm.LRU;
import algorithm.MRU;
import dao.NWaysSetAssociativeCache;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.cache.support.NullValue;

import javax.validation.constraints.Null;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/*
Test validation rules
 */
public class SetAssociativeCacheTest {

	@Test
	public void testLRU(){

		Algorithms<Integer, String> algorithms = new LRU<>();
		NWaysSetAssociativeCache<Integer, String> testCache = new NWaysSetAssociativeCache<Integer, String>(2, 2, algorithms);

		testCache.saveToCache(10, "one");
		testCache.saveToCache(1099, "two");
		testCache.saveToCache(2112, "three");


		System.out.println(testCache.getFromCache(10));
		System.out.println(testCache.getFromCache(1099));
		System.out.println(testCache.getFromCache(2112));
		assertNull(testCache.getFromCache(10));


	}

	@Test
	public void testMRU() {
		Algorithms<Integer, String> algorithms = new MRU<>();
		NWaysSetAssociativeCache<Integer, String> testCache = new NWaysSetAssociativeCache<Integer, String>(2, 2, algorithms);
		testCache.saveToCache(10, "one");
		testCache.saveToCache(1099, "two");
		testCache.saveToCache(2112, "three");


		System.out.println(testCache.getFromCache(10));
		System.out.println(testCache.getFromCache(1099));
		System.out.println(testCache.getFromCache(2112));
		assertNull(testCache.getFromCache(1099));

	}


}