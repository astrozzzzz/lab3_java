/** Задание №1.10
 * Дороги.
 * Измените сущности из задачи 1.3.3. Гарантируйте, что между двумя городами может быть только
 * одна прямая дорога (другой путь может быть проложен только транзитом через другие города).
 * Города можно создавать с указанием заранее заданных путей, в любой момент времени можно
 * добавить новую дорогу в любой город и удалить имеющуюся дорогу.
 * Задание 6.5
 * Сравнение городов.
 * Измените сущность Город, полученную в задаче 2.1.10. Переопределите метод сравнения
 * объектов по состоянию таким образом, чтобы два Города считались одинаковыми тогда, когда у
 * них одинаковый набор путей в другие города. Также, подвид Города из задачи 2.3.3 должен быть
 * сравним с городом из задачи 2.1.10.
 */
package ru.lazarev.city;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Представляет город с возможностью определения путей (дорог) в другие города.
 * Гарантируется, что между двумя городами может быть только одна прямая дорога.
 */
public class City {
    private String name;
    private Map<City, Integer> paths;

    /**
     * Создает новый город с указанным именем.
     * @param name Имя города. Если имя {@code null}, будет выведено сообщение об ошибке,
     *             и имя города будет установлено в "Неизвестный город".
     */
    public City(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Ошибка: имя города не может быть null или пустым. Установлено имя 'Неизвестный город'.");
            this.name = "Неизвестный город";
        } else {
            this.name = name;
        }
        this.paths = new HashMap<>();
    }

    /**
     * Создает новый город с указанным именем и начальным набором путей.
     * @param name Имя города. Если имя {@code null}, будет выведено сообщение об ошибке,
     *             и имя города будет установлено в "Неизвестный город".
     * @param p    Карта путей, где ключ - город назначения, а значение - стоимость пути.
     *             Пути с нулевой стоимостью или уже существующие пути (в этот город) не будут добавлены.
     *             Если {@code p} равно {@code null}, пути не будут добавлены.
     */
    public City(String name, Map<City, Integer> p) {
        this(name);
        if (p != null) {
            for (Map.Entry<City, Integer> entry : p.entrySet()) {
                addPath(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Возвращает имя города.
     * @return Имя города.
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает карту путей из этого города.
     * Ключ - город назначения, значение - стоимость пути.
     * @return Неизменяемая копия карты путей. Рекомендуется не модифицировать полученную карту напрямую.
     *         Для управления путями используйте методы {@link #addPath(City, int)} и {@link #removePath(City)}.
     */
    public Map<City, Integer> getPaths() {
        return new HashMap<>(this.paths);
    }

    /**
     * Устанавливает новое имя для города.
     * @param name Новое имя города. Если имя {@code null} или пустое, будет выведено сообщение об ошибке,
     *             и имя не будет изменено.
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Ошибка: новое имя города не может быть null или пустым. Имя не изменено.");
        } else {
            this.name = name;
        }
    }

    /**
     * Устанавливает новый набор путей для города, предварительно удаляя все существующие.
     * @param p Карта новых путей. Если {@code null}, все пути будут удалены.
     *          Пути с нулевой стоимостью или дублирующиеся пути (в этот же город назначения)
     *          не будут добавлены согласно логике {@link #addPath(City, int)}.
     */
    public void setPaths(Map<City, Integer> p) {
        this.paths.clear();
        if (p != null) {
            for (Map.Entry<City, Integer> entry : p.entrySet()) {
                addPath(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Добавляет путь из текущего города в указанный город с заданной стоимостью.
     * Путь не будет добавлен, если:
     * - город назначения {@code null}.
     * - путь в этот город уже существует.
     * - стоимость пути равна 0.
     * - город назначения совпадает с текущим городом (путь к самому себе).
     * @param city Город назначения.
     * @param cost Стоимость пути. Должна быть не равна 0. Отрицательные стоимости допустимы,
     *             если это предусмотрено логикой задачи.
     */
    public void addPath(City city, int cost) {
        if (city == null) {
            System.out.println("Ошибка: не удалось добавить путь. Город назначения не может быть null.");
            return;
        }
        if (city == this) {
            System.out.println("Предупреждение: попытка добавить путь из города '" + this.name + "' в самого себя. Путь не добавлен.");
            return;
        }
        if (cost == 0) {
            System.out.println("Предупреждение: стоимость пути не может быть 0. Путь в город '" + city.getName() + "' не добавлен.");
            return;
        }
        if (paths.containsKey(city)) {
            System.out.println("Предупреждение: путь из города '" + this.name + "' в город '" + city.getName() + "' уже существует. Новый путь не добавлен.");
            return;
        }
        paths.put(city, cost);
    }

    /**
     * Удаляет путь из текущего города в указанный город.
     * @param city Город, путь в который нужно удалить. Если {@code null} или путь отсутствует,
     *             никаких действий не производится.
     */
    public void removePath(City city) {
        if (city == null) {
            System.out.println("Информация: город для удаления пути не указан (null).");
            return;
        }
        if (!paths.containsKey(city)) {
            System.out.println("Информация: путь из города '" + this.name + "' в город '" + city.getName()
                    + "' не существует. Удаление не требуется.");
            return;
        }
        paths.remove(city);
    }

    /**
     * Возвращает строковое представление города, включая его имя и список путей.
     * @return Строковое представление города.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Город: ").append(this.name).append("\n");
        result.append("Пути: \n");
        if (paths.isEmpty()) {
            result.append("  (нет путей)\n");
        } else {
            for (Map.Entry<City, Integer> entry : paths.entrySet()) {
                result.append("  в город ").append(entry.getKey().getName()).append(": ")
                        .append(entry.getValue()).append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Сравнивает этот город с другим объектом.
     * Два города считаются равными, если у них одинаковые имена и одинаковый набор путей
     * (включая города назначения и стоимости).
     * @param obj Объект для сравнения.
     * @return {@code true}, если объекты равны, иначе {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof City)) {
            return false;
        }
        City otherCity = (City) obj;
        return Objects.equals(name, otherCity.name) &&
                Objects.equals(paths, otherCity.paths);
    }

    /**
     * Возвращает хэш-код для города.
     * Хэш-код базируется на имени города и его путях.
     * @return Хэш-код города.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, paths);
    }
}