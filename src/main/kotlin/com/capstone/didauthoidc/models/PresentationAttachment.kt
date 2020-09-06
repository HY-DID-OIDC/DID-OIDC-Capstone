import java.util.*
import com.fasterxml.jackson.annotation.JsonProperty

data class PresentationAttachment(
    @JsonProperty("@id") var Id: String,

    @JsonProperty("mime-type") var MimeType: String,

    @JsonProperty("data") var Data: Dictionary<String, String>
)

