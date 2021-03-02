/*
 * Copyright 2021 The Android Open Source Project
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
package com.example.androiddevchallenge.ui.bunnies

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.androiddevchallenge.model.Bunny
import com.example.androiddevchallenge.model.getBunnies
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.utils.NetworkImage

private fun decoupledConstraints(expandedState: Boolean): ConstraintSet {
    return ConstraintSet {
        val imgRef = createRefFor("image")
        val columnRef = createRefFor("column")

        constrain(imgRef) {
            top.linkTo(parent.top)
            end.linkTo(parent.end)
            start.linkTo(parent.start)
        }
        constrain(columnRef) {
            if (expandedState) {
                top.linkTo(imgRef.bottom)
            } else {

                bottom.linkTo(parent.bottom)
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BunnyListItem(
    bunnyEnquiry: (bunny: Bunny) -> Unit,
    bunny: Bunny,
    expanded: Boolean = false
) {

    var expandedState by remember { mutableStateOf(expanded) }

    Card(
        modifier = Modifier
            .padding(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            )
            .wrapContentHeight(),
        elevation = 0.dp
    ) {

        ConstraintLayout(
            constraintSet = decoupledConstraints(expandedState),
            modifier = Modifier
                .clickable(onClick = { expandedState = !expandedState })
                .background(MaterialTheme.colors.background)
                .animateContentSize()
        ) {

            NetworkImage(
                url = bunny.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .layoutId("image")
                    .height(200.dp)
            )

            BunnyDetails(
                bunnyEnquiry = bunnyEnquiry,
                bunny,
                expandedState,
                modifier = Modifier
                    .layoutId("column")
                    .background(
                        MaterialTheme.colors.background
                    )
                    .padding(10.dp)
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BunnyDetails(
    bunnyEnquiry: (bunny: Bunny) -> Unit,
    bunny: Bunny,
    expanded: Boolean,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        val textItemModifier = Modifier.padding(bottom = 4.dp)
        Text(
            text = bunny.name,
            style = MaterialTheme.typography.subtitle1,
            overflow = TextOverflow.Ellipsis,
            modifier = textItemModifier
        )
        if (expanded) {
            Text(
                text = bunny.dob,
                style = MaterialTheme.typography.subtitle1,
                overflow = TextOverflow.Ellipsis,
                modifier = textItemModifier
            )
            Text(
                text = bunny.breed,
                style = MaterialTheme.typography.body1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = textItemModifier
            )
            Text(
                text = bunny.description,
                style = MaterialTheme.typography.body2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = textItemModifier
            )
            Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { bunnyEnquiry(bunny) },
                    modifier = modifier,
                    content = { Text(text = "Enquire") }
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview(name = "Bunnies list item")
@Composable
private fun CourseListItemPreviewLight() {
    CourseListItemPreview(false, false)
}

@ExperimentalAnimationApi
@Preview(name = "Bunnies list item expanded")
@Composable
private fun CourseListItemPreviewExpandedLight() {
    CourseListItemPreview(false, true)
}

@ExperimentalAnimationApi
@Composable
private fun CourseListItemPreview(darkTheme: Boolean, expanded: Boolean) {
    MyTheme(darkTheme) {
        BunnyListItem(
            {},
            bunny = getBunnies().first(), expanded
        )
    }
}
