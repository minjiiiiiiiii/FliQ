import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hongul.fliq.R
import com.hongul.fliq.model.card.Card
import com.hongul.fliq.ui.home.styles.CardInfoStyles
import com.hongul.fliq.ui.home.styles.CardInfoStyles.Modifiers.cardImage
import com.hongul.fliq.ui.home.styles.CardInfoStyles.Modifiers.contentColumn
import com.hongul.fliq.ui.home.styles.CardInfoStyles.Modifiers.infoCard
import com.hongul.fliq.ui.home.styles.CardInfoStyles.Modifiers.infoCardContent
import com.hongul.fliq.ui.home.styles.CardInfoStyles.Modifiers.infoColumn
import com.hongul.fliq.ui.home.styles.CardInfoStyles.Modifiers.infoIcon
import com.hongul.fliq.ui.home.styles.CardInfoStyles.Modifiers.spacerLarge
import com.hongul.fliq.ui.home.styles.CardInfoStyles.Modifiers.spacerMedium
import com.hongul.fliq.ui.home.styles.CardInfoStyles.Modifiers.spacerSmall

@Composable
fun CardInfoContent(card: Card) {
    Column(
        modifier = Modifier
            .contentColumn()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 카드 이미지 (가로9, 세로5 비율)
        Card(
            modifier = Modifier.cardImage(),
            elevation = CardDefaults.cardElevation(defaultElevation = CardInfoStyles.Colors.cardElevation),
        ) {
            // 내장 이미지 로더로 이미지 로드
            Image(
                painter = painterResource(id = R.drawable.img_card_example),
                contentDescription = "${card.name}의 명함",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.spacerLarge())

        // 이름
        Text(
            text = card.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.spacerSmall())

        // 직책 및 소속
        Text(
            text = "${card.position} | ${card.organization}",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.spacerLarge())

        // 연락처 정보
        ContactInfoItem(
            icon = Icons.Default.Email,
            label = "이메일",
            value = card.email
        )

        Spacer(modifier = Modifier.spacerMedium())

        ContactInfoItem(
            icon = Icons.Default.Phone,
            label = "연락처",
            value = card.contact
        )

        Spacer(modifier = Modifier.spacerMedium())

        ContactInfoItem(
            icon = Icons.Default.Person,  // Business 대신 Person 아이콘 사용
            label = "소속",
            value = card.organization
        )

        Spacer(modifier = Modifier.spacerMedium())

        ContactInfoItem(
            icon = Icons.Default.Info,  // Work 대신 Assignment 아이콘 사용
            label = "직책",
            value = card.position
        )
    }
}

@Composable
fun ContactInfoItem(
    icon: ImageVector,
    label: String,
    value: String
) {
    Card(
        modifier = Modifier.infoCard(),
        colors = CardDefaults.cardColors(
            containerColor = CardInfoStyles.Colors.infoCardBackground
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = CardInfoStyles.Colors.infoCardElevation)
    ) {
        Column(
            modifier = Modifier.infoCardContent()
        ) {
            androidx.compose.foundation.layout.Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.infoIcon(),
                    tint = CardInfoStyles.Colors.iconTint
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.infoColumn()
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelMedium,
                        color = CardInfoStyles.Colors.labelText
                    )
                    Text(
                        text = value,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}