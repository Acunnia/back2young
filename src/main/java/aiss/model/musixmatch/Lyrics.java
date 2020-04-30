
package aiss.model.musixmatch;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private static final Logger log = Logger.getLogger(Lyrics.class.getName());
	private static final Pattern p = Pattern.compile("\"lyrics_body.+?Commercial use");
	private String lyrics;

	public Lyrics(String rawText) {

		Matcher m = p.matcher(rawText);
		String lyrics = "Lyrics aren't available for this track";

		if (m.find()) {

			lyrics = m.group();
			lyrics = lyrics.replace("\"lyrics_body\":\"", "");
			lyrics = lyrics.replace("\\n", "\n");
			lyrics = lyrics.replace("\\u00bf", "¿");
			lyrics = lyrics.replace("\\u00c0", "À");
			lyrics = lyrics.replace("\\u00c1", "Á");
			lyrics = lyrics.replace("\\u00c2", "Â");
			lyrics = lyrics.replace("\\u00c4", "Ä");
			lyrics = lyrics.replace("\\u00c7", "Ç");
			lyrics = lyrics.replace("\\u00c8", "È");
			lyrics = lyrics.replace("\\u00c9", "É");
			lyrics = lyrics.replace("\\u00ca", "Ê");
			lyrics = lyrics.replace("\\u00cb", "Ë");
			lyrics = lyrics.replace("\\u00cc", "Ì");
			lyrics = lyrics.replace("\\u00cd", "Í");
			lyrics = lyrics.replace("\\u00ce", "Ê");
			lyrics = lyrics.replace("\\u00cf", "Ï");
			lyrics = lyrics.replace("\\u00d1", "Ñ");
			lyrics = lyrics.replace("\\u00d2", "Ò");
			lyrics = lyrics.replace("\\u00d3", "Ó");
			lyrics = lyrics.replace("\\u00d4", "Ô");
			lyrics = lyrics.replace("\\u00d6", "Ö");
			lyrics = lyrics.replace("\\u00d9", "Ù");
			lyrics = lyrics.replace("\\u00da", "Ú");
			lyrics = lyrics.replace("\\u00db", "Û");
			lyrics = lyrics.replace("\\u00dc", "Ü");
			lyrics = lyrics.replace("\\u00df", "ß");
			lyrics = lyrics.replace("\\u00e0", "à");
			lyrics = lyrics.replace("\\u00e1", "á");
			lyrics = lyrics.replace("\\u00e2", "â");
			lyrics = lyrics.replace("\\u00e4", "ä");
			lyrics = lyrics.replace("\\u00e7", "ç");
			lyrics = lyrics.replace("\\u00e8", "è");
			lyrics = lyrics.replace("\\u00e9", "é");
			lyrics = lyrics.replace("\\u00ea", "ê");
			lyrics = lyrics.replace("\\u00eb", "ë");
			lyrics = lyrics.replace("\\u00ec", "ì");
			lyrics = lyrics.replace("\\u00ed", "í");
			lyrics = lyrics.replace("\\u00ee", "î");
			lyrics = lyrics.replace("\\u00ef", "ï");
			lyrics = lyrics.replace("\\u00f1", "ñ");
			lyrics = lyrics.replace("\\u00f2", "ò");
			lyrics = lyrics.replace("\\u00f3", "ó");
			lyrics = lyrics.replace("\\u00f4", "ô");
			lyrics = lyrics.replace("\\u00f6", "ö");
			lyrics = lyrics.replace("\\u00f9", "ù");
			lyrics = lyrics.replace("\\u00fa", "ú");
			lyrics = lyrics.replace("\\u00fb", "û");
			lyrics = lyrics.replace("\\u00fc", "ü");
			lyrics = lyrics.replace("\\u0152", "Œ");
			lyrics = lyrics.replace("\\u0153", "œ");
			lyrics = lyrics.concat(" *******");
		} else {
			log.log(Level.FINE, "There are no lyrics");
			log.log(Level.FINE, "Received raw text: " + rawText);
		}

		this.lyrics = lyrics;

	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}
    
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
