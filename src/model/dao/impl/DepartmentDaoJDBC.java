package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection connection;

    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO department (Name) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, department.getName());

            int rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected > 0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()){
                    int generatedId = resultSet.getInt(1);
                    department.setId(generatedId);
                }
                DB.closeResultSet(resultSet);
            }
            else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(Department department) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE department SET Name = ? WHERE Id = ?",
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, department.getName());
            preparedStatement.setInt(2, department.getId());

            preparedStatement.executeUpdate();

        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM department WHERE Id = ?");

            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows == 0){
                throw new DbException("Error! Id do not exist!");
            } else {
                System.out.print("Delete completed!");
            }
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT department.* FROM department WHERE Id = ?");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Department dep = instantiateDepartment(resultSet);
                return dep;
            }
            return null;

        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT department.* FROM department ORDER BY Name");
            resultSet = preparedStatement.executeQuery();

            List<Department> depList = new ArrayList<>();
            while (resultSet.next()){
                Department department = instantiateDepartment(resultSet);
                depList.add(department);
            }
            return depList;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    //reuse code:
    private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
        Department dep = new Department();
        dep.setId(resultSet.getInt("Id"));
        dep.setName(resultSet.getString("Name"));
        return dep;
    }
}
