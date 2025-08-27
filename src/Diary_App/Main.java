package Diary_App;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Diaries diaries = new Diaries();
            boolean running = true;

            while (running) {
                System.out.println("==Diaries App==");
                System.out.println("1. Add a new Diary");
                System.out.println("2. Manage Diary");
                System.out.println("3. Delete Diary");
                System.out.println("4. Exit");
                System.out.println("Enter your choice: ");
                int choice = 0;
                
                try{
                      choice = scanner.nextInt();
                }
                catch (InputMismatchException e) {
                    System.out.println("Error: Invalid choice");
                    continue;
                }
                finally{
                    scanner.nextLine();
                }


                     switch (choice) {
                         case 1:
                             System.out.println("Enter username for diary: ");
                             String username = scanner.nextLine();
                             System.out.println("Enter password for diary: ");
                             String password = scanner.nextLine();
                             diaries.add(username, password);
                             System.out.println("Diary added successfully");
                             break;
                         case 2:
                             System.out.println("Enter username for diary: ");
                             String manageUsername = scanner.nextLine();
                             System.out.println("Enter password for diary: ");
                             String managePassword = scanner.nextLine();
                             try {
                                 Diary diary = diaries.findByUsername(manageUsername);
                            diary.verifyPassword(managePassword);
                            diary.unlockDiary(managePassword);
                            boolean md = true;
                            while (md) {
                                System.out.println("== Manage " + diary.getUsername() + "'s Diary ==");
                                System.out.println("1. Lock Diary");
                                System.out.println("2. Create new entry");
                                System.out.println("3. Delete entry");
                                System.out.println("4. Update entry");
                                System.out.println("5. View entries");
                                System.out.println("6. Exit");
                                System.out.print("Enter your choice: ");

                                int option = scanner.nextInt();
                                scanner.nextLine(); // Consume newline

                                switch (option) {
                                    case 1:
                                        diary.lockDiary();
                                        System.out.println("Diary locked successfully.");
                                        break;
                                    case 2:
                                        System.out.print("Enter title: ");
                                        String entryTitle = scanner.nextLine();
                                        System.out.print("Enter body: ");
                                        String entryBody = scanner.nextLine();
                                        try {
                                            diary.createEntry(entryTitle, entryBody);
                                            System.out.println("Entry created successfully.");
                                        } catch (Exception e) {
                                            System.out.println("Error: " + e.getMessage());
                                        }
                                        break;
                                    case 3:
                                        System.out.print("Enter entry ID to delete: ");
                                        int entryId = scanner.nextInt();
                                        scanner.nextLine(); // Consume newline
                                        try {
                                            diary.deleteEntry(entryId);
                                            System.out.println("Entry deleted successfully.");
                                        } catch (Exception e) {
                                            System.out.println("Error: " + e.getMessage());
                                        }
                                        break;
                                    case 4:
                                        System.out.print("Enter entry ID to update: ");
                                        int entryIdForUpdate = scanner.nextInt();
                                        scanner.nextLine(); // Consume newline
                                        System.out.print("Enter new entry title: ");
                                        String newTitle = scanner.nextLine();
                                        System.out.print("Enter new entry body: ");
                                        String newBody = scanner.nextLine();
                                        try {
                                            diary.updateEntry(entryIdForUpdate, newTitle, newBody);
                                            System.out.println("Entry updated successfully.");
                                        } catch (Exception e) {
                                            System.out.println("Error: " + e.getMessage());
                                        }
                                        break;
                                    case 5:
                                        try {
                                            diary.viewEntriesInDiary();
                                        } catch (Exception e) {
                                            System.out.println("Error: " + e.getMessage());
                                        }
                                        break;
                                    case 6:
                                        md = false;
                                        break;
                                    default:
                                        System.out.println("Invalid choice.");
                                        break;
                                }
                            }
                             } catch (Exception e) {
                                 System.out.println("Error: " + e.getMessage());
                             }
                             break;
                         case 3:
                             System.out.print("Enter the username of the diary to delete: ");
                             String deleteUsername = scanner.next();
                             System.out.print("Enter the password for the diary: ");
                             String deletePassword = scanner.next();
                             try {
                                 diaries.delete(deleteUsername, deletePassword);
                                 System.out.println("Diary deleted successfully!");
                             } catch (Exception e) {
                                 System.out.println("Error: " + e.getMessage());
                             }
                             break;
                         case 4:
                             System.out.println("Exiting the Diaries app");
                             running = false;
                             break;
                         default:
                             System.out.println("Invalid choice");
                             break;
                     }
            }
    }
 }


