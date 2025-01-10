package com.weather.forecast.widgets

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit

const val DEFAULT_MINIMUM_TEXT_LINE = 3

/**
 * An expandable text component that provides access to truncated text with a dynamic ... Show More/ Show Less button.
 *
 * @param modifier Modifier for the Box containing the text.
 * @param textModifier Modifier for the Text composable.
 * @param style The TextStyle to apply to the text.
 * @param fontStyle The FontStyle to apply to the text.
 * @param text The text to be displayed.
 * @param collapsedMaxLine The maximum number of lines to display when collapsed.
 * @param showMoreText The text to display for "... Show More" button.
 * @param showMoreStyle The SpanStyle for "... Show More" button.
 * @param showLessText The text to display for "Show Less" button.
 * @param showLessStyle The SpanStyle for "Show Less" button.
 * @param textAlign The alignment of the text.
 * @param fontSize The font size of the text.
 *
 * https://medium.com/@munbonecci/how-to-implement-expandable-text-in-jetpack-compose-ca9ba35b645c
 */
@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current, // 访问当前上下文中默认 TextStyle 的CompositionLocal
    fontStyle: FontStyle? = null,
    text: String,
    collapsedMaxLine: Int = DEFAULT_MINIMUM_TEXT_LINE,
    showMoreText: String = "... Show More",
    showMoreStyle: SpanStyle = SpanStyle(fontWeight = FontWeight.W500),
    showLessText: String = " Show Less",
    showLessStyle: SpanStyle = showMoreStyle,
    textAlign: TextAlign? = null,
    fontSize: TextUnit,
) {
    // State variables to track the expanded state, clickable state, and last character index.
    var isExpanded by remember { mutableStateOf(false) }
    var clickable by remember { mutableStateOf(false) }
    // 存储折叠状态下显示的最后一个字符的索引
    var lastCharIndex by remember { mutableIntStateOf(0) }

    // Box composable containing the Text composable.
    Box(modifier = Modifier
        .clickable(clickable) {
            isExpanded = !isExpanded
        }
        .then(modifier)
    ) {
        // Text composable with buildAnnotatedString to handle "Show More" and "Show Less" buttons.
        Text(
            modifier = textModifier
                .fillMaxWidth()
                .animateContentSize(), // 为 Text 的大小变化添加动画效果，使得展开和折叠的切换更加平滑
            text = buildAnnotatedString {
                if (clickable) {
                    if (isExpanded) {
                        // Display the full text and "Show Less" button when expanded.
                        append(text)
                        withStyle(style = showLessStyle) { append(showLessText) }
                    } else {
                        // Display truncated text and "Show More" button when collapsed.
                        val adjustText = text.substring(startIndex = 0, endIndex = lastCharIndex)
                            .dropLast(showMoreText.length)
                            .dropLastWhile { Character.isWhitespace(it) || it == '.' }
                        append(adjustText)
                        withStyle(style = showMoreStyle) { append(showMoreText) }
                    }
                } else {
                    // Display the full text when not clickable.
                    append(text)
                }
            },
            // Set max lines based on the expanded state.
            maxLines = if (isExpanded) Int.MAX_VALUE else collapsedMaxLine,
            fontStyle = fontStyle,
            // Callback to determine visual overflow and enable click ability.
            onTextLayout = { textLayoutResult ->
                if (!isExpanded && textLayoutResult.hasVisualOverflow) {
                    clickable = true
                    lastCharIndex = textLayoutResult.getLineEnd(collapsedMaxLine - 1)
                }
            },
            style = style,
            textAlign = textAlign,
            fontSize = fontSize
        )
    }
}