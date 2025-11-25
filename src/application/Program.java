package application;


import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TEST 1: seller findById ====");
		Seller seller = sellerDao.findById(1);
		System.out.println(seller);
		
		System.out.println();
		System.out.println("=== TEST 2: seller findByDepartment ====");
		Department deparment = new Department(2, null);
		List<Seller> list = sellerDao.findBydepartment(deparment);
		for(Seller obj : list) {
			System.out.println(obj);
		}
	}
}
