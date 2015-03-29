package com.treelzebub.umap.api.discogs.model;

import com.treelzebub.umap.api.AuthenticatedSession;

import java.util.ArrayList;
import java.util.List;

import retrofit.client.Response;

/**
 * Created by Tre Murillo 1/27/2015
 *
 * A class representing an authenticated Discogs.com session
 */

public class Discogs extends AuthenticatedSession {
    private static Discogs instance;
    public static Discogs getInstance() {
        return instance == null ? instance = new Discogs() : instance;
    }

}