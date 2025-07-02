
data class AddressModel(
    val title: String = "",
    val fullAddress: String= "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val tag: AddressTag = AddressTag.None
)

enum class AddressTag {
    Home, Office, None
}
