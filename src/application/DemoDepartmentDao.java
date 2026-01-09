package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DemoDepartmentDao {
    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("=== TEST 1: department findByID ===");
        Department dep = departmentDao.findById(7);
        System.out.println(dep);

    }
}
