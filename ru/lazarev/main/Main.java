/** Продолжение задания №1.10, 2.5, 3.3, 3.5, 4.3, 6.5, 7.1, 7.2,
 * Задание 5.9
 * Городим.
 * Реализуйте граф показанный на рисунке, используя в одной и той же программе и сущности из
 * задачи 2.1.10(односторонняя дорога)и сущности из задачи 2.3.3 (двусторонняя дорога) применяя
 * их там, где это необходимо. На рисунке 66 односторонняя стрелка обозначает что возможно
 * перемещение только в одном направлении (например только из пункта Eв пункт C), а
 * двусторонняя стрелка говорит о том, что возможно перемещение в обоих направлениях
 * (например из Aв B и наоборот).
 * Задание 7.3
 * Возведение в степень.
 * Создайте метод принимающий две строки, в которых будут записаны числа X и Y. Возвращает
 * метод результат возведения X в степень Y. Для преобразования строки в число следует
 * использовать метод Integer.parseInt, а для возведения в степень метод Math.pow. Вызовите
 * разработанный метод передав туда параметры командной строки полученные точкой входа в
 * программу. Реализуйте метод так, что бы для возведения в степень и преобразования строки
 * использовались короткие имена статических методов.
 * */
package ru.lazarev.main;

import ru.lazarev.bird.Bird;
import ru.lazarev.bird.Cuckoo;
import ru.lazarev.bird.Parrot;
import ru.lazarev.bird.Sparrow;
import ru.lazarev.city.BidirectionalCity;
import ru.lazarev.city.City;
import ru.lazarev.city.Route;
import ru.lazarev.geometry.Dot;
import ru.lazarev.geometry.Dot2;

import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;
import static java.lang.System.out;

/**
 * Главный класс приложения для демонстрации работы различных сущностей:
 * Птицы, Города, Маршруты, Точки.
 * Также выполняет специфические задания, такие как возведение в степень
 * и обработка аргументов командной строки.
 */
public class Main {

    /**
     * Возводит число, представленное строкой {@code base}, в степень,
     * представленную строкой {@code exp}.
     * Использует {@link Integer#parseInt(String)} для преобразования строк в числа
     * и {@link Math#pow(double, double)} для возведения в степень.
     * В случае ошибки преобразования строк в числа, выводит сообщение
     * и возвращает {@link Double#NaN}.
     * @param base Строковое представление основания степени.
     * @param exp  Строковое представление показателя степени.
     * @return Результат возведения в степень или {@link Double#NaN} в случае ошибки.
     */
    public static double power(String base, String exp) {
        if (base == null || exp == null) {
            out.println("Ошибка в методе power: строки основания и показателя не могут быть null.");
            return Double.NaN;
        }
        double result = 0.0;
        try {
            int baseNum = parseInt(base);
            int expNum = parseInt(exp);
            result = pow(baseNum, expNum);
        } catch (NumberFormatException e) {
            out.println("Ошибка в методе power: Неверный формат числа для основания ('" + base
                    + "') или показателя ('" + exp + "'). "
                    + e.getMessage());
            return Double.NaN;
        }
        return result;
    }

