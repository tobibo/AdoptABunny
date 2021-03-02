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
package com.example.androiddevchallenge.model

import java.io.Serializable

data class Bunny(
    val name: String,
    val dob: String,
    val breed: String,
    val sex: String,
    val description: String,
    val imageUrl: String
) : Serializable

fun getBunnies() = listOf<Bunny>(
    Bunny(
        "Gigi's Babies",
        "2/12/2020",
        "giants",
        "females",
        "These girls are very sweet and friendly and love pats.",
        "https://static.wixstatic.com/media/df8370_38925f62a6704474b57a6fd9d1721080~mv2.jpg"
    ),
    Bunny(
        "Gigi",
        "1/12/2020",
        "giants",
        "male",
        "Gigi is a giant bun and she has 2 babies waiting for homes.",
        "https://static.wixstatic.com/media/df8370_38925f62a6704474b57a6fd9d1721080~mv2.jpg"
    ),
    Bunny(
        "Ginger & Wilder (vib)",
        "Ginger 1/7/2020 Wilder 1/8/2020",
        "giants",
        "male/female",
        "Ginger is very friendly once he gets to know you.  Wilder is a native bush bunny.  And yes... you can keep a bush bunny in Victoria.  See link below for more information.",
        "https://static.wixstatic.com/media/df8370_29ab6117f9ec40419a9c4004e9afb22e~mv2.jpg"
    ),
    Bunny(
        "Dapple",
        "unknown",
        "Dwarf x",
        "female",
        "Ginger is very friendly once he gets to know you.  Wilder is a native bush bunny.  And yes... you can keep a bush bunny in Victoria.  See link below for more information.",
        "https://static.wixstatic.com/media/df8370_4e24de34c57243b5afb70d7b7c6acbba~mv2.jpg"
    ),
    Bunny(
        "Elisa (vib)",
        "18/10/2019",
        "Dwarf x",
        "female",
        "Elsa is an active and inquisitive bun.  Not suitable for families with children as small humans make her nervous.",
        "https://static.wixstatic.com/media/df8370_451e688f4c80445b9ce07c223535c5dd~mv2.jpg"
    ),
    Bunny(
        "Kisses",
        "27/5/2017",
        "Standard sating",
        "female",
        "Kisses was left with a friend after her owners returned to NZ, and they were not to return.  Kisses is a little shy to start but is litter trained and has been a free range house bun.\n" +
            "\n" +
            "Kisses is recovering from trauma from a bite",
        "https://static.wixstatic.com/media/df8370_aaf082dbef1049f5bbca4db8c7adf3eb~mv2.jpg"
    ),
    Bunny(
        "Flur",
        "23/10/2020",
        "Dwarf",
        "female",
        "nethie dwarf/dutch colours",
        "https://static.wixstatic.com/media/df8370_9a95addaf7b2473da5c660072960249d~mv2.jpg"
    ),
    Bunny(
        "Carrot",
        "27/11/2018",
        "Ginger lop",
        "male",
        "",
        "https://static.wixstatic.com/media/df8370_0a1a5d1da09645ca86f57810bbcbf2f4~mv2.jpg"
    ),
    Bunny(
        "Mercury",
        "06/08/2020",
        "lop",
        "male",
        "",
        "https://static.wixstatic.com/media/df8370_e33bab2f10b94e8196faaaacc09844b8~mv2.jpg"
    )
)
