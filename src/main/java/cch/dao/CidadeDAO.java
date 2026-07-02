package cch.dao;

import cch.model.Cidade;
import cch.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CidadeDAO {

    // Cadastrar nova cidade
    public void salvar(Cidade cidade) {
        Transaction t = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();
            session.persist(cidade);
            t.commit();
        } catch (Exception e) {
            if (t != null) t.rollback();
            throw e;
        }
    }

    // Alterar cidade existente
    public void atualizar(Cidade cidade) {
        Transaction t = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();
            session.merge(cidade);
            t.commit();
        } catch (Exception e) {
            if (t != null) t.rollback();
            throw e;
        }
    }

    // Excluir cidade
    public void excluir(Cidade cidade) {
        Transaction t = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            t = session.beginTransaction();
            // Garante que o objeto está gerenciado pela sessão antes de remover
            Cidade cidadeGerenciada = session.get(Cidade.class, cidade.getId());
            if (cidadeGerenciada != null) {
                session.remove(cidadeGerenciada);
            }
            t.commit();
        } catch (Exception e) {
            if (t != null) t.rollback();
            throw e;
        }
    }

    // Listar todas as cidades cadastradas
    public List<Cidade> listarTodas() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Cidade> query = session.createQuery("FROM Cidade ORDER BY nome", Cidade.class);
            return query.list();
        }
    }

    // Pesquisar cidades pelo nome (busca parcial, sem diferenciar maiúsc./minúsc.)
    public List<Cidade> pesquisarPorNome(String nome) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Cidade> query = session.createQuery(
                    "FROM Cidade WHERE lower(nome) LIKE lower(:nome) ORDER BY nome", Cidade.class);
            query.setParameter("nome", "%" + nome + "%");
            return query.list();
        }
    }

    // Busca por id - útil para popular o JComboBox de Cliente e para excluir/atualizar
    public Cidade buscarPorId(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Cidade.class, id);
        }
    }
}
