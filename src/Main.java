import java.sql.*;
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

            testReizigerDAO(new ReizigerDAOPsql(myConn));

            myRS.close();
            myStmt.close();
            myConn.close();
        } catch (
                Exception e) {
            System.err.println(e);
        }
    }

    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {
        System.out.println("\n---------- Test ReizigerDAO -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(77, "S", "", "Boers",
                java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");

        // Reiziger ophalen met id
        System.out.println("[Test] Reiziger met id 3: " + rdao.findById(3));

        // Reiziger ophalen met geboortedatum
        System.out.println("[Test] Reiziger(s) met geboortedatum 2002-12-03: " + rdao.findByGbdatum("2002-12-03"));

        // Update achternaam van een reiziger
        Reiziger reiziger = rdao.findById(1);
        System.out.print("[Test] Eerst: " + reiziger.getAchternaam() + ", ");
        reiziger.setAchternaam("Maas");
        rdao.update(reiziger);
        System.out.println("na ReizigerDAO.update(): " + rdao.findById(1).getAchternaam());

        // Update ongedaan maken
        reiziger.setAchternaam("Rijn");
        rdao.update(reiziger);

        // Reiziger verwijderen
        System.out.print("[Test] Eerst: " + rdao.findById(77) + ", ");
        rdao.delete(rdao.findById(77));
        System.out.println("na rdao.delete(): " + rdao.findById(77));
    }
}
