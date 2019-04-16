package domain;

public class Reservation {

    private int movieId;
    private int scheduleNumber;
    private int numberOfPeople;

    public int price() {
        return MovieRepository.getMovieById(movieId).getPrice() * this.numberOfPeople;
    }
}
