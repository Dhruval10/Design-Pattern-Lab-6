package AgentDemo;

import PoolPattern.ObjectCreation_IF;
/**
 *
 * @author Dhruval
 */
public class FBI_Agent_Creator implements ObjectCreation_IF {
    private String[] names = {"A", "B", "C", "D", "E"};
    private int index;
    public Object create(){
        FBI_Agent agent = new FBI_Agent(names[index++]);
        return agent;
    }
}