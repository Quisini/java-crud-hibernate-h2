package cch.dao;

import cch.model.Cliente;
import cch.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClienteDAO {
    
    public void salvar(Cliente cliente){
        Transaction t = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            t = session.beginTransaction();
            session.persist(cliente);
            t.commit();
        }catch(Exception e){
            t.rollback();
            throw e;
        }
    }
}
