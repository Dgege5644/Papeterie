package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.papeterie.bo.Article;
import fr.eni.papeterie.bo.Ramette;
import fr.eni.papeterie.bo.Stylo;
import fr.eni.papeterie.dal.ArticleDAO;
import fr.eni.papeterie.dal.DALException;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	
	
	//private Article articleByID;
	final String SQL_DELETE = "DELETE FROM Articles  WHERE idArticle = ? ;";


	// constructor vide
	public ArticleDAOJdbcImpl() {
		
	}
	/**
	 * selectById : méthode qui Sélectionne un article par son identifiant
	 * @param id
	 * @return
	 */
	//
	public Article selectById(int id)throws DALException {
		// Connection
		
		Article articleById = null;
		try (Connection connexionReussie = JdbcTools.getConnection()) {
						
		// Maquette preparée (reconnaissable par les ? dans values
				String sqlSelctById = "SELECT idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type FROM Articles WHERE idArticle = ? "; // ? quand c'est une variable java
				// je crée un état préparé
				
				PreparedStatement ep = connexionReussie.prepareStatement(sqlSelctById);
				// lui dire ce que j'ai à la place des ?
				ep.setInt(1, id);
				ResultSet rs = ep.executeQuery();
				//Lire le ResultSet
				while(rs.next()) {
					int identifiant = rs.getInt("idArticle");
					String reference = rs.getString("reference");
					String designation = rs.getString("designation");
					String marque = rs.getString("marque");
					float prixUnitaire = rs.getFloat("prixUnitaire");
					int QuantiteStock = rs.getInt("qteStock");
					int grammage = rs.getInt("grammage");
					String couleur = rs.getString("couleur");
					String type = rs.getString("type");
					if (type.trim().equalsIgnoreCase("STYLO")){
						articleById = new Stylo(identifiant, marque, reference, designation, prixUnitaire, QuantiteStock, couleur);
	                } else {
	                    articleById = new Ramette(identifiant, marque, reference, designation, prixUnitaire, QuantiteStock, grammage);
					}
				}
		} catch (Exception e) {
			throw new DALException("Erreur dans la méthode selectById.");
		}
		return articleById;
	}
	
	
	//selectAll : méthode qui Sélectionne tous les articles de la table Articles
