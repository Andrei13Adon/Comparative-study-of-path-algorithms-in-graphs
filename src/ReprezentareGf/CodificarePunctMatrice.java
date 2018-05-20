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
public class CodificarePunctMatrice {
    /*
    ->oblicul codifica prin metoda linie * numarColoane + coloana
    ->numeroatre liniilor , coloanelor si implicit a punctelor se face de la 
    stanga la dreapta rspectiv de sus in jos incepand cu pozitia 0.
    */
    public int getCodificare(int liniePunct , int coloanaPunct , int numarColoane){
        return liniePunct * numarColoane + coloanaPunct;
    }
    
    public int getColoana(int pozPunct , int numarColoane){
        return pozPunct % numarColoane;
    }
    
    public int getLinie(int pozPunct , int numarColoane){
        return pozPunct / numarColoane;
    } 
}