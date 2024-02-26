package org.maxim.crud.view;

import org.maxim.crud.controller.PostController;
import org.maxim.crud.controller.WriterController;
import org.maxim.crud.model.Post;
import org.maxim.crud.model.Status;
import org.maxim.crud.model.Writer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class WriterView {
    private final WriterController writerController = new WriterController();
    private final Scanner sc = new Scanner(System.in);
    private final PostController postController = new PostController();
    public void consoleStart() {

        final String MENU = """
            
                1) Create 
                2) update 
                3) delete 
                4) read
              
                0) Back to Main Menu""";
        int select;

        do {
            System.out.print(MENU + "\n ");
            select = sc.nextInt();
            sc.nextLine();
            switch (select) {
                case 1 -> create();
                case 2 -> update();
                case 3 -> delete();
                case 4 -> read();
            }
        } while (select != 0);
    }


    public void create() {
        System.out.println("Create new Writer");
        System.out.print("Last name: ");
        String lastName = sc.nextLine();
        System.out.print("First name: ");
        String firstName = sc.nextLine();

        System.out.println("Add posts for the writer by ID");
        new PostView().showAllPosts();
        List<Post> writerPosts = new ArrayList<>();
        for (Integer chosenPostId;;) {
            System.out.print("Post ID to add: ");
            chosenPostId = sc.nextInt();
            if (chosenPostId == -1) break;
            Post postToAdd = postController.getById(chosenPostId);
            if (postToAdd != null) {
                writerPosts.add(postToAdd);
            } else {
                System.out.println("Wrond ID");
            }
        }
        sc.nextLine();

        Writer addedWriter = writerController.add(lastName, firstName, writerPosts);
        if (addedWriter != null) {
            System.out.println("Added 1 new writer with ID " + addedWriter.getId());
        } else {
            System.out.println("Error adding new writer.");
        }
    }


    public void update() {
        System.out.print("Edit writer with ID: ");
        Integer id = sc.nextInt();
        sc.nextLine();

        Writer writer = writerController.getById(id);
        if (writer != null) {
            System.out.println("Current lastName: " + writer.getLastName());
            System.out.print("New last name: ");
            String newLastName = sc.nextLine();

            System.out.println("Current firstName: " + writer.getFirstName());
            System.out.print("New first name: ");
            String newFirstName = sc.nextLine();

            System.out.println("Current posts");
            System.out.println("ID " +  " TITLE");
            List<Post> writerPosts = writer.getPosts();
            for (var post : writerPosts) {
                System.out.printf(post.getId() + " " +  post.getContent());
            }
            System.out.println();
            System.out.println("Enter new post ID");
            List<Post> newWriterPosts = new ArrayList<>();
            for (Integer chosenPostId;;) {
                System.out.print("Post ID to add: ");
                chosenPostId = sc.nextInt();
                if (chosenPostId == -1) break;
                Post postToAdd = postController.getById(chosenPostId);
                if (postToAdd != null) {
                    newWriterPosts.add(postToAdd);
                } else {
                    System.out.println("Wrong ID");
                }
            }
            sc.nextLine();

            System.out.println("Current status: " + writer.getStatus());
            String action = (writer.getStatus() == Status.ACTIVE) ? "Delete" : "Restore";
            System.out.print(action + " yes or no: ");
            String statusReply = sc.nextLine();
            boolean changeStatus = "yes".equalsIgnoreCase(statusReply.trim());

            Writer updatedWriter = writerController.update(writer, newLastName, newFirstName, newWriterPosts, changeStatus);
            if (updatedWriter != null) {
                System.out.println("Updating writer ID " + updatedWriter.getId());
            } else {
                System.out.println("Couldn't update");
            }
        } else {
            System.out.println("ID" + id + " not found");
        }
    }


    public void delete() {
        System.out.println("Delete Post");
        System.out.print("ID to delete: ");
        Integer id = sc.nextInt();
        sc.nextLine();
        if (writerController.deleteById(id)) {
            System.out.println(id + "is deleted");
        } else {
            System.out.println("ID " + id + "  not found");
        }
    }


    public void read() {
        System.out.print("Show writer with ID: ");
        Integer id = sc.nextInt();
        sc.nextLine();
        System.out.printf("ID " +  " NAME " +  " STATUS");
        Writer writer = writerController.getById(id);
        if (writer != null) {
            System.out.println(writer.getId() + "   " + writer.getFirstName() + "   " + writer.getLastName() + "   " +  writer.getStatus());
        } else {
            System.out.println("ID " + id + " not found");
        }

    }
}
