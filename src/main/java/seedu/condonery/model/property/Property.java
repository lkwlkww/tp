package seedu.condonery.model.property;

import static seedu.condonery.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.condonery.model.client.Client;
import seedu.condonery.model.fields.Address;
import seedu.condonery.model.fields.Name;
import seedu.condonery.model.tag.Tag;

/**
 * Represents a Property in Condonery.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Property {

    // Identity fields
    private final Name name;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Client> interestedClients = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Property(Name name, Address address, Set<Tag> tags, Set<Client> interestedClients) {
        requireAllNonNull(name, address, tags);
        this.name = name;
        this.address = address;
        this.tags.addAll(tags);
        this.interestedClients.addAll(interestedClients);
    }

    public Name getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable interested client set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Client> getInterestedClients() {
        return Collections.unmodifiableSet(interestedClients);
    }

    /**
     * Returns an immutable set containing tag names, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<String> getTagNames() {
        HashSet<String> stringTags = new HashSet<>(tags.size());
        tags.forEach(tag -> stringTags.add(tag.tagName));
        return Collections.unmodifiableSet(stringTags);
    }

    /**
     * Returns an immutable set containing interested client names, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<String> getInterestedClientNames() {
        HashSet<String> stringInterestedClients = new HashSet<>(client.size());
        interestedClients.forEach(interestedClient -> stringInterestedClients.add(interestedClient.getName()));
        return Collections.unmodifiableSet(stringInterestedClients);
    }

    /**
     * Returns true if both properties have the same name.
     * This defines a weaker notion of equality between two properties.
     */
    public boolean isSameProperty(Property otherProperty) {
        if (otherProperty == this) {
            return true;
        }

        return otherProperty != null
                && otherProperty.getName().equals(getName());
    }

    /**
     * Returns true if both properties have the same identity and data fields.
     * This defines a stronger notion of equality between two properties.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Property)) {
            return false;
        }

        Property otherProperty = (Property) other;
        return otherProperty.getName().equals(getName())
            && otherProperty.getAddress().equals(getAddress())
            && otherProperty.getTags().equals(getTags())
            && otherProperty.getInterestedClients().equals(getInterestedClients());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, address, tags, interestedClients);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append("; Address: ")
            .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        Set<Client> interestedClients = getInterestedClients();
        if (!interestedClients.isEmpty()) {
            builder.append(": Interested clients: ");
            interestedClients.forEach(builder::append);
        }
        return builder.toString();
    }
}

