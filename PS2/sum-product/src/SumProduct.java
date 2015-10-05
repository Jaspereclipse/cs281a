import java.util.ArrayList;


public class SumProduct {
	public void Collect(TreeNode currentNode){
	//post-order traversal
		if(currentNode.getFirstChild() != null){
			Collect(currentNode.getFirstChild());
		}
		
		if(currentNode.getParentEdge() != null){
			SendCollectedMsg(currentNode);
		}
		if(currentNode.getNextSib() != null){
			Collect(currentNode.getNextSib());
		}
	}
	
	public void Distribute(TreeNode currentNode){
	//first-order traversal
		if(currentNode.getParentEdge()!=null){
			//System.out.println(currentNode.getIndex());
			SendDistributedMsg(currentNode);
			//System.out.println(currentNode.getParentEdge().getDistributedMsg()[0]);
		}
		if(currentNode.getFirstChild() != null){
			Distribute(currentNode.getFirstChild());	
		}
		if(currentNode.getNextSib() != null){
			Distribute(currentNode.getNextSib());
		}
	}
	
	public void SendDistributedMsg(TreeNode node){
		double[] Msg = null;
		double[] singleton = node.getParentEdge().getParent().getPotential().clone();
		double[] sibMsg = null;
		ArrayList<TreeNode> sib = new ArrayList<TreeNode>();
		TreeNode nextSib = node.getParentEdge().getParent().getFirstChild(); //start from first child
		while(nextSib!=null && !nextSib.equals(node)){ //stop at the node before current node
			sib.add(nextSib);
			nextSib = nextSib.getNextSib();
		}
		nextSib = node.getNextSib();
		while(nextSib!=null){ //start from the node after current node till the last sibling
			sib.add(nextSib);
			nextSib = nextSib.getNextSib();
		}
		//System.out.println(sib.get(0).getIndex() + " " + node.getIndex());
		sibMsg = sib.get(0).getParentEdge().getCollectedMsg().clone();
		for (int i = 1; i < sib.size();i++){
			sibMsg = wiseMultiply(sibMsg,sib.get(i).getParentEdge().getCollectedMsg());
		}
		
		if(node.getEvidence() != null){ //is evidence node
			double[] pair = node.getParentEdge().getPotential().clone()[node.getEvidence()];
			Msg = wiseMultiply(sibMsg, wiseMultiply(singleton,pair));
		}
		else{
			double[][] pair = node.getParentEdge().getPotential().clone();
				Msg = wiseMultiply(sibMsg,wiseMultiply(singleton[0],pair[0]));
			for (int i = 1; i < singleton.length; i++){
				Msg = wiseAdd(Msg, wiseMultiply(sibMsg,wiseMultiply(singleton,pair[i])));
			}
		}
		node.getParentEdge().setDistributedMsg(Msg);
	}
	
	public void SendCollectedMsg(TreeNode node){
		if(node.getEvidence() != null){ //is evidence node
			//System.out.println(node.getIndex()+" "+node.getParentEdge().getPotential()[0][0]);//M[1,1]
			double singleton = node.getPotential().clone()[node.getEvidence()];
			//System.out.println(singleton);
			double[] pair = node.getParentEdge().getPotential().clone()[node.getEvidence()];
			//System.out.println(pair[0]);
			double[] Msg = pair.clone();
			Msg = wiseMultiply(singleton,Msg); //no need to collect other message
			node.getParentEdge().setCollectedMsg(Msg);
		}
		else{
			//System.out.println(node.getIndex()+" "+node.getParentEdge().getPotential()[0][0]);//M[1,1]
			double[] singleton = node.getPotential().clone();
			//System.out.println(singleton[0]);
			double[][] pair = node.getParentEdge().getPotential().clone();
			ArrayList<Edge> childEdge = node.getChildEdge();
			double[] Msg = wiseMultiply(0,singleton);
			for (int i = 0; i < singleton.length; i++){ //loop for each discrete value of the variable
				Msg = wiseAdd(Msg,wiseMultiply(msgProduct(childEdge,i), wiseMultiply(singleton[i],pair[i]))); //add up the potential
			}
			node.getParentEdge().setCollectedMsg(Msg);
		}
		/*if(node.getParentEdge()!=null){
			System.out.println(node.getIndex()+" "+node.getParentEdge().getPotential()[0][0]);
		}*/
	}
	
	public double[] ComputeMarginal(TreeNode node){
		double[] inMsg = null;
		ArrayList<Edge> childedgelist = node.getChildEdge();
		//singleton potential
		inMsg = node.getPotential().clone();	
		//look for parent edge
		if(node.getParentEdge()!=null){
			inMsg = wiseMultiply(inMsg,node.getParentEdge().getDistributedMsg());
		}
		//search for child edges		
		for (int i = 0; i < childedgelist.size(); i++){
			inMsg = wiseMultiply(inMsg,childedgelist.get(i).getCollectedMsg());	
		}
		//Normalization
		double factor = 0;
		for (int i = 0; i < inMsg.length; i++){
			factor = factor + inMsg[i];
		}
		
		inMsg = wiseDivide(inMsg,factor);
		
		return(inMsg);
	}
	
	public static double msgProduct(ArrayList<Edge> edgelist, int ind){
		double product = 1.0;
		for(int i = 0; i < edgelist.size();i++){
			product = product * edgelist.get(i).getCollectedMsg()[ind];
		}
		return(product);
	}
	
	public static double[] wiseAdd(double[] a, double[] b){
		double[] p = a.clone();
		double[] q = b.clone();
		for (int i=0; i < p.length;i++){
			p[i] = p[i]+q[i];
		}
		return(p);
	}
	
	public static double[] wiseMultiply(double a, double[] b){
		double[] p = b.clone();
		for (int i = 0; i < b.length;i++){
			p[i] = p[i]*a;
		}
		return(p);
	}
 	
	public static double[] wiseMultiply(double[] a, double[] b) {
		double[] p = a.clone();
		double[] q = b.clone();
		for (int i=0; i < a.length;i++){
			p[i] = p[i]*q[i];
		}
		return(p);
	}
	
	public static double[][] wiseMultiply(double[][] a, double[][] b) {
		double[][] p = a;
		double[][] q = b;
		for (int i = 0; i < p.length; i++){
			for (int j = 0; j < p.length; j++){
				p[i][j] = p[i][j]*q[i][j];
			}
		}
		return(p);
	}
	
	public static double[] wiseDivide(double[] a, double b) {
		double[] p = a.clone();
		for (int i=0; i < a.length;i++){
			p[i] = p[i]/b;
		}
		return(p);
	}
	
	static double[][] copyArray(double[][] array) {
		double[][] result = new double[array.length][];
		for(int i = 0; i < array.length; i++) {
			result[i] = new double[array[i].length];
		    for(int j = 0; j < array[i].length; j++) {
		      result[i][j] = array[i][j];
		    }
		}
		return result;
	}
	
	
	
}
