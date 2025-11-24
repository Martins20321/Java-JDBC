package application;


import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {
	public static void main(String[] args) {
		
		Department obj = new Department(1,"Books");
		
		Seller obj1 = new Seller(21,"Pedro", "pedro@email.com", new Date(), 3000.00, obj);
		
		System.out.println(obj);
		System.out.println(obj1);
	}
}
