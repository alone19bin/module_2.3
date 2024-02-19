package org.maxim.crud.view;

import java.util.Scanner;

public class MainView {
    private final LabelView labelView = new LabelView();
    private final PostView postView = new PostView();
    private final WriterView writerView = new WriterView();
    private final Scanner sc = new Scanner(System.in);

    public void run() {
        final String MENU = """
   
                1) Labels
                2) Posts
                3) Writers
                0) Exit""";

        int select;
        do {
            System.out.print(MENU + "\nSelect item: ");
            select = sc.nextInt();
            switch (select) {
                case 1 -> labelView.consoleStart();
                case 2 -> postView.consoleStart();
                case 3 -> writerView.consoleStart();
            }
        } while (select != 0);
    }
}

