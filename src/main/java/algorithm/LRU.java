package algorithm;

/**
 * Created by qu2k8o6 on 4/27/16.
 */
public class LRU implements Algorithms {
    @Override
    public int getIndexOfCacheNodeToBeRemoved(int setCapacity) {
        return 0; //remove the least recent used node which is the first node in the NodeSet
    }
}
