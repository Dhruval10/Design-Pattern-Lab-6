package AgentDemo;

import PoolPattern.ObjectPool;
import static java.lang.Thread.sleep;

/**
 *
 * @author Dhruval
 */
public class Task {
    private int id;
    public Task(int id) {
        this.id = id;
    }
    public void setTaskID(int id) {
        this.id = id;
    }
    public int getID() {
        return id;
    }
}