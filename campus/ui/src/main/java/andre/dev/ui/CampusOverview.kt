package andre.dev.ui

import andre.dev.presentation.model.BranchUIModel
import andre.dev.presentation.model.AboutUIModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CampusOverview(overview: AboutUIModel) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Text(text = overview.name, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = overview.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(overview.branches) { branch ->
            BranchCard(branch)
        }
    }
}

@Composable
fun BranchCard(branch: BranchUIModel) {
    //Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
    Column(/*modifier = Modifier.padding(16.dp)*/) {
        Text(text = branch.name, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        RemoteImage(imageUrl = branch.imageUrl)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = branch.address, style = MaterialTheme.typography.bodyMedium)
        //Text("Cursos ofertados:", style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(8.dp))

//        FlowRow(
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            branch.courses.forEach { course ->
//                Text(text = course, style = MaterialTheme.typography.bodyMedium)
//            }
//        }
    }
    // }
}
