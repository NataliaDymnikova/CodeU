import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.Collections.nCopies;
import static java.util.stream.Collectors.toList;

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
        List<Movement> movements = new ArrayList<>();
        List<Integer> beginParkingList = stream(beginParking).boxed().collect(toList());
        List<Integer> endParkingList = stream(endParking).boxed().collect(toList());

        while (true) {
            int posOfZero = beginCarsPosition.get(0);
            int carToPosOfZero = endParkingList.get(posOfZero);
            int posOfCarToZeroPos = beginCarsPosition.get(carToPosOfZero);

            if (carToPosOfZero != 0) {
                makeMovement(posOfZero, posOfCarToZeroPos, beginCarsPosition, beginParkingList, movements);
            } else {
                int posCarToZero = firstPosDifferentCar(beginParkingList, endParkingList);
                if (posCarToZero == -1) {
                    break;
                }
                makeMovement(posOfZero, posCarToZero, beginCarsPosition, beginParkingList, movements);
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
    private void makeMovement(int pos1, int pos2, List<Integer> beginCarsPosition, List<Integer> beginParking, List<Movement> movements) {
        swap(0, beginParking.get(pos2), beginCarsPosition);
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
    private int firstPosDifferentCar(List<Integer> beginParking, List<Integer> endParking) {
        int size = beginParking.size();
        for (int i = 0; i < size; i++) {
            if (!beginParking.get(i).equals(endParking.get(i))) {
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

    private void swap(int pos1, int pos2, List<Integer> parking) {
        int from = parking.get(pos1);
        parking.set(pos1, parking.get(pos2));
        parking.set(pos2, from);
    }
}
