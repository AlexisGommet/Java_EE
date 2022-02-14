/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import model.bean;


/**
 *
 * @author sherm
 */
public class TestJDBC {
    
    private List<String> messages = new ArrayList<>();
    private List<bean> beans = new ArrayList<>();
    public List<bean> executerTests( HttpServletRequest request ) {
        
        int nbrpara = 3;
        
    try {
        messages.add( "Chargement du driver..." );
        Class.forName( "org.apache.derby.jdbc.ClientDriver" );  //« "org.apache.derby.jdbc.ClientDriver«  for derby
        messages.add( "Driver chargé !" );
    } catch ( ClassNotFoundException e ) {
        messages.add( "Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"
                + e.getMessage() );
    }
    /* Connexion à la base de données */
    String url = "jdbc:derby://localhost:1527/tp_jdbc";
    String utilisateur = "root";
    String motDePasse = "root";
    Connection connexion = null;
    Statement statement = null;
    ResultSet resultat = null;
    try {
        messages.add( "Ceci est un test de JDBC" );
        messages.add( "Connexion à la base de données..." );
        connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
        messages.add( "Connexion réussie !" );

        /* Création de l'objet gérant les requêtes */
        statement = connexion.createStatement();
        messages.add( "Objet requête créé !" );

        Map<String, String[]> hm = request.getParameterMap();
        ArrayList<Integer> numssn = new ArrayList<>();
        ArrayList<Integer> multiple = new ArrayList<>();
        ArrayList<Integer> bonus = new ArrayList<>();

        for (Map.Entry<String, String[]> entry : hm.entrySet()) {
            if ("numssn".equals(entry.getKey())) {
                for (int i = 0; i < nbrpara; i++) {
                    try {
                        numssn.add(Integer.valueOf(entry.getValue()[i]));
                    } catch (Exception e) {
                        numssn.add(0);
                    }
                }
            }
            if ("multiple".equals(entry.getKey())) {
                for (int i = 0; i < nbrpara; i++) {
                    try {
                        multiple.add(Integer.valueOf(entry.getValue()[i]));
                        bonus.add(Integer.valueOf(entry.getValue()[i]) * 100);
                    } catch (Exception e) {
                        multiple.add(0);
                        bonus.add(0);
                    }
                }
            }
        }
        
        for (int i = 0; i < nbrpara; i++) {
            statement.execute( "INSERT INTO ID (SSN, MULTIPLE, BONUS) VALUES ("+ numssn.get(i)+", "+ multiple.get(i) +", "+ bonus.get(i)+")" );
        }

        /* Exécution d'une requête de lecture */
        resultat = statement.executeQuery( "SELECT SSN, MULTIPLE, BONUS FROM ID" );
        messages.add( "Requête \"SELECT (SSN, MULTIPLE, BONUS) FROM ID\" effectuée !" );
        /* Récupération des données du résultat de la requête de lecture */
        while ( resultat.next() ) {
            int ssn = resultat.getInt( "ssn" );
            int multiple2 = resultat.getInt( "multiple" );
            int bonus2 = resultat.getInt( "bonus" );
            /* Formatage des données pour affichage dans la JSP finale. */
            messages.add( "Données retournées par la requête : ssn = " + ssn + ", multiple = " + multiple2
                    + ", bonus = "
                    + bonus2 + "." );
            bean b = new bean();
            b.setNumssn(ssn);
            b.setMultiple(multiple2);
            b.setBonus(bonus2);
            beans.add(b);
        }
    } catch ( SQLException e ) {
        messages.add( "Erreur lors de la connexion : <br/>"
                + e.getMessage() );
    } finally {
        messages.add( "Fermeture de l'objet ResultSet." );
        if ( resultat != null ) {
            try {
                resultat.close();
            } catch ( SQLException ignore ) {
            }        }
messages.add( "Fermeture de l'objet Statement." );
        if ( statement != null ) {
            try {
                statement.close();
            } catch ( SQLException ignore ) {
            }
        }
        messages.add( "Fermeture de l'objet Connection." );
        if ( connexion != null ) {
            try {
                connexion.close();
            } catch ( SQLException ignore ) {
            }
        }
    }

        return beans;
    }
}


