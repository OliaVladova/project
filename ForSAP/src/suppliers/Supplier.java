package suppliers;

import channels.TVChannel;

import java.util.Collection;

public interface Supplier<T> {

    int getId();

    double getPrice();

    String getDeadline();

    Collection<TVChannel> getChannels();
    Collection<T> getContracts();
    String getName();


}
