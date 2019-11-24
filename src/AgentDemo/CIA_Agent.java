package AgentDemo;
import static java.lang.Thread.sleep;

/**
 *
 * @author Dhruval
 */
public class CIA_Agent extends Object {
    private boolean working;
    int i;
    String name;
    private Task t;
    public CIA_Agent(String name) {
        this.name = name;
    }
    public void setTask(Task t) {
        this.t = t;
    }
    public Task getTask() {
        return t;
    }
    public void run() {
        try {
            sleep(100);
            System.out.println("This is agent " + name + ", work on task " + getTask().getID());
        } catch (InterruptedException ex) {}
    }
    public synchronized void start() {
        this.working = true;
    }
    public synchronized void stop() {
        this.working = false;
    }
}