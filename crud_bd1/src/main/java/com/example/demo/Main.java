package com.example.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("crud");
		EntityManager em = emf.createEntityManager();
		
		Aluno aluno0 = new Aluno();
		Aluno aluno1 = new Aluno("121210197", "Rian Ismael", "rian.melo@ccc.ufc.edu.br");
		Aluno aluno2 = new Aluno("123456789", "Gustavo", "gustavo@ccc.ufcg.edu.br");
		Aluno aluno3 = new Aluno("987654321", "ovatsug", "ovatsug@ccc.ufcg.edu.br");

//		// TABELA
		//aluno0.setMatricula("121210550");
//		em.getTransaction().begin();
//		em.persist(aluno0);
//		em.getTransaction().commit();

//		// Recupere alunos por matrícula
//		em.find(Aluno.class, "121210197");
		
		// Altera Email do Aluno
//		aluno1.setNome("rianelias@gmail.com");
//		em.getTransaction().begin();
//		em.merge(aluno1);
//		em.getTransaction().commit();
		
		// Remove aluno do BD, Recupera com find e remove com o aluno recuperado.
//		Aluno aluno = em.find(Aluno.class, "123456789");

//		em.getTransaction().begin();
//		em.remove(aluno);
//		em.getTransaction().commit();
		
		AlunoDAO alunoDAO = AlunoDAO.getInstance();
		
	}
}
