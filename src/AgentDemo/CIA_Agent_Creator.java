package AgentDemo;

import PoolPattern.ObjectCreation_IF;

/**
 *
 * @author Dhruval
 */
public class CIA_Agent_Creator implements ObjectCreation_IF{
    private String[] names = {"a", "b", "c", "d", "e"};
    private int index;
    public Object create(){
        CIA_Agent agent = new CIA_Agent(names[index++]);
        return agent;
    }
}