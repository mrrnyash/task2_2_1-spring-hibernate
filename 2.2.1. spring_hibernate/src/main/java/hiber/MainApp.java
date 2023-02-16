package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        // Add five users
        User user1 = new User("Ivan", "Ivanov", "user1@mail.ru");
        Car toyota = new Car("Toyota Wish", 955);
        user1.setCar(toyota);

        User user2 = new User("Maria", "Petrova", "user2@mail.ru");
        Car mazda = new Car("Mazda CX-7", 600);
        user2.setCar(mazda);

        User user3 = new User("Evgeniy", "Sidorov", "user3@gmail.com");
        Car bmw = new Car("BMW X5", 693);
        user3.setCar(bmw);

        User user4 = new User("Elena", "Sergeeva", "user4@yahoo.com");
        Car lexus = new Car("Lexus GX460", 154);
        user4.setCar(lexus);

        User user5 = new User("Andrey", "Petrov", "user5@mail.ru");
        Car ford = new Car("Ford Transit", 250);
        user5.setCar(ford);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);
        userService.add(user5);


        // List all users in database
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user);
        }

        // Let's find user by his car model and series
        System.out.println(userService.getUserByCar("BMW X5", 693));

        context.close();
    }
}
