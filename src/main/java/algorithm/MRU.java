package algorithm;

/**
 * Created by qu2k8o6 on 4/27/16.
 */
public class MRU implements Algorithms {
    @Override
    public int getIndexOfCacheNodeToBeRemoved(int setCapacity) {
        return setCapacity - 1; //remove the most recent used node which is the last node in the NodeSet
    }
}
