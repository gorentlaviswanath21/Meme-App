package com.example.PS_MemeApp.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.PS_MemeApp.model.Meme
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
class memeDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: MemeDatabase
    private lateinit var dao:Roomdao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MemeDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.memeDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertmemes() = runBlocking {
        val memeList = listOf<Meme>(
            Meme(1,1,"1","name","url",1),
            Meme(2,2,"2","name","url",2))
        dao.insertmemes(memeList)

        val allMemes = dao.getMemes()

        assertThat(allMemes).contains(Meme(2,2,"2","name","url",2))

    }

    @Test
    fun deletememes() = runBlocking {
        val memeList = listOf<Meme>(
            Meme(1,1,"1","name","url",1),
            Meme(2,2,"2","name","url",2))
        val meme = Meme(1,1,"1","name","url",1)
        dao.insertmemes(memeList)
        dao.delete(meme)

        val allMemes = dao.getMemes()

        assertThat(allMemes).doesNotContain(memeList)

    }
}