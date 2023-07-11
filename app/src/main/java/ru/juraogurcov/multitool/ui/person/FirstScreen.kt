package ru.juraogurcov.multitool.ui.person

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.juraogurcov.multitool.R
import ru.juraogurcov.multitool.data.UserInfoData

@Preview
@Composable
fun FirstScreen() {
    val personViewModel = PersonViewModel()
    var userInfoData = personViewModel.getUserInfo()
    val textStateFirstName = remember {
        mutableStateOf(userInfoData.firstNameUser)
    }
    val textStateSecondName = remember {
        mutableStateOf(userInfoData.secondNameUser)
    }
    val textStateThirdName = remember {
        mutableStateOf(userInfoData.thirdNameUser)
    }
    val textStateDate = remember {
        mutableStateOf(userInfoData.dayOfBirthUser)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
    ) {
        IconButton(
            onClick = {
                personViewModel.setUserInfo(
                    UserInfoData(
                        textStateFirstName.value,
                        textStateSecondName.value,
                        textStateThirdName.value,
                        textStateDate.value
                    )
                )
            }, modifier = Modifier
                .height(56.dp)
                .width(56.dp)
                .align(Alignment.End)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_rename),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

        IconButton(
            onClick = { },
            modifier = Modifier
                .padding(0.dp, 34.dp)
                .height(100.dp)
                .width(100.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_person_button),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
        Box(
            modifier = Modifier
                .padding(0.dp, 44.dp)
                .align(Alignment.CenterHorizontally)
                .border(
                    width = 0.dp, color = Color.Transparent, shape = RoundedCornerShape(
                        topStart = 15.dp, topEnd = 15.dp, bottomStart = 15.dp, bottomEnd = 15.dp
                    )
                )
                .background(
                    color = Color.Transparent, shape = RoundedCornerShape(
                        topStart = 15.dp, topEnd = 15.dp, bottomStart = 15.dp, bottomEnd = 15.dp
                    )
                )
        ) {
            Column() {
                TextField(
                    value = textStateFirstName.value,
                    onValueChange = {
                        textStateFirstName.value = it
                        personViewModel.setUserInfo(
                            UserInfoData(
                                textStateFirstName.value,
                                textStateSecondName.value,
                                textStateThirdName.value,
                                textStateDate.value
                            )
                        )
                    },
                    modifier = Modifier
                        .border(
                            width = 0.dp,
                            color = Color.Transparent,
                            shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                        )
                        .background(
                            colorResource(id = R.color.light_gray),
                            shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                        ),
                    singleLine = true

                )
                TextField(
                    value = textStateSecondName.value, onValueChange = {
                        textStateSecondName.value = it
                        personViewModel.setUserInfo(
                            UserInfoData(
                                textStateFirstName.value,
                                textStateSecondName.value,
                                textStateThirdName.value,
                                textStateDate.value
                            )
                        )
                    },
                    Modifier
                        .border(
                            width = 0.dp, color = Color.Transparent
                        )
                        .background(colorResource(id = R.color.light_gray)),
                    singleLine = true
                )
                TextField(
                    value = textStateThirdName.value, onValueChange = {
                        textStateThirdName.value = it
                        personViewModel.setUserInfo(
                            UserInfoData(
                                textStateFirstName.value,
                                textStateSecondName.value,
                                textStateThirdName.value,
                                textStateDate.value
                            )
                        )
                    },
                    Modifier
                        .border(
                            width = 0.dp, color = Color.Transparent
                        )
                        .background(colorResource(id = R.color.light_gray)),
                    singleLine = true
                )
                TextField(
                    value = textStateDate.value, onValueChange = {
                        textStateDate.value = it
                        personViewModel.setUserInfo(
                            UserInfoData(
                                textStateFirstName.value,
                                textStateSecondName.value,
                                textStateThirdName.value,
                                textStateDate.value
                            )
                        )
                    },
                    Modifier
                        .border(
                            width = 0.dp,
                            color = Color.Transparent,
                            shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
                        )
                        .background(
                            colorResource(id = R.color.light_gray),
                            shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
                        ),
                    singleLine = true
                )
            }
        }

    }
}

