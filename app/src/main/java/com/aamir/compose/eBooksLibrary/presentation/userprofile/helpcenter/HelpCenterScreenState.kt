package com.aamir.compose.eBooksLibrary.presentation.userprofile.helpcenter

import com.aamir.compose.eBooksLibrary.domain.Feedback

data class HelpCenterScreenState(
    var userFeedback: Feedback= Feedback()
)