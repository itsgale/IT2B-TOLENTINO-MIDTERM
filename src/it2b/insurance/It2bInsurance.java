package it2b.insurance;

import java.util.Scanner;

public class It2bInsurance {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int choice;
            String response;
            do {
                System.out.println("1. ADD INSURANCE");
                System.out.println("2. VIEW INSURANCE");
                System.out.println("3. UPDATE INSURANCE");
                System.out.println("4. DELETE INSURANCE");
                System.out.println("5. EXIT");

                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addInsurance(sc);
                        break;
                    case 2:
                        viewInsurance();
                        break;
                    case 3:
                        viewInsurance();
                        updateInsurance(sc);
                        viewInsurance();
                        break;
                    case 4:
                        viewInsurance();
                        deleteInsurance(sc);
                        viewInsurance();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

                System.out.println("Do you want to continue? (yes/no):");
                response = sc.next();
            } while (response.equalsIgnoreCase("yes"));
            System.out.println("Thank you, See you soonest!");
        }
    }

    public static void addInsurance(Scanner sc) {
        config conf = new config();
        System.out.print("Enter Insurance Name: ");
        String iname = sc.next();
        System.out.print("Enter Coverage Amount: ");
        String cammount = sc.next();
        System.out.print("Enter Policy Number: ");
        String pnumber = sc.next();

        String sql = "INSERT INTO tbl_insurance (i_name, c_ammount, p_number) VALUES (?, ?, ?)";
        conf.addInsurance(sql, iname, cammount, pnumber);
    }

    public static void viewInsurance() {
        config conf = new config();
        String sqlQuery = "SELECT * FROM tbl_insurance";
        String[] columnHeaders = {"Insurance ID", "Insurance Name", "Coverage Amount", "Policy Number"};
        String[] columnNames = {"i_id", "i_name", "c_ammount", "p_number"};
        conf.viewInsurance(sqlQuery, columnHeaders, columnNames);
    }

    public static void updateInsurance(Scanner sc) {
        config conf = new config();

        System.out.print("Enter the ID of Insurance to edit: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new insurance name: ");
        String iname = sc.nextLine();
        System.out.print("Enter new coverage amount: ");
        String cammount = sc.nextLine();
        System.out.print("Enter new policy number: ");
        String pnumber = sc.nextLine();

        String sql = "UPDATE tbl_insurance SET i_name = ?, c_ammount = ?, p_number = ? WHERE i_id = ?";
        conf.updateInsurance(sql, iname, cammount, pnumber, id);
        System.out.println("Insurance updated successfully.");
    }

    public static void deleteInsurance(Scanner sc) {
        config conf = new config();
        System.out.print("Enter the ID of Insurance to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM tbl_insurance WHERE i_id = ?";
        conf.deleteInsurance(sql, id);
        System.out.println("Insurance deleted successfully.");
    }
}