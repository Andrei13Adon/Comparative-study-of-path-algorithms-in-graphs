/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ReprezentareGf;

/**
 *
 * @author STOLO
 */
public class DijkInfo implements Comparable<DijkInfo>{
    
     private double Prioritate;
     private int Tata;
     private int PunctCurent;

    public DijkInfo() {
    }

    public DijkInfo(double Prioritate, int Tata, int PunctCurent) {
        this.Prioritate = Prioritate;
        this.Tata = Tata;
        this.PunctCurent = PunctCurent;
    }

    public int getTata() {
        return Tata;
    }

    public int getPunctCurent() {
        return PunctCurent;
    }


    public double getPrioritate() {
        return Prioritate;
    }

    @Override
    public int compareTo(DijkInfo adversar) {
        if(this.Prioritate < adversar.getPrioritate())
            return -1;
        if(this.Prioritate > adversar.getPrioritate())
            return 1;
        return 0;
    }
    
    
    
}
