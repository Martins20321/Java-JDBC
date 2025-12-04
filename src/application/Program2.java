package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {
public static void main(String[] args) {
	
	DepartmentDao depDao =  DaoFactory.createDepartmentDao();
	
	/*
	System.out.println("=== TEST 1: Department Insert ====");
	Department newDep = new Department(null, "Universities");
	depDao.insert(newDep);
	System.out.println("Insert Completed! New Id: " + newDep.getId());
	*/
	
	System.out.println();
	System.out.println("=== TEST 2: Department FindByID ====");
	Department dep = depDao.findById(6);
	System.out.println(dep);

	System.out.println();
	System.out.println("=== TEST 3: Department Update ====");
	dep = depDao.findById(8);
	dep.setName("Drinks");
	depDao.update(dep);
	System.out.println("Update Completed");
	
	/*
	System.out.println();
	System.out.println("=== TEST 4: Department Delete ====");
	depDao.deleteById(10);
	System.out.println("Delete Completed");
	*/
	
	System.out.println();
	System.out.println("=== TEST 5: Department findAll ====");
	List<Department> list = depDao.findAll();
	for(Department dep1 : list) {
		System.out.println(dep1);
	}
}
}
