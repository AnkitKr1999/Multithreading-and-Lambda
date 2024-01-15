package hotels;

public class Hotel {
    private int pricePerNight;
    private int rating;
    private HotelType hotelType;
    public Hotel(int pricePerNight,int rating,HotelType hotelType){
        this.hotelType=hotelType;
        this.pricePerNight=pricePerNight;
        this.rating=rating;
    }

    public int getPricePerNight(){
        return pricePerNight;
    }
    public void setPricePerNight(int pricePerNight){
        this.pricePerNight=pricePerNight;
    }

    public int getRating(){
        return rating;
    }
    public void setRating(int raiting){
        this.rating=raiting;
    }

    public HotelType getHotelType(){
        return hotelType;
    }
    public void setHotelType(HotelType hotelType){
        this.hotelType=hotelType;
    }
    
    @Override
    public String toString(){
        return  "Hotel [pricePerNight: " + pricePerNight + ", Rating: "+ rating + ", Type: "+ hotelType+ "]";
    }
}
