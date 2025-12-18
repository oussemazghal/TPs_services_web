package Productt.Products;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    Connection con = null;

    public ProductDAO() {
        String url = "jdbc:mysql://localhost:3306/productbase";
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

    /* Liste de tous les produits */
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Product p = new Product();

                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getFloat("price"));

                products.add(p);
            }

            st.close();
        } catch (Exception e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }

        return products;
    }

    /* Produit par ID */
    public Product getProduct(int id) {
        Product p = null;

        String sql = "SELECT * FROM product WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getFloat("price"));
            }

            ps.close();
        } catch (Exception e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }

        return p;
    }

    /* Recherche par nom */
    public List<Product> searchProductsByName(String name) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE name LIKE ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getFloat("price"));
                products.add(p);
            }

            ps.close();
        } catch (Exception e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }

        return products;
    }

    /* Ajouter produit */
    public void addProduct(Product p) {
        String sql = "INSERT INTO product(id, name, price) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, p.getId());
            ps.setString(2, p.getName());
            ps.setFloat(3, p.getPrice());

            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Mettre à jour */
    public void updateProduct(Product p) {
        String sql = "UPDATE product SET name=?, price=? WHERE id=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, p.getName());
            ps.setFloat(2, p.getPrice());
            ps.setInt(3, p.getId());

            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Supprimer */
    public void deleteProduct(int id) {
        String sql = "DELETE FROM product WHERE id=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}