package fr.istic.tpgae.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.istic.tpgae.shared.Personne;


/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	void inscrirePersonne(Personne p) throws IllegalArgumentException;
	List<Personne> lirePersonnes() throws IllegalArgumentException;
	void deletePersonnes() throws IllegalArgumentException;
}
