package controller;

import channels.Channel;
import repository.ChannelRepository;

public class ChannelController {
    private ChannelRepository channelRepository;

    public ChannelController() {
        this.channelRepository = new ChannelRepository();
    }

    public void addChannel(Channel channel) {
        this.channelRepository.add(channel);
    }

    public void deleteChannel (String channelName){
        Channel channel = this.channelRepository.getByName(channelName);
        if (channel==null){
            throw new IllegalArgumentException("Invalid channel name!");
        }
        this.channelRepository.remove(channel);
    }
}
