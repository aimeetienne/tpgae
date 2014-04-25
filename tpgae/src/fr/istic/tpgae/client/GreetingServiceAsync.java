package fr.istic.tpgae.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.istic.tpgae.shared.Personne;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void inscrirePersonne(Personne p, AsyncCallback<Void> callback)
			throws IllegalArgumentException;
	void lirePersonnes(AsyncCallback<List<Personne>> callback)
			throws IllegalArgumentException;
	void deletePersonnes(AsyncCallback<Void> callback)
			throws IllegalArgumentException;
}
