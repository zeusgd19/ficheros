package ficheros;

import java.io.File;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

public class Fichero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File f = File.listRoots()[0];
        System.out.println("-------------------------------------------");
        System.out.println("LISTA DE FICHEROS Y DIRECTORIOS DEL SISTEMA");
        System.out.println("-------------------------------------------");
        list(f);
        int option = sc.nextInt();
        while (option != -1){
            f = move(f,option);
            list(f);
            option= sc.nextInt();
        }
    }

    public static void list(File f){
        int i = 1;
        System.out.println("0 DIRECTORIO PADRE <DIRECTORIO>");
        String permisosDir = "d---";
        String permisosFile = "----";
        DateFormat formatter;
        formatter = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.getDefault());
        for (File fi : f.listFiles()){
            if(fi.isDirectory()){
                if(fi.canRead() && fi.canWrite() && fi.canExecute()){
                    permisosDir = "drwx";
                } else if(fi.canRead()){
                    permisosDir = "dr--";
                } else if(fi.canWrite()){
                    permisosDir = "d-w-";
                } else if(fi.canExecute()){
                    permisosDir = "d--x";
                }
                System.out.println(i + "\t\t" + permisosDir + "\t\t" +  fi.length() / 1024 + "MB " + "\t\t" + formatter.format(fi.lastModified()) + "\t" +  fi.getName() + " <Directoio>");
            } else {
                if(fi.canRead() && fi.canWrite() && fi.canExecute()){
                    permisosFile = "-rwx";
                } else if(fi.canRead()){
                    permisosFile = "-r--";
                } else if(fi.canWrite()){
                    permisosFile = "--w-";
                } else if(fi.canExecute()){
                    permisosFile = "---x";
                }
                System.out.println(i + "\t\t" + permisosFile + "\t\t" + fi.length() / 1024 + "MB " + "\t\t" + formatter.format(fi.lastModified()) + "\t" +  fi.getName() + " <Archivo>");
            }
            i++;
        }
        System.out.println("A que directorio te quiere mover: ");
    }

    public static File move(File f, int mv) {
        if(mv == 0){
            return f.getParentFile();
        }
        return f.listFiles()[mv - 1].isFile() ? f:f.listFiles()[mv -1];
    }
}
