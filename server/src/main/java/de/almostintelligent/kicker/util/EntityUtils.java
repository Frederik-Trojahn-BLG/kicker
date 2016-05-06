package de.almostintelligent.kicker.util;

import de.almostintelligent.kicker.model.BaseEntity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class EntityUtils {

    public static Collection<String> toIds(Collection<? extends BaseEntity> entities) {
        if (entities != null) {
            Set<String> result = new HashSet<>();
            for (BaseEntity e : entities) {
                result.add(e.getId());
            }

            return result;
        }
        return null;
    }

}
