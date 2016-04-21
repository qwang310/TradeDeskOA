package algorithm;

import dao.CacheNode;
import dao.NodeSet;

import java.util.Map;

/**
 * Created by qu2k8o6 on 4/20/16.
 */
public class MRU<T1, T2> implements Algorithms<T1, T2> {


    @Override
    public T2 getValue(T1 key, NodeSet<T1,T2> nodeSet){
        Map<T1, CacheNode<T1, T2>> nodeMap = nodeSet.nodeMap;
        if(!nodeMap.containsKey(key)){
            return null;
        }
        CacheNode<T1, T2> currentCache = nodeMap.get(key);
        currentCache.pre.next = currentCache.next;
        currentCache.next.pre = currentCache.pre;

        moveToHead(currentCache, nodeSet);

        return nodeMap.get(key).value;
    }

    @Override
    public void setValue(T1 key, T2 value, NodeSet<T1,T2> nodeSet) {

        Map<T1, CacheNode<T1, T2>> nodeMap = nodeSet.nodeMap;
        if(getValue(key, nodeSet) != null){
            nodeMap.get(key).value = value;
            return;
        }

        if(nodeSet.setCapacity == 0){
            return;
        }

        if(nodeMap.size() == nodeSet.setCapacity){
            T1 first = (T1)nodeSet.head.next.key;
            nodeMap.remove(first);
            nodeSet.head.next = nodeSet.head.next.next;
            nodeSet.head.next.pre = nodeSet.head;
        }

        CacheNode<T1, T2> newCacheNode = new CacheNode<T1, T2>(key, value);
        moveToHead(newCacheNode, nodeSet);
        nodeMap.put(key, newCacheNode);

    }


    private void moveToHead(CacheNode<T1, T2> currentCache, NodeSet<T1,T2> nodeSet){
        nodeSet.head.next.pre = currentCache;
        currentCache.next = nodeSet.head.next;
        currentCache.pre = nodeSet.head;
        nodeSet.head.next = currentCache;
    }

}
