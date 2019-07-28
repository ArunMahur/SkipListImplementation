import java.util.*;
public class SkipListSearch<E>{

	    static private Random value = new Random(); 
	    
	    static int random(int n) {
	        return Math.abs(value.nextInt()) % n;
	    }
	    
	    private class SkipNode<E> {
	        private int key;
	        private E element;
	        private SkipNode<E>[] forward;
	       
	        public SkipNode( int key, E element, int levels ) {
	            this.key = key;
	            this.element = element;
	            this.forward = (SkipNode<E>[]) new SkipNode[levels+1];
	            for (int i = 0; i <= levels; i++)
	                this.forward[i] = null;
	        }
	        
	  
	        public SkipNode( int levels ) {
	            this.key = 0;
	            this.element = null;
	            this.forward = new SkipNode[levels+1];
	            for (int i = 0; i <= levels; i++)
	                this.forward[i] = null;
	        }
	        
	      
	        public void adjustLevel( int newLevel ) {
	            SkipNode[] oldf = this.forward;
	            this.forward = new SkipNode[newLevel+1];
	            for (int i = 0; i < oldf.length; i++)
	                this.forward[i] = oldf[i];
	            for (int i = oldf.length; i <= newLevel; i++)
	                this.forward[i] = null; 
	        }

	   
	        public void printForward( ) {
	            for (int i = this.forward.length-1; i >= 0; i--) {
	                if (this.forward[i] == null)
	                    System.out.println( "level : " + i + " ----> null" );
	                else
	                    System.out.println( "level : " + i + " ----> (" + this.forward[i].key() + " : " + this.forward[i].element() + ")");
	            }
	        }

	     
	        public int key() { return this.key; }
	        public E element() { return this.element; }
	    }
	    
	 
	    private SkipNode head; 
	    private int level;  
	    private int size; 
	    
	   
	    public SkipListSearch() {
	      
	        this.level = 1;
	
	        this.head = new SkipNode( this.level );
	        this.size = 0;
	    }
	   
	    
	
	    int randomLevel() {
	        //return 1;
	      int lev;
	      for (lev=0; random(2) == 0; lev++); 
	      return lev;
	    }
	    

	    public void adjustHead(int newLevel) {  
	        this.head.adjustLevel( newLevel );
	    }
	    
	
	    public void insert(int k, E newValue) {
	      int newLevel = randomLevel();  
	      if (newLevel > level)         
	        adjustHead(newLevel);       
	      this.level = newLevel;        
	      // Track end of level
	      SkipNode<E>[] update = (SkipNode<E>[]) new SkipNode[level+1];
	      SkipNode<E> x = this.head;       
	      for (int i=level; i>=0; i--) { 
	        while((x.forward[i] != null) &&
	              (k > x.forward[i].key()))
	          x = x.forward[i];
	        update[i] = x;            
	      }
	      x = new SkipNode<E >(k, newValue, newLevel);
	      for (int i=0; i <= newLevel; i++) {      
	        x.forward[i] = update[i].forward[i];
	        update[i].forward[i] = x;           
	      }
	      this.size++;                    
	    }

	  
	    public E find(int searchKey) {
	      SkipNode<E> x = this.head;          
	      for (int i=level; i>=0; i--)      
	        while ((x.forward[i] != null) && (searchKey > x.forward[i].key()))
	          x = x.forward[i];             
	      x = x.forward[0];  
	      if ((x != null) && (searchKey == x.key()))
	        return x.element();             
	      else return null;               
	    }
	        
	    public void printContents() {
	        SkipNode ptr = this.head;
	        while (true) {
	            if (ptr.forward[0] == null)
	                break;
	            ptr = ptr.forward[0];
	            System.out.println( ptr.key() + " : " + ptr.element() );
	        }
	    }
	    
	    public void printEverything() {
	        SkipNode ptr = this.head;
	        System.out.println( "Head Node " );
	        ptr.printForward();
	        ptr = ptr.forward[0];
	        while (ptr != null) {
	            System.out.println( "Node (" + ptr.key() + " : " + ptr.element() + ")" );
	            ptr.printForward();
	            ptr = ptr.forward[0];
	        }
	    }
	    public static void main( String[] args ) {
	        SkipListSearch<String> sl = new SkipListSearch<String>();
	        sl.insert( 1, "One" );
	        System.out.println( "\nPrinting the list ");
	        //System.out.println( "Printing the header array: ");
	        //sl.head.printForward();
	        sl.printEverything();
	        sl.insert( 4, "Four" );
	        sl.insert( 10, "Ten" );
	        sl.insert( 3, "Three" );
	        sl.insert( 11, "Eleven" );
	        System.out.println( "\nPrinting the list ");
	        sl.printEverything();
	        
	        System.out.println( "\nPrinting just the traversal" );
	        sl.printContents();
	    }
}