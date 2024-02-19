package org.maxim.crud.view;

import org.maxim.crud.controller.LabelController;
import org.maxim.crud.model.Label;
import org.maxim.crud.model.Status;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LabelView {

    private final Scanner sc = new Scanner(System.in);
    private final LabelController labelController = new LabelController();

    public void consoleStart() {
        int views;
        do {
            System.out.print("Select: \n" +
                    "1. Create Label\n" +
                    "2. Update Label\n" +
                    "3. Delete Label\n" +
                    "4. Read Label\n" +
                    "5. Exit. \n");
            views = sc.nextInt();
            sc.nextLine();
            switch (views) {
                case 1:
                    create();
                    break;
                case 2:
                    update();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    read();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Select correct number " + views);

            }
        }
        while (views != 0);
        sc.close();
    }

    public void create() {
        System.out.println("New Label");
        System.out.print("Name: ");
        String labelName = sc.nextLine();
        Label newLabel = labelController.add(labelName);
        if (newLabel != null) {
            System.out.println("Add new label with ID " + newLabel.getId());
        } else {
            System.out.println("Error IN create new label.");
        }
    }


    public void update() {
        System.out.print("Edit label with ID: ");

        Integer id = sc.nextInt();
        sc.nextLine();

        Label label = labelController.getById(id);
        if (label != null) {
            System.out.println("Current name: " + label.getName());
            System.out.print("New name: ");
            String newName = sc.nextLine();


            System.out.println("Current status: " + label.getStatus());
            String action = (label.getStatus() == Status.ACTIVE) ? "Delete" : "Restore";
            System.out.print(action + "yes or no ");
            String statusReply = sc.nextLine();
            boolean changeStatus = "yes".equalsIgnoreCase(statusReply);
            Label updatedLabel = labelController.update(label, newName, changeStatus);
            if (updatedLabel != null) {
                System.out.println("Update label ID "+ updatedLabel.getId() + ": OK");
            } else {
                System.out.println("Couldn't update or write");
            }
        }
        else
            System.out.println("ID " + id + "  not found");
    }


    public void delete() {
        System.out.println("Delete Label");
        System.out.print("ID to delete: ");
        Integer id = sc.nextInt();
        sc.nextLine();
        if (labelController.deleteById(id)) {
            System.out.println(id + " is deleted");
        } else {
            System.out.println("ID " + id + "  not found");
        }
    }


    public void read() {
        System.out.print("Show label with ID: ");

        Integer id = sc.nextInt();
        sc.nextLine();
        System.out.printf("ID" +  " NAME " + " STATUS");

        Label label = labelController.getById(id);
        if (label != null) {
            System.out.printf( label.getId() + " " +  label.getName() + " "+ label.getStatus());
        } else {
            System.out.println("ID " + id + " not found");
        }
    }

   public void showAllLabels() {
        System.out.println("List of all Labels");
        System.out.printf( "ID " +  " NAME " +  " STATUS");
        List<Label> labels = labelController.getAll();
        if (labels != null && !labels.isEmpty()) {
            for (var label : labels) {
                System.out.printf(label.getId() + " " + label.getName() + " " + label.getStatus());
            }
        } else {
            System.out.println("List is null.");
        }
    }


}
