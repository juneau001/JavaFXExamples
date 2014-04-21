
import static groovyx.javafx.GroovyFX.start
import groovyx.javafx.beans.FXBindable
import java.util.ArrayList


/**
 *
 * @author Juneau
 */
@FXBindable
class Car{
    String make
    String model
    String year
    String description
}

@FXBindable
class CarHotel{
    
  List<Car> carList = new ArrayList()
  Car car = new Car()
  String carCount 
    
  def addCar(car){
      print "Adding car: ${car.make}"
      carList.add(car)
      carCount = "The number of cars is: ${carList.size()}"
      car = new Car() 
    }

}
start {

    def hotel = new CarHotel()
    stage(title: 'GroovyFX Form Demo', visible: true) {
        scene(fill: WHITE, width: 500, height: 600) {
                gridPane(hgap: 10, vgap: 10,
                         alignment: CENTER, padding: 20){
                    label("Make:", row:1, column:1)
                    textField(
                     text: bind(hotel.car.makeProperty()),
                     row:1, column:2)
                    
                    label("Model:", row:2, column:1)
                    textField(
                     text: bind(hotel.car.modelProperty()),
                     row:2, column:2)
                    
                    label("Year:", row:3, column:1)
                    textField(
                     text: bind(hotel.car.yearProperty()),
                     row:3, column:2)
                    
                    label("Description:", row:4, column:1)
                    textArea(
                     text: bind(hotel.car.descriptionProperty()),
                     row:5, columnSpan:3)
                    
                    button("Add Car", 
                        onAction: {
                            hotel.addCar(hotel.car)
                        },
                        row:6)
                    
                    label(
                        text: bind(hotel.carCountProperty()),
                        row:8, column:1)
                    
                }
                
            }
    }
}

