package Script;

import java.io.DataInputStream;
import java.io.FileInputStream;

public class ReadMapFile {
    static int[][] map1;
    static int[][] map2;
    static int[][] map3;
    static int[][] map4;

    static void readfile(String path){
        try{
            //从path路径下的地图文件中得到文件输入流
            FileInputStream fis = new FileInputStream(path);
            //将文件输入流包装成基本数据输入流
            DataInputStream dis = new DataInputStream(fis);
            //按照保存时的顺序依次读出地图文件中的四个地图数组
            int i = dis.readInt();
            int j = dis.readInt();

            map1 = new int[i][j];
            map2 = new int[i][j];
            map3 = new int[i][j];
            map4 = new int[i][j];

            for(int ii = 0; ii < i; ii++){
                for(int jj = 0; jj < j; jj++){
                    map1[ii][jj] = dis.readInt();
                    map2[ii][jj] = dis.readInt();
                    map3[ii][jj] = dis.readInt();
                    map4[ii][jj] = dis.readInt();
                }
            }
            dis.close();
            fis.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
