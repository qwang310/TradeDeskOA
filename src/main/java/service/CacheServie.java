package service;

import dao.CacheNode;
import dao.NodeSet;

import java.util.Map;

/**
 * Created by qu2k8o6 on 4/28/16.
 */
public class CacheServie<T1, T2> {

    public T2 getValue(T1 key, NodeSet<T1,T2> nodeSet){
        Map<T1, CacheNode<T1, T2>> nodeMap = nodeSet.nodeMap;
        synchronized (nodeMap) {
            if (!nodeMap.containsKey(key)) {
                return null;
            }
            CacheNode<T1, T2> currentCache = nodeMap.get(key);
            currentCache.pre.next = currentCache.next;
            currentCache.next.pre = currentCache.pre;

            moveToTail(currentCache, nodeSet);

            return nodeMap.get(key).value;
        }
    }

    public void setValue(T1 key, T2 value, NodeSet<T1,T2> nodeSet, int indexOfCacheNodeToBeRemovedb) {

        Map<T1, CacheNode<T1, T2>> nodeMap = nodeSet.nodeMap;
        synchronized (nodeMap) {
            if(getValue(key, nodeSet) != null){
                nodeMap.get(key).value = value;
                return;
            }

            if (nodeMap.size() == nodeSet.setCapacity) {
                removeCache(nodeSet, indexOfCacheNodeToBeRemovedb);
            }

            CacheNode<T1, T2> newCacheNode = new CacheNode<T1, T2>(key, value);
            moveToTail(newCacheNode, nodeSet);
            nodeMap.put(key, newCacheNode);
        }

    }

    private void removeCache(NodeSet<T1,T2> nodeSet, int counter){
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
