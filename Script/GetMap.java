package Script;

import javax.swing.*;

public class GetMap implements gameConfig{
    //通过数字匹配图片
    static ImageIcon intToicon(int num){
        if(num==100){
            return icon0;
        } else if (num == 101){
            return icon1;
        } else if (num == 102){
            return icon2;
        } else if (num == 103){
            return icon3;
        } else if (num == 104){
            return icon4;
        } else if (num == 105){
            return icon5;
        } else if (num == 106){
            return icon6;
        } else if (num == 107){
            return icon7;
        } else if (num == 108){
            return icon8;
        } else if (num == 109){
            return icon9;
        } else if (num == 110){
            return icon10;
        } else if (num == 111){
            return icon11;
        } else if (num == 112) {
            return icon12;
        } else {
            return null;
        }
        //return null;
    }
}
