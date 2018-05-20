/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ReprezentareGf;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author STOLO
 */
public class Grafic extends Canvas {
    int fPrecis;//=50;///factor de precizie
    final long Secunda = 1000;
    boolean haveReachEnd ;
    boolean[][] Labirint;
    boolean diagonealeStatus;
    ArrayList <Punct> Rezultat,traseu;
    int lungime;
    int marimeLab;
    CodificarePunctMatrice Codder;
    boolean Euclidian,Manhattan,Octile,Chebyshev;
    boolean Resset;
    
    int DF,BF,Dij,AStar,Greedy;
    int dDF,dBF,dDij;
    
    double pondere;

    Grafic(boolean[][] Labirint ,int marimeLab) {
        this.Labirint=Labirint;
        this.marimeLab = marimeLab;
        this.Codder = new CodificarePunctMatrice();
        this.diagonealeStatus = false;
        Euclidian = true;
        Manhattan = false;
        Octile = false;
        Chebyshev = false;;
        fPrecis = (int)500/this.marimeLab;
        Resset = false;
        
        DF=0;
        BF=0;
        Dij=0;
        AStar=0;
        Greedy=0;
        dDF=0;
        dBF=0;
        dDij=0;
        
        pondere = 1.0;
        
    }
    
    public void delay(long timp){
        try {
            Thread.sleep(timp/this.marimeLab);
        } catch (InterruptedException ex) {
            Logger.getLogger(Grafic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void DepthFirst(Graphics g , boolean[][] Labirint,int linie,int coloana,Punct Destinatie,int Lungime){
         this.DF++;
         g.setColor(Color.BLUE);
         g.fillRect(coloana*this.fPrecis+1 , linie*this.fPrecis+1 , this.fPrecis-1 , this.fPrecis-1);
         //this.traseu.add(Lungime,Plecare);
         //System.out.println(Plecare.getLinie()+" "+ Plecare.getColana());
         this.delay(Secunda);
         
         //Setare pozitie ca una ce nu mai poate fi vizitata
         Labirint[linie][coloana] = false;
         if(linie==Destinatie.getLinie()&&coloana==Destinatie.getColoana()){
             this.lungime = Lungime;
             this.Rezultat = new ArrayList <Punct>(Lungime);
             this.haveReachEnd = true;
             this.dDF = Lungime;
         }
         //Pas recursivitate incercare de a vedea pe unde se mai poate duce
         if( !this.haveReachEnd && linie-1 >= 0 && Labirint[linie-1][coloana] == true )
             DepthFirst(g,Labirint,linie-1,coloana,Destinatie,Lungime+1);
         if( !this.haveReachEnd && coloana+1 < this.marimeLab && Labirint[linie][coloana+1] == true )
             this.DepthFirst(g,Labirint,linie,coloana+1,Destinatie,Lungime+1);
         if( !this.haveReachEnd && linie+1 < this.marimeLab && Labirint[linie+1][coloana] == true )
             DepthFirst(g,Labirint,linie+1,coloana,Destinatie,Lungime+1);
         if( !this.haveReachEnd && coloana-1 >= 0 && Labirint[linie][coloana-1] == true )
             DepthFirst(g,Labirint,linie,coloana-1,Destinatie,Lungime+1);
         if(this.diagonealeStatus){
         //Pas recursiv pentru diagonale activate adica miscarea pe diagonale
         if( !this.haveReachEnd && (linie-1 >= 0 && coloana-1 >= 0) && Labirint[linie-1][coloana-1] == true )
             DepthFirst(g,Labirint,linie-1,coloana-1,Destinatie,Lungime+1);
         if( !this.haveReachEnd && (linie+1 < this.marimeLab && coloana+1 < this.marimeLab) && Labirint[linie+1][coloana+1] == true )
             DepthFirst(g,Labirint,linie+1,coloana+1,Destinatie,Lungime+1);
         if( !this.haveReachEnd && (linie-1 >= 0 && coloana+1 < this.marimeLab) && Labirint[linie-1][coloana+1] == true )
             DepthFirst(g,Labirint,linie-1,coloana+1,Destinatie,Lungime+1);
         if( !this.haveReachEnd && (linie+1 < this.marimeLab && coloana-1 >= 0) && Labirint[linie+1][coloana-1] == true )
             DepthFirst(g,Labirint,linie+1,coloana-1,Destinatie,Lungime+1);
         }
         //Ilustrare pozitie vizitata blocanta
         if(!this.haveReachEnd){
             this.delay(Secunda);
             g.setColor(Color.GRAY);
             g.fillRect(coloana*this.fPrecis+1 , linie*this.fPrecis+1 , this.fPrecis-1 , this.fPrecis-1);
         }
         if(this.haveReachEnd)
             this.Rezultat.add(new Punct(linie,coloana));
     }
    
    @Override
    public void paint(Graphics g) {
      /*  g.translate(300,300);
        g.setColor(Color.red);
        g.drawLine(-300,0,300,0);
        g.drawLine(0,-300,0,300);
        g.setColor(Color.BLACK); 
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Labirint.get(0).size()*fPrecis, Labirint.size()*fPrecis);*/
        
        g.setColor(Color.WHITE);//umplere initiala
        g.fillRect(0,0, 501 , 501);
        g.setColor(Color.BLACK);
     //   int x1,y1,x2,y2;
      //  g.drawRect(0*fPrecis, 1*fPrecis, 1, 1);
        //creare grila
        for(int i=0; i<= marimeLab;i++)
            g.drawLine(i*fPrecis, 0, i*fPrecis, marimeLab*fPrecis);
        for(int j=0; j<= marimeLab; j++)
            g.drawLine(0, j*fPrecis, marimeLab*fPrecis, j*fPrecis);       
        //pune puncte pe figura
        //g.setColor(Color.RED);
        for(int i=0;i<marimeLab;i++)
            for(int j=0;j<marimeLab;j++)
                if(Labirint[i][j]==false){
                    g.fillRect(j*fPrecis+1, i*fPrecis+1, fPrecis-1 , fPrecis-1);
                }
        
            /*      //penru Down
                if(!joc.existDown(i,j)){
                    g.drawLine(j*fPrecis, (i+1)*fPrecis, (j+1)*fPrecis, (i+1)*fPrecis);
                }
                
              //penru Right
                if(!joc.existRight(i,j)){
                    g.drawLine((j+1)*fPrecis, i*fPrecis, (j+1)*fPrecis, (i+1)*fPrecis);
                }
            }
        
        x1 = (int)Math.round(fPrecis*pControl[0].X());
        y1 = (int)Math.round(fPrecis*pControl[0].Y())*(-1);
        g.drawRect(x1, y1, 1, 1);
        for(int i=1;i<nrPunctCont;i++){
            x1 = (int)Math.round(fPrecis*pControl[i].X());
            y1 = (int)Math.round(fPrecis*pControl[i].Y())*(-1);
            g.drawRect(x1, y1, 1, 1);
            x2 = (int)Math.round(fPrecis*pControl[i-1].X());
            y2 = (int)Math.round(fPrecis*pControl[i-1].Y()*(-1));
            g.drawLine(x1, y1, x2, y2);
        }
        for(int i=1;i<nrPunctCurba;i++){
            x1 = (int)Math.round(fPrecis*pCurba[i].X());
            y1 = (int)Math.round(fPrecis*pCurba[i].Y())*(-1);
            g.drawRect(x1, y1, 1, 1);
            x2 = (int)Math.round(fPrecis*pCurba[i-1].X());
            g.drawLine(x1, y1, x2, y2);
            y2 = (int)Math.round(fPrecis*pCurba[i-1].Y()*(-1));
        }
        */
        if(!Resset){
        g.setColor(Color.GREEN);
        g.fillRect( 0*this.fPrecis+1 , 0*this.fPrecis+1 , this.fPrecis-1 , this.fPrecis-1);
        
        g.setColor(Color.RED);
        g.fillRect( (this.marimeLab-1)*this.fPrecis+1 , (this.marimeLab-1)*this.fPrecis+1 , this.fPrecis-1 , this.fPrecis-1);
        }
        
    }

    void init(Graphics g) {
        this.traseu = new ArrayList<Punct>();
        lungime = Integer.MAX_VALUE;
        this.haveReachEnd = false;
        for(int i=0;i<this.marimeLab;i++)
            for(int j=0;j<this.marimeLab;j++)
                if(this.Labirint[i][j]){
                 g.setColor(Color.WHITE);
                 g.fillRect(j*this.fPrecis+1 , i*this.fPrecis+1 , this.fPrecis-1 , this.fPrecis-1);
        
            }
    }

    void afisRez(Graphics g) {
        if(lungime != Integer.MAX_VALUE){
        for(int i=this.lungime-1;i>=0;i--){
            g.setColor(Color.RED);
            g.fillRect(this.Rezultat.get(i).getColoana()*this.fPrecis+1 , this.Rezultat.get(i).getLinie()*this.fPrecis+1 , this.fPrecis-1 , this.fPrecis-1);
            if(i!=this.lungime-1){
                g.setColor(Color.BLACK);
                g.drawLine(this.Rezultat.get(i+1).getColoana()*this.fPrecis + this.fPrecis/2, this.Rezultat.get(i+1).getLinie()*this.fPrecis+ this.fPrecis/2, this.Rezultat.get(i).getColoana()*this.fPrecis+ this.fPrecis/2, this.Rezultat.get(i).getLinie()*this.fPrecis+ this.fPrecis/2);
            }
            this.delay(Secunda);
        }
        }
    }

    void BreakFirst(Graphics g, boolean[][] Labirint, Punct Plecare, Punct Destinatie, int Lungime) {
        ArrayList<Integer> Tati = new ArrayList<Integer>();
        this.traseu.add(Plecare);
        Tati.add(-1);
        Lungime ++;
        Labirint[Plecare.getLinie()][Plecare.getColoana()] = false;
        g.setColor(Color.GREEN);
        int rez=0;
        for(int i=0;i<Lungime;i++){
            this.BF++;
            //desenare patrat la fiecare pas
            g.fillRect(traseu.get(i).getColoana()*this.fPrecis+1 , traseu.get(i).getLinie()*this.fPrecis+1 , this.fPrecis-1 , this.fPrecis-1);
            this.delay(Secunda);
            //Oprire for
         if(traseu.get(i).getLinie()==Destinatie.getLinie()&&traseu.get(i).getColoana()==Destinatie.getColoana()){
             rez = i;
             break;
         }
            //Pas incercare de a vedea pe unde se mai poate duce
         if( traseu.get(i).getLinie()-1 >= 0 && Labirint[traseu.get(i).getLinie()-1][traseu.get(i).getColoana()] == true ){
             this.traseu.add(new Punct((traseu.get(i).getLinie()-1),(traseu.get(i).getColoana())));
             Tati.add(i);
             Lungime++;
             Labirint[traseu.get(i).getLinie()-1][traseu.get(i).getColoana()] = false;
         }
         if( traseu.get(i).getColoana()+1 < this.marimeLab && Labirint[traseu.get(i).getLinie()][traseu.get(i).getColoana()+1] == true ){
             this.traseu.add(new Punct(traseu.get(i).getLinie(),traseu.get(i).getColoana()+1));
             Tati.add(i);
             Lungime++;
             Labirint[traseu.get(i).getLinie()][traseu.get(i).getColoana()+1] = false;
         }
         if( traseu.get(i).getLinie()+1 < this.marimeLab && Labirint[traseu.get(i).getLinie()+1][traseu.get(i).getColoana()] == true ){
             this.traseu.add(new Punct((traseu.get(i).getLinie()+1),(traseu.get(i).getColoana())));
             Tati.add(i);
             Lungime++;
             Labirint[traseu.get(i).getLinie()+1][traseu.get(i).getColoana()] = false;
         }
         if( traseu.get(i).getColoana()-1 >= 0 && Labirint[traseu.get(i).getLinie()][traseu.get(i).getColoana()-1] == true ){
             this.traseu.add(new Punct((traseu.get(i).getLinie()),(traseu.get(i).getColoana()-1)));
             Tati.add(i);
             Lungime++;
             Labirint[traseu.get(i).getLinie()][traseu.get(i).getColoana()-1] = false;
         }
         if(this.diagonealeStatus){
         //Pas incercare pentru diagonale activate adica miscarea pe diagonale
         if( (traseu.get(i).getLinie()-1 >= 0 && traseu.get(i).getColoana()-1 >= 0) && Labirint[traseu.get(i).getLinie()-1][traseu.get(i).getColoana()-1] == true ){
             this.traseu.add(new Punct(traseu.get(i).getLinie()-1,traseu.get(i).getColoana()-1));
             Tati.add(i);
             Lungime++;
             Labirint[traseu.get(i).getLinie()-1][traseu.get(i).getColoana()-1] = false;
         }
         if(  (traseu.get(i).getLinie()+1 < this.marimeLab && traseu.get(i).getColoana()+1 < this.marimeLab) && Labirint[traseu.get(i).getLinie()+1][traseu.get(i).getColoana()+1] == true ){
             this.traseu.add(new Punct(traseu.get(i).getLinie()+1,traseu.get(i).getColoana()+1));
             Tati.add(i);
             Lungime++;
             Labirint[traseu.get(i).getLinie()+1][traseu.get(i).getColoana()+1] = false;
         }
         if( (traseu.get(i).getLinie()-1 >= 0 && traseu.get(i).getColoana()+1 < this.marimeLab) && Labirint[traseu.get(i).getLinie()-1][traseu.get(i).getColoana()+1] == true ){
             this.traseu.add(new Punct(traseu.get(i).getLinie()-1,traseu.get(i).getColoana()+1));
             Tati.add(i);
             Lungime++;
             Labirint[traseu.get(i).getLinie()-1][traseu.get(i).getColoana()+1] = false;
         }
         if( (traseu.get(i).getLinie()+1 < this.marimeLab && traseu.get(i).getColoana()-1 >= 0) && Labirint[traseu.get(i).getLinie()+1][traseu.get(i).getColoana()-1] == true ){
            this.traseu.add(new Punct(traseu.get(i).getLinie()+1,traseu.get(i).getColoana()-1));
             Tati.add(i);
             Lungime++;
             Labirint[traseu.get(i).getLinie()+1][traseu.get(i).getColoana()-1] = false;
         }
         }
        }
        afisRezultBF(Tati,rez,g);
    }

    private void afisRezultBF(ArrayList<Integer> Tati, int rez,Graphics g) {
        if(Tati.get(rez)!= -1){
            this.dBF++;
            afisRezultBF(Tati,Tati.get(rez),g);
            this.dBF--;
        }
        this.dBF++;
        if(!(this.dBF<=2)){
        this.delay(Secunda);
        g.setColor(Color.RED);
        g.fillRect(this.traseu.get(rez).getColoana()*this.fPrecis+1 , this.traseu.get(rez).getLinie()*this.fPrecis+1 , this.fPrecis-1 , this.fPrecis-1);
        if(Tati.get(rez)!= -1){
                g.setColor(Color.BLACK);
                g.drawLine(this.traseu.get(Tati.get(rez)).getColoana()*this.fPrecis + this.fPrecis/2, this.traseu.get(Tati.get(rez)).getLinie()*this.fPrecis+ this.fPrecis/2, this.traseu.get(rez).getColoana()*this.fPrecis+ this.fPrecis/2, this.traseu.get(rez).getLinie()*this.fPrecis+ this.fPrecis/2);
            }
        }else{
            this.dBF = 0;
        }
    }

    void Dijkstra(Graphics g, boolean[][] Labirint, int plecare, int destinatie) {
        /*coada de prioritati(heap) ce tine minte legaturile calculate prin 
        costNodCurent + costLegatura(in cazul acesta va fi tot tilmpul 1) si 
        da urmatorea legatura ce poate fi adaugat la vectorul de tati adica 
        returneaza cel mai scurt drum de la plecare pana la nodul respectiv.*/
        PriorityQueue<DijkInfo> nextLink; 
        int tati[] = new int[this.marimeLab * this.marimeLab];
        int linie,coloana;
        nextLink = new PriorityQueue<DijkInfo>();
        nextLink.add(new DijkInfo(0,-1,plecare));
        DijkInfo punctCurent;
        while(!haveReachEnd&&!nextLink.isEmpty()){
            this.Dij++;
            punctCurent = nextLink.poll();
            linie = this.Codder.getLinie(punctCurent.getPunctCurent(),this.marimeLab);
            coloana = this.Codder.getColoana(punctCurent.getPunctCurent(),this.marimeLab);
            if(Labirint[linie][coloana] == true){
            tati[punctCurent.getPunctCurent()] = punctCurent.getTata();
            Labirint[linie][coloana] = false;
            g.setColor(Color.BLUE);
            g.fillRect(coloana*this.fPrecis+1 , linie*this.fPrecis+1 , this.fPrecis-1 , this.fPrecis-1);
            delay(Secunda);
            if(punctCurent.getPunctCurent() == destinatie)
                haveReachEnd = true;
            else{
                //aici se scrie practic pasul de interatie al algoritmului
                 if(linie-1 >= 0 && Labirint[linie-1][coloana] == true )
                     nextLink.add(new DijkInfo(punctCurent.getPrioritate()+1,punctCurent.getPunctCurent(),this.Codder.getCodificare(linie-1, coloana, this.marimeLab)));
                 if(coloana+1 < this.marimeLab && Labirint[linie][coloana+1] == true )
                     nextLink.add(new DijkInfo(punctCurent.getPrioritate()+1,punctCurent.getPunctCurent(),this.Codder.getCodificare(linie, coloana+1, this.marimeLab)));
                 if(linie+1 < this.marimeLab && Labirint[linie+1][coloana] == true )
                     nextLink.add(new DijkInfo(punctCurent.getPrioritate()+1,punctCurent.getPunctCurent(),this.Codder.getCodificare(linie+1, coloana, this.marimeLab)));
                 if(coloana-1 >= 0 && Labirint[linie][coloana-1] == true )
                     nextLink.add(new DijkInfo(punctCurent.getPrioritate()+1,punctCurent.getPunctCurent(),this.Codder.getCodificare(linie, coloana-1, this.marimeLab)));
                 if(this.diagonealeStatus){
                  //pasul de interatie al algoritmului pentru diagonale activate adica miscarea pe diagonale
                    if( (linie-1 >= 0 && coloana-1 >= 0) && Labirint[linie-1][coloana-1] == true )
                        nextLink.add(new DijkInfo(punctCurent.getPrioritate()+1,punctCurent.getPunctCurent(),this.Codder.getCodificare(linie-1, coloana-1, this.marimeLab)));
                    if( (linie+1 < this.marimeLab && coloana+1 < this.marimeLab) && Labirint[linie+1][coloana+1] == true )
                        nextLink.add(new DijkInfo(punctCurent.getPrioritate()+1,punctCurent.getPunctCurent(),this.Codder.getCodificare(linie+1, coloana+1, this.marimeLab)));
                    if( (linie-1 >= 0 && coloana+1 < this.marimeLab) && Labirint[linie-1][coloana+1] == true )
                        nextLink.add(new DijkInfo(punctCurent.getPrioritate()+1,punctCurent.getPunctCurent(),this.Codder.getCodificare(linie-1, coloana+1, this.marimeLab)));
                    if( (linie+1 < this.marimeLab && coloana-1 >= 0) && Labirint[linie+1][coloana-1] == true )
                        nextLink.add(new DijkInfo(punctCurent.getPrioritate()+1,punctCurent.getPunctCurent(),this.Codder.getCodificare(linie+1, coloana-1, this.marimeLab)));
                 }
            }
            }
        }
        afisDijkstra(g,tati,destinatie);
    } 

    private void afisDijkstra(Graphics g, int[] tati, int pozitiaCurenta) {
        if(tati[pozitiaCurenta] != -1){
            this.dDij++;
            afisDijkstra(g,tati,tati[pozitiaCurenta]);
            this.dDij--;
        }
        dDij++;
        if(!(this.dDij<=2)){
        this.delay(Secunda);
        g.setColor(Color.RED);
        g.fillRect(this.Codder.getColoana(pozitiaCurenta, marimeLab)*this.fPrecis+1 , this.Codder.getLinie(pozitiaCurenta, marimeLab)*this.fPrecis+1 , this.fPrecis-1 , this.fPrecis-1);
        if(tati[pozitiaCurenta] != -1){
                g.setColor(Color.BLACK);
                g.drawLine(this.Codder.getColoana(tati[pozitiaCurenta], marimeLab)*this.fPrecis + this.fPrecis/2, this.Codder.getLinie(tati[pozitiaCurenta], marimeLab)*this.fPrecis+ this.fPrecis/2, this.Codder.getColoana(pozitiaCurenta, marimeLab)*this.fPrecis+ this.fPrecis/2, this.Codder.getLinie(pozitiaCurenta, marimeLab)*this.fPrecis+ this.fPrecis/2);
            }
        }else{
            dDij = 0;
        }
    }

    void AStarAlg(Graphics g, boolean[][] Labirint, int plecare, int destinatie) {
        /*coada de prioritati(heap) ce tine minte legaturile calculate prin 
        costNodCurent + costLegatura(in carul acesta va fi tot tilmpul 1) si 
        da urmatorea legatura ce poate fi adaugat la vectorul de tati adica 
        returneaza cel mai scurt drum de la plecare pana la nodul respectiv
        acesta fiind considerat prioritate si calculat dupa formula:
        castNodCurent + costLegatura + costFunctieDeAproximareRezultat*/
        PriorityQueue<AStarInfo> nextLink; 
        int tati[] = new int[this.marimeLab * this.marimeLab];
        int linie,coloana;
        nextLink = new PriorityQueue<AStarInfo>();
        nextLink.add(new AStarInfo(Aprox(plecare,destinatie),0,-1,plecare));
        AStarInfo punctCurent;
        while(!haveReachEnd&&!nextLink.isEmpty()){
            this.AStar++;
            punctCurent = nextLink.poll();
            linie = this.Codder.getLinie(punctCurent.getPunctCurent(),this.marimeLab);
            coloana = this.Codder.getColoana(punctCurent.getPunctCurent(),this.marimeLab);
            if(Labirint[linie][coloana] == true){
            tati[punctCurent.getPunctCurent()] = punctCurent.getTata();
            Labirint[linie][coloana] = false;
            g.setColor(Color.BLUE);
            g.fillRect(coloana*this.fPrecis+1 , linie*this.fPrecis+1 , this.fPrecis-1 , this.fPrecis-1);
            delay(Secunda);
            if(punctCurent.getPunctCurent() == destinatie)
                haveReachEnd = true;
            else{//System.out.println(punctCurent.getPunctCurent() + " " +destinatie+ " " +(punctCurent.getCostDrum()+1+this.AStarAprox(punctCurent.getPunctCurent(), destinatie)));
                //aici se scrie practic pasul de interatie al algoritmului
                 if(linie-1 >= 0 && Labirint[linie-1][coloana] == true )
                     nextLink.add(new AStarInfo(punctCurent.getCostDrum()+1+this.pondere * this.Aprox(this.Codder.getCodificare(linie-1, coloana, this.marimeLab), destinatie),punctCurent.getCostDrum()+1,punctCurent.getPunctCurent(),this.Codder.getCodificare(linie-1, coloana, this.marimeLab)));
                 if(coloana+1 < this.marimeLab && Labirint[linie][coloana+1] == true )
                     nextLink.add(new AStarInfo(punctCurent.getCostDrum()+1+this.pondere * this.Aprox(this.Codder.getCodificare(linie, coloana+1, this.marimeLab), destinatie),punctCurent.getCostDrum()+1,punctCurent.getPunctCurent(),this.Codder.getCodificare(linie, coloana+1, this.marimeLab)));
                 if(linie+1 < this.marimeLab && Labirint[linie+1][coloana] == true )
                     nextLink.add(new AStarInfo(punctCurent.getCostDrum()+1+this.pondere * this.Aprox(this.Codder.getCodificare(linie+1, coloana, this.marimeLab), destinatie),punctCurent.getCostDrum()+1,punctCurent.getPunctCurent(),this.Codder.getCodificare(linie+1, coloana, this.marimeLab)));
                 if(coloana-1 >= 0 && Labirint[linie][coloana-1] == true )
                     nextLink.add(new AStarInfo(punctCurent.getCostDrum()+1+this.pondere * this.Aprox(this.Codder.getCodificare(linie, coloana-1, this.marimeLab), destinatie),punctCurent.getCostDrum()+1,punctCurent.getPunctCurent(),this.Codder.getCodificare(linie, coloana-1, this.marimeLab)));
                 if(this.diagonealeStatus){
                 //pasul de interatie al algoritmului pentru diagonale activate adica miscarea pe diagonale
                    if( (linie-1 >= 0 && coloana-1 >= 0) && Labirint[linie-1][coloana-1] == true )
                     nextLink.add(new AStarInfo(punctCurent.getCostDrum()+1+this.pondere * this.Aprox(this.Codder.getCodificare(linie-1, coloana-1, this.marimeLab), destinatie),punctCurent.getCostDrum()+1,punctCurent.getPunctCurent(),this.Codder.getCodificare(linie-1, coloana-1, this.marimeLab)));
                    if( (linie+1 < this.marimeLab && coloana+1 < this.marimeLab) && Labirint[linie+1][coloana+1] == true )
                     nextLink.add(new AStarInfo(punctCurent.getCostDrum()+1+this.pondere * this.Aprox(this.Codder.getCodificare(linie+1, coloana+1, this.marimeLab), destinatie),punctCurent.getCostDrum()+1,punctCurent.getPunctCurent(),this.Codder.getCodificare(linie+1, coloana+1, this.marimeLab)));
                    if( (linie-1 >= 0 && coloana+1 < this.marimeLab) && Labirint[linie-1][coloana+1] == true )
                     nextLink.add(new AStarInfo(punctCurent.getCostDrum()+1+this.pondere * this.Aprox(this.Codder.getCodificare(linie-1, coloana+1, this.marimeLab), destinatie),punctCurent.getCostDrum()+1,punctCurent.getPunctCurent(),this.Codder.getCodificare(linie-1, coloana+1, this.marimeLab)));
                    if( (linie+1 < this.marimeLab && coloana-1 >= 0) && Labirint[linie+1][coloana-1] == true )
                     nextLink.add(new AStarInfo(punctCurent.getCostDrum()+1+this.pondere * this.Aprox(this.Codder.getCodificare(linie+1, coloana-1, this.marimeLab), destinatie),punctCurent.getCostDrum()+1,punctCurent.getPunctCurent(),this.Codder.getCodificare(linie+1, coloana-1, this.marimeLab)));
                 }
            }
            }
        }
        afisDijkstra(g,tati,destinatie);
    }

    private double Aprox(int plecare, int destinatie) {
        int linieDistanta = Math.abs(this.Codder.getLinie(plecare, marimeLab) - this.Codder.getLinie(destinatie, marimeLab));
        int coloanaDistanta = Math.abs(this.Codder.getColoana(plecare, marimeLab) - this.Codder.getColoana(destinatie, marimeLab));
       // System.out.println( Math.max(linieDistanta,coloanaDistanta)+"****");
        if(this.Euclidian)
            return  Math.sqrt(Math.pow(linieDistanta, 2)+Math.pow(coloanaDistanta, 2));
        if(this.Manhattan)
            return Math.abs(linieDistanta) + Math.abs(coloanaDistanta);
        if(this.Octile)
            return Math.max(linieDistanta,coloanaDistanta)+(Math.sqrt(2)-1)*Math.min(linieDistanta,coloanaDistanta);
        //this.Chebyshev;
        return Math.max(linieDistanta,coloanaDistanta);
    }

    void GreeBFSAlg(Graphics g, boolean[][] Labirint, int plecare, int destinatie) {
        /*Obs! NU obtine Drum Minim
        este implemantare algoritmuli Greedy ce utilizeaza o functe eursistca 
        ce in cazul acesta reprezinta disatanta minima NU PE DIAGONALA ce se 
        pate reazliza plecand de la puntul curet pan la destinatie drep 
        prioritatea in heap se mai ine minte si lungimea drumuli precum si un 
        vector de tati algoritmul ia totdeauna valoarea ce mai mica din heap 
        pan cand se ajunge ca heapul sa fie gol sa se ajunge la radacnina se 
        foloseste de structuara din A**/
        PriorityQueue<DijkInfo> nextLink; 
        int tati[] = new int[this.marimeLab * this.marimeLab];
        int linie,coloana;
        nextLink = new PriorityQueue<DijkInfo>();
        nextLink.add(new DijkInfo(Aprox(plecare,destinatie),-1,plecare));
        DijkInfo punctCurent;
        while(!haveReachEnd&&!nextLink.isEmpty()){
            this.Greedy++;
            punctCurent = nextLink.poll();
            linie = this.Codder.getLinie(punctCurent.getPunctCurent(),this.marimeLab);
            coloana = this.Codder.getColoana(punctCurent.getPunctCurent(),this.marimeLab);
            if(Labirint[linie][coloana] == true){
            tati[punctCurent.getPunctCurent()] = punctCurent.getTata();
            Labirint[linie][coloana] = false;
            g.setColor(Color.BLUE);
            g.fillRect(coloana*this.fPrecis+1 , linie*this.fPrecis+1 , this.fPrecis-1 , this.fPrecis-1);
            delay(Secunda);
            if(punctCurent.getPunctCurent() == destinatie)
                haveReachEnd = true;
            else{//System.out.println(punctCurent.getPunctCurent() + " " +destinatie+ " " +(Aprox(punctCurent.getPunctCurent(), destinatie)));
                //aici se scrie practic pasul de interatie al algoritmului
                 if(linie-1 >= 0 && Labirint[linie-1][coloana] == true )
                     nextLink.add(new DijkInfo(this.Aprox(this.Codder.getCodificare(linie-1, coloana, this.marimeLab), destinatie),punctCurent.getPunctCurent(),this.Codder.getCodificare(linie-1, coloana, this.marimeLab)));
                 if(coloana+1 < this.marimeLab && Labirint[linie][coloana+1] == true )
                     nextLink.add(new DijkInfo(this.Aprox(this.Codder.getCodificare(linie, coloana+1, this.marimeLab), destinatie),punctCurent.getPunctCurent(),this.Codder.getCodificare(linie, coloana+1, this.marimeLab)));
                 if(linie+1 < this.marimeLab && Labirint[linie+1][coloana] == true )
                     nextLink.add(new DijkInfo(this.Aprox(this.Codder.getCodificare(linie+1, coloana, this.marimeLab), destinatie),punctCurent.getPunctCurent(),this.Codder.getCodificare(linie+1, coloana, this.marimeLab)));
                 if(coloana-1 >= 0 && Labirint[linie][coloana-1] == true )
                     nextLink.add(new DijkInfo(this.Aprox(this.Codder.getCodificare(linie, coloana-1, this.marimeLab), destinatie),punctCurent.getPunctCurent(),this.Codder.getCodificare(linie, coloana-1, this.marimeLab)));
                 if(this.diagonealeStatus){
                  //pasul de interatie al algoritmului pentru diagonale activate adica miscarea pe diagonale
                    if( (linie-1 >= 0 && coloana-1 >= 0) && Labirint[linie-1][coloana-1] == true )
                        nextLink.add(new DijkInfo(this.Aprox(this.Codder.getCodificare(linie-1, coloana-1, this.marimeLab), destinatie),punctCurent.getPunctCurent(),this.Codder.getCodificare(linie-1, coloana-1, this.marimeLab)));
                    if( (linie+1 < this.marimeLab && coloana+1 < this.marimeLab) && Labirint[linie+1][coloana+1] == true )
                        nextLink.add(new DijkInfo(this.Aprox(this.Codder.getCodificare(linie+1, coloana+1, this.marimeLab), destinatie),punctCurent.getPunctCurent(),this.Codder.getCodificare(linie+1, coloana+1, this.marimeLab)));
                    if( (linie-1 >= 0 && coloana+1 < this.marimeLab) && Labirint[linie-1][coloana+1] == true )
                        nextLink.add(new DijkInfo(this.Aprox(this.Codder.getCodificare(linie-1, coloana+1, this.marimeLab), destinatie),punctCurent.getPunctCurent(),this.Codder.getCodificare(linie-1, coloana+1, this.marimeLab)));
                    if( (linie+1 < this.marimeLab && coloana-1 >= 0) && Labirint[linie+1][coloana-1] == true )
                        nextLink.add(new DijkInfo(this.Aprox(this.Codder.getCodificare(linie+1, coloana-1, this.marimeLab), destinatie),punctCurent.getPunctCurent(),this.Codder.getCodificare(linie+1, coloana-1, this.marimeLab)));
                 }
            }
            }
        }
        afisDijkstra(g,tati,destinatie);
    }

    

    public boolean getDiagonealeStatus() {
        return diagonealeStatus;
    }

    public void setDiagonealeStatus(boolean diagonealeStatus) {
        this.diagonealeStatus = diagonealeStatus;
    }

    public boolean getEuclidian() {
        return Euclidian;
    }

    public boolean getManhattan() {
        return Manhattan;
    }

    public boolean getOctile() {
        return Octile;
    }

    public boolean getChebyshev() {
        return Chebyshev;
    }

    public void setEuclidian(boolean Euclidian) {
        this.Euclidian = Euclidian;
    }

    public void setManhattan(boolean Manhattan) {
        this.Manhattan = Manhattan;
    }

    public void setOctile(boolean Octile) {
        this.Octile = Octile;
    }

    public void setChebyshev(boolean Chebyshev) {
        this.Chebyshev = Chebyshev;
    }

    public int getfPrecis() {
        return fPrecis;
    }

    boolean getLabirintCellValue(int linie, int coloana) {
        return this.Labirint[linie][coloana];
    }
    
    void setLabirintCellValue(int linie, int coloana, boolean valureNoua) {
        this.Labirint[linie][coloana] = valureNoua;
    }

    void drawCell(int linieApasata, int coloanaApasata,Graphics g) {
       // System.out.println(this.Labirint[linieApasata][coloanaApasata]);
        if(this.Labirint[linieApasata][coloanaApasata])
            g.setColor(Color.WHITE);
        else 
            g.setColor(Color.BLACK);
        g.fillRect( coloanaApasata*this.fPrecis+1 , linieApasata*this.fPrecis+1 , this.fPrecis-1 , this.fPrecis-1);
    }

    void resetLabirint(boolean[][] Labirint, int marimeLab) {
        this.Labirint=Labirint;
        this.marimeLab = marimeLab;
        fPrecis = (int)500/this.marimeLab;
        Resset = true;
        
        DF=0;
        BF=0;
        Dij=0;
        AStar=0;
        Greedy=0;
        dDF=0;
        dBF=0;
        dDij=0;
    }

    void GenerateNewMazeUsingPrime(Graphics g, int Plecare, Punct Destinatie) {
        for(int i=0;i<this.marimeLab;i++)
            for(int j=0;j<this.marimeLab;j++)
                this.Labirint[i][j] = false;
        LinkedList<Integer> pereti=new <Integer> LinkedList();
        pereti.add(Plecare);
        int linieCurenta,coloanaCurenta;
        int codificarePunctCurent,pozitiePunctCurent;
        int obtiuni[] = new int[4];
        /*
        Codificare pbtiuni;
        0 - stanga
        1 - sus
        2 - dreapta
        3 - jos
        */
        int numarObtiuni;
        while(!pereti.isEmpty()){
            //selectare punct random pentru a fi facut casuta din zid
            pozitiePunctCurent = (int) ((Math.random() * this.marimeLab * this.marimeLab)%pereti.size());
            codificarePunctCurent = pereti.get(pozitiePunctCurent);
            pereti.remove(pozitiePunctCurent);
            linieCurenta = this.Codder.getLinie(codificarePunctCurent, marimeLab);
            coloanaCurenta = this.Codder.getColoana(codificarePunctCurent, marimeLab);
            if(!this.Labirint[linieCurenta][coloanaCurenta]){
                this.Labirint[linieCurenta][coloanaCurenta] = true;
                //adug vecnii sai care sunt ziduri
                numarObtiuni = 0;
                if(linieCurenta - 2 >= 0)
                    if(!this.Labirint[linieCurenta - 2][coloanaCurenta])
                        pereti.add(this.Codder.getCodificare(linieCurenta-2, coloanaCurenta, this.marimeLab));
                    else {
                        obtiuni[numarObtiuni] = 3; 
                        numarObtiuni++;
                    }
                if(linieCurenta + 2 < this.marimeLab)
                    if(!this.Labirint[linieCurenta + 2][coloanaCurenta])
                        pereti.add(this.Codder.getCodificare(linieCurenta+2, coloanaCurenta, this.marimeLab));
                    else {
                        obtiuni[numarObtiuni] = 1; 
                        numarObtiuni++;
                    }
                if(coloanaCurenta - 2 >= 0)
                    if(!this.Labirint[linieCurenta][coloanaCurenta - 2])
                        pereti.add(this.Codder.getCodificare(linieCurenta, coloanaCurenta-2, this.marimeLab));
                    else {
                        obtiuni[numarObtiuni] = 0; 
                        numarObtiuni++;
                    }
                if(coloanaCurenta + 2 < this.marimeLab)
                    if(!this.Labirint[linieCurenta][coloanaCurenta + 2])
                        pereti.add(this.Codder.getCodificare(linieCurenta, coloanaCurenta+2, this.marimeLab));
                    else {
                        obtiuni[numarObtiuni] = 2; 
                        numarObtiuni++;
                    }
                if(numarObtiuni!=0){
                //caut random un vecin care sa fie parte din labirint
                pozitiePunctCurent = (int) ((Math.random() * 10)%numarObtiuni); 
                //creez pasaz intre el si labirint devanind toti labirint
                switch (obtiuni[pozitiePunctCurent]){
                    case 0: this.Labirint[linieCurenta][coloanaCurenta - 1] =true; break;
                    case 1: this.Labirint[linieCurenta + 1][coloanaCurenta] = true; break;
                    case 2: this.Labirint[linieCurenta][coloanaCurenta + 1] = true; break;
                    case 3: this.Labirint[linieCurenta - 1][coloanaCurenta]= true; break;
                }
                }
            }
        }
        if(!this.Labirint[Destinatie.getLinie()][Destinatie.getColoana()]){
            int linie = Destinatie.getLinie();
            int coloana = Destinatie.getColoana();
            this.Labirint[linie][coloana] =true;
            boolean loc = false;
            if(linie-1 >= 0 && this.Labirint[linie-1][coloana])
                loc =true;
            if(linie+1 < this.marimeLab && this.Labirint[linie+1][coloana])
                loc =true;
            if(coloana-1 >= 0 && this.Labirint[linie][coloana-1])
                loc =true;
            if(coloana+1 < this.marimeLab && this.Labirint[linie][coloana+1])
                loc =true;
            if(!loc){
                if(linie-1 >= 0 && coloana-1 >= 0 && this.Labirint[linie-1][coloana-1]){
                    this.Labirint[linie-1][coloana] = true;
                    this.Labirint[linie][coloana-1] = true;
                }else if(linie-1 >= 0 && coloana+1 < this.marimeLab && this.Labirint[linie-1][coloana+1]){
                    this.Labirint[linie-1][coloana] = true;
                    this.Labirint[linie][coloana+1] = true;
                }else if(linie+1 < this.marimeLab && coloana-1 >= 0 && this.Labirint[linie+1][coloana-1]){
                    this.Labirint[linie+1][coloana] = true;
                    this.Labirint[linie][coloana-1] = true;
                }else if(linie+1 < this.marimeLab && coloana+1 < this.marimeLab && this.Labirint[linie+1][coloana+1]){
                    this.Labirint[linie+1][coloana] = true;
                    this.Labirint[linie][coloana+1] = true;
                }
            }
        }
        for(int i=0;i<this.marimeLab;i++)
            for(int j=0;j<this.marimeLab;j++)
                this.drawCell(i, j, g);
    }

    void GenerateNewMazeUsingKruskal(Graphics g, int Plecare, Punct Destinatie) {
        boolean comlete=false;
        int nrIncercari=0;
        while(!comlete && nrIncercari<100){
            nrIncercari++;
        LinkedList<Integer> pereti=new <Integer> LinkedList();
        int linieCurenta = this.Codder.getLinie(Plecare, marimeLab);
        int coloanaCurenta = this.Codder.getColoana(Plecare, marimeLab);
        int nrClustere = 0;
        int[][] labCloneCluster = new int[this.marimeLab][this.marimeLab]; //retinr culsterele in labirint
        for(int i=0;i<this.marimeLab;i++)
            for(int j=0;j<this.marimeLab;j++)
            if(!((i==linieCurenta && j==coloanaCurenta)||(i==Destinatie.getLinie() && j==Destinatie.getColoana()))){
                    this.Labirint[i][j] = false;
                    pereti.add(this.Codder.getCodificare(i, j, this.marimeLab));
                    pereti.add(this.Codder.getCodificare(i, j, this.marimeLab));
                   // pereti.add(this.Codder.getCodificare(i, j, this.marimeLab));
                   // pereti.add(this.Codder.getCodificare(i, j, this.marimeLab));
                    labCloneCluster[i][j]=0;
            }else{
                nrClustere++;
                this.Labirint[i][j]=true;
                labCloneCluster[i][j]=nrClustere;
            }
        /*
        nrClustere++;
        this.Labirint[linieCurenta][coloanaCurenta]=true;
        this.Labirint[linieCurenta+1][coloanaCurenta]=true;
        this.Labirint[linieCurenta][coloanaCurenta+1]=true;
        labCloneCluster[linieCurenta][coloanaCurenta]=nrClustere;
        labCloneCluster[linieCurenta+1][coloanaCurenta]=nrClustere;
        labCloneCluster[linieCurenta][coloanaCurenta+1]=nrClustere;
        
        this.Labirint[linieCurenta+2][coloanaCurenta]=true;
        this.Labirint[linieCurenta][coloanaCurenta+2]=true;
        labCloneCluster[linieCurenta+2][coloanaCurenta]=nrClustere;
        labCloneCluster[linieCurenta][coloanaCurenta+2]=nrClustere;
        
        nrClustere++;        
        this.Labirint[Destinatie.getLinie()][Destinatie.getColoana()]=true;
        this.Labirint[Destinatie.getLinie()-1][Destinatie.getColoana()]=true;
        this.Labirint[Destinatie.getLinie()][Destinatie.getColoana()-1]=true;
        labCloneCluster[Destinatie.getLinie()][Destinatie.getColoana()]=nrClustere;
        labCloneCluster[Destinatie.getLinie()-1][Destinatie.getColoana()]=nrClustere;
        labCloneCluster[Destinatie.getLinie()][Destinatie.getColoana()-1]=nrClustere;
        
        this.Labirint[Destinatie.getLinie()-2][Destinatie.getColoana()]=true;
        this.Labirint[Destinatie.getLinie()][Destinatie.getColoana()-2]=true;
        labCloneCluster[Destinatie.getLinie()-2][Destinatie.getColoana()]=nrClustere;
        labCloneCluster[Destinatie.getLinie()][Destinatie.getColoana()-2]=nrClustere;
        */
        int pozitiePunctCurent,codificarePunctCurent;
        //int numarObtiuni;
   //     int obtiuni[] = new int[4];
          /*
        Codificare pbtiuni;
        0 - stanga
        1 - sus
        2 - dreapta
        3 - jos
        */
        while(!pereti.isEmpty()){
        for(int i=0;i<this.marimeLab;i++)
            for(int j=0;j<this.marimeLab;j++){
               if(labCloneCluster[i][j]!=0) {
                   setCluster(labCloneCluster,labCloneCluster[i][j],i,j);
               }
            }
            pozitiePunctCurent = (int)((Math.random()*2.0 * this.marimeLab * this.marimeLab)%pereti.size());
           // System.out.println(pozitiePunctCurent+" "+pereti.size());
            codificarePunctCurent = pereti.get(pozitiePunctCurent);
            linieCurenta = this.Codder.getLinie(codificarePunctCurent, this.marimeLab);
            coloanaCurenta = this.Codder.getColoana(codificarePunctCurent, this.marimeLab);
            pereti.remove(pozitiePunctCurent);
            
            if(!this.Labirint[linieCurenta][coloanaCurenta]){
                int rezVerificareLinie, rezVerifcareColoana;
                rezVerificareLinie = getVerificareLinie(linieCurenta,coloanaCurenta,labCloneCluster);
                rezVerifcareColoana = getVerificareColoana(linieCurenta,coloanaCurenta,labCloneCluster);
          //      System.out.println(rezVerificareLinie+" "+rezVerifcareColoana+" "+nrClustere+"++++++++");
                if(rezVerificareLinie!=1 && rezVerifcareColoana!=1){
                    if(rezVerificareLinie==2 || rezVerifcareColoana==2){
                        //2 clustere diferite
                        //this.Labirint[linieCurenta][coloanaCurenta] = true;
                        if(rezVerificareLinie==2){
                            setCluster(labCloneCluster,labCloneCluster[linieCurenta-1][coloanaCurenta],linieCurenta+1,coloanaCurenta);
                            labCloneCluster[linieCurenta][coloanaCurenta] = labCloneCluster[linieCurenta-1][coloanaCurenta];
                            this.Labirint[linieCurenta][coloanaCurenta] = true;
                        }else{
                            setCluster(labCloneCluster,labCloneCluster[linieCurenta][coloanaCurenta-1],linieCurenta,coloanaCurenta+1);
                            labCloneCluster[linieCurenta][coloanaCurenta] = labCloneCluster[linieCurenta][coloanaCurenta+1];
                            this.Labirint[linieCurenta][coloanaCurenta] = true;
                        }  
                    }else
                      /*  if((rezVerificareLinie>=3 &&rezVerifcareColoana==0) ||( rezVerifcareColoana>=3 && rezVerificareLinie==0)) {
                        if(rezVerificareLinie>=3){
                            if(rezVerificareLinie==3){
                                this.Labirint[linieCurenta][coloanaCurenta] = true;
                             //   this.Labirint[linieCurenta+1][coloanaCurenta] = true;
                                labCloneCluster[linieCurenta][coloanaCurenta] = labCloneCluster[linieCurenta-1][coloanaCurenta];
                              //  labCloneCluster[linieCurenta+1][coloanaCurenta] = labCloneCluster[linieCurenta-1][coloanaCurenta];
                            }
                           if(rezVerificareLinie==4){
                                this.Labirint[linieCurenta][coloanaCurenta] = true;
                           //  this.Labirint[linieCurenta-1][coloanaCurenta] = true;
                             labCloneCluster[linieCurenta][coloanaCurenta] = labCloneCluster[linieCurenta+1][coloanaCurenta];
                            // labCloneCluster[linieCurenta-1][coloanaCurenta] = labCloneCluster[linieCurenta+1][coloanaCurenta];
                           }
                        }else if(rezVerifcareColoana>=3){
                            if(rezVerifcareColoana==3){
                              this.Labirint[linieCurenta][coloanaCurenta] = true;
                           //   this.Labirint[linieCurenta][coloanaCurenta+1] = true;
                              labCloneCluster[linieCurenta][coloanaCurenta] = labCloneCluster[linieCurenta][coloanaCurenta-1];
                            //  labCloneCluster[linieCurenta][coloanaCurenta+1] = labCloneCluster[linieCurenta][coloanaCurenta-1];
                            }
                            if(rezVerifcareColoana==4){
                                this.Labirint[linieCurenta][coloanaCurenta] = true;
                              //  this.Labirint[linieCurenta][coloanaCurenta-1] = true;
                                labCloneCluster[linieCurenta][coloanaCurenta] = labCloneCluster[linieCurenta][coloanaCurenta+1];
                             //   labCloneCluster[linieCurenta][coloanaCurenta-1] = labCloneCluster[linieCurenta][coloanaCurenta+1];
                            }
                        }
                    
                        }else */if(rezVerificareLinie==0 && rezVerifcareColoana==0){
                        if(((int)(Math.random()*10)%2)==0){
                            this.Labirint[linieCurenta][coloanaCurenta] = true;
                            this.Labirint[linieCurenta-1][coloanaCurenta] = true;
                            this.Labirint[linieCurenta+1][coloanaCurenta] = true;
                            nrClustere++;//crearea cluster nou
                            
                            labCloneCluster[linieCurenta][coloanaCurenta] = nrClustere;
                            labCloneCluster[linieCurenta-1][coloanaCurenta] = nrClustere;
                            labCloneCluster[linieCurenta+1][coloanaCurenta] = nrClustere;
                        }else{
                            this.Labirint[linieCurenta][coloanaCurenta] = true;
                            this.Labirint[linieCurenta][coloanaCurenta-1] = true;
                            this.Labirint[linieCurenta][coloanaCurenta+1] = true;
                            nrClustere++;//crearea cluster nou
                            labCloneCluster[linieCurenta][coloanaCurenta] = nrClustere;
                            labCloneCluster[linieCurenta][coloanaCurenta-1] = nrClustere;
                            labCloneCluster[linieCurenta][coloanaCurenta+1] = nrClustere;
                        }
                    }
                }
            }
        }/*
        for(int i=0;i<this.marimeLab;i++)
            for(int j=0;j<this.marimeLab;j++){
           //     this.drawCell(i, j, g);
               if(labCloneCluster[i][j]!=0) {
                   if((i-1 >= 0 && j-1 >= 0) && Labirint[i-1][j-1] == true && labCloneCluster[i][j] != labCloneCluster[i-1][j-1]){
                       this.Labirint[i-1][j] = true;
                       labCloneCluster[i-1][j] = labCloneCluster[i][j];
                       setCluster(labCloneCluster,labCloneCluster[i][j],i-1,j-1);
                   }
         if((i+1 < this.marimeLab && j+1 < this.marimeLab) && Labirint[i+1][j+1] == true  && labCloneCluster[i][j] != labCloneCluster[i+1][j+1]){
                       this.Labirint[i+1][j] = true;
                       labCloneCluster[i+1][j] = labCloneCluster[i][j];
                       setCluster(labCloneCluster,labCloneCluster[i][j],i+1,j+1);
                   }
         if((i-1 >= 0 && j+1 < this.marimeLab) && Labirint[i-1][j+1] == true && labCloneCluster[i][j] != labCloneCluster[i-1][j+1]){
                       this.Labirint[i][j+1] = true;
                       labCloneCluster[i][j+1] = labCloneCluster[i][j];
                       setCluster(labCloneCluster,labCloneCluster[i][j],i-1,j+1);
                   }
         if((i+1 < this.marimeLab && j-1 >= 0) && Labirint[i+1][j-1] == true && labCloneCluster[i][j] != labCloneCluster[i+1][j-1]){
                       this.Labirint[i][j-1] = true;
                       labCloneCluster[i][j-1] = labCloneCluster[i][j];
                       setCluster(labCloneCluster,labCloneCluster[i][j],i+1,j-1);
                   }
             //  System.out.println(labCloneCluster[i][j]+" "+nrClustere+" i:"+i+" j:"+j);
               }
            }*/
        comlete=true;
        for(int i=0;i<this.marimeLab;i++)
            for(int j=0;j<this.marimeLab;j++)
                if(labCloneCluster[i][j]!=0 && labCloneCluster[i][j]!=labCloneCluster[Destinatie.getLinie()][Destinatie.getColoana()])comlete=false;
        }
        for(int i=0;i<this.marimeLab;i++)
            for(int j=0;j<this.marimeLab;j++)
                this.drawCell(i, j, g);
        if(nrIncercari==100){
            System.out.println("Prim");
            this.GenerateNewMazeUsingPrime(g, Plecare, Destinatie);
        }
       // System.out.println(nrIncercari);
    }


    private int[][] setCluster(int[][] labCloneCluster, int numarCluster, int linieCurenta, int coloanaCurenta) {
        /* 
       System.out.println(numarCluster+"???????????????????");
       for(int i=0;i<this.marimeLab;i++){
            for(int j=0;j<this.marimeLab;j++)
                System.out.print(labCloneCluster[i][j]+" ");
        System.out.println();}*/
        Punct Plecare = new Punct(linieCurenta,coloanaCurenta);
        ArrayList <Punct> tempTraseu = new <Punct> ArrayList();
        int lugime = 0;
        tempTraseu.add(Plecare);
        lugime ++;
        for(int i=0;i<lugime;i++){
            //Pas incercare de a vedea pe unde se mai poate duce
         if( tempTraseu.get(i).getLinie()-1 >= 0 && Labirint[tempTraseu.get(i).getLinie()-1][tempTraseu.get(i).getColoana()] == true && labCloneCluster[tempTraseu.get(i).getLinie()-1][tempTraseu.get(i).getColoana()] != numarCluster ){
             tempTraseu.add(new Punct((tempTraseu.get(i).getLinie()-1),(tempTraseu.get(i).getColoana())));
             lugime++;
             labCloneCluster[tempTraseu.get(i).getLinie()-1][tempTraseu.get(i).getColoana()] = numarCluster;
         }
         if( tempTraseu.get(i).getColoana()+1 < this.marimeLab && Labirint[tempTraseu.get(i).getLinie()][tempTraseu.get(i).getColoana()+1] == true && labCloneCluster[tempTraseu.get(i).getLinie()][tempTraseu.get(i).getColoana()+1] != numarCluster){
             tempTraseu.add(new Punct(tempTraseu.get(i).getLinie(),tempTraseu.get(i).getColoana()+1));
             lugime++;
             labCloneCluster[tempTraseu.get(i).getLinie()][tempTraseu.get(i).getColoana()+1] = numarCluster;
         }
         if( tempTraseu.get(i).getLinie()+1 < this.marimeLab && Labirint[tempTraseu.get(i).getLinie()+1][tempTraseu.get(i).getColoana()] == true && labCloneCluster[tempTraseu.get(i).getLinie()+1][tempTraseu.get(i).getColoana()] != numarCluster){
             tempTraseu.add(new Punct((tempTraseu.get(i).getLinie()+1),(tempTraseu.get(i).getColoana())));
             lugime++;
             labCloneCluster[tempTraseu.get(i).getLinie()+1][tempTraseu.get(i).getColoana()] = numarCluster;
         }
         if( tempTraseu.get(i).getColoana()-1 >= 0 && Labirint[tempTraseu.get(i).getLinie()][tempTraseu.get(i).getColoana()-1] == true && labCloneCluster[tempTraseu.get(i).getLinie()][tempTraseu.get(i).getColoana()-1] != numarCluster ){
             tempTraseu.add(new Punct((tempTraseu.get(i).getLinie()),(tempTraseu.get(i).getColoana()-1)));
             lugime++;
             labCloneCluster[tempTraseu.get(i).getLinie()][tempTraseu.get(i).getColoana()-1] = numarCluster;
         }
    }
    return labCloneCluster;
    }

    private int getVerificareLinie(int linieCurenta, int coloanaCurenta, int[][] labCloneCluster) {
        if(!(linieCurenta - 1 >= 0 && linieCurenta+1 < this.marimeLab))
            return -1;//este imposibil ca sa fie verificat
        if(!this.Labirint[linieCurenta - 1][coloanaCurenta] && !this.Labirint[linieCurenta + 1][coloanaCurenta])
            return 0;//are doar ziduri
        if(this.Labirint[linieCurenta - 1][coloanaCurenta] && this.Labirint[linieCurenta + 1][coloanaCurenta])
            if(labCloneCluster[linieCurenta - 1][coloanaCurenta] == labCloneCluster[linieCurenta + 1][coloanaCurenta])
                return 1;//are ambele casute sunt din acelasi cluster
            else return 2;//doua culstere diferite
        //doar un cluster inca doua if-ure
        if(this.Labirint[linieCurenta - 1][coloanaCurenta])
            return 3;//3 stanga
        return 4;//4 dreapta
    }

    private int getVerificareColoana(int linieCurenta, int coloanaCurenta, int[][] labCloneCluster) {
        if(!(coloanaCurenta - 1 >= 0 && coloanaCurenta+1 < this.marimeLab))
            return -1;//este imposibil ca sa fie verificat
        if(!this.Labirint[linieCurenta][coloanaCurenta - 1] && !this.Labirint[linieCurenta][coloanaCurenta + 1])
            return 0;//are doar ziduri
        if(this.Labirint[linieCurenta][coloanaCurenta - 1] && this.Labirint[linieCurenta][coloanaCurenta + 1])
            if(labCloneCluster[linieCurenta][coloanaCurenta - 1] == labCloneCluster[linieCurenta][coloanaCurenta + 1])
                return 1;//are ambele casute sunt din acelasi cluster
            else return 2;//doua culstere diferite
        //doar un cluster inca doua if-ure
        if(this.Labirint[linieCurenta][coloanaCurenta - 1])
            return 3;//3 sus
        return 4;//4 jos
    }

    void showPlecareSosire(Graphics g, Punct Plecare, Punct Destinatie) {
        //plecare
        g.setColor(Color.GREEN);
        g.fillRect( Plecare.getColoana()*this.fPrecis+1 , Plecare.getLinie()*this.fPrecis+1 , this.fPrecis-1 , this.fPrecis-1);
        
        g.setColor(Color.RED);
        g.fillRect( Destinatie.getColoana()*this.fPrecis+1 , Destinatie.getLinie()*this.fPrecis+1 , this.fPrecis-1 , this.fPrecis-1);
        
    }

    public double getPondere() {
        return pondere;
    }

    public void setPondere(double pondere) {
        this.pondere = pondere;
    }

}




