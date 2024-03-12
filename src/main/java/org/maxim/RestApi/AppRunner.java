package org.maxim.RestApi;

import org.maxim.RestApi.utils.DataBaseRun;


public class AppRunner {
    public static void main(String[] args) {
        DataBaseRun.migrateDatabase();
        System.out.println("Running");
    }
}
