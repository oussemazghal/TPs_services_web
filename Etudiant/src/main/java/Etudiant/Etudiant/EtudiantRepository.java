package Etudiant.Etudiant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EtudiantRepository {

    Connection con = null;

    public EtudiantRepository() {
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "";
        try {       
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion établie !");
        } catch (Exception e) {
            System.out.println("Erreur connexion : " + e.getMessage());
        }
    }

   
    public List<Etudiant> getEtudiants() {
        List<Etudiant> etudiants = new ArrayList<>();
        String sql = "SELECT * FROM Etudiant";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Etudiant e = new Etudiant();

                e.setId(rs.getInt("id"));
                e.setName(rs.getString("nom"));
                e.setScore(rs.getInt("score"));

                etudiants.add(e);
            }

            st.close();
        } catch (Exception e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }

        return etudiants;
    }
}
