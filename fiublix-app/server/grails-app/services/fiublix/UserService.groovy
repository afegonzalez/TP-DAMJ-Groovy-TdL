package fiublix

import grails.gorm.transactions.Transactional

@Transactional
class UserService {
    MovieService movieService;

    def addUser(User user) {
        user.save flush:true
    }

    User getUser(Long userId){
        User user =  User.findById(userId);
        return user;
    }

    List<Movie> getCircleMovies(Long userId){
        User user =  User.findById(userId);
        return user.getFriendsMovies();
    }


    def addFriend(Long userId, Long friendId){
        User firstUser = this.getUser(userId);
        User secondUser = this.getUser(friendId);

        firstUser.addToFriends(secondUser);
        secondUser.addToFriends(firstUser);

        return secondUser
    }

    def addMovie(Long userId, Long movieId) {
        User user = this.getUser(userId);
        Movie movie = movieService.getMovie(movieId);

        user.addToFavoriteMovies(movie);
        
        return user
    }

    def addRecommendedMovie(Long userId, Long movieId) {
        User user = this.getUser(userId);
        Movie movie = movieService.getMovie(movieId);

        user.addToRecommendedMovies(movie);
        
        return user
    }

}
