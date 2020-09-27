import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        try {
            Connection myConn = DriverManager.getConnection("jdbc:postgresql:ovchip",
                    "postgres",
                    "erniet");

            Statement myStmt = myConn.createStatement();
            ResultSet myRS = myStmt.executeQuery("select * from reiziger");
            StringBuilder reizigers = new StringBuilder();
            while (myRS.next()) {
                reizigers.append(myRS.getString("voorletters")).append(" ");
                if (myRS.getString("tussenvoegsel") != null) {
                    reizigers.append(myRS.getString("tussenvoegsel")).append(" ");
                }
                reizigers.append(myRS.getString("achternaam")).append(" ")
                        .append(myRS.getString("geboortedatum")).append("\n");
            }

            System.out.println("Alle reizigers: \n" + reizigers);

            myRS.close();
            myStmt.close();

//            testReizigerDAO(new ReizigerDAOPsql(myConn));
//
//            testAdresDAO(new AdresDAOPsql(myConn));

            testProductDAO(new ProductDAOPsql(myConn));

            myConn.close();

        } catch (
                Exception e) {
            System.err.println(e);
        }
    }

//    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
//        System.out.println("\n---------- Test ReizigerDAO -------------");
//
//        // Haal alle reizigers op uit de database
//        List<Reiziger> reizigers = rdao.findAll();
//        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
//        for (Reiziger r : reizigers) {
//            System.out.println(r);
//        }
//        System.out.println();
//
//        // Maak een nieuwe reiziger aan en persisteer deze in de database
//        String gbdatum = "1981-03-14";
//        Reiziger sietske = new Reiziger(77, "S", "", "Boers",
//                java.sql.Date.valueOf(gbdatum));
//        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
//        rdao.save(sietske);
//        reizigers = rdao.findAll();
//        System.out.println(reizigers.size() + " reizigers\n");
//
//
//
//        rdao.save(new Reiziger(12, "V", "van", "Sloot", Date.valueOf("1993-03-19")));
//
//
//
//        // Reiziger ophalen met id
//        System.out.println("[Test] Reiziger met id 3: " + rdao.findById(3));
//
//        // Reiziger ophalen met geboortedatum
//        System.out.println("[Test] Reiziger(s) met geboortedatum 2002-12-03: " + rdao.findByGbdatum("2002-12-03"));
//
//        // Update achternaam van een reiziger
//        Reiziger reiziger = rdao.findById(1);
//        System.out.print("[Test] Eerst: " + reiziger.getAchternaam() + ", ");
//        reiziger.setAchternaam("Maas");
//        rdao.update(reiziger);
//        System.out.println("na ReizigerDAO.update(): " + rdao.findById(1).getAchternaam());
//
//        // Update ongedaan maken
//        reiziger.setAchternaam("Rijn");
//        rdao.update(reiziger);
//
//        // Reiziger verwijderen
//        System.out.print("[Test] Eerst: " + rdao.findById(77) + ", ");
//        rdao.delete(rdao.findById(77));
//        System.out.println("na rdao.delete(): " + rdao.findById(77));
//    }
//
//    private static void testAdresDAO(AdresDAOPsql adao) {
//        System.out.println("\n---------- Test AdresDAO -------------");
//
//        // Maak een nieuw adres aan en persisteer deze in de database
//        List<Adres> adressen = adao.findAll();
//        Adres Utrecht = new Adres(21, "3718EH", "17", "Utrechtsestraat",
//                "Utrecht",  new Reiziger(12, "S", "van", "Hoek",
//                Date.valueOf("1982-12-09")));
//        System.out.print("[Test] Eerst " + adressen.size() + " adressen, na AdresDAO.save() ");
//        adao.save(Utrecht);
//        adressen = adao.findAll();
//        System.out.println(adressen.size() + " adressen\n");
//
//        // Update straat van een adres
//        Adres adres = adao.findById(1);
//        System.out.print("[Test] Eerst: " + adres.getStraat() + ", ");
//        adres.setStraat("weg");
//        adao.update(adres);
//        System.out.println("na AdresDAO.update(): " + adao.findById(1).getStraat());
//
//        // Update ongedaan maken
//        adres.setStraat("Visschersplein");
//        adao.update(adres);
//
//        // Adres verwijderen
//        System.out.print("[Test] Eerst: " + adao.findById(21) + ", ");
//        adao.delete(adao.findById(21));
//        System.out.println("na adao.delete(): " + adao.findById(21));
//
//        // Adres ophalen met reiziger id
//        System.out.println("[Test] Adres vinden met reiziger ID 1: " + adao.findByReiziger(new Reiziger(1, "G", "van", "Rijn", Date.valueOf("2002-09-17"))));
//    }

    private static void testProductDAO(ProductDAOPsql pdao) {
        List<Product> producten = pdao.findAll();
        Product product = new Product(7, "testProduct1", "test product 1",
                19.99, new ArrayList<OVChipkaart>());

        product.addOVChipkaart(new OVChipkaart(35.283, Date.valueOf("2018-05-31"), 2,
                25.50, null, new ArrayList<Product>()));

        System.out.print("[Test] Eerst " + producten.size() + " producten, na ProductDAO.save() ");
        pdao.save(product);
        producten = pdao.findAll();
        System.out.println(producten.size() + " producten\n");

        // Nieuw product verwijderen
        pdao.delete(product);
    }
}
