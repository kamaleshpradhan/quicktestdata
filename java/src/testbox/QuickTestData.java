package testbox;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuickTestData {

	
	private static String CFG_FN="config.txt";
	private static int FETCH_SZ=1;
	public static void main(String[] args) throws SQLException{
		
		int in=0,tab_row=-1;
		String[] cfg;
		
		Assist ast=new Assist();
		CoreLogic cl=new CoreLogic();
		ProgressBar pb=new ProgressBar();
				  
		ast.banner();
		ast.menu();
		in=ast.readFromConsole();
		
		System.out.print("This may take several minutes. Attempting...");
		//System.out.println("");
		
		pb.start();
		
		/* Read connection string*/
		cfg=nullCheck(cl.readTextFile(CFG_FN, "constrng"),"Connection string not specified,check cfg...");
		
		cl.setDBConnectionParams(cfg[0], cfg[1], cfg[2], cfg[3], cfg[4], cfg[5]);
		cl.makeDBConnection();
		
		FETCH_SZ=Integer.parseInt(nullCheck(cl.readTextFile(CFG_FN, "fetch_sz")[0],"Fetch size not specified,check cfg..."));

		tab_row=fetchAndShowData(in, FETCH_SZ, cl, ast);

		if(tab_row==0){
			
			System.out.println("No matching records found...");
		}
		else if(tab_row==-1){
			
			//do nothing
		}
		else{
			
			System.out.println("");
			System.out.print("Successfully retrieved ");
			System.out.print(tab_row);
			System.out.println(" rows matching your criteria.");
		}
		
	}
	public static String nullCheck(String probe,String err_msg){
		
		if(probe==null){
			
			System.out.println("");
			System.out.println("Error:"+err_msg);
			System.exit(1);
			
		}
		
			return(probe);
	}
	public static String[] nullCheck(String[] probe,String err_msg){
		
		if(probe[0]==null){
			
			System.out.println("");
			System.out.println("Error:"+err_msg);
			System.exit(1);
			
		}
		
			return(probe);
	}
	 
	public static int fetchAndShowData(int opt,int f_sz,CoreLogic cl,Assist ast) throws SQLException{
		
		ResultSet rs;
		rs=cl.retrieveData(ast.getSQLQuery(opt, f_sz));
		ProgressBar.alive=false;
		return(cl.showData(rs));
	}

}

