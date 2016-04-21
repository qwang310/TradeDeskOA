package algorithm;

import dao.NodeSet;

/**
 * Created by qu2k8o6 on 4/20/16.
 */
public interface Algorithms<T1, T2> {

    public T2 getValue(T1 key, NodeSet<T1, T2> nodeSet);
    public void setValue(T1 key, T2 value, NodeSet<T1, T2> nodeSet);
}
