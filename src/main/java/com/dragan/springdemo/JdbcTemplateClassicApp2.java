package com.dragan.springdemo;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dragan.springdemo.dao.OrganizationDao;
import com.dragan.springdemo.daoimpl.OrganizationDaoImpl;
import com.dragan.springdemo.domain.Organization;

public class JdbcTemplateClassicApp2 {

    public static void main(String[] args) {

	// create application context
	ApplicationContext ctx = new ClassPathXmlApplicationContext("bean-cp.xml");

	// create the bean
	OrganizationDao dao = (OrganizationDao) ctx.getBean("orgDao");

	// createing seed data
	DaoUtils.createSeedData(dao);

	// list organization
	List<Organization> orgs = dao.getAllOrganizations();
	DaoUtils.printOrganizations(orgs, DaoUtils.readOperation);

	// create a new organization record
	Organization org = new Organization("General Electric", 1978, "25659", 2565, "Imagination at work");
	boolean isCreated = dao.create(org);
	DaoUtils.printSuccessFailure(DaoUtils.createOperation, isCreated);
	DaoUtils.printOrganizations(dao.getAllOrganizations(), DaoUtils.readOperation);

	// get a single organization
	Organization org2 = dao.getOrganization(1);
	DaoUtils.printOrganization(org2, " getOrganization");

	// update a slogan for an organization
	Organization org3 = dao.getOrganization(2);
	org3.setSlogan("We build **** awesome **** machines.");
	boolean isUpdate = dao.update(org3);
	DaoUtils.printSuccessFailure(DaoUtils.updateOperation, isUpdate);
	DaoUtils.printOrganization(dao.getOrganization(2), DaoUtils.updateOperation);
	
	//delete an organization
	boolean isDeleted = dao.delete(dao.getOrganization(3));
	DaoUtils.printSuccessFailure(DaoUtils.deleteOperation, isDeleted);
	DaoUtils.printOrganizations(dao.getAllOrganizations(), DaoUtils.deleteOperation);

	// Cleanup
	 dao.cleanup();
	DaoUtils.printOrganitationCount(dao.getAllOrganizations(), DaoUtils.cleanUpOperation);

	// close app context
	((ClassPathXmlApplicationContext) ctx).close();

    }

}
