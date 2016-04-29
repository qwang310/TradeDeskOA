package dao;

import algorithm.Algorithms;
import service.CacheServie;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qu2k8o6 on 4/19/16.
 */
public class NWaysSetAssociativeCache<T1, T2>{

    private final int N; //NodeSet Capacity
    private final int totalNumOfCacheNode;
    private Map<Integer, NodeSet<T1, T2>> setMap;
    private final int numOfSets;
    private final Algorithms algorithms;
    private final CacheServie<T1, T2> cacheServie = new CacheServie<>();

    public NWaysSetAssociativeCache(int N, int totalNumOfCacheNode, Algorithms algorithms){
        if(N < 1 || totalNumOfCacheNode < N){
            throw new RuntimeException("N or TotalNumOfCacheNode is not defined correctly");
        }

        if(totalNumOfCacheNode%N != 0){
            throw new RuntimeException("TotalNumOfCacheNode is not divisiable by N");
        }

        this.N = N; // Cache Set Capacity
        this.totalNumOfCacheNode = totalNumOfCacheNode;
        this.setMap = new HashMap<Integer, NodeSet<T1, T2>>();
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
        NodeSet<T1, T2> currentNodeSet = setMap.get(setIndex);
        int indexOfCacheNodeToBeRemovedb = algorithms.getIndexOfCacheNodeToBeRemoved(N);
        cacheServie.setValue(key, value, currentNodeSet, indexOfCacheNodeToBeRemovedb);
    }

    public T2 getFromCache(T1 key){
        int setIndex = key.hashCode()%numOfSets;
        NodeSet<T1, T2> currentNodeSet = setMap.get(setIndex);
        T2 value = cacheServie.getValue(key, currentNodeSet);
        return value;
    }


}
