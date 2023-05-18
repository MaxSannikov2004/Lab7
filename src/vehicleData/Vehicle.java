package vehicleData;

//import database.DatabaseConnection;
//import database.UserAuthentication;
import database.Authentication;
import database.ConnectionToBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Vehicle implements Comparable<Vehicle> {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double enginePower; //Поле может быть null, Значение поля должно быть больше 0
    private long capacity; //Значение поля должно быть больше 0
    private long fuelConsumption; //Значение поля должно быть больше 0
    private VehicleType type; //Поле может быть null
    private final String creator;

    /**
     * Constructor for new object creation from console
     * @param name
     * @param coordinates
     * @param enginePower
     * @param capacity
     * @param fuelConsumption
     * @param type
     */
    public Vehicle (String name, Coordinates coordinates, Double enginePower, long capacity, long fuelConsumption, VehicleType type){
        setId();
        creationDate = new Date();
        this.name = name;
        this.coordinates = coordinates;
        this.enginePower = enginePower;
        this.capacity = capacity;
        this.fuelConsumption = fuelConsumption;
        this.type = type;
        creator = Authentication.getCurrentUser();
    }
    private long setId() {
        long newId = 0;
        ResultSet resultSet = ConnectionToBase.executePreparedStatement("SELECT nextval(?)", "id");
        try {
            resultSet.next();
            newId = resultSet.getLong(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return newId;
    }
    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }

    public Coordinates getCoordinates(){
        return coordinates;
    }
    public Double getEnginePower(){
        return enginePower;
    }
    public long getCapacity(){
        return capacity;
    }
    public long getFuelConsumption(){
        return fuelConsumption;
    }

    public VehicleType getType() {return type;}

    public void setName (String name){
        this.name = name;
    }

    public void setCapacity (long capacity){
        this.capacity = capacity;
    }

    public void setType (VehicleType type){
        this.type = type;
    }

    public void setEnginePower (Double enginePower){
        this.enginePower = enginePower;
    }

    public void setFuelConsumption (long fuelConsumption){
        this.fuelConsumption = fuelConsumption;
    }
    public String getCreator() {
        return creator;
    }
    @Override
    public String toString() {
        return "Транспортное средство " + id + "\n" +
                "Название: " + name + "\n" +
                "Координаты: (" + coordinates.getX() + "; " + coordinates.getY() + ")\n" +
                "Дата и время создания: " + creationDate + "\n" +
                "Мощность двигателя: " + enginePower + "\n" +
                "Вместимость: " + capacity + "\n" +
                "Расход топлива: " + fuelConsumption + "\n" +
                "Тип транспортного средства: " + type + "\n";

    }
    public int compareTo(Vehicle vehicle) {
        return (int)Math.signum(this.enginePower - vehicle.getEnginePower());
    }

}
