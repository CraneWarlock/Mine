import java.util.LinkedList;

public class Chain {

    private LinkedList<ChainLink> chainLinks;
    private int chainLength = 0;
    private String name;

    public Chain(String name) {
        this.name = name;
        this.chainLinks = new LinkedList<>();
    }

    public boolean craftLink(Resources resource1, Resources resource2, Resources resource3){
        if(resource1 == Resources.Iron && resource2 == Resources.Coal && resource3 == Resources.Coal){
            this.chainLinks.add(new ChainLink("Cast iron link"));
            this.chainLength = chainLinks.size();
            System.out.println("Cast iron link created and added to chain");
            return true;
        }else if(resource1 == Resources.Copper && resource2 == Resources.Copper && resource3 == Resources.Tin){
            this.chainLinks.add(new ChainLink("Bronze link"));
            this.chainLength = chainLinks.size();
            System.out.println("Bronze link created and added to chain");
            return true;
        }else{
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChainLength() {
        return chainLength;
    }

    private class ChainLink{

        private String linkName;
        public ChainLink(String linkName) {
            this.linkName = linkName;
        }

        public String getLinkName() {
            return linkName;
        }
    }
}
