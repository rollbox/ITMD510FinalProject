package iit.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class IitDefaultController implements Initializable {
    @FXML
    private ImageView iitImg1;
    @FXML
    private ImageView iitImg2;
    //轮播图资源数组
    String[] imgPath1 = {"iit1.jpg", "iit2.jpg", "iit3.jpg"};
    String[] imgPath2 = {"iit4.jpg", "iit5.jpg", "iit6.jpg"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imgPath1();
        imgPath2();
    }


    void imgPath1() {  //新建一个线程，用来轮播
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (int i = 0; i < imgPath1.length; i++) {
                        //用每个资源创建一个图片对象
                        Image image = new Image("/img/" + imgPath1[i]);
                        //开启一个UI线程
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                //将创建的Image对象设置给ImageView
                                iitImg1.setImage(image);
                            }
                        });
                        try {
                            //休眠2秒
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        //轮播到最后一张图的时候，回到第一张重新播放
                        if (i == imgPath1.length - 1) {
                            i = -1;
                        }

                    }
                }
            }
        }).start();
    }

    void imgPath2() {  //新建一个线程，用来轮播
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    for (int i = 0; i < imgPath2.length; i++) {
                        //用每个资源创建一个图片对象
                        Image image = new Image("/img/" + imgPath2[i]);
                        //开启一个UI线程
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                //将创建的Image对象设置给ImageView
                                iitImg2.setImage(image);
                            }
                        });

                        try {
                            //休眠2秒
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (i == imgPath2.length - 1) {
                            i = -1;
                        }
                    }
                }
            }
        }).start();
    }

}
