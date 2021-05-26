package uz.jkamoliddinov0303.androiddaggerhilt.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.jkamoliddinov0303.androiddaggerhilt.db.UserDao
import uz.jkamoliddinov0303.androiddaggerhilt.db.UserDatabase
import uz.jkamoliddinov0303.androiddaggerhilt.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase =
        Room.databaseBuilder(context, UserDatabase::class.java, "user_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideUserDao(userDatabase: UserDatabase): UserDao = userDatabase.userDao()

    @Provides
    fun provideUserRepository(userDao: UserDao): UserRepository = UserRepository(userDao)

}