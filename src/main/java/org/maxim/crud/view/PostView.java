package org.maxim.crud.view;

import org.maxim.crud.controller.LabelController;
import org.maxim.crud.controller.PostController;
import org.maxim.crud.model.Label;
import org.maxim.crud.model.Post;
import org.maxim.crud.model.Status;

import java.text.SimpleDateFormat;
import java.util.*;

public class PostView {
    private final PostController postController = new PostController();
    private final LabelController labelController = new LabelController();
    private final Scanner sc = new Scanner(System.in);


    public void consoleStart() {
        int views;
        do {
            System.out.print("Select: \n" +
                    "1. Create Post\n" +
                    "2. Update Post\n" +
                    "3. Delete Post\n" +
                    "4. Read Post\n" +
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
        Post createdPost = new Post();
        System.out.println(" New Post ");
        System.out.print("Content: ");
        String postContent = sc.nextLine();


       // createdPost.setCreated(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime()));

        System.out.println("Add labels to the post  ID");
        new LabelView().showAllLabels();
        List<Label> postLabels = new ArrayList<>();
        for (Integer chosenLabelId; ; ) {
            System.out.print("Label ID to add: ");
            chosenLabelId = sc.nextInt();
            if (chosenLabelId == -1) break;
            Label labelToAdd = labelController.getById(chosenLabelId);
            if (labelToAdd != null) {
                postLabels.add(labelToAdd);
            } else {
                System.out.println("Wrong ID");
            }
        }
        sc.nextLine();
        Post newPost = postController.add(postContent, postLabels);
        if (newPost != null) {
            System.out.println("Added 1 new pos with ID " + newPost.getId());
        } else {
            System.out.println("Error occured while adding new post.");
        }
    }


    public void update() {
        System.out.println("Edit Post ");
        System.out.print("Edit post with ID: ");
        Integer id = sc.nextInt();
        sc.nextLine();

        Post post = postController.getById(id);
        if (post != null) {

            System.out.println("Current content: " + post.getContent() + " Enter new Content");
            String newContent = sc.nextLine();

            System.out.println("Uddate time " + post.getUpdated());
            System.out.print("Current labels: ");
            List<Label> currentLabels = post.getLabels();
            for (Label label : currentLabels) {
                System.out.print(label.getName());
            }
            System.out.println();
            System.out.println("Enter new label IDs below");
            List<Label> newPostLabels = new ArrayList<>();
            for (Integer chosenLabelId; ; ) {
                System.out.print("Label ID to add: ");
                chosenLabelId = sc.nextInt();
                if (chosenLabelId == -1) break;
                Label labelToAdd = labelController.getById(chosenLabelId);
                if (labelToAdd != null)
                    newPostLabels.add(labelToAdd);
                else
                    System.out.println("Wrong ID");
            }
            sc.nextLine();


            System.out.println("Current status: " + post.getStatus());
            String action = (post.getStatus() == Status.ACTIVE) ? "Delete" : "Restore";
            System.out.print(action + " yes or no ");
            String statusReply = sc.nextLine();
            boolean changeStatus = "yes".equalsIgnoreCase(statusReply.trim());
            Post updatedPost = postController.update(post, newContent, newPostLabels, changeStatus);
            if (updatedPost != null) {
                System.out.println("Updating post with ID " + updatedPost.getId() + ": OK");
            } else {
                System.out.println("Can't update or write to DB");
            }
        } else {
            System.out.println("ID " + id + " is not found");

        }
    }


    public void delete() {
        System.out.println("Delete Post");
        System.out.print("ID to delete: ");
        Integer id = sc.nextInt();
        sc.nextLine();
        if (postController.deleteById(id)) {
            System.out.println(id + " is deleted");
        } else {
            System.out.println("ID " + id + "  not found");
        }
    }


    public void read() {
        System.out.println("Detailed POST info by ID");
        System.out.print("Show post with ID: ");
        Integer id = sc.nextInt();
        sc.nextLine();
        System.out.printf("ID" +  " " + "TITLE" + " " + "STATUS");
        Post post = postController.getById(id);
        if (post != null) {
            System.out.println(post.getId() + post.getContent() + post.getStatus());

            System.out.print("Labels: ");
            List<Label> postLabels = post.getLabels();
            for (Label label : postLabels) {
                System.out.print(label.getName());
            }
            System.out.println();

            System.out.println("Content");
            System.out.print(post.getContent());
        } else {
            System.out.println("ID " + id + " not found");
        }

    }

    public void showAllPosts() {
        System.out.println("List of all Posts");
        System.out.printf("ID", "TITLE", "STATUS");

        List<Post> posts = postController.getAll();

        if (posts != null && !posts.isEmpty()) {
            for (var post : posts)
                System.out.printf(post.getId() + " " + post.getContent() + " " + post.getStatus());
        } else {
            System.out.println("List is null");
        }


    }
}






