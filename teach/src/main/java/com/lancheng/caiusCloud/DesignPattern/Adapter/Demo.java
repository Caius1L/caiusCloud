package com.lancheng.caiusCloud.DesignPattern.Adapter;

public class Demo {

    public static void main(String[] args) {
        RoundHole roundHole = new RoundHole(5);
        RoundPeg roundPeg = new RoundPeg(5);
        if (roundHole.fits(roundPeg)) {
            System.out.println("Round peg r5 fits round hole r5");
        }

        SquarePeg smallSqPeg = new SquarePeg(2);
        SquarePeg largeSqPeg = new SquarePeg(20);
        SquarePegAdapter MSquarePegAdapter = new SquarePegAdapter(smallSqPeg);
        SquarePegAdapter LSquarePegAdapter = new SquarePegAdapter(largeSqPeg);
        if(roundHole.fits(MSquarePegAdapter)){
            System.out.println("Square peg w2 fits round hole r5");
        }
        if(!roundHole.fits(LSquarePegAdapter)){
            System.out.println("Square peg w2 does not fit into round hole r5");
        }
    }
}
