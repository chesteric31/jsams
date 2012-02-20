package be.jsams.server.model.rss;

import java.util.ArrayList;
import java.util.List;

/**
 * RSS feed for auto-update module.
 *
 * @author ebinard
 * @version $Rev$ $Date::                  $ $Author$
 */
public class Feed {

    private String title;
    private String description;
    private String link;
    private String author;
    private String version;
    private String releaseDate;
    private List<FeedMessage> messages = new ArrayList<FeedMessage>();
    
    /**
     * Constructor.
     * 
     * @param title the title
     * @param description the description
     * @param link the link
     * @param author the author
     * @param version the version
     * @param releaseDate the release date
     */
    public Feed(String title, String description, String link, String author, String version,
            String releaseDate) {
        super();
        this.title = title;
        this.description = description;
        this.link = link;
        this.author = author;
        this.version = version;
        this.releaseDate = releaseDate;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate the releaseDate to set
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return the messages
     */
    public List<FeedMessage> getMessages() {
        return messages;
    }

    /**
     * @param messages the messages to set
     */
    public void setMessages(List<FeedMessage> messages) {
        this.messages = messages;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Feed [title=" + title + ", description=" + description + ", link=" + link + ", author=" + author
                + ", version=" + version + ", releaseDate=" + releaseDate + ", messages=" + messages + "]";
    }
    
}
