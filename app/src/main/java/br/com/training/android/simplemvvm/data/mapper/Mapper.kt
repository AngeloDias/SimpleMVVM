package br.com.training.android.simplemvvm.data.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}
