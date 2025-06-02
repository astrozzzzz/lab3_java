/** Задание №2.5
 * Маршрут.
 * Данная задача предполагает разработку новой сущности на основе той, что была получена в
 * задаче 1.4.8 (Город). Сущность Город может быть доработана по своему усмотрению для более
 * удобного использования.
 * Основная идея задачи в разработке такой сущности, которая будет представлять собой маршрут
 * между двумя городами. Данный маршрут в любой момент времени можно получить как массив
 * для дальнейшего использования. Сущность Маршрут имеет следующие характеристики:
 *  Имеет Город начала и Город конца пути.
 *  Инициализация Маршрута может быть выполнена только если указана точка начала и
 * конца пути. Если указано nullзначение - то ошибка.
 *  Точку начала и конца можно изменить в любой момент времени, но они всегда должны
 * существовать.
 *  Может вернуть массив Городов, представляющий собой маршрут из начала в конец.
 * Массив содержит все Города (в порядке очереди) через которые надо пройти что бы
 * попасть из Города начала в Город конца, причем и начало и конец также содержатся в
 * этом массиве. Алгоритм формирования пути в данном случае не существенен, можно
 * выбрать вариант со случайным путем, путем проходящим через наименьшее число
 * городов, или самым дешевым путем. Если путь найти невозможно – возвращается пустой
 * массив.
 *  Маршрут может быть приведен к строке, которая будет возвращать название всех
 * городов маршрута в порядке очередности.
 *  Создание объекта и изменение точек начала и конца выполняется за константное время
 * O(1).
 * Воспользуйтесь картой городов из задачи 1.3.3 и выведите маршрут из Города F в Город D
 */
package ru.lazarev.city;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Представляет маршрут между двумя городами.
 * Позволяет найти путь и отобразить его в виде строки или массива городов.
 */
public class Route {
    private City startCity;
    private City endCity;

    /**
     * Создает объект маршрута с указанием начального и конечного городов.
     * Если какой-либо из городов {@code null}, выводится сообщение об ошибке,
     * и маршрут считается невалидным (поля остаются {@code null}).
     *
     * @param startCity Начальный город маршрута.
     * @param endCity   Конечный город маршрута.
     */
    public Route(City startCity, City endCity) {
        if (startCity == null) {
            System.out.println("Ошибка создания маршрута: Начальный город не должен быть null.");
        } else {
            this.startCity = startCity;
        }
        if (endCity == null) {
            System.out.println("Ошибка создания маршрута: Конечный город не должен быть null.");
        } else {
            this.endCity = endCity;
        }
        if (this.startCity == null || this.endCity == null) {
            this.startCity = null;
            this.endCity = null;
        }
    }

    /**
     * Возвращает начальный город маршрута.
     * @return Начальный город или {@code null}, если маршрут невалиден.
     */
    public City getStartCity() {
        return startCity;
    }

    /**
     * Возвращает конечный город маршрута.
     * @return Конечный город или {@code null}, если маршрут невалиден.
     */
    public City getEndCity() {
        return endCity;
    }

    /**
     * Устанавливает начальный город маршрута.
     * Если {@code startCity} равен {@code null}, выводится сообщение об ошибке,
     * и начальный город не изменяется (или маршрут становится невалидным, если endCity тоже null).
     * @param startCity Новый начальный город.
     */
    public void setStartCity(City startCity) {
        if (startCity == null) {
            System.out.println("Ошибка изменения начального города: Начальный город не должен быть null.");
            return;
        }
        this.startCity = startCity;
        if (this.endCity == null) {
            System.out.println("Предупреждение: Начальный город изменен, но конечный город все еще не задан. Маршрут невалиден.");
        }
    }

    /**
     * Устанавливает конечный город маршрута.
     * Если {@code endCity} равен {@code null}, выводится сообщение об ошибке,
     * и конечный город не изменяется.
     *
     * @param endCity Новый конечный город.
     */
    public void setEndCity(City endCity) {
        if (endCity == null) {
            System.out.println("Ошибка изменения конечного города: Конечный город не должен быть null.");
            return;
        }
        this.endCity = endCity;
        if (this.startCity == null) {
            System.out.println("Предупреждение: Конечный город изменен, но начальный город все еще не задан. Маршрут невалиден.");
        }
    }

    /**
     * Находит и возвращает путь между начальным и конечным городами.
     * Использует алгоритм поиска в глубину (DFS).
     * @return Массив городов, представляющий путь. Первый элемент - начальный город,
     *         последний - конечный. Если путь не найден или маршрут невалиден,
     *         возвращается пустой массив.
     */
    public City[] getRoute() {
        if (startCity == null || endCity == null) {
            System.out.println("Ошибка: Невозможно построить маршрут. Начальный или " +
                    "конечный город не инициализирован.");
            return new City[0];
        }
        List<City> routeList = new ArrayList<>();
        Set<City> visited = new HashSet<>();
        boolean pathFound = findRouteRecursive(startCity, endCity, routeList, visited);
        if (!pathFound) {
            return new City[0];
        }
        return routeList.toArray(new City[0]);
    }

    /**
     * Рекурсивный вспомогательный метод для поиска пути (DFS).
     * @param currentCity Текущий город в процессе поиска.
     * @param targetCity  Целевой город.
     * @param routeList   Список для сохранения найденного пути.
     * @param visited     Множество для отслеживания посещенных городов.
     * @return {@code true}, если путь найден, иначе {@code false}.
     */
    private boolean findRouteRecursive(City currentCity, City targetCity, List<City> routeList, Set<City> visited) {
        routeList.add(currentCity);
        visited.add(currentCity);

        if (currentCity.equals(targetCity)) {
            return true;
        }

        Map<City, Integer> pathsFromCurrent = currentCity.getPaths();
        if (pathsFromCurrent != null) {
            for (City nextCity : pathsFromCurrent.keySet()) {
                if (nextCity != null && !visited.contains(nextCity)) {
                    if (findRouteRecursive(nextCity, targetCity, routeList, visited)) {
                        return true;
                    }
                }
            }
        }

        routeList.remove(routeList.size() - 1);
        visited.remove(currentCity);
        return false;
    }

    /**
     * Возвращает строковое представление маршрута.
     * Формат: "Город1 -> Город2 -> ... -> ГородN".
     * Если маршрут не найден или невалиден, возвращается соответствующее сообщение.
     * @return Строковое представление маршрута.
     */
    @Override
    public String toString() {
        if (startCity == null || endCity == null) {
            return "Маршрут не может быть построен (города не инициализированы).";
        }
        City[] routeArray = getRoute();
        if (routeArray.length == 0) {
            return "Маршрут из " + startCity.getName() + " в " + endCity.getName() + " не найден.";
        }
        StringBuilder routeBuilder = new StringBuilder();
        for (int i = 0; i < routeArray.length; i++) {
            if (routeArray[i] != null) {
                routeBuilder.append(routeArray[i].getName());
                if (i < routeArray.length - 1) {
                    routeBuilder.append(" -> ");
                }
            } else {
                routeBuilder.append("[Ошибка: null город в маршруте]");
            }
        }
        return routeBuilder.toString();
    }
}