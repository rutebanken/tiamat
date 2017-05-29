package org.rutebanken.tiamat.diff;

import javassist.util.proxy.MethodHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class GenericObjectDiffer {

    private static final Logger logger = LoggerFactory.getLogger(GenericObjectDiffer.class);

    public List<Difference> compareObjects(Object oldObject, Object newObject, String identifierPropertyName, Set<String> ingoreFields) throws IllegalAccessException {
        return compareObjects(null, oldObject, newObject, identifierPropertyName, ingoreFields);
    }


    public List<Difference> compareObjects(String property, Object oldObject, Object newObject, String identifierPropertyName, Set<String> ignoreFields) throws IllegalAccessException {
        List<Difference> differences = new ArrayList<>();

        Class clazz = oldObject.getClass();

        Field[] fields = getAllFields(clazz, ignoreFields);
        Field identifierField = identifierField(identifierPropertyName, fields);

        if (property == null) {
            property = oldObject.getClass().getSimpleName();
        }

        if (identifierField != null) {
            identifierField.setAccessible(true);
        }

        for (Field field : fields) {

            try {

                if (field.getType().isAssignableFrom(MethodHandler.class)) {
                    logger.info("Ignoring field {}", field);
                    continue;
                }

                field.setAccessible(true);

                Object oldValue = field.get(oldObject);
                Object newValue = field.get(newObject);

                if (oldValue == null && newValue == null) {
                    continue;
                }

                if (oldValue == null && newValue != null || oldValue != null && newValue == null) {
                    differences.add(new Difference(property + '.' + field.getName(), oldValue, newValue));
                    continue;
                }

                if (Collection.class.isAssignableFrom(field.getType())) {

                    Collection oldCollection = (Collection) oldValue;
                    Collection newCollection = (Collection) newValue;


                    compareCollection(property + '.' + field.getName(), oldCollection, newCollection, differences, identifierPropertyName, fields, ignoreFields);
                    continue;

                } else if (Map.class.isAssignableFrom(field.getType())) {

                    Map<?, ?> oldMap = (Map) oldValue;
                    Map<?, ?> newMap = (Map) newValue;

                    String mapPropertyName = property + "." + field.getName();

                    compareMap(oldMap, newMap, differences, false, mapPropertyName, ignoreFields);

                    continue;

                }

                if (oldValue == newValue) {
                    continue;
                }

                if (oldValue.equals(newValue)) {
                    continue;
                }

                String propertyName = property + '.' + field.getName();

                if (isPrimitive(oldValue)) {
                    differences.add(new Difference(propertyName, oldValue, newValue));
                } else {
                    differences.addAll(compareObjects(property + '.' + field.getName(), oldValue, newValue, identifierPropertyName, ignoreFields));
                }
            } catch (IllegalAccessException | IllegalArgumentException e) {
                throw new RuntimeException("Could not compare field '" + field + "'. old object " + oldObject + " new object " + newObject, e);
            }
        }

        return differences;
    }

    public boolean isPrimitive(Object value) {
        return value instanceof Number || value instanceof String || value instanceof Boolean;
    }

    public void compareMap(Map<?, ?> map1, Map<?, ?> map2, List<Difference> differences, boolean reverse, String mapPropertyName, Set<String> ignoreFields) throws IllegalAccessException {

        Map<?, ?> leftMap;
        Map<?, ?> rightMap;

        if (reverse) {
            leftMap = map2;
            rightMap = map1;
        } else {
            leftMap = map1;
            rightMap = map2;
        }

        for (Object leftMapKey : leftMap.keySet()) {

            Object leftMapValue = leftMap.get(leftMapKey);

            if (!rightMap.containsKey(leftMapKey)) {
                logger.debug("right map does not contain key {}", leftMapKey);

                differences.add(new Difference(mapPropertyName + "{" + leftMapKey + "}", leftMapValue, null));

            } else if (rightMap.containsKey(leftMapKey)) {

                logger.debug("right map contain key {}", leftMapKey);

                String childProperty = mapPropertyName + "{" + leftMapKey + "}";
                differences.addAll(compareObjects(childProperty, leftMapValue, rightMap.get(leftMapKey), null, ignoreFields));
            }
        }
    }

    private Field identifierField(String identifierPropertyName, Field[] fields) {
        return Stream.of(fields).filter(field -> field.getName().equals(identifierPropertyName)).findFirst().orElse(null);
    }

    public void compareCollection(final String propertyName, Collection oldCollection, Collection newCollection, List<Difference> differences, String identifierPropertyName, Field[] fields, Set<String> ignoreFields) throws IllegalAccessException {

        if (oldCollection == null && newCollection == null) {
            return;
        }

        if (oldCollection == null && newCollection != null) {
            differences.add(new Difference(propertyName, null, newCollection.size()));
        } else if (oldCollection != null && newCollection == null) {
            differences.add(new Difference(propertyName, oldCollection.size(), null));
        } else if (oldCollection.isEmpty() && newCollection.isEmpty()) {
            return;
        } else //if(Collections.disjoint(oldCollection, newCollection)) {
        {
            Field identifierField = identifierField(identifierPropertyName, fields);

            Set<Object> ignoreIdentifiers = new HashSet<>();
            compareCollectionItems(propertyName, oldCollection, newCollection, identifierField, differences, identifierPropertyName, ignoreIdentifiers, false, ignoreFields);
            compareCollectionItems(propertyName, newCollection, oldCollection, identifierField, differences, identifierPropertyName, ignoreIdentifiers, true, ignoreFields);

        }
    }

    public void compareCollectionItems(String propertyName, Collection collectionLeft, Collection collectionRight, Field identifierField, List<Difference> differences, String identifierPropertyName, Set<Object> ignoreIdentifiers, boolean reverse, Set<String> ignoreFields) throws IllegalAccessException {

        for (Object itemLeft : collectionLeft) {

            Object itemLeftIdentitier;
            if (identifierField != null) {
                itemLeftIdentitier = identifierField.get(itemLeft);
                if (ignoreIdentifiers.contains(itemLeftIdentitier)) {
                    continue;
                }
            } else {
                itemLeftIdentitier = null;
            }

            boolean foundMatchOnId = false;
            for (Object itemRight : collectionRight) {
                if (identifierField != null && itemLeftIdentitier != null) {
                    Object itemRightIdentifier = identifierField.get(itemRight);
                    if (itemLeftIdentitier.equals(itemRightIdentifier)) {

                        String newProperty = propertyName + "[" + itemRightIdentifier + "]";
                        ignoreIdentifiers.add(itemLeftIdentitier);
                        differences.addAll(compareObjects(newProperty, itemLeft, itemRight, identifierPropertyName, ignoreFields));
                        foundMatchOnId = true;
                        break;
                    }
                }
            }

            if (!foundMatchOnId) {
                if (reverse && !collectionRight.contains(itemLeft)) {
                    differences.add(new Difference(DiffType.COLLECTION_ADD, propertyName + "[]", null, itemLeft));
                    break;

                } else if (!collectionRight.contains(itemLeft)) {
                    differences.add(new Difference(DiffType.COLLECTION_REMOVE, propertyName + "[]", itemLeft, null));
                }
            }
        }

    }

    public Field[] getAllFields(Class clazz, Set<String> ignoreFields) {
        List<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        if (clazz.getSuperclass() != null) {
            fields.addAll(Arrays.asList(getAllFields(clazz.getSuperclass(), ignoreFields)));
        }
        return fields.stream()
                .filter(field -> !ignoreFields.contains(field.getName()))
                .collect(Collectors.toList()).toArray(new Field[]{});
    }

    public String diffListToString(List<Difference> differences) {
        return differences.stream().map(difference -> difference.toString()).collect(Collectors.joining("\n", "\n", "\n"));
    }
}