package dao;

/**
 * Created by qu2k8o6 on 4/20/16.
 */
public interface SetAssociativeCache<T1, T2> {

    public void saveToCache(T1 key, T2 value);
    public T2 getFromCache(T1 key);
}
