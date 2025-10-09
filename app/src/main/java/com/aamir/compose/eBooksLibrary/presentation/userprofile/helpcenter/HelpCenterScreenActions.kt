package com.aamir.compose.eBooksLibrary.presentation.userprofile.helpcenter

sealed interface HelpCenterScreenActions {
    data class OnEmailChange(val email: String) : HelpCenterScreenActions
    data class OnSubjectChange(val subject: String) : HelpCenterScreenActions
    data class OnDescriptionChange(val description: String) : HelpCenterScreenActions
    data object OnShareFeedback : HelpCenterScreenActions
    data object OnBackClick : HelpCenterScreenActions
}