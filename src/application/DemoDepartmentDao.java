package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;

public class DemoDepartmentDao {
    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("=== TEST 1: department findByID ===");
        Department dep = departmentDao.findById(7);
        System.out.println(dep);

        System.out.println("\n=== TEST 2: department findAll ===");
        List<Department> depList = departmentDao.findAll();
        for (Department d : depList){
            System.out.println(d);
        }

    }
}
