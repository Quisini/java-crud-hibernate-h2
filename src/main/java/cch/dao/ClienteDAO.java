package cch.dao;

import cch.model.Cliente;
import cch.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 *
 * @author Gustavo
 */
public class ClienteDAO {
    
    public void salvar(Cliente cliente){
        Transaction t = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            t = session.beginTransaction();
            session.persist(cliente);
            t.commit();
        }catch(Exception e){
            if(t != null)
                t.rollback();
            throw e;
        }
    }

    public void atualizar(Cliente cliente) {
        Transaction t = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            t = session.beginTransaction();
            session.merge(cliente);
            t.commit();
        } catch (Exception e) {
            if (t != null) 
                t.rollback();
            throw e;
        }
    }

     // Excluir cliente
    public void excluir(Cliente cliente) {
        Transaction t = null;
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            t = session.beginTransaction();
            // Garante que o objeto está gerenciado pela sessão antes de remover
            Cliente clienteGerenciado = session.get(Cliente.class, cliente.getId());
            if (clienteGerenciado != null) {
                session.remove(clienteGerenciado);
            }
            t.commit();
        } catch (Exception e) {
            if (t != null) 
                t.rollback();
            throw e;
        }
    }

    public List<Cliente> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Cliente> query = session.createQuery("FROM Cliente ORDER BY nome", Cliente.class);
            return query.list();
        }
    }

    // Pesquisar clientes pelo nome (busca parcial, sem diferenciar maiúsc./minúsc.)
    public List<Cliente> pesquisarPorNome(String nome) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Cliente> query = session.createQuery(
                    "FROM Cliente WHERE lower(nome) LIKE lower(:nome) ORDER BY nome", Cliente.class);
            query.setParameter("nome", "%" + nome + "%");
            return query.list();
        }
    }

    public Cliente buscarPorId(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Cliente.class, id);
        }
    }
}
