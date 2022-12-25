package controllers;

import models.AppModel;

import utils.Users;
import views.AppView;

public class AppController {

    AppModel model;
    AppView view;

    public AppController(AppModel model, AppView view) {
        this.model = model;
        this.view = view;
    }

    public void runApp() {
        filterChoice(view.doChoice());
    }

    private void filterChoice(int choice) {
        switch (choice) {
            case 1 -> model.createUser();
            case 2 -> model.readUsers();
            case 3 -> model.updateUser();
            case 4 -> model.deleteUser();
            case 0 -> {
                String output = Users.CLOSE_APP_MSG;
                view.getOutput(output, choice);
            }
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        }
    }
}
