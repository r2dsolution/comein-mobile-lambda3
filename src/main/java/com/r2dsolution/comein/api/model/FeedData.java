package com.r2dsolution.comein.api.model;

import java.util.ArrayList;
import java.util.List;

public class FeedData {
    public int count;
    public String nextPageToken;
    public FeedFilter filter;
    public ArrayList<FeedMessage> messages = new ArrayList<FeedMessage>();
    public FeedOption option;
}
