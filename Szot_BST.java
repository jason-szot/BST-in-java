/*
*
* 1. put(key, value) 	=> Here, key is the DOB and value is the name. It will add the pair in the BST
* 2. get(key) 			=> Given a DOB, it will give the name.
* 3. min() 				=> It will give the min DOB and the name of the person. That is it will give DOB, name of the oldest person in the BST
* 4. max()				=> It will give the max DOB and the name of the person. That is, it will give DOB, name of the youngest person in the BST
* 5. floor(key)			=> It will give the floor DOB and the person's name. That is, given a DOB as key, it will print the largest DOB (and name) which is less than or equal to the given DOB.
* 6. ceiling(key)		=> It will give the ceiling DOB and the person's name. That is, given a DOB as key, it will print the smallest DOB (and name) which is greater than or equal to the given DOB.
* 7. rank(key)			=> It will give how many persons have earlier DOB that the given DOB.
* 8. iterator()			=> Prints all the key-values in ascending order, one in each line
*
*/
// parts of code grabbed from lecture slides in class

public class Szot_BST{
	private Node root;
	public static void main(String args[]){
		try{
			File file = new File(args[0]);
			FileReader freader = new FileReader(file);
			BufferedReader reader = new BufferedReader(freader);

			String str = reader.readLine();		// gets the number from first line of file

			while((str = reader.readLine()) != "*"){
				String split = str.split("," , -1);
				DOB key = new DOB(split[0].trim());
				String val = split[1].trim();
				put(key,val,false);

			}
				// will be at * after this

			while ((str = reader.readLine())!= null){	// dealing with commands now
				String split = str.split(", ", -1);
				int sel = Integer.parseInt(split[0].trim());
				switch(sel){
					case 1:	// put
					{
						DOB key = new DOB(split[1].trim());
						String val = split[2].trim();
						put(key,val,true);
						break;
					}
					case 2:	// get
					{
						DOB key = new DOB(split[1].trim());
						get(key);
						break;
					}
					case 3:	// min
					{
						min();
						break;
					}
					case 4:	// max
					{
						max();
						break;
					}
					case 5:	// floor
					{
						DOB key = new DOB(split[1].trim());
						floor(key);
						break;
					}
					case 6:	// ceiling
					{
						DOB key = new DOB(split[1].trim());
						ceiling(key);
						break;
					}
					case 7:	// rank
					{
						DOB key = new DOB(split[1].trim());
						rank(key);
						break;
					}
					case 8:	// In Order Transversal
					{
						iot();
						break;
					}
				}

			}	// end of while
						
			
				
					
			reader.close();			 
			System.exit(0);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void put(DOB key, String val, Boolean command){
		root = put(root, key, val, command);
	}

	private Node put( Node x, DOB key, String value, Boolean command){
		if (x == null)
		{
			if (command) System.out.println("Done");
			return new Node(key,val);
		}
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
		{
			x.rank++;
			x.left = put(x.left, key, val, command);

		}
		else if (cmp > 0)
			x.right = put(x.right, key, val, command);
		else if (cmp == 0)
			x.val = val;
		return x;

	}	// end of put

	void get(DOB key){	// get key-value pair and display
		get(root,key);
	}

	void get(Node x, DOB key){
		if (node == null){
			System.out.printLn("Not Found");
			return;
		}
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
		{
			System.out.printLn(x.name);
			return;
		}
		else if (cmp < 0){
			get(x.left,key);
		}
		else if(cmp > 0){
			get(x.right,key);
		}
	}	// end of get

	void min(){
		Node min = min(root);
		System.out.printLn(min.toString());
	}

	Node min(Node x)
	{
		if (x == null) return null;
		Node min = min(x.left);
		if (min == null) return x;
	}	// end of min

	void max(){
		Node max = max(root);
		System.out.printLn(max.toString());
	}

	Node max(Node x){
		if (x == null) return null;
		Node max = max(x.right);
		if (max == null) return x;
	}	// end of max

	void floor(key){
		Node floor = floor(root,key);
		System.out.println(floor.toString());
	}

	Node floor(Node x, DOB key){
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if (cmp < 0) return floor(x.left,key);

		Node tmp = floor(x.right, key);
		if (tmp != null) return tmp;
		else return x;
	}

	void ceiling(DOB key){
		Node c = ceiling(root, key);
		System.out.printLn(c.toString());
	}

	Node ceiling(Node x, DOB key){
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if (cmp > 0) return ceiling(x.right,key);

		Node temp = ceiling(x.left, key);
		if (tmp != null) return tmp;
		else return x;
	}	// end of ceiling

	void rank(DOB key){	// get key-value pair and display
		rank(root,key);
	}

	void rank(Node x, DOB key){
		if (node == null){
			System.out.printLn("Not Found");
			return;
		}
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
		{
			System.out.printLn(x.rank);
			return;
		}
		else if (cmp < 0){
			rank(x.left,key);
		}
		else if(cmp > 0){
			rank(x.right,key);
		}
	}	// end of get

	void iot(){	// in order traversal
		iot(root);

	}

	void iot(Node x){
		if (x == null) return;
		iot(x.left);
		System.out.printLn(x.toString());
		iot(x.right);
	}

	//  **********************nested Node class*******************************
class Node{
	DOB key;
	String name;
	Node left;
	Node right;
	int rank;

	Node(){
		key = new DOB();
		name = "";
		left = null;
		right = null;
		rank = 0;
	}

	public Node(String input){
		String str[] = input.split(",",-1);	// DOB, name
		name = str[1].trim();
		key = new DOB(str[0].trim());
		left = null;
		right = null;
		rank = 0;
	}

	public Node(DOB key, String value){
		name = value;
		key = key;
		left = null;
		right = null;
		rank = 0;
	}

	public String toString(){
		String str = key.dob + ", " + name;
		return str;
	}
}	// end of node class

	//  **********************nested DOB class*******************************

class DOB implements Comparable<DOB>{
	public String dob;
    public int day,month,year;

    DOB(){
        day = 0;
        month = 0;
        year = 0;
        dob = "";
    }

    DOB(String str){
        // str has dob in mm/dd/yyyy format
        String[] split = str.split("/", -1);
        month = Integer.parseInt(split[0].trim());
        day = Integer.parseInt(split[1].trim());
        year = Integer.parseInt(split[2].trim());
        dob = str;
    }

    public int compareTo(DOB that){
        if (year < that.year)
            return -1;
        else if (year > that.year)
            return 1;
        else if (year == that.year)
        {
            if (month < that.month)
                return -1;
            else if (month > that.month)
                return 1;
            else if (month == that.month)
            {
                return this.day.compareTo(that.day);
            }	// end of day
        }// end of month
    }
}   // end of DOB class


}	// end of main class