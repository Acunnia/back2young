
package aiss.model.musixmatch;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lyrics_id",
    "explicit",
    "lyrics_body",
    "script_tracking_url",
    "pixel_tracking_url",
    "lyrics_copyright",
    "updated_time"
})
public class Lyrics {

    @JsonProperty("lyrics_id")
    private Integer lyricsId;
    @JsonProperty("explicit")
    private Integer explicit;
    @JsonProperty("lyrics_body")
    private String lyricsBody;
    @JsonProperty("script_tracking_url")
    private String scriptTrackingUrl;
    @JsonProperty("pixel_tracking_url")
    private String pixelTrackingUrl;
    @JsonProperty("lyrics_copyright")
    private String lyricsCopyright;
    @JsonProperty("updated_time")
    private String updatedTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("lyrics_id")
    public Integer getLyricsId() {
        return lyricsId;
    }

    @JsonProperty("lyrics_id")
    public void setLyricsId(Integer lyricsId) {
        this.lyricsId = lyricsId;
    }

    @JsonProperty("explicit")
    public Integer getExplicit() {
        return explicit;
    }

    @JsonProperty("explicit")
    public void setExplicit(Integer explicit) {
        this.explicit = explicit;
    }

    @JsonProperty("lyrics_body")
    public String getLyricsBody() {
        return lyricsBody;
    }

    @JsonProperty("lyrics_body")
    public void setLyricsBody(String lyricsBody) {
        this.lyricsBody = lyricsBody;
    }

    @JsonProperty("script_tracking_url")
    public String getScriptTrackingUrl() {
        return scriptTrackingUrl;
    }

    @JsonProperty("script_tracking_url")
    public void setScriptTrackingUrl(String scriptTrackingUrl) {
        this.scriptTrackingUrl = scriptTrackingUrl;
    }

    @JsonProperty("pixel_tracking_url")
    public String getPixelTrackingUrl() {
        return pixelTrackingUrl;
    }

    @JsonProperty("pixel_tracking_url")
    public void setPixelTrackingUrl(String pixelTrackingUrl) {
        this.pixelTrackingUrl = pixelTrackingUrl;
    }

    @JsonProperty("lyrics_copyright")
    public String getLyricsCopyright() {
        return lyricsCopyright;
    }

    @JsonProperty("lyrics_copyright")
    public void setLyricsCopyright(String lyricsCopyright) {
        this.lyricsCopyright = lyricsCopyright;
    }

    @JsonProperty("updated_time")
    public String getUpdatedTime() {
        return updatedTime;
    }

    @JsonProperty("updated_time")
    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
