package ficheros;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Fichero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File f = File.listRoots()[0];
        System.out.println("-------------------------------------------");
        System.out.println("LISTA DE FICHEROS Y DIRECTORIOS DEL SISTEMA");
        System.out.println("-------------------------------------------");
        listar(f);
        int option = sc.nextInt();
        while (option != -1){
            f = move(f,option);
            listar(f);
            option= sc.nextInt();
        }
    }

    public static void listar(File f){
        int i = 1;
        System.out.println("0 DIRECTORIO PADRE <DIRECTORIO>");
        for (File fi : f.listFiles()){
            if(fi.isDirectory()){
                System.out.println(i + " " + fi.getName() + " <Directoio>");
            } else {
                System.out.println(i + " " + fi.getName() + " <Archivo>");
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
