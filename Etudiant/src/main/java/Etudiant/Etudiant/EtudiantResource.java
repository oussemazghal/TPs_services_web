package Etudiant.Etudiant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;





@Path("/etudiants")
public class EtudiantResource {
	 
     
	static EtudiantRepository repo= new EtudiantRepository();
	Connection con=repo.con;
	
	/*méthode retourant la liste des étudiants*/
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Etudiant> getEtudiants()
	{String sql = "SELECT * FROM Etudiant";
	List<Etudiant> etudiants = new ArrayList<>();
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
	
	/* méthode retourant l'étudiant d'identifiant id */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("etudiant/{id}")
	public Etudiant getEtudiantId(@PathParam("id") int id) {
	    
	    String sql = "SELECT * FROM Etudiant WHERE id=?";
	    Etudiant etu = null;

	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, id);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            etu = new Etudiant();
	            etu.setId(rs.getInt("id"));
	            etu.setName(rs.getString("nom"));
	            etu.setScore(rs.getInt("score"));
	        }

	        ps.close();
	    } catch (Exception e) {
	        System.out.println("Erreur SQL : " + e.getMessage());
	    }

	    return etu; 
	}
	
	/* méthode retourant l'étudiant d'identifiant id */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("etudiant2")
	public Etudiant getEtudiant2(@QueryParam("id") int id)
	{
		String sql = "SELECT * FROM Etudiant WHERE id=?";
	    Etudiant etu = null;

	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, id);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            etu = new Etudiant();
	            etu.setId(rs.getInt("id"));
	            etu.setName(rs.getString("nom"));
	            etu.setScore(rs.getInt("score"));
	        }

	        ps.close();
	    } catch (Exception e) {
	        System.out.println("Erreur SQL : " + e.getMessage());
	    }

	    return etu; 
	}
	
	
	/* méthode retourant la liste des étudiants ayant le même nom "name"  */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("etudiant3")
	public List<Etudiant> getEtudiantByName(@QueryParam("name") String name) {
	    List<Etudiant> etudiants = new ArrayList<>();
	    String sql = "SELECT * FROM Etudiant WHERE nom = ?";

	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, name);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Etudiant e = new Etudiant();
	            e.setId(rs.getInt("id"));
	            e.setName(rs.getString("nom"));
	            e.setScore(rs.getInt("score"));
	            etudiants.add(e);
	        }

	        ps.close();
	    } catch (Exception e) {
	        System.out.println("Erreur SQL : " + e.getMessage());
	    }

	    return etudiants;
	}

	
	/* méthode permettant d'ajouter un étudiant e   */
	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Etudiant addEtudiant(Etudiant e) {
	    try {
	       
	        String sql = "INSERT INTO Etudiant(id, nom, score) VALUES (?, ?, ?)";
	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setInt(1, e.getId());      
	        ps.setString(2, e.getName());  
	        ps.setInt(3, e.getScore());    

	        ps.executeUpdate();
	        ps.close();

	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }

	    return e; 
	}

	
	/* méthode permettant d'ajouter un étudiant d'identifiant "id", du nom "name" et du score "score"   */
	@POST
	@Path("createQuery/{id}/{name}/{score}")
	public void addEtudiantQuery1(@PathParam("id") int identifiant, 
			@PathParam("name") String name, 
			@PathParam("score") int score)
	{
	    try {
	       
	        String sql = "INSERT INTO Etudiant(id, nom, score) VALUES (?, ?, ?)";
	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setInt(1,identifiant);      
	        ps.setString(2,name);  
	        ps.setInt(3,score);    

	        ps.executeUpdate();
	        ps.close();

	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }

	    
	}
	
	/* méthode permettant d'ajouter un étudiant d'identifiant "id", du nom "name" et du score "score"   */
	@POST
	@Path("createQuery2/{id}")
	public void addEtudiantQuery12(@PathParam("id") int identifiant, 
			@QueryParam("name") String name, 
			@QueryParam("score") int score)
	{
	    try {
	       
	        String sql = "INSERT INTO Etudiant(id, nom, score) VALUES (?, ?, ?)";
	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setInt(1,identifiant);      
	        ps.setString(2,name);  
	        ps.setInt(3,score);    

	        ps.executeUpdate();
	        ps.close();

	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }

	    
	}
	
	/* méthode permettant d'ajouter un étudiant d'identifiant "id", du nom "name" et du score "score"   */
	@POST
	@Path("createQuery1")
	public void addEtudiantQuery(@QueryParam("id") int identifiant, 
			@QueryParam("name") String name, 
			@QueryParam("score") int score)
	{
	    try {
	       
	        String sql = "INSERT INTO Etudiant(id, nom, score) VALUES (?, ?, ?)";
	        PreparedStatement ps = con.pr4epareStatement(sql);

	        ps.setInt(1,identifiant);      
	        ps.setString(2,name);  
	        ps.setInt(3,score);    

	        ps.executeUpdate();
	        ps.close();

	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }

	    
	}
	
	@PUT
    @Path("update")
    public void updateEtudiant(@QueryParam("id") int id,
                               @QueryParam("score") int score) {
        try {
            String sql = "UPDATE Etudiant SET score ="+score+" WHERE id ="+id;
            PreparedStatement ps = con.prepareStatement(sql);

            ps.executeUpdate();

           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	/*méthode permettant de modifier les données de l'étudiant et*/
	@PUT
	@Path("update2")
	@Consumes(MediaType.APPLICATION_JSON)
	public void upadteEtudiant2(Etudiant et)

	{try {
        String sql = "UPDATE Etudiant SET score ="+et.getScore()+" WHERE id ="+et.getId();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.executeUpdate();

       
    } catch (Exception ex) {
        ex.printStackTrace();
    }
		
	
	}
	
	/* méthode permettant de supprimer un étudiant d'identifiant id */
	@DELETE
	@Path("delete")
	public void deleteEtudiant(@QueryParam("id") int id)

	{try {
        String sql = "DELETE FROM  Etudiant WHERE id ="+id;
        PreparedStatement ps = con.prepareStatement(sql);

        ps.executeUpdate();

       
    } catch (Exception ex) {
        ex.printStackTrace();
    }
	}
	
	/* méthode permettant de supprimer un étudiant d'identifiant id */
	@DELETE
	@Path("delete2/{id}")
	public void deleteEtudiant3(@PathParam("id") int id)

	{
		try {
	        String sql = "DELETE FROM  Etudiant WHERE id ="+id;
	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.executeUpdate();

	       
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	
	/* méthode permettant de supprimer l'étudiant et */
	@DELETE
	@Path("deleteObj")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteEtudiant(Etudiant et)

	{try {
        String sql = "DELETE FROM  Etudiant WHERE id ="+et.getId();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.executeUpdate();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
	}
	

}
