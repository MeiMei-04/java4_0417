/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.SanPham;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author HieuCute
 */
public class SanPhamDao {
    SessionFactory sessionFactory = null;
    Session session = null;
    Transaction transaction = null;

    public SanPhamDao() {
        sessionFactory = util.HibernateUtil.getSessionFactory();
    }
    public List<SanPham> getAlldata(){
        session = sessionFactory.openSession();
        return session.createQuery("FROM SanPham", entity.SanPham.class).list();
    }
    public SanPham getDataById(int id){
        String sql = "FROM SanPham where id = :id";
        SanPham sanPham = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(sql);
            query.setParameter("id", id);
            sanPham = (SanPham) query.getSingleResult();
            transaction.commit();
            return sanPham;
        } catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public void edtData(SanPham sanPham){
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(sanPham);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public void addData(SanPham sanPham){
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(sanPham);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }public void deleteData(SanPham sanPham){
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(sanPham);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
