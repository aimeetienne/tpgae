package fr.istic.tpgae.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.istic.tpgae.client.GreetingService;
import fr.istic.tpgae.shared.Maison;
import fr.istic.tpgae.shared.Personne;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {
	
	private final EntityManager manager;
	

	public GreetingServiceImpl(){
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("transactions-optional");
		manager = factory.createEntityManager();
	}
	
	@Override
	public void inscrirePersonne(Personne p) throws IllegalArgumentException {
		manager.getTransaction().begin();
		manager.persist(p);
		manager.getTransaction().commit();
	}

	@Override
	public List<Personne> lirePersonnes() throws IllegalArgumentException {
		
		TypedQuery<Personne> q = manager.createQuery("SELECT p FROM Personne p",Personne.class);
		List<Personne> resul = new ArrayList<Personne>();
		try{
			List<Personne> temp = q.getResultList();
			for (Personne p : temp){
		
			//List<Maison> temp1 = null;
			if (p.getMaison() != null){
				//temp1 = new ArrayList<Maison>();
				for (Maison m : p.getMaison()){
					List<Personne> listes = m.getPersonnes();
					//manager.detach(m);
					//temp1.add(m);
				}
			}
				
			//p.setMaison(temp1);
			resul.add(p);

			}	
				
			
		}
		catch (Exception e){
			System.out.println(e);
		}
		
		return resul;

	}

	@Override
	public void deletePersonnes() throws IllegalArgumentException {
		manager.getTransaction().begin();
		manager.createQuery("DELETE FROM Personne p").executeUpdate();
		manager.getTransaction().commit();
		
	}
	

	
	
}
