package fiublix

import grails.gorm.transactions.Transactional

@Transactional
class UserService {

    def addUser(User user) {
        user.save flush:true
    }

    User getUser(Long userId){
        User user =  User.findById(userId);
        return user;
    }

    def addFriend(Long userId, Long friendId){
        User firstUser = this.getUser(userId);
        User secondUser = this.getUser(friendId);

        firstUser.addToFriends(secondUser);
        secondUser.addToFriends(firstUser);

        return secondUser
    }

}
