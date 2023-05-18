package vehicleData;

import commands.Invoker;
import database.ConnectionToBase;
import database.Authentication;
import exeptions.IllegalValueOfYException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Updater {
    /** Метод, обновляющий данные о транспорте
     * @param id id транспорта, параметр которого нужно изменить
     * @see Updater#requestInput(Scanner)
     * @see Updater#fieldsUpdater(String, Scanner, Vehicle) */
    public static void updateVehicle(long id) {
        List<Vehicle> matchedVehicle = VehiclesCollection.getVehicle().stream().filter(vehicle -> vehicle.getId() == id).toList();
        if (matchedVehicle.isEmpty()) {
            System.out.println("Такого объекта не существует");
        } else {
            try {
                ResultSet resultSet = ConnectionToBase.executePreparedStatement("select * from vehicle where id = " + matchedVehicle.get(0).getId() + " and creator = '" + Authentication.getCurrentUser() + "'");
                resultSet.next();
                resultSet.getLong(1);
                Scanner scanner = new Scanner(System.in);
                String s = requestInput(scanner);
                if (!(s.matches("[1-6]"))) {
                    System.out.println("Неверный параметр");
                } else {
                    fieldsUpdater(s, scanner, matchedVehicle.get(0));
                }
            } catch (SQLException sqlException) {
                System.out.println("Вы не можете изменить объект созданный другим пользователем");
            }
        }
    }
    /** Метод, выводящий варианты параметров для изменения и возвращающий один из них
     * @return возвращает цифру, обозначающую параметр для изменения
     * @param scanner сканер из консоли для получения параметра */
    private static String requestInput(Scanner scanner) {
        boolean i = true;
        String s = "";
        while (i) {
            System.out.println("""
                                    Выберите параметр транспорта, который хотите изменить:
                                    Название - введите  1
                                    Мощность двигателя - введите 2
                                    Вместимость - введите 3
                                    Расход топлива - введите 4
                                    Вид - введите 5
                                    Координаты - введите 6""");
            s = scanner.nextLine().trim();
            i = false;
        }
        return s;
    }
    /** Метод, вызывающий нужный метод для обновления определенного параметра
     * @param vehicle дракон, параметр которого нужно изменить
     * @param s число, обозначающее, какую характеристику транспорта надо изменить
     * @see Updater#updateName(Scanner, Vehicle)
     * @see Updater#updateEnginePower(Scanner, Vehicle)
     * @see Updater#updateCapacity(Scanner, Vehicle)
     * @see Updater#updateFuelConsumption(Scanner, Vehicle)
     * @see Updater#updateType(Scanner, Vehicle)
     * @see Updater#updateCoordinates(Scanner, Vehicle)*/
    private static void fieldsUpdater(String s, Scanner scanner, Vehicle vehicle) {
        switch (s) {
            case "1" -> updateName(scanner, vehicle);
            case "2" -> updateEnginePower(scanner, vehicle);
            case "3" -> updateCapacity(scanner, vehicle);
            case "4" -> updateFuelConsumption(scanner, vehicle);
            case "5" -> updateType(scanner, vehicle);
            case "6" -> updateCoordinates(scanner, vehicle);
        }
        VehiclesCollection.updateFromDB();
        System.out.println("Параметр транспорта успешно обновлён");
    }
    /**
     * Метод, обновляющий название
     * @param vehicle объект, у которого меняется имя
     * @param scanner сканер из консоли для получения нового имени
     */
    private static void updateName(Scanner scanner, Vehicle vehicle) {
        boolean i = true;
        while (i) {
            System.out.println("Введите название");
            String name = scanner.nextLine().trim();
            if (!(name.trim().isEmpty() | name.contains("'"))) {
                ConnectionToBase.executeStatement("update vehicle set name = '" + name + "' where id = " + vehicle.getId());
                i = false;
            } else {
                System.out.println("Неверный тип данных");
            }
        }
    }
    /** Метод, обновляющий мощность двигателя
     * @param vehicle объект, у которого мощность двигателя
     * @param scanner сканер из консоли для получения новой мощности двигателя */
    private static void updateEnginePower(Scanner scanner, Vehicle vehicle) {
        boolean i = true;
        while (i) {
            System.out.println("Введите новое значение мощности двигателя (должен быть больше 0)");
            try {
                Double enginePower = Double.parseDouble(scanner.nextLine().trim());
                if (enginePower <= 0) throw new NumberFormatException();
                ConnectionToBase.executeStatement("update vehicle set enginePower = '" + enginePower + "' where id = " + vehicle.getId());
                i = false;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Неверный тип данных");
            }
        }
    }
    /** Метод, обновляющий вид техники
     * @param vehicle объект, у которого меняется тип
     * @param scanner сканер из консоли для получения нового вида техники */
    private static void updateType(Scanner scanner, Vehicle vehicle) {
        boolean i = true;
        while (i) {
            System.out.println("Введите новый вид техники (Цифру или полное название) 1 - PLANE, 2 - HELICOPTER, 3 - SUBMARINE");
            String type = scanner.nextLine().trim();
            if (type.matches("[1-3]") || type.equals("PLANE") || type.equals("HELICOPTER") || type.equals("SUBMARINE")) {
                switch (type) {
                    case "1" -> type = "PLANE";
                    case "2" -> type = "HELICOPTER";
                    case "3" -> type = "SUBMARINE";
                }
                ConnectionToBase.executeStatement("update vehicle set type = '" + type + "' where id = " + vehicle.getId());
                i = false;
            } else {
                System.out.println("Неверный тип данных");
            }
        }
    }

    /** Метод, обновляющий показателя вместимости
     * @param vehicle объект, у которого есть показатель вместимости
     * @param scanner сканер из консоли для получения нового показателя вместимости */
    private static void updateCapacity(Scanner scanner, Vehicle vehicle) {
        boolean i = true;
        while (i) {
            System.out.println("Введите новое значение вместимости (должен быть больше 0)");
            try {
                long capacity = Long.parseLong(scanner.nextLine().trim());
                if (capacity <= 0) throw new NumberFormatException();
                ConnectionToBase.executeStatement("update vehicle set capacity = '" + capacity + "' where id = " + vehicle.getId());
                i = false;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Неверный тип данных");
            }
        }
    }
    /** Метод, обновляющий значение расхода топлива
     * @param vehicle объект, у которого меняется значение расхода топлива
     * @param scanner сканер из консоли для получения нового количества значения расхода топлива */
    private static void updateFuelConsumption(Scanner scanner, Vehicle vehicle) {
        boolean i = true;
        while (i) {
            System.out.println("ведите новое значение расхода топлива (должен быть больше 0)");
            try {
                long fuelConsumption = Long.parseLong(scanner.nextLine().trim());
                if (fuelConsumption <= 0) throw new NumberFormatException();
                ConnectionToBase.executeStatement("update vehicle set capacity = '" + fuelConsumption + "' where id = " + vehicle.getId());
                i = false;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Неверный тип данных");
            }
        }
    }
    /** Метод, обновляющий координаты
     * @param vehicle объект, у которого меняется координаты
     * @see Updater#getNewXCoordinate(Scanner)
     * @see Updater#getNewYCoordinate(Scanner) */
    private static void updateCoordinates(Scanner scanner, Vehicle vehicle) {
        ConnectionToBase.executeStatement("update vehicle set x = '" + getNewXCoordinate(scanner) + "' where id = " + vehicle.getId());
        ConnectionToBase.executeStatement("update vehicle set y = '" + getNewYCoordinate(scanner) + "' where id = " + vehicle.getId());
    }
    /** Метод, получающий новую координату х
     * @return возвращает координату х
     * @param scanner сканер из консоли для получения новой координаты х */
    private static double getNewXCoordinate(Scanner scanner) {
        double x = 0;
        boolean i = true;
        while (i) {
            System.out.println("Введите новую координату Х");
            String xString = scanner.nextLine();
            if (!xString.trim().isEmpty()) {
                x = Double.parseDouble(xString);
                i = false;
            } else {
                System.out.println("Неверный тип данных");
            }
        }
        return x;
    }
    /** Метод, получающий новую координату у
     * @return возвращает координату у
     * @param scanner сканер из консоли для получения новой координаты у */
    private static long getNewYCoordinate(Scanner scanner) {
        long y = 0;
        boolean i = true;
        while (i) {
            System.out.println("Введите новую координату Y");
            String yString = scanner.nextLine();
            try {
                if (!yString.trim().isEmpty()) {
                    y = Long.parseLong(yString);
                    if (y > 746) {
                        throw new IllegalValueOfYException();
                    } else {
                        i = false;
                    }
                } else {
                    System.out.println("Неверный тип данных");
                }
            } catch (IllegalValueOfYException illegalValueOfYException) {
                System.out.println(illegalValueOfYException.getMessage());
            }
        }
        return y;
    }
    /** Метод, обновляющий дракона параметрами из файла
     * @see Updater#parametersReader(Scanner)
     * @see Updater#fieldsUpdaterFromFile(String, String, Vehicle, Scanner)
     * @param scanner сканер из файла для получения новых параметров */
    public static void updaterFromFile(Scanner scanner) {
        String[] parameters = parametersReader(scanner);
        try {
            if (Invoker.getSplit().length != 2) throw new InputMismatchException();
            List<Vehicle> matchedVehicle = VehiclesCollection.getVehicle().stream().filter(vehicle -> vehicle.getId() == Long.parseLong(Invoker.getSplit()[1])).toList();
            if (matchedVehicle.isEmpty()) {
                throw new InputMismatchException();
            } else {
                try {
                    ResultSet resultSet = ConnectionToBase.executePreparedStatement("select * from vehicle where id = " + matchedVehicle.get(0).getId() + " and creator = '" + Authentication.getCurrentUser() + "'");
                    resultSet.next();
                    resultSet.getLong(1);
                    fieldsUpdaterFromFile(parameters[0], parameters[1], matchedVehicle.get(0), scanner);
                } catch (SQLException sqlException) {
                    throw new InputMismatchException();
                }
            }
        } catch (InputMismatchException | NumberFormatException ignored) {}
    }
    /** Метод, считывающий обновляемое поле из фала
     * @return возвращает массив, состоящий из номера обновляемого параметра и его нового значения
     * @param scanner сканер из файла для получения новых параметров */
    private static String[] parametersReader(Scanner scanner) {
        String[] parameters = new String[2];
        for (int i = 0; i < parameters.length; ++i) {
            try {
                parameters[i] = scanner.nextLine();
                if (parameters[i].trim().isEmpty()) parameters[i] = null;
            } catch (NoSuchElementException noSuchElementException) {
                parameters[i] = null;
            }
        }
        return parameters;
    }
    /** Метод, обновляющий выбранное поле из файла
     * @see Updater#updateNameFromFile(String, Vehicle)
     * @see Updater#updateEnginePowerFromFile(String, Vehicle)
     * @see Updater#updateTypeFromFile(String, Vehicle)
     * @see Updater#updateCapacityFromFile(String, Vehicle)
     * @see Updater#updateFuelConsumptionFromFile(String, Vehicle)
     * @see Updater#updateCoordinatesFromFile(String, Scanner, Vehicle)
     * @see Updater#updateFromDB()
     * @param parameter номер обновляемого параметра
     * @param newValue новое значение параметра
     * @param vehicle обновляемый объект
     * @param scanner сканер из файла в случае (нужен для добавления у координаты, так как она читается с новой строки) */
    private static void fieldsUpdaterFromFile(String parameter, String newValue, Vehicle vehicle, Scanner scanner) {
        if (parameter.matches(("[1-7]"))) {
            switch (parameter) {
                case "1" -> updateNameFromFile(newValue, vehicle);
                case "2" -> updateEnginePowerFromFile(newValue, vehicle);
                case "3" -> updateTypeFromFile(newValue, vehicle);
                case "4" -> updateCapacityFromFile(newValue, vehicle);
                case "5" -> updateFuelConsumptionFromFile(newValue, vehicle);
                case "6" -> updateCoordinatesFromFile(newValue, vehicle);
                case "7" -> updateCoordinatesFromFile(newValue, scanner, vehicle);
            }
            VehiclesCollection.updateFromDB();
            System.out.println("Параметр успешно обновлён");
        } else {
            throw new InputMismatchException();
        }
    }
    /**
     * Метод, обновляющий название объекта на новое из файла
     */
    private static void updateNameFromFile(String name, Vehicle vehicle) {
        if (!(name.trim().isEmpty() | name.contains("'"))) {
            ConnectionToBase.executeStatement("update vehicle set name = '" + name + "' where id = " + vehicle.getId());
        } else {
            throw new InputMismatchException();
        }
    }
    /**
     * Метод, обновляющий значение мощности двигателя на новое из файла
     */
    private static void updateEnginePowerFromFile(String ageString, Vehicle vehicle) {
        try {
            Double enginePower = Double.parseDouble(ageString);
            if (enginePower <= 0) throw new InputMismatchException();
            ConnectionToBase.executeStatement("update vehicle set age = '" + enginePower + "' where id = " + vehicle.getId());
        } catch (NumberFormatException numberFormatException) {
            throw new InputMismatchException();
        }
    }
    /**
     * Метод, обновляющий тип транспорта на новый из файла
     */
    private static void updateTypeFromFile(String type, Vehicle vehicle) {
        if (type.matches("[1-3]") || type.equals("PLANE") || type.equals("HELICOPTER") || type.equals("SUBMARINE")) {
            switch (type) {
                case "1" -> type = "PLANE";
                case "2" -> type = "HELICOPTER";
                case "3" -> type = "SUBMARINE";
            }
            ConnectionToBase.executeStatement("update vehicle set type = '" + type + "' where id = " + vehicle.getId());
        } else {
            throw new InputMismatchException();
        }
    }
