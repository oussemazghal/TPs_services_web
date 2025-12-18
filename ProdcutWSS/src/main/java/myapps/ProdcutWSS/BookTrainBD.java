package myapps.ProdcutWSS;


import java.util.ArrayList;
import java.util.List;


    public class BookTrainBD { 
        static List<Train> trains; 
      public BookTrainBD() {
	trains= new ArrayList<Train>(); 
       

            trains.add(new Train("TR123", "Sfax", "Tunis", 1250)); 

            trains.add(new Train("TR127", "Sfax", "Tunis", 1420)); 

            trains.add(new Train("TR129", "Sfax", "Sousse", 1710)); 

        } 
        public static List<Train> getTrains() { 
     
				return trains;
			
        } 
        
     

    } 
