package fr.eni.papeterie.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;
import fr.eni.papeterie.dal.DAOFactory;

public class CatalogueManager {

	private ArticleDAO daoArticle; // à cet instant il ne vaut rien car il n'st pas instancié
							
	private static CatalogueManager instance; // ce n'était pas dans le diagramme mais on l'a fait parce que c'est facile et utile
	
	private CatalogueManager(){
		//DAOFactory est la seule clase qui permet de créer une instance de ArticleDAO, avec la méthode getArticleDAO()
			this.daoArticle = DAOFactory.getArticleDAO(); // cela permet d'avoir accès à toute la DAL.
		// daoArticle représente maintenant toute la DAL et on va pouvoir lui appliquer toutes les méthodes de la DAL
				
	}
	/**
	 * création du singleton qui n'était pas demandé dans le TP mais c'est utile et facile
	 * permet d'avoir une et une seule instance de CatalogueManager
	 * @return
	 * @throws BLLException
	 */
	public static CatalogueManager getInstance() throws BLLException {
		if (instance == null) {
			instance = new CatalogueManager();
		}
		return instance; 
	
	}
	/**
	 * cette méthode permet de récupérer l'intégralité du catalogue cad tous les articles insérés dans la BDD
	 * @return
	 * @throws BLLException
	 */
	public List<Article> getCatalogue() throws BLLException{
		List<Article> catalogue = null;
		try { // on est obligé de faire un try catch car selectAll peut lever une DALException et on ne veut pas la propager
			catalogue = daoArticle.selectAll();// on choisit cette méthode parmi toutes celles de la DAl qui retournent une liste car c'est celle qu'il nous faut
		} catch (DALException e) {
			throw new BLLException("Erreur dans la methode getCatalogue");
		}
		return catalogue;
	}
	/**
	 * Cette méthode permet d'ajouter des articles (s'ils sont conformes à ce qui est demandé (cases non nulles/ non vides) et valeurs
	 * positives pour qtéStock, prixUnitaire et grammage
	 * @param a
	 * @throws BLLException
	 */
	public void addArticle(Article a) throws BLLException{
		try { // on est obligé de faire un try catch car insert peut lever une DALException et on ne veut pas la propager
			this.validerArticle(a); // appel de la méthode validerArticle avant d'ajouter un article afin de garantir que tout est conforme
									// this. pour bien préciser que c'est la mtéhode de cette classe là (au cas où il ya uarait une ou 
									// plusieurs autres méthodes qui s'appelle(nt) comme ça.
			daoArticle.insert(a);// insertion de l'article s'il est validé
		} catch (DALException e) {
			throw new BLLException("Erreur dans la methode addArticle");
		}
	}
	
	/**
	 * Cette méthode permet de modifier un artice
	 * @param a
	 * @throws BLLException
	 */
	public void updateArticle(Article a) throws BLLException {
		try { // on est obligé de faire un try catch car selectAll peut lever une DALException et on ne veut pas la propager
			daoArticle.update(a);
		} catch (DALException e) {
			throw new BLLException("Erreur dans la methode updateArticle");
		}
	}
	/**
	 * Cette méthode permet de supprimer un article
	 * @param index
	 * @throws BLLException
	 */
	public void removeArticle(int index)throws BLLException {
		try { // on est obligé de faire un try catch car selectAll peut lever une DALException et on ne veut pas la propager
			daoArticle.delete(index);
		} catch (DALException e) {
			throw new BLLException("Erreur dans la methode removeArticle");
		}
	}
	
	
	/**
	 * Méthode qui permet de valider l'ajout d'un article si les contraintes sont respectées cad :
	 * cases non nulles et non vides sur toutes les colonnes où c'est demandé dans la BDD
	 * prix unitaire, grammage et stock obligatoirement positifs
	 * @param a
	 * @throws BLLException
	 */
	private void validerArticle(Article a)throws BLLException {
		// vérification des critères communs à tous les articles : si l'un est null ou vide 
		// cela génère l'exception "erreur de validation sur un critère général
		if (a.getMarque()==null || a.getMarque().equalsIgnoreCase("")
				|| a.getDesignation()==null || a.getDesignation().equalsIgnoreCase("")
				|| a.getReference()==null || a.getReference().equalsIgnoreCase("")
				|| a.getQteStock()<0
				|| a.getPrixUnitaire()< 0.f){
			throw new BLLException("Erreur de validation sur un critère général");
		}
		// vérification du critère si c'est un stylo 
			if (a instanceof Stylo) { // instanceof retourne le type de la variable
				if (((Stylo) a).getCouleur()==null || ((Stylo) a).getCouleur().equalsIgnoreCase("")) { //vérifie le critère 
																									//pour un stylo
					throw new BLLException("Erreur de stylo");// génération du message d'erreur si couleur non conforme
				}
			} else if(a instanceof Ramette) {
				if (((Ramette)a).getGrammage()<0) { // vérifie le critère pour une rammette
				throw new BLLException("Erreur de rammette"); // génére l'exception si crière d'une rammette non conforme
				}

			}		
		}	
					
	
	public Article getArticle(int index) throws BLLException{
		Article articleArecupérer;
		try {
			articleArecupérer = daoArticle.selectById(index);
		} catch (DALException e) {
			throw new BLLException("Erreur dans la methode getArticle");
		}
		return articleArecupérer;
	}
	
}


