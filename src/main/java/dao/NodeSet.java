package dao;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qu2k8o6 on 4/19/16.
 */
public class NodeSet<T1, T2> {
    public int setIndex;
    public Map<T1, CacheNode<T1, T2>> nodeMap;
    public int setCapacity;
    public CacheNode<T1, T2> head = new CacheNode<T1, T2>();
    public CacheNode<T1, T2> tail = new CacheNode<T1, T2>();
    public NodeSet(int setIndex, int setCapacity){
        this.setIndex = setIndex;
        this.setCapacity = setCapacity;
        this.nodeMap = Collections.synchronizedMap(new HashMap<T1, CacheNode<T1, T2>>());
        this.head.next = tail;
        this.tail.pre = head;
    }
}
