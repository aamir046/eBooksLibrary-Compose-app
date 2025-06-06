
data class AddressModel(
    val title: String,
    val fullAddress: String,
    val latitude: Double,
    val longitude: Double,
    val tag: AddressTag = AddressTag.None
)

enum class AddressTag {
    Home, Office, None
}
