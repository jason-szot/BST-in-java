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
}