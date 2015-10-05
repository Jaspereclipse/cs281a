import java.util.ArrayList;
import java.lang.Integer;

/* Properties:
 * 1. parent
 * 2. list of children
 * 3. singleton potential
 */
public class TreeNode {
	private ArrayList<Edge> childrenEdgeList;
	private Edge parentEdge=null;
	private TreeNode firstChild=null;
	private TreeNode nextSibling=null;
	private double[] potential=null;
	private int index=0;
	private Integer evidence=null; 
	
	public TreeNode(int index, double[] singletonPotential){
		this.index = index;
		this.potential = singletonPotential;
		this.childrenEdgeList = new ArrayList<Edge>();
	}
	
	public void addChildEdge(Edge child){
		this.childrenEdgeList.add(child);
	}
	
	public ArrayList<Edge> getChildEdge(){
		return(this.childrenEdgeList);
	}
	
	public void addParentEdge(Edge parent){
		this.parentEdge = parent;
	}
	
	public void addFirstChild(TreeNode firstchild){
		this.firstChild = firstchild;
	}
	
	public void addNextSibling(TreeNode nextSib){
		this.nextSibling = nextSib;
	}
	
	public TreeNode getFirstChild(){
		return(this.firstChild);
	}
	
	public TreeNode getNextSib(){
		return(this.nextSibling);
	}
	
	public double[] getPotential(){
		return(this.potential);
	}
	
	public Integer getEvidence(){
		return(this.evidence);
	}
	
	public void setEvidence(Integer e){
		this.evidence = e;
	}
	
	public Edge getParentEdge(){
		return(this.parentEdge);
	}
	
	public int getIndex(){
		return(this.index);
	}
}
