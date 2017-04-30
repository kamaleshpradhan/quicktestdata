package testbox;

public class ProgressBar extends Thread{
	
	private Thread t=null;
	private String threadname="pb";
	public static boolean alive=true;
	public void run(){
		
		try {
			while(alive){
				System.out.print(".");
	            Thread.sleep(500);
	       }
	       System.out.println("\n");
	    }catch (InterruptedException e) {
	         System.out.println("Thread " +  threadname + " interrupted.");
	    }
	}
	public void start(){
		
		if (t == null) {
	         t = new Thread (this, threadname);
	         t.start ();
	      }
	}
	
}
