/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ReprezentareGf;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 *
 * @author STOLO
 */
public class F extends JFrame implements ActionListener {
    JButton DF,BF,Dijkstra,AStar,GreeBFS;
    JButton DiagonaleStatus,CreateNewLabirint;
    JButton Euclidian,Manhattan,Octile,Chebyshev;
    JButton PrimeGenerateMaze,KruskalGenerateMaze;
    JButton SetPlecare,SetDestinatie;
    Grafic g;
    Punct Plecare , Destinatie;
    boolean[][] Labirint;
    int marimeLab;
    CodificarePunctMatrice Codder;
    JLabel lFunctiiHeuristice, lMarimeText;
    JLabel lSchimbareSosPlec;
    JLabel lExplorare,lDrumuri;
    TextField marimeText;
    MouseHandlerEnents handlerMouse;
    JLabel lDF,lBF,lDij,lAStar,lGreedy;
    JLabel ldDF,ldBF,ldDij,ldAStar,ldGreedy;
    
    static int pixeliPush = 25;
    TextField pondereText;
    JButton PondereStatus;
    
    public F(String s , boolean[][] Labirint ,int marimeLab) {
        this.Codder = new CodificarePunctMatrice();
        this.marimeLab = marimeLab;
        Plecare = new Punct(0,0);
        Destinatie = new Punct (9,9);
        this.Labirint = Labirint;
        this.setSize(906,629);
        this.setLocation(50,50);
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(false);
        setTitle(s);
        //labirintul reprezentare grafica
        g = new Grafic(Labirint,this.marimeLab);
        g.setBounds(0, 50, 501, 501);
        g.setBackground(Color.WHITE);
        add(g);
        
        
        //mtrebuie resetat la schimbarea labirintului
        handlerMouse = new MouseHandlerEnents(this.Labirint,this.marimeLab,this.g,this);
        g.addMouseListener( handlerMouse);
        g.addMouseMotionListener(handlerMouse);
        
        //Buttons
        DF=new JButton();
        DF.setText("Depth First");
        DF.setBounds(0, 0, 100, 50);
        this.add(DF); //adugare de action Listners
        DF.addActionListener(this);
        
        BF=new JButton();
        BF.setText("Bread First");
        BF.setBounds(100, 0, 100, 50);
        this.add(BF); //adugare de action Listners
        BF.addActionListener(this);
        
        Dijkstra=new JButton();
        Dijkstra.setText("Dijkstra");
        Dijkstra.setBounds(200, 0, 100, 50);
        this.add(Dijkstra); //adugare de action Listners
        Dijkstra.addActionListener(this);
        
        AStar=new JButton();
        AStar.setText("A*");
        AStar.setBounds(300, 0, 100, 50);
        this.add(AStar); //adugare de action Listners
        AStar.addActionListener(this);
        
        GreeBFS=new JButton();
        GreeBFS.setText("Greedy BFS");
        GreeBFS.setBounds(400, 0, 100, 50);
        this.add(GreeBFS); //adugare de action Listners
        GreeBFS.addActionListener(this);
        
        //aici se seteaza ponderea daca ponderea se modifica so este on ramane on
        pondereText = new TextField();
        pondereText.setText("1.0");
        pondereText.setBounds(550, 125, 150, pixeliPush);
        this.add(pondereText);
        
        PondereStatus = new JButton();
        PondereStatus.setText("A* pondere OFF");
        PondereStatus.setBounds(700, 125, 200, pixeliPush);
        PondereStatus.setBackground(Color.RED);
        this.add(PondereStatus);
        PondereStatus.addActionListener(this);
        
        
        
        //buton care schimba starea digonalelor adica daca algoritmii utilizezea
        //parcurgerea pe diagonala su merg doar la vecini
        DiagonaleStatus = new JButton();
        DiagonaleStatus.setText("Parcurgere Diagonale OFF");
        DiagonaleStatus.setBackground(Color.RED);
        DiagonaleStatus.setBounds(600, 150 + pixeliPush, 200, 50);
        this.add(DiagonaleStatus); //adugare de action Listners
        DiagonaleStatus.addActionListener(this);
        
        lFunctiiHeuristice = new JLabel();
        lFunctiiHeuristice.setText("Selectati o functie Heuristica:");
        lFunctiiHeuristice.setBounds(625, 200 + pixeliPush, 200, 50);
        this.add(lFunctiiHeuristice);
        
        Euclidian = new JButton();
        Euclidian.setText("Euclidian ON");
        Euclidian.setBackground(Color.GREEN);
        Euclidian.setBounds(500, 250 + pixeliPush, 200, 50);
        this.add(Euclidian); //adugare de action Listners
        Euclidian.addActionListener(this);
        
        Manhattan = new JButton();
        Manhattan.setText("Manhattan OFF");
        Manhattan.setBackground(Color.RED);
        Manhattan.setBounds(700, 250 + pixeliPush, 200, 50);
        this.add(Manhattan); //adugare de action Listners
        Manhattan.addActionListener(this);
        
        Octile = new JButton();
        Octile.setText("Octile OFF");
        Octile.setBackground(Color.RED);
        Octile.setBounds(500, 300 + pixeliPush, 200, 50);
        this.add(Octile); //adugare de action Listners
        Octile.addActionListener(this);
        
        Chebyshev = new JButton();
        Chebyshev.setText("Chebyshev OFF");
        Chebyshev.setBackground(Color.RED);
        Chebyshev.setBounds(700, 300 + pixeliPush, 200, 50);
        this.add(Chebyshev); //adugare de action Listners
        Chebyshev.addActionListener(this);
        
        lMarimeText =new JLabel();
        lMarimeText.setText("Setare numarul de linii si coloane:");
        lMarimeText.setBounds(500, 0, 200, 50);
        this.add(lMarimeText);
        
        marimeText = new TextField();
        marimeText.setText("Intre 2 si 50");
        marimeText.setBounds(700, 15, 200, 20);
        this.add(marimeText);
        
        CreateNewLabirint = new JButton();
        CreateNewLabirint.setText("Creare de labirint nou cu marimea specificata");
        CreateNewLabirint.setBounds(550, 50, 300, 50);
        this.add(CreateNewLabirint);
        CreateNewLabirint.addActionListener(this);
        
        PrimeGenerateMaze = new JButton();
        PrimeGenerateMaze.setText("Generare labirint nou cu Prim");
        PrimeGenerateMaze.setBounds(0, 550 , 250, 50);
        this.add(PrimeGenerateMaze);
        PrimeGenerateMaze.addActionListener(this);
        
        KruskalGenerateMaze = new JButton();
        KruskalGenerateMaze.setText("Generare labirint nou cu Kruskal");
        KruskalGenerateMaze.setBounds(250, 550 , 250, 50);
        this.add(KruskalGenerateMaze);
        KruskalGenerateMaze.addActionListener(this);
        
        this.lSchimbareSosPlec = new JLabel();
        lSchimbareSosPlec.setText("Apasati un buton si dupa aceea apasati pe casuta dorita din labirint:");
        lSchimbareSosPlec.setBounds(510, 350 + pixeliPush , 390, 50);
        this.add(lSchimbareSosPlec);
        
        SetPlecare = new JButton();
        SetPlecare.setText("Setare Punct Plecare");
        SetPlecare.setBounds(500, 400 + pixeliPush, 200, 50);
        this.add(SetPlecare); //adugare de action Listners
        SetPlecare.addActionListener(this);
        
        SetDestinatie = new JButton();
        SetDestinatie.setText("Setare Punct Destinatie");
        SetDestinatie.setBounds(700, 400 + pixeliPush, 200, 50);
        this.add(SetDestinatie); //adugare de action Listners
        SetDestinatie.addActionListener(this);
        
        this.lExplorare = new JLabel();
        lExplorare.setText("Numarul de casute pe care le exploreaza fiecare algoritm:");
        lExplorare.setBounds(535, 450 + pixeliPush , 365, 20);
        this.add(lExplorare);
        //lDF,lBF,lDij,lAStar,lGreedy;
        //470
        
        lDF = new JLabel();
        lDF.setText("DF: 0");
        lDF.setBounds(501, 470 + pixeliPush, 60, 40);
        this.add(lDF);
        
        lBF = new JLabel();
        lBF.setText("BF: 0");
        lBF.setBounds(560, 470 + pixeliPush, 60, 40);
        this.add(lBF);
        
        lDij = new JLabel();
        lDij.setText("Dijkstra: 0");
        lDij.setBounds(620, 470 + pixeliPush, 90, 40);
        this.add(lDij);
        
        lAStar = new JLabel();
        lAStar.setText("AStar: 0");
        lAStar.setBounds(710, 470 + pixeliPush, 90, 40);
        this.add(lAStar);
        
        lGreedy = new JLabel();
        lGreedy.setText("GreeBFS: 0");
        lGreedy.setBounds(800, 470 + pixeliPush, 100, 40);
        this.add(lGreedy);
        
        this.lDrumuri = new JLabel();
        lDrumuri.setText("Numarul de casute pe care le are drumul generat de fiecare algoritm:");
        lDrumuri.setBounds(501, 510 + pixeliPush, 399, 20);
        this.add(lDrumuri);
        
        ldDF = new JLabel();
        ldDF.setText("DF: 0");
        ldDF.setBounds(501, 530 + pixeliPush, 60, 40);
        this.add(ldDF);
        
        ldBF = new JLabel();
        ldBF.setText("BF: 0");
        ldBF.setBounds(560, 530 + pixeliPush, 60, 40);
        this.add(ldBF);
        
        ldDij = new JLabel();
        ldDij.setText("Dijkstra: 0");
        ldDij.setBounds(620, 530 + pixeliPush, 90, 40);
        this.add(ldDij);
        
        ldAStar = new JLabel();
        ldAStar.setText("AStar: 0");
        ldAStar.setBounds(710, 530 + pixeliPush, 90, 40);
        this.add(ldAStar);
        
        ldGreedy = new JLabel();
        ldGreedy.setText("GreeBFS: 0");
        ldGreedy.setBounds(800, 530 + pixeliPush, 100, 40);
        this.add(ldGreedy);
                
        addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
              //  System.exit(0);
                e.getWindow().dispose();
            }
        } );
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource()== DF){
            try{
                g.DF = 0;
                g.dDF = 0;
                g.init(g.getGraphics());
                g.showPlecareSosire(g.getGraphics(),Plecare , Destinatie);
                g.DepthFirst(g.getGraphics(), this.coloneLabirint(),Plecare.getLinie(),Plecare.getColoana(),Destinatie,1);
                g.afisRez(g.getGraphics());
                this.lDF.setText("DF: "+g.DF);
                this.ldDF.setText("DF: "+g.dDF);
           }catch(Exception ex){
                System.out.println(ex);
                System.exit(0);
            }
        }
         if(e.getSource()== BF){
            try{
                g.BF = 0;
                g.dBF = 0;
                g.init(g.getGraphics());
                g.showPlecareSosire(g.getGraphics(),Plecare , Destinatie);
                g.BreakFirst(g.getGraphics(), this.coloneLabirint(),Plecare,Destinatie,0);
                this.lBF.setText("BF: "+g.BF);
                this.ldBF.setText("BF: "+g.dBF);
           }catch(Exception ex){
                System.out.println(ex);
                System.exit(0);
            }
        }
         if(e.getSource()== Dijkstra){
            try{
                g.Dij = 0;
                g.dDij= 0;
                g.init(g.getGraphics());
                g.showPlecareSosire(g.getGraphics(),Plecare , Destinatie);
                g.Dijkstra(g.getGraphics(), this.coloneLabirint(),this.Codder.getCodificare(Plecare.getLinie(), Plecare.getColoana(), marimeLab),this.Codder.getCodificare(Destinatie.getLinie(), Destinatie.getColoana(), marimeLab));
                this.lDij.setText("Dijkstra: "+g.Dij);
                this.ldDij.setText("Dijkstra: "+g.dDij);
           }catch(Exception ex){
                System.out.println(ex);
                System.exit(0);
            }
        }
         if(e.getSource()== AStar){
            try{
                g.AStar = 0;
                g.dDij= 0;
                g.init(g.getGraphics());
                g.showPlecareSosire(g.getGraphics(),Plecare , Destinatie);
                g.AStarAlg(g.getGraphics(), this.coloneLabirint(),this.Codder.getCodificare(Plecare.getLinie(), Plecare.getColoana(), marimeLab),this.Codder.getCodificare(Destinatie.getLinie(), Destinatie.getColoana(), marimeLab));
                this.lAStar.setText("AStar: "+g.AStar);
                this.ldAStar.setText("AStar: "+g.dDij);
           }catch(Exception ex){
                System.out.println(ex);
                System.exit(0);
            }
        }
         if(e.getSource()== GreeBFS){
            try{
                g.Greedy = 0;
                g.dDij= 0;
                g.init(g.getGraphics());
                g.showPlecareSosire(g.getGraphics(),Plecare , Destinatie);
                g.GreeBFSAlg(g.getGraphics(), this.coloneLabirint(),this.Codder.getCodificare(Plecare.getLinie(), Plecare.getColoana(), marimeLab),this.Codder.getCodificare(Destinatie.getLinie(), Destinatie.getColoana(), marimeLab));
                this.lGreedy.setText("GreeBFS: "+g.Greedy);
                this.ldGreedy.setText("GreeBFS: "+g.dDij);
           }catch(Exception ex){
                System.out.println(ex);
                System.exit(0);
            }
        }
         if(e.getSource()== DiagonaleStatus){
            try{
                if(g.getDiagonealeStatus()){
                    g.setDiagonealeStatus(false);
                    DiagonaleStatus.setText("Parcurgere Diagonale OFF");
                    DiagonaleStatus.setBackground(Color.RED);
                }else{
                    g.setDiagonealeStatus(true);
                    DiagonaleStatus.setBackground(Color.GREEN);
                    DiagonaleStatus.setText("Parcurgere Diagonale ON");
                }
           }catch(Exception ex){
                System.out.println(ex);
                System.exit(0);
            }
        }
         if(e.getSource()== Euclidian){
            try{
                if(!g.getEuclidian()){
                    g.setEuclidian(true);
                    Euclidian.setBackground(Color.GREEN);
                    Euclidian.setText("Euclidian ON");
                    g.setManhattan(false);
                    Manhattan.setBackground(Color.RED);
                    Manhattan.setText("Manhattan OFF");
                    g.setOctile(false);
                    Octile.setBackground(Color.RED);
                    Octile.setText("Octile OFF");
                    g.setChebyshev(false);
                    Chebyshev.setBackground(Color.RED);
                    Chebyshev.setText("Chebyshev OFF");
                }
           }catch(Exception ex){
                System.out.println(ex);
                System.exit(0);
            }
        }
         if(e.getSource()== Manhattan){
            try{
                if(!g.getManhattan()){
                    g.setEuclidian(false);
                    Euclidian.setBackground(Color.RED);
                    Euclidian.setText("Euclidian OFF");
                    g.setManhattan(true);
                    Manhattan.setBackground(Color.GREEN);
                    Manhattan.setText("Manhattan ON");
                    g.setOctile(false);
                    Octile.setBackground(Color.RED);
                    Octile.setText("Octile OFF");
                    g.setChebyshev(false);
                    Chebyshev.setBackground(Color.RED);
                    Chebyshev.setText("Chebyshev OFF");
                }
           }catch(Exception ex){
                System.out.println(ex);
                System.exit(0);
            }
        }
         if(e.getSource()== Octile){
            try{
                if(!g.getOctile()){
                    g.setEuclidian(false);
                    Euclidian.setBackground(Color.RED);
                    Euclidian.setText("Euclidian OFF");
                    g.setManhattan(false);
                    Manhattan.setBackground(Color.RED);
                    Manhattan.setText("Manhattan OFF");
                    g.setOctile(true);
                    Octile.setBackground(Color.GREEN);
                    Octile.setText("Octile ON");
                    g.setChebyshev(false);
                    Chebyshev.setBackground(Color.RED);
                    Chebyshev.setText("Chebyshev OFF");
                }
           }catch(Exception ex){
                System.out.println(ex);
                System.exit(0);
            }
        }
         if(e.getSource()== Chebyshev){
            try{
                if(!g.getChebyshev()){
                    g.setEuclidian(false);
                    Euclidian.setBackground(Color.RED);
                    Euclidian.setText("Euclidian OFF");
                    g.setManhattan(false);
                    Manhattan.setBackground(Color.RED);
                    Manhattan.setText("Manhattan OFF");
                    g.setOctile(false);
                    Octile.setBackground(Color.RED);
                    Octile.setText("Octile OFF");
                    g.setChebyshev(true);
                    Chebyshev.setBackground(Color.GREEN);
                    Chebyshev.setText("Chebyshev ON");
                }
           }catch(Exception ex){
                System.out.println(ex);
                System.exit(0);
            }
        }
          if(e.getSource()== CreateNewLabirint){
            try{
                String temp;
                temp = this.marimeText.getText();
                if(temp.isEmpty())
                    this.marimeText.setText("Introduceti numar intre 2 si 50");
                else{
                    int numar = getNumar(temp.toCharArray());
                    if(numar == -2 )
                        this.marimeText.setText("Necesita cifre intre 2 si 50");
                    else
                        if(numar == -1 || numar < 2 || numar > 50)
                            this.marimeText.setText("Introduceti numar intre 2 si 50");
                        else{
                            //fa ceva
                            this.Labirint = new boolean[numar][numar];
                            for(int i=0;i<numar;i++)
                                for(int j=0;j<numar;j++)
                                    this.Labirint[i][j] = true;
                            if(this.Destinatie.getLinie()== this.marimeLab-1 && this.Destinatie.getColoana()== this.marimeLab-1)
                                this.Destinatie =new Punct(numar-1,numar-1);
                            
                             this.marimeLab = numar;
                             if(this.Plecare.getLinie()>= this.marimeLab || this.Plecare.getColoana()>= this.marimeLab)
                                this.Plecare =new Punct(0,0);
                            if(this.Destinatie.getLinie()>= this.marimeLab || this.Destinatie.getColoana()>= this.marimeLab)
                                this.Destinatie =new Punct(numar-1,numar-1);
                            g.resetLabirint(Labirint,this.marimeLab);
                            g.paint(g.getGraphics());
                            handlerMouse.reset(this.Labirint,this.marimeLab,this.g);
                            
                            this.lDF.setText("DF: "+g.DF);
                            this.ldDF.setText("DF: "+g.dDF);
                            this.lBF.setText("BF: "+g.BF);
                            this.ldBF.setText("BF: "+g.dBF);
                            this.lDij.setText("Dijkstra: "+g.Dij);
                            this.ldDij.setText("Dijkstra: "+g.dDij);
                            this.lAStar.setText("AStar: "+g.AStar);
                            this.ldAStar.setText("AStar: "+g.dDij);
                            this.lGreedy.setText("GreeBFS: "+g.Greedy);
                            this.ldGreedy.setText("GreeBFS: "+g.dDij);
                        }
                }
               g.showPlecareSosire(g.getGraphics(),Plecare , Destinatie);
           }catch(Exception ex){
                System.out.println(ex);
                System.exit(0);
            }
        }
          if(e.getSource()== PrimeGenerateMaze){
            try{
                g.GenerateNewMazeUsingPrime(g.getGraphics(),this.Codder.getCodificare(Plecare.getLinie(), Plecare.getColoana(), marimeLab),Destinatie);
                g.showPlecareSosire(g.getGraphics(),Plecare , Destinatie);
                this.lDF.setText("DF: "+0);
                this.ldDF.setText("DF: "+0);
                this.lBF.setText("BF: "+0);
                this.ldBF.setText("BF: "+0);
                this.lDij.setText("Dijkstra: "+0);
                this.ldDij.setText("Dijkstra: "+0);
                this.lAStar.setText("AStar: "+0);
                this.ldAStar.setText("AStar: "+0);
                this.lGreedy.setText("GreeBFS: "+0);
                this.ldGreedy.setText("GreeBFS: "+0);
           }catch(Exception ex){
                System.out.println(ex);
                System.exit(0);
            }
        }
          if(e.getSource()== KruskalGenerateMaze){
            try{
                g.GenerateNewMazeUsingKruskal(g.getGraphics(),this.Codder.getCodificare(Plecare.getLinie(), Plecare.getColoana(), marimeLab),Destinatie);
                g.showPlecareSosire(g.getGraphics(),Plecare , Destinatie);
                this.lDF.setText("DF: "+0);
                this.ldDF.setText("DF: "+0);
                this.lBF.setText("BF: "+0);
                this.ldBF.setText("BF: "+0);
                this.lDij.setText("Dijkstra: "+0);
                this.ldDij.setText("Dijkstra: "+0);
                this.lAStar.setText("AStar: "+0);
                this.ldAStar.setText("AStar: "+0);
                this.lGreedy.setText("GreeBFS: "+0);
                this.ldGreedy.setText("GreeBFS: "+0);
           }catch(Exception ex){
                System.out.println(ex);
                System.exit(0);
            }
        }
          if(e.getSource()== SetPlecare){
            try{
                this.handlerMouse.setPlecare();
           }catch(Exception ex){
                System.out.println(ex);
                System.exit(0);
            }
        }
          if(e.getSource()== SetDestinatie){
            try{
                this.handlerMouse.setDestinatie();
           }catch(Exception ex){
                System.out.println(ex);
                System.exit(0);
            }
        }
          if(e.getSource()== PondereStatus){
            try{
                String temp;
                temp = this.pondereText.getText();
                if(temp.isEmpty()){
                    this.pondereText.setText("1.0");
                    this.PondereStatus.setText("A* pondere OFF");
                    this.PondereStatus.setBackground(Color.RED);
                    g.setPondere(1.0);
                }else{
                    try{
                    double numar = Double.parseDouble(temp);
                    if(this.PondereStatus.getBackground() == Color.RED){
                        this.pondereText.setText(numar+"");
                        this.PondereStatus.setText("A* pondere ON");
                        this.PondereStatus.setBackground(Color.GREEN);
                        g.setPondere(numar);
                    }else
                        if(g.getPondere() != numar){
                            this.pondereText.setText(numar+"");
                            g.setPondere(numar);
                        }else{
                            this.PondereStatus.setText("A* pondere OFF");
                            this.PondereStatus.setBackground(Color.RED);
                            g.setPondere(1.0);
                        }
                        
                    }catch(Exception ex){
                        this.pondereText.setText("1.0");
                        this.PondereStatus.setText("A* pondere OFF");
                        this.PondereStatus.setBackground(Color.RED);
                        g.setPondere(1.0);
                    }
                    
                }
           }catch(Exception ex){
                System.out.println(ex);
                System.exit(0);
            }
        }
    }
    
    public boolean[][] coloneLabirint(){
        boolean temp[][] = new boolean[this.marimeLab][this.marimeLab]; 
        for(int i=0;i<this.marimeLab;i++)
            for(int j=0;j<this.marimeLab;j++)
                temp[i][j] = this.Labirint[i][j];
        return temp;
    }

    private int getNumar(char[] vectorExtrasNumar) {
        //-1 numar prea mare
        //-2 nu este numar
        if(vectorExtrasNumar.length>2)
            return -1;
        int numar = 0;
        for(int i=0;i<vectorExtrasNumar.length;i++)
            if(vectorExtrasNumar[i]<='9'&&vectorExtrasNumar[i]>='0')
                numar=numar*10+vectorExtrasNumar[i]-'0';
            else
                return -2;
        return numar;
    }

    void setPlecare(int linie, int coloana) {
        this.Plecare = new Punct(linie,coloana);
        this.Labirint[linie][coloana] = true;
        g.init(g.getGraphics());
        g.showPlecareSosire(g.getGraphics(),Plecare , Destinatie);
    }

    void setDestinatie(int linie, int coloana) {
        this.Destinatie = new Punct(linie,coloana);
        this.Labirint[linie][coloana] = true;
        g.init(g.getGraphics());
        g.showPlecareSosire(g.getGraphics(),Plecare , Destinatie);
    }
}
