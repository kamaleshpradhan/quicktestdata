package testbox;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class CoreLogic {
	
	
	private String un,pwd,host,port,srvc_name;
	private Connection conn;
	
	public void setDBConnectionParams(String _un,String _pwd,String _con_name,
			String _host,String _port,String _srvc_name){
		
		un=_un;
		pwd=_pwd;
		//con_name=_con_name;
		host=_host;
		port=_port;
		srvc_name=_srvc_name;
		
		
	}
	public void makeDBConnection(){
		
		String conn_str;
		String con_typ;
		
		/* Register driver class*/
		try {
			   Class.forName("oracle.jdbc.driver.OracleDriver");
			}
			catch(ClassNotFoundException ex) {
				ProgressBar.alive=false;
			   System.out.println("Error: unable to load driver class!");
			   ex.printStackTrace();
			   System.exit(1);
			}
			
		/*database URL formation - for Oracle DB*/
		con_typ=QuickTestData.nullCheck(readTextFile("config.txt", "contyp")[0], "Connection type not specified,check cfg...");
		if(con_typ.compareToIgnoreCase("service")==0){
			
			conn_str="jdbc:oracle:thin:@//"+host+":"+port+"/"+srvc_name;
		}
		else{
			
			conn_str="jdbc:oracle:thin:@"+host+":"+port+":"+srvc_name;
		}
			
			
		/*Create DB connection object*/
			
			try {
				
				conn=DriverManager.getConnection(conn_str, un, pwd);
				
			} catch (SQLException e) {
				
				ProgressBar.alive=false;
				System.out.println("Could not establish DB connection, please check your connection string...");
				System.out.println("Possible cause: "+e.getMessage());
				System.exit(1);
				
			}
	}
	
	public ResultSet retrieveData(String q) throws SQLException{
		
		ResultSet rs= null;
		PreparedStatement pstmt = null;
		
		try{
			
			pstmt=conn.prepareStatement(q);
			rs=pstmt.executeQuery();
			
			
		}catch(SQLException e){
			
			ProgressBar.alive=false;
			System.out.println("");
			System.out.println("Problem during DB query, please try again...");
			System.out.println("Possible cause: "+e.getMessage());
			System.exit(1);
		}
		finally{
			
			//conn.close();
			//pstmt.close();
			
		}
		return (rs);
		
		
		
	}
	public int showData(ResultSet rs) throws SQLException{
		
		int data_cnt=0,m=0;
		try {
			
			
			
			ResultSetMetaData rmd;
			rmd=rs.getMetaData();
			for(int j=1;j<=rmd.getColumnCount();j++){
				
				System.out.printf("%-17s",rmd.getColumnLabel(j));
				
			}
			System.out.println("");
			for(int k=1;k<=rmd.getColumnCount();k++){
				
				for(m=0;m<rmd.getColumnLabel(k).length();m++){
					
					System.out.print("=");
				}
				m=17-m;
				System.out.printf("%-"+m+"s","");
			}
			System.out.println("");
			while(rs.next()){
				
				data_cnt++;
				for(int i=1;i<=rmd.getColumnCount();i++){
					
					
					System.out.printf("%-17s",rs.getString(i));
					
				}
				System.out.println("");
			}
			
		} catch (SQLException e) {
			
			ProgressBar.alive=false;
			System.out.println("Problem during retrieving data from DB...");
			System.out.println("Possible cause: "+e.getMessage());
			System.exit(1);
		}
		finally{
			rs.close();
			conn.close();
		}
		return(data_cnt);
	}
	public String[] readTextFile(String fn,String key) {
		
		String line;
		String[] t=null;;
		try{
			BufferedReader br=new BufferedReader(new FileReader(fn));
			if(br.ready()){
			
				while((line=br.readLine())!=null){
				
					if(line.charAt(0)=='#'){
						continue;
					}
					else {
					
						line.concat("\n");
						t=line.split("=");
						if(t[0].compareToIgnoreCase(key)!=0||t==null){
							
							t[0]=null;
							continue;
						}
						else{
							
							t=t[1].split(":");
							break;
						}
						
					}
				}
			}
			else{
				System.out.println("Error reading config file...");
			}
			br.close();
		}catch(FileNotFoundException e){
			ProgressBar.alive=false;
			System.out.println("Config file not found...");
			System.out.println("Possible cause: "+e.getMessage());
			System.exit(1);
		}catch(IOException e){
			ProgressBar.alive=false;
			System.out.println("Error ocurred...");
			System.out.println("Possible cause: "+e.getMessage());
			System.exit(1);
		}catch(ArrayIndexOutOfBoundsException e){
			ProgressBar.alive=false;
			System.out.println("Error ocurred...");
			System.out.println("Possible cause: "+e.getMessage()+":Malformed config parameters");
			System.exit(1);
		}
		return(t);
		
	}

	
	
	

}
