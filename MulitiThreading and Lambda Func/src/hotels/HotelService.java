package hotels;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class HotelService {
    List<Hotel> hotels = new ArrayList<>();
    public HotelService(){
        hotels.add(new Hotel(2000, 2, HotelType.THREE_STAR));
        hotels.add(new Hotel(1000, 1, HotelType.THREE_STAR));
        hotels.add(new Hotel(2000, 2, HotelType.THREE_STAR));
        hotels.add(new Hotel(10000, 4, HotelType.FOUR_STAR));
        hotels.add(new Hotel(20000, 5, HotelType.FIVE_STAR));
        hotels.add(new Hotel(5000, 3, HotelType.FIVE_STAR));
        hotels.add(new Hotel(2000, 2, HotelType.TWO_STAR));
        hotels.add(new Hotel(6000, 3, HotelType.FOUR_STAR));
    }

    // using explicitly defined functional interface for lambda function
    public List<Hotel> filterHotels(FilterCondition filterCondition){
        List<Hotel> filteredHotels = new ArrayList<>();
        for(Hotel hotel: hotels){
            if(filterCondition.test(hotel))
            filteredHotels.add(hotel);
        }
        return filteredHotels;
    }
    
    // using predicate inbuilt functional interface for lambda function
    public List<Hotel> filterHotelsWithPredicate(Predicate<Hotel> filterCondition){
        List<Hotel> filteredHotels = new ArrayList<>();
        for(Hotel hotel: hotels){
            if(filterCondition.test(hotel))
            filteredHotels.add(hotel);
        }
        return filteredHotels;
    }
}
