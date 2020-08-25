package br.com.training.android.simplemvvm.data.mapper

class SingleMapperImpl<I, O>(private val mapper: Mapper<I, O>): Mapper<I, O> {

    override fun map(input: I): O {
        return mapper.map(input)
    }

}
