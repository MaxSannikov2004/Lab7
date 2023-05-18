package commands.AllCommands;

import vehicleData.*;
import commands.Command;
import commands.Invoker;
import exeptions.IllegalValueOfYException;
import exeptions.InvalidCommandException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Update implements Command {

    /**
     * Method, that update name
     * @param vehicle - element of collection, that name will be changed
     */

    private void updateName(Scanner scanner, Vehicle vehicle) {
        boolean i = true;
        while (i) {
            System.out.println("Введите название");
            String name = scanner.nextLine();
            if (!name.trim().isEmpty()) {
                vehicle.setName(name);
                i = false;
            } else {
                System.out.println("Неверный тип данных");
            }
        }
    }

    /**
     * Method, that update engine power
     * @param vehicle - element of collection, that value of engine power will be changed
     * */
    private void updateEnginePower(Scanner scanner, Vehicle vehicle) {
        boolean i = true;
        while (i) {
            System.out.println("Введите новую мощность двигателя");
            String string = scanner.nextLine();
            if (!string.trim().isEmpty()) {
                Double enginePower = Double.parseDouble(string);
                vehicle.setEnginePower(enginePower);
                i = false;
            } else {
                System.out.println("Неверный тип данных");
            }
        }
    }
    /**
     * Method, that update capacity
     * @param vehicle - element of collection, that value of capacity will be changed
     */
    private void updateCapacity(Scanner scanner, Vehicle vehicle) {
        boolean i = true;
        while (i) {
            System.out.println("Введите новую вместимость");
            String string = scanner.nextLine();
            if (!string.trim().isEmpty()) {
                long capacity = Long.parseLong(string);
                vehicle.setCapacity(capacity);
                i = false;
            } else {
                System.out.println("Неверный тип данных");
            }
        }
    }
    /**
     * Method, that update fuel consumption
     * @param vehicle - element of collection, that value of fuel consumption will be changed
     */
    private void updateFuelConsumption(Scanner scanner, Vehicle vehicle) {
        boolean i = true;
        while (i) {
            System.out.println("Введите новый расход топлива");
            String string = scanner.nextLine();
            if (!string.trim().isEmpty()){
                long fuelConsumption = Long.parseLong(string);
                vehicle.setFuelConsumption(fuelConsumption);
                i = false;
            } else {
                System.out.println("Неверный тип данных");
            }
        }
    }
    /**
     * Method, that update type of vehicle
     * @param vehicle - element of collection, that vehicle type will be changed
     */
    private void updateType(Scanner scanner, Vehicle vehicle) {
        boolean i = true;
        while (i) {
            System.out.println("Введите тип транспорта (Цифру или название) 1 - PLANE, 2 - HELICOPTER, 3 - SUBMARINE");
            String vehicleType = scanner.nextLine();
            if ((vehicleType.matches("[1-3]")||vehicleType.equals("PLANE")||vehicleType.equals("HELICOPTER")||vehicleType.equals("SUBMARINE"))) {
                switch (vehicleType) {
                    case "1", "PLANE" -> vehicle.setType(VehicleType.PLANE);
                    case "2", "HELICOPTER" -> vehicle.setType(VehicleType.HELICOPTER);
                    case "3", "SUBMARINE" -> vehicle.setType(VehicleType.SUBMARINE);
                }
                i = false;
            } else {
                System.out.println("Неверный тип данных");
            }
        }
    }

    /**
     * Method, that update vehicle coordinates
     * @param vehicle - element of collection, that vehicle type will be changed
     * @see Update#getNewXCoordinate(Scanner)
     * @see Update#getNewYCoordinate(Scanner)
     */
    private void updateCoordinates(Scanner scanner, Vehicle vehicle) {
        vehicle.getCoordinates().setX(getNewXCoordinate(scanner));
        vehicle.getCoordinates().setY(getNewYCoordinate(scanner));
    }
    /**
     * Method, that get new X coordinate
     * @return return new X coordinate
     */
    private double getNewXCoordinate(Scanner scanner) {
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
    /**
     * Method, that get new Y coordinate
     * @return return new Y coordinate
     */
    private long getNewYCoordinate(Scanner scanner) {
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
    /**
     * Method, that print variants of parameters that can be changed and return selected parameter
     * @return return number of changing parameter
     */
    private String requestInput(Scanner scanner) {
        boolean i = true;
        String s = "";
        while (i) {
            System.out.println("""
                                    Выберите характеристику, которую хотите изменить:
                                    Название - введите  1
                                    Мощность двигателя - введите 2
                                    Вместимость - введите 3
                                    Расход топлива - введите 4
                                    Вид - введите 5
                                    Координаты - введите 6""");
            s = scanner.nextLine();
            i = false;
        }
        return s;
    }
    /**
     * Method, that choose desired method for updating parameter
     * @param vehicle - element of collection, that will be changed
     * @param s Number of changed parameter
     * @see Update#updateName(Scanner, Vehicle)
     * @see Update#updateEnginePower(Scanner, Vehicle)
     * @see Update#updateCapacity(Scanner, Vehicle)
     * @see Update#updateFuelConsumption(Scanner, Vehicle)
     * @see Update#updateType(Scanner, Vehicle)
     * @see Update#updateCoordinates(Scanner, Vehicle)
     */
    private void fieldsUpdater(String s, Scanner scanner, Vehicle vehicle) {
        switch (s) {
            case "1" -> updateName(scanner, vehicle);
            case "2" -> updateEnginePower(scanner, vehicle);
            case "3" -> updateCapacity(scanner, vehicle);
            case "4" -> updateFuelConsumption(scanner, vehicle);
            case "5" -> updateType(scanner, vehicle);
            case "6" -> updateCoordinates(scanner, vehicle);
        }
        System.out.println("Характеристика успешно обновлена");
    }
    /**
     * Method, that update information about element of collection
     * @param id - id of element of collection, that will be changed
     * @see Update#requestInput(Scanner)
     * @see Update#fieldsUpdater(String, Scanner, Vehicle)
     */
    private void updateVehicle(long id) {
        boolean vehicleExists = false;
        for (Vehicle vehicle : VehiclesCollection.getVehicle()) {
            if (vehicle.getId() == id) {
                vehicleExists = true;
                Scanner scanner = new Scanner(System.in);
                String s = requestInput(scanner);
                if (!(s.matches("[1-7]"))) {
                    System.out.println("Неверный параметр");
                } else {
                    fieldsUpdater(s, scanner, vehicle);
                }
            }
        }
        if (!vehicleExists) System.out.println("Такого элемента не существует");
    }
    /**
     * Method, that executes command
     * @see Update#updateVehicle(long)
     */
    @Override
    public void execute() {
        try {
            if (Invoker.getSplit().length != 2) {
                throw new InvalidCommandException();
            }
            try {
                Long.parseLong(Invoker.getSplit()[1]);
            } catch (NumberFormatException ex) {
                throw new InvalidCommandException();
            }
            long id = Long.parseLong(Invoker.getSplit()[1]);
            if (!VehiclesCollection.getVehicle().isEmpty()) {
                updateVehicle(id);
            } else {
                System.out.println("Такого элемента не существует");
            }
        } catch (InvalidCommandException e) { System.out.println(e.getMessage()); }
    }
    /**
     * Method, that updates element of collection from file
     * @see Update#parametersReader(Scanner)
     * @see Update#fieldsUpdaterFromFile(String, String, Vehicle, Scanner)
     */
    protected static void updaterFromFile(Scanner scanner) {
        String[] parameters = parametersReader(scanner);
        try {
            if (Invoker.getSplit().length != 2) throw new InputMismatchException();
            try {
                Long.parseLong(Invoker.getSplit()[1]);
            } catch (NumberFormatException numberFormatException) {
                throw new InputMismatchException();
            }
            long id = Long.parseLong(Invoker.getSplit()[1]);
            boolean vehicleExists = false;
            Vehicle thisVehicle = new Vehicle("", new Coordinates(0, 0), Double.parseDouble("0"), Long.parseLong("0"), Long.parseLong("0"), null);
            for (Vehicle vehicle : VehiclesCollection.getVehicle()) {
                if (vehicle.getId() == id) {
                    vehicleExists = true;
                    thisVehicle = vehicle;
                }
            }
            if (!vehicleExists) throw new InputMismatchException();
            fieldsUpdaterFromFile(parameters[0], parameters[1], thisVehicle, scanner);
        } catch (InputMismatchException ignored) {}
    }
    /**
     * Method, that read updated field from file
     * @return return array, that contain number of updated parameter, and it's new value
     */
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
    /** Method, that updates selected field from file
     * @see Update#updateName(Scanner, Vehicle)
     * @see Update#updateEnginePower(Scanner, Vehicle)
     * @see Update#updateCapacity(Scanner, Vehicle)
     * @see Update#updateFuelConsumption(Scanner, Vehicle)
     * @see Update#updateType(Scanner, Vehicle)
     * @see Update#updateCoordinates(Scanner, Vehicle) */
    private static void fieldsUpdaterFromFile(String parameter, String newValue, Vehicle vehicle, Scanner scanner) {
        if (parameter.matches(("[1-7]"))) {
            switch (parameter) {
                case "1" -> updateNameFromFile(newValue, vehicle);
                case "2" -> updateEnginePowerFromFile(newValue, vehicle);
                case "3" -> updateCapacityFromFile(newValue, vehicle);
                case "4" -> updateFuelConsumptionFromFile(newValue, vehicle);
                case "5" -> updateTypeFromFile(newValue, vehicle);
                case "6" -> updateCoordinatesFromFile(newValue,scanner, vehicle);
            }
            System.out.println("Характеристика успешно обновлена");
        } else {
            throw new InputMismatchException();
        }
    }
    /**
     * Method, that update name from file
     */
    private static void updateNameFromFile(String name, Vehicle vehicle) {
        if (!name.trim().isEmpty()) {
            vehicle.setName(name);
        } else {
            throw new InputMismatchException();
        }
    }
    /**
     * Method, that update engine power from file
     */
    private static void updateEnginePowerFromFile(String enginePowerString, Vehicle vehicle) {
        try {
            Double enginePower = Double.parseDouble(enginePowerString);
            vehicle.setEnginePower(enginePower);
        } catch (NumberFormatException numberFormatException) {
            throw new InputMismatchException();
        }
    }
    /**
     * Method, that update capacity from file
     */
    private static void updateCapacityFromFile(String capacityString, Vehicle vehicle) {
        try {
            long capacity = Long.parseLong(capacityString);
            vehicle.setCapacity(capacity);
        } catch (NumberFormatException numberFormatException) {
            throw new InputMismatchException();
        }
    }
    /**
     * Method, that update fuel consumption from file
     */
    private static void updateFuelConsumptionFromFile(String fuelConsumptionString, Vehicle vehicle) {
        try {
            long fuelConsumption = Long.parseLong(fuelConsumptionString);
            vehicle.setFuelConsumption(fuelConsumption);
        } catch (NumberFormatException numberFormatException) {
            throw new InputMismatchException();
        }
    }
    /**
     * Method, that update type from file
     */
    private static void updateTypeFromFile(String vehicleType, Vehicle vehicle) {
        if (vehicleType.matches("[1-3]") || vehicleType.equals("PLANE") || vehicleType.equals("HELICOPTER") || vehicleType.equals("SUBMARINE")) {
            switch (vehicleType) {
                case "1", "PLANE" -> vehicle.setType(VehicleType.PLANE);
                case "2", "HELICOPTER" -> vehicle.setType(VehicleType.HELICOPTER);
                case "3", "SUBMARINE" -> vehicle.setType(VehicleType.SUBMARINE);
            }
        } else {
            throw new InputMismatchException();
        }
    }
    /**
     * Method, that update coordinates from file
     */
    private static void updateCoordinatesFromFile(String xString, Scanner scanner, Vehicle vehicle) {
        try {
            Double.parseDouble(xString);
        } catch (NumberFormatException numberFormatException) {
            throw new InputMismatchException();}
            double x = Double.parseDouble(xString);
            vehicle.getCoordinates().setX(x);

        try {
            String yString = scanner.nextLine();
            long y = Long.parseLong(yString);
            if (y > 746) {
                throw new InputMismatchException();
            }
            vehicle.getCoordinates().setY(y);


        } catch (NoSuchElementException noSuchElementException) {
            throw new InputMismatchException();
        }
    }
    @Override
    public String description() {
        return "update id : обновить значение элемента коллекции, id которого равен заданному";
    }
}

