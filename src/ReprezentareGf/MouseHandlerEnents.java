/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ReprezentareGf;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author STOLO
 */
public class MouseHandlerEnents  implements MouseListener, MouseMotionListener {
    
    boolean[][] Labirint;
    int marimeLab;
    Grafic g;
    F window;
    boolean valoareNoua;
    boolean setPlecare,setDestinatie;
    
    public MouseHandlerEnents(boolean[][] Labirint, int marimeLab, Grafic g, F window) {
        this.Labirint = Labirint;
        this.marimeLab = marimeLab;
        this.g = g;
        this.window = window;
        this.setPlecare = false;
        this.setDestinatie = false;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        if(this.setPlecare == true){
            int linieApasata = me.getY()/g.getfPrecis();
            int coloanaApasata = me.getX()/g.getfPrecis();
            this.window.setPlecare(linieApasata,coloanaApasata);
            this.setPlecare = false;
        }
        
        if(this.setDestinatie == true){
            int linieApasata = me.getY()/g.getfPrecis();
            int coloanaApasata = me.getX()/g.getfPrecis();
            this.window.setDestinatie(linieApasata,coloanaApasata);
            this.setDestinatie = false;
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if(!setPlecare && !setDestinatie){
       int linieApasata = me.getY()/g.getfPrecis();
       int coloanaApasata = me.getX()/g.getfPrecis();
        if(linieApasata>=0 && coloanaApasata<this.marimeLab){
       if(g.getLabirintCellValue(linieApasata,coloanaApasata))
           this.valoareNoua = false;
       else this.valoareNoua = true;
       g.setLabirintCellValue(linieApasata, coloanaApasata, valoareNoua);
       g.drawCell(linieApasata, coloanaApasata, g.getGraphics());
        }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if(!setPlecare && !setDestinatie){
        int linieApasataNew = me.getY()/g.getfPrecis();
       int coloanaApasataNew = me.getX()/g.getfPrecis();
       if((linieApasataNew>=0 && linieApasataNew<this.marimeLab) && (coloanaApasataNew>=0 && coloanaApasataNew<this.marimeLab))
       if(this.valoareNoua != g.getLabirintCellValue(linieApasataNew,coloanaApasataNew)){
       g.setLabirintCellValue(linieApasataNew, coloanaApasataNew, valoareNoua);
       g.drawCell(linieApasataNew, coloanaApasataNew, g.getGraphics());
       }
        }
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }

    void reset(boolean[][] Labirint, int marimeLab, Grafic g) {
        this.Labirint = Labirint;
        this.marimeLab = marimeLab;
        this.g = g;
    }

    void setPlecare() {
        this.setPlecare = true;
        this.setDestinatie = false;
    }

    void setDestinatie() {
        this.setPlecare = false;
        this.setDestinatie = true;
    }
    
}
