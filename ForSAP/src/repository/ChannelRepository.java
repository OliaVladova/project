package repository;

import channels.Channel;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChannelRepository {
    private Map<String, Channel> channels;

    public ChannelRepository() {
        this.channels = new LinkedHashMap<>();
    }

    public void add(Channel element) {
        this.channels.putIfAbsent(element.getName(), element);
    }

    public boolean remove(Channel element) {
        return this.channels.remove(element.getName())!=null;
    }

    public Channel getByName(String name) {
        return this.channels.get(name);
    }

    public Collection<Channel> getEntities() {
        return Collections.unmodifiableCollection(this.channels.values());
    }
}
