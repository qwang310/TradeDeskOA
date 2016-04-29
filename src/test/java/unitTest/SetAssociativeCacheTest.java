package unitTest;


import algorithm.*;
import dao.CacheNode;
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
	public void testDifferentClass(){
		final int N = 2; //NodeSet Capacity
		final int totalNumOfCacheNode = 2;

		Algorithms algorithms = new LRU();
		NWaysSetAssociativeCache<CacheNode<Integer,Integer>, String> testCache = new NWaysSetAssociativeCache<>(N, totalNumOfCacheNode, algorithms);

        CacheNode<Integer,Integer> cacheNode1 = new CacheNode<>(1,2);
        CacheNode<Integer,Integer> cacheNode2 = new CacheNode<>(1,2);
        CacheNode<Integer,Integer> cacheNode3 = new CacheNode<>(1,2);
		testCache.saveToCache(cacheNode1, "0");
		testCache.saveToCache(cacheNode2, "1");
		testCache.saveToCache(cacheNode3, "2");

		System.out.println(testCache.getFromCache(cacheNode1));
		System.out.println(testCache.getFromCache(cacheNode2));
		System.out.println(testCache.getFromCache(cacheNode3));
		assertNull(testCache.getFromCache(cacheNode1));


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