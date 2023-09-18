import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Toys {
    private List<Toy> toys;
    private List<Toy> prizeToys;

    public Toys() {
        toys = new ArrayList<>();
        prizeToys = new ArrayList<>();
    }

    public List<Toy> getToys() {
        return toys;
    }

    public void setToys(List<Toy> toys) {
        this.toys = toys;
    }

    public List<Toy> getPrizeToys() {
        return prizeToys;
    }

    public void setPrizeToys(List<Toy> prizeToys) {
        this.prizeToys = prizeToys;
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void updateToyWeight(int toyId, int weight) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setWeight(weight);
                System.out.println("Игрушка с ID " + toyId + " успешно обновлена.");
                return;
            }
        }
        System.out.println("Игрушка с ID " + toyId + " не найдена.");
    }

    public void selectPrizeToy() {
        if (!toys.isEmpty()) {
            double totalWeight = toys.stream().mapToDouble(Toy::getWeight).sum();
            Random random = new Random();
            double randomValue = random.nextDouble() * totalWeight;

            double cumulativeWeight = 0;

            for (Toy toy : toys) {
                cumulativeWeight += toy.getWeight();
                if (randomValue <= cumulativeWeight) {
                    Toy prizeToy = new Toy(toy.getId(), toy.getName(), 1, toy.getWeight());
                    toy.setQuantity(toy.getQuantity() - 1);
                    toys.removeIf(t -> t.getQuantity() == 0);
                    prizeToys.add(prizeToy);
                    break;
                }
            }
        }
    }

    private void saveToyToFile(Toy toy) {
        try (FileWriter writer = new FileWriter("prize_toys.txt", true)) {
            writer.write("Призовая игрушка: " + toy.getName() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Toy getPrizeToy() {
        if (!prizeToys.isEmpty()) {
            Toy prizeToy = prizeToys.remove(0);
            saveToyToFile(prizeToy);
            return prizeToy;
        }
        return null;
    }
}
