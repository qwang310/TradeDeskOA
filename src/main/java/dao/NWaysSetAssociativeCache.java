package dao;

import algorithm.Algorithms;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qu2k8o6 on 4/19/16.
 */
public class NWaysSetAssociativeCache<T1, T2> implements SetAssociativeCache<T1, T2> {

    private final int N; //NodeSet Capacity
    private final int totalNumOfCacheNode;
    private Map<Integer, NodeSet<T1, T2>> setMap;
    private final int numOfSets;
    private final Algorithms algorithms;

    public NWaysSetAssociativeCache(int N, int totalNumOfCacheNode, Algorithms algorithms){
        if(N < 1 || totalNumOfCacheNode < N){
            throw new RuntimeException("N or TotalNumOfCacheNode is not defined correctly");
        }

        if(totalNumOfCacheNode%N != 0){
            throw new RuntimeException("TotalNumOfCacheNode is not divisiable by N");
        }

        this.N = N; // Cache Set Capacity
        this.totalNumOfCacheNode = totalNumOfCacheNode;
        this.setMap = Collections.synchronizedMap(new HashMap<Integer, NodeSet<T1, T2>>());
        this.numOfSets = totalNumOfCacheNode/N;
        this.algorithms = algorithms;
        setConfigration(N);

    }

    public void setConfigration(int N){
        for(int i = 0; i < numOfSets;i++){
            setMap.put(i, new NodeSet(i, N));
        }
    }

    public void saveToCache(T1 key, T2 value){
        int setIndex = key.hashCode()%numOfSets;
        synchronized(setMap) {
            NodeSet<T1, T2> currentNodeSet = setMap.get(setIndex);
            setValue(key, value, currentNodeSet);
        }
    }

    public T2 getFromCache(T1 key){
        int setIndex = key.hashCode()%numOfSets;
        synchronized(setMap) {
            NodeSet<T1, T2> currentNodeSet = setMap.get(setIndex);
            T2 value = getValue(key, currentNodeSet);
            return value;
        }
    }


    private T2 getValue(T1 key, NodeSet<T1,T2> nodeSet){
        Map<T1, CacheNode<T1, T2>> nodeMap = nodeSet.nodeMap;
        if(!nodeMap.containsKey(key)){
            return null;
        }
        CacheNode<T1, T2> currentCache = nodeMap.get(key);
        currentCache.pre.next = currentCache.next;
        currentCache.next.pre = currentCache.pre;

        moveToTail(currentCache, nodeSet);

        return nodeMap.get(key).value;
    }

    private void setValue(T1 key, T2 value, NodeSet<T1,T2> nodeSet) {

        Map<T1, CacheNode<T1, T2>> nodeMap = nodeSet.nodeMap;
        if(getValue(key, nodeSet) != null){
            nodeMap.get(key).value = value;
            return;
        }

        if(nodeSet.setCapacity == 0){
            return;
        }

        if(nodeMap.size() == nodeSet.setCapacity){
            removeCache(nodeSet);
        }

        CacheNode<T1, T2> newCacheNode = new CacheNode<T1, T2>(key, value);
        moveToTail(newCacheNode, nodeSet);
        nodeMap.put(key, newCacheNode);

    }

    private void removeCache(NodeSet<T1,T2> nodeSet){
        int counter = algorithms.getIndexOfCacheNodeToBeRemoved(N);
        CacheNode<T1, T2> nodeToRemove = nodeSet.head.next;
        while(counter > 0){
            nodeToRemove = nodeToRemove.next;
            counter--;
        }
        T1 first = nodeToRemove.key;
        nodeSet.nodeMap.remove(first);
        nodeSet.head.next = nodeSet.head.next.next;
        nodeSet.head.next.pre = nodeSet.head;

    }


    private void moveToTail(CacheNode<T1, T2> currentCache, NodeSet<T1,T2> nodeSet){
        nodeSet.tail.pre.next = currentCache;
        currentCache.pre = nodeSet.tail.pre;
        currentCache.next = nodeSet.tail;
        nodeSet.tail.pre = currentCache;
    }

}
