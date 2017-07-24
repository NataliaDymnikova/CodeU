import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import static java.util.Collections.nCopies;

/**
 * Parking class for rearranging cars
 */
public class Parking {
    /**
     * Return sequence of movement cars (from which places cars move to which).
     * Function changes beginParking.
     * <p>
     * Complexity: O(n)
     *
     * @param beginParking -- sequence of cars on the beginning (from zero)
     * @param endParking   -- sequence of cars on the end
     * @return -- sequence of movements
     */
    public List<Movement> RearrangingCars(int[] beginParking, int[] endParking) {
        List<Integer> beginCarsPosition = createCarsPositionList(beginParking);
        List<Movement> movements = new LinkedList<>();

        while (true) {
            int posOfZero = beginCarsPosition.get(0);
            int carToPosOfZero = endParking[posOfZero];
            int posOfCarToZeroPos = beginCarsPosition.get(carToPosOfZero);

            if (endParking[posOfZero] != 0) {
                makeMovement(posOfZero, posOfCarToZeroPos, beginCarsPosition, beginParking, movements);
            } else {
                int posCarToZero = firstPosDifferentCar(beginParking, endParking);
                if (posCarToZero == -1) {
                    break;
                }
                makeMovement(posOfZero, posCarToZero, beginCarsPosition, beginParking, movements);
            }
        }

        return movements;
    }

    /**
     * Change cars on beginParking and on beginCarsPosition
     *
     * @param pos1              -- pos of 1st car
     * @param pos2              -- pos of 2nd car
     * @param beginCarsPosition -- list of positions cars where cars will be swapped
     * @param beginParking      -- array of positions cars where cars will be swapped
     * @param movements         -- list to adding movement
     */
    private void makeMovement(int pos1, int pos2, List<Integer> beginCarsPosition, int[] beginParking, List<Movement> movements) {
        swap(0, beginParking[pos2], beginCarsPosition);
        swap(pos1, pos2, beginParking);

        Movement movement = new Movement(beginCarsPosition.get(0), pos1);
        movements.add(movement);
        movement.print();
    }

    /**
     * Find first car place where car is not on the right place
     *
     * @param beginParking -- current sequence of cars
     * @param endParking   -- sequence of cars on the end
     * @return -- position of the car, -1 if all cars are on the right places
     */
    private int firstPosDifferentCar(int[] beginParking, int[] endParking) {
        int size = beginParking.length;
        for (int i = 0; i < size; i++) {
            if (beginParking[i] != endParking[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Create list of cars position from sequence of cars
     *
     * @param parking -- sequence of cars
     * @return -- cars positions list
     */
    private List<Integer> createCarsPositionList(int[] parking) {
        int size = parking.length;
        List<Integer> carsPosition = new ArrayList<>(nCopies(size, 0));
        for (int i = 0; i < size; i++) {
            carsPosition.set(parking[i], i);
        }
        return carsPosition;
    }


    private void swap(int pos1, int pos2, int[] parking) {
        parking[pos1] ^= parking[pos2];
        parking[pos2] ^= parking[pos1];
        parking[pos1] ^= parking[pos2];
    }

    private void swap(int pos1, int pos2, List<Integer> parking) {
        parking.set(pos1, parking.get(pos1) ^ parking.get(pos2));
        parking.set(pos2, parking.get(pos2) ^ parking.get(pos1));
        parking.set(pos1, parking.get(pos1) ^ parking.get(pos2));
    }
}
