

public class Edge {
	private TreeNode parent=null;
	private TreeNode child=null;
	private double[][] potential=null;
	private double[] collectedMsg=null;
	private double[] distributedMsg=null;
	
	public TreeNode getParent(){
		return(this.parent);
	}
	
	public TreeNode getChild(){
		return(this.child);
	}
	
	public Edge(TreeNode parent, TreeNode child, double[][] potential){
		this.parent = parent;
		this.child = child;
		this.potential = potential;
	}
	
	public void setCollectedMsg(double[] Msg){
		this.collectedMsg = Msg;
	}
	
	public void setDistributedMsg(double[] Msg){
		this.distributedMsg = Msg;
	}
	
	public double[] getCollectedMsg(){
		return(this.collectedMsg);
	}
	
	public double[] getDistributedMsg(){
		return(this.distributedMsg);
	}
	
	public double[][] getPotential(){
		return(this.potential);
	}
	
	
}
