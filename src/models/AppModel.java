package models;
import controllers.UserDeleteController;
import controllers.UserCreateController;
import controllers.UserReadController;
import controllers.UserUpdateController;
import views.UserCreateView;
import views.UserDeleteView;
import views.UserReadView;
import views.UserUpdateView;

public class AppModel {

    private int choice;

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public void createUser() {
        UserCreateModel model = new UserCreateModel();
        UserCreateView view = new UserCreateView();
        UserCreateController controller = new UserCreateController(model, view);
        controller.createUser();
    }

    public void readUsers() {
        UserReadModel model = new UserReadModel();
        UserReadView view = new UserReadView();
        UserReadController controller = new UserReadController(model, view);
        controller.readUsers();
    }

    public void updateUser() {
        UserUpdateModel model = new UserUpdateModel();
        UserUpdateView view = new UserUpdateView();
        UserUpdateController controller = new UserUpdateController(model, view);
        controller.updateUser();
    }

    public void deleteUser() {
        UserDeleteModel model = new UserDeleteModel();
        UserDeleteView view = new UserDeleteView();
        UserDeleteController controller = new UserDeleteController(model, view);
        controller.deleteUser();
    }
}
