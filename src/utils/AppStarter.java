package utils;

import controllers.AppController;
import models.AppModel;
import views.AppView;

public class AppStarter {
    public static void startApp() {
        AppModel appModel = new AppModel();
        AppView appView = new AppView(appModel);
        AppController controller = new AppController(appModel, appView);
        controller.runApp();
    }
}
