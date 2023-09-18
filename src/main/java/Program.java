public class Program {
    public static void main(String[] args) {
        Toys toys =  Toys.getInstance();

        toys.addToy(new Toy(1, "Мяч", 10, 20));
        toys.addToy(new Toy(2, "Кукла", 5, 10));
        toys.addToy(new Toy(3, "Автомобиль", 8, 15));

        toys.updateToyWeight(1, 25);

        toys.selectPrizeToy();
        toys.selectPrizeToy();

        Toy prizeToy = toys.getPrizeToy();
        if (prizeToy != null) {
            System.out.println("Выиграна игрушка: " + prizeToy.getName());
        } else {
            System.out.println("Игра закончена, больше призовых игрушек нет.");
        }
    }
}
