package com.testing.mobinttest.presentation.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.testing.mobinttest.R
import com.testing.mobinttest.data.remote.response.Company
import com.testing.mobinttest.presentation.theme.Black
import com.testing.mobinttest.presentation.theme.Blue
import com.testing.mobinttest.presentation.theme.DarkGrey
import com.testing.mobinttest.presentation.theme.LightGrey
import com.testing.mobinttest.presentation.theme.Red
import com.testing.mobinttest.presentation.theme.White


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsScreen(
    companies: LazyPagingItems<Company>
) {
    Scaffold(containerColor = LightGrey, modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.manage_cards),
                    fontFamily = FontFamily(Font(R.font.segoe)),
                    fontSize = dimensionResource(
                        id = R.dimen.text1
                    ).value.sp
                )
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = White, titleContentColor = Blue
            )
        )
    }) { paddingValues ->
        Log.d("data", companies.itemCount.toString())
        if (companies.loadState.refresh is LoadState.Loading) {
            ProgressWithText(paddingValues)
        }
        if (companies.loadState.refresh is LoadState.Error) {
            NoData(paddingValues)
        }
        if (companies.loadState.refresh is LoadState.NotLoading) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.margin1))
            ) {
                item {
                    Spacer(
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                items(companies.itemCount) { index ->
                    val company = companies[index]
                    if (company != null) {
                        CardItem(company = company)
                    }
                }
                item {
                    if (companies.loadState.append is LoadState.Loading) {
                        ProgressWithText()
                    } else {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CardItem(company: Company) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .padding(
                start = dimensionResource(id = R.dimen.margin1),
                end = dimensionResource(id = R.dimen.margin1)
            ), colors = CardDefaults.cardColors(containerColor = White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.margin1),
                    end = dimensionResource(id = R.dimen.margin1),
                    top = dimensionResource(id = R.dimen.margin1)
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = company.mobileAppDashboard.companyName,
                fontSize = dimensionResource(id = R.dimen.text1).value.sp,
                color = Black
            )

            AsyncImage(
                model = company.mobileAppDashboard.logo,
                contentDescription = company.mobileAppDashboard.companyName,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.logoSize))
                    .clip(CircleShape)
            )
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.margin1),
                    end = dimensionResource(id = R.dimen.margin1),
                    top = dimensionResource(id = R.dimen.margin2)
                )
        )

        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = dimensionResource(
                            id = R.dimen.text0
                        ).value.sp,
                        fontWeight = FontWeight.Bold,
                    )
                ) {
                    append(company.customerMarkParameters.mark.toString() + " ")
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = dimensionResource(
                            id = R.dimen.text2
                        ).value.sp, color = DarkGrey
                    )
                ) {
                    append(
                        pluralStringResource(
                            id = R.plurals.points,
                            count = company.customerMarkParameters.loyaltyLevel.markToCash
                        )
                    )
                }
            }, modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.margin1),
                end = dimensionResource(id = R.dimen.margin1),
                top = dimensionResource(id = R.dimen.margin1)
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.margin1)),
            horizontalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.margin3)
            )
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.cashback),
                    fontSize = dimensionResource(id = R.dimen.text3).value.sp,
                    color = DarkGrey
                )
                Text(
                    text = company.customerMarkParameters.loyaltyLevel.cashToMark.toString() + " %",
                    modifier = Modifier.padding(
                        top = dimensionResource(
                            id = R.dimen.margin2
                        )
                    ),
                    fontSize = dimensionResource(id = R.dimen.text2).value.sp
                )
            }
            Column {
                Text(
                    text = stringResource(id = R.string.level),
                    fontSize = dimensionResource(id = R.dimen.text3).value.sp,
                    color = DarkGrey
                )
                Text(
                    text = company.customerMarkParameters.loyaltyLevel.name.capitalize(Locale.current),
                    modifier = Modifier.padding(
                        top = dimensionResource(
                            id = R.dimen.margin2
                        )
                    ),
                    fontSize = dimensionResource(id = R.dimen.text2).value.sp
                )
            }
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.margin1),
                    end = dimensionResource(id = R.dimen.margin1),
                    top = dimensionResource(id = R.dimen.margin2)
                )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.margin1),
                    bottom = dimensionResource(id = R.dimen.margin1),
                    end = dimensionResource(id = R.dimen.margin1),
                    top = dimensionResource(id = R.dimen.margin2)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.margin3))
        ) {
            val localContext = LocalContext.current
            Image(
                painter = painterResource(id = R.drawable.ic_eye),
                contentDescription = "Eye button",
                modifier = Modifier
                    .size(
                        dimensionResource(id = R.dimen.iconSize)
                    )
                    .clickable {
                        Toast
                            .makeText(
                                localContext,
                                "Нажата кнопка eye, id: ${company.companyX.companyId}",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    },
                colorFilter = ColorFilter.tint(Blue)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_trash),
                contentDescription = "Trash button",
                modifier = Modifier
                    .size(
                        dimensionResource(id = R.dimen.iconSize)
                    )
                    .clickable {
                        Toast
                            .makeText(
                                localContext,
                                "Нажата кнопка trash, id: ${company.companyX.companyId}",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    },
                colorFilter = ColorFilter.tint(Red)
            )

            Button(
                onClick = {
                    Toast.makeText(
                        localContext,
                        "Нажата кнопка подробнее, id: ${company.companyX.companyId}",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = LightGrey)
            ) {
                Text(
                    text = stringResource(id = R.string.detail), color = Blue
                )
            }
        }
    }
}

@Composable
private fun NoData(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.no_companies),
            fontSize = dimensionResource(id = R.dimen.text2).value.sp,
            color = Black
        )
    }
}

@Composable
private fun ProgressWithText(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.refresh),
            fontSize = dimensionResource(id = R.dimen.text2).value.sp,
            color = Black
        )
        CircularProgressIndicator(color = Black)
    }
}

@Composable
private fun ProgressWithText() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.refresh),
            fontSize = dimensionResource(id = R.dimen.text2).value.sp,
            color = Black
        )
        CircularProgressIndicator(color = Black)
    }
}