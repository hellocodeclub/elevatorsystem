package com.hellocodeclub.elevatorsystem;


public class ElevatorState {

    int[][] state;

    ElevatorState(){
        state = new int[][]{{0, 0, 0, 0, 1}, {0, 0, 0, 0, 1}, {0, 0, 0, 0, 1}};
    }

    public int findCloserLift(int destinationFloor){
        int liftMinimumJourney = 0;
        int minDistance = 4;
        for(int lift=0;lift<state.length;lift++){

            int indexOfLift = findFloorWhereLiftIs(state[lift]);
            int distance = Math.abs(destinationFloor-indexOfLift);
            if(distance<minDistance){
                minDistance = distance;
                liftMinimumJourney=lift;
            }
        }
        return liftMinimumJourney;
    }

    public void moveLiftToFloor(int destinationFloor, int selectedLift) {

        int liftCurrentFloor = findFloorWhereLiftIs(state[selectedLift]);

        state[selectedLift][liftCurrentFloor]=0;
        state[selectedLift][destinationFloor]=1;


    }

    public int findFloorWhereLiftIs(int[] floors){
        for(int i=0;i<floors.length;i++){
            if(floors[i]==1){
                return i;
            }
        }
        return 0;
    }

}
