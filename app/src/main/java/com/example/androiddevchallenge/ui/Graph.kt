/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.model.Bunny
import com.example.androiddevchallenge.model.getBunnies
import com.example.androiddevchallenge.ui.MainDestinations.BUNNY_ENQUIRY_ROUTE_KEY
import com.example.androiddevchallenge.ui.bunnies.Bunnies
import java.net.URLEncoder

/**
 * Destinations used in the ([OwlApp]).
 */
object MainDestinations {
    const val BUNNIES_ROUTE = "bunnies"
    const val BUNNY_ENQUIRY_ROUTE = "bunny_enquiry"

    const val BUNNY_ENQUIRY_ROUTE_KEY = "name"
}

@Composable
fun NavGraph(startDestination: String = MainDestinations.BUNNIES_ROUTE) {
    val navController = rememberNavController()

    val actions = remember(navController) { MainActions(navController) }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.BUNNIES_ROUTE) {
            Bunnies(actions.bunnyEnquiry, getBunnies())
        }

        composable(
            "${MainDestinations.BUNNY_ENQUIRY_ROUTE}/{$BUNNY_ENQUIRY_ROUTE_KEY}",
            arguments = listOf(
                navArgument(BUNNY_ENQUIRY_ROUTE_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:") // only email apps should handle this

            intent.putExtra(Intent.EXTRA_SUBJECT, it.arguments?.getString(BUNNY_ENQUIRY_ROUTE_KEY))

            navController.navigateUp()
            startActivity(LocalContext.current, intent, null)
        }
    }
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController) {
    val bunnyEnquiry: (bunny: Bunny) -> Unit = {

        navController.navigate(
            "${MainDestinations.BUNNY_ENQUIRY_ROUTE}/${
            URLEncoder.encode(
                it.name,
                "UTF-8"
            )
            }"
        )
    }
}
