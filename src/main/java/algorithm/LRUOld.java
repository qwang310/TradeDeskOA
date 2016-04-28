package algorithm;

import dao.CacheNode;
import dao.NodeSet;

import java.util.Map;

/**
 * Created by qu2k8o6 on 4/20/16.
 */
public class LRUOld<T1, T2>{


    public T2 getValue(T1 key, NodeSet<T1,T2> nodeSet){
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
            CacheNode<T1, T2> nodeToRemove = nodeSet.head.next;
            T1 first = nodeToRemove.key;
            nodeMap.remove(first);
            nodeSet.head.next = nodeSet.head.next.next;
            nodeSet.head.next.pre = nodeSet.head;
        }

        CacheNode<T1, T2> newCacheNode = new CacheNode<T1, T2>(key, value);
        moveToTail(newCacheNode, nodeSet);
        nodeMap.put(key, newCacheNode);

    }


    private void moveToTail(CacheNode<T1, T2> currentCache, NodeSet<T1,T2> nodeSet){
        nodeSet.tail.pre.next = currentCache;
        currentCache.pre = nodeSet.tail.pre;
        currentCache.next = nodeSet.tail;
        nodeSet.tail.pre = currentCache;
    }

}
