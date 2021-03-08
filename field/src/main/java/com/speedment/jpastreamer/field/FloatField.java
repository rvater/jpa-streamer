/*
 * JPAstreamer - Express JPA queries with Java Streams
 * Copyright (c) 2020-2021, Speedment, Inc. All Rights Reserved.
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * See: https://github.com/speedment/jpa-streamer/blob/master/LICENSE
 *
 */
package com.speedment.jpastreamer.field;

import com.speedment.jpastreamer.field.trait.HasComparableOperators;
import com.speedment.jpastreamer.field.trait.HasFloatValue;
import com.speedment.runtime.compute.ToFloat;
import com.speedment.jpastreamer.field.comparator.FloatFieldComparator;
import com.speedment.jpastreamer.field.internal.FloatFieldImpl;
import com.speedment.jpastreamer.field.method.FloatGetter;

/**
 * A field that represents a primitive {@code float} value.
 * <p>
 * Generated by com.speedment.sources.pattern.FieldPattern.
 * 
 * @param <ENTITY> entity type
 *
 * @author Emil Forslund
 * @since  3.0.0
 */
public interface FloatField<ENTITY> extends
        Field<ENTITY>,
        HasFloatValue<ENTITY>,
        HasComparableOperators<ENTITY, Float>,
        ToFloat<ENTITY>,
        FloatFieldComparator<ENTITY> {
    
    /**
     * Creates a new {@link FloatField} using the default implementation.
     * 
     * @param <ENTITY>   entity type
     * @param table      the field that this field belongs to
     * @param columnName the name of the database column the field represents
     * @param getter     method reference to getter in entity
     * @param unique     if column only contains unique values
     * @return           the created field
     */
    static <ENTITY> FloatField<ENTITY> create(
            Class<ENTITY> table,
            String columnName,
            FloatGetter<ENTITY> getter,
            boolean unique) {
        return new FloatFieldImpl<>(
                table, columnName, getter, unique
        );
    }
    
    @Override
    default float applyAsFloat(ENTITY entity) {
        return getAsFloat(entity);
    }
    
    @Override
    FloatFieldComparator<ENTITY> comparator();
    
    @Override
    default FloatFieldComparator<ENTITY> reversed() {
        return comparator().reversed();
    }
    
    @Override
    default FloatField<ENTITY> getField() {
        return this;
    }
}
