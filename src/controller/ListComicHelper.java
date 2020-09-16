package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListComic;

public class ListComicHelper {
	static EntityManagerFactory emfactory =
			Persistence.createEntityManagerFactory("ConsoleComicsList");
	
	public void insertItem(ListComic li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}
	
	public void deleteItem(ListComic toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListComic>typedQuery = em.createQuery("SELECT li FROM ListComic li WHERE "
				+ "li.title = :selectedTitle and li.writer = :selectedWriter and li.artist = :selectedArtist", ListComic.class);
		
		typedQuery.setParameter("selectedTitle", toDelete.getTitle());
		typedQuery.setParameter("selectedWriter", toDelete.getWriter());
		typedQuery.setParameter("selectedArtist", toDelete.getArtist());
		typedQuery.setMaxResults(1);
		
		ListComic result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}


	public void updateItem(ListComic toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public List<ListComic> showAllItems(){
		EntityManager em = emfactory.createEntityManager();
		List<ListComic> allItems = em.createQuery("SELECT i FROM ListComic i").getResultList();
		return allItems;
	}

	public List<ListComic> searchForItemByWriter(String writerName) {
		EntityManager em =emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListComic> typedQuery = em.createQuery("SELECT li FROM ListComic"
				+ " li WHERE li.writer = :selectedWriter", ListComic.class);
		typedQuery.setParameter("selectedWriter", writerName);
		
		List<ListComic> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	public List<ListComic> searchForItemByArtist(String artistName) {
		EntityManager em =emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListComic> typedQuery = em.createQuery("SELECT li FROM ListComic"
				+ " li WHERE li.artist = :selectedArtist", ListComic.class);
		typedQuery.setParameter("selectedArtist", artistName);
		
		List<ListComic> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	public List<ListComic> searchForItemByTitle(String titleName) {
		EntityManager em =emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListComic> typedQuery = em.createQuery("SELECT li FROM ListComic"
				+ " li WHERE li.title = :selectedTitle", ListComic.class);
		typedQuery.setParameter("selectedTitle", titleName);
		
		List<ListComic> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	public ListComic searchForItemById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListComic found = em.find(ListComic.class, idToEdit);
		em.close();
		return found;
	}
	
	public void cleanUp() {
		emfactory.close();
	}
}