package com.treelzebub.umap.api.discogs.model;

/**
 * Created by Tre Murillo on 3/29/15
 * <p/>
 * A pojo that represents basic information about an authenticated Discogs user
 */
public class Identity {
    private int id;
    private String username, resourceUrl, consumerName;

    public Identity(int id, String username, String resourceUrl, String consumerName) {
        this.id = id;
        this.username = username;
        this.resourceUrl = resourceUrl;
        this.consumerName = consumerName;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public String getConsumerName() {
        return consumerName;
    }
}
