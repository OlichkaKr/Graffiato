package ua.lviv.iot.persistence.dao;

import ua.lviv.iot.Plane;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Named
@Dependent
public class PlaneDaoImpl extends AbstractDao<Plane> implements PlaneDao, Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected Class<Plane> getEntityClass() {
        return Plane.class;
    }

    //@Resource
    //private UserTransaction userTransaction;

    @PostConstruct
    public void init() {
        System.out.println("ready");
    }

}
