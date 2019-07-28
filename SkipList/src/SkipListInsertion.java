
import java.util.Scanner;
 
class SkipNode        
{
    int element;
    SkipNode right;
    SkipNode down;
 
    
    public SkipNode(int x)
    {
        this(x, null, null);
    }
   
    public SkipNode(int x, SkipNode rt, SkipNode dt)
    {
        element = x;
        right = rt;
        down = dt;
    }
}
 

class SkipList 
{
    private SkipNode header;
    private int infinity;
    private SkipNode bottom = null;
    private SkipNode tail = null;
 
    
    public SkipList(int inf)
    {
        infinity = inf;
        bottom = new SkipNode(0);
        bottom.right = bottom.down = bottom;
        tail = new SkipNode(infinity);
        tail.right = tail;
        header = new SkipNode(infinity, tail, bottom);
    }
   
    public int insert(int x)
    {
        SkipNode current = header;
        bottom.element = x;
        while (current != bottom)
        {
            while (current.element < x)
            current = current.right;
            
            if (current.down.right.right.element < current.element)
            {
                current.right = new SkipNode(current.element, current.right, current.down.right.right);
                current.element = current.down.right.element;
            }
            else
                current = current.down;
        }
        if (header.right != tail)
            header = new SkipNode(infinity, tail, header);
        return(x);
    }
  
    public void makeEmpty()
    {
        header.right = tail;
        header.down = bottom;
    }
  
    public boolean isEmpty()
    {
        return header.right == tail && header.down == bottom;
    }
    
    private int elementAt(SkipNode t)
    {
        return t == bottom ? 0 : t.element;
    }
 
    public void printList()
    {
        System.out.print("\nSkiplist = ");
        SkipNode current = header;
        while( current.down != bottom )
            current = current.down;
        while (current.right != tail )
        {
            System.out.print(current.element +" ");
            current = current.right;
        }
        System.out.println();
    }   
}
public class SkipListInsertion {

	public static void main(String[] args) {
		
		 Scanner scan = new Scanner(System.in);
	       
	        SkipList sl = new SkipList(100000000); 
	        System.out.println("SkipList Inertion and complete list Removal\n");          
	        char ch;
	       
	        do
	        {
	            System.out.println("\nSkipList Operations\n");
	            System.out.println("1. To insert the element");
	            System.out.println("2. To check whether the skiplist is empty or not");
	            System.out.println("3. To clear the complete list");
	 
	            int choice = scan.nextInt();            
	            switch (choice)
	            {
	            case 1 : 
	                System.out.println("Enter integer element to insert in SkipList");
	                sl.insert( scan.nextInt() );   
	                break;                          
	            case 2 : 
	                System.out.println("Empty status (true=Empty and false=Not Empty) = "+ sl.isEmpty());
	                break;            
	            case 3 : 
	                System.out.println("List is cleared\n");
	                sl.makeEmpty();
	                break;                         
	            default : 
	                System.out.println("Wrong Entry \n ");
	                break;   
	            }    
	            /*  Display List  */ 
	            sl.printList();
	            System.out.println("\nDo you want to continue (Type y or n) \n");
	            ch = scan.next().charAt(0);    
	 
	        } while (ch == 'Y'|| ch == 'y');  
	}

}
