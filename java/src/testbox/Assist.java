package testbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Assist {

	
	public String getSQLQuery(int opt,int row){
		
		String q=null;
		CoreLogic cl=new CoreLogic();
	switch(opt){
	
		case 1:
			q="select mem_num,fst_name,last_name,TIER,status_cd,MILES,EMAIL from"+
				"(select"+
				" MEM.MEM_NUM,CON.FST_NAME,CON.LAST_NAME,TIER.NAME AS TIER,MEM.STATUS_CD,MEM.POINT_TYPE_A_VAL AS MILES,COMM.ADDR AS EMAIL"+
				" from"+
				" SIEBEL.S_CONTACT CON,"+
				" SIEBEL.S_LOY_MEMBER MEM,"+
				" SIEBEL.S_LOY_TIER TIER,"+
				" SIEBEL.S_PER_COMM_ADDR COMM,"+
				" SIEBEL.S_LOY_MEMBER_X MEM_X"+
				" WHERE"+
				" CON.ROW_ID=MEM.PR_CON_ID AND"+
				" MEM.PR_DOM_TIER_ID=TIER.ROW_ID AND"+
				" MEM.PR_CON_ID=COMM.PER_ID AND"+
				" MEM.ROW_ID=MEM_X.PAR_ROW_ID AND"+
				" MEM.X_MEM_LOCK_FLG='N' AND"+
				" MEM.STATUS_CD='Active' AND"+
				" COMM.X_VALID_FLG='Y' AND"+
				" COMM.COMM_MEDIUM_CD='Email' and"+
				" COMM.COMM_MEDIUM_CD IS NOT NULL AND"+
				" TIER.NAME='Red' AND"+
				" to_date(to_char(START_DT,'DD-MON-YYYY'),'DD-MON-YYYY')>'31-DEC-2014'"+
				" ORDER BY DBMS_RANDOM.RANDOM)"+
				" WHERE ROWNUM<="+row;
			break;
		case 2:
			q="select mem_num,fst_name,last_name,TIER,status_cd,MILES,EMAIL from"+
			"(select"+
			" MEM.MEM_NUM,CON.FST_NAME,CON.LAST_NAME,TIER.NAME AS TIER,MEM.STATUS_CD,MEM.POINT_TYPE_A_VAL AS MILES,COMM.ADDR AS EMAIL"+
			" from"+
			" SIEBEL.S_CONTACT CON,"+
			" SIEBEL.S_LOY_MEMBER MEM,"+
			" SIEBEL.S_LOY_TIER TIER,"+
			" SIEBEL.S_PER_COMM_ADDR COMM,"+
			" SIEBEL.S_LOY_MEMBER_X MEM_X"+
			" WHERE"+
			" CON.ROW_ID=MEM.PR_CON_ID AND"+
			" MEM.PR_DOM_TIER_ID=TIER.ROW_ID AND"+
			" MEM.PR_CON_ID=COMM.PER_ID AND"+
			" MEM.ROW_ID=MEM_X.PAR_ROW_ID AND"+
			" MEM.X_MEM_LOCK_FLG='N' AND"+
			" MEM.STATUS_CD='Active' AND"+
			" COMM.X_VALID_FLG='Y' AND"+
			" COMM.COMM_MEDIUM_CD='Email' and"+
			" COMM.COMM_MEDIUM_CD IS NOT NULL AND"+
			" TIER.NAME='Silver' AND"+
			" to_date(to_char(START_DT,'DD-MON-YYYY'),'DD-MON-YYYY')>'31-DEC-2014'"+
			" ORDER BY DBMS_RANDOM.RANDOM)"+
			" WHERE ROWNUM<="+row;
		break;
		case 3:
			q="select mem_num,fst_name,last_name,TIER,status_cd,MILES,EMAIL from"+
			"(select"+
			" MEM.MEM_NUM,CON.FST_NAME,CON.LAST_NAME,TIER.NAME AS TIER,MEM.STATUS_CD,MEM.POINT_TYPE_A_VAL AS MILES,COMM.ADDR AS EMAIL"+
			" from"+
			" SIEBEL.S_CONTACT CON,"+
			" SIEBEL.S_LOY_MEMBER MEM,"+
			" SIEBEL.S_LOY_TIER TIER,"+
			" SIEBEL.S_PER_COMM_ADDR COMM,"+
			" SIEBEL.S_LOY_MEMBER_X MEM_X"+
			" WHERE"+
			" CON.ROW_ID=MEM.PR_CON_ID AND"+
			" MEM.PR_DOM_TIER_ID=TIER.ROW_ID AND"+
			" MEM.PR_CON_ID=COMM.PER_ID AND"+
			" MEM.ROW_ID=MEM_X.PAR_ROW_ID AND"+
			" MEM.X_MEM_LOCK_FLG='N' AND"+
			" MEM.STATUS_CD='Active' AND"+
			" COMM.X_VALID_FLG='Y' AND"+
			" COMM.COMM_MEDIUM_CD='Email' and"+
			" COMM.COMM_MEDIUM_CD IS NOT NULL AND"+
			" TIER.NAME='Gold' AND"+
			" to_date(to_char(START_DT,'DD-MON-YYYY'),'DD-MON-YYYY')>'31-DEC-2014'"+
			" ORDER BY DBMS_RANDOM.RANDOM)"+
			" WHERE ROWNUM<="+row;
			break;
		case 4:
			q="SELECT mem_num,fst_name,last_name,decode(NULL,NULL,'N')AS EMAIL FROM"+
			" (SELECT mem.mem_num,con.fst_name,con.last_name"+
			" FROM siebel.s_loy_member mem,siebel.s_contact con"+
			" WHERE pr_con_id"+
			" IN (SELECT row_id"+ 
			" FROM (SELECT row_id"+ 
			" FROM siebel.s_contact"+
			" MINUS SELECT DISTINCT per_id"+ 
			" FROM siebel.s_per_comm_addr))"+
			" AND mem.pr_con_id=con.row_id"+
			" AND upper(mem.status_cd)='ACTIVE'"+
			" ORDER BY DBMS_RANDOM.RANDOM)"+ 
			" WHERE ROWNUM<="+row;
			break;
		case 5:
			q="SELECT mem_num,SQA_FLG FROM"+
			" (SELECT mem.mem_num,decode(conx.attrib_57,NULL,'N') AS SQA_FLG"+ 
			" FROM siebel.s_contact_x conx, siebel.s_loy_member mem"+ 
			" WHERE conx.row_id=mem.pr_con_id"+
			" AND conx.attrib_57 IS NULL"+
			" AND conx.attrib_58 IS NULL"+
			" AND upper(mem.status_cd)='ACTIVE'"+
			" ORDER BY DBMS_RANDOM.RANDOM)"+
			" WHERE ROWNUM<="+row;
			break;
		case 6:
			q="SELECT mem_num,fst_name,last_name,decode(NULL,NULL,'N')AS EMAIL, decode(NULL,NULL,'N') AS SQA_FLG FROM"+
			" (SELECT mem.mem_num,con.fst_name,con.last_name"+
			" FROM siebel.s_loy_member mem,siebel.s_contact con,siebel.s_contact_x conx"+
			" WHERE pr_con_id"+ 
			" IN (SELECT row_id"+ 
			" FROM (SELECT row_id"+ 
			" FROM siebel.s_contact"+
			" MINUS SELECT DISTINCT per_id"+ 
			" FROM siebel.s_per_comm_addr))"+
			" AND mem.pr_con_id=con.row_id"+
			" AND mem.pr_con_id=conx.row_id"+
			" AND conx.attrib_57 IS NULL"+
			" AND conx.attrib_58 IS NULL"+
			" AND upper(mem.status_cd)='ACTIVE'"+
			" ORDER BY DBMS_RANDOM.RANDOM)"+ 
			" WHERE ROWNUM<="+row;
			break;
		case 00:
			//q="select fst_name,last_name from s_contacts where rownum<"+row;
			//q="select * from s_contacts where x_title='Ms' and rownum<"+row;
			q="select first_name,last_name,hire_date from employees where salary>=1000 and rownum<"+row;;
			break;
		case 7:
			q="select mem_num,DOB from"+
			"(select mem.mem_num,to_char(con.birth_dt,'dd-mon-yy') AS DOB from siebel.s_contact con, siebel.s_loy_member mem"+
			" where"+
			" con.row_id=mem.pr_con_id and"+
			" con.birth_dt IS NOT NULL and"+
			" TRUNC(months_between(to_date(to_char(sysdate,'dd-mm-yyyy'),'dd-mm-yyyy'),to_date(to_Char(con.birth_dt,'dd-mm-yyyy'),'dd-mm-yyyy'))/12)>16"+
			" ORDER BY DBMS_RANDOM.RANDOM)"+
			" WHERE rownum<="+row;
			break;
		case 8:
			q="select mem_num,DOB from"+
			"(select mem.mem_num,to_char(con.birth_dt,'dd-mon-yy') AS DOB from siebel.s_contact con, siebel.s_loy_member mem"+
			" where"+
			" con.row_id=mem.pr_con_id and"+
			" con.birth_dt IS NOT NULL and"+
			" TRUNC(months_between(to_date(to_char(sysdate,'dd-mm-yyyy'),'dd-mm-yyyy'),to_date(to_Char(con.birth_dt,'dd-mm-yyyy'),'dd-mm-yyyy'))/12)=14"+
			" ORDER BY DBMS_RANDOM.RANDOM)"+
			" WHERE rownum<="+row;
			break;
		case 9:
			q="select mem_num,DOB from"+
			"(select mem.mem_num,to_char(con.birth_dt,'dd-mon-yy') AS DOB from siebel.s_contact con, siebel.s_loy_member mem"+
			" where"+
			" con.row_id=mem.pr_con_id and"+
			" con.birth_dt IS NOT NULL and"+
			" TRUNC(months_between(to_date(to_char(sysdate,'dd-mm-yyyy'),'dd-mm-yyyy'),to_date(to_Char(con.birth_dt,'dd-mm-yyyy'),'dd-mm-yyyy'))/12)=10"+
			" ORDER BY DBMS_RANDOM.RANDOM)"+
			" WHERE rownum<="+row;
			break;
		case 10:
			q="select mem_num,DOB from"+
			"(select mem.mem_num,to_char(con.birth_dt,'dd-mon-yy') AS DOB from siebel.s_contact con, siebel.s_loy_member mem"+
			" where"+
			" con.row_id=mem.pr_con_id and"+
			" con.birth_dt IS NOT NULL and"+
			" TRUNC(months_between(to_date(to_char(sysdate,'dd-mm-yyyy'),'dd-mm-yyyy'),to_date(to_Char(con.birth_dt,'dd-mm-yyyy'),'dd-mm-yyyy'))/12)<2"+
			" ORDER BY DBMS_RANDOM.RANDOM)"+
			" WHERE rownum<="+row;
			break;
		case 11:
			String sec_val="VACRD";
			sec_val=QuickTestData.nullCheck(cl.readTextFile("config.txt", "sec")[0], "SEC not specified,check cfg...");
			q="SELECT mem_num,sec FROM"+
			" (SELECT mem_num,sec.x_status_enh_cd AS SEC"+
			" FROM siebel.s_loy_member mem, siebel.S_LOY_MEMPTRPG sec"+ 
			" WHERE mem.row_id=sec.member_id"+ 
			" AND mem.status_cd='Active'"+ 
			" AND sec.x_status_enh_cd="+"'"+sec_val+"'"+
			" ORDER BY DBMS_RANDOM.RANDOM)"+
			" WHERE ROWNUM<="+row;
			break;
		case 12:
			q="SELECT mem_num,TIER,MILES FROM"+
			" (SELECT mem_num,decode(tier.tier_id,'1-1XWL','Red','1-2KO8','Silver','1-2KOC','Gold') AS TIER,mem.point_type_a_val AS MILES"+
			" FROM siebel.s_loy_member mem, siebel.s_contact con,siebel.s_loy_mem_tier tier"+ 
			" WHERE mem.pr_con_id=con.row_id"+ 
			" AND mem.row_id=tier.member_id"+
			" AND upper(mem.status_cd)='ACTIVE'"+ 
			" AND mem.point_type_a_val>100000"+
			" ORDER BY DBMS_RANDOM.RANDOM)"+
			" WHERE ROWNUM<="+row;
			break;
		case 13:
			q="SELECT mem_num,fst_name,last_name,TIER,STAT,MM_FLG FROM"+
			" (SELECT MEM.MEM_NUM,CON.FST_NAME,CON.LAST_NAME,TIER.NAME AS TIER,MEM.STATUS_CD AS STAT,MEM.X_MILL_MILER_FLG AS MM_FLG"+
			" FROM SIEBEL.S_CONTACT CON,"+
			" SIEBEL.S_LOY_MEMBER MEM,"+
			" SIEBEL.S_LOY_TIER TIER,"+
			" SIEBEL.S_PER_COMM_ADDR COMM,"+
			" SIEBEL.S_LOY_MEMBER_X MEM_X"+
			" WHERE CON.ROW_ID=MEM.PR_CON_ID"+ 
			" AND MEM.PR_DOM_TIER_ID=TIER.ROW_ID"+ 
			" AND MEM.PR_CON_ID=COMM.PER_ID"+ 
			" AND MEM.ROW_ID=MEM_X.PAR_ROW_ID"+ 
			" AND MEM.X_MEM_LOCK_FLG='N'"+ 
			" AND MEM.STATUS_CD='Active'"+
			" AND COMM.X_VALID_FLG='Y'"+
			" AND COMM.COMM_MEDIUM_CD='Email'"+
			" AND COMM.COMM_MEDIUM_CD IS NOT NULL"+
			" AND MEM.X_MILL_MILER_FLG='Y'"+
			" ORDER BY DBMS_RANDOM.RANDOM)"+
			" WHERE ROWNUM<="+row;
			break;
		case 14:
			q="SELECT mem_num,fst_name,last_name,TIER,STAT,LTG_FLG FROM"+
			" (SELECT MEM.MEM_NUM,CON.FST_NAME,CON.LAST_NAME,TIER.NAME AS TIER,MEM.STATUS_CD AS STAT,MEM.X_LFT_TME_FLG AS LTG_FLG"+
			" FROM SIEBEL.S_CONTACT CON,"+
			" SIEBEL.S_LOY_MEMBER MEM,"+
			" SIEBEL.S_LOY_TIER TIER,"+
			" SIEBEL.S_PER_COMM_ADDR COMM,"+
			" SIEBEL.S_LOY_MEMBER_X MEM_X"+
			" WHERE CON.ROW_ID=MEM.PR_CON_ID"+ 
			" AND MEM.PR_DOM_TIER_ID=TIER.ROW_ID"+ 
			" AND MEM.PR_CON_ID=COMM.PER_ID"+ 
			" AND MEM.ROW_ID=MEM_X.PAR_ROW_ID"+ 
			" AND MEM.X_MEM_LOCK_FLG='N'"+ 
			" AND MEM.STATUS_CD='Active'"+
			" AND COMM.X_VALID_FLG='Y'"+
			" AND COMM.COMM_MEDIUM_CD='Email'"+
			" AND COMM.COMM_MEDIUM_CD IS NOT NULL"+
			" AND MEM.X_LFT_TME_FLG='Y'"+
			" ORDER BY DBMS_RANDOM.RANDOM)"+
			" WHERE ROWNUM<="+row;
			break;
		case 15:
			q="SELECT mem_num,fst_name,last_name,TIER,STAT,TOP_FLG FROM"+
			" (SELECT MEM.MEM_NUM,CON.FST_NAME,CON.LAST_NAME,TIER.NAME AS TIER,MEM.STATUS_CD AS STAT,MEM_X.ATTRIB_09 AS TOP_FLG"+
			" FROM SIEBEL.S_CONTACT CON,"+
			" SIEBEL.S_LOY_MEMBER MEM,"+
			" SIEBEL.S_LOY_TIER TIER,"+
			" SIEBEL.S_PER_COMM_ADDR COMM,"+
			" SIEBEL.S_LOY_MEMBER_X MEM_X"+
			" WHERE CON.ROW_ID=MEM.PR_CON_ID"+ 
			" AND MEM.PR_DOM_TIER_ID=TIER.ROW_ID"+ 
			" AND MEM.PR_CON_ID=COMM.PER_ID"+ 
			" AND MEM.ROW_ID=MEM_X.PAR_ROW_ID"+ 
			" AND MEM.X_MEM_LOCK_FLG='N'"+ 
			" AND MEM.STATUS_CD='Active'"+
			" AND COMM.X_VALID_FLG='Y'"+
			" AND COMM.COMM_MEDIUM_CD='Email'"+
			" AND COMM.COMM_MEDIUM_CD IS NOT NULL"+
			" AND MEM_X.ATTRIB_09='Y'"+
			" ORDER BY DBMS_RANDOM.RANDOM)"+
			" WHERE ROWNUM<="+row;
			break;
		case 16:
			q="SELECT mem_num,fst_name,last_name,TIER,STAT,LCKD_FLG FROM"+
			" (SELECT MEM.MEM_NUM,CON.FST_NAME,CON.LAST_NAME,TIER.NAME AS TIER,MEM.STATUS_CD AS STAT,MEM.X_MEM_LOCK_FLG AS LCKD_FLG"+
			" FROM SIEBEL.S_CONTACT CON,"+
			" SIEBEL.S_LOY_MEMBER MEM,"+
			" SIEBEL.S_LOY_TIER TIER,"+
			" SIEBEL.S_PER_COMM_ADDR COMM,"+
			" SIEBEL.S_LOY_MEMBER_X MEM_X"+
			" WHERE CON.ROW_ID=MEM.PR_CON_ID"+ 
			" AND MEM.PR_DOM_TIER_ID=TIER.ROW_ID"+ 
			" AND MEM.PR_CON_ID=COMM.PER_ID"+ 
			" AND MEM.ROW_ID=MEM_X.PAR_ROW_ID"+ 
			" AND MEM.X_MEM_LOCK_FLG='Y'"+ 
			" AND MEM.STATUS_CD='Active'"+
			" AND COMM.X_VALID_FLG='Y'"+
			" AND COMM.COMM_MEDIUM_CD='Email'"+
			" AND COMM.COMM_MEDIUM_CD IS NOT NULL"+
			" ORDER BY DBMS_RANDOM.RANDOM)"+
			" WHERE ROWNUM<="+row;
			break;
		case 17:
			q="SELECT mem_num,ACCT_NAME,STAT FROM"+
			" (SELECT mem.mem_num,con.fst_name AS ACCT_NAME,mem.status_cd AS STAT FROM siebel.s_contact con,siebel.s_loy_member mem"+
			" WHERE con.row_id=mem.pr_con_id"+
			" AND upper(mem.status_cd)='ACTIVE'"+
			" AND upper(con.con_cd)='MEMBER'"+
			" AND upper(mem.mem_type_cd)='ACCOUNT'"+
			" ORDER BY DBMS_RANDOM.RANDOM)"+
			" WHERE ROWNUM<="+row;
			break;
		case 18:
			String stat_val="INACTIVE";
			stat_val=QuickTestData.nullCheck(cl.readTextFile("config.txt", "stat")[0], "Status Code not specified,check cfg...");
			q="SELECT mem_num,fst_name,last_name,TIER,STAT FROM"+
			" (SELECT MEM.MEM_NUM,CON.FST_NAME,CON.LAST_NAME,TIER.NAME AS TIER,MEM.STATUS_CD AS STAT"+
			" FROM SIEBEL.S_CONTACT CON,"+
			" SIEBEL.S_LOY_MEMBER MEM,"+
			" SIEBEL.S_LOY_TIER TIER,"+
			" SIEBEL.S_PER_COMM_ADDR COMM,"+
			" SIEBEL.S_LOY_MEMBER_X MEM_X"+
			" WHERE CON.ROW_ID=MEM.PR_CON_ID"+ 
			" AND MEM.PR_DOM_TIER_ID=TIER.ROW_ID"+ 
			" AND MEM.PR_CON_ID=COMM.PER_ID"+ 
			" AND MEM.ROW_ID=MEM_X.PAR_ROW_ID"+ 
			" AND MEM.X_MEM_LOCK_FLG='N'"+ 
			" AND upper(MEM.STATUS_CD)="+"'"+stat_val+"'"+
			" AND COMM.X_VALID_FLG='Y'"+
			" AND COMM.COMM_MEDIUM_CD='Email'"+
			" AND COMM.COMM_MEDIUM_CD IS NOT NULL"+
			" ORDER BY DBMS_RANDOM.RANDOM)"+
			" WHERE ROWNUM<="+row;
			break;
		default:
			System.out.println("Please choose a valid option...");
			
		}
	return(q);
	}
	public void banner(){
		
		System.out.println("*===================================================================*");
		System.out.println("* Hello! This is a tiny mojo to extract data from your App's backend*");
		System.out.println("* system. This mojo relies on predefined queries to fetch desired   *");
		System.out.println("* data. The options listed below are types of data the mojo can     *");
		System.out.println("* currently fetch for you. So, why to wait...please choose an option*");
		System.out.println("* and see what the mojo brings for you. If you have any issue using *");
		System.out.println("* this mojo,please write to Kamalesh.Pradhan@cognizant.com X        *");
		System.out.println("*===================================================================*");
		System.out.println("");
	}
	public void menu(){
		
		System.out.println("1 -  Any active Red accounts");
		System.out.println("2 -  Any active Silver accounts");
		System.out.println("3 -  Any active Gold accounts");
		System.out.println("4 -  Any active accounts having no email address");
		System.out.println("5 -  Any active accounts having no SQA");
		System.out.println("6 -  Any accounts without SQA and Email");
		System.out.println("7 -  Any active accounts age >16 (Adults)");
		System.out.println("8 -  Any active accounts age 12-15 (Young Adults)");
		System.out.println("9 -  Any active accounts age 2-11 (Children)");
		System.out.println("10 - Any active accounts age <2 (Infants)");
		System.out.println("11 - Any acounts having SEC - VCCU|VACRD|AMEX|AMEXUK|AMEXC");
		System.out.println("12 - Any accounts having lots of miles");
		System.out.println("13 - Any Million Miler accounts");
		System.out.println("14 - Any LTG accounts");
		System.out.println("15 - Any Top accounts");
		System.out.println("16 - Any Locked accounts");
		System.out.println("17 - Any Flying Co accounts");
		System.out.println("18 - Any accounts having different status");
		
		System.out.print("\nChoose an option:");
	}
	public int readFromConsole() {
		
		int r=0;
		
		try{
			
			BufferedReader in=new BufferedReader(new InputStreamReader(System.in));	
			r=Integer.parseInt(in.readLine());
		
			
		}catch(NumberFormatException ex){
			
			System.out.println("Not a number, please enter a valid option...");
			System.out.println("Possible cause: "+ex.getMessage());
			System.exit(1);
		}catch(IOException e){
			
			System.out.println("Error ocurred...");
			System.out.println("Possible cause: "+e.getMessage());
			System.exit(1);
		}
		
		
		if((r==0)|| (r<0)||((r/r)!=1)){
			System.out.println("Please enter a valid option...");
			System.exit(1);
		}
		return(r);
	}
	
	
}
