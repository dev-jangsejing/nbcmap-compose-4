package com.jess.nbcamp.compose4.market.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.EmojiSupportMatch
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jess.nbcamp.compose4.R
import com.jess.nbcamp.compose4.market.model.MarketSaleItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketDetailScreen(
    viewModel: MarketDetailViewModel,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    uiState.item?.let { item ->
        Scaffold(
            modifier = modifier
                .fillMaxSize()
        ) { _ ->

            Box(modifier = Modifier.fillMaxSize()) {

                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState),
                ) {
                    Box(
                        modifier = Modifier
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f),
                            painter = painterResource(id = item.image),
                            contentDescription = item.itemTitle
                        )

                        IconButton(
                            onClick = onBackPressed,
                            modifier = Modifier
                                .statusBarsPadding()
                                .padding(start = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                tint = Color.White,
                                contentDescription = null,
                            )
                        }
                    }

                    Profile(item = item)

                    HorizontalDivider()

                    Detail(
                        item = item
                    )
                }

                Footer(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    item = item,
                    onClick = {

                    }
                )
            }
        }
    }
}

@Composable
private fun Profile(
    item: MarketSaleItem,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(12.dp)
    ) {
        Text(
            text = "😀",
            textAlign = TextAlign.Center,
            fontSize = 48.sp,
            style = TextStyle(
                platformStyle = PlatformTextStyle(
                    emojiSupportMatch = EmojiSupportMatch.None,
                ),
            ),
        )

        Spacer(modifier = Modifier.width(4.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = item.sellerName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = item.address,
                color = Color.DarkGray,
            )
        }
    }
}

@Composable
private fun Detail(
    item: MarketSaleItem,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(24.dp),
    ) {
        Text(
            text = item.itemTitle,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = item.itemDetail,
        )
    }
}

@Composable
private fun Footer(
    item: MarketSaleItem,
    onClick: (MarketSaleItem) -> Unit,
    modifier: Modifier = Modifier,
) {

    var favorite by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = {
                onClick(item)
                favorite = favorite.not()
            },
            modifier = Modifier
        ) {
            Icon(
                imageVector = if (favorite) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Filled.FavoriteBorder
                },
                tint = if (favorite) {
                    Color.Red
                } else {
                    Color.Black
                },
                contentDescription = null,
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        VerticalDivider(
            modifier = Modifier
                .height(32.dp)
                .background(
                    Color.DarkGray,
                )
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = stringResource(id = R.string.market_price_won).format(item.price),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}