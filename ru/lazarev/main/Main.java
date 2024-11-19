package ru.lazarev.main;
import ru.lazarev.bird.*;
import ru.lazarev.city.*;
import ru.lazarev.geometry.*;

import static java.lang.System.out;
import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;
import java.util.*;

public class Main {
    public static double power(String base, String exp) {
        double result;
        try {
            int b = parseInt(base);
            int e = parseInt(exp);
            result = pow(b, e);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный ввод");
        }
        return result;
    }

    private static double readCoordinate(Scanner scanner, String coordinateName) {
        while (true) {
            out.print("Введите координату " + coordinateName + ": ");
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine();
                if (value == -0.0){
                    value = 0.0;
                }
                return value;
            } else {
                out.println("Некорректный ввод");
                scanner.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        out.println("Задание 1.10");
        System.out.print("Введите имя первого города: ");
        String cityName1 = scanner.nextLine();
        City city1 = new City(cityName1);
        System.out.print("Введите имя второго города: ");
        String cityName2 = scanner.nextLine();
        City city2 = new City(cityName2);
        System.out.println("Добавление пути между городами:");
        System.out.print("Введите стоимость пути от " + city1.getName() + " до " + city2.getName() + ": ");
        while (!scanner.hasNextInt()) {
            System.out.println("Пожалуйста, введите корректное целое число.");
            scanner.next();
        }
        int cost1 = scanner.nextInt();
        scanner.nextLine();

        city1.addPath(city2, cost1);

        System.out.println("\nИнформация о городах и их путях:");
        System.out.println(city1);

        out.println("Попытка добавить ещё один путь");
        city1.addPath(city2, 12345);
        city2.addPath(city1, 12345);
        System.out.println("\nИнформация о городах и их путях:");
        System.out.println(city1);

        out.println("Удаление пути из города " + city1.getName() + " в город " + city2.getName());
        city1.removePath(city2);

        System.out.println("\nОбновленная информация о городах и их путях:");
        System.out.println(city1);

        out.println("\nЗадание 2.5");
        City cityA = new City("A");
        City cityB = new City("B");
        City cityC = new City("C");
        City cityD = new City("D");
        City cityE = new City("E");
        City cityF = new City("F");

        cityA.addPath(cityB, 5);
        cityA.addPath(cityF, 1);
        cityA.addPath(cityD, 6);

        cityB.addPath(cityA, 5);
        cityB.addPath(cityC, 3);

        cityC.addPath(cityB, 3);
        cityC.addPath(cityD, 4);

        cityD.addPath(cityC, 4);
        cityD.addPath(cityE, 2);
        cityD.addPath(cityA, 6);

        cityE.addPath(cityF, 2);

        cityF.addPath(cityB, 1);
        cityF.addPath(cityE, 2);

        Route route = new Route(cityF, cityD);
        out.println("Маршрут из F в D: " + route);

        out.println("\nЗадание 3.3");
        out.println("Введите имя первого города: ");
        String BicityName1 = scanner.nextLine();
        BidirectionalCity biCity1 = new BidirectionalCity(BicityName1);

        out.println("Введите имя второго города: ");
        String BicityName2 = scanner.nextLine();
        BidirectionalCity biCity2 = new BidirectionalCity(BicityName2);

        out.println("Добавляем путь из " + biCity1.getName() + " в " + biCity2.getName());
        biCity1.addPath(biCity2, 10);
        out.println(biCity1);
        out.println(biCity2);

        biCity1.removePath(biCity2);
        out.println("После удаления пути:");
        out.println(biCity1);
        out.println(biCity2);

        out.println("\nЗадание 3.5");

        out.println("Создание точки в 3D пространстве.");
        double x = readCoordinate(scanner, "X");
        double y = readCoordinate(scanner, "Y");
        double z = readCoordinate(scanner, "Z");

        Dot2 point = new Dot2(x, y, z);
        out.println("Создана точка: " + point);

        out.println("\nТеперь обновим координаты точки.");
        point.setX(readCoordinate(scanner, "новую X"));
        point.setY(readCoordinate(scanner, "новую Y"));
        point.setZ(readCoordinate(scanner, "новую Z"));

        out.println("Обновленные координаты точки: " + point);

        out.println("\nЗадание 4.3");
        Bird sparrow = new Sparrow();
        Bird cuckoo = new Cuckoo();
        Bird parrot = new Parrot("Всем привет я попугай");

        out.println(sparrow.getName() + " поёт");
        sparrow.sing();

        out.println(cuckoo.getName() + " поёт");
        cuckoo.sing();

        out.println(parrot.getName() + " поёт");
        parrot.sing();

        out.println("\nЗадача 5.9");
        City cityA2 = new BidirectionalCity("A");
        City cityB2 = new City("B");
        City cityC2 = new BidirectionalCity("C");
        City cityD2 = new BidirectionalCity("D");
        City cityE2 = new City("E");

        cityA2.addPath(cityB2, 1);
        cityA2.addPath(cityC2, 1);

        cityB2.addPath(cityC2, 1);

        cityD2.addPath(cityB2, 1);
        cityD2.addPath(cityE2, 1);

        cityE2.addPath(cityC2, 1);

        out.println("\nИнформация о городах и их путях:");
        out.println(cityA2);
        out.println(cityB2);
        out.println(cityC2);
        out.println(cityD2);
        out.println(cityE2);

        out.println("\nЗадача 6.5");
        City A1 = new City("A1");
        City A2 = new City("A2");
        City B = new City("B");

        A1.addPath(B, 10);
        A2.addPath(B, 10);
        out.println(A1);
        out.println(A2);
        System.out.println("Города A1 и A2 равны: " + A1.equals(A2));

        A1.addPath(A2, 10);
        out.println(A1);
        out.println(A2);
        System.out.println("Города A1 и A2 равны: " + A1.equals(A2));

        out.println("\nЗадача 7.3");
        if (args.length < 2) {
            out.println("Неверный ввод");
            return;
        }
        String x1 = args[0];
        String y1 = args[1];
        try {
            double result = power(x1, y1);
            out.println("Результат возведения " + x1 + " в степень " + y1 + " равен: " + result);
        } catch (NumberFormatException e) {
            out.println("Неверный ввод");
        }
    }

}