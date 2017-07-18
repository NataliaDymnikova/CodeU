import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import static java.util.Collections.nCopies;

public class Parking {
    public List<Entry<Integer, Integer>> RearrangingCars(int[] beginParking, int[] endParking) {
        List<Integer> beginCarsPosition = createCarsPositionList(beginParking);
        List<Integer> endCarsPosition = createCarsPositionList(endParking);
        List<Entry<Integer, Integer>> movements = new LinkedList<>();
        while (true) {
            int positionOfZero = beginCarsPosition.get(0);
            int shouldBeAtPositionOfZero = endParking[positionOfZero];
            if (positionOfZero != endCarsPosition.get(0)) {
                swap(positionOfZero, beginCarsPosition.get(shouldBeAtPositionOfZero), beginParking);
                swap(0, shouldBeAtPositionOfZero, beginCarsPosition);

                movements.add(new SimpleEntry<>(beginCarsPosition.get(0), positionOfZero));
                System.out.println("move from " + beginCarsPosition.get(0) + " to " + positionOfZero);
            } else {
                int carToZero = firstDifferentCar(beginCarsPosition, endCarsPosition);
                if (carToZero == -1) {
                    break;
                }

                swap(positionOfZero, beginCarsPosition.get(carToZero), beginParking);
                swap(0, carToZero, beginCarsPosition);

                movements.add(new SimpleEntry<>(beginCarsPosition.get(0), positionOfZero));
                System.out.println("move from " + beginCarsPosition.get(0) + " to " + positionOfZero);
            }
        }

        return movements;
    }

    private int firstDifferentCar(List<Integer> beginCarsPosition, List<Integer> endCarsPosition) {
        int size = beginCarsPosition.size();
        for (int i = 0; i < size; i++) {
            if (!beginCarsPosition.get(i).equals(endCarsPosition.get(i))) {
                return i;
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

    private List<Integer> createCarsPositionList(int[] parking) {
        int size = parking.length;
        List<Integer> carsPosition = new ArrayList<>(nCopies(size, 0));
        for (int i = 0; i < size; i++) {
            carsPosition.set(parking[i], i);
        }
        return carsPosition;
    }
}
