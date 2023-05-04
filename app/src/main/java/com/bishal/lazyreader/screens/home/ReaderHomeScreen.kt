@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class
)

package com.bishal.lazyreader.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bishal.lazyreader.components.FABContent
import com.bishal.lazyreader.components.ListCard
import com.bishal.lazyreader.components.ReaderAppBar
import com.bishal.lazyreader.components.RoundedButton
import com.bishal.lazyreader.components.TitleSection
import com.bishal.lazyreader.model.MBook
import com.bishal.lazyreader.navigation.ReaderScreen
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ReaderHomeScreen(navController: NavController){
    

    Scaffold(topBar = {
                      ReaderAppBar(title = "Lazy Reader", navController = navController)
    },
        floatingActionButton = {
            FABContent{
                navController.navigate(ReaderScreen.SearchScreen.name)
            }
        }) {
        Surface(modifier = Modifier.fillMaxSize()) {
            //home content
            HomeContent(navController)

        }


    }





    
}

@Composable
fun HomeContent(navController: NavController) {



    val listOfBooks = listOf(
        MBook(id = "sdjhk", title = null, authors = "gakhskj", notes = null),
        MBook(id = "sdjhk", title = null, authors = "gakhskj", notes = null),
        MBook(id = "sdjhk", title = null, authors = "gakhskj", notes = null),
        MBook(id = "sdjhk", title = null, authors = "gakhskj", notes = null),
        MBook(id = "sdjhk", title = "oull", authors = "gakhskj", notes = null)
    )

    val email = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName = if (!email.isNullOrEmpty())
        email
            ?.split("@")?.get(0)
    else "NA"
    Column(
        Modifier.padding(top = 65.dp),
        verticalArrangement = Arrangement.Top

    ) {
        Row(modifier = Modifier.align(alignment = Alignment.Start)) {
            TitleSection(label = "Currently Reading")
            Spacer(modifier = Modifier.fillMaxWidth(0.7f))

            Column {
                Icon(imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .clickable {
                            navController.navigate(ReaderScreen.ReaderStatsScreen.name)
                        }
                        .size(45.dp),
                    tint = MaterialTheme.colorScheme.secondaryContainer)
                Text(
                    text = currentUserName!!,
                    modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Red,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip)
                Divider()


            }


        }
        ReadingRightNowArea(listOfBooks = listOf(),
            navController = navController)

        TitleSection(label = "Reading List")

        BookListArea(listOfBooks = listOfBooks, navController = navController)


       


    }

}

@Composable
fun BookListArea(listOfBooks: List<MBook>,
                 navController: NavController) {
    HorizontalScrollableComponent(listOfBooks){
        Log.d("TAG", "Booklistarea: $it")
        //TODO: on card clicked navigate to details screen
    }



}

@Composable
fun HorizontalScrollableComponent(listOfBooks: List<MBook>,
                                onCardPressed: (String) -> Unit) {
    val scrollState = rememberScrollState()
    
    Row(modifier = Modifier
        .fillMaxWidth()
        .heightIn(280.dp)
        .horizontalScroll(scrollState)) {

        for (book in listOfBooks) {
            ListCard(book = MBook()) {
             onCardPressed(it)

            }
        }

    }
}


@Composable
fun ReadingRightNowArea(listOfBooks: List<MBook>,
                        navController: NavController) {
    ListCard(book = MBook())

}