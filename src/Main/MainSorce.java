/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

import ReprezentareGf.F;
import java.util.ArrayList;

/**
 *
 * @author STOLO
 */
public class MainSorce {

    /**
     * @param args the command line arguments
     */
     static boolean[][]labirint(int marime){
         int nrLinii = marime;
         int nrColoane = marime;
         boolean temp[][] = new boolean[nrLinii][nrLinii];
         for(int i=0; i<nrLinii; i++)
             for(int j=0; j<nrColoane; j++)
                 temp[i][j] = true;
         temp[1][1] = false;
         temp[2][1] = false;
         temp[3][1] = false;
         temp[4][1] = false;
         temp[5][1] = false;
         temp[6][1] = false;
         temp[7][1] = false;
         temp[2][5] = false;
         temp[2][6] = false;
         temp[2][7] = false;
         temp[2][8] = false;
         temp[4][4] = false;
         temp[5][4] = false;
         temp[6][4] = false;
         temp[7][4] = false;
         temp[8][4] = false;
         temp[9][4] = false;
         temp[6][5] = false;
         temp[6][6] = false;
         temp[6][7] = false;
         temp[8][7] = false;
         temp[8][8] = false;
         temp[8][9] = false;
         return temp;
     }
     
     private static void afis(boolean[][] Labirint, int marime) {
         for(int i=0; i<marime; i++){
             for(int j=0; j<marime; j++)
                 System.out.print(Labirint[i][j] +" ");
             System.out.println();
         }
     }
    
    public static void main(String[] args) {
        // Momentatn consider o matrice crata de mine pe care o folosesc sa arat algoritmii
        //in matrice consider ca bariere parti ale ei care sint folse si nu se poate traversa prin acestea
        //Momentan scopul jocului este sa se ajunga din coltul stanga sus in coltul dreapta jos pe cel ai scurt drum
        
        //creare marice
        boolean[][] Labirint;
        int marime = 10;
        Labirint = labirint(marime);
        
        //afisare matrice sub forma de tabel
      //  afis(Labirint,marime);
        
        //apelare afisare grefica
        F  Figura;
        Figura = new F(" Studiu comparativ al algoritmilor de drumuri in grafuri. ", Labirint, marime);
        
    }

}
