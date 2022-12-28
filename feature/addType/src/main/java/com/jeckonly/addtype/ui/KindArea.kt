package com.jeckonly.addtype.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeckonly.addtype.ui.state.AddTypeKind
import com.jeckonly.designsystem.Mdf
import com.jeckonly.designsystem.R

const val GRID_COUNT = 5

@Composable
fun KindArea(
    nowChooseId: Int,
    onClickIcon: (Int) -> Unit,
    kinds: List<AddTypeKind>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(columns = GridCells.Fixed(GRID_COUNT), modifier = modifier, content = {
        kinds.forEach {
            item(span = {
                GridItemSpan(GRID_COUNT)
            }) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = it.kindName,
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Mdf.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
            }
            items(items = it.idList) { id ->
                Box(contentAlignment = Alignment.Center, modifier = Mdf.padding(10.dp)) {
                    IconType(nowChooseId = nowChooseId, id = id, onClick = onClickIcon)
                }
            }
        }
        item(span = {
            GridItemSpan(GRID_COUNT)
        }) {
            Box(modifier = Mdf.fillMaxWidth().height(150.dp))
        }
    })
}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewKindArea() {
    KindArea(
        R.drawable.cc_entertainmente_archery_stroke,
        {
        
        },
        listOf(
            AddTypeKind(
                kindName = "111",
                idList = listOf(
                    R.drawable.cc_entertainmente_archery_stroke ,
                    R.drawable.cc_entertainmente_badminton_stroke ,
                    R.drawable.cc_entertainmente_baseball_stroke ,
                    R.drawable.cc_entertainmente_basketball_stroke ,
                    R.drawable.cc_entertainmente_bowling_stroke ,
                    R.drawable.cc_entertainmente_chess_stroke ,
                    R.drawable.cc_entertainmente_climbing_stroke ,
                    R.drawable.cc_entertainmente_gambling_stroke ,
                    R.drawable.cc_entertainmente_game_stroke ,
                    R.drawable.cc_entertainmente_movies_stroke ,
                    R.drawable.cc_entertainmente_ping_pong_stroke ,
                    R.drawable.cc_entertainmente_poker_stroke ,
                    R.drawable.cc_entertainmente_racing_stroke ,
                    R.drawable.cc_entertainmente_roller_skating_stroke ,
                    R.drawable.cc_entertainmente_sailing_stroke ,
                    R.drawable.cc_entertainmente_skiing_stroke ,
                    R.drawable.cc_entertainmente_swimming_stroke ,
                    R.drawable.cc_entertainmente_whirligig_stroke ,
                )
            ),
            AddTypeKind(
                kindName = "222",
                idList = listOf(
                    R.drawable.cc_catering_apple_stroke ,
                    R.drawable.cc_catering_banana_stroke ,
                    R.drawable.cc_catering_beer_stroke ,
                    R.drawable.cc_catering_birthday_cake_stroke ,
                    R.drawable.cc_catering_bottle_stroke ,
                    R.drawable.cc_catering_birthday_cake_stroke ,
                    R.drawable.cc_catering_chicken_stroke ,
                    R.drawable.cc_catering_coffee_stroke ,
                    R.drawable.cc_catering_drumstick_stroke ,
                    R.drawable.cc_catering_hamburg_stroke ,
                    R.drawable.cc_catering_hot_pot_stroke ,
                    R.drawable.cc_catering_ice_cream_stroke ,
                    R.drawable.cc_catering_ice_lolly_stroke ,
                    R.drawable.cc_catering_noodle_stroke ,
                    R.drawable.cc_catering_red_wine_stroke ,
                    R.drawable.cc_catering_rice_stroke ,
                    R.drawable.cc_catering_seafood_stroke ,
                    R.drawable.cc_catering_skewer_stroke ,
                    R.drawable.cc_catering_sushi_stroke ,
                    R.drawable.cc_catering_tea_stroke ,
                )
            )
        )
    )
}