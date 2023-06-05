package org.example.intensive.repositories;

import org.example.intensive.models.Employee;
import org.example.intensive.models.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Repository
public class ProjectsRepository {
    private final SessionFactory sessionFactory;
    @Autowired
    public ProjectsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional(readOnly = true)
    public Set<Project> index() {
        Session session = sessionFactory.getCurrentSession();
        return new HashSet<>(session.createQuery("SELECT p FROM Project p LEFT JOIN FETCH p.employees")
                .getResultList());

    }
}
