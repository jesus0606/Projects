package Uber;

public class Main {
    public static void main(String[] args) {
        Client client1 = new Client();

        client1.setClientId(001);
        client1.setName("Jesus");
        client1.setPhone("111111111");

        Car car1 = new Car();


        Driver driver1 = new Driver();

        driver1.setName("Jose");
        driver1.setDriverId(002);
        driver1.setPhone("2222222222");
        driver1.setCar(car1);



    }
}
