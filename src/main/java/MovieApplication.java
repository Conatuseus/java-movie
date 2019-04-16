/*
 *  @(#)MovieApplication.java       1.00    2019/04/16
 *
 *  Copyright   (c) 2019 Myungki Sa.
 *  Computer Science Engineering, Java, Daejeon, Korea
 *  All rights reserved.
 *  conatuseus@gmail.com
 */

import domain.Movie;
import domain.MovieRepository;
import domain.Reservation;
import domain.User;
import view.InputView;
import view.OutputView;

import java.util.List;

public class MovieApplication {
    public static final int ZERO = 0;
    public static final int TRUE = 1;

    public static void main(String[] args) {
        List<Movie> movies = MovieRepository.getMovies();
        User user = new User();
        Reservation reservation;
        int isContinue;

        do {
            OutputView.printMovies(movies);
            int movieId = movieIdInit();
            OutputView.printMovieById(movieId);

            int scheduledNumber = scheduleNumberInit(movieId);
            int numberOfPeople = InputView.inputNumberOfPeople();

            reservation = new Reservation(movieId, scheduledNumber, numberOfPeople);
            user.addReservation(reservation);
            MovieRepository.getMovieById(movieId).getPlaySchedule(scheduledNumber).subCapacity(numberOfPeople);

            isContinue = InputView.inputContinue();
        }
        while (isContinue != TRUE);

        OutputView.printUserReservationList(user.getReservationList());
        int point = InputView.inputPoint();
        boolean isCard = InputView.inputCardOrNot();
        user.setPoint(point);
        OutputView.printPayment(user);
    }

    public static int movieIdInit() {
        int movieId;
        do {
            movieId = InputView.inputMovieId();
        }
        while (!MovieRepository.isContains(movieId));
        return movieId;
    }

    public static int scheduleNumberInit(int movieId) {
        int scheduledNumber;
        Movie movie = MovieRepository.getMovieById(movieId);
        do {
            scheduledNumber = InputView.inputMovieSchedule();
        }
        while (scheduledNumber > movie.getScheduleSize()
                || MovieRepository.getMovieById(movieId).getPlaySchedule(scheduledNumber).getCapacity() == ZERO);
        return scheduledNumber;
    }

}
