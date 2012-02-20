package be.jsams.server.model.rss;

/**
 * RSS message for auto-update module.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class FeedMessage {

    private String title;
    private String description;
    private String link;
    private String author;
    private String guid;

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
     * @return the guid
     */
    public String getGuid() {
        return guid;
    }
    /**
     * @param guid the guid to set
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "FeedMessage [title=" + title + ", description=" + description + ", link=" + link + ", author=" + author
                + ", guid=" + guid + "]";
    }

}
