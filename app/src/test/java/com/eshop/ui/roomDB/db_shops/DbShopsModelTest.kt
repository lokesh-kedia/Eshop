package com.eshop.ui.roomDB.db_shops


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.eshop.getOrAwaitValue
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DbShopsModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Test
    fun addNewShop_addNewShopToDB() {
        val testViewModel = DbShopsModel(ApplicationProvider.getApplicationContext())


        testViewModel.insert(
            DbShops(
                "Hariom",
                "Lokesh",
                "74138",
                "D90",
                "Murlipura",
                "Jaipur",
                "Rajasthan",
                false,
                26.9720704,
                75.7629849
            )
        )

        val value = testViewModel.getLiveShops().getOrAwaitValue()

        assertThat(value, (not(nullValue())))


    }
}
