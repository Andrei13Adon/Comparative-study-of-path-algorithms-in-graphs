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
public class Punct {
    private int  linie;
    private int coloana;
    public Punct() {
        linie = 0;
        coloana = 0;
    }

    public Punct(int linie, int coloana) {
        this.linie = linie;
        this.coloana = coloana;
    }

    public int getColoana() {
        return coloana;
    }

    public int getLinie() {
        return linie;
    }

    public void setColoana(int coloana) {
        this.coloana = coloana;
    }

    public void setLinie(int linie) {
        this.linie = linie;
    }
    
}
