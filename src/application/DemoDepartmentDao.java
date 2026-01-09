package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class DemoDepartmentDao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("=== TEST 1: department findByID ===");
        Department dep = departmentDao.findById(7);
        System.out.println(dep);

        System.out.println("\n=== TEST 2: department findAll ===");
        List<Department> depList = departmentDao.findAll();
        for (Department d : depList){
            System.out.println(d);
        }

        System.out.println("\n=== TEST 3: department insert ===");
        Department insertDep = new Department(null, "Food");
        departmentDao.insert(insertDep);
        System.out.println("Inserted! New id = " + insertDep.getId());

        System.out.println("\n=== TEST 4: department update ===");
        dep = departmentDao.findById(10);
        dep.setName("Education");
        departmentDao.update(dep);
        System.out.println("Update completed!");

        System.out.println("\n=== TEST 5: department deleteById ===");
        System.out.print("Enter id for delete test: ");
        int id = scanner.nextInt();
        departmentDao.deleteById(id);

        scanner.close();

    }
}
