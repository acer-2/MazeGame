package Script;

import java.io.File;
import java.util.ArrayList;

public class Role {
    private ArrayList<String> roleImages;
    private int currentIndex;

    public Role() {
        roleImages = new ArrayList<>();
        File folder = new File("src/img/RoleShow");
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".png")) {
                    roleImages.add(file.getAbsolutePath());
                }
            }
        }
    }


    public String getCurrentImagePath() {
        return roleImages.get(currentIndex);
    }

    public static String getFileNameFromPath(String path) {
        File file = new File(path);
        return file.getName();
    }


    public ArrayList<String> getRoleImages() {
        return roleImages;
    }

    public String LastRolePath() {
        if (currentIndex > 0) {
            currentIndex--;
            return roleImages.get(currentIndex);
        } else {
            System.out.println("已经到最前面啦");
            return roleImages.get(currentIndex);
        }
    }

    public String NextRolePath() {
        if (currentIndex < roleImages.size() - 1) {
            currentIndex++;
            return roleImages.get(currentIndex);
        } else {
            System.out.println("已经到最后面啦");
            return roleImages.get(currentIndex);
        }
    }
}