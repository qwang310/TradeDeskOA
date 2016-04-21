package dao;

/**
 * Created by qu2k8o6 on 4/19/16.
 */
public class CacheNode<T1, T2> {
    public T1 key;
    public T2 value;
    public CacheNode pre;
    public CacheNode next;
    public CacheNode(T1 key, T2 value){
        this.key = key;
        this.value = value;
        this.pre = null;
        this.next = null;
    }
    public CacheNode(){
        this.key = null;
        this.value = null;
        this.pre = null;
        this.next = null;
    }

}