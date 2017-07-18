import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.logging.Logger;

import static java.util.Collections.nCopies;

/**
 * Parking class for rearranging cars
 */
public class Parking {
    private final static Logger logger = Logger.getLogger(Parking.class.getName());

    /**
     * Return sequence of movement cars (from which places cars move to which).
     * Function changes beginParking.
     *
     * Complexity: O(n)
     *
     * @param beginParking -- sequence of cars on the beginning (from zero)
     * @param endParking -- sequence of cars on the end
     * @return -- sequence of movements
     */
    public List<Entry<Integer, Integer>> RearrangingCars(int[] beginParking, int[] endParking) {
        List<Integer> beginCarsPosition = createCarsPositionList(beginParking);
        List<Integer> endCarsPosition = createCarsPositionList(endParking);
        List<Entry<Integer, Integer>> movements = new LinkedList<>();

        while (true) {
            int posOfZero = beginCarsPosition.get(0);
            int carToPosOfZero = endParking[posOfZero];
            int posOfCarToZeroPos = beginCarsPosition.get(carToPosOfZero);

            if (posOfZero != endCarsPosition.get(0)) {
                makeMovement(posOfZero, posOfCarToZeroPos, beginCarsPosition, beginParking, movements);
            } else {
                int posCarToZero = firstPosDifferentCar(beginCarsPosition, endCarsPosition);
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
     * @param pos1 -- pos of 1st car
     * @param pos2 -- pos of 2nd car
     * @param beginCarsPosition -- list of positions cars where cars will be swapped
     * @param beginParking -- array of positions cars where cars will be swapped
     * @param movements -- list to adding movement
     */
    private void makeMovement(int pos1, int pos2, List<Integer> beginCarsPosition, int[] beginParking, List<Entry<Integer, Integer>> movements) {
        swap(0, beginParking[pos2], beginCarsPosition);
        swap(pos1, pos2, beginParking);

        movements.add(new SimpleEntry<>(beginCarsPosition.get(0), pos1));
        logger.info("move from " + beginCarsPosition.get(0) + " to " + pos1);
    }

    /**
     * Find place of first car where car is not on the right place
     * @param beginCarsPosition -- current cars positions
     * @param endCarsPosition -- cars positions on the end
     * @return -- position of th car, -1 if all cars are on the right places
     */
    private int firstPosDifferentCar(List<Integer> beginCarsPosition, List<Integer> endCarsPosition) {
        int size = beginCarsPosition.size();
        for (int i = 0; i < size; i++) {
            if (!beginCarsPosition.get(i).equals(endCarsPosition.get(i))) {
                return beginCarsPosition.get(i);
            }
        }
        return -1;
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


    /**
     * Create list of cars position from sequence of cars
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
}
