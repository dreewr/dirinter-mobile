package andre.dev.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberImagePainter
@Composable
fun NewsDetailsScreen(newsId: String,
                      viewModelProvider: ViewModelProvider.Factory) {


//    Column(modifier = Modifier.padding(16.dp)) {
//        Text(text = state.title, style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
//        state.imageUrl?.let {
//            Image(
//                painter = rememberImagePainter(it),
//                contentDescription = null,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(200.dp)
//                    .clip(RoundedCornerShape(8.dp)),
//                contentScale = ContentScale.Crop
//            )
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Text(text = state.body, style = MaterialTheme.typography.body1)
//        Spacer(modifier = Modifier.height(8.dp))
//        FlowRow(
//            mainAxisSpacing = 8.dp,
//            crossAxisSpacing = 8.dp
//        ) {
//            state.categories.forEach { category ->
//                Chip(label = category)
//            }
//        }
//    }
}
//
//@Composable
//fun Chip(label: String) {
//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = Modifier
//            .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(8.dp))
//            .padding(horizontal = 8.dp, vertical = 4.dp)
//    ) {
//        Text(text = label)
//    }
//}
