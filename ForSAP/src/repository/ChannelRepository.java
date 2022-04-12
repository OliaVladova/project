package repository;

import channels.TVChannel;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChannelRepository implements Repository<TVChannel> {
    private Map<String,TVChannel> channels;

    public ChannelRepository() {
        this.channels = new LinkedHashMap<>();
    }

    @Override
    public void add(TVChannel element) {
        this.channels.putIfAbsent(element.getName(), element);
    }

    @Override
    public boolean remove(TVChannel element) {
        return this.channels.remove(element.getName())!=null;
    }

    public TVChannel getByName(String name) {
        return this.channels.get(name);
    }

    @Override
    public Collection<TVChannel> getEntities() {
        return Collections.unmodifiableCollection(this.channels.values());
    }
}
