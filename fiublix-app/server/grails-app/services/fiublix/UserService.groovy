package fiublix

import grails.gorm.transactions.Transactional

@Transactional
class UserService {


      def addUser(User user) {
        user.save flush:true
    }

}
