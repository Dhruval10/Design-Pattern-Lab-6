package AgentDemo;

import PoolPattern.ObjectPool;
import java.util.ArrayList;

/**
 *
 * @author Dhruval
 */

public class FBIAgentApp {
    public static void main(String arg[]) throws InterruptedException {
        FBI_Agent_Creator creator = new FBI_Agent_Creator();
        ObjectPool CIA = ObjectPool.getPoolInstance(creator, 5, 5);
        ArrayList<Task> list = new ArrayList();
        for (int i = 0; i < 10; i++) {
        Task t = new Task(i);
        list.add(t);
    }
    System.out.println("Testing FBI Agent pool");
    int index = 0;
    Task t = list.get(index);
    while (t != null && index < list.size()) {
        try {
            FBI_Agent agent = (FBI_Agent) CIA.waitForObject();
            t = list.get(index);
            index++;
            if (t != null) {
                agent.setTask(t);
                agent.run();
                Thread t1 = new Thread(() -> {
                try {
                    Thread.sleep(400);
                    CIA.release(agent);
                } catch (InterruptedException ex) {}
            });
            t1.start();
            }
            } catch (InterruptedException ex) {}
        }
    }
}