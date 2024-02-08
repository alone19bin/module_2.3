package org.maxim.crud.view;

import org.maxim.crud.controller.PostController;
import org.maxim.crud.controller.WriterController;
import org.maxim.crud.model.Status;
import org.maxim.crud.model.Writer;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class WriterView {
    private final WriterController writerController = new WriterController();
    private final Scanner scanner = new Scanner(System.in);


    public void consoleStart() {
        int views;
        do {
            System.out.print("Select: \n" +
                    "1. Create Writer\n" +
                    "2. Update Writer\n" +
                    "3. Delete Writer\n" +
                    "4. Read Writer\n" +
                    "5. Add Post to Writer\n" +
                    "6. Exit. \n");
            views = scanner.nextInt();
            scanner.nextLine();
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
                case 6:
                    break;
                default:
                    System.out.println("Select correct number " + views);

            }
        }
        while (views != 0);
        scanner.close();
    }


    public void create() {
        Writer createdWriter = new Writer();
        System.out.println("Enter Firstname");
        String firstName = scanner.nextLine();
        System.out.println("Enter second name: ");
        String lastName = scanner.nextLine();
        createdWriter.setFirstName(firstName);
        createdWriter.setLastName(lastName);
        createdWriter.setStatus(Status.ACTIVE);
        try {
            writerController.createWriter(createdWriter);
            System.out.println("Writer created");
        } catch (Exception e) {
            System.out.println("Error in create writer");
        }
    }


    public void update() {
        read();
        System.out.println("Update writer");
        Long id = Long.valueOf(Integer.parseInt(scanner.nextLine()));
        try {
            Writer writer = writerController.getWriter(id);
            System.out.println("Enter firstname ");
            String firstName = scanner.nextLine();
            System.out.println("Enter lastname: ");
            String lastName = scanner.nextLine();
            writer.setFirstName(firstName);
            writer.setLastName(lastName);
            writerController.updateWriter(writer);
            System.out.println("Writer updated");
        } catch (Exception e) {
            System.out.println("Error in writer update");
        }
    }


    public void delete() {
        read();
        System.out.println("Writers");
        System.out.println("Enter id to delete writer");
        Integer id = Integer.parseInt(scanner.nextLine());
        try {
            writerController.deleteById(id);
            System.out.println("Writer deleted");
        } catch (Exception e) {
            System.out.println("Error in delete writer");
        }
    }


    public void read() {
        List<Writer> writers = null;
        try {
            System.out.println("Writers");
            writers = writerController.getWriters();
        } catch (Exception e) {
            System.out.println("Error loading writers");
        }
        System.out.println("Writers");
        if (writers != null) {
            writers.sort(Comparator.comparing(Writer::getId));
            writers.forEach(System.out::println);
        }


    }
}
