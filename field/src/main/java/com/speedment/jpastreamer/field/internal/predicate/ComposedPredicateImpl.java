/*
 * JPAstreamer - Express JPA queries with Java Streams
 * Copyright (c) 2020-2020, Speedment, Inc. All Rights Reserved.
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * See: https://github.com/speedment/jpa-streamer/blob/master/LICENSE
 */
package com.speedment.jpastreamer.field.internal.predicate;

import com.speedment.jpastreamer.field.predicate.ComposedPredicate;
import com.speedment.jpastreamer.field.predicate.SpeedmentPredicate;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

/**
 * Default implementation of the {@link ComposedPredicate}
 *
 * @author Emil Forslund
 * @since  3.1.2
 */
public class ComposedPredicateImpl<T, A> implements ComposedPredicate<T, A> {

    private final Function<? super T, ? extends A> firstStep;
    private final SpeedmentPredicate<A> secondStep;

    public ComposedPredicateImpl(Function<? super T, ? extends A> firstStep,
                                 SpeedmentPredicate<A> secondStep) {
        this.firstStep  = requireNonNull(firstStep);
        this.secondStep = requireNonNull(secondStep);
    }

    @Override
    public Function<T, A> firstStep() {
        @SuppressWarnings("unchecked")
        final Function<T, A> function = (Function<T, A>) firstStep;
        return function;
    }

    @Override
    public SpeedmentPredicate<A> secondStep() {
        return secondStep;
    }

    @Override
    public boolean applyAsBoolean(T object) {
        return secondStep.applyAsBoolean(firstStep.apply(object));
    }
}
