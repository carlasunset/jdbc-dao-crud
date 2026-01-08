package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Program {
    public static void main(String[] args) {
        Department depart = new Department(1, "Books");
        System.out.println(depart);

        Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), 1852.00, depart);
        System.out.println(seller);

        SellerDao sellerDao = DaoFactory.createSellerDao();
    }
}
