package be.jsams.server.model.rss;

import java.util.ArrayList;
import java.util.List;

/**
 * RSS feed for auto-update module.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class Feed {

    private String title;
    private String description;
    private String link;
    private String author;
    private List<FeedMessage> messages = new ArrayList<FeedMessage>();

    /**
     * Constructor.
     * 
     * @param title the title
     * @param description the description
     * @param link the link
     * @param author the author
     */
    public Feed(String title, String description, String link, String author) {
        super();
        this.title = title;
        this.description = description;
        this.link = link;
        this.author = author;
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
                + ", messages=" + messages + "]";
    }

}
