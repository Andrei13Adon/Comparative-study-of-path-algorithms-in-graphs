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
public class AStarInfo extends DijkInfo{
    double costDrum;

    public AStarInfo() {
    }

    public AStarInfo(double Prioritate, double costDrum, int Tata, int PunctCurent) {
        super(Prioritate, Tata, PunctCurent);
        this.costDrum = costDrum;
    }

    public double getCostDrum() {
        return costDrum;
    }
}
