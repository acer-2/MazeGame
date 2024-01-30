package Script;

import javax.swing.*;

public interface gameConfig {
    //设置标题
    String title = "MazeGame";
    //设置窗体大小
    int frameX = 700;
    int frameY = 700;
    //设置游戏面板大小
    int panelX = 650;
    int panelY = 650;
    //游戏素材大小
    int eleSize = 50;
    //角色大小
    int roleSize = 50;

    //游戏素材
    ImageIcon icon0 = new ImageIcon("src/img/element/100GrassLand.png");
    ImageIcon icon1 = new ImageIcon("src/img/element/101GreenTree.png");
    ImageIcon icon2 = new ImageIcon("src/img/element/102Hill.png");
    ImageIcon icon3 = new ImageIcon("src/img/element/103cactus.png");
    ImageIcon icon4 = new ImageIcon("src/img/element/104FallenTree.png");
    ImageIcon icon5 = new ImageIcon("src/img/element/105Hole.png");
    ImageIcon icon6 = new ImageIcon("src/img/element/106tombStone.png");
    ImageIcon icon7 = new ImageIcon("src/img/element/107road1.png");
    ImageIcon icon8 = new ImageIcon("src/img/element/108Blank.png");
    ImageIcon icon9 = new ImageIcon("src/img/element/109road2.png");
    ImageIcon icon10 = new ImageIcon("src/img/element/110water.png");
    ImageIcon icon11 = new ImageIcon("src/img/element/111coinStack.png");
    ImageIcon icon12 = new ImageIcon("src/img/element/112RedTree.png");

    //阴影镜头
    ImageIcon shadow2 = new ImageIcon("src/img/others/Shadow2.png");

    //角色行走
    ImageIcon walk1 = new ImageIcon(ChooseRole.getPlayerName());

    //角色停顿
    ImageIcon walk2 = new ImageIcon(ChooseRole.getPlayerName2());



}