//	idArticle int IDENTITY(1,1) NOT NULL,
//	reference char(10) NOT NULL,
//	marque varchar(200) NOT NULL,
//	designation varchar(250) NOT NULL,
//	prixUnitaire float NOT NULL,
//	qteStock int NOT NULL,
//	grammage int NULL,
//	couleur varchar(50) NULL,
//	type char(10) NOT NULL,
				public List<Article> selectAll() throws DALException {
					List <Article> lignesArticlesAafficher = new ArrayList<Article>();
					Article articleAafficher = null;
					// Connection		
					try (Connection connexionReussie = JdbcTools.getConnection()){
												
					// Maquette preparée (reconnaissable par les ? dans values
							String sqlSelctAll = "SELECT idArticle, reference, marque, designation, prixUnitaire, "
									+ "qteStock, grammage, couleur, type FROM Articles ;"; // ? quand c'est une variable java
							// je crée un état préparé
							
							PreparedStatement epsa = connexionReussie.prepareStatement(sqlSelctAll);
							// lui dire ce que j'ai à la place des ?
							
							ResultSet rs = epsa.executeQuery();
							while(rs.next()) {
								int identifiant = rs.getInt("idArticle");
								String reference = rs.getString("reference");
								String designation = rs.getString("designation");
								String marque = rs.getString("marque");
								float prixUnitaire = rs.getFloat("prixUnitaire");
								int QuantiteStock = rs.getInt("qteStock");
								int grammage = rs.getInt("grammage");
								String couleur = rs.getString("couleur");
								String type = rs.getString("type");
								if (type.trim().equalsIgnoreCase("STYLO")){
									//récupéère la ligne si c'est un stylo et la met dans la variable
									lignesArticlesAafficher.add(new Stylo (identifiant, reference, marque, designation, prixUnitaire, QuantiteStock, couleur));
				                } else {
				                	// récupère la ligne si c'est une ramette et la met dans la variable
				                	lignesArticlesAafficher.add(new Ramette(identifiant, reference, marque, designation, prixUnitaire, QuantiteStock, grammage));
								}
								
							}
					} catch (Exception e) {
						throw new DALException("Erreur dans la méthode selectAll.");
					}
					return lignesArticlesAafficher;
				}
	
	//update : méthode qui Modifie les attributs d’un article
			public void update(Article article) throws DALException { 
					// Connection
					try (Connection connexionReussie = JdbcTools.getConnection()) {		
					// Maquette preparée (reconnaissable par les ? dans values
						String sqlUpdate = " UPDATE Articles SET marque = ?, reference = ?, designation = ?,  prixUnitaire = ?, qteStock = ?, grammage= ?, couleur= ?, type = ? WHERE idArticle = ? ;";
						// je crée un état préparé
						PreparedStatement epu = connexionReussie.prepareStatement(sqlUpdate);
						// lui dire ce que j'ai à la place des ?
						epu.setString(1, article.getMarque());
						epu.setString(2, article.getReference());
						epu.setString(3, article.getDesignation());
						epu.setFloat(4, article.getPrixUnitaire());
						epu.setInt(5, article.getQteStock());
						epu.setInt(9, article.getIdArticle());
						if (article instanceof Stylo) {
							epu.setNull(6, Types.INTEGER);
							epu.setString(7, ((Stylo) article).getCouleur());
							epu.setString(8, "STYLO");
							
						} else {
						epu.setInt(6, ((Ramette) article).getGrammage());
						epu.setNull(7, Types.VARCHAR);
						epu.setString(8, "RAMETTE");
						}
						epu.executeUpdate();
						
					} catch (Exception e) {
						throw new DALException("Erreur dans la méthode update.");
					}
					}
	
	
	/**
	 * insert : méthode qui Ajoute un article en base de données et affecte l’identifiant créé par la base de données à l’article
	 * @param article
	 * @throws SQLException
	 * sous la forme
	 * Stylo( "Bic", "BBOrange","Bic bille Orange", 1.2f, 20, "bleu");
	 * Ramette(  "Clairef", "CRA4S", "Ramette A4 Sup", 9f, 20, 80);
	 * Stylo( "Stypen", "PlumeS", "Stylo Plume Stypen", 5.5f, 20, "jaune");
	 */
	

	public void insert(Article article)throws DALException  {
			// Connection
				try (Connection connexionReussie = JdbcTools.getConnection()) {		
				// Maquette preparée (reconnaissable par les ? dans values
					String sqlInsert = "INSERT INTO Articles (marque, reference, designation, prixUnitaire, qteStock, grammage, couleur, type) VALUES (?, ?, ?, ?, ?, ?, ?, ? );";
					// je crée un état préparé
					PreparedStatement epi = connexionReussie.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
					// lui dire ce que j'ai à la place des ?
					// stylo a1
					epi.setString(1, article.getMarque());
					epi.setString(2, article.getReference());
					epi.setString(3, article.getDesignation());
					epi.setFloat(4, article.getPrixUnitaire());
					epi.setInt(5, article.getQteStock());
					if (article instanceof Stylo) {
						epi.setNull(6, Types.INTEGER);
						epi.setString(7, ((Stylo) article).getCouleur());
						epi.setString(8, "STYLO");
					} else {
					epi.setInt(6, ((Ramette) article).getGrammage());
					epi.setNull(7, Types.VARCHAR);
					epi.setString(8, "RAMETTE");
					}
					epi.executeUpdate();
					ResultSet clesGenerees= epi.getGeneratedKeys(); // récupérer les colonnes auto incrémentées
					if (clesGenerees.next()) {
						int idGenere= clesGenerees.getInt(1);// pour récupérer la 1ère clé générée
						article.setIdArticle(idGenere);// affecte l'id généré à l'article 
					
					}
			} catch (Exception e) {
				throw new DALException("Erreur dans la méthode insert.");
			}
			
		
	}
	
	
	
	//delete : méthode qui Supprime un article en base de données
	public void delete(int id) throws DALException {
		// Connection
		
		try (Connection connexionReussie = JdbcTools.getConnection()){		
			// Maquette preparée (reconnaissable par les ? dans values
			
			// je crée un état préparé
			PreparedStatement epd = connexionReussie.prepareStatement(this.SQL_DELETE);
			// lui dire ce que j'ai à la place des ?
			epd.setInt(1, id);
			epd.executeUpdate();		
				
		} catch (Exception e) {
			throw new DALException("Erreur dans la méthode delete.");
		}
		
	}
	

	
	@Override
	public List<Article> selectByMarque(String marque) throws DALException {
		List <Article> lignesArticlesAafficher = new ArrayList<Article>();
		Article article;
		// Connection		
		try (Connection connexionReussie = JdbcTools.getConnection()){
									
		// Maquette preparée (reconnaissable par les ? dans values
				String sqlSelectByMarque = "SELECT idArticle, marque, reference, designation, prixUnitaire, "
						+ "qteStock, grammage, couleur, type FROM Articles WHERE marque = ?;"; // ? quand c'est une variable java
				// je crée un état préparé
				
				PreparedStatement epsbm = connexionReussie.prepareStatement(sqlSelectByMarque);
				// lui dire ce que j'ai à la place des ?
				epsbm.setString(1, marque);
				ResultSet rs = epsbm.executeQuery();
				while(rs.next()) {
					int identifiant = rs.getInt("idArticle");
					String reference = rs.getString("reference");
					String designation = rs.getString("designation");
					
					float prixUnitaire = rs.getFloat("prixUnitaire");
					int QuantiteStock = rs.getInt("qteStock");
					int grammage = rs.getInt("grammage");
					String couleur = rs.getString("couleur");
					String type = rs.getString("type");
					if (type.trim().equalsIgnoreCase("STYLO")){
						lignesArticlesAafficher.add (new Stylo (identifiant, reference, marque, designation, prixUnitaire, QuantiteStock, couleur));
	                } else {
	                    lignesArticlesAafficher.add (new Ramette(identifiant, reference, marque, designation, prixUnitaire, QuantiteStock, grammage));
					}
				}
		} catch (Exception e) {
			throw new DALException("Erreur dans la méthode selectByMarque.");
		}
		return lignesArticlesAafficher;
	}
	
	@Override
	public List<Article> selectByMotCle(String motCle) throws DALException  {
		List <Article> lignesArticlesAafficher = new ArrayList<Article>();
		Article article;
		// Connection		
		try (Connection connexionReussie = JdbcTools.getConnection()){
									
		// Maquette preparée (reconnaissable par les ? dans values
				String sqlSelectByMotCle = "SELECT idArticle, marque, reference, designation, prixUnitaire, \"\r\n" + 
						"						+ \"qteStock, grammage, couleur, type FROM Articles WHERE marque LIKE ? OR designation LIKE ?;"; // ? quand c'est une variable java
				// je crée un état préparé
				
				PreparedStatement epsbm = connexionReussie.prepareStatement(sqlSelectByMotCle);
				// lui dire ce que j'ai à la place des ?
				epsbm.setString(1, motCle);
				epsbm.setString(2, motCle);
				
				ResultSet rs = epsbm.executeQuery();
				
				while(rs.next()) {
					int identifiant = rs.getInt("idArticle");
					String reference = rs.getString("reference");
					String designation = rs.getString("designation");
					String marque = rs.getString("marque");
					float prixUnitaire = rs.getFloat("prixUnitaire");
					int QuantiteStock = rs.getInt("qteStock");
					int grammage = rs.getInt("grammage");
					String couleur = rs.getString("couleur");
					String type = rs.getString("type");
					if (type.trim().equalsIgnoreCase("STYLO")){
						article = new Stylo (identifiant, reference, marque, designation, prixUnitaire, QuantiteStock, couleur);
	                } else {
	                    lignesArticlesAafficher.add (new Ramette(identifiant, reference, marque, designation, prixUnitaire, QuantiteStock, grammage));
					}
				}
		} catch (Exception e) {
			throw new DALException("Erreur dans la méthode selectByMotCle.");
		}
		return lignesArticlesAafficher;
		
		}
	@Override
	public Connection getConnection() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
		
	
	
}
		