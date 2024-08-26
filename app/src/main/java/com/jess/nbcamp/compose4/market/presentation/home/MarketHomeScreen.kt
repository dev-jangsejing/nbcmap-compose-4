package com.jess.nbcamp.compose4.market.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jess.nbcamp.compose4.R
import com.jess.nbcamp.compose4.market.model.MarketSaleItem

@Composable
fun MarketHomeScreen(
    viewModel: MarketHomeViewModel,
    onClick: (MarketSaleItem) -> Unit,
    modifier: Modifier = Modifier,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .height(56.dp),
            ) {
                Text(
                    modifier = Modifier
                        .height(IntrinsicSize.Max)
                        .padding(start = 16.dp)
                        .align(Alignment.CenterVertically),
                    text = stringResource(id = R.string.market_app_bar),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

        },
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) { innerPadding ->

        // 아이템
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            itemsIndexed(
                items = uiState.items,
                key = { _, item ->
                    item.hashCode()
                },
            ) { index, item ->
                SaleItem(
                    item = item,
                    onClick = onClick,
                )

                // 구분선
                if (index < uiState.items.lastIndex) {
                    Divider()
                }
            }
        }
    }
}

@Composable
private fun SaleItem(
    item: MarketSaleItem,
    onClick: (MarketSaleItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .clickable {
                onClick(item)
            }
            .padding(20.dp)
    ) {

        Image(
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(10.dp)),
            painter = painterResource(id = item.image),
            contentDescription = item.itemTitle
        )

        Spacer(
            modifier = Modifier.width(8.dp)
        )

        Box(
            modifier = Modifier.weight(1f)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = item.itemTitle,
                    fontSize = 16.sp
                )
                Text(
                    text = item.address,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
                Text(
                    text = stringResource(id = R.string.market_price_won).format(item.price),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            TailItem(
                item = item,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}

@Composable
private fun TailItem(
    item: MarketSaleItem,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(12.dp),
            painter = painterResource(id = R.drawable.img_chat),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = item.chatCnt.toString(),
            fontSize = 12.sp,
        )

        Spacer(modifier = Modifier.width(4.dp))

        Image(
            modifier = Modifier.size(12.dp),
            painter = painterResource(id = R.drawable.img_like),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = item.interestCnt.toString(),
            fontSize = 12.sp,
        )
    }
}

@Composable
private fun Divider() {
    HorizontalDivider(
        modifier = Modifier.fillMaxWidth()
    )
}