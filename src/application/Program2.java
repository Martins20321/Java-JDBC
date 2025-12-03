package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {
public static void main(String[] args) {
	
	DepartmentDao depDao =  DaoFactory.createDepartmentDao();
	
	System.out.println("=== TEST 1: Department Insert ====");
	Department newDep = new Department(null, "José Gabriel");
	depDao.insert(newDep);
	System.out.println("Insert Completed! New Id: " + newDep.getId());
}
}
