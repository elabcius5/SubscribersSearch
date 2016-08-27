package com.globant.tutoria.subscriberssearch.events;

import com.globant.tutoria.subscriberssearch.api.model.SubscribersModel;

import java.util.List;

/**
 * Created by juan.herrera@globant.com on 26/08/2016.
 */
public class SendSubscribersEvent {

    private List<SubscribersModel> subscribersModelList;

    public SendSubscribersEvent(List<SubscribersModel> subscribersModelList){
        this.subscribersModelList = subscribersModelList;
    }

    public List<SubscribersModel> getSubscribersModelList() {
        return subscribersModelList;
    }
}
