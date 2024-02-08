package org.maxim.crud.view;

import org.maxim.crud.controller.LabelController;
import org.maxim.crud.controller.PostController;
import org.maxim.crud.controller.WriterController;
import org.maxim.crud.model.Label;
import org.maxim.crud.model.Post;
import org.maxim.crud.model.Status;
import org.maxim.crud.model.Writer;
import org.maxim.crud.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.*;

public class PostView {
    private final PostController postController = new PostController();
    private final LabelController labelController = new LabelController();
    private final WriterController writerController = new WriterController();
    private final Scanner scanner = new Scanner(System.in);

    public void consoleStart() {
        int views;
        do {
            System.out.print("Select: \n" +
                    "1. Create Post\n" +
                    "2. Update Post\n" +
                    "3. Delete Post\n" +
                    "4. Read Post\n" +
                    "5. Exit. \n");
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
                case 5:
                    break;
                default:
                    System.out.println("Select correct number " + views);

            }
        }
        while (views != 0);
        scanner.close();
    }


    public void create() {
        Post createdPost = new Post();
        System.out.println("Creating post");
        createdPost.setStatus(Status.ACTIVE);
        String content = scanner.nextLine();
        createdPost.setContent(content);
        createdPost.setCreated(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime()));
        createdPost.setUpdated("NEW");

        List<Label> postLabels = labelController.getLabels();
        if(postLabels != null){
            postLabels.sort(Comparator.comparing(Label::getId));
            postLabels.forEach(System.out::println);
        }
        System.out.println("Enter ID of labels to add to the post:");
        List<Label> labels = new ArrayList<>();
        addLabelsToPost(labels);
        createdPost.setLabels(labels);

        List<Writer> writers = writerController.getWriters();
        if (writers != null) {
            writers.sort(Comparator.comparing(Writer::getId));
            writers.forEach(System.out::println);
        }
        System.out.println("Enter writer ID: ");
        int writerId = Integer.parseInt(scanner.nextLine());
        Writer writer = writerController.getWriter(writerId);
        createdPost.setWriter(writer);

        try {
            postController.createPost(createdPost);
            System.out.println("Post created");
        } catch (Exception e) {
            System.out.println("Error while creating post");
        }
        }


    public void update() {
        System.out.println("Update post");
        Integer id = Integer.parseInt(scanner.nextLine());
        try{
            Optional<Post> post = postController.getPost(id);
            post.setUpdated(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime()));
            System.out.println("Enter new content: ");
            String content = scanner.nextLine();
            post.setContent(content);
            postController.updatePost(post);
            System.out.println("Post updated: " + post);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error post update");
        }

    }


    public void delete() {
        System.out.println("Enter id to delete post");
        Integer id = Integer.parseInt(scanner.nextLine());
        try{
            postController.deleteById(id);
            System.out.println("Post deleted");
        } catch (Exception e){
            System.out.println("Error in post deleted");
        }
    }


    public void read() {
        List<Post> posts = null;
        System.out.println("Read post");
        if(posts != null){
            posts.sort(Comparator.comparing(Post::getId));
            posts.forEach(System.out::println);
        }

    }

    private void addLabelsToPost(List<Label> postLabels){
        boolean isContinue = true;
        while(isContinue) {
            Integer labelId = Integer.parseInt(scanner.nextLine());
            postLabels.add(labelController.getLabel(labelId));
            System.out.println("Do you want to add one more label? Y/N");
            if (scanner.nextLine().equalsIgnoreCase("n")){
                isContinue = false;
            } else {
                System.out.println("Enter id of label to add: ");
            }
        }
    }



}






