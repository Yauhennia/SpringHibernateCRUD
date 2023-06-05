package org.example.intensive.repositories;

import org.example.intensive.dto.EmployeeDTOsave;
import org.example.intensive.models.Employee;
import org.example.intensive.models.Position;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class PositionsRepository {
    private final SessionFactory sessionFactory;
    @Autowired
    public PositionsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional(readOnly = true)
    public Set<Position> index() {
        Session session = sessionFactory.getCurrentSession();
        return new HashSet<>(session.createQuery("SELECT p FROM Position p LEFT JOIN FETCH p.employees")
                .getResultList());
    }
    @Transactional(readOnly = true)
    public Position show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT p FROM Position p LEFT JOIN FETCH p.employees WHERE p.id = :id", Position.class)
                .setParameter("id", id).getSingleResult();
    }
    @Transactional
    public void save(Position position) {
        Session session = sessionFactory.getCurrentSession();
        session.save(position);
    }
    @Transactional
    public void update(int id, Position position) {

        Session session = sessionFactory.getCurrentSession();

        Position positionToBeUpdated = session.get(Position.class, id);
        positionToBeUpdated.setName(position.getName());


    }
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Position.class, id));
    }

}
