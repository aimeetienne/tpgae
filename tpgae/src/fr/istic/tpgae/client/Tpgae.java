package fr.istic.tpgae.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.view.client.ListDataProvider;

import fr.istic.tpgae.shared.Personne;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Tpgae implements EntryPoint {


	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	private Button btn_valider = new Button("Ajouter");
	private Button btn_supprimer_personnes = new Button("Supprimer les personnes");
	private TextBox txt_nom = new TextBox();
	private TextBox txt_prenom = new TextBox();
	private TextBox txt_email = new TextBox();
	private ListDataProvider<Personne> dataProvider = new ListDataProvider<Personne>();
	private CellTable<Personne> table = new CellTable<Personne>();
	
	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {
		RootPanel.get("txt_nom").add(txt_nom);
		RootPanel.get("txt_prenom").add(txt_prenom);
		RootPanel.get("txt_email").add(txt_email);
		
		RootPanel.get("btn_supprimer_personnes").add(btn_supprimer_personnes);
		btn_supprimer_personnes.addClickHandler(handlerSupprimerPersonnes);
		
		btn_valider.addClickHandler(handlerValider);
		RootPanel.get("btn_test").add(btn_valider);



	    TextColumn<Personne> prenomColumn = new TextColumn<Personne>() {
	      @Override
	      public String getValue(Personne p) {
	        return p.getPrenom();
	      }
	    };


	    TextColumn<Personne> nomColumn = new TextColumn<Personne>() {
	      @Override
	      public String getValue(Personne p) {
	        return p.getNom();
	      }
	    };

	    TextColumn<Personne> mailColumn = new TextColumn<Personne>() {
	      @Override
	      public String getValue(Personne p) {
	        return p.getMail();
	      }
	    };
	   

	    table.addColumn(prenomColumn, "Prenom");
	    table.addColumn(nomColumn, "Nom");
	    table.addColumn(mailColumn, "Email");  


	    dataProvider.addDataDisplay(table);
	    
	    RootPanel.get("listePersonnes").add(table);
	    
	    chargerPersonnes();
			
	}
	
	private ClickHandler handlerValider = new  ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			Personne p = new Personne();
			p.setNom(txt_nom.getText());
			p.setMail(txt_email.getText());
			p.setPrenom(txt_prenom.getText());
			greetingService.inscrirePersonne(p,callback);
		}
		
		private AsyncCallback<Void> callback = new AsyncCallback<Void> (){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("BUG");
			}

			@Override
			public void onSuccess(Void result) {
				txt_nom.setText(null);
				txt_prenom.setText(null);
				txt_email.setText(null);
				chargerPersonnes();
				btn_supprimer_personnes.setVisible(true);
			}
			
		};
		
	};
	
private ClickHandler handlerSupprimerPersonnes = new  ClickHandler(){
		

		@Override
		public void onClick(ClickEvent event) {
			greetingService.deletePersonnes(callback);
		}
		
		private AsyncCallback<Void> callback = new AsyncCallback<Void> (){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("BUG");
			}

			@Override
			public void onSuccess(Void result) {
				btn_supprimer_personnes.setVisible(false);
				chargerPersonnes();
			}
			
			
		};
		
	};
	
	private void chargerPersonnes(){
		greetingService.lirePersonnes(new AsyncCallback<List<Personne>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("BUG CHARGEMENT DES PERSONNES");
				
			}

			@Override
			public void onSuccess(List<Personne> result) {
				dataProvider.setList(result);
				
			}
		});
		
	};
	
}
