package com.example.demo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class AlunoDAO {

       private static AlunoDAO unique_instance;
       private static EntityManager entityManager;
       private static EntityManagerFactory factory;

       public static AlunoDAO getInstance(){
         if (unique_instance == null){
        	 unique_instance = new AlunoDAO();
         }
         return unique_instance;
       }
       
       public static void close() {
    	   entityManager.close();
    	   factory.close();
       }

       private AlunoDAO() {
         entityManager = getEntityManager();
       }

       private EntityManager getEntityManager() {
        factory = Persistence.createEntityManagerFactory("crud");
        if (entityManager == null) {
          entityManager = factory.createEntityManager();
        }

        return entityManager;
       }
       
       public Aluno getByMatricula(String matricula) {
         return entityManager.find(Aluno.class, matricula);
       }


       public boolean persist(Aluno aluno) {
    	   try {
			entityManager.getTransaction().begin();
			entityManager.persist(aluno);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
       }

       public Aluno update(Aluno aluno) {
    	   Aluno upd_aluno = null;
    	   try {
			entityManager.getTransaction().begin();
			upd_aluno = entityManager.merge(aluno);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			upd_aluno = null;
			entityManager.getTransaction().rollback();
		}
    	   return upd_aluno;
       }
       
       public boolean removeByMatricula(String matricula) {
    	 try {
			entityManager.getTransaction().begin();
			Aluno aluno = entityManager.find(Aluno.class, matricula);
			entityManager.remove(aluno);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println(e);
			return false;
		}
       }
       
       public List<Aluno> getAllByNome(String nome) {
    	   Query query = entityManager.createQuery("FROM Aluno WHERE nome LIKE :nome");
    	   query.setParameter("nome", "%"+nome+"%");
    	   List<Aluno> listAluno = query.getResultList();
    	   return listAluno;
       }
       
       public Aluno getFirstByNome(String nome) {
    	   Query query = entityManager.createQuery("FROM Aluno WHERE nome LIKE :nome");
    	   query.setParameter("nome", "%"+nome+"%");
    	   query.setMaxResults(1);
    	   List<Aluno> listAluno = query.getResultList();
    	   if(listAluno.isEmpty()) {
    		   return null;
    	   }
    	   return listAluno.get(0);
       }
       
       
}