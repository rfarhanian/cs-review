package com.mnaseri.cs.homework.ch05.s3;

import com.mmnaseri.cs.clrs.common.Permuter;
import com.mmnaseri.cs.clrs.common.Sampler;
import com.mmnaseri.cs.qa.annotation.Quality;
import com.mmnaseri.cs.qa.annotation.Stage;

import java.lang.reflect.Array;

@Quality(Stage.UNTESTED)
public class PermuterSampler<E> implements Sampler<E> {

    private final Permuter<E> permuter;

    public PermuterSampler(Permuter<E> permuter) {
        this.permuter = permuter;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E[] sample(E[] array, int size) {
        final E[] result = (E[]) Array.newInstance(array.getClass().getComponentType(), size);
        final E[] permutation = (E[]) Array.newInstance(array.getClass().getComponentType(), array.length);
        permuter.permute(permutation);
        System.arraycopy(permutation, 0, result, 0, size);
        return result;
    }

}
