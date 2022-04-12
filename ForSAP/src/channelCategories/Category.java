package channelCategories;

import java.util.Collection;

public interface Category<T> {
    int getId();

    String getType();

    double getPrice();

    Collection<T> getChannels();
}