    /**
     * Считывает координату (вещественное число) с консоли.
     * Повторяет запрос ввода до тех пор, пока не будет введено корректное число.
     * Обрабатывает ввод "-0.0" как "0.0".
     * @param scanner        Объект {@link Scanner} для чтения ввода.
     * @param coordinateName Имя координаты для отображения в приглашении (например, "X").
     * @return Введенное пользователем корректное значение координаты.
     */
    private static double readCoordinate(Scanner scanner, String coordinateName) {
        while (true) {
            out.print("Введите координату " + coordinateName + ": ");
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine();
                if (value == -0.0) {
                    value = 0.0;
                }
                return value;
            } else {
                out.println("Некорректный ввод. Пожалуйста, введите вещественное число.");
                String invalidInput = scanner.nextLine();
                out.println("Вы ввели: '" + invalidInput + "'");
            }
        }
    }

    /**
     * Точка входа в программу.
     * Демонстрирует работу с различными классами и выполняет задания.
     * @param args Аргументы командной строки. Ожидается, что первые два аргумента
     *             (если присутствуют) будут числами для задания на возведение в степень.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        out.println("--- Задание 1.10: Работа с классом City ---");

        out.print("Введите имя первого города: ");
        String cityName1 = scanner.nextLine();
        City city1 = new City(cityName1);

        out.print("Введите имя второго города: ");
        String cityName2 = scanner.nextLine();
        City city2 = new City(cityName2);

        out.println("Добавление пути между городами:");
        out.print("Введите стоимость пути от " + city1.getName() + " до " + city2.getName() + ": ");
        int cost1 = 0;
        boolean validInput = false;
        while (!validInput) {
            if (scanner.hasNextInt()) {
                cost1 = scanner.nextInt();
                if (cost1 == 0) {
                    out.println("Стоимость пути не может быть 0. Пожалуйста, введите другое значение.");
                } else {
                    validInput = true;
                }
            } else {
                out.println("Пожалуйста, введите корректное целое число для стоимости.");
                scanner.next();
            }
        }
        scanner.nextLine();

        city1.addPath(city2, cost1);
        out.println("\nИнформация о городах и их путях:");
        out.println(city1);
        out.println(city2);

        out.println("\n--- Задание 2.5: Поиск маршрута ---");
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
        cityF.addPath(cityA, 1);

        Route routeFD = new Route(cityF, cityD);
        out.println("Маршрут из F в D: " + routeFD);

        Route routeFC = new Route(cityF, cityC);
        out.println("Маршрут из F в C: " + routeFC);

        Route routeNonExistent = new Route(cityE, cityF);
        out.println("Маршрут из E в F: " + routeNonExistent);

        out.println("\n--- Задание 3.3: Работа с BidirectionalCity ---");
        out.print("Введите имя первого двунаправленного города: ");
        String biCityName1 = scanner.nextLine();
        BidirectionalCity biCity1 = new BidirectionalCity(biCityName1);

        out.print("Введите имя второго двунаправленного города: ");
        String biCityName2 = scanner.nextLine();
        BidirectionalCity biCity2 = new BidirectionalCity(biCityName2);

        out.println("Добавляем путь из " + biCity1.getName() + " в " + biCity2.getName()
                + " стоимостью 10 (путь станет двунаправленным)");
        biCity1.addPath(biCity2, 10);
        out.println(biCity1);
        out.println(biCity2);

        out.println("\n--- Задание 3.5 и 8.4: Работа с Dot, Dot2 и клонированием ---");
        out.println("Создание точки Dot в 2D пространстве.");
        double dotX = readCoordinate(scanner, "X для Dot");
        double dotY = readCoordinate(scanner, "Y для Dot");
        Dot originalDot = new Dot(dotX, dotY);
        out.println("Оригинальная точка Dot: " + originalDot);

        Dot clonedDot = originalDot.clone();
        if (clonedDot != null) {
            out.println("Клонированная точка Dot: " + clonedDot);
            out.println("Оригинал == Клон (по ссылке)? " + (originalDot == clonedDot));
            clonedDot.setX(readCoordinate(scanner, "новую X для клона Dot"));
            out.println("Измененный клон Dot: " + clonedDot);
            out.println("Оригинал Dot после изменения клона: " + originalDot);
        } else {
            out.println("Не удалось клонировать Dot.");
        }

        out.println("\nСоздание точки Dot2 в 3D пространстве.");
        double x = readCoordinate(scanner, "X для Dot2");
        double y = readCoordinate(scanner, "Y для Dot2");
        double z = readCoordinate(scanner, "Z для Dot2");
        Dot2 originalDot2 = new Dot2(x, y, z);
        out.println("Оригинальная точка Dot2: " + originalDot2);

        Dot2 clonedDot2 = originalDot2.clone();
        if (clonedDot2 != null) {
            out.println("Клонированная точка Dot2: " + clonedDot2);
            out.println("Оригинал Dot2 == Клон Dot2 (по ссылке)? " + (originalDot2 == clonedDot2));
            clonedDot2.setZ(readCoordinate(scanner, "новую Z для клона Dot2"));
            out.println("Измененный клон Dot2: " + clonedDot2);
            out.println("Оригинал Dot2 после изменения клона: " + originalDot2);
        } else {
            out.println("Не удалось клонировать Dot2.");
        }

        out.println("\n--- Задание 4.3: Полиморфизм птиц ---");
        Bird sparrow = new Sparrow();
        Bird cuckoo = new Cuckoo();
        out.print("Введите текст для Попугая: ");
        String parrotText = scanner.nextLine();
        Bird parrot = new Parrot(parrotText);

        out.println(sparrow.getName() + " поёт:");
        sparrow.sing();

        out.println(cuckoo.getName() + " поёт:");
        cuckoo.sing();

        out.println(parrot.getName() + " поёт:");
        parrot.sing();

        out.println("\n--- Задача 5.9: Смешанные типы городов (City и BidirectionalCity) ---");
        City mixedA = new BidirectionalCity("MixedA");
        City mixedB = new City("MixedB");
        City mixedC = new BidirectionalCity("MixedC");
        City mixedD = new BidirectionalCity("MixedD");
        City mixedE = new City("MixedE");

        mixedA.addPath(mixedB, 1);
        mixedA.addPath(mixedC, 2);
        mixedB.addPath(mixedC, 3);
        mixedD.addPath(mixedB, 4);
        mixedD.addPath(mixedE, 5);
        mixedE.addPath(mixedC, 6);

        out.println("\nИнформация о городах и их путях (смешанный тип):");
        out.println(mixedA);
        out.println(mixedB);
        out.println(mixedC);
        out.println(mixedD);
        out.println(mixedE);

        out.println("\n--- Задача 6.5: Сравнение городов (equals) ---");
        City cityEquals1 = new City("ГородX");
        City cityEquals2 = new City("ГородX");
        City cityEquals3 = new City("ГородY");
        City targetCityForPath = new City("Цель");

        cityEquals1.addPath(targetCityForPath, 10);
        cityEquals2.addPath(targetCityForPath, 10);
        cityEquals3.addPath(targetCityForPath, 10);

        out.println(cityEquals1);
        out.println(cityEquals2);
        out.println("Города '" + cityEquals1.getName() + "' и '" + cityEquals2.getName() +
                "' равны: " + cityEquals1.equals(cityEquals2));

        out.println("\n" + cityEquals1);
        out.println(cityEquals3);
        out.println("Города '" + cityEquals1.getName() + "' и '" + cityEquals3.getName() +
                "' равны: " + cityEquals1.equals(cityEquals3));

        City cityEquals4 = new BidirectionalCity("ГородX");
        cityEquals4.addPath(targetCityForPath, 10);
        out.println("\nСравнение City и BidirectionalCity с одинаковым состоянием:");
        out.println(cityEquals1);
        out.println(cityEquals4);
        out.println("Города '" + cityEquals1.getName() + "' (City) и '" + cityEquals4.getName() +
                "' (BidirectionalCity) равны: " + cityEquals1.equals(cityEquals4));

        out.println("\n--- Задача 7.3: Возведение в степень из аргументов командной строки ---");
        if (args.length < 2) {
            out.println("Ошибка: Необходимо два аргумента командной строки (основание и показатель степени).");
            out.println("Пример использования: java ru.lazarev.main.Main 2 10");
        } else {
            String argBase = args[0];
            String argExp = args[1];
            out.println("Получены аргументы: основание='" + argBase + "', показатель='" + argExp + "'");
            double powerResult = power(argBase, argExp);
            if (!Double.isNaN(powerResult)) {
                out.println("Результат возведения " + argBase + " в степень " + argExp + " равен: " + powerResult);
            }
        }
        scanner.close();
    }
}