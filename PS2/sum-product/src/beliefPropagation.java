import java.util.ArrayList;

public class beliefPropagation {

	public static void main(String[] args) {
		// Initialize a tree
		
		//i)
		double m1 = 8;
		double m2 = 3;
		double m3 = 2;
		double m4 = 1;
		
		//ii)
		/*double m1 = 7;
		double m2 = 4;
		double m3 = 3;
		double m4 = 2;
		*/
		double[] singletonPotential = {1,1,1,1};
		double[] singletonPotential_9 = {8,9,9,8};
		double[][] edgePotential = {{m1,m4,m2,m3},
									{m4,m1,m3,m2},
									{m2,m3,m1,m4},
									{m3,m2,m4,m1}};
		double[][] edgePotential_59 = {{  m1,3*m4,4*m2,3*m3},
									   {3*m4,  m1,3*m3,4*m2},
									   {4*m2,3*m3,  m1,3*m4},
									   {3*m3,4*m2,3*m4,  m1}};
		
		//Create all the nodes with singletonPotential and index
		ArrayList<TreeNode> nodelist = new ArrayList<TreeNode>();
		TreeNode nine = new TreeNode(9,singletonPotential_9);
		TreeNode eight = new TreeNode(8,singletonPotential);
		TreeNode seven = new TreeNode(7,singletonPotential);
		TreeNode six = new TreeNode(6,singletonPotential);
		TreeNode five = new TreeNode(5,singletonPotential);
		TreeNode four = new TreeNode(4,singletonPotential);
		TreeNode three = new TreeNode(3,singletonPotential); 
		TreeNode two = new TreeNode(2,singletonPotential); 
		TreeNode one = new TreeNode(1,singletonPotential); 
		
		//Add node to list
		nodelist.add(nine);
		nodelist.add(eight);
		nodelist.add(seven);
		nodelist.add(six);
		nodelist.add(five);
		nodelist.add(four);
		nodelist.add(three);
		nodelist.add(two);
		nodelist.add(one);
		
		//Create all the edges with potential, parent and child node
		ArrayList<Edge> edgelist = new ArrayList<Edge>();
		Edge nineEight = new Edge(nine, eight, edgePotential);
		Edge nineFive = new Edge(nine,five,edgePotential_59);
		Edge eightSix = new Edge(eight,six,edgePotential);
		Edge eightSeven = new Edge(eight,seven,edgePotential);
		Edge sixOne = new Edge(six,one,edgePotential);
		Edge sixTwo = new Edge(six,two,edgePotential);
		Edge sevenThree = new Edge(seven,three,edgePotential);
		Edge sevenFour = new Edge(seven,four,edgePotential);
		
		//Add edges to list
		edgelist.add(nineEight);
		edgelist.add(nineFive);
		edgelist.add(eightSix);
		edgelist.add(eightSeven);
		edgelist.add(sixOne);
		edgelist.add(sixTwo);
		edgelist.add(sevenThree);
		edgelist.add(sevenFour);
		
		
		//Add Child Edge
		nine.addChildEdge(nineEight);
		nine.addChildEdge(nineFive);
		eight.addChildEdge(eightSeven);
		eight.addChildEdge(eightSix);
		seven.addChildEdge(sevenFour);
		seven.addChildEdge(sevenThree);
		six.addChildEdge(sixTwo);
		six.addChildEdge(sixOne);
		
		//Add Parent Edge
		one.addParentEdge(sixOne);
		two.addParentEdge(sixTwo);
		three.addParentEdge(sevenThree);
		four.addParentEdge(sevenFour);
		five.addParentEdge(nineFive);
		six.addParentEdge(eightSix);
		seven.addParentEdge(eightSeven);
		eight.addParentEdge(nineEight);
		
		//Add First Child
		nine.addFirstChild(eight);
		eight.addFirstChild(six);
		seven.addFirstChild(three);
		six.addFirstChild(one);
		
		//Add Next Sibling
		eight.addNextSibling(five);
		six.addNextSibling(seven);
		one.addNextSibling(two);
		three.addNextSibling(four);
		
		//Change Evidence Node for example i), ACTG=0123	
		one.setEvidence(3);
		two.setEvidence(1);
		three.setEvidence(0);
		four.setEvidence(2);
		five.setEvidence(2);
		
		//Change Evidence Node for example ii)
		/*one.setEvidence(1);
		two.setEvidence(0);
		three.setEvidence(3);
		four.setEvidence(2);
		five.setEvidence(1);*/
		
		SumProduct sp = new SumProduct();
		sp.Collect(nine);
		sp.Distribute(nine);
		
		double[] out = sp.ComputeMarginal(nine);
		//Output Test
		// i)
		System.out.println("Given the evidence of x1,x2,x3,x4,x5 as (G,C,A,T,T),\nthe marginal probability of root node 9 is:");
		// ii)
		//System.out.println("Given the evidence of x1,x2,x3,x4,x5 as (C,A,G,T,C),\nthe marginal probability of root node 9 is:");
		for (int i = 0; i < out.length; i++){
			System.out.println(String.format("%.04f", out[i]));
		}
	}
}
