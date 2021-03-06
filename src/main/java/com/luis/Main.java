package com.luis;

import com.luis.controller.DetailController;
import com.luis.controller.LoginController;
import com.luis.controller.MainController;
import com.luis.db.DBOperater;
import com.luis.entity.Member;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main extends Application {

    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        this.primaryStage.setTitle("客户管理系统");

        initRootLayout();

        /**
         * 创建表
         */
        DBOperater.initDB();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/LoginScene.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image(
                    Main.class.getResourceAsStream("/image/crm.png")));
            primaryStage.show();

            LoginController loginController = loader.getController();
            loginController.setMainApp(this);
        } catch (IOException e) {
            logger.error("error", e);
        }
    }

    /**
     * 显示主窗口
     */
    public void showMainDialog() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/MainScene.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            MainController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            logger.error("error", e);
        }
    }

    /**
     * 显示客户账单
     * @param member
     */
    public void showDetailDialog(Member member) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/DetailScene.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add("/css/detail.css");
            primaryStage.setScene(scene);

            DetailController controller = loader.getController();
            controller.setMainApp(this);
            controller.setMember(member);
        } catch (IOException e) {
            logger.error("error", e);
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
