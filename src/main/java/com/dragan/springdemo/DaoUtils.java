package com.dragan.springdemo;

import java.util.ArrayList;
import java.util.List;

import com.dragan.springdemo.dao.OrganizationDao;
import com.dragan.springdemo.domain.Organization;

public class DaoUtils {

    public static final String createOperation = "CREATE";
    public static final String readOperation = "READ";
    public static final String updateOperation = "UPDATE";
    public static final String deleteOperation = "DELETE";
    public static final String cleanUpOperation = "TRUNCATE";
    
    public static void printOrganizations(List<Organization> orgs, String operation){
	System.out.println("\n******** printing organization after " + operation + " operation *****");
	for(Organization org : orgs){
	    System.out.println(org);
	}
    }
    
    public static void printOrganization(Organization org, String operation){
	System.out.println("\n*****Print after invoking  " + operation + " operation******\n" + org);
    }
    public static void printSuccessFailure(String operation, boolean param){
	if(param){
	    System.out.println("\nOperation " + operation + " successful");
	}else{
	    System.out.println("\nOperation " + operation + " failed");
	}
    }
    public static void createSeedData(OrganizationDao dao){
	Organization org1 = new Organization("Amazon",1994,"65656",8585,"Work hard");
	Organization org2 = new Organization("BMW",1929,"45454",5252,"We build the ultimate machine");
	Organization org3 = new Organization("Google", 1996, "25252", 2365, "Don't be evil");
	
	List<Organization> orgs = new ArrayList<Organization>();
	orgs.add(0,org1);orgs.add(1, org2);orgs.add(2, org3);
	//int orgCount = orgs.size();
	int createCount = 0;
	for(Organization org : orgs){
	    boolean isCreated = dao.create(org);
	    if(isCreated){
		createCount += 1;
	    }
	    System.out.println("Created " + createCount + " organizations");
	}
	    
    }
    public static void  printOrganitationCount(List<Organization> orgs, String operation){
	System.out.println("\n*****Currently we have " +orgs.size() + " organizations after " + operation
			 + " opeartion ********");
    }
}
