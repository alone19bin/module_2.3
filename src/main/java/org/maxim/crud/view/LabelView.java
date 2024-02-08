package org.maxim.crud.view;

import org.maxim.crud.controller.LabelController;
import org.maxim.crud.model.Label;
import org.maxim.crud.model.Status;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LabelView {

    private final Scanner scanner = new Scanner(System.in);
    LabelController labelController = new LabelController();

    public void consoleStart() {
        boolean isExit = false;
        while (true) {
            Integer views = scanner.nextInt();
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
                    isExit = true;
                    break;
                default:
                    System.out.println("Error");
                    break;
            }
            if (isExit)
                break;
        }
        scanner.close();
    }

    public void create() {
        Label createdLabel = new Label();
        System.out.println("Create lable");
        createdLabel.setStatus(Status.ACTIVE);
        System.out.println("Enter name");
        String name = scanner.nextLine();
        createdLabel.setName(name);
        try {
            labelController.createlabel(createdLabel);
            System.out.println("Label created: " + createdLabel);
        } catch (Exception e) {
            System.out.println("Enter in label create");
        }
    }


    public void update() {
        try {
            Integer id = Integer.parseInt(scanner.nextLine());
            Label labelToUpdate = labelController.getLabel(id);
                System.out.println("Enter new name: ");
                String name = scanner.nextLine();
                labelToUpdate.setName(name);
                labelController.updateLabel(labelToUpdate);
                System.out.println("Label updated: " + labelToUpdate);
        } catch (Exception e) {
            e.getMessage();
            System.out.println("Error in label update");
        }
    }


    public void delete() {
        try {
            Integer id = Integer.parseInt(scanner.nextLine());
            Label labelToDelete = labelController.getLabel(id);
            if (labelToDelete.getId() == -1) {
                System.out.println("No such label with ID " + id);
            } else {
                labelController.deleteById(id);
                System.out.println("Label deleted: " + labelToDelete);
            }
        } catch (Exception e) {
            e.getMessage();
            System.out.println("Error while deleting label with ID");
        }
    }


    public void read() {
        try {
            List<Label> labels = labelController.getLabels();
            System.out.println("Labels");
            labels.forEach(System.out::println);
        } catch (Exception e) {
            e.getMessage();
            System.out.println("Error in label read");
        }
    }


}
