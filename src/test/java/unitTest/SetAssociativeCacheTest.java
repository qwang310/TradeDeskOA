package unitTest;


import algorithm.*;
import dao.NWaysSetAssociativeCache;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/*
Test validation rules
 */
public class SetAssociativeCacheTest {

	@Test
	public void testLRU(){
		final int N = 2; //NodeSet Capacity
		final int totalNumOfCacheNode = 2;

		Algorithms algorithms = new LRU();
		NWaysSetAssociativeCache<Integer, String> testCache = new NWaysSetAssociativeCache<Integer, String>(N, totalNumOfCacheNode, algorithms);

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
		final int N = 2; //NodeSet Capacity
		final int totalNumOfCacheNode = 2;
		Algorithms algorithms = new MRU();
		NWaysSetAssociativeCache<Integer, String> testCache = new NWaysSetAssociativeCache<Integer, String>(N, totalNumOfCacheNode, algorithms);
		testCache.saveToCache(10, "one");
		testCache.saveToCache(1099, "two");
		testCache.saveToCache(2112, "three");


		System.out.println(testCache.getFromCache(10));
		System.out.println(testCache.getFromCache(1099));
		System.out.println(testCache.getFromCache(2112));
		assertNull(testCache.getFromCache(1099));

	}

	@Test
	public void testException() {
		final int N = 2; //NodeSet Capacity
		final int totalNumOfCacheNode = 5;
		Algorithms algorithms = new MRU();
		String exceptionMessage = "";
		try {
			NWaysSetAssociativeCache<Integer, String> testCache = new NWaysSetAssociativeCache<Integer, String>(N, totalNumOfCacheNode, algorithms);
		} catch (Exception e) {
			exceptionMessage = e.getMessage();
		}

		assertThat(exceptionMessage, is("TotalNumOfCacheNode is not divisiable by N"));

	}



}