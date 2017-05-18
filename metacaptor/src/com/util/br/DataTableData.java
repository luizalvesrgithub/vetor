package com.util.br;

import java.io.Serializable;
import java.util.List;
import com.util.br.VehicleGenerator;
import com.util.br.Car;
import com.util.br.SelectableCar;

public class DataTableData implements Serializable {
    
        public static final String BEAN_NAME = "tableData";

        public static final int DEFAULT_ROWS;
        public static final int DEFAULT_LIST_SIZE;
        public static final String[] CHASSIS_ALL;
        //static data initialization
        static {
            DEFAULT_ROWS = 8;
            DEFAULT_LIST_SIZE = 30;
            CHASSIS_ALL = new String[] {"Motorcycle", "Subcompact", "Mid-Size", "Luxury",
                                                        "Station Wagon", "Pickup", "Van", "Bus", "Semi-Truck"};
        }

        public DataTableData() {   }

        public static List<Car> getDefaultData() {
            VehicleGenerator generator = new VehicleGenerator();
            return generator.getRandomCars(DEFAULT_LIST_SIZE);
        }
        public static List<SelectableCar> getDefaultSelectableData() {
            VehicleGenerator generator = new VehicleGenerator();
            return generator.getRandomSelectableCars(DEFAULT_LIST_SIZE); 
        }
        public String[] getChassisAll() { return CHASSIS_ALL; }
}