package org.example.intensive.repositories;

import org.example.intensive.dto.EmployeeDTOsave;
import org.example.intensive.models.Employee;
import org.example.intensive.models.Position;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Repository
public class EmployeesRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public EmployeesRepository(SessionFactory sessionFactory, ModelMapper modelMapper) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional(readOnly = true)
    public Set<Employee> index() {
        Session session = sessionFactory.getCurrentSession();
       return new HashSet<>(session.createQuery("SELECT e FROM Employee e JOIN FETCH e.position LEFT JOIN FETCH e.projects")
                .getResultList());

    }
    @Transactional(readOnly = true)
    public Employee show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT e FROM Employee e JOIN FETCH e.position LEFT JOIN FETCH e.projects WHERE e.id = :id", Employee.class)
                .setParameter("id", id).getSingleResult();
    }

    @Transactional
    public void save(Employee employee, String positionName) {

        Session session = sessionFactory.getCurrentSession();

        Position position = session.createQuery("from Position where name = :name", Position.class)
                .setParameter("name", positionName).getSingleResult();
        employee.setPosition(position);

        session.save(employee);
    }
    @Transactional
    public void update(int id, EmployeeDTOsave employeeDTOsave) {

        Session session = sessionFactory.getCurrentSession();

        Employee employeeToBeUpdated = session.get(Employee.class, id);

        Position position = session.createQuery("from Position where name = :name", Position.class)
                .setParameter("name", employeeDTOsave.getPositionName()).getSingleResult();

        employeeToBeUpdated.setFirstName(employeeDTOsave.getFirstName());
        employeeToBeUpdated.setLastName(employeeDTOsave.getLastName());
        employeeToBeUpdated.setPosition(position);

    }
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Employee.class, id));
    }

}
