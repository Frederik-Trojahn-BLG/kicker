package de.almostintelligent.kicker.ember;

import com.google.common.base.CaseFormat;
import org.atteo.evo.inflector.English;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class EmberModel extends ConcurrentHashMap<String, Object> {

    private EmberModel() {
        super();
    }

    private static EmberModel create() {
        return new EmberModel();
    }

    public static class Builder {
        private ConcurrentHashMap<String, Object> items = new ConcurrentHashMap<>();

        public Builder(Class<?> clazz, Collection<?> entities) {
            sideLoad(clazz, entities);
        }

        public Builder(String name, Object entity) {
            addToMap(name, entity);
        }

        public Builder(Class<?> clazz, Object entity) {
            sideLoad(clazz, entity, false);
        }

        public Builder(Class<?> clazz, Object entity, boolean forcePlural) {
            sideLoad(clazz, entity, forcePlural);
        }

        public Builder sideLoad(Class<?> clazz, Collection<?> entities) {
            addToMap(getPluralName(clazz), entities);
            return this;
        }

        public Builder sideLoad(Class<?> clazz, Object entity) {
            sideLoad(clazz, entity, false);
            return this;
        }

        public Builder sideLoad(Class<?> clazz, Object entity, boolean forcePlural) {
            String name = getSingularName(clazz);
            if (forcePlural) {
                name = getPluralName(clazz);
                List<Object> entities = new ArrayList<>();
                entities.add(entity);
                addToMap(name, entities);
            } else {
                addToMap(name, entity);
            }

            return this;
        }

        private String getPluralName(Class<?> clazz) {
            return English.plural(getSingularName(clazz));
        }

        private String getSingularName(Class<?> clazz) {
            return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, clazz.getSimpleName());
        }

        private void addToMap(String name, Object data) {
            Assert.notNull(data);
            items.put(name, data);
        }

        public EmberModel build() {
            EmberModel model = EmberModel.create();
            model.putAll(items);
            return model;
        }
    }

}
