package PoolPattern;

/**
 *
 * @author Dhruval
 */
public class ObjectPool implements ObjectPool_IF {

    private static ObjectPool poolInstance;
    private final Object lockObject;
    private int size;
    private int maxInstances;
    private int instanceCount;
    private ObjectCreation_IF c;
    private Object[] pool;

    private ObjectPool(ObjectCreation_IF c, int capacity, int max) {
        this.c = c;
        this.size = 0;
        this.instanceCount = 0;
        this.maxInstances = max;
        this.lockObject = new Object();
        pool = new Object[capacity];
    }

    public synchronized static ObjectPool getPoolInstance(ObjectCreation_IF c, int capacity, int max) 
    {
        poolInstance = new ObjectPool(c, capacity, max);
        return poolInstance;
    }    
    @Override
    public int getSize() {
        return this.size;
    }    
    @Override
    public int getCapacity() {
        return pool.length;
    }    
    public int getInstanceCount() {
        return this.instanceCount;
    }
    public int getMaxInstances() {
        return maxInstances;
    }    
    @Override
    public void setCapacity(int c) {
        if (c <= 0) {
            String msg = "Capacity must be greater than zero";
            throw new IllegalArgumentException(msg);        
        }
        synchronized (lockObject) {
            Object[] newPool = new Object[c];
            System.arraycopy(pool, 0, newPool, 0, c);
            pool = newPool;
        }
    }
    @Override
    public Object getObject() {
        synchronized (lockObject) {
            if (size > 0) {
                return removeObject();
            } 
            else if (getInstanceCount() < getMaxInstances()) {
                return createObject();
            } 
            else {
                return null;
            }
        }
    }
    @Override
    public Object waitForObject() throws InterruptedException {
        synchronized (lockObject) {
            if (getInstanceCount() < getMaxInstances()) {
                return createObject();
            } 
            else if (size > 0) {
                return removeObject();
            }
            else {
                do {
                    wait();
                } 
                while (size <= 0);
                return removeObject();
            }
        }
    }
    private Object removeObject() {
        size--;
        return pool[size];
    }
    @Override
    public void release(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        synchronized (lockObject) {
            if (getSize() < getCapacity()) {
                pool[size] = o;
                size++;
                lockObject.notify();
            }
        }
    }
    private Object createObject() {
        Object newObject = c.create();
        instanceCount++;
        return newObject;
    }
}