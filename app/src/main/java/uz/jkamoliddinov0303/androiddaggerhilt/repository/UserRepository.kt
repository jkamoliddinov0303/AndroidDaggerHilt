package uz.jkamoliddinov0303.androiddaggerhilt.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.jkamoliddinov0303.androiddaggerhilt.db.User
import uz.jkamoliddinov0303.androiddaggerhilt.db.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {
    val getAllUserData = userDao.getAllUsers()

    suspend fun insertUser(user: User) = withContext(Dispatchers.IO) {
        userDao.insert(user)
    }

    suspend fun updateUser(user: User) = withContext(Dispatchers.IO) {
        userDao.update(user)
    }

    suspend fun deleteUSer(user: User) = withContext(Dispatchers.IO) {
        userDao.delete(user)
    }
}